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

  </head>
  
  <body>
  <div class="user-center-home">
	<h1><i class="fa fa-lock"></i>&nbsp;修改密码</h1>
	<form id="password" action="">
		<table class="info-edit-table">
			<tr>
				<td>原密码：</td>
				<td><input type="password" id="oldPass" name="oldPass"/></td>
			</tr>
			<tr>
				<td>新密码：</td>
				<td><input type="password" name="newPass1" id="newPwd"/></td>
				<!-- <td><p class="tip">密码要求8-20位,同时包含数字和字母</p></td> -->
			</tr>
			<tr>
				<td>确认密码：</td>
				<td><input type="password" name="newPass2"  id="repeatPwd"/></td>
			</tr>
			<!-- <tr><td colspan="2"><a class="btn btn-square" style="width:80px;" href="javascript:updatePass();">确定</a></td></tr> -->
		    <tr><td><a class="btn btn-primary btn-large" href="javascript:updatePass();">保存</a></td></tr>
		</table>
	</form>
</div>
  </body>
  <script type="text/javascript">
  function updatePass(){
	var isValid = true;
	$("input").not("#repeatPwd").each(function(){
		$(this).val(cTrim($(this).val(), 0));
		if(isNull($(this).val())){
			isError($(this).get(0),"不能为空");
			isValid = false;
		}/* else if(!checkPassword($(this).val())){
			isError($(this).get(0),"密码要求8-20位,同时包含数字和字母");
			isValid = false;
		} */else{
			isOk($(this).get(0));
		}
	});
	if($("#newPwd").val()!=$("#repeatPwd").val()){
		isError("#repeatPwd","两次输入不一致");
		isValid = false;
	}else{
		isOk("#repeatPwd");
	}
	
	if(!isValid)
		return false;
   $("#password").ajaxSubmit({
	  url:'user/updateSelfPassword',
	  type:'post',
	  success:function(data){
			if(data){
				layer.alert("保存成功",{icon:1,title:'提示',end:function(){
					window.location.href="${base}/login/logout"
				}});
			}else{
				layer.alert("修改失败",{icon:2,title:'提示'});
			}
		}
  });
  }
  </script>
</html>
