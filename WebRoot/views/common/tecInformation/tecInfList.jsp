<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/inc/defaultInc.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<link href="${base}/resources/css/page.css"  rel="stylesheet" type="text/css" />
<link href="${base}/resources/css/limit.css"  rel="stylesheet" type="text/css" />
	<table class="table table-hover" >
   <tbody>
   <tr>
   <th>题目</th>
   <th>内容</th>
   <th>附件</th>
   <th>日期</th>
   <th>发表人</th>
   <th>操作</th>
   </tr>
   
   <c:forEach items="${tecInfList }" var="tecInf">
   <tr>
   <td><div class="length">${tecInf.inforTitle}</div></td>
   <td><a href="javascript:tecInformatin('${tecInf.id}');"><div class="length">${tecInf.inforContent}</div></a></td>
   <td><c:if test="${tecInf.informationFile!=null && tecInf.informationFile!=''}">
   		<a href="javascript:location.href='${shareupload}${infomationDir}/${tecInf.informationFile}';">
			<div class="length">${fn:split(tecInf.informationFile, fileNameSeparator)[0]}
			<i class="fa fa-download"></i></div>
	   	</a>
   	</c:if></td>
   <td>${tecInf.inforTime}</td>
   <td>${tecInf.user.username}</td>
   <td>
   <% %> 
    <a href="javascript:delUser('${tecInf.id }')">删除</a>
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
function tecInformatin(id){
   layer.open({
	  type: 2,
	  title: '消息',
	  shadeClose: true,
	  maxmin: true,
	  shade: 0.8,
	  area: ['80%', '90%'],
	  content: '${base}/portal/getTecInf?id='+id, //iframe的url
	  end:getData
	}); 
}    
function delUser(id) {
	layer.confirm('确定删除么？', {
		btn: ['确定','取消'] //按钮
	}, function(){
	     $.ajax({
			url:'user/delTecInf?id='+id,
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
