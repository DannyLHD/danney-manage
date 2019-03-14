<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/inc/defaultInc.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table class="table table-hover">
   <tbody>
   <tr>
   <th>项目编号</th>
   <th>项目名称</th>
   <th>负责人</th>
   <th>项目内容</th>
   <th>项目经费</th>
   <th>完成时间</th>
   <th>编辑</th>
   </tr>
   
   <c:forEach items="${projectList }" var="project">
  <tr class="projectTr">
  <td><input type="hidden" class="projectId"  value="${project.id}">${project.projectNumber}</td>
   <td class="projectName">${project.projectName}</a></td>
   <td>${project.user.username}</td>
   <td>${project.projectContent}</td>
   <td>${project.projectcost}</td>
   <td>${project.project_Acceptance.completionTime}</td>
   <td>
   <% %> 
   <p><i class="fa fa-square-o select-icon"></i></p>
   </td>
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
     $(".projectTr").click(function(){
 		var $selectIcon = $(this).find(".select-icon");
 		if($selectIcon.hasClass("fa-square-o")){
 			$(".select-icon").removeClass("fa-check-square-o");
 			$(".select-icon").addClass("fa-square-o");
 			$selectIcon.removeClass("fa-square-o");
 			$selectIcon.addClass("fa-check-square-o");
 			parent.$("#projectName").val($(this).find(".projectName").html());
 			parent.$("#projectId").val($(this).find(".projectId").val());
 		}else{
 			$selectIcon.removeClass("fa-check-square-o");
 			$selectIcon.addClass("fa-square-o");
 		}
 	});
});

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
