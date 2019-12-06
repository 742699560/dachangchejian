<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="basePath" value="${pageContext.request.contextPath}"></c:set>

<form  class="form-horizontal" role="form" id="editFormId">
	<div class="form-group">
		<label  class="col-sm-2 control-label" >摄像头编号:</label> 
	    <div class="col-sm-10">
	    <input type="text"class="form-control required" name="tcode" id="comtcode"></input>
	    </div>
	</div>
	<div class="form-group">
		<label  class="col-sm-2 control-label" >摄像头名称:</label> 
	    <div class="col-sm-10">
	    <input type="text"class="form-control required" name="cname" id="cname"></input>
	    </div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">摄像头品牌:</label>
		<div class="col-sm-10">
		<input type="text"class="form-control required" name="brand" id="brand"></input>
		</div> 
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">摄像头型号:</label>
		<div class="col-sm-10">
		<input type="text"class="form-control required" name="model" id="model"></input>
		</div> 
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">通道号:</label>
		<div class="col-sm-10">
		<input type="text"class="form-control required" name="nChannel" id="nChannel"></input>
		</div> 
	</div>
</form>
<script type="text/javascript" src="${basePath}/js/video/video_list.js"></script>
