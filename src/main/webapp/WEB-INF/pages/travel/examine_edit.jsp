<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="basePath" value="${pageContext.request.contextPath}"></c:set>

<form  class="form-horizontal" role="form" id="editFormIds">
	<div class="form-group">
		<label  class="col-sm-3 control-label" >执法人员姓名:</label> 
	    <div class="col-sm-7">
	    <input type="text"class="form-control required" name="examinename" id="examinename"></input>
	    </div>
	</div>
</form>
<script type="text/javascript" src="${basePath}/js/travel/examine_list.js"></script>
