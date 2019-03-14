package com.kj.domain;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.system.domain.BaseDomain;

@Entity 
@Table(name = "TECINFORMATION")
public class TecInformation extends BaseDomain{

	//其他字段
	@Column(name="INFORTITLE",nullable=false,length=80)
	private String inforTitle;
	@Column(name="INFORCONTENT",nullable=false,length=1000)
	private String inforContent;
	@Column(name="INFORTIME",nullable=false,length=80)
	private String inforTime;
	@Column(name="INFORTYPE",nullable=false,length=10)
	private int inforType;
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "USERID")
	private User user;
	@Column(name="INFORMATIONFILE",length=200)
	private String informationFile;

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
	public String getInforTitle() {
		return inforTitle;
	}
	public void setInforTitle(String inforTitle) {
		this.inforTitle = inforTitle;
	}
	public String getInforContent() {
		return inforContent;
	}
	public void setInforContent(String inforContent) {
		this.inforContent = inforContent;
	}
	public String getInforTime() {
		return inforTime;
	}
	public void setInforTime(String inforTime) {
		this.inforTime = inforTime;
	}
	public int getInforType() {
		return inforType;
	}
	public void setInforType(int inforType) {
		this.inforType = inforType;
	}
	public String getInformationFile() {
		return informationFile;
	}
	public void setInformationFile(String informationFile) {
		this.informationFile = informationFile;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}

