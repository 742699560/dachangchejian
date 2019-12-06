<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="basePath" value="${pageContext.request.contextPath}"></c:set>
<div class="breadcrumbs ace-save-state" id="breadcrumbs">
	<ul class="breadcrumb">
		<li><i class="ace-icon fa fa-home home-icon"></i> <a
			href="indexUI.do">主页</a></li>
		<li><a href="#">旅行社信息</a></li>
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
									style="padding-left: 100px; padding-top: 40px;">
									<div class="form-group" style="padding-top: 20px;">
										<div class="col-sm-3 control-label">
											<font color="red">*</font>录像点名称：
										</div>
										<div class="col-sm-8">
											<input type="text" name="vname" id="vname"
												placeholder="录像点名称"
												class="form-control dynamicClear required">
										</div>
									</div>

									<div class="form-group">
										<div class="col-sm-3 control-label">
											<font color="red">*</font>录像机编号：
										</div>
										<div class="col-sm-8">
											<input type="text" name="tcode" id="tcode"
												placeholder="录像机编号"
												class="form-control dynamicClear required">
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-3 control-label">
											<font color="red">*</font>归属机构：
										</div>
										<div class="col-sm-8">
											<input type="text" name="type" id="type" placeholder="归属机构"
												class="form-control dynamicClear required">
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-3 control-label">
											<font color="red">*</font>经纬度：
										</div>
										<div class="col-md-4" >
											<input type="text" id=longitude name="longitude"
												class="form-control dynamicClear required"placeholder="自动获取..." readonly="readonly" />
										</div>
										<div class="col-md-4" >
											<input type="text" id="latitude" name="latitude"
												class="form-control dynamicClear required"placeholder="自动获取..." readonly="readonly" />
										</div>
									</div>

									<div class="form-group">
										<div class="col-sm-3 control-label">
											<font color="red">*</font>IP地址：
										</div>
										<div class="col-sm-8">
											<input type="text" name="dvrIP" id="dvrIP" placeholder="IP地址"
												class="form-control dynamicClear required">
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-3 control-label">
											<font color="red">*</font>端口号：
										</div>
										<div class="col-sm-8">
											<input type="text" name="vport" id="vport" placeholder="端口号"
												class="form-control dynamicClear required">
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-3 control-label">
											<font color="red">*</font>账户：
										</div>
										<div class="col-sm-8">
											<input type="text" name="userName" id="userName"
												placeholder="账户" class="form-control dynamicClear required">
										</div>
									</div>


								<div class="form-group" style="padding-left: 100px;">
									<div class="col-sm-4 control-label"></div>
									<input type="button" value="新增摄像头" class="btn btn-primary btn-add" id="btn_add"> &nbsp;&nbsp; 
									<input type="button" class="btn btn-primary" id="btn_ok"> &nbsp;&nbsp;
									<input type="button" value="返回" class="btn btn-warning" id="btn_return">
								</div>
								</form>
							</div>
							<div class="col-md-6">
								<div id="allmap"
									style="margin-top: 20px; border-style: ridge; height: 400px; width: 95%;">
								</div>
							</div>
							<div class="col-md-12" style="padding: 30px; ">
								<div class="table-header">摄像头列表</div>
								<div>
									<table id="projectList"
										class="table table-striped table-bordered table-hover"
										style="margin: 0px;">
										<thead>
											<tr>
												<th data-align="center" data-width="100px">摄像头编号</th>
												<th data-align="center" data-width="150px">摄像头描述</th>
												<th data-align="center">通道号</th>
												<th data-align="center">摄像头品牌</th>
												<th data-align="center">摄像头型号</th>
												<th data-align="center" data-width="120" colspan="3">操作</th>
											</tr>
										</thead>

										<tbody id="tbodyId">

										</tbody>
									</table>
									<div class="modal-footer no-margin-top" id="pageId">
										<ul class="pagination pull-right no-margin">
											<li class="prev"><a class="pre"> 
											<i class="ace-icon fa fa-angle-double-left"></i>
											</a></li>
											<li class="next"><a class="next">
											 <i class="ace-icon fa fa-angle-double-right"></i>
											</a></li>
										</ul>
									</div>
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
<script type="text/javascript" src="${basePath}/js/video/video_edit.js"></script>
