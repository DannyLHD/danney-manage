<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/inc/defaultInc.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
		   <input type="text" class="form-control"  id="username-id" name="inforTitle" placeholder="请输入标题">
		  
		   <a href="javascript:queryUser()">查找</a>
		   <a  href="javascript:addTecInformation()" >添加</a>
		    </div>
	  </form>
	   
	   <div id="infListContent"></div>
 
<script type="text/javascript">
$(function(){
    getData();
});

function addTecInformation(){
   layer.open({
	  type: 2,
	  title: '添加用户',
	  shadeClose: true,
	  shade: 0.8,
	  area: ['380px', '90%'],
	  content: '${base}/user/toAddTecInf?type=${TYPE}', //iframe的url
	  end:getData
	}); 
} 
   
function queryUser(){
    getData();
}

function getData(){
	$("#searchForm").ajaxSubmit({
		url:"${base}/user/queryTecInf_Self?type=${TYPE}",
		success:function(data){
			$("#infListContent").html(data);
		}
	});
}
</script>
</body>
</html>
