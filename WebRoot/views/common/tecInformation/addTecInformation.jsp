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
		<h3>添加消息</h3>
		</td>
		</tr>
		<tr>
		<td style="text-align:center;">
		<form action="" name="informationForm" id="formId"> 
			<textarea id="inforTitle" name="inforTitle" placeholder="题目"></textarea><br/><br/>
			<textarea id="inforContent" style="height: 200px;" name="inforContent" placeholder="内容"></textarea><br/><br/>
		 	<!-- uploaderName设置为domain对应的属性名 -->
			<div class="word_uploaderDiv" uploaderName="informationFile" style="float:left;">
			    
			    <div id="informationFile_Picker" style="float:left;">选择文档</div>
			    <div id="informationFile_List" class="uploader-list" style="float:left;"></div>
			</div> <br/><br/>
		 	<input type="button" value="添加" onclick="addTecInf();"/>
			<input type="button" value="返回" onclick="goBack();"/>
		</form>
		</td>
		</tr>
	</table>
  </body>
  <script type="text/javascript">
  var uploader;//上传文件控件的实例
  function addTecInf(){
	if(($("#informationFile_List").html()=='')){
		submit();
	}
  	//uploader.options.formData={param:value,param2:value2}; 
	uploader.upload();
  }
  
  function submit()
  {
//添加验证信息
$("#formId").ajaxSubmit({
	url:'user/addTecInf?type=${TYPE}',
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
	uploader = word_createUploader('/kj-manage/user/fileUpload.do', 
		uploaderName, uploaderName+"_List", uploaderName+"_Picker",submit);
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
  </script>
</html>
