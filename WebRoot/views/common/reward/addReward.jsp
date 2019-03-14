<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/inc/defaultInc.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addResult.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<!--web uploader-->
	<link rel="stylesheet" type="text/css" href="${base }/resources/plugins/webuploader/webuploader.css">
	<script type="text/javascript" src="${base }/resources/plugins/webuploader/webuploader.js"></script>
	<script type="text/javascript" src="${base }/resources/plugins/webuploader/kj-manage/wordUpload.js"></script>
	<!--web uploader-->

  </head>
  
   <body>
   	<table width="89%">
		<tr>
		<td style="text-align:center;">
		<h3>添加</h3>
		</td>
		</tr>
		<tr>
		<td style="text-align:center;">
		<form action="" name="rewardForm" id="formId"> 
			申请级别<input type="text" id="rank" name="rank" /><br/><br/>
			项目名称<input type="text" id="projectName" name="project.projectName"/><br/><br/>
			<input type="hidden" id="projectId" name="project.id"/>
			<a href="javascript:selectProject();">选择项目</a><br/><br/> 
			完成单位<input type="text" id="unit" name="unit" /><br/><br/>
			等级<input type="text" id="grade" name="grade" /><br/><br/>
			申请人<input type="text" id="username" name="user.username"/><br/><br/>
			<input type="hidden" id="userId" name="user.id"/>
			<a href="javascript:selectUser();">选择人员</a><br/><br/> 
			相关人员<input type="text" id="participants" name="participants" /><br/><br/>
			存放处<input type="text" id="storage" name="storage" /><br/><br/>
		 	<input type="button" value="保存" onclick="saveReward();"/>
		 	<input type="button" value="提交" onclick="submitReward();"/>
			<input type="button" value="返回" onclick="goBack();"/>
		</form>
		</td>
		</tr>
	</table>
  </body>
  <script type="text/javascript">
  
function saveReward(){
//添加验证信息
var status=<%=CodeBookConsts.STATUS_SAVE%>;
$("#formId").ajaxSubmit({
	url:'ord/addReward?status='+status,
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
function submitReward() {
//添加验证信息
var status=<%=CodeBookConsts.STATUS_SUBMIT%>;
$("#formId").ajaxSubmit({
	url:'ord/addReward?status='+status,
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
  
function goBack()
{
	//关闭窗口
	var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
    parent.layer.close(index); //执行关闭
}
function selectUser()
  {
  layer.open({
  	  type: 2,
	  title: '添加用户',
	  shadeClose: true,
	  shade: 0.8,
	  area: ['380px', '90%'],
	  content: '${base}/user/toSelectUser',
  });
  }
  function selectProject()
  {
  layer.open({
  	  type: 2,
	  title: '添加用户',
	  shadeClose: true,
	  shade: 0.8,
	  area: ['380px', '90%'],
	  content: '${base}/user/toSelectProject',
  });
  }
  </script>
</html>
