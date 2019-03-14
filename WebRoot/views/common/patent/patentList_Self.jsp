<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/inc/defaultInc.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<link href="${base}/resources/css/page.css"  rel="stylesheet" type="text/css" />
	<table class="table table-hover" >
   <tbody>
   <tr>
   <th>类型</th>
   <th>专利名称</th>
   <th>发明人</th>
   <th>专利号</th>
   <th>专利权人</th>
   <th>申请日</th>
   <th>授权日</th>
   <th>代理机构</th>
   <th>效益转化</th>
   <th>存放处</th>
   <th>登记人员</th>
   <th>操作</th>
   </tr>
   <c:forEach items="${patentList }" var="patent">
   <tr>
   <td>${patent.type}</td>
   <td>${patent.patentName}</td>
   <td>${patent.inventor}</td>
   <td>${patent.patentNo}</td>
  <td>${patent.patentee}</td>
  <td>${patent.date_Application}</td>
  <td>${patent.date_Authorization}</td>
  <td>${patent.agency}</td>
  <td><div class="length">${patent.benefits}</div></td>
  <td>${patent.storage}</td>
  <td>${patent.user.username}</td>
   <td>
   <% %> 
    <a href="javascript:delPatent('${patent.id }')">删除</a>
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
   
function delPatent(id) {
	layer.confirm('确定删除么？', {
		btn: ['确定','取消'] //按钮
	}, function(){
	     $.ajax({
			url:'user/delPatent?id='+id,
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
