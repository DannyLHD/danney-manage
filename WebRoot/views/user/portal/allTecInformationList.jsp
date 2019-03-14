<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/inc/defaultInc.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<link href="${base}/resources/css/page.css"  rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>/resources/css/datagrid.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/resources/css/div.css">
<link rel="stylesheet" type="text/css" href="${base }/resources/css/userCenter.css">
  <body>
   <div class="container" style="cursor:  pointer;">
      <%@include file="/frame/head.jsp"%>
 <div class="row clearfix">
	<div class="col-md-4 column">
	 <form class="navbar-form navbar-right"  action="" id="searchForm" >
	   		<div class="form-group" >
	   		<input type="hidden" name="currentPage" value="${currentPage==null?1:currentPage}"/>
		   <input type="text" class="form-control"  id="username-id" name="inforTitle" placeholder="请输入标题">
		  
		   <a href="javascript:queryUser()">查找</a>
		    </div>
	  </form>
	<table  class="table table-hover" >
   <tbody>
   <c:forEach items="${tecInfList }" var="tecInf">
   <tr>
    <td> <a href="#"><span class="glyphicon glyphicon-bell"></span></a></td>
   <td><a href="javascript:getInformation('${tecInf.id}');"><div class="length2">${tecInf.inforTitle}</div></a></td>
   <td>${tecInf.inforTime}</td>
  
   </tr>
    </c:forEach>
   </tbody>
   </table>
   <c:if test="${totalPages>1 }">
	   <ul class="page" id="page">
			<li id="nextPage"><a>&gt;&gt;</a></li>
			<li class="page-text">${currentPage==null?1:currentPage}/${totalPages}</li>
			<li id="lastPage"><a>&lt;&lt;</a></li>
		</ul>
		<div style="clear:both;"></div>
   </c:if>
   </div>
   <div class="col-md-8 column">
    <div id="infContent"></div>
   </div>
   </div>
   </div>
   <%@include file="/frame/foot.jsp" %>
   </body>
<script type="text/javascript">
$(function(){
    $("#lastPage").click(function(){
    	var curPage = parseInt($("[name=currentPage]").val());
    	if(curPage-1>0){
	    	$("[name=currentPage]").val(curPage-1);
	    	getData();
    	}
    });
    $("#nextPage").click(function(){
    	var curPage = parseInt($("[name=currentPage]").val());
    	if(curPage ==null)
    		curPage=1;
    	var totalPage = ${totalPages};
    	if(curPage+1<=totalPage){
    		$("[name=currentPage]").val(curPage+1);
    		getData();
   		}
    });
});
   
function getData() {
	$("#searchForm").ajaxSubmit({
		url:"${base}/portal/getAllTecInformationList?type=${type}",
		success:function(data){
			$("body").html(data);
		}
	});
}
function queryUser(){
    getData();
}
function getInformation(id) {
	$.ajax({
		url:"${base}/portal/getTecInf?id="+id,
		success:function(data){
			$("#infContent").html(data);
		}
	});
}
   </script>
  </body>
</html>
