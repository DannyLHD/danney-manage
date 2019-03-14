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
  <body background="<%=basePath%>/resources/images/background/1.jpg">
   <div  id="user"  class="user-div">
	   <a href="javascript:addUser()" >添加学生</a>
	   <form action="" id="searchForm" >
	   		<input type="hidden" name="currentPage" value="${currentPage==null?1:currentPage}"/>
		   <input type="text"  id="username-id" name="username" size="15px"  style="margin-left:50%;" placeholder="请输入姓名">
		   <a href="javascript:queryUser()">查找学生</a>
	  </form>
	   
	   <div id="userListContent"></div>
   </div>
<script type="text/javascript">
$(function(){
    getData();
});

function addUser(){
   layer.open({
	  type: 2,
	  title: '添加用户',
	  shadeClose: true,
	  shade: 0.8,
	  area: ['380px', '90%'],
	  content: '${base}/sup/toAddUser', //iframe的url
	  end:getData
	}); 
} 
   
function queryUser(){
    getData();
}

function getData(){
	$("#searchForm").ajaxSubmit({
		url:"${base}/sup/queryUser?role_auth=${ROLE_AUTH}",
		success:function(data){
			$("#userListContent").html(data);
		}
	});
}
</script>
</body>
</html>
