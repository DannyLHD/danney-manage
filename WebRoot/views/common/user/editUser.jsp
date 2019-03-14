<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/inc/defaultInc.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'editUser.jsp' starting page</title>
    
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
    
<div style="width:50%;height:auto;margin:0 auto;">
<table width="89%">
<tr>
<td style="text-align:center;">
<h3>编辑学生</h3>
</td>
</tr>
<tr>
<td style="text-align:center;">
<form action=""  id="userForm" >
<input type="hidden" name="id" value="${user.id }"/>
工号<input type="text" id="employeeID" name="employeeID" value="${user.employeeID }"/> <br/><br/>
姓名<input type="text" id="username" name="username" value="${user.username }"/> <br/><br/>
电话<a type="text" id="phone" name="phone" >${user.phone}</a> <br/><br/>
邮箱<input type="text" name="email" id="email" value="${user.email}"/><br/><br/>
子公司<input type="text" id="subsidiary" name="subsidiary" value="${user.subsidiary}"/><br/><br/>
单位<input type="text" id="unit" name="unit" value="${user.unit}"/><br/><br/>
职务<input type="text" id="position" name="position" value="${user.position}"/><br/><br/>
职称<input type="text" id="protitle" name="protitle" value="${user.protitle}"/><br/><br/>
学历<input type="text" id="degree" name="degree" value="${user.degree}"/><br/><br/>
研究领域<input type="text" id="researchField" name="researchField" value="${user.researchField}"/><br/><br/>
 <input type="button" value="保存"
onclick="addUser();">
<input type="button" value="返回"
onclick="goBack();">
</form>
</td>
</tr>
</table>
</div>
  </body>
  <script type="text/javascript">
  function addUser()
  {
  $("#userForm").ajaxSubmit({
	  url:'super/updateUser',
	  type:'post',
	  success:function(data){
			if(data){
				goBack();
			}else{
				layer.alert("保存失败",{icon:2,title:'出错'});
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
