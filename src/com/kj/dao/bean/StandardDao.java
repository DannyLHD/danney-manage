package com.kj.dao.bean;

import org.springframework.stereotype.Repository;

import com.kj.dao.IStandardDao;
import com.kj.dao.ITecInfDao;
import com.kj.domain.TecInformation;
import com.kj.domain.achievement.Standard;
import com.system.dao.bean.BaseDao;
@Repository
public class StandardDao extends BaseDao<Standard> implements IStandardDao{

}
