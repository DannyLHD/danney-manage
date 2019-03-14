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
			<input type="text" id="paperName" name="paperName" placeholder="论文名"><br/><br/>
			<input type="text" id="author" name="author" placeholder="作者"><br/><br/>
			<input type="text" id="unit" name="unit" placeholder="单位"><br/><br/>
		 	<input type="text" id="publishingCompany" name="publishingCompany" placeholder="出版社"><br/><br/>
		 	<input type="text" id="publicationNo" name="publicationNo" placeholder="出版号"><br/><br/>
		 	<input type="text" id="retrieve" name="retrieve" placeholder="检索"><br/><br/>
		
		 	<input type="button" value="添加" onclick="addPaper();"/>
			<input type="button" value="返回" onclick="goBack();"/>
		</form>
		</td>
		</tr>
	</table>
  </body>
  <script type="text/javascript">
  
  
  function addPaper()
  {
//添加验证信息
$("#formId").ajaxSubmit({
	url:'ord/addPaper',
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
