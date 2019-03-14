package com.kj.dao.bean;

import org.springframework.stereotype.Repository;

import com.kj.dao.IProject_AcceptanceDao;
import com.kj.dao.IProject_ApplicationDao;
import com.kj.domain.Project_Acceptance;
import com.kj.domain.Project_Application;
import com.system.dao.bean.BaseDao;
@Repository
public class Project_AcceptanceDao extends BaseDao<Project_Acceptance> implements IProject_AcceptanceDao{

}
