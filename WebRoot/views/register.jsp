
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/inc/defaultInc.jsp"%>
  <head>
    <base href="<%=basePath%>">
    <title>My JSP 'login.jsp' starting page</title>
    <script src="${base }/resources/js/validform.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/resources/css/login.css">
    <link rel="stylesheet" type="text/css" href="${base }/resources/css/userCenter.css">
</head>
<body>

<div id="content_register" >
    <div class="login-header">
        <h1>科技管理信息系统</h1>
       <!--  <p class="error-tip" style="color:red;text-align: center;"></p> -->
    </div>
  <form action="" name="userForm" id="formId"> 
  		<div class="login-input-box">
            <input type="text"  name="employeeID" id="employeeID" placeholder="请输入员工号">
        </div>
        <div class="login-input-box">
     		<input type="text"  name="username" id="username" placeholder="请输入姓名">
        </div>
         <div class="login-input-box">
            <input type="text" name="phone"  id="phone" placeholder="请输入手机号">
        </div>
        <div class="login-input-box">
            <input type="password" name="password"  id="password" placeholder="请输入密码">
        </div>
        <div class="login-input-box">
            <input type="text" name="unit"  id="unit" placeholder="单位">
        </div>
        <div class="login-input-box">
            <input type="text" name="subsidiary"  id="subsidiary" placeholder="子公司">
        </div>
    <div class="login-button-box">
        <input type="button" class="button" value="注册" onclick="registerUser();"/>
    </div>
    <div class="logon-box">
       	我有账号，去<a href="${base}/login/login">登录</a>
    </div>
    </form>
</div>

</body>
<script>
function registerUser(){
var isValid = true;
if ($("#employeeID").val()== '') {
		isError("#employeeID", "员工号不能为空");
		isValid = false;
} else {
	isOk("#employeeID");
}
if ($("#username").val() == '') {
		isError("#username", "用户名不能为空");
		isValid = false;
} else {
	isOk("#username");
}
if ($("#phone").val() == '' || !checkMobile($("#phone").val())) {
		isError("#phone", "手机号错误");
		isValid = false;
} else {
	isOk("#phone");
}
if ($("#password").val() == '') {
		isError("#password", "密码不能为空");
		isValid = false;
} else {
	isOk("#password");
}
if ($("#unit").val() == '') {
		isError("#unit", "单位不能为空");
		isValid = false;
} else {
	isOk("#unit");
}
if ($("#subsidiary").val() == '') {
		isError("#subsidiary", "子公司不能为空");
		isValid = false;
} else {
	isOk("#subsidiary");
}
if(!isValid)
	return false;
$("#formId").ajaxSubmit({
		url : '${base}/login/register.do',
		success : function(result) {
			if (result.status != "<%=Consts.MSG_SUCCESS%>"){
				/* $(".error-tip").html(result.message); */
				layer.msg(result.message);
			} else {
				layer.msg("注册成功",{time:1000,end:function(){
					window.location.href="${base}/login/login";
				}});
		//$("#formId").attr("action","${base}/login/login.do?toIndex=true")
		/* $("#formId").submit(); */
			}
		}
	});
}
</script>
</html>

