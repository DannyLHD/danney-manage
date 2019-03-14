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
		<h3>添加设备</h3>
		</td>
		</tr>
		<tr>
		<td style="text-align:center;">
		<form action="" name="informationForm" id="formId"> 
			设备编号<input type="text" id="equipmentID" name="equipmentID"/><br/><br/>
			设备名称<input type="text" id="equipmentName" name="equipmentName"/><br/><br/>
			子公司<input type="text" id="subsidiary" name="subsidiary"/><br/><br/>
			单位<input type="text" id="unit" name="unit"/><br/><br/>
			维护人员<input type="text" id="username" name="user.username" readonly="readonly"/><br/><br/>
			<input type="hidden" id="userId" name="user.id"/>
			<a href="javascript:selectUser();">选择人员</a><br/><br/> 
			设备价格<input type="text" id="price" name="price"/><br/><br/>
			购买时间<input type="text" id="date" name="date"/><br/><br/>
			维护费用<input type="text" id="maintenanceCosts" name="maintenanceCosts"/><br/><br/>
			设备功能<input type="text" id="function" name="function"/><br/><br/>
			对外租赁:&nbsp;&nbsp;
			是<input type="radio" id="foreignLease" name="foreignLease" value="true"/>
			否<input type="radio" id="foreignLease" name="foreignLease" value="false"/><br/><br/><br/><br/>
			<!-- 关联项目<input type="text" id="projectList" name="projectList"/><br/><br/> -->
			备注<input type="text" id="remark" name="remark"/><br/><br/>
		 	<input type="button" value="添加" onclick="addEquipment();"/>
			<input type="button" value="返回" onclick="goBack();"/>
		</form>
		</td>
		</tr>
	</table>
  </body>
  <script type="text/javascript">
  function addEquipment()
  {
//添加验证信息
$("#formId").ajaxSubmit({
	url:'admin/addEquipment',
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
  </script>
</html>
