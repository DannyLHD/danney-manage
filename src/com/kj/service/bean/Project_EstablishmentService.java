package com.kj.service.bean;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kj.dao.IProject_EstablishmentDao;
import com.kj.domain.Project_Establishment;
import com.kj.service.IProject_EstablishmentService;
import com.system.dao.IBaseDao;
import com.system.service.bean.BaseService;
@Service
@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
public class Project_EstablishmentService extends BaseService<Project_Establishment> implements IProject_EstablishmentService{

	@Resource private IProject_EstablishmentDao project_EstablishmentDao;
	protected IBaseDao<Project_Establishment> getBaseDao() {
		return project_EstablishmentDao;
	}
	
	/*@Override
	public boolean doDeleteByIds(Serializable[] ids)
	{
		Map<String, Serializable[]> foreignMap = new HashMap<String, Serializable[]>();
		foreignMap.put("project.id", ids);
		return resultDao.deleteByForeigns(foreignMap)&&projectDao.deleteByIds(ids);
	}*/

}
