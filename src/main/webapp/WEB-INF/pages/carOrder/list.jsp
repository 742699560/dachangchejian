<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"></c:set>
<%String type = request.getParameter("type").toString();%>

<script src="${basePath}/js/common/page.js"></script>
<div class="breadcrumbs ace-save-state" id="breadcrumbs">
    <ul class="breadcrumb">
        <li><i class="ace-icon fa fa-home home-icon"></i> <a
                href="indexUI.do">主页</a></li>
        <li class="active">车检数据管理</li>
    </ul>
</div>
<div class="page-content" style="padding: 0px;">
    <form class="form-horizontal col-md-12" id="searchform" style="padding-top: 20px;">
        <div class="form-group">

            <div class="col-md-12 col-lg-3">
                <label class="col-md-6 col-lg-3">车检日期从: </label>
                <div class="col-md-8">
                    <input type="text" id="dateBegin" name="dateBegin" placeholder="" style="height: 30px;"
                           class="col-md-12">
                </div>
            </div>
            <div class="col-md-12 col-lg-3">
                <label class="col-md-6 col-lg-3">车检日期至: </label>
                <div class="col-md-8">
                    <input type="text" id="dateEnd" name="dateEnd" placeholder="" style="height: 30px;"
                           class="col-md-12">
                </div>
            </div>
            <div class="col-md-12 col-lg-3">
                <label class="col-md-6 col-lg-3">客户姓名: </label>
                <div class="col-sm-8">
                    <input type="text" id="userName" name="userName" placeholder="" style="height: 30px;"
                           class="col-md-12">
                </div>
            </div>
            <div class="col-md-12 col-lg-3">
                <label class="col-md-6 col-lg-3">客户手机号: </label>
                <div class="col-sm-8">
                    <input type="text" id="mobile" name="mobile" placeholder="" style="height: 30px;" class="col-md-12">
                </div>
            </div>
            <div class="clear" style="margin: 40px;"></div>
            <div class="col-md-12 col-lg-3">
                <label class="col-md-6 col-lg-3">车牌号: </label>
                <div class="col-sm-8">
                    <input type="text" id="carNum" name="carNum" placeholder="" style="height: 30px;" class="col-md-12">
                </div>
            </div>
            <div class="col-md-12 col-lg-3">
                <label class="col-md-6 col-lg-3">订单状态: </label>
                <div class="col-sm-8">
                    <select name="carStatus" id="carStatus"
                            class="chosen-select form-control dynamicClear tag-input-style required">
                    </select>
                </div>
            </div>
            <div class="col-md-12 col-lg-3">
                <button type="button" onclick="search()"
                        class="btn btn-xs btn-primary"
                        style="margin-left: 5px; padding: 3px 5px; outline: 0">
                    <i class="ace-icon fa fa-search align-top bigger-125"></i>搜索
                </button>
                <button type="reset"
                        class="btn btn-xs btn-danger"
                        style="margin-left: 5px; padding: 3px 5px; outline: 0">
                    <i class="ace-icon fa fa-trash  align-top bigger-125"></i>清空
                </button>
            </div>
        </div>
    </form>

    <div class="col-md-12">
        <table id="grid-table">
            <tr>
                <th id=""></th>
            </tr>
        </table>
        <div id="grid-pager"></div>
    </div>
</div>
<script type="text/javascript">
    var type = <%= type%>;
    var grid_selector = "#grid-table";
    var pager_selector = "#grid-pager";
    var title = "";
    var userustatus = "";
    var parent_column = $(grid_selector).closest('[class*="col-"]');
    $(window).on('resize.jqGrid', function () {
        $(grid_selector).jqGrid('setGridWidth', parent_column.width());
    })
    $(document).on('settings.ace.jqGrid', function (ev, event_name, collapsed) {
        if (event_name === 'sidebar_collapsed' || event_name === 'main_container_fixed') {
            setTimeout(function () {
                $(grid_selector).jqGrid('setGridWidth', parent_column.width());
            }, 20);
        }
    })
    $(function () {
        $.extend($.jgrid.defaults, {
            recordtext: "&nbsp;&nbsp;当前{0}~{1}条,共{2}条&nbsp;",
            emptyrecords: "未查到任何数据",
            loadtext: "正在加载中...",
            pgtext: "第&nbsp;{0}&nbsp;页,共{1}页"
        });
    })

    $(function () {
        $("#dateBegin").datepicker({
            format: 'yyyy-mm-dd',
            autoclose: true,
            todayHighlight: 1,
            language: "zh-CN",
            startDate: '2016-01-01',
            minView: 2
        });
        // $('#dateBegin').datepicker('setDate', new Date());
        $("#dateEnd").datepicker({
            format: 'yyyy-mm-dd',
            autoclose: true,
            todayHighlight: 1,
            language: "zh-CN",
            startDate: '2016-01-01',
            minView: 2
        });
        var timeSubTF = type == 2 ? false : true;
        var timesText = type == 1 ? '车检日期' : '预约日期';
        var initParamData = {"type": type, "dateBegin": type == 1 ? formatDateYYYYMMDD(new Date()) : ""};
        if (type == 1)
            $('#dateBegin').datepicker('setDate', new Date());
        $(grid_selector).jqGrid({
            url: 'order/queryOrder.do',
            datatype: "json",
            postData: initParamData,
            mtype: "post",
            colModel: [
                {name: 'id', index: 'id', editable: false, hidden: true, editoptions: {readonly: true}},
                {name: 'stationId', index: 'station_id', hidden: true, editable: true, sortable: true},
                {label: '订单号', name: 'orderNumber', index: 'order_number', editable: true, sortable: true},
                {label: '用户姓名', name: 'username', index: 'username', editable: false},
                {label: '手机号', name: 'mobile', index: 'mobile', editable: true},
                {label: '车牌号', name: 'carNum', index: 'car_num', editable: false},
                {label: '订单类型', name: 'type', index: 'type', editable: false, formatter: typeFormatter},
                {label: timesText, name: 'times', index: 'times', editable: false},
                {label: '预约时间', name: 'timeSub', index: 'time_sub', editable: false, hidden: timeSubTF},
                {label: '支付金额', name: 'payAmount', index: 'pay_amount', editable: false},
                {label: '提交时间', name: 'createTime', index: 'create_time', editable: false, formatter: timeFormatter},
                {label: '状态', name: 'status', index: 'status', editable: false, formatter: statusFormatter},
                {label: '打印状态', name: 'printStatus', index: 'print_status', editable: false, formatter: printFormatter}
            ],
            prmNames: {page: "page", rows: "pageSize"},
            viewrecords: true,
            rowNum: 20,
            pager: pager_selector,
            altRows: true,
            sortorder: 'desc',
            height: '100%',
            multiselect: false,
            multiboxonly: false,
            loadComplete: function () {
                var table = this;
                setTimeout(function () {
                    updatePagerIcons(table);
                }, 0);
            },
            rownumbers: true,
            shrinkToFit: true,
            autowidth: true,
            jsonReader: {
                root: "data.list",    // json中代表实际模型数据的入口
                page: "data.page",    // json中代表当前页码的数据
                total: "data.pages",    // json中代表页码总数的数据
                records: "data.total", // json中代表数据行总数的数据
                repeatitems: false
            }
        });
        $(grid_selector).closest(".ui-jqgrid-bdiv").css({'overflow-x': 'hidden'});
        $(grid_selector).jqGrid('navGrid', pager_selector,
            {
                edit: false,
                add: false,
                del: false,
                search: false,
                refresh: true,
                refreshicon: 'ace-icon fa fa-refresh green',
                refreshtitle: "刷新",
                refreshtext: "刷新",
                view: false,
            })
            .navButtonAdd(pager_selector, {
                caption: "修改&ensp;",
                buttonicon: "ace-icon fa fa-pencil blue",
                onClickButton: function () {
                    showEditcompany();
                },
                position: 'first',
                title: "修改信息"
            }).navButtonAdd(pager_selector, {
            caption: "删除&ensp;",
            buttonicon: "ace-icon fa fa-trash red",
            onClickButton: function () {
                deletecompany();
            },
            position: 'first',
            title: "删除信息"
        });

    })


    function deletecompany() {
        var id = $(grid_selector).jqGrid('getGridParam', 'selrow');
        if (id == "" || id == null) {
            swal("错误", "请选择要删除的选项！");
            return;
        }

        var rowData = $(grid_selector).jqGrid('getRowData', id);
        var param = {'id': rowData.id};
        var url = 'order/deleteCarOrder.do';
        swal({
            title: "是否确定删除",
            text: "将无法恢复该数据！",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "确定！",
            cancelButtonText: "取消！",
            closeOnConfirm: false
        }, function () {
            $.post(url, param, function (result) {
                $(grid_selector).trigger("reloadGrid");
            })
            swal("已删除！");
        });
    }

    function showEditcompany() {
        var cid = $(grid_selector).jqGrid('getGridParam', 'selrow');
        if (cid == "" || cid == null) {
            swal("错误", "请选择要修改的选项！");
            return;
        }
        var rowData = $(grid_selector).jqGrid('getRowData', cid);
        $('#container').data('rowData', rowData);
        $('#container').load('carPrice/carPriceEditUI.do');
    }


    function search() {
        $(grid_selector).jqGrid('setGridParam', {
            postData: {
                'type': type,
                'userName': $("#userName").val(),
                'mobile': $("#mobile").val(),
                'dateBegin': $("#dateBegin").val(),
                'dateEnd': $("#dateEnd").val(),
                'carNum': $("#carNum").val(),
                'status': $("#carStatus").val()
            }

        }).trigger("reloadGrid"); //重新载入
    }

    function updatePagerIcons(table) {
        var replacement =
            {
                'ui-icon-seek-first': 'ace-icon fa fa-angle-double-left bigger-140',
                'ui-icon-seek-prev': 'ace-icon fa fa-angle-left bigger-140',
                'ui-icon-seek-next': 'ace-icon fa fa-angle-right bigger-140',
                'ui-icon-seek-end': 'ace-icon fa fa-angle-double-right bigger-140'
            };
        $('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function () {
            var icon = $(this);
            var $class = $.trim(icon.attr('class').replace('ui-icon', ''));

            if ($class in replacement) icon.attr('class', 'ui-icon ' + replacement[$class]);
        })
    }


    function statusFormatter(cellvalue, options, rowObject) {
        var str = "";
        switch (rowObject.status) {
            case 10:
                str = "<span class='label label-default'><!--<i class='icon-warning-sign bigger-120'></i>-->" + rowObject.statusStr + "</span>";
                break;
            case 15:
                str = "<span class='label label-inverse'><i class='icon-warning-sign bigger-120'></i>" + rowObject.statusStr + "</span>";
                break;
            case 20:
                str = "<span class='label label-danger'>" + rowObject.statusStr + "</span>";
                break;
            case 25:
                str = "<span class='label label-success'>" + rowObject.statusStr + "</span>";
                break;
            case 30:
                str = "<span class='label label-warning'>" + rowObject.statusStr + "</span>";
                break;
            case 35:
                str = "<span class='label label-warning'>" + rowObject.statusStr + "</span>";
                break;
            case 40:
                str = "<span class='label label-info'>" + rowObject.statusStr + "</span>";
                break;
        }
        return str;
    }

    function timeFormatter(cellvalue, options, rowObject) {
        var str = formatDate(new Date(rowObject.createTime));
        return str;
    }

    function typeFormatter(cellvalue, options, rowObject) {
        var str = "";
        switch (rowObject.type) {
            case 1:
                str = "<span class='label label-warning'>现场车检</span>";
                break;
            case 2:
                str = "<span class='label label-info'>预约车检</span>";
                break;
            case 3:
                str = "<span class='label label-danger'>其他车检</span>";
                break;
        }
        return str;
    }

    function printFormatter(cellvalue, options, rowObject) {
        var str = "";
        switch (rowObject.printStatus) {
            case 1:
                str = "<span class='label label-default'>未打印</span>";
                break;
            case 2:
                str = "<span class='label label-success'>已打印</span>";
                break;
        }
        return str;
    }


    function loadCarOrderStatus() {
        var param = {'type': 'carOrderStatus', 'timestamp': '1', 'sign': '78d88a8cb24785856d461ec7e4b6955d'};
        $("#carStatus").append("<option selected value='' >请选择</option>");
        $.post("api/dataDir.do", param, function (result) {
            if (result.status == SUCCESS) {
                for (var i = 0; i < result.data.list.length; i++) {
                    var item = result.data.list[i];
                    $("#carStatus").append("<option value='" + item.value + "'>" + item.name + "</option>");
                }
            }
        });
    }

    loadCarOrderStatus();
</script>