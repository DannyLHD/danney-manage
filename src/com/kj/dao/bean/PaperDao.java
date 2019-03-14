package com.kj.dao.bean;

import org.springframework.stereotype.Repository;

import com.kj.dao.IPaperDao;
import com.kj.dao.ITecInfDao;
import com.kj.domain.TecInformation;
import com.kj.domain.achievement.Paper;
import com.system.dao.bean.BaseDao;
@Repository
public class PaperDao extends BaseDao<Paper> implements IPaperDao{

}
