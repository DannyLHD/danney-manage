<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/inc/defaultInc.jsp"%>
  <head>
    <base href="<%=basePath%>">
    <title>My JSP 'login.jsp' starting page</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/resources/css/login.css">
    <link rel="stylesheet" type="text/css" href="${base }/resources/css/userCenter.css">
</head>
<body>

<div id="content" >
    <div class="login-header">
        <h1>科技管理信息系统</h1>
    </div>
    <form action="${base}/login/login.do" method="POST">
        <div class="login-input-box">
            <span class="glyphicon glyphicon-earphone"></span>
            <input type="text"  name="phone" placeholder="Please enter your phone" required="required">
        </div>
        <div class="login-input-box">
            <span class="glyphicon glyphicon-lock"></span>
            <input type="password" name="password"  placeholder="Please enter your password" required="required">
        </div>
    
   <!--  <div class="remember-box">
        <label>
            <input type="checkbox" name="rememberMe">&nbsp;Remember Me
        </label>
    </div> -->
    <div class="login-button-box">
        <input class="button" type="submit" value="login"/>
    </div>
    <div class="logon-box">
        <!-- <a href="">Forgot?</a> -->
        <a href="${base}/login/register" style="float: right;">Register</a>
    </div>
    </form>
</div>

</body>
  <script>
$(function(){
	//从url获取错误信息
	var reg = new RegExp("(^|&)"+ "errorMsg" +"=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if(r!=null){
		$('.error-tip').html("登录失败:"+decodeURIComponent(r[2]));
      }
});
</script>
</html>
