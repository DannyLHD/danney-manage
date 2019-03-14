<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/inc/defaultInc.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'selectProject.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <form action="" id="searchForm" >
    		<input type="hidden" name="returnUrl" value="/result/selectProjectList"/>
	   		<input type="hidden" name="currentPage" value="${currentPage==null?1:currentPage}"/>
		   <input type="text"  id="projectname-id" name="projectname" size="15px"  style="margin-left:50%;" placeholder="请输入项目名">
		   <a href="javascript:queryProject()">查找项目</a>
    </form>
   <div id="projectListContent"></div>
  </body>
<script type="text/javascript">
$(function(){
  getData();
});
function queryProject(){
    getData();
}

function getData(){
	$("#searchForm").ajaxSubmit({
		url:"${base}/project/queryProject",
		success:function(data){
			$("#projectListContent").html(data);
		}
	});
}
</script>
</html>

