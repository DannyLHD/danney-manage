<%@ page language="java" pageEncoding="utf-8"%>
  
 <div style="float: right" class="navbar-header">
	  <div class="navbar-brand" >欢迎您：${username} &nbsp;&nbsp;&nbsp;<a href="${base}/login/index">退出登录</a></div>
	</div>
  
	<div class="row clearfix">
		<div class="col-md-12 column">
			<nav class="navbar navbar-default" role="navigation">
				<div  class="navbar-header">
					  <a class="navbar-brand" href="${base}/ord/ordIndex">首页</a>
				</div>
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
					    <li >
							 <a href="${base}/ord/getTecInfManage">科技动态</a>
						</li>				
						<li >
							 <a href="${base}/ord/getEquipment">科研设备</a>
						</li>
						<li >
							 <a href="${base}/ord/getProjectManage">科研项目</a>
						</li>					
						<li >
							 <a href="${base}/ord/getAchievementManage"> 知识产权</a>
						</li>
						<li >
							 <a href="${base}/ord/getUser">人员信息</a>
						</li>
						<li >
							 <a href="${base}/ord/getSelfMesManage">个人信息管理</a>
						</li>
					</ul>
				</div>
			</nav>
		</div>
</div>
</html>
