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
</ul>
<div>
	<form action="" name="establishmentForm" id="formId"> 
	<div id="page1">
		<input type="hidden" name="id" value="${project.id }"/>
		<input type="text"  class="application-form form2"  id="projectNumber" name="projectNumber" placeholder="项目编号" value="${project.projectNumber }">
		<input type="text"  class="application-form form2"  id="projectName" name="projectName" placeholder="项目名称" value="${project.projectName }">
		<input type="text"  class="application-form form2"  id="projectcost" name="projectcost" placeholder="预计经费" value="${project.projectcost }">
		<input type="text"  class="application-form form2"  id="username" name="user.username"  readonly="true" placeholder="负责人" value="${project.user.username }"> 
  		<input type="hidden" id="userId" name="user.id" value="${project.user.id }"/>
		<input type="button"  class="application-form btn" onclick="selectUser();" value="选择人员">
  		<input type="text"  class="application-form form1"  id="participants" name="participants"  placeholder="参与人员" value="${project.participants }">
		<input type="text"  class="application-form form1"  id="equipmentName" name="equipment.equipmentName" readonly="true" placeholder="相关设备" value="${project.equipment.equipmentName }"> 
		<input type="hidden" id="equipmentId" name="equipment.id" value="${project.equipment.id }"/>
		<input type="button"  class="application-form btn" onclick="selectEquipment();" value="选择设备">
		<input type="text"  class="application-form form2"  id="time" name="time" placeholder="计划完成时间" value="${project.time }"> 
  		<input type="text"  class="application-form form1"  id="unit" name="unit"  placeholder="单位" value="${project.unit }">
  		<input type="text"  class="application-form form1"  id="subsidiary" name="subsidiary"  placeholder="子公司" value="${project.subsidiary }">
  		<input type="text"  class="application-form form4"  id="project_Establishment.relyingUnit" name="project_Establishment.relyingUnit"  placeholder="依托单位情况">
  		<textarea  class="application-form form3"  id="background" name="background" placeholder="背景意义">${project.background }</textarea>
	</div>
	<div id="page2" style="display:none;">
		<textarea  class="establishment-form form1"  id="projectContent" name="projectContent" placeholder="项目内容">${project.projectContent }</textarea>
		<textarea  class="establishment-form form1"  id="technicalField" name="technicalField" placeholder="项目技术领域">${project.technicalField }</textarea>
	</div>
	<div id="page3" style="display:none;">
	<textarea  class="establishment-form form5"  id="project_Establishment.currentSituation" name=" project_Establishment.currentSituation" placeholder="国内外现状"></textarea>
	</div>
	<div id="page4" style="display:none;">
	<textarea  class="application-form form5"  id="project_Establishment.technicalRoute" name="project_Establishment.technicalRoute" placeholder="技术路线"></textarea>
	</div>
	<div id="page5" style="display:none;">
		<textarea  class="establishment-form form1"  id="projectPlan" name="projectPlan" placeholder="项目安排">${project.projectPlan }</textarea>
		<textarea  class="establishment-form form1"  id="achievement" name="achievement" placeholder="预期成果">${project.achievement }</textarea>
	</div>
	<div id="page6" style="display:none;">
		<textarea  class="application-form form5"  id="project_Establishment.workFoundation" name="project_Establishment.workFoundation" placeholder="现有工作基础、特色和优势"></textarea>
	</div>
	<div id="page7" style="display:none;">
		<textarea  class="application-form form6"  id="project_Establishment.target" name="project_Establishment.target" placeholder="预期目标：预计产业化目标，预计技术质量指标，预期知识产权（专利申请和授权），预期制定技术标准，预期经济效益，预期社会效益，预期产业化工作，预期转化效益"></textarea>
		<div class="word_uploaderDiv" uploaderName="projectFile" style="float:left;">
			    <div id="projectFile_Picker" style="float:left;">选择文档</div>
			    <div id="projectFile_List" class="uploader-list" style="float:left;"></div>
			</div><br/><br/>
		<input type="button" id="save" class="application-form btn2"   value="保存" >
		<input type="button" id="submit" class="application-form btn2"   value="提交" ></div>
</form>
</div>

<script>
var uploader;//上传文件控件的实例
 var status=<%=CodeBookConsts.STATUS_SAVE%>;
function submit(){
//添加验证信息
$("#formId").ajaxSubmit({
	url:'${base}/ord/addEstablishmentProject?status='+status,
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
		addEstablishmentProject();
	});
	$("#submit").click(function(){
		status=<%=CodeBookConsts.STATUS_SUBMIT%>
		addEstablishmentProject();
	});
});
function addEstablishmentProject(){
	if(($("#projectFile_List").html()=='')){
		submit();
	}
	uploader.upload();
  }
function goBack()
{
 	//销毁文档uploader
	var uploaderName = $(".word_uploaderDiv").attr("uploaderName");
	word_destroyUploader(uploader, uploaderName+"_List");
	//关闭窗口
	var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
    parent.layer.close(index); //执行关闭
}
function changePage(pageId){
	$("#"+pageId).parent().children().hide()
	$("#"+pageId).show();
}
function selectUser(){
	layer.open({
  	  type: 2,
	  title: '添加用户',
	  shadeClose: true,
	  shade: 0.8,
	  area: ['380px', '90%'],
	  content: '${base}/user/toSelectUser',
  });
}
function selectEquipment(){
	layer.open({
  	  type: 2,
	  title: '添加用户',
	  shadeClose: true,
	  shade: 0.8,
	  area: ['380px', '90%'],
	  content: '${base}/user/toSelectEquipment',
  });
}
</script>