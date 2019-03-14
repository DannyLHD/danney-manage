package com.kj.controller;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.kj.domain.Result;
import com.kj.service.IResultService;
import com.kj.util.Consts;
import com.system.vo.PageInfo;

@Controller
@RequestMapping("/result")
public class ResultController {
@Resource private IResultService resultService;
/*
获取所有用户
*/
@RequestMapping("/allResult")
public String getAllResult(Model model)
{
	return "/result/allResult";
}
/*
删除用户信息
*/
@RequestMapping("/delResult")
@ResponseBody
public String  delResult(@RequestParam String id,Model model) throws Exception
{
String result = Consts.MSG_FAILURE; 
String[] ids = {id};
if(resultService.doDeleteByIds(ids))
   { 
	result = Consts.MSG_SUCCESS;
	}
return result;
}
/*
新增用户 
*/
@RequestMapping("/toAddResult")
public String getAddResultPage(){
return "/result/addResult";
}

@RequestMapping("/addResult")
@ResponseBody
public boolean addResult(@ModelAttribute("domain") Result result,Model model) throws Exception
{
	return resultService.doSave(result); 
}
/*
修改用户信息
*/
@RequestMapping("/editResult")
public String editResult(String id,Model model) throws Exception{
Result result = resultService.doGetById(id); 
model.addAttribute("result", result);
return "/result/editResult";
}

/**
 * 展示用户
 * @param request
 * @param model
 * @return
 * @throws Exception
 */
@RequestMapping("/queryResult")
public String queryResult(HttpServletRequest request, Model model) throws Exception
{
	//分页
	PageInfo pageInfo = new PageInfo();
	pageInfo.setSizePerPage(10);
	String currentPage=request.getParameter("currentPage");
	if(currentPage!=null && !currentPage.isEmpty()){
		pageInfo.setCurrentPageNo(Integer.parseInt(currentPage));
	}
	//查询条件
	DetachedCriteria dc = DetachedCriteria.forClass(Result.class);
	String resultname=request.getParameter("resultname");
	if(resultname!=null && !resultname.isEmpty()){
		dc.add(Restrictions.like("resultname", resultname));
	}
	List<Result> resultList = resultService.doGetPageList(pageInfo, dc);
	model.addAttribute("resultList", resultList);
	model.addAttribute("totalPages", pageInfo.getTotalPages());
	return "/result/resultList";
}
@RequestMapping("/toSelectProject")
public String selectProject(Model model){
	return "/result/selectProject";
}
}
