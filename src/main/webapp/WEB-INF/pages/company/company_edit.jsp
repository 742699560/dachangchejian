<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="basePath" value="${pageContext.request.contextPath}"></c:set>

<div class="breadcrumbs ace-save-state" id="breadcrumbs">
	<ul class="breadcrumb">
		<li><i class="ace-icon fa fa-home home-icon"></i> <a
			href="indexUI.do">主页</a></li>
		<li><a href="#">企业管理</a></li>
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
									<div class="form-group" style="padding-top: 20px;">
										<div class="col-sm-3 control-label">
											<font color="red">*</font>企业名称：
										</div>
										<div class="col-sm-8">
											<input type="text" name="companyName" id="companyName"
												placeholder="企业名称"
												class="form-control dynamicClear required">
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-3 control-label">
											<font color="red">*</font>企业法人：
										</div>
										<div class="col-sm-8">
											<input type="text" name="companyLegal" id="companyLegal"
												placeholder="企业法人"
												class="form-control dynamicClear required">
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-3 control-label">
											<font color="red">*</font>经纬度：
										</div>
										<div class="col-md-4">
											<input type="text" id="lng" name="lng" placeholder="自动获取..."
												class="form-control dynamicClear required"
												readonly="readonly" />
										</div>
										<div class="col-md-4">
											<input type="text" id="lat" name="lat" placeholder="自动获取..."
												class="form-control dynamicClear required"
												readonly="readonly" />
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-3 control-label">企业编号：</div>
										<div class="col-sm-8">
											<input type="text" name="serialnumber" id="serialnumber"
												class="form-control dynamicClear " placeholder="企业编号">
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-3 control-label">企业联系人：</div>
										<div class="col-sm-8">
											<input type="text" name="contats" id="contats"
											  class="form-control dynamicClear"	placeholder="企业联系人">
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-3 control-label">
											<font color="red">*</font>联系电话：
										</div>
										<div class="col-sm-8">
											<input type="text" name="contactnumber" id="contactnumber"
												placeholder="联系电话"
												class="form-control dynamicClear required">
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-3 control-label">
											<font color="red">*</font>企业地址：
										</div>
										<div class="col-sm-8">
											<input type="text" name="site" id="site" placeholder="企业地址"
												class="form-control dynamicClear required">
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-3 control-label"
											style="margin-right: 12px;">
											<font color="red">*</font>企业类型：
										</div>
										<div>
											<select class="chosen-select form-control" id="type"
												data-placeholder="Choose a State..." style="width: 200px;">
												<option value=""></option>
												<option value="1">酒店</option>
												<option value="2">景点</option>
											</select>


										</div>
									</div>
									<div class="form-group" style="padding-left: 100px;">
										<div class="col-sm-4 control-label"></div>
										<input type="button" class="btn btn-primary" id="btn_ok">
										&nbsp;&nbsp; <input type="button" value="返回"
											class="btn btn-warning" id="btn_return">
									</div>
								</form>
							
							</div>
							<div class="col-md-6">
								<div id="allmap"
									style="margin-top: 20px; border-style: ridge; height: 454px; width: 95%;">
								</div>
									
							</div>
						</div>
						<div></div>
						<div></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript"
	src="${basePath}/js/company/company_edit.js"></script>