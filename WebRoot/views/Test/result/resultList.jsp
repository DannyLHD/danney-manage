<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/inc/defaultInc.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<table class="user-table" >
   <tbody>
   <tr>
   <th>成果编号</th>
   <th>成果名称</th>
   <th>负责人</th>
   <th>成果内容</th>
   <th>项目名称</th>
   <th>其它说明</th>
   <th>编辑</th>
   </tr>
   
   <c:forEach items="${resultList }" var="result">
   <tr>
   <td>${result.resultnumber}</td>
   <td>${result.resultname}</td>
   <td>${result.resultleader}</td>
   <td>${result.resultcontent}</td>
   <td>${result.project.projectname}</td>
   <td>${result.other}</td>
   
   <td>
   <% %> 
   <a href="javascript:editResult('${result.id }')">编辑</a>
    <a href="javascript:delResult('${result.id }')">删除</a>
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
});

function editResult(id){
	layer.open({
		type: 2,
		title: '编辑用户',
		shadeClose: true,
		shade: 0.8,
		area: ['380px', '90%'],
		content: '${base}/result/editResult?id='+id, //iframe的url
		end:getData
	}); 
}
   
function delResult(id) {
	layer.confirm('确定删除么？', {
		btn: ['确定','取消'] //按钮
	}, function(){
	     $.ajax({
			url:'result/delResult?id='+id,
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
