package com.kj.dao.bean;

import org.springframework.stereotype.Repository;

import com.kj.dao.IPatentDao;
import com.kj.dao.ITecInfDao;
import com.kj.domain.TecInformation;
import com.kj.domain.achievement.Patent;
import com.system.dao.bean.BaseDao;
@Repository
public class PatentDao extends BaseDao<Patent> implements IPatentDao{

}
