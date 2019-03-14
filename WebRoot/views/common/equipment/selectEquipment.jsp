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
    		<input type="hidden" name="returnUrl" value="/common/user/selectEquipmentList"/>
	   		<input type="hidden" name="currentPage" value="${currentPage==null?1:currentPage}"/>
		   <input type="text"  id="equipmentName" name="equipmentName" size="15px"  style="margin-left:50%;" placeholder="请输入设备名">
		   <a href="javascript:queryEquipment()">查找</a>
    </form>
   <div id="projectListContent"></div>
  </body>
<script type="text/javascript">
$(function(){
  getData();
});
function queryEquipment(){
    getData();
}

function getData(){
	$("#searchForm").ajaxSubmit({
		url:"${base}/user/queryEquipment",
		success:function(data){
			$("#projectListContent").html(data);
		}
	});
}
</script>
</html>

