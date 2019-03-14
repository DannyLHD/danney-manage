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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.system.domain.BaseDomain;

@Entity 
@Table(name = "Project")
public class Project extends BaseDomain{

	//其他字段
	//项目编号
	@Column(name="PROJECTNUMBER",length=40)
	private String projectNumber;
	//项目名称
	@Column(name="PROJECTNAME",length=40)
	private String projectName;
	//负责人
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "USERID")
	private User user;
	//申报单位
	@Column(name="unit",length=40)
	private String unit;
	//子公司
	@Column(name="SUBSIDIARY",length=40)
	private String subsidiary;
	//参与人员
	@Column(name="PARTICIPANTS",length=40)
	private String participants;
	//预计经费
	@Column(name="PROJECTCOST",length=30)
	private String projectcost;
	//背景意义
	@Column(name="BACKGROUND",length=400)
	private String background;
	//项目内容
	@Column(name="PROJECTCONTENT",length=400)
	private String projectContent;
	//项目安排
	@Column(name="PROJECTPLAN",length=400)
	private String projectPlan;
	//附件
	@Column(name="PROJECTFile",length=400)
	private String projectFile;
	//预期成果
	@Column(name="ACHIEVEMENT",length=400)
	private String achievement;
	//技术领域
	@Column(name="TECHNICALFIELD",length=400)
	private String technicalField;
	//预计完成时间
	@Column(name="TIME",length=400)
	private String time;
	//项目阶段
	@Column(name="STAGE",length=10)
	private Integer stage;
	//项目状态
	@Column(name="STATUS",length=10)
	private Integer status;
	/*//项目审核状态
	@Column(name="CHECK_STATUS",length=400)
	private Integer checkStatus;*/
	//提交时间
	@Column(name="SUBMITTIME",length=40)
	private String submitTime;
	//审核状态
	@Column(name="CHECK_UNIT",length=40)
	private Integer check_unit;
	@Column(name="CHECK_CHILD_ADMIN",length=40)
	private Integer check_child_admin;
	@Column(name="CHECK_HEAD_ADMIN",length=40)
	private Integer check_head_admin;
	//关联项目
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="PROJECT_APPLICATIONID")
	private Project_Application project_Application;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="PROJECT_ESTABLISHMENT")
	private Project_Establishment project_Establishment;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="PROJECT_EXECUTION")
	private Project_Execution project_Execution;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="PROJECT_ACCEPTANCE")
	private Project_Acceptance project_Acceptance;
	//设备
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinTable(name = "PROJECT_EQUIPMENT",
		joinColumns = {@JoinColumn(name = "PROJECT_ID", referencedColumnName = "id")},
		inverseJoinColumns = {@JoinColumn(name = "EQUIPMENT_ID", referencedColumnName ="id")})
	private Equipment equipment;
	/** 
     * 用于hibernate级联删除，删除了项目，把它下面的成果一起删除 
     * @return 
     *//*  
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.LAZY)  
    private List<Result>resultList;*/
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
	public String getProjectNumber() {
		return projectNumber;
	}
	public void setProjectNumber(String projectNumber) {
		this.projectNumber = projectNumber;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getParticipants() {
		return participants;
	}
	public void setParticipants(String participants) {
		this.participants = participants;
	}
	public String getProjectcost() {
		return projectcost;
	}
	public void setProjectcost(String projectcost) {
		this.projectcost = projectcost;
	}
	public String getBackground() {
		return background;
	}
	public void setBackground(String background) {
		this.background = background;
	}
	public String getProjectContent() {
		return projectContent;
	}
	public void setProjectContent(String projectContent) {
		this.projectContent = projectContent;
	}
	public String getProjectPlan() {
		return projectPlan;
	}
	public void setProjectPlan(String projectPlan) {
		this.projectPlan = projectPlan;
	}
	public String getProjectFile() {
		return projectFile;
	}
	public void setProjectFile(String projectFile) {
		this.projectFile = projectFile;
	}
	public String getAchievement() {
		return achievement;
	}
	public void setAchievement(String achievement) {
		this.achievement = achievement;
	}
	public String getTechnicalField() {
		return technicalField;
	}
	public void setTechnicalField(String technicalField) {
		this.technicalField = technicalField;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	/*public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}*/
	/*public List<Result> getResultList() {
		return resultList;
	}
	public void setResultList(List<Result> resultList) {
		this.resultList = resultList;
	}*/
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Equipment getEquipment() {
		return equipment;
	}
	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}
	public Integer getStage() {
		return stage;
	}
	public void setStage(Integer stage) {
		this.stage = stage;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getSubsidiary() {
		return subsidiary;
	}
	public void setSubsidiary(String subsidiary) {
		this.subsidiary = subsidiary;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Project_Application getProject_Application() {
		return project_Application;
	}
	public void setProject_Application(Project_Application project_Application) {
		this.project_Application = project_Application;
	}
	/*public Integer getCheckStatus() {
		return checkStatus;
	}
	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}*/
	public Project_Establishment getProject_Establishment() {
		return project_Establishment;
	}
	public void setProject_Establishment(Project_Establishment project_Establishment) {
		this.project_Establishment = project_Establishment;
	}
	public Project_Execution getProject_Execution() {
		return project_Execution;
	}
	public void setProject_Execution(Project_Execution project_Execution) {
		this.project_Execution = project_Execution;
	}
	public Project_Acceptance getProject_Acceptance() {
		return project_Acceptance;
	}
	public void setProject_Acceptance(Project_Acceptance project_Acceptance) {
		this.project_Acceptance = project_Acceptance;
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
	public String getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}
	
	
}
