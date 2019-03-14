package com.kj.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.system.domain.BaseDomain;

@Entity 
@Table(name = "UNIT")
public class Unit extends BaseDomain{

	//其他字段
	//单位名称
	@Column(name="UNITNAME",nullable=true,length=40)
	private String unitName;
	//单位代码
	@Column(name="UNITCODE",nullable=true,length=40)
	private String unitCode;
	
	//代码
	//-----------------基础字段--------------------------------------------------
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name = "system-uuid",strategy="uuid")
	@Column(name = "ID", unique = true, nullable = true)
	private String id;	//id
	
	//-----------------基础字段get、set------------------------------------------
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
  //-----------------其他字段get、set------------------------------------------
}
