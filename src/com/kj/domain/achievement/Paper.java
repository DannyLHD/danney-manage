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
@Table(name = "PAPER")
public class Paper extends BaseDomain{

	//其他字段
	//论文名
	@Column(name="PAPERNAME",length=50)
	private String paperName;
	//作者
	@Column(name="AUTHOR",length=50)
	private String author;
	//单位
	@Column(name="UNIT",length=50)
	private String unit;
	//出版社/期刊名
	@Column(name="PUBLISHINGCOMPANY",length=100)
	private String publishingCompany;
	//出版号/期号页码
	@Column(name="PUBLICATIONNO",length=100)
	private String publicationNo;
	//检索（EI/SCI）
	@Column(name="RETRIEVE",length=100)
	private String retrieve;
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
	public String getPaperName() {
		return paperName;
	}
	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getPublishingCompany() {
		return publishingCompany;
	}
	public void setPublishingCompany(String publishingCompany) {
		this.publishingCompany = publishingCompany;
	}
	public String getPublicationNo() {
		return publicationNo;
	}
	public void setPublicationNo(String publicationNo) {
		this.publicationNo = publicationNo;
	}
	public String getRetrieve() {
		return retrieve;
	}
	public void setRetrieve(String retrieve) {
		this.retrieve = retrieve;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

}
