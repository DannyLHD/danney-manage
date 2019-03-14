<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<title>登陆跳转中...</title>
  </head>
  
  <body>
  	<h3>登陆跳转中...</h3>
  	<br/>
  	<script type="text/javascript">
  		window.location.href="<%=basePath%>${url}";
  	</script>
  </body>
</html>
