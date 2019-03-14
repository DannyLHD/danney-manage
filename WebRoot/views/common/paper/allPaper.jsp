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
		   <input type="text" class="form-control"  id="username-id" name="paperName" placeholder="请输入论文名称">
		  
		   <a href="javascript:queryUser()">查找</a>
		    </div>
	  </form>
	   
	   <div id="infListContent"></div>
 
<script type="text/javascript">
$(function(){
    getData();
});
   
function queryUser(){
    getData();
}

function getData(){
	$("#searchForm").ajaxSubmit({
		url:"${base}/portal/queryPaper",
		success:function(data){
			$("#infListContent").html(data);
		}
	});
}
</script>
</body>
</html>
