package com.kj.dao.bean;

import org.springframework.stereotype.Repository;

import com.kj.dao.IRewardDao;
import com.kj.dao.ITecInfDao;
import com.kj.domain.TecInformation;
import com.kj.domain.achievement.Reward;
import com.system.dao.bean.BaseDao;
@Repository
public class RewardDao extends BaseDao<Reward> implements IRewardDao{

}
