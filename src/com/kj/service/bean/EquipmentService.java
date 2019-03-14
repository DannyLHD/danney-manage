package com.kj.service.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kj.dao.IEquipmentDao;
import com.kj.dao.IProjectDao;
import com.kj.domain.Equipment;
import com.kj.service.IEquipmentService;
import com.system.dao.IBaseDao;
import com.system.service.bean.BaseService;



@Service
@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
public class EquipmentService extends BaseService<Equipment> implements IEquipmentService{
	@Resource private IEquipmentDao equipmentDao;
	@Resource private IProjectDao projectDao;
	protected IBaseDao<Equipment> getBaseDao() {
		return equipmentDao;
	}
	@Override
	public boolean doDeleteByIds(Serializable[] ids)
	{
		List<String> projectForeignList = new ArrayList<String>();
		projectForeignList.add("equipment.id");
		return projectDao.setForeignsNullByIds(ids, projectForeignList)&&
				equipmentDao.deleteByIds(ids);
	}
	
}