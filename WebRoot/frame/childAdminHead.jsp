<%@ page language="java" pageEncoding="utf-8"%>
   <div style="float: right" class="navbar-header">
	  <div class="navbar-brand" >欢迎您，子公司管理员：${username} &nbsp;&nbsp;&nbsp;<a href="${base}/login/logout">退出登录</a></div>
	</div>
	<div class="row clearfix">
		<div class="col-md-12 column">
			<nav class="navbar navbar-default" role="navigation">
				<div  class="navbar-header">
					  <a class="navbar-brand" href="${base}/child/childIndex">首页</a>
				</div>
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
					    <li >
							 <a href="${base}/child/getTecInfManage">科技消息管理</a>
						</li>				
						<li >
							 <a href="${base}/child/getEquipmentManage">科研设备管理</a>
						</li>
						<li >
							 <a href="${base}/child/getProjectManage">科研项目管理</a>
						</li>					
						<li >
							 <a href="${base}/child/getAchievementManage">知识产权管理</a>
						</li>
						<li >
							 <a href="${base}/child/getUserManage">人员信息管理</a>
						</li>
						<li >
							 <a href="${base}/child/getSelfMesManage">个人信息管理</a>
						</li>
					</ul>
				</div>
			</nav>
	</div>
</div>