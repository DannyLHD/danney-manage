package com.kj.controller.user;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kj.domain.User;
import com.kj.domain.UserRole;
import com.kj.service.IEquipmentService;
import com.kj.service.IProjectService;
import com.kj.service.IUserService;
import com.kj.util.CodeBookConsts;
import com.kj.util.Consts;
import com.kj.util.ExcelInput;
import com.system.util.EndecryptUtils;


/**
 * 登录控制层
 * @author administrator
 *
 */
@Controller
@RequestMapping("/super")
public class SuperAdminController {
	@Autowired private IUserService userService;
	@Autowired private IProjectService projectService;
	@Autowired private IEquipmentService equipmentService;
	
	@Value("#{envProperties['smsCodeMsg']}") private String smsCodeMsg;
	@Value("#{envProperties['uploadPath']}") private String shareupload;
	@Value("#{envProperties['excelTemp']}") private String excelTemp;
	
	
	/**
	 *超级用户登录首页
	 * @param model
	 * @return
	 */
	@RequestMapping("/superIndex")
	public String getSupIndex(Model model){
		return "/user/superAdmin/superIndex";
	}
	/**
	 * 用户管理
	 * @param model
	 * @return
	 */
   @RequestMapping("/userManage")
   public String getUserManage(Model model){
	   return "/user/superAdmin/userManage";
   }
   
 
	
	/*
	新增用户 
	*/
	@RequestMapping("/toAddUser")
	public String getAddUserPage(Model model,String role_auth){
		model.addAttribute("ROLE_AUTH", role_auth);
	return "/common/user/addUser";
	}
	@RequestMapping("/addUser")
	@ResponseBody
	public boolean addUser(@ModelAttribute("domain") User user,Model model,String role_auth) throws Exception
	{
		boolean data=false;
		if ( user.getPhone()==null||user.getUsername()==null || user.getPassword()==null ||user.getPhone().isEmpty()|| user.getUsername().isEmpty() || user.getPassword().isEmpty()) {// 如果校验失败,则返回
			data=false;
		} else {
			user.setId(null);
			user.setPassword(EndecryptUtils.md5(user.getPassword()));
			user.setPhone(user.getPhone());
			UserRole role = new UserRole();
			role.setAuthority(Integer.parseInt(role_auth));
			user.setRole(role);
			try {
				if(userService.doSave(user)){
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
	@RequestMapping("/inputUser")
	@ResponseBody
	public boolean inputUser(String role_auth,Model model,HttpServletRequest request) throws Exception{
		boolean data=false;
		UserRole role = new UserRole();
		role.setAuthority(Integer.parseInt(role_auth));
		/*String fileName=request.getParameter("filename");
		if(fileName==null || fileName.isEmpty()){
			return false;
		}*/
		String path="D:/用户.xlsx";
		List<User> list=ExcelInput.readXlsx(path);
		for(User user:list){
			user.setRole(role);
			User user2=userService.doGetUserByPhone(user.getPhone());
			if(user2!=null&&!user2.equals("")){
				user.setId(user2.getId());
				if(userService.doUpdate(user)){
					data=true;
				}
			}
			else if(userService.doSave(user)){
				data=true;
			}
		}
		return data;
	}
	/**
	 * 修改用户
	 * @param user
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/editUser")
	public String editUser(String id,Model model) throws Exception{
	User user = userService.doGetById(id); 
	model.addAttribute("user", user);
	return "/common/user/editUser";
	}
	@RequestMapping("/updateUser")
	@ResponseBody
	public boolean doUpdate(@Valid @ModelAttribute("domain") User user,Model model) throws Exception{
		boolean data=false;
		user.setUsername(null);
		user.setPhone(null);
		user.setPassword(null);
		user.setRole(null);
		if(userService.doUpdate(user)){
			data=true;
		}
		return data;
	}
	/*
	删除用户信息
	*/
	@RequestMapping("/delUser")
	@ResponseBody
	public String  delUser(@RequestParam String id,Model model) throws Exception
	{
	String result = Consts.MSG_FAILURE; 
	String[] ids = {id};
/*	for(String userId:ids){
		DetachedCriteria dc = DetachedCriteria.forClass(Equipment.class);
		dc.add(Restrictions.like("equipmentName", equipmentName));
		List<Equipment> equipmentList = equipmentService.doGetPageList(pageInfo, dc);
	}*/
	if(userService.doDeleteByIds(ids))
       { 
		result = Consts.MSG_SUCCESS;
		}
	return result;
	}
	/**
	 * 科技信息管理
	 * @return
	 */
	@RequestMapping("/tecInfManage")
	public String  getTecInfManage(){
		return "/user/superAdmin/tecInfManage";
	}
	/**
	 * 科技设备管理
	 * @return
	 */
	@RequestMapping("/equipmentManage")
	public String  getEquipmentManage(){
		return "/user/superAdmin/equipmentManage";
	}
	@RequestMapping("/projectManage")
	public String  getProjectManage(){
		return "/user/superAdmin/projectManage";
	}
	
	/* * 
	 * 个人信息管理
	 * 
	 * */
	@RequestMapping("/selfMesManage")
	public String getSelfMesManage(Model model){
		return "/user/superAdmin/selfMesManage";
	}
	/**
	 * 知识产权管理
	 * @param model
	 * @return
	 */
	@RequestMapping("/achievementManage")
	public String getAchievementManage(Model model){
		return "/user/superAdmin/achievementManage";
	}

	/**
	 * 导入人员信息
	 * @param file
	 * @return
	 */
	@RequestMapping("/importUserInfo")
	@ResponseBody
	public boolean importUserInfo(@RequestParam(value = "file", required = false) MultipartFile file){
		String fileType = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."),
				file.getOriginalFilename().length());
		String fileRealName = file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf("."));
		String fileName = fileRealName + "_" + System.currentTimeMillis() + fileType;
		String path = shareupload + excelTemp +File.separator;
		File targetFile = new File(path, fileName);
		if(!targetFile.exists()){
			targetFile.mkdirs();
		}
		targetFile.setWritable(true, false);
		boolean isSuccess = true;
		try{
			//保存文件
			file.transferTo(targetFile);
			List<User> userList = null;
			if(fileType.equals(".xls")){
				userList = ExcelInput.readXls(path+fileName);
			}else if(fileType.equals(".xlsx")){
				userList = ExcelInput.readXlsx(path+fileName);
			}else{
				return false;
			}
			if(userList!=null && userList.size()>0){
				for(User user:userList){
					UserRole role = new UserRole();
					role.setAuthority(CodeBookConsts.ROLE_AUTH_ORDINARY);
					user.setRole(role);
					User user2=userService.doGetUserByPhone(user.getPhone());
					if(user2!=null&&!user2.equals("")){
						user.setId(user2.getId());
						if(!userService.doUpdate(user)){
							isSuccess=false;
							break;
						}
					}else if(!userService.doSave(user)){
						isSuccess=false;
						break;
					}
				}
			}
			targetFile.delete();
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
		return isSuccess;
	}
}
