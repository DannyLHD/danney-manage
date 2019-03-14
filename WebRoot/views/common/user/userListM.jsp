<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/inc/defaultInc.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="${base}/resources/css/page.css"  rel="stylesheet" type="text/css" />
<c:set var="ROLE_AUTH_SUPER_ADMIN" value="<%=CodeBookConsts.ROLE_AUTH_SUPER_ADMIN %>"></c:set>
<table class="table table-hover" >
   <tbody>
   <tr>
   <th>工号</th>
   <th>姓名</th>
   <th>电话</th>
   <th>邮箱</th>
   <th>子公司</th>
   <th>单位</th>
   <th>职务</th>
   <th>职称</th>
   <th>学历</th>
   <th>专业方向</th>
   <c:if test="${currentUser.role.authority==ROLE_AUTH_SUPER_ADMIN}"> 
   <th>操作</th>
   </c:if>
   </tr>
   
   <c:forEach items="${userList }" var="user">
   <tr>
   <td>${user.employeeID}</td>
   <td>${user.username}</td>
   <td>${user.phone}</td>
   <td>${user.email}</td>
   <td>${user.subsidiary}</td>
   <td>${user.unit}</td>
   <td>${user.position}</td>
   <td>${user.protitle}</td>
   <td>${user.degree}</td>
   <td>${user.researchField}</td>
   <td>
   <c:if test="${currentUser.role.authority==ROLE_AUTH_SUPER_ADMIN}"> 
   <a href="javascript:editUser('${user.id }')">编辑</a>
    <a href="javascript:delUser('${user.id }')">删除</a>
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

function editUser(id){
	layer.open({
		type: 2,
		title: '编辑用户',
		shadeClose: true,
		shade: 0.8,
		area: ['380px', '90%'],
		content: '${base}/super/editUser?id='+id, //iframe的url
		end:getData
	}); 
}
   
function delUser(id) {
	layer.confirm('确定删除么？', {
		btn: ['确定','取消'] //按钮
	}, function(){
	     $.ajax({
			url:'super/delUser?id='+id,
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
   </script>
  </body>
</html>
