<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <title>大厂车检管理平台</title>

    <meta name="description"
          content="Dynamic tables and grids using jqGrid plugin"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <link rel="stylesheet" href="${basePath }/assets/css/bootstrap.min.css"/>
    <link rel="stylesheet"
          href="${basePath }/assets/font-awesome/4.5.0/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="${basePath }/assets/css/jquery-ui.min.css"/>
    <link rel="stylesheet"
          href="${basePath }/assets/css/bootstrap-datepicker3.min.css"/>
    <link rel="stylesheet" href="${basePath }/assets/css/ui.jqgrid.min.css"/>
    <link rel="stylesheet"
          href="${basePath }/assets/css/fonts.googleapis.com.css"/>
    <link rel="stylesheet" href="${basePath }/assets/css/ace.min.css"
          class="ace-main-stylesheet" id="main-ace-style"/>
    <link rel="stylesheet" href="${basePath }/assets/css/ace-skins.min.css"/>
    <link rel="stylesheet" href="${basePath }/assets/css/ace-rtl.min.css"/>
    <link rel="stylesheet"
          href="${basePath }/static/plugins/ztree/css/metroStyle/metroStyle.css"
          type="text/css">
    <script src="${basePath }/assets/js/ace-extra.min.js"></script>
    <link rel="stylesheet"
          href="${basePath }/bootstrap/css/sweetalert.min.css"/>
    <link href="${basePath }/js/common/style.css" rel="stylesheet">
    <link rel="stylesheet" href="${basePath }/assets/css/chosen.min.css"/>
    <link rel="stylesheet" href="${basePath }/assets/css/bootstrap-colorpicker.min.css"/>
    <style type="text/css">
        .anchorBL {
            display: none;
        }
    </style>
    <style type="text/css">
        .error {
            color: red;
        }

    </style>


</head>

<body class="no-skin">
<div id="navbar" class="navbar navbar-default          ace-save-state">
    <div class="navbar-container ace-save-state" id="navbar-container">
        <div class="navbar-header pull-left">
            <a href="indexUI.do" class="navbar-brand"> <small> <i
                    class="fa fa-leaf"></i> 车检管理系统
            </small>
            </a>
        </div>
        <div class="navbar-buttons navbar-header pull-right"
             role="navigation">
            <ul class="nav ace-nav">

                <li class="light-blue dropdown-modal"><a
                        data-toggle="dropdown" href="#" class="dropdown-toggle"> <span
                        class="user-info"> <small>当前登录用户</small>
								${currentUser.username }
						</span> <i class="ace-icon fa fa-caret-down"></i>
                </a>

                    <ul
                            class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
                        <li><a href="logout.do"> <i
                                class="ace-icon fa fa-power-off"></i> 退出
                        </a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</div>
<div class="main-container ace-save-state" id="main-container">
    <script type="text/javascript">
        try {
            ace.settings.loadState('main-container')
        } catch (e) {
        }
    </script>

    <div id="sidebar"
         class="sidebar                  responsive                    ace-save-state">
        <script type="text/javascript">
            try {
                ace.settings.loadState('sidebar')
            } catch (e) {
            }
        </script>

        <div class="sidebar-shortcuts" id="sidebar-shortcuts">
            <div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
                <button class="btn btn-success" onclick="window.location.href='indexUI.do'">
                    <i class="ace-icon fa fa-home"></i>
                </button>

                <button class="btn btn-info">
                    <i class="ace-icon fa fa-pencil"></i>
                </button>

                <button class="btn btn-warning">
                    <i class="ace-icon fa fa-users"></i>
                </button>

                <button class="btn btn-danger">
                    <i class="ace-icon fa fa-cogs"></i>
                </button>
            </div>

            <div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
                <span class="btn btn-success"></span> <span class="btn btn-info"></span>

                <span class="btn btn-warning"></span> <span class="btn btn-danger"></span>
            </div>
        </div>

        <ul class="nav nav-list" id="menuList">
        </ul>
        <div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
            <i id="sidebar-toggle-icon"
               class="ace-icon fa fa-angle-double-left ace-save-state"
               data-icon1="ace-icon fa fa-angle-double-left"
               data-icon2="ace-icon fa fa-angle-double-right"></i>
        </div>
    </div>

    <div class="main-content">
        <div class="main-content-inner" id="container">
            <div class="breadcrumbs ace-save-state" id="breadcrumbs">

                <div class="row" style="padding-left:10px; padding-top: 20px;">
                    <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
                        <div class="info-box blue-bg">
                            <i class="fa fa-bookmark"></i>
                            <div class="count" id="comfist"></div>
                            <div class="title" id="fistdata">今日现场车检订单数</div>
                        </div><!--/.info-box-->
                    </div><!--/.col-->

                    <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
                        <div class="info-box brown-bg">
                            <i class="fa fa-tachometer"></i>
                            <div class="count" id="comtow"></div>
                            <div class="title" id="towdata">今日进行车检的预约订单数</div>
                        </div><!--/.info-box-->
                    </div><!--/.col-->

                    <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
                        <div class="info-box dark-bg">
                            <i class="fa fa-bell"></i>
                            <div class="count" id="comthree"></div>
                            <div class="title" id="threedata">今日订单总数</div>
                        </div><!--/.info-box-->
                    </div><!--/.col-->

                    <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
                        <div class="info-box green-bg">
                            <i class="fa fa-comment"></i>
                            <div class="count" id="comfor"></div>
                            <div class="title" id="fordata">今日已完成车检订单数</div>
                        </div>
                    </div>
                </div>
              <%--  <div class="row">
				<div class="col-md-6">
					<table class="table table-striped table-bordered table-hover" id="comListtableone"
						   style="margin-left: 10px; margin-bottom: 0px;">
						<thead>
						<tr>
							<th data-align="center" data-width="100px">许可证已到期旅行社</th>
							<th data-align="center" data-width="150px">许可证到期时间</th>
						</tr>
						</thead>

						<tbody id="tbodyforhome">

						</tbody>
					</table>
					<div style="margin-left: 10px;" class="modal-footer no-margin-top" id="pageId">
						<ul class="pagination pull-right no-margin">
							<li class="prev"><a class="pre"> <i
									class="ace-icon fa fa-angle-double-left"></i>
							</a></li>
							<li class="next"><a class="next"> <i
									class="ace-icon fa fa-angle-double-right"></i>
							</a></li>
						</ul>
					</div>
				</div>
				<div class="col-md-6">
					<div id="line1" style="height: 350px;"></div>

				</div>
				<div class="col-md-6">
					<table class="table table-striped table-bordered table-hover" id="comListtabletwo"
						   style="margin-left: 10px;margin-bottom: 0px;">
						<thead>
						<tr>
							<th data-align="center" data-width="100px">许可证将到期旅行社</th>
							<th data-align="center" data-width="130px">许可证到期时间</th>
						</tr>
						</thead>

						<tbody id="tbodyforabout">

						</tbody>
					</table>
					<div style="margin-left: 10px;" class="modal-footer no-margin-top" id="pageIds">
						<ul class="pagination pull-right no-margin">
							<li class="prev"><a class="pret"> <i
									class="ace-icon fa fa-angle-double-left"></i>
							</a></li>
							<li class="next"><a class="nextt"> <i
									class="ace-icon fa fa-angle-double-right"></i>
							</a></li>
						</ul>
					</div>
				</div>
				<div class="col-md-6">
					<div id="line2" style="height: 350px;"></div>
				</div>

			</div>--%>

            </div>
            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <table id="grid-table"></table>
                        <div id="grid-pager"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="${basePath }/assets/js/jquery-2.1.4.min.js"></script>
<script
        src="${basePath}/static/plugins/treegrid/jquery.treegrid.min.js"></script>
<script type="text/javascript">
    if ('ontouchstart' in document.documentElement)
        document
            .write("<script src='${basePath }/assets/js/jquery.mobile.custom.min.js'>"
                + "<" + "/script>");
</script>
<script src="${basePath}/jquery/jquery.validate.min.js"></script>
<script src="${basePath}/jquery/jquery.form.js"></script>
<script src="${basePath }/assets/js/bootstrap.min.js"></script>
<script src="${basePath }/assets/js/bootstrap-datepicker.min.js"></script>
<script src="${basePath }/assets/js/bootstrap-timepicker.min.js"></script>
<script src="${basePath }/assets/js/moment.min.js"></script>
<script src="${basePath }/assets/js/daterangepicker.min.js"></script>
<script src="${basePath }/assets/js/bootstrap-datetimepicker.min.js"></script>
<script src="${basePath }/assets/js/bootstrap-colorpicker.min.js"></script>
<script src="${basePath }/assets/js/jquery.knob.min.js"></script>
<script src="${basePath }/assets/js/jquery.jqGrid.min.js"></script>
<script src="${basePath }/assets/js/grid.locale-en.js"></script>
<script src="${basePath }/assets/js/ace-elements.min.js"></script>
<script src="${basePath }/assets/js/ace.min.js"></script>

<script
        src="${basePath}/static/plugins/treegrid/jquery.treegrid.bootstrap3.js"></script>
<script
        src="${basePath}/static/plugins/treegrid/jquery.treegrid.extension.js"></script>
<script src="${basePath}/static/plugins/treegrid/tree.table.js"></script>
<script
        src="${basePath}/static/plugins/ztree/js/jquery.ztree.all.min.js"></script>
<script type="text/javascript" src="${basePath }/js/common/menu.js"></script>
<script src="${basePath }/bootstrap/js/sweetalert.min.js"></script>
<script src="${basePath }/assets/js/jquery-ui.custom.min.js"></script>
<script src="${basePath }/assets/js/jquery.ui.touch-punch.min.js"></script>
<script src="${basePath }/assets/js/chosen.jquery.min.js"></script>
<script src="${basePath }/assets/js/spinbox.min.js"></script>
<script src="${basePath }/assets/js/autosize.min.js"></script>
<script src="${basePath }/assets/js/bootstrap-tag.min.js"></script>
<script src="${basePath }/assets/js/jquery.inputlimiter.min.js"></script>
<script src="${basePath }/assets/js/jquery.maskedinput.min.js"></script>
<script
        src="${basePath }/assets/js/date-time/locales/bootstrap-datepicker.zh-CN.js"></script>
<script
        src="http://api.map.baidu.com/getscript?v=2.0&ak=YNlx4aWosE7KWvnAGLRkDzdv&services=&t=20180323171755"></script>
<script type="text/javascript"
        src="http://api.map.baidu.com/library/AreaRestriction/1.2/src/AreaRestriction_min.js"></script>
<!-- ace scripts -->
<!--  <script type="text/javascript" src="themes/insdep/jquery.insdep-extend.min.js"></script> -->
<script src="${basePath }/js/common/echarts.js"></script>
<script src="${basePath }/js/common/bmap.js?v=1"></script>
<script src="${basePath }/js/common/common.js?v=1"></script>
<script type="text/javascript">
    $(function () {
        loadMenuList()
        $('#menuList').on('click', '#menuBtn', loadContainer);
    });
</script>
<script src="${basePath }/js/common/homemessage.js"></script>

<script>
    $(document).ready(function () {
        /*$("#pageId").on('click','.pre,.next',jumpToPageone);
        $("#pageIds").on('click','.pret,.nextt',jumpToPagetow);

        doGetObjects();
        doGetObjectcomfordata();
        doGetObjectfor();

        function doGetObjects(){
            var currentPage=$('#pageId').data('pageCurrent');
            params={"currentPage":currentPage}
            var url = 'homemessage/findDueapprove.do';
            $.post(url,params,function(result){
                if(result.state==SUCCESS){
                    setTableRows(result.data.pageObj.dataList);
                    setPaginationone(result.data.pageObj);
                }else{
                    sweetAlert(result.message);
                }
            })
        }

        function doGetObjectcomfordata(){
            var currentPage=$('#pageIds').data('pageCurrent');
            params={"currentPage":currentPage}
            var url = 'homemessage/findAboutprove.do';
            $.post(url,params,function(result){
                if(result.state==SUCCESS){
                    setTableRowscomdata(result.data.pageObj.dataList);
                    setPaginationtwo(result.data.pageObj);
                }else{
                    sweetAlert(result.message);
                }
            })
        }


        //设置分页
        function setPaginationone(pageObj){
         $("#pageId").data("pageCount",pageObj.pageCount);
         $("#pageId").data("pageCurrent",pageObj.pageCurrent);
        }
        function setPaginationtwo(pageObj){
            $("#pageIds").data("pageCount",pageObj.pageCount);
            $("#pageIds").data("pageCurrent",pageObj.pageCurrent);
           }

        //分页（一）
        function jumpToPageone(){
            var clazz=$(this).attr("class");
            var pageCurrent=$('#pageId').data("pageCurrent");
            var pageCount=$('#pageId').data("pageCount")
            if(clazz=='pre'&&pageCurrent>1){
                pageCurrent--;
            }
            if(clazz=="next"&&pageCurrent<pageCount){
                pageCurrent++;
            }
            $('#pageId').data("pageCurrent",pageCurrent);
            doGetObjects();
        }
        //分页（二）
        function jumpToPagetow(){
            var clazz=$(this).attr("class");
            var pageCurrent=$('#pageIds').data("pageCurrent");
            var pageCount=$('#pageIds').data("pageCount")
            if(clazz=='pret'&&pageCurrent>1){
                pageCurrent--;
            }
            if(clazz=="nextt"&&pageCurrent<pageCount){
                pageCurrent++;
            }
            $('#pageIds').data("pageCurrent",pageCurrent);
            doGetObjectcomfordata();
        }

        function setTableRows(list){
            var tBody=$('#tbodyforhome');
            tBody.empty();
            var tds='<td>[name]</td>'+
                    '<td>[expire]</td>';
            for(var i in list){
                var tr=$('<tr></tr>');
                tr.append(tds.replace('[name]',list[i].name)
                        .replace('[expire]',"<span class='label label-danger'>"+list[i].expire+"</span>"));
                tBody.append(tr);
            }
        }
        function setTableRowscomdata(list){
            var tBody=$('#tbodyforabout');
            tBody.empty();
            var tds='<td>[name]</td>'+
                    '<td>[expire]</td>';
            for(var i in list){
                var tr=$('<tr></tr>');
                tr.append(tds.replace('[name]',list[i].name)
                        .replace('[expire]',"<span class='label label-warning'>"+list[i].expire+"</span>"));
                tBody.append(tr);
            }
        }

        function doGetObjectfor(){
            var url = 'homemessage/findPageNumber.do';
            $.post(url,function(result){
                setTableRowsfist(result);
                       var line1 = echarts.init(document.getElementById('line1'));
                    var line2 = echarts.init(document.getElementById('line2'));
                    //占比图
                    line1.setOption({
                        title : {
                            text : '占比图',
                            x : 0,
                            y : 0
                        },
                        tooltip : {
                            trigger: 'item',
                            formatter: "{b} : {c} ({d}%)"
                        },
                        calculable : true,
                        series : [
                            {
                                name:'占比',
                                type:'pie',
                                radius : '70%',
                                center: ['50%', '55%'],
                                data:[
                                    {value:result.dueapprove,  name:'许可证已过期旅行社数量'},
                                    {value:result.aboutprove, name:'许可证将过期旅行社数量'},
                                    {value:result.talnumner, name:'注册旅行社总量'},
                                    {value:result.comnumner, name:'注册公司总量'},
                                ]
                            }]
                    });

                    line2.setOption({
                        title : {
                            text: '柱状图',
                        },
                        tooltip : {
                            trigger: 'axis'
                        },
                        legend: {
                            data:['许可证已过期旅行社','许可证将过期旅行社','注册旅行社总量','注册公司总量'],
                            y: 30
                        },
                        xAxis : [
                            {
                                type : 'category',
                                data : ['总数']
                            }
                        ],
                        yAxis : [
                            {
                                type : 'value'
                            }
                        ],
                        series : [
                             {
                                name:'许可证已过期旅行社',
                                type:'bar',
                                data:[result.dueapprove],
                            }, {
                                name:'许可证将过期旅行社',
                                type:'bar',
                                data:[result.aboutprove],
                            },{
                                name:'注册旅行社总量',
                                type:'bar',
                                data:[result.talnumner],
                            },{
                                name:'注册公司总量',
                                type:'bar',
                                data:[result.comnumner],
                            }
                        ]
                    });
                    window.onresize = function(){
                        line1.resize();
                        line2.resize();

                     };

            })
        }*/
		setTableRowsfist();
        function setTableRowsfist() {
            $.post("index/statisticsTopNum.do", {}, function (result) {
                if (result.state == SUCCESS) {
                    $('#comtow').html(result.data.appointmentNum);
                    $('#comfist').html(result.data.nowNum);
                    $('#comthree').html(result.data.totalNum);
                    $('#comfor').html(result.data.overNum);
                }
            })
        }
    });

</script>

<!-- Modal(模态框) -->
<div class="modal fade" id="modal-dialog" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">&times;
                </button>
                <h4 class="modal-title" id="myModalLabel">Modal title</h4>
            </div>
            <div class="modal-body"></div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary ok">保存</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>
