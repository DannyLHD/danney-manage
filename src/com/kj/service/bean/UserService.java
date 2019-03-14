package com.kj.service.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kj.dao.IEquipmentDao;
import com.kj.dao.IProjectDao;
import com.kj.dao.IUserDao;
import com.kj.domain.Project;
import com.kj.domain.User;
import com.kj.service.IProjectService;
import com.kj.service.IUserService;
import com.system.dao.IBaseDao;
import com.system.service.bean.BaseService;
import com.system.util.EndecryptUtils;
import com.system.util.ValidateUtil;

/**
 * 账户服务层
 *
 */
@Service
@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
public class UserService extends BaseService<User> implements IUserService{
	@Resource private IUserDao userDao;
	@Resource private IProjectDao projectDao;
	@Resource private IEquipmentDao equipmentDao;
	@Resource private IProjectService projectService;
	@Override
	protected IBaseDao<User> getBaseDao() {
		return userDao;
	}
	@Override
	public boolean doCheckUserPassword(String phone, char[] password)
			throws Exception {
		if(ValidateUtil.isEmpty(phone)){
			return false;
		}
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(User.class);
		detachedCriteria.add(Restrictions.eq("phone", phone.trim()));
		
		List<User> userList=userDao.getFilterList(detachedCriteria);
		
		//如果有结果，username是唯一的
		if(userList.size()==1){
			User user=userList.get(0);
			//判断密码是否等于
			if(EndecryptUtils.md5((String.valueOf(password))).equals(user.getPassword())){
				return true;
			}
		}
		
		return false;
	}

	/**
	 * @see IUserService#doGetUserByUsername(String)
	 */
	@Override
	public User doGetUserByPhone(String phone) throws Exception {
		if(phone==null || phone.isEmpty()){
			return null;
		}
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(User.class);
		detachedCriteria.add(Restrictions.eq("phone", phone.trim()));
		
		List<User> userList=userDao.getFilterList(detachedCriteria);
		
		//如果有结果，username是唯一的
		if(userList.size()==1){
			User user=userList.get(0);
			return user;
		}
		return null;
	}
	public boolean doDeleteByIds(Serializable[] ids){
		int project_num=projectDao.updateByUserID(ids);
		int equipment_num=equipmentDao.updateByUserID(ids);
		return userDao.deleteByIds(ids);
	}
	/*@Override
	public boolean doDeleteByIds(Serializable[] ids) throws Exception
	{
		boolean data=false;
		Map<String, Serializable[]> foreignMap = new HashMap<String, Serializable[]>();
		foreignMap.put("user.id", ids);
		Serializable[] aaaaSerializables = projectDao.getIdsByUserID(ids);
		
		if(projectService.doDeleteByIds(aaaaSerializables)&&equipmentDao.deleteByForeigns(foreignMap)&&userDao.deleteByIds(ids)){
			data=true;
		}
		 return data;
	}*/
}
