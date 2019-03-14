package com.kj.domain.achievement;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.kj.domain.Equipment;
import com.kj.domain.Project;
import com.kj.domain.User;
import com.system.domain.BaseDomain;

@Entity 
@Table(name = "REWARD")
public class Reward extends BaseDomain{

	//其他字段
	//级别
	@Column(name="RANK",length=50)
	private String rank;
	//项目名称
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "PROJECTID")
	private Project project;
	//完成单位
	@Column(name="UNIT",length=50)
	private String unit;
	//等级
	@Column(name="GRADE",length=50)
	private String grade;
	//申请人
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "USERID")
	private User user;
	//相关人员
	@Column(name="PARTICIPANTS",length=40)
	private String participants;
	//证书号
	@Column(name="CERTIFICATENUMBER",length=40)
	private String certificateNumber;
	//申请日期
	@Column(name="DATE",length=40)
	private String date;
	//存放处
	@Column(name="STORAGE",length=50)
	private String storage;
	//科技奖励状态
	@Column(name="STATUS",length=10)
	private Integer status;
	//审核状态
	@Column(name="CHECK_UNIT",length=40)
	private Integer check_unit;
	@Column(name="CHECK_CHILD_ADMIN",length=40)
	private Integer check_child_admin;
	@Column(name="CHECK_HEAD_ADMIN",length=40)
	private Integer check_head_admin;
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
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getParticipants() {
		return participants;
	}
	public void setParticipants(String participants) {
		this.participants = participants;
	}
	public String getCertificateNumber() {
		return certificateNumber;
	}
	public void setCertificateNumber(String certificateNumber) {
		this.certificateNumber = certificateNumber;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStorage() {
		return storage;
	}
	public void setStorage(String storage) {
		this.storage = storage;
	}
	public Integer getCheck_unit() {
		return check_unit;
	}
	public void setCheck_unit(Integer check_unit) {
		this.check_unit = check_unit;
	}
	public Integer getCheck_child_admin() {
		return check_child_admin;
	}
	public void setCheck_child_admin(Integer check_child_admin) {
		this.check_child_admin = check_child_admin;
	}
	public Integer getCheck_head_admin() {
		return check_head_admin;
	}
	public void setCheck_head_admin(Integer check_head_admin) {
		this.check_head_admin = check_head_admin;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}

