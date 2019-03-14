<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/inc/defaultInc.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link href="${base}/resources/css/page.css"  rel="stylesheet" type="text/css" />
	<table  class="table table-hover" >
   <tbody>
   <c:forEach items="${tecInfList }" var="tecInf">
   <tr>
    <td> <a href="#"><span class="glyphicon glyphicon-bell"></span></a></td>
   <td><a href="javascript:tecInformatin('${tecInf.id}');"><div class="length2">${tecInf.inforTitle}</div></a></td>
   <td>${tecInf.inforTime}</td>
  
   </tr>
    </c:forEach>
   </tbody>
   </table>
    <ul class="page" id="page">
		<li id="lastPage"><a href="${base}/portal/getAllTecInformationList?type=${type}">more&gt;&gt;</a></li>
	</ul>
   
<script type="text/javascript">
<%-- $(function(){    
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
   
function delUser(id) {
	layer.confirm('确定删除么？', {
		btn: ['确定','取消'] //按钮
	}, function(){
	     $.ajax({
			url:'tecInformation/delTecInf?id='+id,
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
} --%>
function tecInformatin(id){
   layer.open({
	  type: 2,
	  title: '消息',
	  shadeClose: true,
	  maxmin: true,
	  shade: 0.8,
	  area: ['80%', '90%'],
	  content: '${base}/portal/getTecInf?id='+id, //iframe的url
	}); 
} 
   </script>
  </body>
</html>
