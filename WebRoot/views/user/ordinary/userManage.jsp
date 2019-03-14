<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/inc/defaultInc.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
    <title>人员信息</title>
	<link rel="stylesheet" type="text/css" href="${base }/resources/css/userCenter.css">
</head>


<body>
<div class="container" style="cursor:  pointer;">
<%@include file="/frame/ordinaryHead.jsp" %>
<div class="container user-center">
	<div id="rightContent" class="col-md-12 user-center-right" style="min-height:400px;border-radius: 10px;background-color:#e7e7e7;">
	</div>
</div>
</div>
<%@include file="/frame/foot.jsp" %>

<script type="text/javascript">
$(function(){
	$(".user-center-left li").click(function(){
		$(this).addClass("select").siblings().removeClass("select");
	});
	//默认加载主页
	$.ajax({
		url:'${base}/portal/getAllUser',
		success:function(data){
			$("#rightContent").html(data);
		}
	});
});
	
</script>
</body>
</html>