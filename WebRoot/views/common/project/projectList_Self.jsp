<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/inc/defaultInc.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <c:set value="<%=CodeBookConsts.STATUS_SAVE%>" var="STATUS_SAVE"></c:set>
   <c:set value="<%=CodeBookConsts.STATUS_SUBMIT%>" var="STATUS_SUBMIT"></c:set>
   <c:set value="<%=CodeBookConsts.STAGE_APPLICATION%>" var="STAGE_APPLICATION"></c:set>
   <c:set value="<%=CodeBookConsts.STAGE_ESTABLISHMENT%>" var="STAGE_ESTABLISHMENT"></c:set>
   <c:set value="<%=CodeBookConsts.STAGE_EXECUTION%>" var="STAGE_EXECUTION"></c:set>
    <c:set value="<%=CodeBookConsts.STAGE_ACCEPTANCE%>" var="STAGE_ACCEPTANCE"></c:set>
   
	<table class="table table-hover">
   <tbody>
   <tr>
   <th>项目名称</th>
   <th>负责人</th>
   <th>项目内容</th>
   <th>预计经费</th>
   <th>操作</th>
   </tr>
   
   <c:forEach items="${projectList }" var="project">
   <c:if test="${!(project.stage==STAGE_ACCEPTANCE&&project.check_head_admin>0)}">
   <tr>
   <td><a onclick="getProjectInformation('${project.id }')">${project.projectName}</a></td>
   <td>${project.user.username}</td>
   <td><div class="length">${project.projectContent}</div></td>
   <td>${project.projectcost}</td>
   <td>
  <c:if test="${project.status==STATUS_SAVE}"> 
    <a href="javascript:editProject('${project.id }')">编辑</a>
    <a href="javascript:delProject('${project.id }')">删除</a>
    <a href="javascript:submitProject('${project.id }')">提交</a>
    </c:if>
    <c:if test="${project.status==STATUS_SUBMIT && project.check_unit==0}"> 
   		待审核
    </c:if>
    <c:if test="${project.status==STATUS_SUBMIT &&project.check_unit>0&& project.check_head_admin==0}"> 
    	审核中	
    </c:if>
     <c:if test="${project.status==STATUS_SUBMIT && (project.check_unit<0 || project.check_child_admin<0 || project.check_head_admin<0)}"> 
    	审核未通过<a href="javascript:delProject('${project.id }')">删除</a>
    </c:if>
    <c:if test="${project.status==STATUS_SUBMIT && project.stage==STAGE_APPLICATION && project.check_head_admin>0}"> 
    <a href="javascript:getForm_Establishment('${project.id }')">审核已通过，填写项目立项书</a>
    </c:if>
     <c:if test="${project.status==STATUS_SUBMIT && project.stage==STAGE_ESTABLISHMENT && project.check_head_admin>0}"> 
    <a href="javascript:getForm_Execution('${project.id }')">审核已通过，填写项目执行书</a>
    </c:if>
     <c:if test="${project.status==STATUS_SUBMIT && project.stage==STAGE_EXECUTION&& project.check_head_admin>0}"> 
    <a href="javascript:getForm_Acceptance('${project.id }')">审核已通过，填写验收书</a>
    </c:if>
</td>
   </tr>
   </c:if>
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

function editProject(id){
	layer.open({
		type: 2,
		title: '编辑',
		shadeClose: true,
		maxmin: true,
		shade: 0.8,
		area: ['90%', '90%'],
		content: '${base}/ord/editProject?id='+id, //iframe的url
		end:getData
	}); 
}
   
function delProject(id) {
	layer.confirm('确定删除么？', {
		btn: ['确定','取消'] //按钮
	}, function(){
	     $.ajax({
			url:'user/delProject?id='+id,
			type:'post',
			success:function(result){
				if(result=="<%=Consts.MSG_SUCCESS%>"){
					layer.msg("删除成功",{time:700});
					getData();
				}else{
					layer.msg("删除失败",{time:700});
				}
			}
		});
	});
}
function submitProject(id){
	layer.confirm('确定提交么？', {
		btn: ['确定','取消'] //按钮
	}, function(){
	     $.ajax({
			url:'${base}/ord/submitProject?id='+id,
			type:'post',
			success:function(result){
				if(result){
					layer.msg("提交成功",{time:700});
					getData();
				}else{
					layer.msg("提交失败",{time:700});
				}
			}
		});
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
function getForm_Establishment(id){
layer.open({
		type: 2,
		title: '项目立项',
		shadeClose: true,
		maxmin: true,
		shade: 0.8,
		area: ['70%', '90%'],
		content: '${base}/ord/getEstablishmentForm?id='+id, //iframe的url
	});
}
function getForm_Execution(id){
layer.open({
		type: 2,
		title: '项目执行',
		shadeClose: true,
		maxmin: true,
		shade: 0.8,
		area: ['70%', '90%'],
		content: '${base}/ord/getExecutionForm?id='+id, //iframe的url
	}); 
}
function getForm_Acceptance(id){
layer.open({
		type: 2,
		title: '项目验收',
		shadeClose: true,
		maxmin: true,
		shade: 0.8,
		area: ['70%', '90%'],
		content: '${base}/ord/getAcceptanceForm?id='+id, //iframe的url
	}); 
}
   </script>
  </body>
</html>
