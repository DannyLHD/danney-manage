package com.kj.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.system.domain.BaseDomain;

@Entity 
@Table(name = "EQUIPMENT")
public class Equipment extends BaseDomain{

	//其他字段
	/*设备编号、设备名称、单位、所属分公司、维护人员、设备价格、
	购买时间、维护费用、设备功能、是否对外租赁、关联项目、备注*/
	@Column(name="EQUIPMENTID",nullable=true,length=50)
	private String equipmentID;
	@Column(name="EQUIPMENT_NAME",nullable=true,length=50)
	private String equipmentName;
	@Column(name="unit",nullable=true,length=50)
	private String unit;
	@Column(name="SUBSIDIARY",nullable=true,length=50)
	private String subsidiary;
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "USERID")
	private User user;
	@Column(name="PRICE",nullable=true,length=50)
	private String price;
	@Column(name="DATE",nullable=true,length=50)
	private String date;
	@Column(name="MAINTENANCE_COSTS",nullable=true,length=50)
	private String maintenanceCosts;
	@Column(name="FUNCTION",nullable=true,length=200)
	private String function;
	@Column(name="FOREIGN_LEASE",nullable=true,length=200)
	private Boolean foreignLease;
	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinTable(name = "PROJECT_EQUIPMENT",
		joinColumns = {@JoinColumn(name = "EQUIPMENT_ID", referencedColumnName ="id")},
		inverseJoinColumns = {@JoinColumn(name = "PROJECT_ID", referencedColumnName = "id")})
	private List<Project> projectList;
	@Column(name="REMARK",nullable=true,length=200)
	private String remark;
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
	public String getEquipmentID() {
		return equipmentID;
	}
	public void setEquipmentID(String equipmentID) {
		this.equipmentID = equipmentID;
	}
	public String getEquipmentName() {
		return equipmentName;
	}
	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getMaintenanceCosts() {
		return maintenanceCosts;
	}
	public void setMaintenanceCosts(String maintenanceCosts) {
		this.maintenanceCosts = maintenanceCosts;
	}
	public String getFunction() {
		return function;
	}
	public void setFunction(String function) {
		this.function = function;
	}
	public Boolean isForeignLease() {
		return foreignLease;
	}
	public Boolean getForeignLease() {
		return foreignLease;
	}
	public void setForeignLease(Boolean foreignLease) {
		this.foreignLease = foreignLease;
	}
	public List<Project> getProjectList() {
		return projectList;
	}
	public void setProjectList(List<Project> projectList) {
		this.projectList = projectList;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getSubsidiary() {
		return subsidiary;
	}
	public void setSubsidiary(String subsidiary) {
		this.subsidiary = subsidiary;
	}
}
