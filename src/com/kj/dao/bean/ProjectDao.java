package com.kj.dao.bean;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.kj.dao.IProjectDao;
import com.kj.domain.Project;
import com.system.dao.bean.BaseDao;
@Repository
public class ProjectDao extends BaseDao<Project> implements IProjectDao{

	@Override
	public Serializable[] getIdsByUserID(Serializable[] userIds) {
		String hql = "select id from Project t where t.user.id in (:ids)";  
    	Query query = getSession().createQuery(hql);
    	query.setParameterList("ids", userIds);
    	List queryList = query.list();
    	return (Serializable[])queryList.toArray(new Serializable[queryList.size()]);
	}
	public int updateByUserID(Serializable[] userIds){
		String hql = "update Project t set user=null where t.user.id in (:ids)";  
    	Query query = getSession().createQuery(hql);
    	query.setParameterList("ids", userIds);
    	return query.executeUpdate();
	}
}
