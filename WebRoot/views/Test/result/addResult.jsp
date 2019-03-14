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

  </head>
  
   <body>
   	<table width="89%">
		<tr>
		<td style="text-align:center;">
		<h3>添加成果</h3>
		</td>
		</tr>
		<tr>
		<td style="text-align:center;">
		<form action="" name="resultForm" id="formId"> 
			成果编号<input type="text" id="resultnumber" name="resultnumber"/><br/><br/>
			成果名称<input type="text" id="resultname" name="resultname"/><br/><br/>
			负责人<input type="text" id="resultleader" name="resultleader"/><br/><br/>
			成果内容<input type="text" id="resultcontent" name="resultcontent"/><br/><br/>
			项目名称<input type="text" id="projectname" name="project.projectname"/>
			<input type="hidden" id="projectId" name="project.id"/>
			<a href="javascript:selectProject();">选择项目</a><br/><br/> 
			其它说明<input type="text" id="other" name="other"/><br/><br/>
		 	<input type="button" value="添加" onclick="addResult();"/>
			<input type="button" value="返回" onclick="goBack();"/>
		</form>
		</td>
		</tr>
	</table>
  </body>
  <script type="text/javascript">
  function selectProject()
  {
  layer.open({
  	  type: 2,
	  title: '添加用户',
	  shadeClose: true,
	  shade: 0.8,
	  area: ['380px', '90%'],
	  content: '${base}/result/toSelectProject',
  });
  }
  function addResult()
  {
//添加验证信息
$("#formId").ajaxSubmit({
	url:'result/addResult',
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
