<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/inc/defaultInc.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
    <title>知识产权</title>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="${base}/resources/bootstrap/js/bootstrap.min.js"></script>
	<script src="${base }/resources/js/jquery.min.js"></script>
	<link rel="stylesheet" type="text/css" href="${base }/resources/css/userCenter.css">
	<link rel="stylesheet" type="text/css" href="${base }/resources/css/projectManage.css">
</head>


<body>
<div class="container" style="cursor:  pointer;">
<%@include file="/frame/unitAdminHead.jsp" %>
<div class="container user-center">
	<div class="col-md-3 user-center-left">
		<div class="btn user-center-btn">知识产权</div>
		<div id="accordion">
			<div  class="project-manage-btn" >专利管理
				<span class="glyphicon glyphicon-chevron-down"></span>
			</div>
			<div class="collapseContent" >
				<div  id="patent_self" class="project-btn">专利管理</div>
				<div  id="patent_manage" class="project-btn">所有专利</div>
			</div>
		</div>
		<div id="accordion2">
			<div class="project-manage-btn">标准管理
				<span  class="glyphicon glyphicon-chevron-down"></span>
			</div>
			<div class="collapseContent">
				<div  id="standard_self" class="project-btn">标准管理</div>
				<div  id="standard_manage" class="project-btn">所有标准</div>
			</div>
		</div>
		<div id="accordion3">
			<div class="project-manage-btn">科技论文管理
				<span  class="glyphicon glyphicon-chevron-down"></span>
			</div>
			<div class="collapseContent">
				<div  id="paper_self" class="project-btn">科技论文管理</div>
				<div  id="paper_manage" class="project-btn">所有科技论文</div>
			</div>
		</div>
		<div id="accordion2">
			<div class="project-manage-btn">科技奖励管理
				<span  class="glyphicon glyphicon-chevron-down"></span>
			</div>
			<div class="collapseContent">
				<div id="reward"  class="project-btn">待我审核的科技奖励</div>
				<div  id="reward_manage" class="project-btn">所有科技奖励</div>
			</div>
		</div>
	</div>

	<div id="rightContent" class="col-md-9 user-center-right" style="min-height:400px;border-radius: 10px;background-color:#e7e7e7;">
		
	</div>
</div>
</div>
<%-- <%@include file="/frame/foot.jsp" %> --%>


<script type="text/javascript">
$(function(){
	//默认隐藏
	$('.collapseContent').hide()
	$(".project-manage-btn").click(function(){
		$arrowIcon = $(this).find("span.glyphicon");
		$collapseContent = $(this).next("div.collapseContent");
		if($arrowIcon.hasClass("glyphicon-chevron-up")){
			$arrowIcon.removeClass("glyphicon-chevron-up");
			$arrowIcon.addClass("glyphicon-chevron-down");
			$collapseContent.slideUp();
		}else{
			$arrowIcon.removeClass("glyphicon-chevron-down");
			$arrowIcon.addClass("glyphicon-chevron-up");
			$collapseContent.slideDown();
		}
	});
	$("#patent_self").click(function(){
		$.ajax({
			url:'${base}/user/getPatent_Self',
			success:function(data){
				$("#rightContent").html(data);
			}
		});
		});
	$("#patent_manage").click(function(){
		$.ajax({
			url:'${base}/portal/getPatent',
			success:function(data){
				$("#rightContent").html(data);
			}
		});
		});
		$("#standard_self").click(function(){
		$.ajax({
			url:'${base}/user/getStandard_Self',
			success:function(data){
				$("#rightContent").html(data);
			}
		});
		});
	$("#standard_manage").click(function(){
		$.ajax({
			url:'${base}/portal/getStandard',
			success:function(data){
				$("#rightContent").html(data);
			}
		});
		});
		$("#paper_self").click(function(){
		$.ajax({
			url:'${base}/user/getPaper_Self',
			success:function(data){
				$("#rightContent").html(data);
			}
		});
		});
	$("#paper_manage").click(function(){
		$.ajax({
			url:'${base}/portal/getPaper',
			success:function(data){
				$("#rightContent").html(data);
			}
		});
		});
		$("#reward").click(function(){
		$.ajax({
			url:'${base}/admin/getReward',
			success:function(data){
				$("#rightContent").html(data);
			}
		});
		});
		$("#reward_manage").click(function(){
		$.ajax({
			url:'${base}/user/getReward_Detail',
			success:function(data){
				$("#rightContent").html(data);
			}
		});
	});
	
	//默认加载主页
	/* $.ajax({
		url:'${base}/user/informMessage',
		success:function(data){
			$("#rightContent").html(data);
		}
	}); */
});

		
</script>
</body>
</html>