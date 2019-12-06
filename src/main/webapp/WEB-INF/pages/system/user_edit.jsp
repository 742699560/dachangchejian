<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="basePath" value="${pageContext.request.contextPath}"></c:set>
<div class="breadcrumbs ace-save-state" id="breadcrumbs">
	<ul class="breadcrumb">
		<li><i class="ace-icon fa fa-home home-icon"></i> <a
			href="indexUI.do">主页</a></li>
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
							<div class="panel-heading" id="editTitle"
								style="background: #ececec"></div>
							<div style="">
								<form class="form-horizontal" id="editUserForm">
									<div class="form-group" style="padding-top: 20px;">
										<div class="col-sm-2 control-label">
											<font color="red">*</font>用户名：
										</div>
										<div class="col-sm-4">
											<input type="text" name="userName" id="userName"
												placeholder="登录账号"
												class="form-control dynamicClear required">
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-2 control-label">
											<font color="red">*</font>密码：
										</div>
										<div class="col-sm-4">
											<input type="password" name="userPwd" id="userPwd"
												placeholder="密码" class="form-control dynamicClear required">
										</div>
									</div>
									<div class="form-group" id="newPwdDiv" style="display: none">
										<div class="col-sm-2 control-label">
											<font color="red">*</font>新密码：
										</div>
										<div class="col-sm-4">
											<input type="password" name="newPwd" id="newPwd"
												placeholder="请重新填写密码" class="form-control dynamicClear required">
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-2 control-label">
											<font color="red">*</font>公司：
										</div>
										<div class="col-sm-4">
											<input type="text" name="company" id="company"
												placeholder="公司" class="form-control dynamicClear required">
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-2 control-label">
											<font color="red">*</font>姓名：
										</div>
										<div class="col-sm-4">
											<input type="text" name="realname" id="realname"
												placeholder="姓名" class="form-control dynamicClear required">
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-2 control-label">
											<font color="red">*</font>是否为执法人员：
										</div>
										<div class="col-xs-3">
											<label> <input name="switch-field-1" id="performer"
												class="ace ace-switch ace-switch-7" type="checkbox" /> <span
												class="lbl"></span>
											</label>
										</div>
									</div>
									
									<div class="form-group">
										<div class="col-sm-2 control-label">
											执法证号：
										</div>
										<div class="col-sm-4">
											<input type="text" name="pernumber" id="pernumber"
												placeholder="执法人员证号" class="form-control dynamicClear">
										</div>
									</div>
									
									<div class="form-group">
										<div class="col-sm-2 control-label"
											style="margin-right: 12px;">
											<font color="red">*</font>类型：
										</div>
										<div>
											<select class="chosen-select form-control" id="utype"
												data-placeholder="Choose a State..." style="width: 200px;">
												<option value=""></option>
												<option value="0">普通员工</option>
												<option value="1">管理人员</option>
											</select>
										</div>
									</div>

									<div class="form-group">
										<div class="col-sm-2 control-label"
											style="margin-right: 12px;">
											<font color="red">*</font> 角色：
										</div>
										<div>
											<select class="chosen-select form-control" id="roleList"
												data-placeholder="Choose a State..." style="width: 200px;">
												<option value=""></option>
											</select>
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-3 control-label"></div>
										<input type="button" class="btn btn-primary" id="btn_ok">
										&nbsp;&nbsp; <input type="button" value="返回"
											class="btn btn-warning" id="btn_return">
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

<script type="text/javascript" src="${basePath}/js/system/user_edit.js"></script>