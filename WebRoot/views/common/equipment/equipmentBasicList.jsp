<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/inc/defaultInc.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="${base}/resources/css/page.css"  rel="stylesheet" type="text/css" />
	<table class="table table-hover" >
   <tbody>
   <tr>
   <th>设备编号</th>
   <th>设备名称</th>
   <th>子公司</th>
   <th>单位</th>
   <th>维护人员</th>
   <th>设备功能</th>
   <th>对外租赁</th>
   <th>备注</th>
   </tr>
   
   <c:forEach items="${equipmentList }" var="equipment">
   <tr>
   <td>${equipment.equipmentID}</td>
   <td><div class="length">${equipment.equipmentName}</div></td>
   <td>${equipment.subsidiary}</td>
   <td>${equipment.unit}</td>
   <td>${equipment.user.username}</td>
   <td><div class="length">${equipment.function}</div></td>
   <td>${equipment.foreignLease!=null&&equipment.foreignLease?"是":"否"}</td>
  <%--  <td>
	   <c:forEach items="${equipment.projectList}" var="project">
	 	 ${project.projectName}&nbsp;
	   </c:forEach>
   </td> --%>
   <td><div class="length">${equipment.remark}</div></td>
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


   </script>
  </body>
</html>
