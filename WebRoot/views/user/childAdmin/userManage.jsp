<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/inc/defaultInc.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
    <title>用户信息</title>
	<link rel="stylesheet" type="text/css" href="${base }/resources/css/userCenter.css">
</head>


<body>
<div class="container" style="cursor:  pointer;">
<%@include file="/frame/childAdminHead.jsp" %>
<div class="container user-center">
	<div class="col-md-3 user-center-left">
		<div id="home" class="btn user-center-btn" href="javascript:void(0);">用户信息</div>
		<ul>
			<li id="ordinary"><i class="fa fa-user"></i>普通用户</li>
			<li id="unitAdmin"><i class="fa fa-user"></i>单位管理员</li>
			<li id="childAdmin"><i class="fa fa-user"></i>子公司管理员</li>
			<li id="headAdmin"><i class="fa fa-user"></i>总公司管理员</li>
			<li id="superAdmin"><i class="fa fa-user"></i>超级管理员</li>
		</ul>
	</div>
	<div id="rightContent" class="col-md-9 user-center-right" style="min-height:360px;border-radius: 10px;background-color:#e7e7e7;">
	</div>
</div>
</div>
<%@include file="/frame/foot.jsp" %>

<script type="text/javascript">
$(function(){
	$(".user-center-left li").click(function(){
		$(this).addClass("select").siblings().removeClass("select");
	});
	
	/* $("#home").click(function(){
		$.ajax({
			url:'${base}/user/selfMessage',
			success:function(data){
				$("#rightContent").html(data);
				$(".user-center-left li.select").removeClass("select");
			}
		});
	}); */
	$("#ordinary").click(function(){
		$.ajax({
			url:'${base}/admin/getOrdinary',
			success:function(data){
				$("#rightContent").html(data);
			}
		});
	});
	$("#unitAdmin").click(function(){
		$.ajax({
			url:'${base}/admin/getUnitAdmin',
			success:function(data){
				$("#rightContent").html(data);
			}
		});
	});
	$("#childAdmin").click(function(){
		$.ajax({
			url:'${base}/admin/getChildAdmin',
			success:function(data){
				$("#rightContent").html(data);
			}
		});
	});
	$("#headAdmin").click(function(){
		$.ajax({
			url:'${base}/admin/getHeadAdmin',
			success:function(data){
				$("#rightContent").html(data);
			}
		});
	});
	$("#superAdmin").click(function(){
		$.ajax({
			url:'${base}/admin/getSuperAdmin',
			success:function(data){
				$("#rightContent").html(data);
			}
		});
	});
	//默认加载主页
	$.ajax({
		url:'${base}/admin/getOrdinary',
		success:function(data){
			$("#rightContent").html(data);
		}
	});
});
	
</script>
</body>
</html>
