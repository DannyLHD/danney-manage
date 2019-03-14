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
   <th>编辑</th>
   </tr>
   
   <c:forEach items="${equipmentList }" var="equipment">
   <tr class="equipmentTr">
   <td><input type="hidden" class="equipmentId"  value="${equipment.id}">${equipment.equipmentID}</td>
   <td class="equipmentName">${equipment.equipmentName}</td>
   <td>${equipment.subsidiary}</td>
   <td>${equipment.unit}</td>
   <td>${equipment.user.username}</td>
   <td><div class="length">${equipment.function}</div></td>
   <td>${equipment.foreignLease!=null&&equipment.foreignLease?"是":"否"}</td>
   <td>
   <% %> 
   <p><i class="fa fa-square-o select-icon"></i></p>
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
   <form action="" method="POST">
  
<!--   <input type="submit"  onclick="project()" value="提交"> -->
  </form>
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
    $(".equipmentTr").click(function(){
 		var $selectIcon = $(this).find(".select-icon");
 		if($selectIcon.hasClass("fa-square-o")){
 			$(".select-icon").removeClass("fa-check-square-o");
 			$(".select-icon").addClass("fa-square-o");
 			$selectIcon.removeClass("fa-square-o");
 			$selectIcon.addClass("fa-check-square-o");
 			parent.$("#equipmentName").val($(this).find(".equipmentName").html());
 			parent.$("#equipmentId").val($(this).find(".equipmentId").val());
 		}else{
 			$selectIcon.removeClass("fa-check-square-o");
 			$selectIcon.addClass("fa-square-o");
 		}
 	});
});
   </script>
  </body>
</html>
