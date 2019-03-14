<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/inc/defaultInc.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'selfMessage.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css" href="${base }/resources/css/userCenter.css">
  </head>
  
  <body>
  <div class="user-center-home">
	<h1><i class="fa fa-user"></i>&nbsp;个人信息</h1>
	<form id="userForm" action="">
		<table class="info-edit-table">
			<tr>
				<td><input name="id" type="hidden" value="${user.id}"/></td>
			</tr>
			<tr>
				<td>工号：</td>
				<td>${user.employeeID}</td>
			</tr>
			<tr>
				<td>姓名：</td>
				<td>${user.username}</td>
			</tr>
			<tr>
				<td>手机号：</td>
				<td>${user.phone}</td>
			</tr>
			<tr>
				<td>邮箱：</td>
				<td><input type="text" name="email" value="${user.email}"/></td>
			</tr>
			<tr>
				<td>子公司:</td>
				<td><input type="text" id="subsidiary" name="subsidiary" value="${user.subsidiary}"/></td>
			</tr>
			<tr>
				<td>单位:</td>
				<td><input type="text" id="unit" name="unit" value="${user.unit}"/></td>
			</tr>
			<tr>
				<td>职务:</td>
				<td><input type="text" id="position" name="position" value="${user.position}"/></td>
			</tr>
			<tr>
				<td>职称:</td>
				<td><input type="text" id="protitle" name="protitle" value="${user.protitle}"/></td>
			</tr>
			<tr>
				<td>学历:</td>
				<td><input type="text" id="degree" name="degree" value="${user.degree}"/></td>
			</tr>
			<tr>
				<td>专业方向:</td>
				<td><input type="text" id="researchField" name="researchField" value="${user.researchField}"/></td>
			</tr>
			<tr><td colspan="2"><a class="btn btn-primary btn-large" style="width:80px;" href="javascript:save();">保存</a></td></tr>
		</table>
	</form>
</div>


<script type="text/javascript">
 function save(){
	 $("#userForm").ajaxSubmit({
	  url:'user/updateSelf',
	  type:'post',
	  success:function(data){
			if(data){
				layer.alert("保存成功",{icon:1,title:'提示'});
				/* goBack(); */
			}else{
				layer.alert("保存失败",{icon:2,title:'提示'});
			}
		}
  });
}
</script>
</body>
</html>
  <%-- <a href="javascript:editUser('${user.id }')">修改</a>
  <script type="text/javascript">
  function editUser(id){
	layer.open({
		type: 2,
		title: '编辑用户',
		shadeClose: true,
		shade: 0.8,
		area: ['380px', '90%'],
		content: '${base}/user/editSelf?id='+id, //iframe的url
		end: function () {
                top.window.parent.location.reload();
            }
	}); 
}
</script>
</html> --%>
