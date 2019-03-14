package com.kj.service.bean;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kj.dao.ITecInfDao;
import com.kj.domain.TecInformation;
import com.kj.service.ITecInfService;
import com.system.dao.IBaseDao;
import com.system.service.bean.BaseService;

@Service
@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
public class TecInfService extends BaseService<TecInformation> implements ITecInfService{
	@Resource private ITecInfDao tecInfDao;
	@Override
	protected IBaseDao<TecInformation> getBaseDao() {
		// TODO Auto-generated method stub
		return tecInfDao;
	}
}