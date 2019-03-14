package com.kj.dao.bean;

import org.springframework.stereotype.Repository;

import com.kj.dao.IResultDao;
import com.kj.domain.achievement.Reward;
import com.system.dao.bean.BaseDao;
@Repository
public class ResultDao extends BaseDao<Reward> implements IResultDao{

}
