<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/inc/defaultInc.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="user-center-home">
	<h1><i class="fa fa-phone"></i>&nbsp;修改手机号</h1>
	<form id="phoneForm" action="">
		<table class="info-edit-table">
			<tr>
				<td>原手机号：</td>
				<td>${user.phone}</td>
			</tr>
			<tr>
				<td>登录密码：</td>
				<td><input type="password" id="pwd" name="pwd"/></td>
			</tr>
			<tr>
				<td>新手机号：</td>
				<td>
					<input type="text" id="phone" name="phone"/>
					<!-- <a class="btn btn-square-nofill" id="smsCodeBtn" style="margin-left:10px;height:30px;" onclick="sendCode(this)">发送验证码</a> -->
				</td>
			</tr>
			<!-- <tr>
				<td>验证码：</td>
				<td><input type="text" id="smsCode"/></td>
			</tr> -->
			<tr><td colspan="2"><a class="btn btn-primary btn-large" style="width:80px;" href="javascript:save();">保存</a></td></tr>
		</table>
	</form>
</div>
<html>
<script type="text/javascript">
function save(){
$("#phoneForm").ajaxSubmit({
	  url:'${base}/user/updateSelfPhone',
	  type:'post',
	  success:function(data){
			if(data){
				layer.alert("保存成功",{icon:1,title:'提示',end:function(){
					window.location.href="${base}/login/logout"
				}});
			}else{
				layer.alert("保存失败",{icon:2,title:'提示'});
			}
		}
  });
}
</script>
</html>