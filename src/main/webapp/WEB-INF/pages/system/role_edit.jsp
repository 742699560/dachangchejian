<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="basePath" value="${pageContext.request.contextPath}"></c:set>
<div class="breadcrumbs ace-save-state" id="breadcrumbs">
	<ul class="breadcrumb">
		<li><i class="ace-icon fa fa-home home-icon"></i> <a href="indexUI.do">主页</a>
		</li>
		<li><a href="#">用户管理</a></li>
	</ul>
</div>
<div id="wrapper">
	<div class="main">
		<div class="main-content" style="padding: 0px;">
			<div class="container-fluid">
				<div class="row">
					<div class="container" style="width: 100%;">
	<div class="panel panel-default">
		<div class="panel-heading" id="editTitle"></div>
		<div style="margin-left: 240px;">
			<form class="form-horizontal" id="editRoleForm">
				<div class="form-group" style="padding-top: 20px;">
					<div class="col-sm-2 control-label">角色名称</div>
					<div class="col-sm-4">
						<input type="text" id="roleName" placeholder="角色名称" class="form-control required">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2 control-label">备注</div>
					<div class="col-sm-4">
						<input type="text" id="note" placeholder="备注" class="form-control">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2 control-label">授权</div>
					<div class="col-sm-4">
						<ul id="menuTree" class="ztree"></ul>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2 control-label"></div>
					<input type="button"  class="btn btn-primary btn_save">
					&nbsp;&nbsp;
					<input type="button" value="返回" class="btn btn-warning btn_return">
				</div>
			</form>
		</div>
	</div>
</div>
</div>
</div>
</div>
</div>
</div>

<script type="text/javascript" src="${basePath}/js/system/role_edit.js"></script>