<%@page import="com.kj.util.Consts"%>
<%@page import="com.kj.util.CodeBookConsts"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String username = (String)request.getSession().getAttribute(Consts.CURRENT_USER);
%>
<c:set value="<%=basePath%>" var="base"></c:set>
<c:set var="username" value="<%=username%>" scope="session"></c:set>
<!------------------------------------ css files --------------------------------------->

<!-- font awesome for icons -->
<link href="${base }/resources/font-awesome-4.2.0/css/font-awesome.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${base}/resources/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${base}/resources/css/default.css">	
<!-- custom css -->
<!------------------------------------ css files --------------------------------------->
<link href="${base}/resources/css/limit.css"  rel="stylesheet" type="text/css" />
<!------------------------------------ js files ---------------------------------------->
<!-- jquery -->
<script src="${base }/resources/js/jquery.min.js"></script>
<!-- jquery.form -->
<script src="${base }/resources/js/jquery.form.min.js"></script>
<!-- layer -->
<script src="${base }/resources/layer/layer.js"></script>
<!-- bootstrap -->
<script src="${base}/resources/bootstrap/js/bootstrap.min.js"></script>
<!-- custom js -->
<script src="${base}/resources/js/validform.js"></script>
<!------------------------------------ js files ---------------------------------------->

<!------------------------------------ global functions ---------------------------------------->
<script type="text/javascript">
$(function(){

});

// 设置cook ======================================================================
function setCookie(name, value, seconds){
	if(seconds == null){
		seconds = 3600*24*30;
	}
	var expire = new Date();
	expire.setTime(expire.getTime() + seconds*1000);
	document.cookie = name + "="+ escape (value) + ";expires=" + expire.toGMTString();
}
// 获取cook ======================================================================
function getCookie(name){
	var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
	if(arr=document.cookie.match(reg))
		return unescape(arr[2]);
	else 
		return null;
}
// 删除cook ======================================================================
function deleteCookie(name){
	var expire = new Date();
	expire.setTime(expire.getTime() - 1000);
	var cval=getCookie(name);
	if(cval!=null)
		document.cookie = name+"="+cval+";expires="+expire.toGMTString();
}
</script>
<!------------------------------------ global functions ---------------------------------------->