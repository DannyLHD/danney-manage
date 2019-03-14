<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/inc/defaultInc.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
    <title>科研项目管理</title>
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
		<div class="btn user-center-btn">科研项目</div>
		<div id="accordion">
			<div  class="project-manage-btn" >项目申报管理
				<span class="glyphicon glyphicon-chevron-down"></span>
			</div>
			<div class="collapseContent" >
				<div id="application_manage" class="project-btn">待我审核的申报项目</div>
				<!-- <div  id="application_manage" class="project-btn">已审核项目</div> -->
			</div>
		</div>
		<div id="accordion2">
			<div class="project-manage-btn">项目立项管理
				<span  class="glyphicon glyphicon-chevron-down"></span>
			</div>
			<div class="collapseContent">
				<div  id="establishment_manage" class="project-btn">待我审核的立项项目</div>
				<!-- <div  class="project-btn">项目立项管理</div> -->
			</div>
		</div>
		<div id="accordion3">
			<div class="project-manage-btn">项目执行管理
				<span  class="glyphicon glyphicon-chevron-down"></span>
			</div>
			<div class="collapseContent">
				<div id="execution_manage"  class="project-btn">待我审核的执行项目</div>
				<!-- <div  class="project-btn">项目执行管理</div> -->
			</div>
		</div>
		<div id="accordion2">
			<div class="project-manage-btn">项目验收管理
				<span  class="glyphicon glyphicon-chevron-down"></span>
			</div>
			<div class="collapseContent">
				<div id="acceptance_manage"  class="project-btn">待我审核的验收项目</div>
				<!-- <div  class="project-btn">项目验收管理</div> -->
			</div>
		</div>
		<div id="accordion2">
			<div class="project-manage-btn">科研项目
				<span  class="glyphicon glyphicon-chevron-down"></span>
			</div>
			<div class="collapseContent">
				<div   id="project_self" class="project-btn">所有项目</div>
				<!-- <div id="project"  class="project-btn">所有项目</div> -->
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
		$("#application_manage").click(function(){
		$.ajax({
			url:'${base}/admin/getApplication',
			success:function(data){
				$("#rightContent").html(data);
			}
		});
		});
		$("#establishment_manage").click(function(){
		$.ajax({
			url:'${base}/admin/getEstablishment',
			success:function(data){
				$("#rightContent").html(data);
			}
		});
		});
		$("#execution_manage").click(function(){
		$.ajax({
			url:'${base}/admin/getExecution',
			success:function(data){
				$("#rightContent").html(data);
			}
		});
		});
		$("#acceptance_manage").click(function(){
		$.ajax({
			url:'${base}/admin/getAcceptance',
			success:function(data){
				$("#rightContent").html(data);
			}
		});
	});
	$("#project").click(function(){
		$.ajax({
			url:'${base}/portal/getProjectBasicList',
			success:function(data){
				$("#rightContent").html(data);
			}
		});
	});
	$("#project_self").click(function(){
		$.ajax({
			url:'${base}/user/getProjectDetailList',
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