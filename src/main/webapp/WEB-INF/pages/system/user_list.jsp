<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="basePath" value="${pageContext.request.contextPath}"></c:set>
<script src="${basePath}/js/common/page.js"></script>

<div class="breadcrumbs ace-save-state" id="breadcrumbs">
	<ul class="breadcrumb">
		<li><i class="ace-icon fa fa-home home-icon"></i> <a href="indexUI.do">主页</a>
		</li>
		<li class="active">用户管理</li>
	</ul>

</div>
<div class="col-md-12">
	<table id="grid-table">
		<tr>
			<th id=""></th>
		</tr>
	</table>
	<div id="grid-pager"></div>
</div>

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
	$(grid_selector).jqGrid({
		url : 'user/findObjects.do',
        datatype : "json",
        mtype : "post",
		colModel:[
			{name:'id',index:'id', editable: false , hidden: true ,editoptions:{readonly:true}},
			{name:'urid',index:'urid', editable: false , hidden: true ,editoptions:{readonly:true}},
			{name:'ugid',index:'ugid', editable: false , hidden: true ,sortable:false,editoptions:{readonly:true}},
			{label:'用户名',name:'username',index:'username',editable: true,editrules:{required : false}},
			{label:'车检所',name:'company',index:'company',editable: true},
			{label:'类型',name:'utype',index:'utype',editable: false,formatter:userType},
			{label:'状态',name:'valid',index:'valid',editable: false,formatter:userStatus},
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
		/* caption: "用户列表", */
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
		}).navButtonAdd(pager_selector,{  
	    	   caption:"恢复&ensp;",  
	    	   buttonicon:"ace-icon  fa fa-undo blue",   
	    	   onClickButton: function(){   
	    		   changeState()
	    	   },   
	    	   position:'first',
	           title:"恢复人员"
   	   }).navButtonAdd(pager_selector,{  
	    	   caption:"冻结&ensp;",  
	    	   buttonicon:"ace-icon fa fa-asterisk purple",   
	    	   onClickButton: function(){   
	    		   changeValid()
	    	   },   
	    	   position:'first',
	           title:"冻结人员"
   	   }).navButtonAdd(pager_selector,{  
	           caption:"修改&ensp;",   
	    	   buttonicon:"ace-icon fa fa-pencil blue",   
	    	   onClickButton: function(){   
	    		   showEditUserPage();
	    	   },   
	    	   position:'first',
	           title:"修改信息"
   	   }).navButtonAdd(pager_selector,{  
	    	   caption:"添加&ensp;",  
	    	   buttonicon:"ace-icon fa fa-plus-circle purple",   
	    	   onClickButton: function(){   
	    		   showAddUserPage();
	    	   },   
	    	   position:'first',
	           title:"添加人员"
   	   });
  	  
})
function showAddUserPage(){
	var url = 'user/editUserUI.do';
	$('#container').load(url);
}
//跳转到修改页面
function showEditUserPage(){
	var id=$(grid_selector).jqGrid('getGridParam','selrow');
	if(id==""||id==null){
		swal("错误", "请选择要修改的选项！"); 
		return;
	}
	var rowData = $(grid_selector).jqGrid('getRowData',id);
	$('#container').data('userId',rowData.id);
	$('#container').load('user/editUserUI.do');
}
//启用/禁用
function changeState(){
	var id=$(grid_selector).jqGrid('getGridParam','selrow');
	if(id==""||id==null){
		swal("错误", "请选择要修改的选项！", "error"); 
		return;
	}
	var rowData = $(grid_selector).jqGrid('getRowData',id);	
	var params = {'userId':id,'valid':1};
	var url ='user/changeValid.do';
	$.post(url,params,function(result){
		if(result.state==SUCCESS){
			sweetAlert("操作成功！");
			searchuser();
		}else{
			sweetAlert(result.message);
		}
	})
}

function changeValid(){
	var id=$(grid_selector).jqGrid('getGridParam','selrow');
	if(id==""||id==null){
		swal("错误", "请选择要修改的选项！"); 
		return;
	}
	var rowData = $(grid_selector).jqGrid('getRowData',id);	
	var params = {'userId':id,'valid':0};
	var url ='user/changeValid.do';
	swal({
		title: "是否确定冻结", 
	
		showCancelButton: true, 
		confirmButtonColor: "#DD6B55",
		confirmButtonText: "确定！", 
		cancelButtonText: "取消！", 
		closeOnConfirm: false
	},function(){
		
               $.post(url,params,function(result){ 
               		$(grid_selector).trigger("reloadGrid"); 
               })
               swal("已冻结！"); 
	});
}
function searchuser(){
	$(grid_selector).trigger("reloadGrid"); //自动刷新
}
function openDialogFreeze(str){
	type = "freeze";
	var id=$(grid_selector).jqGrid('getGridParam','selrow');
	if(id==""||id==null){
		showbootbox("请选择一个人员");
		return;
	}
	$("#modal-tip").empty();
	$("#modal-tip").append("确认"+str+"该人员?");
	$('#deleteModal').modal('show');
	
}
function userType(cellvalue, options, rowObject){
	var str = "";
	switch(rowObject.utype){
		case "0": 
		str = "<span class='label label-default'><i class='icon-warning-sign bigger-120'></i>普通员工</span>";
		break;	
		case "1": 
			str = "<span class='label label-primary'>管理人员</span>";
			break;
			
	}
	return str;
}


function userStatus(cellvalue, options, rowObject){
	var str = "";
	switch(rowObject.valid){
		case 0: 
		str = "<span class='label label-danger'><i class='icon-warning-sign bigger-120'></i>冻结</span>";
		break;	
		case 1: 
			str = "<span class='label label-success'>正常</span>";
			break;
			
		default: 
			str = "<span class='label label-warning'><i class='icon-warning-sign bigger-120'></i>错误状态</span>";
	}
	return str;
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
function getRole(){

	var url = "${pageContext.request.contextPath}/role/list";
	$.ajax({
	    url:url,
	    type:"post",
	    async:true,
	    timeout:5000,
	    dataType:'json',
	    beforeSend:function(xhr){
	    	
	    },
	    success:function(data){
	    	if(judgeNull(data)){
	    		var str =""
	    		$.each(data,function(n,value) {
	    			str += "<option value ='"+value.rid+"'>"+value.rname+"</option>";
	    		});
	    		$("#urid").append(str);
	    	}
	    }
	});
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
<%-- <script type="text/javascript" src="${basePath}/js/system/user_list.js"></script> --%>