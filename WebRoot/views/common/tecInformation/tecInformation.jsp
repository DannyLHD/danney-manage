<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/inc/defaultInc.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<link href="${base}/resources/css/page.css"  rel="stylesheet" type="text/css" />
<link href="${base}/resources/css/font.css"  rel="stylesheet" type="text/css" />
   <div class="title">
  	${tecInf.inforTitle}</br></br>
   </div>
   <div class="content" style="padding:0 20px;">
  	 ${tecInf.inforContent}</br></br></br>
   </div>
   <div class="foot">
	   <c:if test="${tecInf.informationFile!=null && tecInf.informationFile!=''}">
	   		<a href="javascript:location.href='${shareupload}${infomationDir}/${tecInf.informationFile}';">
				附件：${fn:split(tecInf.informationFile, fileNameSeparator)[0]}
				<i class="fa fa-download"></i>
		   	</a>
	   	</c:if></br>
	   发表时间：${tecInf.inforTime}
	   发表人${tecInf.user.username}
   </div>
</html>
