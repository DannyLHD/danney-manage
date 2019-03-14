<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/inc/defaultInc.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ROLE_AUTH_UNIT_ADMIN" value="<%=CodeBookConsts.ROLE_AUTH_UNIT_ADMIN %>"></c:set>
<c:set var="ROLE_AUTH_CHILD_ADMIN" value="<%=CodeBookConsts.ROLE_AUTH_CHILD_ADMIN %>"></c:set>
<c:set var="ROLE_AUTH_HEAD_ADMIN" value="<%=CodeBookConsts.ROLE_AUTH_HEAD_ADMIN %>"></c:set>
<c:set var="ROLE_AUTH_SUPER_ADMIN" value="<%=CodeBookConsts.ROLE_AUTH_SUPER_ADMIN %>"></c:set>
<c:set value="<%=CodeBookConsts.STATUS_SUBMIT%>" var="STATUS_SUBMIT"></c:set>	
 <body>
  <form class="navbar-form navbar-right"  action="" id="searchForm" >
  		<div class="form-group" >
  		<input type="hidden" name="currentPage" value="${currentPage==null?1:currentPage}"/>
   <input type="text" class="form-control"  id="projectname-id" name="rewardName" placeholder="请输入奖励名称">
   <a href="javascript:queryReward()">查找</a>
    </div>
 </form>
	<table class="table table-hover">
   <tbody>
   <tr>
    <th>级别</th>
   <th>项目名称</th>
   <th>完成单位</th>
   <th>等级</th>
   <th>申请人</th>
   </tr>
   
   <c:forEach items="${rewardList }" var="reward">
  <tr>
    <td>${reward.rank}</td>
   <td>${reward.project.projectName}</td>
   <td>${reward.unit}</td>
   <td>${reward.grade}</td>
   <td>${reward.user.username}</td>
   </tr>
    </c:forEach>
   </tbody>
   </table>
   
   <c:if test="${totalPages>1 }">
	   <div style="float:right;margin-right:10%;">
			<input type="button" value="&lt;"  id="lastPage">
			<input type="button" value="&gt;"  id="nextPage">
		</div>
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
/* $(function(){
    getData();
}); */

   
function queryReward(){
    getData();
}

function getData(){
	$("#searchForm").ajaxSubmit({
		url:"${base}/portal/getRewardBasicList",
		success:function(data){
			$("#rightContent").html(data);
		}
	});
}
   </script>
  </body>
</html>
