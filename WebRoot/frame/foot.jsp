<%@ page language="java" pageEncoding="utf-8"%>

<div class="container footer">
    <p class="footer-nav">
      <a href="${base}/index#about">关于我们</a><span class="split">|</span>
      <a href="${base}/contactUs">联系我们</a>
    </p>
    <div class="row">
	    <ul class="footer-icon col-md-7">
	      <li data-toggle="popover"><img src="${base}/resources/images/weixin.png" id="wx_qrcode">&nbsp;微信
	        <div class="hidden popover-content">
	          <img src="${base}/resources/images/weixin_LHD.png">
	          <p>扫一扫，关注公众号</p>
	        </div>
	      </li>
	      <li data-toggle="popover"><img src="${base}/resources/images/ios.png" id="ios_qrcode">&nbsp;Ios
	        <div class="hidden popover-content">
	          <p>尽请期待~</p>
	        </div>
	      </li>
	    </ul>
	    <div class="footer-tel col-md-5">
	      <p><i class="fa fa-headphones"></i>151XXXXXXXX</p>
	      <p>周一至周日9:00-18:00</p>
	    </div>
    </div>
</div>
<div class="copyright">
  © 2017 李慧丹 版权所有 中国矿业大学计算机科学与技术2013-1
</div>
<script>
$(function(){
	$(".footer").prev().css("min-height","80%");
});
</script>