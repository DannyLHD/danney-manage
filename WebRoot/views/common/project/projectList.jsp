<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/inc/defaultInc.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ROLE_AUTH_UNIT_ADMIN" value="<%=CodeBookConsts.ROLE_AUTH_UNIT_ADMIN %>"></c:set>
<c:set var="ROLE_AUTH_CHILD_ADMIN" value="<%=CodeBookConsts.ROLE_AUTH_CHILD_ADMIN %>"></c:set>
<c:set var="ROLE_AUTH_HEAD_ADMIN" value="<%=CodeBookConsts.ROLE_AUTH_HEAD_ADMIN %>"></c:set>
<c:set var="ROLE_AUTH_SUPER_ADMIN" value="<%=CodeBookConsts.ROLE_AUTH_SUPER_ADMIN %>"></c:set>
<c:set value="<%=CodeBookConsts.STATUS_SUBMIT%>" var="STATUS_SUBMIT"></c:set>	
	<table class="table table-hover">
   <tbody>
   <tr>
   <th>项目名称</th>
   <th>负责人</th>
   <th>项目内容</th>
   <th>预计经费</th>
   <th>提交时间</th>
   <th>审核</th>
   <th>操作</th>
   </tr>
   
   <c:forEach items="${projectList }" var="project">
   <!-- 单位管理员 -->
   <c:if test="${user.role.authority==ROLE_AUTH_UNIT_ADMIN && user.unit == project.unit && project.check_unit==0}">
   <tr>
   <td><a onclick="getProjectInformation('${project.id }')">${project.projectName}</a></td>
   <td>${project.user.username}</td>
   <td><div class="length">${project.projectContent}</div></td>
   <td>${project.projectcost}</td>
   <td>${project.submitTime}</td>
   <td>
   	 <a href="javascript:check_approved('${project.id }')" style="background-color: #c0c0c0;border-radius: 2px;"><i class="fa  fa-check">通过</i></a>
   	 <a href="javascript:check_unapproved('${project.id }')" style="background-color: #c0c0c0;border-radius: 2px;"><i class="fa  fa-times">否决</i></a>
   </td>
   <td>
   <% %> 
  <%--  <a href="javascript:editProject('${project.id }')">编辑</a> --%>
    <a href="javascript:delProject('${project.id }')">删除</a>
</td>
   </tr>
   </c:if>
   <!-- 子公司管理员 -->
    <c:if test="${user.role.authority==ROLE_AUTH_CHILD_ADMIN && user.subsidiary == project.subsidiary && project.check_child_admin==0&&project.check_unit>0}">
   <tr>
   <td><a onclick="getProjectInformation('${project.id }')">${project.projectName}</a></td>
   <td>${project.user.username}</td>
   <td><div class="length">${project.projectContent}</div></td>
   <td>${project.projectcost}</td>
    <td>${project.submitTime}</td>
   <td>
   	 <a href="javascript:check_approved('${project.id }')" style="background-color: #c0c0c0;border-radius: 2px;"><i class="fa  fa-check">通过</i></a>
   	 <a href="javascript:check_unapproved('${project.id }')" style="background-color: #c0c0c0;border-radius: 2px;"><i class="fa  fa-times">否决</i></a>
   </td>
   <td>
   <% %> 
 <%--   <a href="javascript:editProject('${project.id }')">编辑</a> --%>
    <a href="javascript:delProject('${project.id }')">删除</a>
</td>
   </tr>
   </c:if>
   <!-- 总公司管理员 -->
    <c:if test="${user.role.authority==ROLE_AUTH_HEAD_ADMIN && project.check_head_admin==0&&project.check_child_admin>0}">
   <tr>
   <td><a onclick="getProjectInformation('${project.id }')">${project.projectName}</a></td>
   <td>${project.user.username}</td>
   <td><div class="length">${project.projectContent}</div></td>
   <td>${project.projectcost}</td>
    <td>${project.submitTime}</td>
   <td>
   <form id="projectNumberForm" action="">
   	 <input type="text" name="projectNumber" placeholder="项目编号" value="${project.projectNumber }"><a href="javascript:check_approved_number('${project.id }')" style="background-color: #c0c0c0;border-radius: 2px;"><i class="fa  fa-check">通过</i></a>
   	 <a href="javascript:check_unapproved('${project.id }')" style="background-color: #c0c0c0;border-radius: 2px;"><i class="fa  fa-times">否决</i></a>
   </form>
   </td>
   <td>
   <% %> 
  <%--  <a href="javascript:editProject('${project.id }')">编辑</a> --%>
    <a href="javascript:delProject('${project.id }')">删除</a>
</td>
   </tr>
   </c:if>
   <!--超级管理员 -->
    <c:if test="${user.role.authority==ROLE_AUTH_SUPER_ADMIN}">
   <tr>
   <td><a onclick="getProjectInformation('${project.id }')">${project.projectName}</a></td>
   <td>${project.user.username}</td>
   <td><div class="length">${project.projectContent}</div></td>
   <td>${project.projectcost}</td>
    <td>${project.submitTime}</td>
   <td>
   <c:if test="${project.status==STATUS_SUBMIT && project.check_unit==0}"> 
    <a>待审核</a>
    </c:if>
    <c:if test="${project.status==STATUS_SUBMIT &&project.check_unit>0&& project.check_head_admin==0}"> 
    <a>审核中</a>
    </c:if>
     <c:if test="${project.status==STATUS_SUBMIT && (project.check_unit<0 || project.check_child_admin<0 || project.check_head_admin<0)}"> 
    <a>审核未通过</a>
    </c:if>
    <c:if test="${project.status==STATUS_SUBMIT && project.check_head_admin>0}"> 
    <a >审核已通过</a>
    </c:if>
   </td>
   <td>
   <% %> 
   <a href="javascript:editProject('${project.id }')">编辑</a>
    <a href="javascript:delProject('${project.id }')">删除</a>
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
function check_approved(id){
 $.ajax({
			url:'${base}/admin/check_approved?id='+id,
			type:'post',
			success:function(result){
				if(result){
					layer.msg("审核成功",{time:700});
					getData();
				}else{
					layer.msg("审核失败",{time:700});
				}
			}
		});
}

function check_approved_number(id){
 $("#projectNumberForm").ajaxSubmit({
			url:'${base}/admin/check_approved?id='+id,
			type:'post',
			success:function(result){
				if(result){
					layer.msg("审核成功",{time:700});
					getData();
				}else{
					layer.msg("审核失败",{time:700});
				}
			}
		});
}
function check_unapproved(id){
 $.ajax({
			url:'${base}/admin/check_unapproved?id='+id,
			type:'post',
			success:function(result){
				if(result){
					layer.msg("审核成功",{time:700});
					getData();
				}else{
					layer.msg("审核失败",{time:700});
				}
			}
		});
}

   </script>
  </body>
</html>
