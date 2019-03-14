package com.kj.dao.bean;

import org.springframework.stereotype.Repository;

import com.kj.dao.ITecInfDao;
import com.kj.domain.TecInformation;
import com.system.dao.bean.BaseDao;
@Repository
public class TecInfDao extends BaseDao<TecInformation> implements ITecInfDao{

}
