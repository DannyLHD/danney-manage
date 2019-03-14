package com.system.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;

import com.system.domain.BaseDomain;
import com.system.vo.PageInfo;


public interface IBaseDao<T extends BaseDomain> {
	/**
	 * 根据id物理删除实体
	 * @param id
	 */
	public boolean deleteByIds(Serializable[] ids);
	/**
	 * 根据外键删除实体
	 * @param foreignMap 外键名--外键值
	 */
	public boolean deleteByForeigns(Map<String, Serializable[]> foreignMap);
	/**
	 * 根据id设置外键为Null
	 * @param ids 需要设置null的项
	 * @param foreignKey 外键名
	 */
	public boolean setForeignsNullByIds(Serializable[] ids, List<String> foreignKey);
	/**
	 * 保存
	 * @param t
	 * @return
	 */
	public Serializable save(T t);
	/**
	 * 更新
	 * @param t
	 * @return
	 */
	public boolean update(T t);
	/**
	 * 根据id获取实体
	 * @param id
	 * @return
	 */
	public T getById(Serializable id);
	/**
	 * 翻页查询list
	 * @param detachedCriteria
	 * @param pageInfo
	 * @return
	 */
	public List<T> getPageList(DetachedCriteria detachedCriteria,PageInfo pageInfo);
	/**
	 * 根据过滤条件查询所有实体，并返回list
	 * @param detachedCriteria
	 * @return
	 */
   public List<T> getFilterList(DetachedCriteria detachedCriteria);
   /**
	 * 获取数目
	 * @param detachedCriteria
	 * @return
	 */
	public int getTotalCount(DetachedCriteria detachedCriteria);
	
}
