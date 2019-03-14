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

import com.kj.domain.User;
import com.system.domain.BaseDomain;
//专利
@Entity 
@Table(name = "STANDARD")
public class Standard extends BaseDomain{

	//其他字段
	//计划编号
	@Column(name="SCHEMENO",length=50)
	private String schemeNo;
	//发布编号
	@Column(name="RELEASENO",length=50)
	private String releaseNo;
	//标准名称
	@Column(name="STANDARDNAME",length=50)
	private String standardName;
	//制定/参与制定
	@Column(name="FORMULATION",length=50)
	private String formulation;
	//标准类型（国家/行业/企业）
	@Column(name="TYPE",length=50)
	private String type;
	//管理机构
	@Column(name="MANAGEORGANIZATION",length=50)
	private String manageOrganization;
	//起草单位
	@Column(name="DRAFTINGUNIT",length=50)
	private String draftingUnit;
	//编写人员
	@Column(name="WRITERS",length=50)
	private String writers;
	//申请/发布时间
	@Column(name="RELEASETIME",length=50)
	private String releaseTime;
	//登记人
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "USERID")
	private User user;
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
	public String getSchemeNo() {
		return schemeNo;
	}
	public void setSchemeNo(String schemeNo) {
		this.schemeNo = schemeNo;
	}
	public String getReleaseNo() {
		return releaseNo;
	}
	public void setReleaseNo(String releaseNo) {
		this.releaseNo = releaseNo;
	}
	public String getStandardName() {
		return standardName;
	}
	public void setStandardName(String standardName) {
		this.standardName = standardName;
	}
	public String getFormulation() {
		return formulation;
	}
	public void setFormulation(String formulation) {
		this.formulation = formulation;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getManageOrganization() {
		return manageOrganization;
	}
	public void setManageOrganization(String manageOrganization) {
		this.manageOrganization = manageOrganization;
	}
	public String getDraftingUnit() {
		return draftingUnit;
	}
	public void setDraftingUnit(String draftingUnit) {
		this.draftingUnit = draftingUnit;
	}
	public String getWriters() {
		return writers;
	}
	public void setWriters(String writers) {
		this.writers = writers;
	}
	public String getReleaseTime() {
		return releaseTime;
	}
	public void setReleaseTime(String releaseTime) {
		this.releaseTime = releaseTime;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

}
