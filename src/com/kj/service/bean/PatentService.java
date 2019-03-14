package com.kj.service.bean;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kj.dao.IPatentDao;
import com.kj.dao.ITecInfDao;
import com.kj.domain.TecInformation;
import com.kj.domain.achievement.Patent;
import com.kj.service.IPatentService;
import com.kj.service.ITecInfService;
import com.system.dao.IBaseDao;
import com.system.service.bean.BaseService;

@Service
@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
public class PatentService extends BaseService<Patent> implements IPatentService{
	@Resource private IPatentDao patentDao;
	@Override
	protected IBaseDao<Patent> getBaseDao() {
		// TODO Auto-generated method stub
		return patentDao;
	}
}