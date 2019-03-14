package com.kj.controller.user;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.hibernate.criterion.DetachedCriteria;
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
import com.kj.domain.achievement.Reward;
import com.kj.service.IEquipmentService;
import com.kj.service.IProjectService;
import com.kj.service.IProject_ApplicationService;
import com.kj.service.IRewardService;
import com.kj.service.ITecInfService;
import com.kj.service.IUserService;
import com.kj.util.CodeBookConsts;
import com.kj.util.Consts;
import com.system.util.ExportExcel;
import com.system.util.ValidateUtil;
import com.system.vo.PageInfo;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Resource private ITecInfService tecInfService;
	@Resource private IUserService userService;
	@Resource private IProjectService projectService;
	@Resource private IProject_ApplicationService project_ApplicationService;
	@Resource private IEquipmentService equipmentService;
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
	/**
	 * 科技设备
	 * @return
	 */
	@RequestMapping("/getEquipment")
	public String getEquipment(Model model)
	{
		User currentUser=getUser();
		model.addAttribute("currentUser", currentUser);
		return "/common/equipment/allEquipment";
	}
	/**
	 * 新增设备信息
	 * @return
	 */
	@RequestMapping("/toAddEquipment")
	public String getAddEquipmentPage(Model model){
	return "/common/equipment/addEquipment";
	}
	/**
	 * 查询设备
	 * @param request
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception
	 */
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
		User user=getUser();
		model.addAttribute("user", user);
		model.addAttribute("equipmentList", equipmentList);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", pageInfo.getTotalPages());
		return "/common/equipment/equipmentDetailedList";
	}
	/**
	 * 删除设备
	 * @param id
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delEquipment")
	@ResponseBody
	public String  delEquipment(@RequestParam String id,Model model) throws Exception
	{
	String result = Consts.MSG_FAILURE; 
	String[] ids = {id};
	if(equipmentService.doDeleteByIds(ids))
       { 
		result = Consts.MSG_SUCCESS;
		}
	return result;
	}
	/**
	 * 新增设备消息
	 * @param tecInf
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addEquipment")
	@ResponseBody
	public boolean addEquipment(@ModelAttribute("domain")Equipment equipment,Model model,HttpServletRequest request) throws Exception
	{
		boolean data=false;
			try {
				String id=request.getParameter("user.id");
				if(equipment.getForeignLease()==null||equipment.isForeignLease().equals("")){
					equipment.setForeignLease(false);
				}
				User user=userService.doGetById(id);
				equipment.setUser(user);
				if(equipmentService.doSave(equipment)){
					data=true;
				}else{
					data=false;
				}
			} catch (Exception e) {
				data=false;
			}
		return data;
	}
	/**
	 * 编辑设备信息
	 * @param id
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/editEquipment")
	public String getEditEquipment(String id,Model model) throws Exception{
		Equipment equipment=equipmentService.doGetById(id);
		model.addAttribute("equipment",equipment);
		return "/common/equipment/editEquipment";
	}
	@RequestMapping("/getEquipmentInfo")
	public String getEquipmentInfo(String id,Model model) throws Exception{
		Equipment equipment=equipmentService.doGetById(id);
		model.addAttribute("equipment",equipment);
		return "/common/equipment/equipmentInfo";
	}
	/**
	 * 更新设备信息
	 * @param equipment
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateEquipment")
	@ResponseBody
	public boolean doUpdateEquipment(@ModelAttribute("domain") Equipment equipment,Model model,HttpServletRequest request) throws Exception{
		boolean data=false;
		try {
			String id=request.getParameter("user.id");
			if(equipment.getForeignLease()==null||equipment.isForeignLease().equals("")){
				equipment.setForeignLease(false);
			}
			User user=userService.doGetById(id);
			equipment.setUser(user);
			if(equipmentService.doUpdate(equipment)){
				data=true;
			}else{
				data=false;
			}
		} catch (Exception e) {
			data=false;
		}
	return data;
	}
	/**
	 * 查看负责人信息
	 * @param username
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getUser")
	public String editUser(String id,Model model) throws Exception{
	User user = userService.doGetById(id); 
	model.addAttribute("user", user);
	return "/common/user/userInformation";
	}
	@RequestMapping("/exportEquipment")
	@ResponseBody
	public boolean exportEquipment(HttpServletRequest request, Model model) throws Exception
	{
		String[] headers = { "设备编号", "设备名称", "单位", "维护人员", "设备价格","购买时间","维护费用","设备功能","对外租赁","关联项目","备注" };
	     String title="设备表";
	     OutputStream out = new FileOutputStream("F://equipments.xls");
	     String date=new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis()));
	     DetachedCriteria dc = DetachedCriteria.forClass(Equipment.class);
	     List<Object> equipmentList = ( List<Object>)(List)equipmentService.doGetFilterList(dc);
	     
	     ExportExcel equipmentExport=new ExportExcel();
	     return equipmentExport.exportExcel(title, headers, equipmentList, out,date);
	}
	/**
	 * 新增科技信息
	 * @return
	 */
	@RequestMapping("/toAddTecInf")
	public String getAddUserPage(Model model,String type){
		model.addAttribute("TYPE", type);
	return "/common/tecInformation/addTecInformation";
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
	
	/*-----------人员管理-----------*/
	@RequestMapping("/getOrdinary")
	   public String getOrdinaryUser(Model model){
		   User currentUser=getUser();
		   model.addAttribute("currentUser", currentUser);
		   model.addAttribute("ROLE_AUTH", CodeBookConsts.ROLE_AUTH_ORDINARY);
		   return  "/common/user/allUser";
	   }
	   @RequestMapping("/getUnitAdmin")
	   public String getUnitAdmin(Model model){
		   User currentUser=getUser();
		   model.addAttribute("currentUser", currentUser);
		   model.addAttribute("ROLE_AUTH", CodeBookConsts.ROLE_AUTH_UNIT_ADMIN);
		   return  "/common/user/allUser";
	   }
	   @RequestMapping("/getChildAdmin")
	   public String getChildAdmin(Model model){
		   User currentUser=getUser();
		   model.addAttribute("currentUser", currentUser);
		   model.addAttribute("ROLE_AUTH", CodeBookConsts.ROLE_AUTH_CHILD_ADMIN);
		   return  "/common/user/allUser";
	   }
	   @RequestMapping("/getHeadAdmin")
	   public String getHeadAdmin(Model model){
		   User currentUser=getUser();
		   model.addAttribute("currentUser", currentUser);
		   model.addAttribute("ROLE_AUTH", CodeBookConsts.ROLE_AUTH_HEAD_ADMIN);
		   return  "/common/user/allUser";
	   }
	   @RequestMapping("/getSuperAdmin")
	   public String getSuperAdmin(Model model){
		   User currentUser=getUser();
		   model.addAttribute("currentUser", currentUser);
		   model.addAttribute("ROLE_AUTH", CodeBookConsts.ROLE_AUTH_SUPER_ADMIN);
		   return  "/common/user/allUser";
	   }
  /**
	 * 展示用户
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryUser")
	public String queryUser(HttpServletRequest request, Model model,String role_auth) throws Exception
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
		if(role_auth!=null && !role_auth.isEmpty()){
			dc.add(Restrictions.eq("role.authority", Integer.parseInt(role_auth)));
		}
		if(username!=null && !username.isEmpty()){
			dc.add(Restrictions.eq("username", username));
		}
		User currentUser=getUser();
		List<User> userList = userService.doGetPageList(pageInfo, dc);
		model.addAttribute("currentUser", currentUser);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("userList", userList);
		model.addAttribute("totalPages", pageInfo.getTotalPages());
		return "/common/user/userListM";
	}
	@RequestMapping("/getApplication")
	public String getApplication(Model model) 
	{
		model.addAttribute("STAGE", CodeBookConsts.STAGE_APPLICATION);
		return "/common/project/allProject";
	}
	@RequestMapping("/getEstablishment")
	public String getEstablishment(Model model) 
	{
		model.addAttribute("STAGE", CodeBookConsts.STAGE_ESTABLISHMENT);
		return "/common/project/allProject";
	}
	@RequestMapping("/getExecution")
	public String getExecution(Model model) 
	{
		model.addAttribute("STAGE", CodeBookConsts.STAGE_EXECUTION);
		return "/common/project/allProject";
	}
	@RequestMapping("/getAcceptance")
	public String getAcceptance(Model model) 
	{
		model.addAttribute("STAGE", CodeBookConsts.STAGE_ACCEPTANCE);
		return "/common/project/allProject";
	}
	/**
	 * 审核通过
	 * @param id
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/check_approved")
	@ResponseBody
	public boolean check_approved(String id,HttpServletRequest request,Model model) throws Exception{
		boolean data=false;
		Project project=projectService.doGetById(id);
		User user=getUser();
		if(user.getRole().getAuthority()==CodeBookConsts.ROLE_AUTH_UNIT_ADMIN){
			project.setCheck_unit(CodeBookConsts.CHECK_APPROVED);
			project.setCheck_child_admin(CodeBookConsts.UNCHECKED);
			project.setCheck_head_admin(CodeBookConsts.UNCHECKED);
			projectService.doUpdate(project);
			data=true;
		}
		else if(user.getRole().getAuthority()==CodeBookConsts.ROLE_AUTH_CHILD_ADMIN){
			project.setCheck_child_admin(CodeBookConsts.CHECK_APPROVED);
			project.setCheck_head_admin(CodeBookConsts.UNCHECKED);
			projectService.doUpdate(project);
			data=true;
		}
		else if(user.getRole().getAuthority()==CodeBookConsts.ROLE_AUTH_HEAD_ADMIN){
			String projectNumber=request.getParameter("projectNumber");
			project.setProjectNumber(projectNumber);
			project.setCheck_head_admin(CodeBookConsts.CHECK_APPROVED);
			projectService.doUpdate(project);
			data=true;
		}
		return data;
	}
	/**
	 * 审核不通过
	 * @param id
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/check_unapproved")
	@ResponseBody
	public boolean check_unapproved(String id,Model model) throws Exception{
		boolean data=false;
		Project project=projectService.doGetById(id);
		User user=getUser();
		if(user.getRole().getAuthority()==CodeBookConsts.ROLE_AUTH_UNIT_ADMIN){
			project.setCheck_unit(CodeBookConsts.CHECK_UNAPPROVED);
			//project.getProject_Application().setCheck_child_admin(CodeBookConsts.UNCHECKED);
			projectService.doUpdate(project);
			data=true;
		}
		else if(user.getRole().getAuthority()==CodeBookConsts.ROLE_AUTH_CHILD_ADMIN){
			project.setCheck_child_admin(CodeBookConsts.CHECK_UNAPPROVED);
			project.setCheck_unit(CodeBookConsts.CHECK_UNAPPROVED);
			projectService.doUpdate(project);
			data=true;
		}
		else if(user.getRole().getAuthority()==CodeBookConsts.ROLE_AUTH_HEAD_ADMIN){
			project.setCheck_head_admin(CodeBookConsts.CHECK_UNAPPROVED);
			project.setCheck_child_admin(CodeBookConsts.CHECK_UNAPPROVED);
			project.setCheck_unit(CodeBookConsts.CHECK_UNAPPROVED);
			projectService.doUpdate(project);
			data=true;
		}
		return data;
	}
	@RequestMapping("/getReward")
	public String getReward(Model model) 
	{
		return "/common/reward/allReward";
	}
	/**
	 * 审核通过
	 * @param id
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/check_approved_reward")
	@ResponseBody
	public boolean check_approved_reward(String id,HttpServletRequest request,Model model) throws Exception{
		boolean data=false;
		Reward reward=rewardService.doGetById(id);
		User user=getUser();
		if(user.getRole().getAuthority()==CodeBookConsts.ROLE_AUTH_UNIT_ADMIN){
			reward.setCheck_unit(CodeBookConsts.CHECK_APPROVED);
			reward.setCheck_child_admin(CodeBookConsts.UNCHECKED);
			reward.setCheck_head_admin(CodeBookConsts.UNCHECKED);
			rewardService.doUpdate(reward);
			data=true;
		}
		else if(user.getRole().getAuthority()==CodeBookConsts.ROLE_AUTH_CHILD_ADMIN){
			reward.setCheck_child_admin(CodeBookConsts.CHECK_APPROVED);
			reward.setCheck_head_admin(CodeBookConsts.UNCHECKED);
			rewardService.doUpdate(reward);
			data=true;
		}
		else if(user.getRole().getAuthority()==CodeBookConsts.ROLE_AUTH_HEAD_ADMIN){
			reward.setCheck_head_admin(CodeBookConsts.CHECK_APPROVED);
			rewardService.doUpdate(reward);
			data=true;
		}
		return data;
	}
	/**
	 * 审核不通过
	 * @param id
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/check_unapproved_reward")
	@ResponseBody
	public boolean check_unapproved_reward(String id,Model model) throws Exception{
		boolean data=false;
		Reward reward=rewardService.doGetById(id);
		User user=getUser();
		if(user.getRole().getAuthority()==CodeBookConsts.ROLE_AUTH_UNIT_ADMIN){
			reward.setCheck_unit(CodeBookConsts.CHECK_UNAPPROVED);
			//project.getProject_Application().setCheck_child_admin(CodeBookConsts.UNCHECKED);
			rewardService.doUpdate(reward);
			data=true;
		}
		else if(user.getRole().getAuthority()==CodeBookConsts.ROLE_AUTH_CHILD_ADMIN){
			reward.setCheck_child_admin(CodeBookConsts.CHECK_UNAPPROVED);
			reward.setCheck_unit(CodeBookConsts.CHECK_UNAPPROVED);
			rewardService.doUpdate(reward);
			data=true;
		}
		else if(user.getRole().getAuthority()==CodeBookConsts.ROLE_AUTH_HEAD_ADMIN){
			reward.setCheck_head_admin(CodeBookConsts.CHECK_UNAPPROVED);
			reward.setCheck_child_admin(CodeBookConsts.CHECK_UNAPPROVED);
			reward.setCheck_unit(CodeBookConsts.CHECK_UNAPPROVED);
			rewardService.doUpdate(reward);
			data=true;
		}
		return data;
	}
}
