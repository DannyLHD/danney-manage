<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/inc/defaultInc.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
    <title>个人信息管理</title>
	<link rel="stylesheet" type="text/css" href="${base }/resources/css/userCenter.css">
</head>


<body>
<div class="container" style="cursor:  pointer;">
<%@include file="/frame/superAdminHead.jsp" %>
<div class="container user-center">
	<div class="col-md-3 user-center-left">
		<div id="home" class="btn user-center-btn" href="javascript:void(0);">个人中心</div>
		<ul>
			<li id="myInfo"><i class="fa fa-user"></i>个人资料</li>
			<li id="editPwd"><i class="fa fa-lock"></i>修改密码</li>
			<li id="editPhone"><i class="fa fa-mobile-phone"></i>修改手机号</li>
		</ul>
	</div>

	<div id="rightContent" class="col-md-9 user-center-right" style="min-height:400px;border-radius: 10px;background-color:#e7e7e7;">
		
	</div>
</div>
</div>
<%@include file="/frame/foot.jsp" %>


<script type="text/javascript">
$(function(){
	$(".user-center-left li").click(function(){
		$(this).addClass("select").siblings().removeClass("select");
	});
	
	$("#home").click(function(){
		$.ajax({
			url:'${base}/user/selfMessage',
			success:function(data){
				$("#rightContent").html(data);
				$(".user-center-left li.select").removeClass("select");
			}
		});
	});
	$("#myInfo").click(function(){
		$.ajax({
			url:'${base}/user/selfMessage',
			success:function(data){
				$("#rightContent").html(data);
			}
		});
	});
	$("#editPwd").click(function(){
		$.ajax({
			url:'${base}/user/selfPassword',
			success:function(data){
				$("#rightContent").html(data);
			}
		});
	});
	$("#editPhone").click(function(){
		$.ajax({
			url:'${base}/user/selfPhone',
			success:function(data){
				$("#rightContent").html(data);
			}
		});
	});
	
	//默认加载主页
	$.ajax({
		url:'${base}/user/selfMessage',
		success:function(data){
			$("#rightContent").html(data);
		}
	});
});
	
</script>
</body>
</html>