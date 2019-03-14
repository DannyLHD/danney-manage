package com.kj.service.bean;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kj.dao.IPaperDao;
import com.kj.dao.ITecInfDao;
import com.kj.domain.TecInformation;
import com.kj.domain.achievement.Paper;
import com.kj.service.IPaperService;
import com.kj.service.ITecInfService;
import com.system.dao.IBaseDao;
import com.system.service.bean.BaseService;

@Service
@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
public class PaperService extends BaseService<Paper> implements IPaperService{
	@Resource private IPaperDao paperDao;
	@Override
	protected IBaseDao<Paper> getBaseDao() {
		// TODO Auto-generated method stub
		return paperDao;
	}
}