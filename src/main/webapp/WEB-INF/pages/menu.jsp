<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri= "http://java.sun.com/jsp/jstl/core" prefix= "c" %> 
<c:set var="basePath" value="${pageContext.request.contextPath}"></c:set>
	
		<div class="main-container ace-save-state" id="main-container">
			<div id="sidebar" class="sidebar responsive  ace-save-state">
				<ul class="nav nav-list" id="menuList">
																	
				</ul>
				<div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
					<i id="sidebar-toggle-icon" class="ace-icon fa fa-angle-double-left ace-save-state" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
				</div>
			</div>
		</div>

<script type="text/javascript" src="${basePath }/js/common/menu.js"></script>
<script type="text/javascript">
$(function(){
	loadMenuList()
	$('#menuList').on('click','#menuBtn',loadContainer);
});
</script>
	
	
