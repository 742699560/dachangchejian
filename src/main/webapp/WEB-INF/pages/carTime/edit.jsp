<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"></c:set>
<script src="${basePath}/static/libs/jquery-clock-timepicker.min.js" type="text/javascript"></script>

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
                                            <font color="red">*</font>时间从：
                                        </div>
                                        <div class="col-sm-8">
                                            <input type="text" name="timeSubBegin" id="timeSubBegin"
                                                   class="form-control dynamicClear required " placeholder="时间从">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-3 control-label"><font color="red">*</font>时间至：</div>
                                        <div class="col-sm-8">
                                            <input type="text" name="timeSubEnd" id="timeSubEnd"
                                                   class="form-control dynamicClear required " placeholder="时间至">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-3 control-label">
                                            <font color="red">*</font>可预约车辆：
                                        </div>
                                        <div class="col-sm-8">
                                            <input type="number" name="timeNum" id="timeNum"
                                                   class="form-control dynamicClear required " max="999" min="1" placeholder="可预约车辆">
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
            var url = params.id ? 'carTime/updateCarTime.do' : 'carTime/saveCarTime.do';
            $.post(url, params, function (result) {
                if (result.status == SUCCESS) {
                    sweetAlert('操作成功！');
                    clearData();
                    $('#container').load('carTime/carTimeUI.do');
                } else {
                    sweetAlert(result.message);
                }
            })
        }
    }


    //点击返回按钮
    function gobackUserList() {
        clearData();
        $('#container').load('carTime/carTimeUI.do');
    }

    //设置按钮文字
    function setBtnVal() {
        var data = $('#container').data('rowData');
        if (data) {
            $('#btn_ok').val('修改');
            $('#editTitle').text('修改');
            loadEditUserForm(data);
        } else {
            $('#btn_ok').val('保存');
            $('#editTitle').text('新增');
            point = new BMap.Point(116.905114, 39.921127);
        }
    }

    //回显
    function loadEditUserForm(data) {
        var timeSub = data.timeSub;
        var subArr = timeSub.split("-");
        $('#timeSubBegin').val(subArr[0]);
        $('#timeSubEnd').val(subArr[1]);
        $('#timeNum').val(data.timeNum);
    }

    //获取表单参数
    function getFormParams() {
        var timeSubBegin = $('#timeSubBegin').val();
        var timeSubEnd = $('#timeSubEnd').val();
        var timeNum = $('#timeNum').val();
        var timeSub = timeSubBegin + "-" + timeSubEnd;

        var params = {
            'timeSub': timeSub,
            'timeNum': timeNum
        }
        return params;
    }

    //点击返回，保存，修改按钮，清除editForm数据
    function clearData() {
        $('#editCompanyForm .dynamicClear').val('');
        $('#container').data('rowData', '');
    }


    $('input[name="timeSubBegin"]').clockTimePicker({});
    $('input[name="timeSubEnd"]').clockTimePicker({});
</script>