package com.kj.service.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kj.dao.IProject_ApplicationDao;
import com.kj.domain.Project_Application;
import com.kj.service.IProject_ApplicationService;
import com.system.dao.IBaseDao;
import com.system.service.bean.BaseService;
@Service
@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
public class Project_ApplicationService extends BaseService<Project_Application> implements IProject_ApplicationService{

	@Resource private IProject_ApplicationDao project_ApplicationDao;
	protected IBaseDao<Project_Application> getBaseDao() {
		return project_ApplicationDao;
	}
	
	/*@Override
	public boolean doDeleteByIds(Serializable[] ids)
	{
		Map<String, Serializable[]> foreignMap = new HashMap<String, Serializable[]>();
		foreignMap.put("project.id", ids);
		return resultDao.deleteByForeigns(foreignMap)&&projectDao.deleteByIds(ids);
	}*/

}
