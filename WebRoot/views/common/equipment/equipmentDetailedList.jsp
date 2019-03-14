<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/inc/defaultInc.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="${base}/resources/css/page.css"  rel="stylesheet" type="text/css" />
<c:set var="ROLE_AUTH_UNIT_ADMIN" value="<%=CodeBookConsts.ROLE_AUTH_UNIT_ADMIN %>"></c:set>
<c:set var="ROLE_AUTH_CHILD_ADMIN" value="<%=CodeBookConsts.ROLE_AUTH_CHILD_ADMIN %>"></c:set>
<c:set var="ROLE_AUTH_HEAD_ADMIN" value="<%=CodeBookConsts.ROLE_AUTH_HEAD_ADMIN %>"></c:set>
<c:set var="ROLE_AUTH_SUPER_ADMIN" value="<%=CodeBookConsts.ROLE_AUTH_SUPER_ADMIN %>"></c:set>
	<table class="table table-hover" >
   <tbody>
   <tr>
   <th>设备编号</th>
   <th>设备名称</th>
   <th>子公司</th>
   <th>单位</th>
   <th>维护人员</th>
   <th>设备价格</th>
    <th>购买时间</th>
   <th>维护费用</th>
   <th>设备功能</th>
   <th>对外租赁</th>
   <th>关联项目</th>
   <th>备注</th>
    <c:if test="${user.role.authority!=ROLE_AUTH_HEAD_ADMIN}">
   <th>操作</th>
    </c:if>
   </tr>
   
   <c:forEach items="${equipmentList }" var="equipment">
   <tr>
   <td>${equipment.equipmentID}</td>
   <td><a onclick="getEquipmentInfo('${equipment.id}')"><div class="length">${equipment.equipmentName}</div></a></td>
   <td>${equipment.subsidiary}</td>
   <td>${equipment.unit}</td>
   <td><a onclick="getUser('${equipment.user.id}');">${equipment.user.username}</a></td>
   <td>${equipment.price}</td>
   <td>${equipment.date}</td>
   <td>${equipment.maintenanceCosts}</td>
   <td><div class="length">${equipment.function}</div></td>
   <td>${equipment.foreignLease!=null&&equipment.foreignLease?"是":"否"}</td>
   <td>
	   <div class="length"><c:forEach items="${equipment.projectList}" var="project">
	 	 ${project.projectName}&nbsp;
	   </c:forEach></div>
   </td>
   <td><div class="length3">${equipment.remark}</div></td>
   <td>
   
   <c:if test="${user.role.authority==ROLE_AUTH_UNIT_ADMIN && user.unit==equipment.unit&& user.subsidiary==equipment.subsidiary}">
    <a href="javascript:delEquipment('${equipment.id }')">删除</a>
    <a href="javascript:editEquipment('${equipment.id }')">编辑</a>
    </c:if>
    <c:if test="${user.role.authority==ROLE_AUTH_CHILD_ADMIN && user.subsidiary==equipment.subsidiary}">
    <a href="javascript:delEquipment('${equipment.id }')">删除</a>
    <a href="javascript:editEquipment('${equipment.id }')">编辑</a>
    </c:if>
     <c:if test="${user.role.authority==ROLE_AUTH_HEAD_ADMIN}">
    </c:if>
    <c:if test="${user.role.authority==ROLE_AUTH_SUPER_ADMIN}">
    <a href="javascript:delEquipment('${equipment.id }')">删除</a>
    <a href="javascript:editEquipment('${equipment.id }')">编辑</a>
    </c:if>
</td>
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
   
function delEquipment(id) {
	layer.confirm('确定删除么？', {
		btn: ['确定','取消'] //按钮
	}, function(){
	     $.ajax({
			url:'admin/delEquipment?id='+id,
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
function editEquipment(id){
	layer.open({
		type: 2,
		title: '编辑',
		shadeClose: true,
		shade: 0.8,
		area: ['380px', '90%'],
		content: '${base}/admin/editEquipment?id='+id, //iframe的url
		end:getData
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
function getEquipmentInfo(id){
layer.open({
		type: 2,
		title: '信息',
		shadeClose: true,
		shade: 0.8,
		area: ['380px', '90%'],
		content: '${base}/admin/getEquipmentInfo?id='+id, //iframe的url
		end:getData
	}); 
}
   </script>
  </body>
</html>
