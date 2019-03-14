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
   <th>级别</th>
   <th>项目名称</th>
   <th>完成单位</th>
   <th>等级</th>
   <th>申请人</th>
   <th>相关人员</th>
   <th>证书号</th>
   <th>申请日期</th>
   <th>存放处</th>
   <th>审核</th>
   <th>操作</th>
   </tr>
   
   <c:forEach items="${rewardList }" var="reward">
   <!-- 单位管理员 -->
   <c:if test="${user.role.authority==ROLE_AUTH_UNIT_ADMIN && user.unit == reward.user.unit && reward.check_unit==0}">
   <tr>
   <td>${reward.rank}</td>
   <td><a onclick="getProjectInformation('${reward.project.id }')">${reward.project.projectName}</a></td>
   <td>${reward.unit}</td>
   <td>${reward.grade}</td>
   <td><a onclick="getUser('${reward.user.id}');">${reward.user.username}</a></td>
   <td>${reward.participants}</td>
   <td>${reward.certificateNumber}</td>
   <td>${reward.date}</td>
   <td>${reward.storage}</td>
   <td>
   	 <a href="javascript:check_approved('${reward.id }')" style="background-color: #c0c0c0;border-radius: 2px;"><i class="fa  fa-check">通过</i></a>
   	 <a href="javascript:check_unapproved('${reward.id }')" style="background-color: #c0c0c0;border-radius: 2px;"><i class="fa  fa-times">否决</i></a>
   </td>
   <td>
   <% %> 
   <a href="javascript:editProject('${reward.id }')">编辑</a>
    <a href="javascript:delProject('${reward.id }')">删除</a>
</td>
   </tr>
   </c:if>
   <!-- 子公司管理员 -->
    <c:if test="${user.role.authority==ROLE_AUTH_CHILD_ADMIN && user.subsidiary == reward.user.subsidiary && reward.check_child_admin==0}">
   <tr>
   <td>${reward.rank}</td>
   <td><a onclick="getProjectInformation('${reward.project.id }')">${reward.project.projectName}</a></td>
   <td>${reward.unit}</td>
   <td>${reward.grade}</td>
   <td><a onclick="getUser('${reward.user.id}');">${reward.user.username}</a></td>
   <td>${reward.participants}</td>
   <td>${reward.certificateNumber}</td>
   <td>${reward.date}</td>
   <td>${reward.storage}</td>
   <td>
   	 <a href="javascript:check_approved('${reward.id }')" style="background-color: #c0c0c0;border-radius: 2px;"><i class="fa  fa-check">通过</i></a>
   	 <a href="javascript:check_unapproved('${reward.id }')" style="background-color: #c0c0c0;border-radius: 2px;"><i class="fa  fa-times">否决</i></a>
   </td>
   <td>
   <% %> 
   <a href="javascript:editProject('${reward.id }')">编辑</a>
    <a href="javascript:delProject('${reward.id }')">删除</a>
</td>
   </tr>
   </c:if>
   <!-- 总公司管理员 -->
    <c:if test="${user.role.authority==ROLE_AUTH_HEAD_ADMIN && reward.check_head_admin==0}">
   <tr>
   <td>${reward.rank}</td>
   <td><a onclick="getProjectInformation('${reward.project.id }')">${reward.project.projectName}</a></td>
   <td>${reward.unit}</td>
   <td>${reward.grade}</td>
   <td><a onclick="getUser('${reward.user.id}');">${reward.user.username}</a></td>
   <td>${reward.participants}</td>
   <td>${reward.certificateNumber}</td>
   <td>${reward.date}</td>
   <td>${reward.storage}</td>
   <td>
   	 <a href="javascript:check_approved('${reward.id }')" style="background-color: #c0c0c0;border-radius: 2px;"><i class="fa  fa-check">通过</i></a>
   	 <a href="javascript:check_unapproved('${reward.id }')" style="background-color: #c0c0c0;border-radius: 2px;"><i class="fa  fa-times">否决</i></a>
   </td>
   <td>
   <% %> 
   <a href="javascript:editProject('${reward.id }')">编辑</a>
    <a href="javascript:delProject('${reward.id }')">删除</a>
</td>
   </tr>
   </c:if>
   <!--超级管理员 -->
    <c:if test="${user.role.authority==ROLE_AUTH_SUPER_ADMIN}">
   <tr>
  <td>${reward.rank}</td>
   <td><a onclick="getProjectInformation('${reward.project.id }')">${reward.project.projectName}</a></td>
   <td>${reward.unit}</td>
   <td>${reward.grade}</td>
   <td><a onclick="getUser('${reward.user.id}');">${reward.user.username}</a></td>
   <td>${reward.participants}</td>
   <td>${reward.certificateNumber}</td>
   <td>${reward.date}</td>
   <td>${reward.storage}</td>
   <td>
   <c:if test="${reward.status==STATUS_SUBMIT && reward.check_unit==0}"> 
    	待审核
    </c:if>
    <c:if test="${reward.status==STATUS_SUBMIT &&reward.check_unit>0&& reward.check_head_admin==0}"> 
    	审核中 
    </c:if>
     <c:if test="${reward.status==STATUS_SUBMIT && (reward.check_unit<0 || reward.check_child_admin<0 || reward.check_head_admin<0)}"> 
    	审核未通过 
    </c:if>
    <c:if test="${reward.status==STATUS_SUBMIT && reward.check_head_admin>0}"> 
    	审核已通过 
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

function editReward(id){
	layer.open({
		type: 2,
		title: '编辑',
		shadeClose: true,
		maxmin: true,
		shade: 0.8,
		area: ['90%', '90%'],
		content: '${base}/ord/editReward?id='+id, //iframe的url
		end:getData
	}); 
}
   
function delReward(id) {
	layer.confirm('确定删除么？', {
		btn: ['确定','取消'] //按钮
	}, function(){
	     $.ajax({
			url:'user/delReward?id='+id,
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
function getUser(id){
layer.open({
		type: 2,
		title: '信息',
		shadeClose: true,
		shade: 0.8,
		area: ['380px', '90%'],
		content: '${base}/admin/getUser?id='+id, //iframe的url
		end:getData
	}); 
}
function check_approved(id){
 $.ajax({
			url:'${base}/admin/check_approved_reward?id='+id,
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
			url:'${base}/admin/check_unapproved_reward?id='+id,
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
