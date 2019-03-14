package com.kj.dao.bean;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.kj.dao.UserDao;
import com.kj.domain.User;

@Repository
public class UserDaoImpl implements UserDao
{
	@Resource SessionFactory sessionFactory; 
	private int pageSize = 10;
	public void setSessionFactory(SessionFactory sessionFactory)
	{
	this.sessionFactory = sessionFactory; 
	}
	/**
	* 获取所有用户
	*/
	public List<User> allUser(String page)
	{ 
	String hql=" from User ";
	Query query =sessionFactory.getCurrentSession().createQuery(hql);
	query.setFirstResult((Integer.parseInt(page)-1)*pageSize);
	query.setMaxResults(pageSize);
	return query.list();
	}
	/**
	* 根据id查找用户
	*/
	public User findById(String id) 
	{
	String hql = "from User u where u.id=? "; 
	Query query =sessionFactory.getCurrentSession().createQuery(hql);
	query.setString(0, id);
	return (User)query.uniqueResult();
	}
	/**
	* 新增用户
	*/
	public void addUser(User user) 
	{ 
		sessionFactory.getCurrentSession().save(user);
		sessionFactory.getCurrentSession().flush();
	}
	/**
	* 根据id删除用户
	*/
	public boolean deleteUser(String id) 
	{
	String hql ="delete User u where u.id = ? "; 
	Query query =sessionFactory.getCurrentSession().createQuery(hql);
	query.setString(0, id);
	return (query.executeUpdate()>0);
	}
	/**
	* 修改学生信息
	*/
	public boolean updateUser(User user)
	{ 
	String hql="update User u set u.username =?,u.password=?,u.phone=?,u.email=? where u.id =?"; 
	Query query =sessionFactory.getCurrentSession().createQuery(hql);
	query.setString(0, user.getUsername());
	query.setString(1, user.getPassword());
	query.setString(2, user.getPhone()); 
	query.setString(3, user.getEmail()); 
	query.setString(4, user.getId());
	return (query.executeUpdate() >0); }
	/**
	* 条件查找学生
	*/
	public List<User> queryUser(User user) 
	{
	StringBuffer hql = new StringBuffer();
	hql.append("from User user where 1=1");
	if( user.getUsername()!=null&&!user.getUsername().equals("") )
	{
	hql.append(" and user.username like'%"+user.getUsername()+"%'");
	}
	if(user.getPassword()!=null&&!user.getPassword().equals(""))
	{
	hql.append(" and user.password like'%"+user.getPassword()+"%'");
	}
	if( user.getPhone() != null&&!user.getPhone().equals(""))
	{
	hql.append(" and user.phone like '%"+user.getPhone()+"%'");
	}
	if(user.getEmail()!=null&&!user.getEmail().equals(""))
	{
	hql.append(" and user.email like'%"+user.getEmail()+"%'");
	}
	Query query =sessionFactory.getCurrentSession().createQuery(hql.toString());
	return query.list();
	/*DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
	if(!user.getUsername().equals("") && user.getUsername()!=null)
	{
	 detachedCriteria.add(Restrictions.eq("username", user.getUsername()));
	}
	Session session=sessionFactory.getCurrentSession();
	return detachedCriteria.getExecutableCriteria(session).list();*/
	}
	
	public int getPageNum()
	{
	String hql="from User";
	Query query =
	sessionFactory.getCurrentSession().createQuery(hql);
	int temp = query.list().size()/pageSize; 
	if(query.list().size()%pageSize !=0)
	{ 
		temp++;
	}
	return temp;
	}

}
