<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/inc/defaultInc.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addProject.jsp' starting page</title>
 
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
   <table width="89%">
<tr>
<td style="text-align:center;">
<h3>添加项目</h3>
</td>
</tr>
<tr>
<td style="text-align:center;">
<form action="" name="projectForm" id="formId">
项目编号<input type="text" id="projectnumber" name="projectnumber"/><br/><br/> 
项目名称<input type="text" id="projectname" name="projectname"/><br/><br/>
负责人<input type="text" id="projectleader" name="projectleader"/><br/><br/>
项目内容<input type="text" id="projectcontent" name="projectcontent"/><br/><br/>
项目费用<input type="text" id="projectcost" name="projectcost"/><br/><br/>
其它<input type="text" id="other" name="other"/><br/><br/>
 <input type="button" value="添加" onclick="addProject();"/>
<input type="button" value="返回" onclick="goBack();"/>
</form>
</td>
</tr>
</table>
  </body>
  <script type="text/javascript">
 /*  $(function(){
  	$("#projectname").click(function(){
  		alert("sdfs");
  	});
  }); */
  
  function addProject()
  {
//添加验证信息
$("#formId").ajaxSubmit({
	url:'project/addProject',
	type:'post',
	success:function(data){
		if(data){
			goBack();
		}else{
			layer.alert("保存失败",{icon:2,title:'出错'});
		}
	}
});
}

function goBack()
{
	var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
    parent.layer.close(index); //执行关闭
}
  </script>
</html>

