package com.kj.service.bean;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kj.dao.IRewardDao;
import com.kj.dao.ITecInfDao;
import com.kj.domain.TecInformation;
import com.kj.domain.achievement.Reward;
import com.kj.service.IRewardService;
import com.kj.service.ITecInfService;
import com.system.dao.IBaseDao;
import com.system.service.bean.BaseService;

@Service
@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
public class RewardService extends BaseService<Reward> implements IRewardService{
	@Resource private IRewardDao rewardDao;
	@Override
	protected IBaseDao<Reward> getBaseDao() {
		// TODO Auto-generated method stub
		return rewardDao;
	}
}