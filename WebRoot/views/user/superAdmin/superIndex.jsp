<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/inc/defaultInc.jsp"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>煤炭企业科技管理信息系统（超级管理员）</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="${base}/resources/css/pic.css">
	<link rel="stylesheet" type="text/css" href="${base }/resources/css/userCenter.css">
  </head>
  
  <body>
    
   <div class="container" style="cursor:  pointer;">
	<%@include file="/frame/superAdminHead.jsp" %>
	<div class="slide">  
		<ul class="slide-img">  
			<li><a href="#"><img src="${base}/resources/images/3.png"></a></li>  
			<li><a href="#"><img src="${base}/resources/images/4.png"></a></li> 
			<li><a href="#"><img src="${base}/resources/images/5.png"></a></li> 
		</ul>  
		<ul class="slide-number">  </ul>  
		<div class="left btn">&lt;</div>  
		<div class="right btn">&gt;</div>  
	</div>  
	
	<div class="row clearfix">
		<div class="col-md-4 column">
			<h2>
				科技通知
			</h2>
			<div id="notice"></div>
		</div>
		<div class="col-md-4 column">
				
		</div>
		<div class="col-md-4 column">
			<h2>
				科研动态
			</h2>
			<div id="news"></div>
		</div>
	</div>
		
	<!-- <div class="row clearfix">
		<div class="col-md-4 column">
			<h2>
				项目申报申请
			</h2>
			<p>
				Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui.
			</p>
			<p>
				 <a class="btn" href="#">More »</a>
			</p>
		</div>
		<div class="col-md-4 column">
			<h2>
				相关图片
			</h2>
			<p>
				动态展示图片
			</p>
		</div>
		<div class="col-md-4 column">
			<h2>
				项目立项申请
			</h2>
			<p>
				Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui.
			</p>
			<p>
				 <a class="btn" href="#">More »</a>
			</p>
		</div>
	</div> -->
	</div>
	<%@include file="/frame/foot.jsp" %>


  </body>
  <script type="text/javascript">
 $(function(){  
	//初始化图片个数  
	var size = $(".slide-img li").size();  
	for(var i = 1; i <= size; i++){  
	var li = "<li>" + i + "</li>";  
	$(".slide-number").append(li);  
	}  
	//手动控制轮播  
	$(".slide-number li").eq(0).addClass("active");  
	$(".slide-img li").eq(0).show();  
	$(".slide-number li").mouseover(function(){  
	$(this).addClass("active").siblings().removeClass("active");  
	var index= $(this).index();  
	//如果不加这行，鼠标离开时index不与i相等，i=index保证接着往后走  
	i = index;  
	$(".slide-img li").eq(index).stop().fadeIn(500).siblings().stop().fadeOut(500);  
	});  
	  
	  
	//自动轮播  
	var i = 0;  
	var t = setInterval(animates,2000);  
	//核心代码  
	function animates(){  
	i++;  
	if(i == size){  
	i = 0;  
	}  
	$(".slide-number li").eq(i).addClass("active").siblings().removeClass("active");  
	$(".slide-img li").eq(i).fadeIn(500).siblings().fadeOut(500);  
	}  
	//核心向左  
	function animatesL(){  
	i--;  
	if(i == -1){  
	i = size - 1;  
	}  
	$(".slide-number li").eq(i).addClass("active").siblings().removeClass("active");  
	$(".slide-img li").eq(i).fadeIn(500).siblings().fadeOut(500);  
	}  
	//hover时停止播放图片  
	$(".slide").hover(function(){  
	clearInterval(t);  
	},function(){  
	t = setInterval(animates,5000);  
	});  
	  
	  
	//左右轮播  
	$(".left").click(function(){  
	animatesL();  
	});  
	$(".right").click(function(){  
	animates();  
	});  
	//加载科技消息
	getNews();
	getNotice();
	});  
function getNotice(){
 $.ajax({
		url:'${base}/login/getInform?type='+<%=CodeBookConsts.TYPE_INFORM%>,
		success:function(data){
			$("#notice").html(data);
		}
	});
}
function getNews(){
 $.ajax({
		url:'${base}/login/getInform?type='+<%=CodeBookConsts.TYPE_DYNAMIC%>,
		success:function(data){
			$("#news").html(data);
		}
	});
}
 </script>
</html>
