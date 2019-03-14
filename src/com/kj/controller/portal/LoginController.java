package com.kj.controller.portal;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kj.domain.TecInformation;
import com.kj.domain.User;
import com.kj.domain.UserRole;
import com.kj.service.ITecInfService;
import com.kj.service.IUserService;
import com.kj.util.CodeBookConsts;
import com.kj.util.Consts;
import com.kj.util.SmsUtil;
import com.system.util.EndecryptUtils;
import com.system.util.RandCodeGenerator;
import com.system.vo.MsgReturn;
import com.system.vo.PageInfo;


/**
 * 登录控制层
 * @author administrator
 *
 */
@Controller
@RequestMapping("/login")
public class LoginController {
	@Resource private ITecInfService tecInfService;
	@Autowired private IUserService userService;
	@Value("#{envProperties['smsCodeMsg']}") private String smsCodeMsg;
	
	/**
	 * 登录页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/login")
	public String getLogin(Model model){
		return "/login";
	}	
	/**
	 * 网站首页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/index")
	public String getIndex(Model model){
		return "/index";
	}	
	/**
	 * 注册页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/register")
	public String getRegister(Model model){
		return "/register";
	}
	/**
	 * 忘记密码页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/forgetPwd")
	public String getForgetPwd(Model model){
		return "/common/login/forgetPwd";
	}	
    /**
     * 登出
     * @param model
     * @return
     */
	@RequestMapping("/logout")
	public String getLogout(){
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();
		return "redirect:/login/index";
	}
    /**
     * 未经授权
     * @param model
     * @return
     */
	@RequestMapping("/unauthorized")
	public String getUnauthorized(){
		return "/error/unauthorized";
	}
	
	/**
	 * 进行登录
	 * @param request
	 * @param rememberMe
	 * @param model
	 * @return
	 */
	@RequestMapping("login.do")
	public String doLogin(HttpServletRequest request, Model model, Boolean toIndex){
		String phone = request.getParameter("phone");  
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("rememberMe");
        if(phone==null ||phone.isEmpty() || password==null || password.isEmpty()){
            model.addAttribute("errorMsg", "请填写用户名和密码");
            model.addAttribute("error", Consts.MSG_FAILURE);
            return "redirect:/login";
        }        
        UsernamePasswordToken token = new UsernamePasswordToken(phone, password);
       //获取当前的Subject  
        Subject currentUser = SecurityUtils.getSubject();
        try {
            //在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查  
            //每个Realm都能在必要时对提交的AuthenticationTokens作出反应  
            //所以这一步在调用login(token)方法时,它会走到ShiroRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法  
            currentUser.login(token);
			User user = userService.doGetUserByPhone(phone);
			if(user.getRole()!=null){
	     		SavedRequest savedRequest = (SavedRequest)SecurityUtils.getSubject().getSession().getAttribute("shiroSavedRequest");
	     		String url ="";
	     		if((toIndex==null||!toIndex) && savedRequest!=null){
	     			url = savedRequest.getRequestUrl().substring(savedRequest.getRequestUrl().indexOf("/",2));
	     		}else{
	     			int authority=user.getRole().getAuthority();
	     			if(authority == 0){
	     				url="/ord/ordIndex";
	     			}
	     			else if(authority==1){
	     				url="/unit/unitIndex";	
	     			}
	     			else if(authority==2){
	     				url="/child/childIndex";	
	     			}
	     			else if(authority==3){
	     				url="/head/headIndex";	
	     			}
	     			else if(authority==4){
	     				url="/super/superIndex";	
	     			}
	     		}
	     		model.addAttribute("url", url);
	     		return "/loginSuccess";
	     	}
        }catch(UnknownAccountException uae){  
            //验证未通过,未知账户
            model.addAttribute("errorMsg", "用户名或密码错误");
        }catch(IncorrectCredentialsException ice){  
            //验证未通过,错误的凭证
            model.addAttribute("errorMsg", "用户名或密码错误");
        }catch(LockedAccountException lae){  
            //验证未通过,账户已锁定
            model.addAttribute("errorMsg", "用户已被锁定");
        }catch(ExcessiveAttemptsException eae){  
            //验证未通过,错误次数过多
            model.addAttribute("errorMsg", "错误次数过多,请稍后重试");
        }catch(AuthenticationException ae){  
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景  
            model.addAttribute("errorMsg", "用户名或密码错误");
        }catch (Exception e) {
			//获取用户失败
            model.addAttribute("errorMsg", "用户名或密码错误");
		}
     model.addAttribute("error", Consts.MSG_FAILURE);
     return "redirect:/login/login";
	}
	
	/**
	 * 进行注册
	 * @param model
	 * @return
	 */
	@RequestMapping("/register.do")
	@ResponseBody
	public MsgReturn doRegister(@ModelAttribute("domain") User user, Model model){
		MsgReturn msgReturn = new MsgReturn();
		String phone=user.getPhone();
		DetachedCriteria dc = DetachedCriteria.forClass(User.class);
		List<User> userList = null;
		try {
			userList = userService.doGetFilterList(dc);
		} catch (Exception e1) {
			msgReturn.setStatus(Consts.MSG_FAILURE);
			msgReturn.setMessage("注册失败");
		}
		for(User user2:userList){
			if (user2.getPhone().equals(user.getPhone())) {// 如果该手机号已被注册,则返回
				msgReturn.setStatus(Consts.MSG_FAILURE);
				msgReturn.setMessage("该手机号已被注册");
				return msgReturn;
			} 
		}
		user.setId(null);
		user.setPassword(EndecryptUtils.md5(user.getPassword()));
		UserRole role = new UserRole();
		role.setAuthority(CodeBookConsts.ROLE_AUTH_SUPER_ADMIN);
		user.setRole(role);
		try {
			if(userService.doSave(user)){
				msgReturn.setStatus(Consts.MSG_SUCCESS);
			}else{
				msgReturn.setStatus(Consts.MSG_FAILURE);
				msgReturn.setMessage("注册失败");
			}
		} catch (Exception e) {
			msgReturn.setStatus(Consts.MSG_FAILURE);
			msgReturn.setMessage("注册失败");
		}
		return msgReturn;
	}	
	
	
	/**
	 * 进行密码找回
	 * @param model
	 * @return
	 */
	@RequestMapping("/forgetPwd.do")
	@ResponseBody
	public MsgReturn doForgetPwd(@Valid @ModelAttribute("domain") User domain,String smsCode, HttpSession session, BindingResult result, Model model){
		MsgReturn msgReturn = new MsgReturn();
		if (result.hasErrors() || domain.getUsername()==null || domain.getPassword()==null || domain.getUsername().isEmpty() || domain.getPassword().isEmpty()) {// 如果校验失败,则返回
			msgReturn.setStatus(Consts.MSG_FAILURE);
			msgReturn.setMessage("校验失败");
		} else {
			try {
				User olduserDomain = userService.doGetUserByPhone(domain.getPhone());
				if(olduserDomain==null || olduserDomain.getId()==null){
					msgReturn.setStatus(Consts.MSG_FAILURE);
					msgReturn.setMessage("用户不存在");
					return msgReturn;
				}
				//获取cookie中的验证码
				String code = (String)session.getAttribute("gh.forgetPwd.smsCode");
				if(code==null || code.isEmpty()){
					msgReturn.setStatus(Consts.MSG_FAILURE);
					msgReturn.setMessage("验证码已过期，请重新发送");
					return msgReturn;
				}
				if(smsCode==null || !code.equals(smsCode.toUpperCase())){
					msgReturn.setStatus(Consts.MSG_FAILURE);
					msgReturn.setMessage("验证码错误");
					return msgReturn;
				}
				olduserDomain.setPassword(EndecryptUtils.md5(domain.getPassword()));
				if(userService.doSave(olduserDomain)){
					msgReturn.setStatus(Consts.MSG_SUCCESS);
				}else{
					msgReturn.setStatus(Consts.MSG_FAILURE);
					msgReturn.setMessage("修改密码失败");
				}
			} catch (Exception e) {
				msgReturn.setStatus(Consts.MSG_FAILURE);
				msgReturn.setMessage("修改密码失败");
			}
		}
		return msgReturn;
	}	
	
	@RequestMapping("/checkUsername/{username}")
	@ResponseBody
	public boolean checkUsername(@PathVariable String username){
		DetachedCriteria dc = DetachedCriteria.forClass(User.class);
		dc.add(Restrictions.eq("username", username));
		return userService.doGetTotalCount(dc)<=0;
	}
	
	@RequestMapping("smsCode/{mobile}")
	@ResponseBody
	public String doSmsCode(@PathVariable String mobile, String serviceType, HttpServletRequest request, HttpServletResponse response, HttpSession session){
		String servNameCh = "";
		String servNameEn = "";
		if("1".equals(serviceType)){
			servNameCh = "注册";
			servNameEn = "register";
		}else if("2".equals(serviceType)){
			servNameCh = "重置密码";
			servNameEn = "forgetPwd";
		}else if("3".equals(serviceType)){
			servNameCh = "修改手机号";
			servNameEn = "editPhone";
		}
		//获取cookie中的验证码
		String code = "";
		Cookie[] cookie = request.getCookies();
		for (int i = 0; i < cookie.length; i++) {
			Cookie cook = cookie[i];
			if(cook.getName().equals("gh."+servNameEn+".smsCode")){ //获取键 
				code = cook.getValue();
				break;
			}
		}
		if(code.equals("")){
			code = RandCodeGenerator.generate(4, false);
			if("2".equals(serviceType)){	//找回密码的验证码放在session里
				session.setAttribute("gh."+servNameEn+".smsCode", code);
			}else{
				Cookie codeCookie = new Cookie("gh."+servNameEn+".smsCode", code);
				codeCookie.setMaxAge(15*60);//设置过期时间,单位秒
				response.addCookie(codeCookie);
			}
		}
		//替换内容并发短信
		String newMsg = smsCodeMsg.replaceAll("\\$\\{service\\}", servNameCh);
		newMsg = newMsg.replaceAll("\\$\\{code\\}", code);
		try {
			if(SmsUtil.sendMessage(mobile, newMsg)){
				return code;
			}
		} catch (Exception e) {
		}
		return code;
	}
	@RequestMapping("/getInform")
	public String getInform(HttpServletRequest request, Model model,int type) throws Exception
	{
		//分页
		PageInfo pageInfo = new PageInfo();
		pageInfo.setSizePerPage(5);
		String currentPage=request.getParameter("currentPage");
		if(currentPage!=null && !currentPage.isEmpty()){
			pageInfo.setCurrentPageNo(Integer.parseInt(currentPage));
		}
		//查询条件
		DetachedCriteria dc = DetachedCriteria.forClass(TecInformation.class);
		String title=request.getParameter("inforTitle");
		dc.add(Restrictions.eq("inforType", type));
		if(title!=null && !title.isEmpty()){
			dc.add(Restrictions.like("inforTitle", title));
		}
		dc.addOrder(Order.desc("id"));
		List<TecInformation> tecInfList = tecInfService.doGetPageList(pageInfo, dc);
		model.addAttribute("type", type);
		model.addAttribute("tecInfList", tecInfList);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", pageInfo.getTotalPages());
		return "/common/tecInformation/showList";
	}
}
