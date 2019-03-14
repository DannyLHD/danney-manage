package com.kj.service.bean;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kj.dao.IProject_ExecutionDao;
import com.kj.domain.Project_Execution;
import com.kj.service.IProject_ExecutionService;
import com.system.dao.IBaseDao;
import com.system.service.bean.BaseService;
@Service
@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
public class Project_ExecutionService extends BaseService<Project_Execution> implements IProject_ExecutionService{

	@Resource private IProject_ExecutionDao project_ExecutionDao;
	protected IBaseDao<Project_Execution> getBaseDao() {
		return project_ExecutionDao;
	}
	
	/*@Override
	public boolean doDeleteByIds(Serializable[] ids)
	{
		Map<String, Serializable[]> foreignMap = new HashMap<String, Serializable[]>();
		foreignMap.put("project.id", ids);
		return resultDao.deleteByForeigns(foreignMap)&&projectDao.deleteByIds(ids);
	}*/

}
