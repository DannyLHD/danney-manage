	<%@ page language="java" pageEncoding="utf-8"%>
	<link rel="stylesheet" type="text/css" href="${base}/resources/css/pic.css">
	<div style="width: 10%;float: right" class="navbar-header">
	  <a class="navbar-brand" href="${base}/login/login">登录</a>
	</div>
	<div class="row clearfix">
		<div class="col-md-12 column">
			<nav class="navbar navbar-default" role="navigation">
				<div class="navbar-header" style="border-color: 808080;">
					  <a class="navbar-brand" href="${base}">首页</a>
				</div>
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li class="">
							 <a href="${base}/portal/getPortal_Equipment">科研设备</a>
						</li>
						<li class="">
							 <a href="${base}/portal/getPortal_Project">科研项目</a>
						</li>
						<li class="dropdown">
							 <a href="#" class="dropdown-toggle" data-toggle="dropdown">知识产权<strong class="caret"></strong></a>
							<ul class="dropdown-menu">
								<li>
									 <a href="${base}/portal/getPortal_Patent">专利</a>
								</li>
								<li>
									 <a href="${base}/portal/getPortal_Paper">论文</a>
								</li>
								<li>
									 <a href="${base}/portal/getPortal_Standard">标准</a>
								</li>
								<li>
									 <a href="${base}/portal/getPortal_Reward">科技奖</a>
								</li>
							</ul>
						</li>
						<!-- <li>
							 <a href="#">科研专家</a>
						</li> -->
						
						<li class="dropdown">
							 <a href="#" class="dropdown-toggle" data-toggle="dropdown">相关链接<strong class="caret"></strong></a>
							<ul class="dropdown-menu">
								<li>
									 <a href="#">公司url</a>
								</li>
								<li>
									 <a href="#">其它url</a>
								</li>
							</ul>
						</li>
					</ul>
					<form class="navbar-form navbar-left" role="search">
						<div class="form-group">
							<input type="text" class="form-control" />
						</div> <button type="submit" class="btn btn-default">Submit</button>
					</form>
				</div>
			</nav>
	</div>
</div>
<div class="slide">  
	<ul class="slide-img">  
		<li><img src="${base}/resources/images/3.png"></li>  
		<li><img src="${base}/resources/images/4.png"></li> 
		<li><img src="${base}/resources/images/5.png"></li> 
	</ul>  
	<ul class="slide-number">  </ul>  
	<div class="left btn">&lt;</div>  
	<div class="right btn">&gt;</div>  
</div>  
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
	});  
	</script>