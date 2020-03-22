<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"></c:set>

<div class="breadcrumbs ace-save-state" id="breadcrumbs">
    <ul class="breadcrumb">
        <li><i class="ace-icon fa fa-home home-icon"></i> <a
                href="indexUI.do">主页</a></li>
        <li><a href="#">车检所管理</a></li>
    </ul>
</div>

<div id="wrapper">
    <div class="main">
        <div class="main-content" style="padding: 0px;">
            <div class="container-fluid">
                <div class="container" style="width: 100%;">
                    <div class="panel panel-default">
                        <div class="panel-heading" id="editTitle"
                             style="background: #ececec"></div>
                        <div class="row">
                            <div class="col-md-6">
                                <form class="form-horizontal" id="editCompanyForm"
                                      style="padding-left: 100px; padding-top: 20px;">
                                    <div class="form-group">
                                        <div class="col-sm-3 control-label">
                                            <font color="red">*</font>车辆类型：
                                        </div>
                                        <div class="col-sm-8">
                                            <select name="carType" id="carType"
                                                    class="chosen-select form-control dynamicClear tag-input-style required"
                                                    id="form-field-select-4" data-placeholder="请选择车辆类型">
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-3 control-label"><font color="red">*</font>车身长度从(米)：</div>
                                        <div class="col-sm-8">
                                            <input type="text" name="heightFrom" id="heightFrom"
                                                   class="form-control dynamicClear required " placeholder="车身长度从">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-3 control-label">
                                            <font color="red">*</font>车身长度至(米)：
                                        </div>
                                        <div class="col-sm-8">
                                            <input type="text" name="heightEnd" id="heightEnd"
                                                   class="form-control dynamicClear required " placeholder="车身长度至">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-3 control-label"><font color="red">*</font>价格：</div>
                                        <div class="col-sm-8">
                                            <input type="text" name="price" id="price"
                                                   class="form-control dynamicClear required" placeholder="价格">
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-3 control-label"></div>
                            <input type="button" class="btn btn-primary" id="btn_ok">
                            &nbsp;&nbsp; <input type="button" value="返回"
                                                class="btn btn-warning" id="btn_return">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    var SUCCESS = 200;
    //设置按钮的文字,以及编辑框的标题
    setBtnVal();
    //点击返回按钮
    $('#btn_return').click(gobackUserList);
    //点击保存/修改按钮
    $('#btn_ok').click(commitUserForm);

    var alllng = null;
    var alllat = null;
    var point;

    //点击保存/修改按钮
    function commitUserForm() {
        if ($('#editCompanyForm').valid()) {
            var params = getFormParams();
            var data = $('#container').data('rowData');
            if (data)
                params.id = data.id;
            var url = params.id ? 'carPrice/updateCarPrice.do' : 'carPrice/saveCarPrice.do';
            $.post(url, params, function (result) {
                if (result.status == SUCCESS) {
                    sweetAlert('操作成功！');
                    clearData();
                    $('#container').load('carPrice/carPriceUI.do');
                } else {
                    sweetAlert(result.message);
                }
            })
        }
    }

    //获取表单参数
    function getFormParams() {
        var carType = $('#carType').val();
        var heightFrom = $('#heightFrom').val();
        var heightEnd = $('#heightEnd').val();
        var price = $('#price').val();

        var params = {
            'carType': carType,
            'heightFrom': heightFrom,
            'heightEnd': heightEnd,
            'price': price
        }
        return params;
    }

    //点击返回按钮
    function gobackUserList() {
        clearData();
        $('#container').load('carPrice/carPriceUI.do');
    }

    //设置按钮文字
    function setBtnVal() {
        var data = $('#container').data('rowData');
        if (data) {
            $('#btn_ok').val('修改');
            $('#editTitle').text('修改');
            loadEditUserForm(data);
            loadCarType(data);
        } else {
            loadCarType();
            $('#btn_ok').val('保存');
            $('#editTitle').text('新增');
            point = new BMap.Point(116.905114, 39.921127);
        }
    }

    function loadCarType(data) {
        if (!data)
            data = {};
        var param = {'type': 'carType', 'timestamp': '1', 'sign': '78d88a8cb24785856d461ec7e4b6955d'};
        $.post("api/dataDir.do", param, function (result) {
            if (result.status == SUCCESS) {
                for (var i = 0; i < result.data.list.length; i++) {
                    var item = result.data.list[i];
                    $("#carType").append("<option value='" + item.name + "'>" + item.name + "</option>");
                }
                $("#carType option[value='"+data.carType+"']").attr("selected", true);
            }
        });
    }

    //回显
    function loadEditUserForm(data) {

        $('#heightFrom').val(data.heightFrom);
        $('#heightEnd').val(data.heightEnd);
        $('#price').val(data.price);
    }

    //点击返回，保存，修改按钮，清除editForm数据
    function clearData() {
        $('#editCompanyForm .dynamicClear').val('');
        $('#container').data('rowData', '');
    }

</script>