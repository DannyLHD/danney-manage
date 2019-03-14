package com.kj.controller.portal;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
import com.system.vo.PageInfo;

@Controller
@RequestMapping("/portal")
public class PortalController {
	@Resource private ITecInfService tecInfService;
	@Resource private IEquipmentService equipmentService;
	@Resource private IUserService userService;
	@Resource private IPatentService patentService;
	@Resource private IStandardService standardService;
	@Resource private IPaperService paperService;
	@Resource private IRewardService rewardService;
	@Resource private IProjectService projectService;

	/**
	 * 科技消息
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/getTecInf")
	public String getTecInf(Model model,String id)
	{
		TecInformation tecInf=null;
		try {
			tecInf=tecInfService.doGetById(id);
		} catch (Exception e) {
			return "";
		}
		model.addAttribute("tecInf", tecInf);
		return "/common/tecInformation/tecInformation";
	}
	@RequestMapping("/getAllTecInformationList")
	public String getAllTecInformationList(HttpServletRequest request,Model model,int type) throws Exception{
		//分页
		PageInfo pageInfo = new PageInfo();
		pageInfo.setSizePerPage(10);
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
		return "/user/portal/allTecInformationList";
	}
	/**
	 * 科技设备
	 * @param model
	 * @return
	 */
	@RequestMapping("/getPortal_Equipment")
	public String getPortal_Equipment(Model model){
		return "/user/portal/equipmentManage";
	}
	@RequestMapping("/getPortal_Project")
	public String getPortal_Project(Model model){
		return "/user/portal/projectManage";
	}
	@RequestMapping("/getPortal_Patent")
	public String getPortal_Patent(Model model){
		return "/user/portal/patentManage";
	}
	@RequestMapping("/getPortal_Paper")
	public String getPortal_Paper(Model model){
		return "/user/portal/paperManage";
	}
	@RequestMapping("/getPortal_Standard")
	public String getPortal_Standard(Model model){
		return "/user/portal/standardManage";
	}
	@RequestMapping("/getPortal_Reward")
	public String getPortal_Reward(Model model){
		return "/user/portal/rewardManage";
	}
	/**
	 * 科技设备
	 * @return
	 */
	@RequestMapping("/getAllEquipment")
	public String getAllEquipment(Model model)
	{
		return "/common/equipment/allEquipment_Basic";
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
		model.addAttribute("equipmentList", equipmentList);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", pageInfo.getTotalPages());
		return "/common/equipment/equipmentBasicList";
	}
	/**
	 * 人员信息
	 * @return
	 */
	@RequestMapping("/getAllUser")
	public String getAllUser(Model model)
	{
		 model.addAttribute("ROLE_AUTH", CodeBookConsts.ROLE_AUTH_ORDINARY);
		return "/common/user/allUser_Basic";
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
			List<User> userList = userService.doGetPageList(pageInfo, dc);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("userList", userList);
			model.addAttribute("totalPages", pageInfo.getTotalPages());
			return "/common/user/userBasicList";
		}
		/**
		 * 专利
		 * @param model
		 * @return
		 */
		@RequestMapping("/getPatent")
		public String getPatent(Model model)
		{
			return "/common/patent/allPatent";
		}
		@RequestMapping("/queryPatent")
		public String queryPatent(HttpServletRequest request,HttpServletResponse response, Model model) throws Exception
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
			String patentName=request.getParameter("patentName");
			if(patentName!=null && !patentName.isEmpty()){
				dc.add(Restrictions.like("patentName", patentName));
			}
			List<Patent> patentList = patentService.doGetPageList(pageInfo, dc);
			model.addAttribute("patentList", patentList);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("totalPages", pageInfo.getTotalPages());
			return "/common/patent/patentList";
		}
		/**
		 * 标准
		 * @param model
		 * @return
		 */
		@RequestMapping("/getStandard")
		public String getStandard(Model model)
		{
			return "/common/standard/allStandard";
		}
		@RequestMapping("/queryStandard")
		public String queryStandard(HttpServletRequest request,HttpServletResponse response, Model model) throws Exception
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
			String standardName=request.getParameter("standardName");
			if(standardName!=null && !standardName.isEmpty()){
				dc.add(Restrictions.like("standardName", standardName));
			}
			List<Standard> standardList = standardService.doGetPageList(pageInfo, dc);
			model.addAttribute("standardList", standardList);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("totalPages", pageInfo.getTotalPages());
			return "/common/standard/standardList";
		}
		/**
		 * 论文
		 * @param model
		 * @return
		 */
		@RequestMapping("/getPaper")
		public String getPaper(Model model)
		{
			return "/common/paper/allPaper";
		}
		@RequestMapping("/queryPaper")
		public String queryPaper(HttpServletRequest request,HttpServletResponse response, Model model) throws Exception
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
			String paperName=request.getParameter("paperName");
			if(paperName!=null && !paperName.isEmpty()){
				dc.add(Restrictions.like("paperName", paperName));
			}
			List<Paper> paperList = paperService.doGetPageList(pageInfo, dc);
			model.addAttribute("paperList", paperList);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("totalPages", pageInfo.getTotalPages());
			return "/common/paper/paperList";
		}
		/**
		 * 科技奖
		 * @param model
		 * @return
		 */
		@RequestMapping("/getRewardBasicList")
		public String getRewardBasicList(HttpServletRequest request, Model model) throws Exception
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
			String rewardname=request.getParameter("rewardName");
			String returnUrl=request.getParameter("returnUrl");
			if(rewardname!=null && !rewardname.isEmpty()){
				dc.add(Restrictions.like("rewardName", rewardname));
			}
			dc.add(Restrictions.eq("status", CodeBookConsts.STATUS_SUBMIT));
			dc.add(Restrictions.eq("check_head_admin", CodeBookConsts.CHECK_APPROVED));
			List<Reward> rewardList = rewardService.doGetPageList(pageInfo, dc);
			model.addAttribute("rewardList", rewardList);
			model.addAttribute("totalPages", pageInfo.getTotalPages());
			if(returnUrl!=null && !returnUrl.isEmpty()){
				return returnUrl;
			}
			return "/common/reward/rewardBasicList";
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
}
