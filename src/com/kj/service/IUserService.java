package com.kj.service;

import com.kj.domain.User;
import com.system.service.IBaseService;

/**
 * 账户服务层接口
 *
 */
public interface IUserService extends IBaseService<User>{
	/**
	 * 验证用户名密码，如果通过，返回User，不通过，返回null
	 * @param username
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public boolean doCheckUserPassword(String phone,char[] password)throws Exception;
	
	/**
	 * 通过手机号获取User实体
	 * @param username
	 * @return
	 * @throws Exception
	 */
	public User doGetUserByPhone(String phone)throws Exception;
	/**
	 * 通过用户名获取实体
	 * @param username
	 * @return
	 * @throws Exception
	 *//*
	public User doGetUserByID(String id)throws Exception;*/
}
