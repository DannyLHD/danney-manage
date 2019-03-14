<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/inc/defaultInc.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<link rel="stylesheet" type="text/css" href="${base }/resources/css/applicationForm.css">
	<div>
 	    <input type="text"  class="application-form form1"  id="projectName" name="projectName" value="项目名称：${project.projectName }">
  		<input type="text"  class="application-form form2"  id="projectcost" name="projectcost" value="预计经费：${project.projectcost }">
		<input type="text"  class="application-form form1"  id="username" name="user.username"  readonly="true" value="负责人：${project.user.username }"> 
 		<input type="text"  class="application-form form1"  id="participants" name="participants"  value="参与人员：${project.participants }">
		<input type="text"  class="application-form form2"  id="time" name="time" value="计划完成时间：${project.time }"> 
		<input type="text"  class="application-form form1"  id="equipmentName" name="equipment.equipmentName" readonly="true" value="相关设备：${project.equipment.equipmentName }"> 
		<c:if test="${project.projectFile!=null && project.projectFile!=''}">
	   		<a href="javascript:location.href='${shareupload}${infomationDir}/${project.projectFile}';">
				附件：${fn:split(project.projectFile, fileNameSeparator)[0]}
				<i class="fa fa-download"></i>
		   	</a>
   		</c:if>
		<textarea  class="application-form form3"  id="background" name="background">背景意义：${project.background }</textarea>
		<textarea  class="application-form form5"  id="projectContent" name="projectContent" >项目内容：${project.projectContent }</textarea>
		<textarea  class="application-form form5"  id="achievement" name="achievement" >预期成果：${project.achievement }</textarea>
		<textarea  class="application-form form5"  id="technicalField" name="technicalField">技术领域：${project.technicalField }</textarea>
		<textarea  class="application-form form6"  id="projectPlan" name="projectPlan" >项目安排：${project.projectPlan }</textarea>
		
</div>
