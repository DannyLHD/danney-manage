<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/inc/defaultInc.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ROLE_AUTH_SUPER_ADMIN" value="<%=CodeBookConsts.ROLE_AUTH_SUPER_ADMIN %>"></c:set>
<c:set var="ROLE_AUTH_ORDINARY" value="<%=CodeBookConsts.ROLE_AUTH_ORDINARY %>"></c:set>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>My JSP 'allUser.jsp' starting page</title>
	
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/resources/css/datagrid.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/resources/css/div.css">
  
  </head>
  <body>
  
	   <form class="navbar-form navbar-right"  action="" id="searchForm" >
	   		<div class="form-group" >
	   		<input type="hidden" name="currentPage" value="${currentPage==null?1:currentPage}"/>
		   <input type="text" class="form-control"  id="username-id" name="patentName" placeholder="请输入专利名称">
		  
		   <a href="javascript:queryUser()">查找</a>
		   <c:if test="${user.role.authority==ROLE_AUTH_ORDINARY || user.role.authority==ROLE_AUTH_SUPER_ADMIN}">
		   	<a  href="javascript:addPatent()" >添加</a>
		   </c:if>
		    </div>
	  </form>
	   
	   <div id="infListContent"></div>
 
<script type="text/javascript">
$(function(){
    getData();
});

function addPatent(){
   layer.open({
	  type: 2,
	  title: '添加用户',
	  shadeClose: true,
	  shade: 0.8,
	  area: ['380px', '90%'],
	  content: '${base}/ord/toAddPatent', //iframe的url
	  end:getData
	}); 
} 
   
function queryUser(){
    getData();
}

function getData(){
	$("#searchForm").ajaxSubmit({
		url:"${base}/user/queryPatent_Self",
		success:function(data){
			$("#infListContent").html(data);
		}
	});
}
</script>
</body>
</html>
