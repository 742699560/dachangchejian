<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="basePath" value="${pageContext.request.contextPath}"></c:set>

<form class="form-horizontal" role="form" id="editFormIds">
	<div class="form-group">
		
		<div class="col-sm-12">
			<textarea style="height: 300px" class="form-control" name="content"
				id="content"></textarea>
		</div>
	</div>
</form>
<script type="text/javascript"
	src="${basePath}/js/travel/recordedit_list.js"></script>
