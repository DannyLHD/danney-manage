package com.kj.service.bean;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kj.dao.IResultDao;
import com.kj.domain.achievement.Reward;
import com.kj.service.IResultService;
import com.system.dao.IBaseDao;
import com.system.service.bean.BaseService;
@Service
@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
public class ResultService extends BaseService<Reward> implements IResultService{
	@Resource private IResultDao resultDao;
	protected IBaseDao<Reward> getBaseDao() {
		return resultDao;
	}
}
