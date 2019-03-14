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
	<script type="text/javascript" src="${base }/resources/plugins/webuploader/webuploader.js"></script>
	<script type="text/javascript" src="${base }/resources/plugins/webuploader/kj-manage/importUserInfo.js"></script>
	<link rel="stylesheet" type="text/css" href="${base }/resources/plugins/webuploader/webuploader.css">
  <c:set var="ROLE_AUTH_SUPER_ADMIN" value="<%=CodeBookConsts.ROLE_AUTH_SUPER_ADMIN %>"></c:set>
  <c:set var="ROLE_AUTH_ORDINARY" value="<%=CodeBookConsts.ROLE_AUTH_ORDINARY %>"></c:set>
  </head>
  <body>
  
	   <form class="navbar-form navbar-right"  action="" id="searchForm" >
	   		<div class="form-group" >
	   		<input type="hidden" name="currentPage" value="${currentPage==null?1:currentPage}"/>
		   <input type="text" class="form-control"  id="username-id" name="username" placeholder="请输入姓名">
		  
		   <a href="javascript:queryUser()">查找</a>
		   <c:if test="${currentUser.role.authority==ROLE_AUTH_SUPER_ADMIN}"> 
		   <a  href="javascript:addUser()" >添加</a>
		  <!--  <input type="file" name="filename"> -->
		  <c:if test="${ROLE_AUTH==ROLE_AUTH_ORDINARY}">
			   <div class="excel_uploaderDiv" style="float:right;">
				    <div id="excel_Picker" style="float:right;">导入</div>
				    <div id="excel_List" class="uploader-list" style="float:right;"></div>
				</div>
			</c:if>
		   </c:if>
		    </div>
	  </form>
	   
	   <div id="userListContent"></div>
 
<script type="text/javascript">
$(function(){
    getData();
    //初始化uploader
	uploader = import_createUploader("excel_List", "excel_Picker");
});

function addUser(){
   layer.open({
	  type: 2,
	  title: '添加用户',
	  shadeClose: true,
	  shade: 0.8,
	  area: ['380px', '90%'],
	  content: '${base}/super/toAddUser?role_auth=${ROLE_AUTH}', //iframe的url
	  end:getData
	}); 
} 
   
function queryUser(){
    getData();
}

function getData(){
	$("#searchForm").ajaxSubmit({
		url:"${base}/admin/queryUser?role_auth=${ROLE_AUTH}",
		success:function(data){
			$("#userListContent").html(data);
		}
	});
}
function inputUser() {
layer.confirm('将导入文件放入D:\用户.xlsx中导入', {
		btn: ['确定','取消'] //按钮
	}, function(){
	     $.ajax({
			url:"super/inputUser?role_auth=${ROLE_AUTH}",
			type:'post',
			success:function(result){
				if(result){
					layer.msg("导入成功",{time:700});
					getData();
				}else{
					layer.msg("导入失败",{time:700});
				}
			}
		});
		});
}
</script>
</body>
</html>
