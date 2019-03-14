package com.kj.controller.user;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kj.domain.Equipment;
import com.kj.domain.Project;
import com.kj.domain.TecInformation;
import com.kj.domain.User;
import com.kj.domain.achievement.Paper;
import com.kj.domain.achievement.Patent;
import com.kj.domain.achievement.Reward;
import com.kj.domain.achievement.Standard;
import com.kj.service.IEquipmentService;
import com.kj.service.IPaperService;
import com.kj.service.IPatentService;
import com.kj.service.IProjectService;
import com.kj.service.IRewardService;
import com.kj.service.IStandardService;
import com.kj.service.ITecInfService;
import com.kj.service.IUserService;
import com.kj.util.CodeBookConsts;
import com.kj.util.Consts;
import com.system.util.EndecryptUtils;
import com.system.util.ValidateUtil;
import com.system.vo.PageInfo;

@Controller
@RequestMapping("/user")
public class UserController 
{
 @Resource private IUserService userService;
 @Resource private ITecInfService tecInfService;
 @Resource private IEquipmentService equipmentService;
 @Resource private IProjectService projectService;
 @Resource private IPatentService patentService;
 @Resource private IStandardService standardService;
 @Resource private IPaperService paperService;
 @Resource private IRewardService rewardService;
 @Value("#{envProperties['uploadPath']}") private String shareupload;
 @Value("#{envProperties['infomationDir']}") private String infomationDir;
 @Value("#{envProperties['fileNameSeparator']}") private String fileNameSeparator;//文件名与时间戳分隔符
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
	
	
	@RequestMapping("/selfMessage")
	public String getSelfMessage(Model model){
		User user=getUser();
		model.addAttribute("user", user);
		return "/common/selfMessage/selfMessage";
	}
	@RequestMapping("/selfPassword")
	public String getSelfPassword(Model model){
		return "/common/selfMessage/selfPassword";
	}
	@RequestMapping("/selfPhone")
	public String getSelfPhone(Model model){
		User user=getUser();
		model.addAttribute("user", user);
		return "/common/selfMessage/selfPhone";
	}
	
	/**
	 * 更新个人信息
	 * @param domain
	 * @param result
	 * @return
	 * @throws Exception 
	 */
	/*
	修改用户信息
	*/
	/*@RequestMapping("/editSelf")
	public String editSelfMes(String id,Model model) throws Exception{
	User user = userService.doGetById(id); 
	model.addAttribute("user", user);
	return "/user/editSelf";
	}*/
	@RequestMapping("/updateSelf")
	@ResponseBody
	public boolean doUpdateSelf(@Valid @ModelAttribute("domain") User user,Model model) throws Exception{
		boolean data=false;
		user.setPhone(null);
		user.setPassword(null);
		user.setRole(null);
		if(userService.doUpdate(user)){
			data=true;
		}
		return data;
	}
	@RequestMapping("/updateSelfPassword")
	@ResponseBody
	public boolean doUpdateSelfPas(Model model,HttpServletRequest request) throws Exception{
		boolean data=false;
        String oldPass=request.getParameter("oldPass");
        String newPass1=request.getParameter("newPass1");
        User user=getUser();
        String oldPassMd5=EndecryptUtils.md5(oldPass);
		if(user.getPassword().equals(oldPassMd5)){
			user.setPassword(EndecryptUtils.md5(newPass1));
			if(userService.doUpdate(user)){
				data=true;
			}
		}
		return data;
	}
	@RequestMapping("/updateSelfPhone")
	@ResponseBody
	public boolean doUpdateSelfPhone(HttpServletRequest request,Model model) throws Exception{
		User user=getUser();
		boolean data=false;
		String pwd=request.getParameter("pwd");
		String phone=request.getParameter("phone");
		if(user.getPassword().equals(EndecryptUtils.md5(pwd))){
			user.setPhone(phone);
			if(userService.doUpdate(user)){
				data=true;
			}
		}
		return data;
	}
	/**
	 * 科技信息——获取所有科技消息
	 * @return
	 */
	@RequestMapping("/getInformMessage")
	public String getInformMessage(Model model)
	{
		model.addAttribute("TYPE", CodeBookConsts.TYPE_INFORM);
		return "/common/tecInformation/allTecInformation";
	}
	@RequestMapping("/getDynamicMessage")
	public String getDynamicMessage(Model model)
	{
		model.addAttribute("TYPE", CodeBookConsts.TYPE_DYNAMIC);
		return "/common/tecInformation/allTecInformation";
	}
	@RequestMapping("/getDynamicMessage_Self")
	public String getDynamicMessage_Self(Model model)
	{
		model.addAttribute("TYPE", CodeBookConsts.TYPE_DYNAMIC);
		return "/common/tecInformation/allTecInformation_self";
	}
	@RequestMapping("/getInformMessage_Self")
	public String getInformMessage_Self(Model model)
	{
		model.addAttribute("TYPE", CodeBookConsts.TYPE_INFORM);
		return "/common/tecInformation/allTecInformation_self";
	}
	@RequestMapping("/toAddTecInf")
	public String toAddTecInf(Model model,String type)
	{
		model.addAttribute("TYPE", type);
		return "/common/tecInformation/addTecInformation";
	}
	/**
	 * 新增科技消息
	 * @param tecInf
	 * @param type
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addTecInf")
	@ResponseBody
	public boolean addTecInf(@ModelAttribute("domain") TecInformation tecInf,String type,Model model) throws Exception
	{
		boolean data=false;
		if ( tecInf.getInforTitle()==null||tecInf.getInforContent()==null || tecInf.getInforTitle().isEmpty()|| tecInf.getInforContent().isEmpty()) {// 如果校验失败,则返回
			data=false;
		} else {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String inforTime=df.format(new Date());
			tecInf.setInforTime(inforTime);
			tecInf.setInforType(Integer.parseInt(type));
			User user=getUser();
			tecInf.setUser(user);
			try {
				if(tecInfService.doSave(tecInf)){
					data=true;
				}else{
					data=false;
				}
			} catch (Exception e) {
				data=false;
			}
		}
		return data;
	}

	/**
	 * 查询科技消息
	 * @param request
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryTecInf")
	public String queryTecInf(HttpServletRequest request,HttpServletResponse response, Model model,int type) throws Exception
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
		model.addAttribute("fileNameSeparator", fileNameSeparator);
		model.addAttribute("shareupload", shareupload);
		model.addAttribute("infomationDir", infomationDir);
		model.addAttribute("tecInfList", tecInfList);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", pageInfo.getTotalPages());
		return "/common/tecInformation/tecInfList";
	}
	/**
	 * 查询科技消息
	 * @param request
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryTecInf_Self")
	public String queryTecInf_Self(HttpServletRequest request,HttpServletResponse response, Model model,int type) throws Exception
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
		
		User user=getUser();
		/*Integer auth=user.getRole().getAuthority();
		if(auth==CodeBookConsts.ROLE_AUTH_ORDINARY){
			dc.add(Restrictions.eq("user", user));
		}*/
		dc.add(Restrictions.eq("user", user));
		dc.addOrder(Order.desc("id"));
		List<TecInformation> tecInfList = tecInfService.doGetPageList(pageInfo, dc);
		model.addAttribute("fileNameSeparator", fileNameSeparator);
		model.addAttribute("shareupload", shareupload);
		model.addAttribute("infomationDir", infomationDir);
		model.addAttribute("tecInfList", tecInfList);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", pageInfo.getTotalPages());
		/*model.addAttribute("user", auth);*/
		return "/common/tecInformation/tecInfList";
	}
	/**
	 * 删除信息
	 * @param id
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delTecInf")
	@ResponseBody
	public String  delTecInf(@RequestParam String id,Model model) throws Exception
	{
	String result = Consts.MSG_FAILURE; 
	String[] ids = {id};
	if(tecInfService.doDeleteByIds(ids))
       { 
		result = Consts.MSG_SUCCESS;
		}
	return result;
	}
	@RequestMapping("/toSelectEquipment")
	public String selectEquipment(Model model){
		return "/common/equipment/selectEquipment";
	}
	
	@RequestMapping("/queryEquipment")
	public String queryEquipment(HttpServletRequest request, Model model) throws Exception
	{
		//分页
		PageInfo pageInfo = new PageInfo();
		pageInfo.setSizePerPage(5);
		String currentPage=request.getParameter("currentPage");
		if(currentPage!=null && !currentPage.isEmpty()){
			pageInfo.setCurrentPageNo(Integer.parseInt(currentPage));
		}
		//查询条件
		DetachedCriteria dc = DetachedCriteria.forClass(Equipment.class);
		String equipmentName=request.getParameter("equipmentName");
		if(equipmentName!=null && !equipmentName.isEmpty()){
			dc.add(Restrictions.like("equipmentName", equipmentName));
		}
		List<Equipment> equipmentList = equipmentService.doGetPageList(pageInfo, dc);
		model.addAttribute("equipmentList", equipmentList);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", pageInfo.getTotalPages());
		return "/common/equipment/selectEquipmentList";
	}
	@RequestMapping("/toSelectUser")
	public String selectUser(Model model){
		return "/common/user/selectUser";
	}
	@RequestMapping("/queryUser")
	public String queryUser(HttpServletRequest request, Model model) throws Exception
	{
		//分页
		PageInfo pageInfo = new PageInfo();
		pageInfo.setSizePerPage(5);
		String currentPage=request.getParameter("currentPage");
		if(currentPage!=null && !currentPage.isEmpty()){
			pageInfo.setCurrentPageNo(Integer.parseInt(currentPage));
		}
		//查询条件
		DetachedCriteria dc = DetachedCriteria.forClass(User.class);
		String username=request.getParameter("username");
		if(username!=null && !username.isEmpty()){
			dc.add(Restrictions.eq("username", username));
		}
		List<User> userList = userService.doGetPageList(pageInfo, dc);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("userList", userList);
		model.addAttribute("totalPages", pageInfo.getTotalPages());
		return "/common/user/selectUserList";
	}
	@RequestMapping("/toSelectProject")
	public String selectProject(Model model){
		return "/common/project/selectProject";
	}
	/*
	删除用户信息
	*/
	@RequestMapping("/delProject")
	@ResponseBody
	public String  delProject(@RequestParam String id,Model model) throws Exception
	{
		
		String result = Consts.MSG_FAILURE; 
		String[] ids = {id};
		if(projectService.doDeleteByIds(ids)){ 
			result = Consts.MSG_SUCCESS;
			}
		return result;
	}
	/**
	 * 查询项目
	 * @param request
	 * @param model
	 * @param stage
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryProject_Finish")
	public String queryProject_Finish(HttpServletRequest request, Model model) throws Exception
	{
		//分页
		PageInfo pageInfo = new PageInfo();
		pageInfo.setSizePerPage(10);
		String currentPage=request.getParameter("currentPage");
		if(currentPage!=null && !currentPage.isEmpty()){
			pageInfo.setCurrentPageNo(Integer.parseInt(currentPage));
		}
		//查询条件
		DetachedCriteria dc = DetachedCriteria.forClass(Project.class);
		String projectname=request.getParameter("projectName");
		String returnUrl=request.getParameter("returnUrl");
		if(projectname!=null && !projectname.isEmpty()){
			dc.add(Restrictions.like("projectName", projectname));
		}
		dc.add(Restrictions.eq("status", CodeBookConsts.STATUS_SUBMIT));
		dc.add(Restrictions.eq("stage", CodeBookConsts.STAGE_ACCEPTANCE));
		dc.add(Restrictions.eq("check_head_admin", CodeBookConsts.CHECK_APPROVED));
		List<Project> projectList = projectService.doGetPageList(pageInfo, dc);
		model.addAttribute("projectList", projectList);
		model.addAttribute("totalPages", pageInfo.getTotalPages());
		if(returnUrl!=null && !returnUrl.isEmpty()){
			return returnUrl;
		}
		return "/common/project/selectProjectList";
	}
	/**
	 * 查询项目
	 * @param request
	 * @param model
	 * @param stage
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryProject")
	public String queryProject(HttpServletRequest request, Model model,Integer stage) throws Exception
	{
		//分页
		PageInfo pageInfo = new PageInfo();
		pageInfo.setSizePerPage(10);
		String currentPage=request.getParameter("currentPage");
		if(currentPage!=null && !currentPage.isEmpty()){
			pageInfo.setCurrentPageNo(Integer.parseInt(currentPage));
		}
		//查询条件
		DetachedCriteria dc = DetachedCriteria.forClass(Project.class);
		String projectname=request.getParameter("projectName");
		String returnUrl=request.getParameter("returnUrl");
		if(projectname!=null && !projectname.isEmpty()){
			dc.add(Restrictions.like("projectName", projectname));
		}
		dc.add(Restrictions.eq("stage", stage));
		dc.add(Restrictions.eq("status", CodeBookConsts.STATUS_SUBMIT));
		List<Project> projectList = projectService.doGetPageList(pageInfo, dc);
		User user=getUser();
		model.addAttribute("user", user);
		model.addAttribute("projectList", projectList);
		model.addAttribute("totalPages", pageInfo.getTotalPages());
		if(returnUrl!=null && !returnUrl.isEmpty()){
			return returnUrl;
		}
		return "/common/project/projectList";
	}
	/**
	 * 查询项目
	 * @param request
	 * @param model
	 * @param stage
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryProject_Self")
	public String queryProject_Self(HttpServletRequest request, Model model,Integer stage) throws Exception
	{
		//分页
		PageInfo pageInfo = new PageInfo();
		pageInfo.setSizePerPage(10);
		String currentPage=request.getParameter("currentPage");
		if(currentPage!=null && !currentPage.isEmpty()){
			pageInfo.setCurrentPageNo(Integer.parseInt(currentPage));
		}
		//查询条件
		DetachedCriteria dc = DetachedCriteria.forClass(Project.class);
		String projectname=request.getParameter("projectName");
		String returnUrl=request.getParameter("returnUrl");
		if(projectname!=null && !projectname.isEmpty()){
			dc.add(Restrictions.like("projectName", projectname));
		}
		dc.add(Restrictions.eq("stage", stage));
        //用户身份
		User user=getUser();
		dc.add(Restrictions.eq("user", user));
		List<Project> projectList = projectService.doGetPageList(pageInfo, dc);
		model.addAttribute("projectList", projectList);
		model.addAttribute("totalPages", pageInfo.getTotalPages());
		if(returnUrl!=null && !returnUrl.isEmpty()){
			return returnUrl;
		}
		return "/common/project/projectList_Self";
	}
	@RequestMapping("/getProjectBasicList")
	public String getProjectBasicList(HttpServletRequest request, Model model) throws Exception
	{
		//分页
		PageInfo pageInfo = new PageInfo();
		pageInfo.setSizePerPage(5);
		String currentPage=request.getParameter("currentPage");
		if(currentPage!=null && !currentPage.isEmpty()){
			pageInfo.setCurrentPageNo(Integer.parseInt(currentPage));
		}
		//查询条件
		DetachedCriteria dc = DetachedCriteria.forClass(Project.class);
		String projectname=request.getParameter("projectName");
		String returnUrl=request.getParameter("returnUrl");
		if(projectname!=null && !projectname.isEmpty()){
			dc.add(Restrictions.like("projectName", projectname));
		}
		dc.add(Restrictions.eq("stage", CodeBookConsts.STAGE_ACCEPTANCE));
		dc.add(Restrictions.eq("status", CodeBookConsts.STATUS_SUBMIT));
		dc.add(Restrictions.eq("check_head_admin", CodeBookConsts.CHECK_APPROVED));
		List<Project> projectList = projectService.doGetPageList(pageInfo, dc);
		model.addAttribute("projectList", projectList);
		model.addAttribute("totalPages", pageInfo.getTotalPages());
		if(returnUrl!=null && !returnUrl.isEmpty()){
			return returnUrl;
		}
		return "/common/project/projectBasicList";
	}
	@RequestMapping("/getProjectDetailList")
	public String getProjectDetailList(HttpServletRequest request, Model model) throws Exception
	{
		//分页
		PageInfo pageInfo = new PageInfo();
		pageInfo.setSizePerPage(5);
		String currentPage=request.getParameter("currentPage");
		if(currentPage!=null && !currentPage.isEmpty()){
			pageInfo.setCurrentPageNo(Integer.parseInt(currentPage));
		}
		//查询条件
		DetachedCriteria dc = DetachedCriteria.forClass(Project.class);
		String projectname=request.getParameter("projectName");
		String returnUrl=request.getParameter("returnUrl");
		if(projectname!=null && !projectname.isEmpty()){
			dc.add(Restrictions.like("projectName", projectname));
		}
		User user=getUser();
		if(user.getRole().getAuthority()==CodeBookConsts.ROLE_AUTH_ORDINARY){
			dc.add(Restrictions.eq("user", user));
		}
		dc.add(Restrictions.eq("stage", CodeBookConsts.STAGE_ACCEPTANCE));
		dc.add(Restrictions.eq("status", CodeBookConsts.STATUS_SUBMIT));
		dc.add(Restrictions.eq("check_head_admin", CodeBookConsts.CHECK_APPROVED));
		List<Project> projectList = projectService.doGetPageList(pageInfo, dc);
		
		model.addAttribute("user", user);
		model.addAttribute("projectList", projectList);
		model.addAttribute("totalPages", pageInfo.getTotalPages());
		if(returnUrl!=null && !returnUrl.isEmpty()){
			return returnUrl;
		}
		return "/common/project/projectDetailList";
	}
	@RequestMapping("/getProjectInformation")
	public String getProjectInformation(Model model,@RequestParam String id) throws Exception{
		Project project=projectService.doGetById(id);
		model.addAttribute("project", project);
		return "/common/project/projectInformation_Application";
	}
	/**
	 * 专利管理
	 * @param model
	 * @return
	 */
	@RequestMapping("/getPatent_Self")
	public String getPatent_Self(Model model)
	{
		User user=getUser();
		model.addAttribute("user", user);
		return "/common/patent/allPatent_Self";
	}
	@RequestMapping("/delPatent")
	@ResponseBody
	public String  delPatent(@RequestParam String id,Model model) throws Exception
	{
	String result = Consts.MSG_FAILURE; 
	String[] ids = {id};
	if(patentService.doDeleteByIds(ids))
       { 
		result = Consts.MSG_SUCCESS;
		}
	return result;
	}
	@RequestMapping("/queryPatent_Self")
	public String queryPatent_Self(HttpServletRequest request,HttpServletResponse response, Model model) throws Exception
	{
		//分页
		PageInfo pageInfo = new PageInfo();
		pageInfo.setSizePerPage(5);
		String currentPage=request.getParameter("currentPage");
		if(currentPage!=null && !currentPage.isEmpty()){
			pageInfo.setCurrentPageNo(Integer.parseInt(currentPage));
		}
		//查询条件
		DetachedCriteria dc = DetachedCriteria.forClass(Patent.class);
		dc.createAlias("user", "user");
		String patentName=request.getParameter("patentName");
		if(patentName!=null && !patentName.isEmpty()){
			dc.add(Restrictions.like("patentName", patentName));
		}
		User user=getUser();
		if(user.getRole().getAuthority()==CodeBookConsts.ROLE_AUTH_ORDINARY){
			dc.add(Restrictions.eq("user", user));
		}
		else if(user.getRole().getAuthority()==CodeBookConsts.ROLE_AUTH_UNIT_ADMIN){
			dc.add(Restrictions.eq("user.unit", user.getUnit()));
		}
		else if(user.getRole().getAuthority()==CodeBookConsts.ROLE_AUTH_CHILD_ADMIN){
			dc.add(Restrictions.eq("user.subsidiary", user.getSubsidiary()));
		}
		else if(user.getRole().getAuthority()==CodeBookConsts.ROLE_AUTH_SUPER_ADMIN){
		}
		List<Patent> patentList = patentService.doGetPageList(pageInfo, dc);
		model.addAttribute("patentList", patentList);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", pageInfo.getTotalPages());
		return "/common/patent/patentList_Self";
	}
	/**
	 * 标准管理
	 * @param model
	 * @return
	 */
	@RequestMapping("/getStandard_Self")
	public String getStandard_Self(Model model)
	{
		User user=getUser();
		model.addAttribute("user", user);
		return "/common/standard/allStandard_Self";
	}
	@RequestMapping("/delStandard")
	@ResponseBody
	public String  delStandard(@RequestParam String id,Model model) throws Exception
	{
	String result = Consts.MSG_FAILURE; 
	String[] ids = {id};
	if(standardService.doDeleteByIds(ids))
       { 
		result = Consts.MSG_SUCCESS;
		}
	return result;
	}
	@RequestMapping("/queryStandard_Self")
	public String queryStandard_Self(HttpServletRequest request,HttpServletResponse response, Model model) throws Exception
	{
		//分页
		PageInfo pageInfo = new PageInfo();
		pageInfo.setSizePerPage(5);
		String currentPage=request.getParameter("currentPage");
		if(currentPage!=null && !currentPage.isEmpty()){
			pageInfo.setCurrentPageNo(Integer.parseInt(currentPage));
		}
		//查询条件
		DetachedCriteria dc = DetachedCriteria.forClass(Standard.class);
		dc.createAlias("user", "user");
		String standardName=request.getParameter("standardName");
		if(standardName!=null && !standardName.isEmpty()){
			dc.add(Restrictions.like("standardName", standardName));
		}
		User user=getUser();
		if(user.getRole().getAuthority()==CodeBookConsts.ROLE_AUTH_ORDINARY){
			dc.add(Restrictions.eq("user", user));
		}
		else if(user.getRole().getAuthority()==CodeBookConsts.ROLE_AUTH_UNIT_ADMIN){
			dc.add(Restrictions.eq("user.unit", user.getUnit()));
		}
		else if(user.getRole().getAuthority()==CodeBookConsts.ROLE_AUTH_CHILD_ADMIN){
			dc.add(Restrictions.eq("user.subsidiary", user.getSubsidiary()));
		}
		else if(user.getRole().getAuthority()==CodeBookConsts.ROLE_AUTH_SUPER_ADMIN){
		}
		List<Standard> standardList = standardService.doGetPageList(pageInfo, dc);
		model.addAttribute("standardList", standardList);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", pageInfo.getTotalPages());
		return "/common/standard/standardList_Self";
	}
	/**
	 * 论文管理
	 * @param model
	 * @return
	 */
	@RequestMapping("/getPaper_Self")
	public String getPaper_Self(Model model)
	{
		User user=getUser();
		model.addAttribute("user", user);
		return "/common/paper/allPaper_Self";
	}
	@RequestMapping("/delPaper")
	@ResponseBody
	public String  delPaper(@RequestParam String id,Model model) throws Exception
	{
	String result = Consts.MSG_FAILURE; 
	String[] ids = {id};
	if(paperService.doDeleteByIds(ids))
       { 
		result = Consts.MSG_SUCCESS;
		}
	return result;
	}
	@RequestMapping("/queryPaper_Self")
	public String queryPaper_Self(HttpServletRequest request,HttpServletResponse response, Model model) throws Exception
	{
		//分页
		PageInfo pageInfo = new PageInfo();
		pageInfo.setSizePerPage(5);
		String currentPage=request.getParameter("currentPage");
		if(currentPage!=null && !currentPage.isEmpty()){
			pageInfo.setCurrentPageNo(Integer.parseInt(currentPage));
		}
		//查询条件
		DetachedCriteria dc = DetachedCriteria.forClass(Paper.class);
		dc.createAlias("user", "user");
		String paperName=request.getParameter("paperName");
		if(paperName!=null && !paperName.isEmpty()){
			dc.add(Restrictions.like("paperName", paperName));
		}
		User user=getUser();
		if(user.getRole().getAuthority()==CodeBookConsts.ROLE_AUTH_ORDINARY){
			dc.add(Restrictions.eq("user", user));
		}
		else if(user.getRole().getAuthority()==CodeBookConsts.ROLE_AUTH_UNIT_ADMIN){
			dc.add(Restrictions.eq("user.unit", user.getUnit()));
		}
		else if(user.getRole().getAuthority()==CodeBookConsts.ROLE_AUTH_CHILD_ADMIN){
			dc.add(Restrictions.eq("user.subsidiary", user.getSubsidiary()));
		}
		else if(user.getRole().getAuthority()==CodeBookConsts.ROLE_AUTH_SUPER_ADMIN){
		}
		List<Paper> paperList =paperService.doGetPageList(pageInfo, dc);
		model.addAttribute("paperList", paperList);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", pageInfo.getTotalPages());
		return "/common/paper/paperList_Self";
	}
	/**
	 * 科技奖励
	 * @param model
	 * @return
	 */
	@RequestMapping("/getReward_Self")
	public String getReward_Self(Model model)
	{
		User user=getUser();
		model.addAttribute("user", user);
		return "/common/reward/allReward_Self";
	}
	@RequestMapping("/getReward_Detail")
	public String getReward_Detail(HttpServletRequest request,Model model) throws Exception
	{
		//分页
		PageInfo pageInfo = new PageInfo();
		pageInfo.setSizePerPage(5);
		String currentPage=request.getParameter("currentPage");
		if(currentPage!=null && !currentPage.isEmpty()){
			pageInfo.setCurrentPageNo(Integer.parseInt(currentPage));
		}
		//查询条件
		DetachedCriteria dc = DetachedCriteria.forClass(Reward.class);
		String rewardName=request.getParameter("rewardName");
		if(rewardName!=null && !rewardName.isEmpty()){
			dc.add(Restrictions.like("rewardName", rewardName));
		}
		User user=getUser();
		if(user.getRole().getAuthority()==CodeBookConsts.ROLE_AUTH_ORDINARY){
			dc.add(Restrictions.eq("user",user));
		}
		dc.add(Restrictions.eq("check_head_admin",CodeBookConsts.CHECK_APPROVED));
		List<Reward> rewardList = rewardService.doGetPageList(pageInfo, dc);
		model.addAttribute("rewardList", rewardList);
		model.addAttribute("totalPages", pageInfo.getTotalPages());
		return "/common/reward/rewardDetailList";
	}
	@RequestMapping("/getReward_Basic")
	public String getReward_Basic(HttpServletRequest request,Model model) throws Exception
	{
		//分页
		PageInfo pageInfo = new PageInfo();
		pageInfo.setSizePerPage(5);
		String currentPage=request.getParameter("currentPage");
		if(currentPage!=null && !currentPage.isEmpty()){
			pageInfo.setCurrentPageNo(Integer.parseInt(currentPage));
		}
		//查询条件
		DetachedCriteria dc = DetachedCriteria.forClass(Reward.class);
		String rewardName=request.getParameter("rewardName");
		if(rewardName!=null && !rewardName.isEmpty()){
			dc.add(Restrictions.like("rewardName", rewardName));
		}
		dc.add(Restrictions.eq("check_head_admin",CodeBookConsts.CHECK_APPROVED));
		List<Reward> rewardList = rewardService.doGetPageList(pageInfo, dc);
		model.addAttribute("rewardList", rewardList);
		model.addAttribute("totalPages", pageInfo.getTotalPages());
		return "/common/reward/rewardBasicList";
	}
	/**
	 * 查询项目
	 * @param request
	 * @param model
	 * @param stage
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryReward_Self")
	public String queryReward_Self(HttpServletRequest request, Model model) throws Exception
	{
		//分页
		PageInfo pageInfo = new PageInfo();
		pageInfo.setSizePerPage(10);
		String currentPage=request.getParameter("currentPage");
		if(currentPage!=null && !currentPage.isEmpty()){
			pageInfo.setCurrentPageNo(Integer.parseInt(currentPage));
		}
		//查询条件
		DetachedCriteria dc = DetachedCriteria.forClass(Reward.class);
		String rewardName=request.getParameter("rewardName");
		if(rewardName!=null && !rewardName.isEmpty()){
			dc.add(Restrictions.like("rewardName", rewardName));
		}
        //用户身份
		User user=getUser();
		dc.add(Restrictions.eq("user", user));
		List<Reward> rewardList = rewardService.doGetPageList(pageInfo, dc);
		model.addAttribute("rewardList", rewardList);
		model.addAttribute("totalPages", pageInfo.getTotalPages());
		return "/common/reward/rewardList_Self";
	}
	/**
	 * 查询科技奖励
	 * @param request
	 * @param model
	 * @param stage
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryReward")
	public String queryReward(HttpServletRequest request, Model model) throws Exception
	{
		//分页
		PageInfo pageInfo = new PageInfo();
		pageInfo.setSizePerPage(10);
		String currentPage=request.getParameter("currentPage");
		if(currentPage!=null && !currentPage.isEmpty()){
			pageInfo.setCurrentPageNo(Integer.parseInt(currentPage));
		}
		//查询条件
		DetachedCriteria dc = DetachedCriteria.forClass(Reward.class);
		String rewardName=request.getParameter("rewardName");
		if(rewardName!=null && !rewardName.isEmpty()){
			dc.add(Restrictions.like("rewardName", rewardName));
		}
		dc.add(Restrictions.eq("status", CodeBookConsts.STATUS_SUBMIT));
        //用户身份
		User user=getUser();
		List<Reward> rewardList = rewardService.doGetPageList(pageInfo, dc);
		model.addAttribute("user", user);
		model.addAttribute("rewardList", rewardList);
		model.addAttribute("totalPages", pageInfo.getTotalPages());
		return "/common/reward/rewardList";
	}
	@RequestMapping("/delReward")
	@ResponseBody
	public String  delReward(@RequestParam String id,Model model) throws Exception
	{
	String result = Consts.MSG_FAILURE; 
	String[] ids = {id};
	if(rewardService.doDeleteByIds(ids))
       { 
		result = Consts.MSG_SUCCESS;
		}
	return result;
	}
	/**
	 * 上传文件
	 * @param file
	 * @param productName
	 * @param isImage
	 * @return
	 */
	@RequestMapping("/fileUpload.do")
	@ResponseBody
	public String doFileUpload(@RequestParam(value = "file", required = false) MultipartFile file){
		String fileType = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."),
				file.getOriginalFilename().length());
		String fileRealName = file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf("."));
		String fileName = fileRealName + fileNameSeparator + System.currentTimeMillis() + fileType;
		String path = shareupload + infomationDir +File.separator;
		File targetFile = new File(path, fileName);
		if(!targetFile.exists()){
			targetFile.mkdirs();
		}
		targetFile.setWritable(true, false);
		try{
			//保存文件
			file.transferTo(targetFile);
		}catch (Exception e){
			e.printStackTrace();
		}
		return fileName;
	}
}
