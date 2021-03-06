package com.system.service.bean;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.system.dao.IBaseDao;
import com.system.domain.BaseDomain;
import com.system.service.IBaseService;
import com.system.vo.PageInfo;


/**
 * 基础服务
 * @author chen
 *
 * @param <T>
 */
@Service
@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
public abstract class BaseService<T extends BaseDomain> implements IBaseService<T>{
	
	protected abstract IBaseDao<T> getBaseDao();
	protected DetachedCriteria defaultDetachedCriteria;
	
	@SuppressWarnings("unchecked")
	public BaseService() {
		Type type = getClass().getGenericSuperclass();
		if (type instanceof ParameterizedType) {  
			Class<T> entityClass = (Class<T>) ((ParameterizedType) type).getActualTypeArguments()[0];
			this.defaultDetachedCriteria = DetachedCriteria.forClass(entityClass);
		} else {  
			this.defaultDetachedCriteria = null;  
		}
	}
	/**
	 * @see IBaseService#doDeleteByIds(Serializable[] ids)
	 */
	@Override
	public boolean doDeleteByIds(Serializable[] ids) throws Exception {
		return getBaseDao().deleteByIds(ids);
	}
	/**
	 * @see IBaseService#doSave(Object)
	 */
	@Override
	public boolean doSave(T t) throws Exception {
		//判断是否为新用户，如果是，新增，否则更新
		if(t.getId()==null || t.getId().isEmpty()){
			return ! getBaseDao().save(t).equals("");
		}else{
			return getBaseDao().update(t);
		}
	}
	/**
	 * @see IBaseService#doUpdate(Object)
	 */
	@Override
	public boolean doUpdate(T t) throws Exception {
		return getBaseDao().update(t);
	}

	/**
	 * @see IBaseService#doGetById(Serializable)
	 */
	@Override
	public T doGetById(Serializable id) throws Exception {
		return getBaseDao().getById(id);
	}
	/**
	 * @see IBaseService#doGetFilterList(DetachedCriteria)
	 */
	@Override
	public List<T> doGetFilterList(DetachedCriteria detachedCriteria) throws Exception {
		if(detachedCriteria == null){
			detachedCriteria = defaultDetachedCriteria;
		}
		return getBaseDao().getFilterList(detachedCriteria);
	}
	/**
	 * @see IBaseService#doGetPageList(DetachedCriteria, PageInfo)
	 */
	@Override
	public List<T> doGetPageList(PageInfo pageInfo, DetachedCriteria detachedCriteria) throws Exception {
		if(detachedCriteria == null){
			detachedCriteria = defaultDetachedCriteria;
		}
		return getBaseDao().getPageList(detachedCriteria, pageInfo);
	}
	public int doGetTotalCount(DetachedCriteria detachedCriteria) {
		return getBaseDao().getTotalCount(detachedCriteria);
	}
	
//调用时需要在子类加入下面方法
//	/**
//	 * 返回基类baseDao
//	 */
//	@Override
//	public IBaseDao<UserDomain> getBaseDao() {
//		return (IBaseDao<UserDomain>) userDao;
//	}
//	/**
//	 * 返回DetachedCriteria
//	 */
//	@Override
//	protected DetachedCriteria getDetachedCriteria(){
//		return DetachedCriteria.forClass(UserDomain.class);
//	}
}
