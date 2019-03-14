package com.kj.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.system.domain.BaseDomain;

@Entity 
@Table(name = "USERROLE")
public class UserRole extends BaseDomain implements Serializable{

	@Column(name = "NAME", nullable = false, length = 100)
	private String name;	//角色名称
	@Column(name = "AUTHORITY", nullable = false, length = 100)
	private Integer authority;	//角色权限
	@Column(name = "VALUE", nullable = false, length = 100)
	private String value;	//角色值
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "role", fetch = FetchType.LAZY)
	private Set<User> users = new HashSet<User>(0);
	
	//-----------------基础字段--------------------------------------------------
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name = "system-uuid",strategy="uuid")
	@Column(name = "ID", unique = true, nullable = true, precision = 10, scale = 0)
	private String id;	//id
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getAuthority() {
		return authority;
	}
	public void setAuthority(Integer authority) {
		this.authority = authority;
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	//-----------------基础字段get、set------------------------------------------
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}