package com.kj.domain;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.system.domain.BaseDomain;


@Entity 
@Table(name = "PROJECT_ESTABLISHMENT")
public class Project_Establishment extends BaseDomain{

	//其他字段
	//...
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="PROJECTID")
	private Project project;
	/*//审核状态
	@Column(name="CHECK_UNIT",length=40)
	private Integer check_unit;
	@Column(name="CHECK_CHILD_ADMIN",length=40)
	private Integer check_child_admin;
	@Column(name="CHECK_HEAD_ADMIN",length=40)
	private Integer check_head_admin;*/
	//依托单位
	@Column(name="RELYINGUNIT",length=400)
	private String relyingUnit;
	//现状
	@Column(name="CURRENTSITUATION",length=400)
	private String currentSituation;
	//技术路线
	@Column(name="TECHNICALROUTE",length=400)
	private String technicalRoute;
	//现有工作基础
	@Column(name="WORKFOUNDATION",length=400)
	private String workFoundation;
	//预期目标
	@Column(name="TARGET",length=400)
	private String target;
	
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
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public String getRelyingUnit() {
		return relyingUnit;
	}
	public void setRelyingUnit(String relyingUnit) {
		this.relyingUnit = relyingUnit;
	}
	public String getCurrentSituation() {
		return currentSituation;
	}
	public void setCurrentSituation(String currentSituation) {
		this.currentSituation = currentSituation;
	}
	public String getTechnicalRoute() {
		return technicalRoute;
	}
	public void setTechnicalRoute(String technicalRoute) {
		this.technicalRoute = technicalRoute;
	}
	public String getWorkFoundation() {
		return workFoundation;
	}
	public void setWorkFoundation(String workFoundation) {
		this.workFoundation = workFoundation;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
}
