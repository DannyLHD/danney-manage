package com.kj.controller.user;

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

import com.kj.domain.User;
import com.kj.domain.UserRole;
import com.kj.service.IUserService;
import com.kj.util.CodeBookConsts;
import com.kj.util.Consts;
import com.kj.util.SmsUtil;
import com.system.util.EndecryptUtils;
import com.system.util.RandCodeGenerator;
import com.system.vo.MsgReturn;


/**
 * 登录控制层
 * @author administrator
 *
 */
@Controller
@RequestMapping("/child")
public class ChildAminController {
	@Autowired private IUserService userService;
	@Value("#{envProperties['smsCodeMsg']}") private String smsCodeMsg;
	/**
	 *普通用户登录首页
	 * @param model
	 * @return
	 */
	@RequestMapping("/childIndex")
	public String getOrdIndex(Model model){
		return "/user/childAdmin/childIndex";
	}
	@RequestMapping("/getTecInfManage")
	public String getTecInfManage(Model model){
		return "/user/childAdmin/tecInfManage";
	}
	@RequestMapping("/getEquipmentManage")
	public String getEquipmentManage(Model model){
		return "/user/childAdmin/equipmentManage";
	}
	@RequestMapping("/getProjectManage")
	public String getProjectManage(Model model){
		return "/user/childAdmin/projectManage";
	}
	@RequestMapping("/getUserManage")
	public String getUserManage(Model model){
		return "/user/childAdmin/userManage";
	}
	@RequestMapping("/getSelfMesManage")
	public String getSelfMesManage(Model model){
		return "/user/childAdmin/selfMesManage";
	}
	/**
	 * 知识产权管理
	 * @param model
	 * @return
	 */
	@RequestMapping("/getAchievementManage")
	public String getAchievementManage(Model model){
		return "/user/childAdmin/achievementManage";
	}
}
