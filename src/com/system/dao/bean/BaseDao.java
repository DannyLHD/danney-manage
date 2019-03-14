package com.system.dao.bean;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import javax.persistence.Table;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.CriteriaImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.system.dao.IBaseDao;
import com.system.domain.BaseDomain;
import com.system.util.BeanCopyUtil;
import com.system.vo.PageInfo;
@Repository
public class BaseDao<T extends BaseDomain> implements IBaseDao<T>
{
	@Autowired private SessionFactory sessionFactory;
	protected Class<T> entityClass;
	protected String tableName;
	
	 @SuppressWarnings("unchecked")
	public BaseDao() {
		 Type type = getClass().getGenericSuperclass();
		  if (type instanceof ParameterizedType) {  
				this.entityClass = (Class<T>) ((ParameterizedType) type).getActualTypeArguments()[0];
		        Annotation[] annotations = entityClass.getAnnotations(); 
		        for (Annotation annotation : annotations) {  
		            if (annotation instanceof Table) {  
		                tableName = ((Table) annotation).name();  
		            }  
		        }   
			} else {  
				this.entityClass = null;  
			}
	}
	 public SessionFactory getSessionFactory() {
			return sessionFactory;
		}

		public void setSessionFactory(SessionFactory sessionFactory) {
			this.sessionFactory = sessionFactory;
		}
	 /**
		 * 获取session
		 * @return
		 */
	protected Session getSession() {
		Session session = sessionFactory.getCurrentSession();
		return sessionFactory.getCurrentSession();
	}
	 /**
	  * @see根据Ids删除
	  */
	 public boolean deleteByIds(Serializable[] ids) {
		if(ids.length<=0){
			return true;
		}
        if (entityClass != null && entityClass.getName()!=null && entityClass.getName() != "") {  
        	String hql = "delete from " + entityClass.getName() + " t where t.id in (:ids)";  
        	Query query = getSession().createQuery(hql);
        	query.setParameterList("ids", ids);
        	query.executeUpdate();
            return true;
        }else{
        	return false;
        }
	}
	 /**
	  * 保存
	  * @param t
	  * @return
	  */
	 public Serializable save(T t) {
		 Session session=getSession();
			try{
				session.save(t);
				session.flush();
			}catch(Exception e){
				e.printStackTrace();
				return "";
			}
			return t.getId();
	 }
	 /**
	  * 更新
	  * @param t
	  * @return
	  */
	 @SuppressWarnings("unchecked")
	public boolean update(T t) {
		 Session session=getSession();
			try{
				//属性为null不更新
				T sourceT = (T)session.get(entityClass, t.getId());
				BeanCopyUtil beanCopyUtil = new BeanCopyUtil();
				beanCopyUtil.copyProperties(sourceT, t, true);
				//如果数据库中有该记录,则更新该记录,如果不存在该记录,则进行insert操作
				session.merge(sourceT);
				session.flush();
			}catch(Exception e){
				e.printStackTrace();
				return false;
			}	
			return true;
	 }
	 /**
	  * @see根据id获取
	  */
	 @SuppressWarnings("unchecked")
	 public T getById(Serializable id) {
			if (id == null){
				return null;
			}
			Session session=getSession();
			T t=(T)session.get(entityClass, id);
			return t;
	 }
	 /**
	 * @see IBaseDao#getPageList(DetachedCriteria, PageInfo)
   */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> getPageList(DetachedCriteria detachedCriteria,PageInfo pageInfo) {
		if(pageInfo==null){
			pageInfo=new PageInfo();
		}
	
		Session session=getSession();
		Criteria criteria=detachedCriteria.getExecutableCriteria(session);
		CriteriaImpl criteriaImpl=(CriteriaImpl)criteria;
		Projection projection=criteriaImpl.getProjection();
		//每页显示几条
		int pagePerSize=pageInfo.getSizePerPage();
		//计算总条目数
		int totalCount=0;
		try {
			totalCount = ((Long)criteria.setProjection(Projections.countDistinct("id")).uniqueResult()).intValue();
			pageInfo.setTotalPages(totalCount/pagePerSize + 1);
			pageInfo.setTotalCount(totalCount);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
		}
		//计算分页数
		//ceil返回大于等于且最接近的那个
		pageInfo.setTotalPages((int)Math.ceil(((double)totalCount/pagePerSize)));
		
		if(pageInfo.getCurrentPageNo()>pageInfo.getTotalPages()){
			pageInfo.setCurrentPageNo(pageInfo.getTotalPages());
		}
		//起始序号
		int startIndex=(pageInfo.getCurrentPageNo()-1)*pagePerSize;
		
		criteria.setProjection(projection);
		if(projection==null){
			criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
			//criteria.setResultTransformer(criteria.DISTINCT_ROOT_ENTITY);//去重
		}
		
		//进行分页查询
		criteria.setFirstResult(startIndex).setMaxResults(pagePerSize);
		List<T> list=criteria.list();
		
		return list;
	}
		/**
		 * 根据过滤条件查询
		 */
		@SuppressWarnings("unchecked")
		@Override
		public List<T> getFilterList(DetachedCriteria detachedCriteria) {
			// TODO Auto-generated method stub
			Session session=getSession();
			List<T> list=detachedCriteria.getExecutableCriteria(session).list();
			//this.closeSession(session);
			return list;
		}
		public int getTotalCount(DetachedCriteria detachedCriteria) {
			// TODO Auto-generated method stub
			detachedCriteria.add(Restrictions.eq("isDelete", false));
			Session session=getSession();
			Criteria criteria=detachedCriteria.getExecutableCriteria(session);
			int totalCount=0;
			try {
				totalCount = ((Long)criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
			} catch (HibernateException e) {
				// TODO Auto-generated catch block
			}
			return totalCount;
		}
		
	public boolean deleteByForeigns(Map<String, Serializable[]> foreignMap) {
		if(foreignMap==null || foreignMap.size()<=0){
			return true;
		}
    	String hql = "delete from " + entityClass.getName() + " t where ";
    	String whereHql = "";
		for (String propertyName : foreignMap.keySet()) {
			if(foreignMap.get(propertyName)!=null && foreignMap.get(propertyName).length>0){
				whereHql += "t." + propertyName + " in (:"+propertyName.replaceAll("\\.", "")+") and ";
			}
		}
		if(whereHql.equals("")){
			return true;
		}else{
			whereHql = whereHql.substring(0, whereHql.length()-5);  
        	Query query = getSession().createQuery(hql+whereHql);
    		for (String propertyName : foreignMap.keySet()) {
	        	query.setParameterList(propertyName.replaceAll("\\.", ""), foreignMap.get(propertyName));
    		}
        	query.executeUpdate();
			return true;
		}
	}
	
	@Override
	public boolean setForeignsNullByIds(Serializable[] ids, List<String> foreignKey) {
		if(ids==null || ids.length<=0){
			return true;
		}
    	String hql = "update " + entityClass.getName() + " t ";
    	String setHql = "";
    	String whereHql = "t.id in (:ids) ";
		for (String propertyName : foreignKey) {
			setHql += "t." + propertyName +"=null and ";
		}
		if(setHql.isEmpty()){
			return true;
		}else{
			setHql = setHql.substring(0, setHql.length()-5);  
        	Query query = getSession().createQuery(hql+" set "+setHql+" where "+whereHql);
        	query.setParameterList("ids", ids);
        	query.executeUpdate();
			return true;
		}
	}
	
}
