package com.kj.controller.user;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import org.apache.shiro.session.Session;
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

import com.kj.domain.Equipment;
import com.kj.domain.Project;
import com.kj.domain.Project_Application;
import com.kj.domain.Project_Establishment;
import com.kj.domain.TecInformation;
import com.kj.domain.User;
import com.kj.domain.UserRole;
import com.kj.domain.achievement.Paper;
import com.kj.domain.achievement.Patent;
import com.kj.domain.achievement.Reward;
import com.kj.domain.achievement.Standard;
import com.kj.service.IEquipmentService;
import com.kj.service.IPaperService;
import com.kj.service.IPatentService;
import com.kj.service.IProjectService;
import com.kj.service.IProject_AcceptanceService;
import com.kj.service.IProject_ApplicationService;
import com.kj.service.IProject_EstablishmentService;
import com.kj.service.IProject_ExecutionService;
import com.kj.service.IRewardService;
import com.kj.service.IStandardService;
import com.kj.service.IUserService;
import com.kj.util.CodeBookConsts;
import com.kj.util.Consts;
import com.kj.util.SmsUtil;
import com.system.util.EndecryptUtils;
import com.system.util.RandCodeGenerator;
import com.system.util.ValidateUtil;
import com.system.vo.MsgReturn;
import com.system.vo.PageInfo;


/**
 * 登录控制层
 * @author administrator
 *
 */
@Controller
@RequestMapping("/ord")
public class OrdinaryController {
	@Autowired private IUserService userService;
	@Autowired private IEquipmentService equipmentService;
	@Autowired private IProjectService projectService;
	@Autowired private IProject_ApplicationService project_ApplicationService;
	@Autowired private IProject_EstablishmentService project_EstablishmentService;
	@Autowired private IProject_ExecutionService project_ExecutionService;
	@Autowired private IProject_AcceptanceService project_AcceptanceService;
	@Autowired private IPatentService patentService;
	@Autowired private IStandardService standardService;
	@Autowired private IPaperService paperService;
	@Autowired private IRewardService rewardService;
	@Value("#{envProperties['smsCodeMsg']}") private String smsCodeMsg;
	/**
	  * 获取当前用户
	  * @return
	  */
	 private  User getUser(){
	     Subject currentUser = SecurityUtils.getSubject();  
	     if(null != currentUser){
	         Session session = currentUser.getSession();  
	         if(null != session){
	            try {
	         	   User user =  userService.doGetUserByPhone((String)session.getAttribute(Consts.CURRENT_USER));
	         	   if(user==null || ValidateUtil.isEmpty(user.getId())){
	         		   throw new Exception("cannot get user information");
	         	   }
	         	   return user;
	            } catch (Exception e) {
						e.printStackTrace();
						return null;
					}
	         }
	     }
	     return null;
		}
	/**
	 *普通用户登录首页
	 * @param model
	 * @return
	 */
	@RequestMapping("/ordIndex")
	public String getOrdIndex(Model model){
		return "/user/ordinary/ordIndex";
	}
	/**
	 * 科技动态
	 * @param model
	 * @return
	 */
	@RequestMapping("/getTecInfManage")
	public String getTecInfManage(Model model){
		return "/user/ordinary/tecInfManage";
	}
	/**
	 * 科技设备
	 * @param model
	 * @return
	 */
	@RequestMapping("/getEquipment")
	public String getEquipment(Model model){
		return "/user/ordinary/equipmentManage";
	}
	/*------------项目----------------*/
	/*
	获取申报表
	*/
	/**
	 * 科技动态
	 * @param model
	 * @return
	 */
	@RequestMapping("/getProjectManage")
	public String getProjectManage(Model model){
		return "/user/ordinary/projectManage";
	}
	@RequestMapping("/getApplicationProject")
	public String getApplicationProject(Model model){
		model.addAttribute("STAGE", CodeBookConsts.STAGE_APPLICATION);
		return "/common/project/allProject_Self";
	}
	@RequestMapping("/getApplicationForm")
	public String getApplicationForm(Model model){
		return "/common/project/form_Application";
	}

	@RequestMapping("/addApplicationProject")
	@ResponseBody
	public boolean addApplicationProject(@ModelAttribute("domain") Project project,Integer status,Model model,HttpServletRequest request) throws Exception
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String submitTime=df.format(new Date());
		project.setSubmitTime(submitTime);
		boolean data=false;
		String id=request.getParameter("user.id");
		User user=userService.doGetById(id);
		project.setUser(user);
		String equipment_id=request.getParameter("equipment.id");
		Equipment equipment=equipmentService.doGetById(equipment_id);
		project.setEquipment(equipment);
		project.setStatus(status);
		project.setStage(CodeBookConsts.STAGE_APPLICATION);
		/*project.setCheckStatus(CodeBookConsts.UNCHECKED);*/
		Project_Application project_Application = new Project_Application();
		project.setCheck_unit(CodeBookConsts.UNCHECKED);
		project.setProject_Application(project_Application);
		if(projectService.doSave(project)){
			project_Application.setProject(project);
			if(project_ApplicationService.doSave(project_Application)){
				data=true;
			}
		}
		else{
			data=false;
		}
		return data;
	}
	@RequestMapping("/editProject")
	public String editProject(Model model,String id) throws Exception
	{
		Project project=projectService.doGetById(id);
		model.addAttribute("project", project);
		return "/common/project/editProject_Application";
	}
	@RequestMapping("/submitProject")
	@ResponseBody
	public boolean submitProject(Model model,String id) throws Exception
	{
		Project project=projectService.doGetById(id);
		project.setStatus(CodeBookConsts.STATUS_SUBMIT);
		if(projectService.doUpdate(project)){
			return true;
		}
		return false;
	}
	@RequestMapping("/updateApplicationProject")
	@ResponseBody
	public boolean doUpdateApplicationProject(@ModelAttribute("domain") Project project,Model model,HttpServletRequest request) throws Exception
	{
		boolean data=false;
		String id=request.getParameter("user.id");
		User user=userService.doGetById(id);
		project.setUser(user);
		String equipment_id=request.getParameter("equipment.id");
		Equipment equipment=equipmentService.doGetById(equipment_id);
		project.setEquipment(equipment);
		if(projectService.doUpdate(project)){
			data=true;
		}
		else{
			data=false;
		}
		return data;
	}
	@RequestMapping("/getEstablishmentProject")
	public String getEstablishmentProject(String id,Model model){
		model.addAttribute("STAGE", CodeBookConsts.STAGE_ESTABLISHMENT);
		return "/common/project/allProject_Self";
	}
	/*
	获取立项表
	*/
	@RequestMapping("/getEstablishmentForm")
	public String getEstablishmentForm(Model model,String id) throws Exception
	{
		Project project=projectService.doGetById(id);
		model.addAttribute("project", project);
		return "/common/project/form_Establishment";
	}
	@RequestMapping("/addEstablishmentProject")
	@ResponseBody
	public boolean addEstablishmentProject(@ModelAttribute("domain") Project project,Integer status,Model model,HttpServletRequest request) throws Exception
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String submitTime=df.format(new Date());
		project.setSubmitTime(submitTime);
		boolean data=false;
		String id=request.getParameter("user.id");
		User user=userService.doGetById(id);
		project.setUser(user);
		String equipment_id=request.getParameter("equipment.id");
		Equipment equipment=equipmentService.doGetById(equipment_id);
		project.setEquipment(equipment);
		project.setStatus(status);
		project.setStage(CodeBookConsts.STAGE_ESTABLISHMENT);
		/*project.setCheckStatus(CodeBookConsts.UNCHECKED);*/
	/*	Project_Establishment project_Establishment = new Project_Establishment();
		String relyingUnit=request.getParameter("relyingUnit");
		String currentSituation=request.getParameter("currentSituation");
		String technicalRoute=request.getParameter("technicalRoute");
		String workFoundation=request.getParameter("workFoundation");
		String target=request.getParameter("target");*/
	/*	project_Establishment.setRelyingUnit(relyingUnit);
		project_Establishment.setCurrentSituation(currentSituation);
		project_Establishment.setTechnicalRoute(technicalRoute);
		project_Establishment.setWorkFoundation(workFoundation);
		project_Establishment.setTarget(target);
		project_Establishment.setCheck_unit(CodeBookConsts.UNCHECKED);
		project.setProject_Establishment(project_Establishment);*/
		project.setCheck_unit(CodeBookConsts.UNCHECKED);
		project.setCheck_child_admin(CodeBookConsts.UNCHECKED);
		project.setCheck_head_admin(CodeBookConsts.UNCHECKED);
		if(projectService.doUpdate(project)){
		/*	project_Establishment.setProject(project);
			if(project_EstablishmentService.doSave(project_Establishment)){*/
				data=true;
			/*}*/
		}
		else{
			data=false;
		}
		return data;
	}
	@RequestMapping("/getExecutionProject")
	public String getExecutionProject(Model model){
		model.addAttribute("STAGE", CodeBookConsts.STAGE_EXECUTION);
		return "/common/project/allProject_Self";
	}
	/*
	获取执行表
	*/
	@RequestMapping("/getExecutionForm")
	public String getExecutionForm(Model model,String id) throws Exception
	{
		Project project=projectService.doGetById(id);
		model.addAttribute("project", project);
		return "/common/project/form_Execution";
	}
	@RequestMapping("/addExecutionProject")
	@ResponseBody
	public boolean addExecutionProject(@ModelAttribute("domain") Project project,Integer status,Model model,HttpServletRequest request) throws Exception
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String submitTime=df.format(new Date());
		project.setSubmitTime(submitTime);
		boolean data=false;
		String id=request.getParameter("user.id");
		User user=userService.doGetById(id);
		project.setUser(user);
		String equipment_id=request.getParameter("equipment.id");
		Equipment equipment=equipmentService.doGetById(equipment_id);
		project.setEquipment(equipment);
		project.setStatus(status);
		project.setStage(CodeBookConsts.STAGE_EXECUTION);
		project.setCheck_unit(CodeBookConsts.UNCHECKED);
		project.setCheck_child_admin(CodeBookConsts.UNCHECKED);
		project.setCheck_head_admin(CodeBookConsts.UNCHECKED);
		if(projectService.doUpdate(project)){
			data=true;
		}
		else{
			data=false;
		}
		return data;
	}
	@RequestMapping("/getAcceptanceProject")
	public String getAcceptanceProject(Model model){
		model.addAttribute("STAGE", CodeBookConsts.STAGE_ACCEPTANCE);
		return "/common/project/allProject_Self";
	}
	/*
	获取验收表
	*/
	@RequestMapping("/getAcceptanceForm")
	public String getAcceptanceForm(Model model,String id) throws Exception
	{
		Project project=projectService.doGetById(id);
		model.addAttribute("project", project);
		return "/common/project/form_Acceptance";
	}
	@RequestMapping("/addAcceptanceProject")
	@ResponseBody
	public boolean addAcceptanceProject(@ModelAttribute("domain") Project project,Integer status,Model model,HttpServletRequest request) throws Exception
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String submitTime=df.format(new Date());
		project.setSubmitTime(submitTime);
		boolean data=false;
		String id=request.getParameter("user.id");
		User user=userService.doGetById(id);
		project.setUser(user);
		String equipment_id=request.getParameter("equipment.id");
		Equipment equipment=equipmentService.doGetById(equipment_id);
		project.setEquipment(equipment);
		project.setStatus(status);
		project.setStage(CodeBookConsts.STAGE_ACCEPTANCE);
		project.setCheck_unit(CodeBookConsts.UNCHECKED);
		project.setCheck_child_admin(CodeBookConsts.UNCHECKED);
		project.setCheck_head_admin(CodeBookConsts.UNCHECKED);
		if(projectService.doUpdate(project)){
			data=true;
		}
		else{
			data=false;
		}
		return data;
	}
	@RequestMapping("/getUser")
	public String getUser(Model model)
	{
		return "/user/ordinary/userManage";
	}
	@RequestMapping("/getSelfMesManage")
	public String getSelfMesManage(Model model)
	{
		return "/user/ordinary/selfMesManage";
	}
	/**
	 * 知识产权管理
	 * @param model
	 * @return
	 */
	@RequestMapping("/getAchievementManage")
	public String getAchievementManage(Model model){
		return "/user/ordinary/achievementManage";
	}
	/**
	 * 专利管理
	 * @param model
	 * @return
	 */
	
	@RequestMapping("/toAddPatent")
	public String toAddPatent(Model model)
	{
		return "/common/patent/addPatent";
	}
	@RequestMapping("/addPatent")
	@ResponseBody
	public boolean addPatent(@ModelAttribute("domain") Patent patent,Model model,HttpServletRequest request) throws Exception
	{
		boolean data=false;
		User user=getUser();
		patent.setUser(user);
		if(patentService.doSave(patent)){
			data=true;
		}
		else{
			data=false;
		}
		return data;
	}
	/**
	 * 标准管理
	 * @param model
	 * @return
	 */
	
	@RequestMapping("/toAddStandard")
	public String toAddStandard(Model model)
	{
		return "/common/standard/addStandard";
	}
	@RequestMapping("/addStandard")
	@ResponseBody
	public boolean addStandard(@ModelAttribute("domain") Standard standard,Model model,HttpServletRequest request) throws Exception
	{
		boolean data=false;
		User user=getUser();
		standard.setUser(user);
		if(standardService.doSave(standard)){
			data=true;
		}
		else{
			data=false;
		}
		return data;
	}
	/**
	 * 论文管理
	 * @param model
	 * @return
	 */
	
	@RequestMapping("/toAddPaper")
	public String toAddPaper(Model model)
	{
		return "/common/paper/addPaper";
	}
	@RequestMapping("/addPaper")
	@ResponseBody
	public boolean addPaper(@ModelAttribute("domain") Paper paper,Model model,HttpServletRequest request) throws Exception
	{
		boolean data=false;
		User user=getUser();
		paper.setUser(user);
		if(paperService.doSave(paper)){
			data=true;
		}
		else{
			data=false;
		}
		return data;
	}
	/**
	 * 科技奖励管理
	 * @param model
	 * @return
	 */
	
	@RequestMapping("/toAddReward")
	public String toAddReward(Model model)
	{
		return "/common/reward/addReward";
	}
	@RequestMapping("/addReward")
	@ResponseBody
	public boolean addReward(@ModelAttribute("domain")Reward reward,Integer status,Model model,HttpServletRequest request) throws Exception
	{
		boolean data=false;
		String projectId=request.getParameter("project.id");
		Project project=projectService.doGetById(projectId);
		reward.setProject(project);
		String userId=request.getParameter("user.id");
		User user=userService.doGetById(userId);
		reward.setUser(user);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String date=df.format(new Date());
		reward.setDate(date);
		reward.setStatus(status);
		reward.setCheck_unit(CodeBookConsts.UNCHECKED);
		if(rewardService.doSave(reward)){
			data=true;
		}
		else{
			data=false;
		}
		return data;
	}
	@RequestMapping("/submitReward")
	@ResponseBody
	public boolean submitReward(Model model,String id) throws Exception
	{
		Reward reward=rewardService.doGetById(id);
		reward.setStatus(CodeBookConsts.STATUS_SUBMIT);
		if(rewardService.doUpdate(reward)){
			return true;
		}
		return false;
	}
}
