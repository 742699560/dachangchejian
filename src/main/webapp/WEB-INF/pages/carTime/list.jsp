<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"></c:set>
<script src="${basePath}/js/common/page.js"></script>
<div class="breadcrumbs ace-save-state" id="breadcrumbs">
    <ul class="breadcrumb">
        <li><i class="ace-icon fa fa-home home-icon"></i> <a
                href="indexUI.do">主页</a></li>
        <li class="active">预约时间管理</li>
    </ul>
</div>
<div class="page-content" style="padding: 0px;">

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
    var grid_selector = "#grid-table";
    var pager_selector = "#grid-pager";
    var type = ""
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
        $("#search_start").datepicker({
            format: 'yyyy-mm-dd',
            autoclose: true,
            language: "zh-CN",
            startDate: '2016-01-01',
            minView: 2
        });
        $("#search_end").datepicker({
            format: 'yyyy-mm-dd',
            autoclose: true,
            language: "zh-CN",
            startDate: '2016-01-01',
            minView: 2
        });
        $(grid_selector).jqGrid({
            url: 'carTime/queryCarTime.do',
            datatype: "json",
            mtype: "post",
            colModel: [
                {name: 'id', index: 'id', editable: false, hidden: true, editoptions: {readonly: true}},
                { name: 'stationId', index: 'station_id', hidden: true,editable: true, sortable: true},
                {label: '时间从至', name: 'timeSub', index: 'time_sub', editable: true, sortable: true},
                {label: '可预约人数', name: 'timeNum', index: 'time_num', editable: false}
            ],
            prmNames: {page: "page", rows: "pageSize"},
            viewrecords: true,
            rowNum: 20,
            pager: pager_selector,
            altRows: true,
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
                    showEditPrice();
                },
                position: 'first',
                title: "修改信息"
            }).navButtonAdd(pager_selector, {
            caption: "删除&ensp;",
            buttonicon: "ace-icon fa fa-trash red",
            onClickButton: function () {
                deleteTime();
            },
            position: 'first',
            title: "删除信息"
        }).navButtonAdd(pager_selector, {
            caption: "添加&ensp;",
            buttonicon: "ace-icon fa fa-plus-circle purple",
            onClickButton: function () {
                showAddTime();
            },
            position: 'first',
            title: "添加"
        });

    })


    function showAddTime() {
        $('#container').data('rowData', null);
        var url = 'carTime/carTimeEditUI.do';
        $('#container').load(url);
    }

    function deleteTime() {
        var id = $(grid_selector).jqGrid('getGridParam', 'selrow');
        if (id == "" || id == null) {
            swal("错误", "请选择要删除的选项！");
            return;
        }

        var rowData = $(grid_selector).jqGrid('getRowData', id);
        var param = {'id': rowData.id};
        var url = 'carTime/deleteCarTime.do';
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

    function showEditPrice() {
        var cid = $(grid_selector).jqGrid('getGridParam', 'selrow');
        if (cid == "" || cid == null) {
            swal("错误", "请选择要修改的选项！");
            return;
        }
        var rowData = $(grid_selector).jqGrid('getRowData', cid);
        $('#container').data('rowData', rowData);
        $('#container').load('carTime/carTimeEditUI.do');
    }


    function search() {
        $(grid_selector).jqGrid('setGridParam', {
            postData: {
                'carType': $('#carType').val()
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

</script>