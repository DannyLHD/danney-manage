<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/inc/defaultInc.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addUser.jsp' starting page</title>
    
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
   <table width="89%">
<tr>
<td style="text-align:center;">
<h3>添加用户</h3>
</td>
</tr>
<tr>
<td style="text-align:center;">
<form action="" name="userForm" id="formId"> 
工号<input type="text" id="employeeID" name="employeeID"/><br/><br/>
用户名<input type="text" id="username" name="username"/><br/><br/>
密码<input type="text" id="password" name="password"/><br/><br/>
电话<input type="text" id="phone" name="phone"/><br/><br/>
邮箱<input type="text" id="email" name="email"/><br/><br/>
子公司<input type="text" id="subsidiary" name="subsidiary"/><br/><br/>
单位<input type="text" id="unit" name="unit"/><br/><br/>
职务<input type="text" id="position" name="position"/><br/><br/>
职称<input type="text" id="protitle" name="protitle"/><br/><br/>
学历<input type="text" id="degree" name="degree"/><br/><br/>
研究领域<input type="text" id="researchField" name="researchField"/><br/><br/>
 <input type="button" value="添加" onclick="addUser();"/>
<input type="button" value="返回" onclick="goBack();"/>
</form>
</td>
</tr>
</table>
  </body>
  <script type="text/javascript">
 /*  $(function(){
  	$("#username").click(function(){
  		alert("sdfs");
  	});
  }); */
  
  function addUser()
  {
//添加验证信息
$("#formId").ajaxSubmit({
	url:'super/addUser?role_auth=${ROLE_AUTH}',
	type:'post',
	success:function(data){
		if(data){
		   layer.alert("保存成功",{icon:2,title:'提示'});
		   goBack();
		}else{
			layer.alert("保存失败",{icon:2,title:'提示'});
		}
	}
});
}

function goBack()
{
	var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
    parent.layer.close(index); //执行关闭
}
  </script>
</html>
