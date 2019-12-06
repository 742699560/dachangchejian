<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="basePath" value="${pageContext.request.contextPath}"></c:set>
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
		<li><a href="#">旅行社管理</a></li>
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
								<form class="form-horizontal" id="editTravelForm"
									style="padding-top: 20px;">
									<div class="form-group" style="padding-top: 20px;">
										<div class="col-sm-4 control-label">
											<font color="red">*</font>旅行社名称：
										</div>
										<div class="col-sm-7">
											<input type="text" name="tname" id="tname"
												placeholder="旅行社名称"
												class="form-control dynamicClear required">
										</div>
									</div>
									
									<div class="form-group">
										<div class="col-sm-4 control-label">
											<font color="red">*</font>旅行社法人：
										</div>
										<div class="col-sm-7">
											<input type="text" name="legal" id="legal"
												placeholder="旅行社法人"
												class="form-control dynamicClear required">
										</div>
									</div>
										<!-- <div class="form-group">
										<div class="col-sm-3 control-label">
											<font color="red">*</font>许可证编号：
										</div>
										<div class="col-sm-8">
											<input type="text" name="num" id="num"
												placeholder="许可证编号"
												class="form-control dynamicClear required">
										</div>
									</div> -->
									<div class="form-group">
										<div class="col-sm-4 control-label">
											<font color="red">*</font>许可证编号：
										</div>
										<div class="col-sm-7">
											<input type="text" name="tcode" id="tcode"
												placeholder="许可证编号"
												class="form-control dynamicClear required">
										</div>
									</div>
									<div class="form-group">
										<label for="project-code" class="col-sm-4 control-label">
										<font color="red">*</font>
										成立时间:</label>
										<div class="col-sm-7">
											<input type="text" class="form-control datepicker "
												name="stime" id="stime">
										</div>
									</div>
									<div class="form-group">
										<label for="project-code" class="col-sm-4 control-label">
										<font color="red">*</font>
										许可证到期时间:</label>
										<div class="col-sm-7">
											<input type="text" class="form-control datepicker "
												name="expire" id="expire">
										</div>
									</div>
								

									
									<div class="form-group">
										<div class="col-sm-4 control-label">
											<font color="red">*</font>联系人：
										</div>
										<div class="col-sm-7">
											<input type="text" name="contats" id="contats"
												placeholder="联系人" class="form-control dynamicClear required">
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-4 control-label">
											<font color="red">*</font>联系电话：
										</div>
										<div class="col-sm-7">
											<input type="text" name="phone" id="phone" placeholder="联系电话"
												class="form-control dynamicClear required">
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-4 control-label">
											<font color="red">*</font>旅行社地址：
										</div>
										<div class="col-sm-7">
											<input type="text" name="address" id="address"
												placeholder="旅行社地址"
												class="form-control dynamicClear required">
										</div>
									</div>
										<div class="form-group">
										<div class="col-sm-4 control-label">
											<font color="red">*</font>经纬度：
										</div>
										<div class="col-md-3" >
											<input type="text" id="lng" name="lng" placeholder="自动获取..."
												class="form-control dynamicClear required"readonly="readonly" />
										</div>
										<div class="col-md-3">
											<input type="text" id="lat" name="lat" placeholder="自动获取..."
												class="form-control dynamicClear required"readonly="readonly" />
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-4 control-label">
											用户账户：
										</div>
										<div class="col-md-3">
											<input class="form-control" type="text" name="user" id="user" placeholder="登录账号"
												>
										</div>
										
										<div class="col-md-3">
											<input class="form-control" type="text" name="passwd" id="passwd" placeholder="登录密码"
												>
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
									style="margin-top: 20px; border-style: ridge; height: 490px; width: 95%;">
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

<script type="text/javascript" src="${basePath}/js/travel/travel_edit.js"></script>