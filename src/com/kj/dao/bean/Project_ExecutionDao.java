package com.kj.dao.bean;

import org.springframework.stereotype.Repository;

import com.kj.dao.IProject_ApplicationDao;
import com.kj.dao.IProject_ExecutionDao;
import com.kj.domain.Project_Application;
import com.kj.domain.Project_Execution;
import com.system.dao.bean.BaseDao;
@Repository
public class Project_ExecutionDao extends BaseDao<Project_Execution> implements IProject_ExecutionDao{

}
