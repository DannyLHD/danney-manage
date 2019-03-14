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
   <input type="text" class="form-control"  id="projectname-id" name="projectName" placeholder="请输入项目名">
   <a href="javascript:queryProject()">查找</a>
    </div>
 </form>
	<table class="table table-hover">
   <tbody>
   <tr>
   <th>项目名称</th>
   <th>负责人</th>
   <th>项目内容</th>
   <th>申报单位</th>
   <th>子公司</th>
   </tr>
   
   <c:forEach items="${projectList }" var="project">
  <tr>
   <td><a onclick="getProjectInformation('${project.id }')">${project.projectName}</a></td>
   <td>${project.user.username}</td>
   <td><div class="length">${project.projectContent}</div></td>
   <td>${project.unit}</td>
   <td>${project.subsidiary}</td>
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

function queryProject(){
    getData();
}

function getData(){
	$("#searchForm").ajaxSubmit({
		url:"${base}/user/getProjectDetailList",
		success:function(data){
			$("#rightContent").html(data);
		}
	});
}
function getProjectInformation(id){
layer.open({
		type: 2,
		title: '项目',
		shadeClose: true,
		maxmin: true,
		shade: 0.8,
		area: ['70%', '90%'],
		content: '${base}/user/getProjectInformation?id='+id, //iframe的url
	}); 
}
   </script>
  </body>
</html>
