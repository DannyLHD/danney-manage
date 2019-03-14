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
		<h3>修改设备</h3>
		</td>
		</tr>
		<tr>
		<td style="text-align:center;">
		<form action="" name="informationForm" id="formId"> 
		<input type="hidden" name="id" value="${equipment.id }"/>
			设备编号<input type="text" id="equipmentID" name="equipmentID" value="${equipment.equipmentID }"/><br/><br/>
			设备名称<input type="text" id="equipmentName" name="equipmentName" value="${equipment.equipmentName }"/><br/><br/>
			子公司<input type="text" id="subsidiary" name="subsidiary" value="${equipment.subsidiary }"/><br/><br/>
			单位<input type="text" id="unit" name="unit" value="${equipment.unit }"/><br/><br/>
			维护人员<input type="text" id="username" name="user.username" value="${equipment.user.username }"  readonly="readonly"/><br/><br/>
			<input type="hidden" id="userId" name="user.id" value="${equipment.user.id }"/>
			<a href="javascript:selectUser();">选择人员</a><br/><br/> 
			设备价格<input type="text" id="price" name="price" value="${equipment.price }"/><br/><br/>
			购买时间<input type="text" id="date" name="date" value="${equipment.date }"/><br/><br/>
			维护费用<input type="text" id="maintenanceCosts" name="maintenanceCosts" value="${equipment.maintenanceCosts }"/><br/><br/>
			设备功能<input type="text" id="function" name="function" value="${equipment.function }"/><br/><br/>
			对外租赁:&nbsp;&nbsp;
			是<input type="radio" id="foreignLease" name="foreignLease" value="true"/>
			否<input type="radio" id="foreignLease" name="foreignLease" value="false"/><br/><br/><br/><br/>
			<!-- 关联项目<input type="text" id="projectList" name="projectList"/><br/><br/> -->
			备注<input type="text" id="remark" name="remark" value="${equipment.remark }"/><br/><br/>
		 	<input type="button" value="确定" onclick="updateEquipment();"/>
			<input type="button" value="返回" onclick="goBack();"/>
		</form>
		</td>
		</tr>
	</table>
  </body>
  <script type="text/javascript">
  function updateEquipment()
  {
//添加验证信息
$("#formId").ajaxSubmit({
	url:'admin/updateEquipment',
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
