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
   <td>${equipment.equipmentID}</td>
   </tr>
   <tr>
   <th>设备名称</th>
   <td>${equipment.equipmentName}</td>
   </tr>
   <tr>
   <th>子公司</th>
   <td>${equipment.subsidiary}</td>
   </tr>
   	<tr>
   <th>单位</th>
   <td>${equipment.unit}</td>
   </tr>
   <tr>
   <th>	维护人员</th>
   <td>${equipment.user.username} </td>
   </tr>
   <tr>
   <th>	设备价格</th>
   <td>${equipment.price} </td>
   </tr>
   <tr>
   <th>	购买时间</th>
   <td>${equipment.date} </td>
   </tr>
   <tr>
   <th>	维护费用</th>
   <td>${equipment.maintenanceCosts} </td>
   </tr>
   <tr>
   <th>	设备功能</th>
   <td>${equipment.function}</td>
   </tr>
   <tr>
   <th>	对外租赁</th>
   <td>${equipment.foreignLease!=null&&equipment.foreignLease?"是":"否"}</td>
   </tr>
   <tr>
   <th>	关联项目</th>
   <td>
		<c:forEach items="${equipment.projectList}" var="project">
	 	 ${project.projectName}&nbsp;
	    </c:forEach> 
	</td>
   </tr>
    <tr>
   <th>	备注</th>
   <td>${equipment.remark}</td>
   </tr>
   </tbody>
   </table>
<script type="text/javascript">

   </script>
  </body>
</html>
