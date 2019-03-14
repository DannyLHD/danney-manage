package com.kj.service.bean;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kj.dao.IProject_AcceptanceDao;
import com.kj.domain.Project_Acceptance;
import com.kj.service.IProject_AcceptanceService;
import com.system.dao.IBaseDao;
import com.system.service.bean.BaseService;
@Service
@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
public class Project_AcceptanceService extends BaseService<Project_Acceptance> implements IProject_AcceptanceService{

	@Resource private IProject_AcceptanceDao project_AcceptanceDao;
	protected IBaseDao<Project_Acceptance> getBaseDao() {
		return project_AcceptanceDao;
	}
	
	/*@Override
	public boolean doDeleteByIds(Serializable[] ids)
	{
		Map<String, Serializable[]> foreignMap = new HashMap<String, Serializable[]>();
		foreignMap.put("project.id", ids);
		return resultDao.deleteByForeigns(foreignMap)&&projectDao.deleteByIds(ids);
	}*/

}
