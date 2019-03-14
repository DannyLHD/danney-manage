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
		<form action="" name="patentForm" id="formId"> 
			<input type="text" id="type" name="type" placeholder="类型"><br/><br/>
			<input type="text" id="patentName" name="patentName" placeholder="专利名称"><br/><br/>
			<input type="text" id="inventor" name="inventor" placeholder="发明人"><br/><br/>
		 	<input type="text" id="patentNo" name="patentNo" placeholder="专利号"><br/><br/>
		 	<input type="text" id="patentee" name="patentee" placeholder="专利权人"><br/><br/>
		 	<input type="text" id="date_Application" name="date_Application" placeholder="申请日"><br/><br/>
		 	<input type="text" id="date_Authorization" name="date_Authorization" placeholder="授权日"><br/><br/>
		 	<input type="text" id="agency" name="agency" placeholder="代理机构"><br/><br/>
		 	<textarea id="benefits" name="benefits" placeholder="效益转化"></textarea><br/><br/>
		 	<textarea id="storage" name="storage" placeholder="存放处"></textarea><br/><br/>
		 	
		 	<input type="button" value="添加" onclick="addPatent();"/>
			<input type="button" value="返回" onclick="goBack();"/>
		</form>
		</td>
		</tr>
	</table>
  </body>
  <script type="text/javascript">
  
  
  function addPatent()
  {
//添加验证信息
$("#formId").ajaxSubmit({
	url:'ord/addPatent',
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
  </script>
</html>
