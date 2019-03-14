<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/inc/defaultInc.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<link href="${base}/resources/css/page.css"  rel="stylesheet" type="text/css" />
	<table class="table table-hover" >
   <tbody>
   <tr>
   <th>计划编号</th>
   <th>发布编号</th>
   <th>标准名称</th>
   <th>制定/参与制定</th>
   <th>标准类型</th>
   <th>管理机构</th>
   <th>起草单位</th>
   <th>编写人员</th>
   <th>发布时间</th>
   <th>登记人员</th>
   </tr>
   <c:forEach items="${standardList }" var="standard">
   <tr>
   <td>${standard.schemeNo}</td>
   <td>${standard.releaseNo}</td>
   <td>${standard.standardName}</td>
   <td>${standard.formulation}</td>
  <td>${standard.type}</td>
  <td>${standard.manageOrganization}</td>
  <td>${standard.draftingUnit}</td>
  <td>${standard.writers}</td>
  <td>${standard.releaseTime}</td>
  <td>${standard.user.username}</td>
   </tr>
    </c:forEach>
   </tbody>
   </table>
   
   <c:if test="${totalPages>1 }">
	   <ul class="page" id="page">
			<li id="nextPage"><a>&gt;&gt;</a></li>
			<li class="page-text">${currentPage}/${totalPages}</li>
			<li id="lastPage"><a>&lt;&lt;</a></li>
		</ul>
		<div style="clear:both;"></div>
   </c:if>
   
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
    	var totalPage = ${totalPages};
    	if(curPage+1<=totalPage){
    		$("[name=currentPage]").val(curPage+1);
    		getData();
   		}
    });
});
  
   </script>
  </body>
</html>
