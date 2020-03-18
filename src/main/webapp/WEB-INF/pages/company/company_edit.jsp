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
											<font color="red">*</font>车检所名称：
										</div>
										<div class="col-sm-8">
											<input type="text" name="name" id="name"
												placeholder="车检所名称"
												class="form-control dynamicClear required">
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-3 control-label">
											<font color="red">*</font>联系人：
										</div>
										<div class="col-sm-8">
											<input type="text" name="contats" id="contats"
												placeholder="联系人"
												class="form-control dynamicClear required">
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-3 control-label">联系方式：</div>
										<div class="col-sm-8">
											<input type="text" name="phone" id="phone"
												   class="form-control dynamicClear " placeholder="联系方式">
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
										<div class="col-sm-3 control-label">详细地址：</div>
										<div class="col-sm-8">
											<input type="text" name="address" id="address"
											  class="form-control dynamicClear"	placeholder="详细地址">
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-3 control-label">
											<font color="red">*</font>描述：
										</div>
										<div class="col-sm-8">
											<input type="text" name="description" id="description"
												placeholder="描述"
												class="form-control dynamicClear required">
										</div>
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