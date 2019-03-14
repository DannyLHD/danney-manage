package com.kj.dao.bean;

import org.springframework.stereotype.Repository;

import com.kj.dao.IUserDao;
import com.kj.domain.User;
import com.system.dao.bean.BaseDao;


@Repository
public class UserDao extends BaseDao<User> implements IUserDao{

}
