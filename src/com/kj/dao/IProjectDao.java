package com.kj.dao;

import java.io.Serializable;
import java.util.List;

import com.kj.domain.Project;
import com.system.dao.IBaseDao;

public interface IProjectDao extends IBaseDao<Project>{

	public Serializable[] getIdsByUserID(Serializable[] userIds);
	public int updateByUserID(Serializable[] userIds);
}
