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
			<input type="text" id="schemeNo" name="schemeNo" placeholder="计划编号"><br/><br/>
			<input type="text" id="releaseNo" name="releaseNo" placeholder="发布编号"><br/><br/>
			<input type="text" id="standardName" name="standardName" placeholder="标准名称"><br/><br/>
		 	<input type="text" id="formulation" name="formulation" placeholder="制定/参与制定"><br/><br/>
		 	<input type="text" id="type" name="type" placeholder="标准类型"><br/><br/>
		 	<input type="text" id="manageOrganization" name="manageOrganization" placeholder="管理机构"><br/><br/>
		 	<input type="text" id="draftingUnit" name="draftingUnit" placeholder="起草单位"><br/><br/>
		 	<input type="text" id="writers" name="writers" placeholder="编写人员"><br/><br/>
		 	<textarea id="releaseTime" name="releaseTime" placeholder="发布时间"></textarea><br/><br/>
		 	
		 	<input type="button" value="添加" onclick="addStandard();"/>
			<input type="button" value="返回" onclick="goBack();"/>
		</form>
		</td>
		</tr>
	</table>
  </body>
  <script type="text/javascript">
  
  
  function addStandard()
  {
//添加验证信息
$("#formId").ajaxSubmit({
	url:'ord/addStandard',
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
