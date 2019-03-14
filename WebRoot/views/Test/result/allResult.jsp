<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/inc/defaultInc.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'allResult.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/resources/css/datagrid.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/resources/css/div.css">
  
  </head>
  <body background="<%=basePath%>/resources/images/background/1.jpg">
   <div class="user-div">
	   <a style="margin-left:10%;" href="javascript:addResult()" >添加成果</a>
	   <form action="" id="searchForm" >
	   		<input type="hidden" name="currentPage" value="${currentPage==null?1:currentPage}"/>
		   <input type="text"  id="resultname-id" name="resultname" size="15px"  style="margin-left:50%;" placeholder="请输入成果名称">
		   <a href="javascript:queryResult()">查找成果</a>
	  </form>
	   
	   <div id="resultListContent"></div>
   </div>
<script type="text/javascript">
$(function(){
    getData();
});

function addResult(){
   layer.open({
	  type: 2,
	  title: '添加用户',
	  shadeClose: true,
	  shade: 0.8,
	  area: ['380px', '90%'],
	  content: '${base}/result/toAddResult', //iframe的url
	  end:getData
	}); 
} 
   
function queryResult(){
    getData();
}

function getData(){
	$("#searchForm").ajaxSubmit({
		url:"${base}/result/queryResult",
		success:function(data){
			$("#resultListContent").html(data);
		}
	});
}
</script>
</body>
</html>
