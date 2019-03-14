package com.kj.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.type.TrueFalseType;

import com.system.domain.BaseDomain;


@Entity 
@Table(name = "USER")
public class User extends BaseDomain{

	//其他字段
	@Column(name="EMPLOYEEID",nullable=true,length=20)
	private String employeeID;
	@Column(name="USERNAME",nullable=true,length=20)
	private String username;
	@Column(name="PASSWORD",nullable=true,length=40)
	private String password;
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "ROLE_AUTH", referencedColumnName="authority")
	private UserRole role;	//角色
	
	@Column(name="PHONE",nullable=true,length=20)
	private String phone;
	@Column(name="EMAIL",nullable=true,length=40)
	private String email;
	@Column(name="UNIT",nullable=true,length=40)
	private String unit;//单位
	@Column(name="SUBSIDIARY",length=40)
	private String subsidiary;//子公司
	@Column(name="POSITION",nullable=true,length=40)
	private String position;//职务
	@Column(name="PROTITLE",nullable=true,length=40)
	private String protitle;//职称
	@Column(name="DEGREE",nullable=true,length=40)
	private String degree;//学历
	@Column(name="RESEARCH_FIELD",nullable=true,length=40)
	private String researchField;//专业方向
	//设备
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Equipment> equipmentList;
	//项目
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Project> projectList;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public UserRole getRole() {
		return role;
	}
	public void setRole(UserRole role) {
		this.role = role;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public String getResearchField() {
		return researchField;
	}
	public void setResearchField(String researchField) {
		this.researchField = researchField;
	}
	public String getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}
	public String getProtitle() {
		return protitle;
	}
	public void setProtitle(String protitle) {
		this.protitle = protitle;
	}
	public String getSubsidiary() {
		return subsidiary;
	}
	public void setSubsidiary(String subsidiary) {
		this.subsidiary = subsidiary;
	}
	public List<Equipment> getEquipmentList() {
		return equipmentList;
	}
	public void setEquipmentList(List<Equipment> equipmentList) {
		this.equipmentList = equipmentList;
	}
	public List<Project> getProjectList() {
		return projectList;
	}
	public void setProjectList(List<Project> projectList) {
		this.projectList = projectList;
	}
	
}