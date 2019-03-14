package com.kj.dao.bean;

import java.io.Serializable;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.kj.dao.IEquipmentDao;
import com.kj.domain.Equipment;
import com.kj.domain.Project;
import com.system.dao.bean.BaseDao;

@Repository
public class EquipmentDao extends BaseDao<Equipment> implements IEquipmentDao{
	public int updateByUserID(Serializable[] userIds){
		String hql = "update Equipment t set user=null where t.user.id in (:ids)";  
    	Query query = getSession().createQuery(hql);
    	query.setParameterList("ids", userIds);
    	return query.executeUpdate();
	}
}
