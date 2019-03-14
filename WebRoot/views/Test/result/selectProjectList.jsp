<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/inc/defaultInc.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <link rel="stylesheet"  href="<%=basePath%>/resources/font-awesome-4.2.0/css/font-awesome.min.css">
 <table class="user-table" >
   <tbody>
   <tr>
   <th>项目编号</th>
   <th>项目名称</th>
   <th>负责人</th>
   <th>项目内容</th>
   <th>项目费用</th>
   <th>其它说明</th>
   <th>编辑</th>
   </tr>
   
   <c:forEach items="${projectList }" var="project">
   <tr class="projectTr" >
   <td> <input type="hidden" class="projectId"  value="${project.id}">${project.projectnumber}</td>
   <td class="projectName">${project.projectname}</td>
   <td>${project.projectleader}</td>
   <td>${project.projectcontent}</td>
   <td>${project.projectcost}</td>
   <td>${project.other}</td>
   <td>
   <% %> 
   <p><i class="fa fa-square-o select-icon"></i></p>
</td>
   </tr>
    </c:forEach>
   </tbody>
   </table>
   <form action="" method="POST">
  
  <input type="submit"  onclick="project()" value="提交">
  </form>
 <script type="text/javascript">
 $(function(){
 	$(".projectTr").click(function(){
 		var $selectIcon = $(this).find(".select-icon");
 		if($selectIcon.hasClass("fa-square-o")){
 			$(".select-icon").removeClass("fa-check-square-o");
 			$(".select-icon").addClass("fa-square-o");
 			$selectIcon.removeClass("fa-square-o");
 			$selectIcon.addClass("fa-check-square-o");
 			parent.$("#projectname").val($(this).find(".projectName").html());
 			parent.$("#projectId").val($(this).find(".projectId").val());
 		}else{
 			$selectIcon.removeClass("fa-check-square-o");
 			$selectIcon.addClass("fa-square-o");
 		}
 	});
 });
 
</script>
   </body>
</html>