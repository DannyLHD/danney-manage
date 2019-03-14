package com.kj.dao;

import java.io.Serializable;

import com.kj.domain.Equipment;
import com.kj.domain.Project;
import com.system.dao.IBaseDao;

public interface IEquipmentDao extends IBaseDao<Equipment>{
	public int updateByUserID(Serializable[] userIds);
}
