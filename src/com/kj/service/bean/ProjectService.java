package com.kj.service.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kj.dao.IProjectDao;
import com.kj.dao.IProject_ApplicationDao;
import com.kj.dao.IRewardDao;
import com.kj.domain.Project;
import com.kj.service.IProjectService;
import com.system.dao.IBaseDao;
import com.system.service.bean.BaseService;
@Service
@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
public class ProjectService extends BaseService<Project> implements IProjectService{

	@Resource private IProjectDao projectDao;
	@Resource private IRewardDao rewardDao;
	@Resource private IProject_ApplicationDao project_ApplicationDao;
	protected IBaseDao<Project> getBaseDao() {
		return projectDao;
	}
	
	@Override
	public boolean doDeleteByIds(Serializable[] ids)
	{
		Map<String, Serializable[]> appForeignMap = new HashMap<String, Serializable[]>();
		appForeignMap.put("project.id", ids);
		List<String> projectForeignList = new ArrayList<String>();
		projectForeignList.add("project_Application.id");
		rewardDao.deleteByForeigns(appForeignMap);
		return projectDao.setForeignsNullByIds(ids, projectForeignList)&&
				project_ApplicationDao.deleteByForeigns(appForeignMap)&&
				projectDao.deleteByIds(ids);
	}

}
