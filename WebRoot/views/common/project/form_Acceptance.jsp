<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/inc/defaultInc.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<script type="text/javascript" src="${base }/resources/plugins/webuploader/webuploader.js"></script>
	<script type="text/javascript" src="${base }/resources/plugins/webuploader/kj-manage/wordUpload.js"></script>
	<link rel="stylesheet" type="text/css" href="${base }/resources/plugins/webuploader/webuploader.css">
	<link rel="stylesheet" type="text/css" href="${base }/resources/css/applicationForm.css">
<ul class="nav nav-tabs">
  <li><a href="javascript:changePage('page1')">第一页</a></li>
  <li><a href="javascript:changePage('page2')">第二页</a></li>
  <li><a href="javascript:changePage('page3')">第三页</a></li>
  <li><a href="javascript:changePage('page4')">第四页</a></li>
  <li><a href="javascript:changePage('page5')">第五页</a></li>
  <li><a href="javascript:changePage('page6')">第六页</a></li>
  <li><a href="javascript:changePage('page7')">第七页</a></li>
  <li><a href="javascript:changePage('page8')">第八页</a></li>
</ul>
<div>
	<form action="" name="acceptanceForm" id="formId"> 
	<div id="page1">
		<input type="hidden" name="id" value="${project.id }"/>
		<input type="text"  class="application-form form2"  id="projectNumber" name="projectNumber" placeholder="项目编号" value="${project.projectNumber }">
		<input type="text"  class="application-form form1"  id="projectName" name="projectName" placeholder="项目名称" value="${project.projectName }">
		<input type="text"  class="application-form form2"  id="projectcost" name="projectcost" placeholder="预计经费" value="${project.projectcost }">
		<input type="text"  class="application-form form2"  id="username" name="user.username"  readonly="true" placeholder="负责人" value="${project.user.username }"> 
  		<input type="hidden" id="userId" name="user.id"/>
		<input type="button"  class="application-form btn" onclick="selectUser();" value="选择人员">
  		<input type="text"  class="application-form form1"  id="participants" name="participants"  placeholder="参与人员" value="${project.participants }">
		<input type="text"  class="application-form form1"  id="equipmentName" name="equipment.equipmentName" readonly="true" placeholder="相关设备" value="${project.equipment.equipmentName }"> 
		<input type="hidden" id="equipmentId" name="equipment.id" value="${project.equipment.id }"/>
		<input type="button"  class="application-form btn" onclick="selectEquipment();" value="选择设备">
		<input type="text"  class="application-form form2"  id="time" name="time" placeholder="计划完成时间" value="${project.time }"> 
		<input type="text"  class="application-form form2"  id="project_Acceptance.completionTime" name="project_Acceptance.completionTime" placeholder="项目完成时间">
  		<input type="text"  class="application-form form1"  id="unit" name="unit"  placeholder="单位" value="${project.unit }">
  		<input type="text"  class="application-form form1"  id="subsidiary" name="subsidiary"  placeholder="子公司" value="${project.subsidiary }">
  		<textarea  class="application-form form3"  id="background" name="background" placeholder="背景意义" >${project.background }</textarea>
	</div>
	<div id="page2" style="display:none;">
		<textarea  class="application-form form5"  id="projectContent" name="projectContent" placeholder="项目内容">${project.projectContent }</textarea>
	</div>
	<div id="page3" style="display:none;">
		<textarea  class="application-form form3"  id="achievement" name="achievement" placeholder="预期成果">${project.achievement }</textarea>
		<textarea  class="application-form form3"  id="technicalField" name="technicalField" placeholder="技术领域">${project.technicalField }</textarea>
	</div>
	<div id="page4" style="display:none;">
		<textarea  class="application-form form3"  id="projectPlan" name="projectPlan" placeholder="项目安排">${project.projectPlan }</textarea>
	</div>
	<div id="page5" style="display:none;">
		<textarea  class="application-form form5"  id="project_Acceptance.implementation" name="project_Acceptance.implementation" placeholder="项目实施情况概述"></textarea>
	</div>
	<div id="page6" style="display:none;">
		
		<textarea  class="application-form form3"  id="project_Acceptance.performance" name="project_Acceptance.performance" placeholder="项目绩效情况。合同约定和实际完成情况"></textarea>
	</div>
	<div id="page7" style="display:none;">
		<textarea  class="application-form form5"  id="project_Acceptance.budgetSituation" name="project_Acceptance.budgetSituation" placeholder="项目经费预算情况"></textarea>
	</div>
	<div id="page8" style="display:none;">
		<textarea  class="application-form form3"  id="project_Acceptance.economicBenefits" name="project_Acceptance.economicBenefits" placeholder="项目经济效益和社会效益，获得政府支持或税收减免补助资金"></textarea>
		<div class="word_uploaderDiv" uploaderName="projectFile" style="float:left;">
			    <div id="projectFile_Picker" style="float:left;">选择文档</div>
			    <div id="projectFile_List" class="uploader-list" style="float:left;"></div>
			</div> <br/><br/>
		<input type="button" id="save" class="application-form btn2"   value="保存" >
		<input type="button" id="submit" class="application-form btn2"   value="提交" >
	</div>
	</form>
</div>

<script>
 var uploader;//上传文件控件的实例
 var status=<%=CodeBookConsts.STATUS_SAVE%>;
function submit(){
//添加验证信息
$("#formId").ajaxSubmit({
	url:'${base}/ord/addAcceptanceProject?status='+status,
	type:'post',
	success:function(data){
		if(data){
			layer.msg("保存成功",{time:1000,end:function(){
				goBack();
			}});			
		}else{
			layer.alert("保存失败",{icon:2,title:'提示'});
		}
	}
});
}
 $(function(){
	//初始化uploader
	var uploaderName = $(".word_uploaderDiv").attr("uploaderName");
	uploader = word_createUploader('/kj-manage/admin/fileUpload.do', 
		uploaderName, uploaderName+"_List", uploaderName+"_Picker",submit);
	$("#save").click(function(){
		status=<%=CodeBookConsts.STATUS_SAVE%>
		addAcceptanceProject();
	});
	$("#submit").click(function(){
		status=<%=CodeBookConsts.STATUS_SUBMIT%>
		addAcceptanceProject();
	});
  });
function goBack()
{
 	//销毁文档uploader
	var uploaderName = $(".word_uploaderDiv").attr("uploaderName");
	word_destroyUploader(uploader, uploaderName+"_List");
	//关闭窗口
	var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
    parent.layer.close(index); //执行关闭
}
  function addAcceptanceProject(){
	if(($("#projectFile_List").html()=='')){
		submit();
	}
	uploader.upload();
  }
  
function changePage(pageId){
	$("#"+pageId).parent().children().hide()
	$("#"+pageId).show();
}
function selectUser(){
	layer.open({
  	  type: 2,
	  title: '添加',
	  shadeClose: true,
	  shade: 0.8,
	  area: ['380px', '90%'],
	  content: '${base}/user/toSelectUser',
  });
}
function selectEquipment(){
	layer.open({
  	  type: 2,
	  title: '添加',
	  shadeClose: true,
	  shade: 0.8,
	  area: ['380px', '90%'],
	  content: '${base}/user/toSelectEquipment',
  });
}
</script>