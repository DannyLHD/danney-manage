<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/inc/defaultInc.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'allProject.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/resources/css/datagrid.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/resources/css/div.css">
  
  </head>
  <body>
   
 	  <form class="navbar-form navbar-right"  action="" id="searchForm" >
	   		<div class="form-group" >
	   		<input type="hidden" name="currentPage" value="${currentPage==null?1:currentPage}"/>
		   <input type="text" class="form-control"  id="projectname-id" name="projectName" placeholder="请输入项目名">
		   <a href="javascript:queryProject()">查找</a>
		    </div>
	  </form>
	 
	   <div id="projectListContent"></div>
<script type="text/javascript">
$(function(){
    getData();
});

   
function queryProject(){
    getData();
}

function getData(){
	$("#searchForm").ajaxSubmit({
		url:"${base}/user/queryProject?stage=${STAGE}",
		success:function(data){
			$("#projectListContent").html(data);
		}
	});
}
</script>
</body>
</html>

