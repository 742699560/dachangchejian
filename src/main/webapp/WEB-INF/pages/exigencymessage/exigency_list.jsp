<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="basePath" value="${pageContext.request.contextPath}"></c:set>
<script src="${basePath}/js/common/page.js"></script>
<div class="breadcrumbs ace-save-state" id="breadcrumbs">
	<ul class="breadcrumb">
		<li><i class="ace-icon fa fa-home home-icon"></i> <a
			href="indexUI.do">主页</a></li>
		<li class="active">紧急信息</li>
	</ul>

</div>
<div class="row" style="height: 20px;"></div>
<form class="form-horizontal col-md-12" id="searchform">
	<div class="form-group">
		<div class="col-md-12 ">
			<label class="col-md-1 ">事件名称: </label> <input type="text"
				id="search_tcarcode" name="search_tcarcode" placeholder=""
				style="height: 30px;" class="col-md-2">
				<label class="col-md-1 ">事件等级: </label> <select
				id="search_ptype" name="search_ptype" class="col-md-2"
				>
				<option value="">全部</option>
				<option value="1">一般</option>
				<option value="2">严重</option>
				<option value="3">非常严重</option>
			</select>
			
		</div>
		
	</div>
	<div class="form-group">
		<div class="col-md-12 ">
			<label class="col-md-1">开始时间: </label> <input
				style="height: 30px;" type="text" id="search_start"
				name="search_start" placeholder="" class="date-picker col-md-2">
				<label class="col-md-1">结束时间: </label> <input
				style="height: 30px;" type="text" id="search_end" name="search_end"
				placeholder="" class="date-picker col-md-2">
				<button type="button" onclick="search()"
				class="btn btn-xs btn-primary"
				style="margin-left: 50px; padding: 3px 5px; outline: 0">
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
<script type="text/javascript">
			jQuery(function($) {
		
				$('.input-daterange').datepicker({autoclose:true});
				$('#colorpicker1').colorpicker();
				$('#simple-colorpicker-1').ace_colorpicker();
		
			});
</script>

<script type="text/javascript">
var grid_selector = "#grid-table";
var pager_selector = "#grid-pager";
var type=""
var title="";
var userustatus = "";
var parent_column = $(grid_selector).closest('[class*="col-"]');
$(window).on('resize.jqGrid', function () {
	$(grid_selector).jqGrid( 'setGridWidth', parent_column.width() );
})
$(document).on('settings.ace.jqGrid' , function(ev, event_name, collapsed) {
	if( event_name === 'sidebar_collapsed' || event_name === 'main_container_fixed' ) {
		setTimeout(function() {
			$(grid_selector).jqGrid( 'setGridWidth', parent_column.width() );
		}, 20);
	}
})
	$(function(){
		$.extend($.jgrid.defaults,{
			 recordtext: "&nbsp;&nbsp;当前{0}~{1}条,共{2}条&nbsp;",  
	         emptyrecords: "未查到任何数据",  
	         loadtext: "正在加载中...",
	         pgtext : "第&nbsp;{0}&nbsp;页,共{1}页"  
		});
	})

$(function(){
	$("#search_start").datepicker({
		format: 'yyyy-mm-dd',
		autoclose:true,
		language:"zh-CN",
		startDate:'2016-01-01',
		minView:2
	});
	$("#search_end").datepicker({
		format: 'yyyy-mm-dd',
		autoclose:true,
		language:"zh-CN",
		startDate:'2016-01-01',
		minView:2
	});
	$(grid_selector).jqGrid({
		url : 'project/findPageObjects.do',
        datatype : "json",
        mtype : "post",
		colModel:[
			{name:'id',index:'id', editable: false , hidden: true ,editoptions:{readonly:true}},
			{name:'urid',index:'urid', editable: false , hidden: true ,editoptions:{readonly:true}},
			{name:'ugid',index:'ugid', editable: false , hidden: true ,sortable:false,editoptions:{readonly:true}},
			{label:'事件名称',name:'tname',index:'tname',editable: true,editrules:{required : false}},
			{label:'发生时间',name:'ttime',index:'ttime',editable: true,sortable:true,formatter:dateFromatter},
			{label:'相关旅行社',name:'ttname',index:'ttname',editable: true},
			{label:'事件等级',name:'type',index:'type',editable: false,formatter:userType},
			{label:'路线',name:'path',index:'path',editable: true},
			{label:'内容',name:'content',index:'content',editable: true},
			{label:'经纬度',name:'lng',editable: false,formatter:lnglat},
		], 
		viewrecords : true,
		rowNum: 20,
		pager : pager_selector,
		altRows: true,
		height: '100%',
		multiselect: false,
        multiboxonly: false,
		loadComplete : function() {
			var table = this;
			setTimeout(function(){
				updatePagerIcons(table);
			}, 0);
		},
		/* caption: "信息列表", */
			rownumbers:true,
		shrinkToFit: true,
		autowidth: true,
	});
	$(grid_selector).closest(".ui-jqgrid-bdiv").css({ 'overflow-x': 'hidden' });
	$(grid_selector).jqGrid('navGrid',pager_selector,
		{ 	
			edit: false,
			add: false,
			del: false,
			search: false,
			refresh: true,
			refreshicon : 'ace-icon fa fa-refresh green',
			refreshtitle:"刷新",
			refreshtext:"刷新",
			view: false,
		});
  	  
})


function lnglat(cellvalue, options, rowObject){
	var str = rowObject.lng+","+rowObject.lat;	
	return str;
}
function dateFromatter(cellvalue, options, rowObject){
	var str = rowObject.ttime
	var endstr = str.split(".")
	var arr = endstr[0]
	return arr;
}

function userType(cellvalue, options, rowObject){
	var str = "";
	switch(rowObject.type){
		case "1": 
		str = "<span class='label label-default'>一般</span>";
		break;	
		case "2": 
			str = "<span class='label label-warning'>严重</span>";
			break;
		case "3": 
			str = "<span class='label label-danger'>非常严重</span>";
			break;	
	}
	return str;
}
function search(){
	$(grid_selector).jqGrid('setGridParam',{ 
		postData:{ 
			'tname':$('#search_tcarcode').val(),
			'type':$("#search_ptype").val(),
			'starttime':$("#search_start").val(),
			'endtime':$("#search_end").val()
		}
	
	}).trigger("reloadGrid"); //重新载入
}


function searchuser(){
	//,'search_ugid':$('#s_gid').val()
	$(grid_selector).jqGrid('setGridParam',{
		url:'${pageContext.request.contextPath}/user/list',//你的搜索程序地址 
		postData:{'search_uname':$('#search_uname').val(),
					'search_uaccount':$('#search_uaccount').val()} //发送搜索条件 
	}).trigger("reloadGrid"); //重新载入
}
function ajaxpost(){
	var data = getFromToJson("#userform");
	if(!$("#userform").valid()){
		return;
	}
	
	if(isEmptyObject(data)){
		return;
	}
	var url="";
	switch(type){
		case "add":
			if(data.upwd==""){
				showbootbox("请输入密码");
				return;
			}
			url="${pageContext.request.contextPath}/user/add";
			break;
		case "edit":url="${pageContext.request.contextPath}/user/updata";break;
	}
	if(url==""){
		return;
	}
	$.ajax({
	    url:url,
	    type:"post",
	    async:true,
	    data:data,
	    timeout:5000,
	    dataType:'json',
	    beforeSend:function(xhr){
	    	
	    },
	    success:function(data){
	    	if(data.Result){
	    		$('#modal-finish').modal('hide');
	    		$(grid_selector).trigger("reloadGrid");
	    	}
	    	showbootbox(data.ResultText);
	    }
	});
}

function updatePagerIcons(table) {
	var replacement = 
	{
		'ui-icon-seek-first' : 'ace-icon fa fa-angle-double-left bigger-140',
		'ui-icon-seek-prev' : 'ace-icon fa fa-angle-left bigger-140',
		'ui-icon-seek-next' : 'ace-icon fa fa-angle-right bigger-140',
		'ui-icon-seek-end' : 'ace-icon fa fa-angle-double-right bigger-140'
	};
	$('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function(){
		var icon = $(this);
		var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
		
		if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
	})
}

function opertionPeple(){
	var sel_id = $(grid_selector).jqGrid('getGridParam', 'selrow');
	if(sel_id==""||sel_id==null){
		showbootbox("请选择一个人员");
		return;
	}
    var value = $(grid_selector).jqGrid('getRowData',sel_id);
    var data = {uid:value.uid};
    var url="";
    switch(type){
		case "delete":url="${pageContext.request.contextPath}/user/del";break;
		case "freeze":
			url="${pageContext.request.contextPath}/user/freeze?type="+userustatus;
			break;
	}
	$.ajax({
	    url:url,
	    type:"post",
	    async:true,
	    data:data,
	    timeout:5000,
	    dataType:'json',
	    beforeSend:function(xhr){
	    	
	    },
	    success:function(data){
	    	if(data.Result){
	    		$('#deleteModal').modal('hide');
	    		$(grid_selector).trigger("reloadGrid");
	    	}
	    	showbootbox(data.ResultText);
	    }
	});


}
</script>