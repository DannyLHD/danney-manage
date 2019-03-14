<%@page import="com.kj.util.CodeBookConsts"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/inc/defaultInc.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>煤炭企业科技管理信息系统</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
   <link rel="stylesheet" type="text/css" href="${base }/resources/css/userCenter.css">
  </head>
  
  <body>
   <div class="container" style="cursor:  pointer;">
      <%@include file="/frame/head.jsp" %>

	<div class="row clearfix">
		<div class="col-md-4 column">
			<h2>
				科技通知
			</h2>
			<div id="notice">
			</div>
		</div>
		<div class="col-md-4 column">
				
		</div>
		<div class="col-md-4 column">
			<h2>
				科研动态
			</h2>
			<div id="news">
			</div>
		</div>
	</div>
</div>
	<%@include file="/frame/foot.jsp" %>
	
  </body>
  <script type="text/javascript">
$(function(){  
	//加载科技消息 */
	getNews();
	getNotice();
	});  
function getNotice(){
 $.ajax({
		url:'${base}/login/getInform?type='+<%=CodeBookConsts.TYPE_INFORM%>,
		success:function(data){
			$("#notice").html(data);
		}
	});
}
function getNews(){
 $.ajax({
		url:'${base}/login/getInform?type='+<%=CodeBookConsts.TYPE_DYNAMIC%>,
		success:function(data){
			$("#news").html(data);
		}
	});
}

  </script>
</html>
