package com.kj.service.bean;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kj.dao.IStandardDao;
import com.kj.dao.ITecInfDao;
import com.kj.domain.TecInformation;
import com.kj.domain.achievement.Standard;
import com.kj.service.IStandardService;
import com.kj.service.ITecInfService;
import com.system.dao.IBaseDao;
import com.system.service.bean.BaseService;

@Service
@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
public class StandardService extends BaseService<Standard> implements IStandardService{
	@Resource private IStandardDao standardDao;
	@Override
	protected IBaseDao<Standard> getBaseDao() {
		// TODO Auto-generated method stub
		return standardDao;
	}
}