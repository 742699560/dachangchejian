<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"></c:set>
<script src="${basePath}/js/common/page.js"></script>
<div class="breadcrumbs ace-save-state" id="breadcrumbs">
    <ul class="breadcrumb">
        <li><i class="ace-icon fa fa-home home-icon"></i> <a
                href="indexUI.do">主页</a></li>
        <li class="active">车检所管理</li>
    </ul>
</div>
<div class="page-content" style="padding: 0px;">
    <form class="form-horizontal col-md-12" id="searchform" style="padding-top: 20px;">
        <div class="form-group">
            <div class="col-md-12 col-lg-3">
                <label class="col-md-2 col-lg-4">车检所名称: </label>
				<input type="text"  id="search_tcarcode" name="search_tcarcode"  placeholder=""  style="height: 30px;" class="col-md-7">
            </div>
            <div class="col-md-12 col-lg-3">
                <button type="button" onclick="search()"
                        class="btn btn-xs btn-primary"
                        style="margin-left: 5px; padding: 3px 5px; outline: 0">
                    <i class="ace-icon fa fa-search align-top bigger-125"></i>搜索
                </button>
                <button type="reset" onclick="searchRset()"
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
            url: 'company/findPageObjects.do',
            datatype: "json",
            mtype: "post",
            colModel: [
                {name: 'id', index: 'id', editable: false, hidden: true, editoptions: {readonly: true}},
                {label: '车检所名称', name: 'name', index: 'name', editable: true, sortable: true},
                {label: '联系人', name: 'contats', index: 'contats', editable: false},
                {label: '联系电话', name: 'phone', index: 'phone', editable: true},
                {label: '详细地址', name: 'address', index: 'address', editable: false},
                {label: '纬度', name: 'lat', index: 'lat', editable: false},
                {label: '经度', name: 'lng', index: 'lng', editable: true}
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
            /* caption: "企业列表", */
            rownumbers: true,
            shrinkToFit: true,
            autowidth: true,
            jsonReader: {
                root: "data.list",    // json中代表实际模型数据的入口
                page: "page",    // json中代表当前页码的数据
                total: "pages",    // json中代表页码总数的数据
                records: "total", // json中代表数据行总数的数据
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
        }).navButtonAdd(pager_selector, {
            caption: "添加&ensp;",
            buttonicon: "ace-icon fa fa-plus-circle purple",
            onClickButton: function () {
                showAddcompany();
            },
            position: 'first',
            title: "添加企业"
        });

    })

    function ordinarilyExamine() {
        var cid = $(grid_selector).jqGrid('getGridParam', 'selrow');
        if (cid == "" || cid == null) {
            swal("错误", "请选择一条数据！");
            return;
        }
        var rowData = $(grid_selector).jqGrid('getRowData', cid);
        $('#container').data('rowData', rowData);
        var url = 'company/examinecomUI.do';
        $('#container').load(url);

    }

    function showAddcompany() {
        $('#container').data('rowData', null);
        var url = 'company/editCompanyUI.do';
        $('#container').load(url);
    }

    function deletecompany() {
        var cid = $(grid_selector).jqGrid('getGridParam', 'selrow');
        if (cid == "" || cid == null) {
            swal("错误", "请选择要删除的选项！");
            return;
        }

        var rowData = $(grid_selector).jqGrid('getRowData', cid);
        var param = {'cid': rowData.cid};
        var url = 'company/deleteCompany.do';
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
        $('#container').load('company/editCompanyUI.do');
    }


    function search() {
        $(grid_selector).jqGrid('setGridParam', {
            postData: {
                'name': $('#search_tcarcode').val()
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