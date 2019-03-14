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
@Table(name = "PATENT")
public class Patent extends BaseDomain{

	//其他字段
	//类型
	@Column(name="TYPE",length=50)
	private String type;
	//专利名称
	@Column(name="PATENTNAME",length=50)
	private String patentName;
	//发明人
	@Column(name="INVENTOR",length=50)
	private String inventor;
	//专利号
	@Column(name="PATENTNO",length=50)
	private String patentNo;
	//专利权人
	@Column(name="PATENTEE",length=50)
	private String patentee;
	//申请日
	@Column(name="DATE_APPLICATION",length=50)
	private String date_Application;
	//授权日
	@Column(name="DATE_AUTHORIZATION",length=50)
	private String date_Authorization;
	//代理机构
	@Column(name="AGENCY",length=50)
	private String agency;
	//效益转化
	@Column(name="BENEFITS",length=50)
	private String benefits;
	//存放处
	@Column(name="STORAGE",length=50)
	private String storage;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPatentName() {
		return patentName;
	}
	public void setPatentName(String patentName) {
		this.patentName = patentName;
	}
	public String getInventor() {
		return inventor;
	}
	public void setInventor(String inventor) {
		this.inventor = inventor;
	}
	public String getPatentNo() {
		return patentNo;
	}
	public void setPatentNo(String patentNo) {
		this.patentNo = patentNo;
	}
	public String getPatentee() {
		return patentee;
	}
	public void setPatentee(String patentee) {
		this.patentee = patentee;
	}
	public String getDate_Application() {
		return date_Application;
	}
	public void setDate_Application(String date_Application) {
		this.date_Application = date_Application;
	}
	public String getDate_Authorization() {
		return date_Authorization;
	}
	public void setDate_Authorization(String date_Authorization) {
		this.date_Authorization = date_Authorization;
	}
	public String getAgency() {
		return agency;
	}
	public void setAgency(String agency) {
		this.agency = agency;
	}
	public String getBenefits() {
		return benefits;
	}
	public void setBenefits(String benefits) {
		this.benefits = benefits;
	}
	public String getStorage() {
		return storage;
	}
	public void setStorage(String storage) {
		this.storage = storage;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

}
