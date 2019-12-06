<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="basePath" value="${pageContext.request.contextPath}"></c:set>
<script src="${basePath}/js/common/page.js"></script>
<script type="text/javascript">
//初始化datepicker对象
$('.datepicker').datepicker({
    format: 'yyyy-mm-dd',
    language:"zh-CN",
    autoclose: true//选中自动关闭
})
</script>
<div class="breadcrumbs ace-save-state" id="breadcrumbs">
	<ul class="breadcrumb">
		<li><i class="ace-icon fa fa-home home-icon"></i> <a
			href="indexUI.do">主页</a></li>
		<li class="active">企业检查记录</li>
	</ul>

</div>
<div class="row" style="height: 20px;"></div>
<form class="form-horizontal col-md-12" id="searchform">
	<div class="form-group">
		<div class="col-md-12 col-lg-3">
			<label class="col-md-2 col-lg-4">企业名称: </label> <input type="text"
				id="cname" name="cname" placeholder=""
				style="height: 30px;" class="col-md-7">
		</div>
		<div class="col-md-12 col-lg-3">
			<label class="col-md-2 col-lg-4">执法人员: </label> <input type="text"
				id="exaname" name="exaname" placeholder=""
				style="height: 30px;" class="col-md-7">
		</div>
	</div>
	<div class="form-group">
		<div class="col-md-12 col-lg-3">
			<label class="col-md-2 col-lg-4">检查日期: </label> <input
				style="height: 30px;" type="text" id="examinetime"
				name="examinetime" placeholder="" class="date-picker col-md-7 datepicker">
		</div>
		<div class="col-md-12 col-lg-3">
			<label class="col-md-2 col-lg-4">检查结果: </label> 
			<select id="result" name="result" class="col-md-7" >
				<option value="">全部</option>
				<option value="正常">正常</option>
				<option value="责令整改">责令整改</option>
				<option value="立案">立案</option>
			</select>
		</div>
		<div class="col-md-12 col-lg-3">
			<button type="button" onclick="search()"
				class="btn btn-xs btn-primary"
				style="margin-left: 5px; padding: 3px 5px; outline: 0">
				<i class="ace-icon fa fa-search align-top bigger-125"></i>搜索
			</button>
			<button type="reset" onclick=""
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
		url : 'company/findCompanyRecord.do',
        datatype : "json",
        mtype : "post",
		colModel:[
			{name:'cid',index:'cid', editable: false , hidden: true ,editoptions:{readonly:true}},
			{name:'urid',index:'urid', editable: false , hidden: true ,editoptions:{readonly:true}},
			{name:'ugid',index:'ugid', editable: false , hidden: true ,sortable:false,editoptions:{readonly:true}},
		
			{label:'企业名称',name:'cname',index:'cname',editable: true,sortable:true},
			{label:'企业类型',name:'type',index:'type',formatter:userType},
			{label:'执法人员',name:'exaname',index:'exaname',editable: false},
			{label:'检查结果',name:'result',index:'result',editable: false},
			{label:'检查日期',name:'examinetime',index:'examinetime',editable: true},
			{label:'检查内容',name:'content',editable: true,formatter:contentFromatters},
			
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
		/* caption: "企业列表", */
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


function contentFromatters(cellvalue, options, rowObject){
	var str= '<button  class="btn btn-minier btn-primary" onclick="findsearchs(this)"><i class="ace-icon fa  fa-folder icon-on-right"></i> 查看检查内容</button>';
	return str;
}

function userType(cellvalue, options, rowObject){
	var str = "";
	switch(rowObject.type){
		case "1": 
		str = "<span class='label label-default'>酒店</span>";
		break;	
		case "2": 
			str = "<span class='label label-warning'>景区</span>";
			break;
	}
	return str;
}

function findsearchs(object){
	var eid = $(object).parent().parent().attr("id");
	
	var url="company/editrecordforcomUI.do";
	var title="检查内容";

	$("#modal-dialog").data("eid",eid);
	$("#modal-dialog .modal-body").load(url,
			function(){
		 $(".modal-title").html(title);
		 $("#modal-dialog").modal("show");
	})
}
$('#btn_return').click(gobackUserList);
//点击返回按钮
function gobackUserList(){
	clearData();
	$('#container').load('company/editrecordcomUI.do');
}
function clearData(){
	$('#editTravelForm .dynamicClear').val('');
	$('#container').data('vid','');
}
function search(){
	$(grid_selector).jqGrid('setGridParam',{ 
		postData:{ 
			'cname':$('#cname').val(),
			'exaname':$("#exaname").val(),
			'examinetime':$("#examinetime").val(),
			'result':$("#result").val()
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