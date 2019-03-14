package com.kj.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.system.domain.BaseDomain;


@Entity 
@Table(name = "PROJECT_EXECUTION")
public class Project_Execution extends BaseDomain{

	//其他字段
	//项目技术来源
	@Column(name="TECSOURCES",length=400)
	private String tecSources;
	//创新类型
	@Column(name="INNOVATIONTYPE",length=400)
	private String innovationType;
	//项目计划投资到位情况
	@Column(name="CAPITALPOSITION",length=400)
	private String capitalPosition;
	//项目支出合计
	@Column(name="TOTALEXPENDITURE",length=400)
	private String totalExpenditure;
	//项目参加人员投入工作量
	@Column(name="WORKLOAD",length=400)
	private String workload;
	//本年度项目执行情况
	@Column(name="EXECONDITION",length=400)
	private String exeCondition;
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
	public String getTecSources() {
		return tecSources;
	}
	public void setTecSources(String tecSources) {
		this.tecSources = tecSources;
	}
	public String getInnovationType() {
		return innovationType;
	}
	public void setInnovationType(String innovationType) {
		this.innovationType = innovationType;
	}
	public String getCapitalPosition() {
		return capitalPosition;
	}
	public void setCapitalPosition(String capitalPosition) {
		this.capitalPosition = capitalPosition;
	}
	public String getTotalExpenditure() {
		return totalExpenditure;
	}
	public void setTotalExpenditure(String totalExpenditure) {
		this.totalExpenditure = totalExpenditure;
	}
	public String getWorkload() {
		return workload;
	}
	public void setWorkload(String workload) {
		this.workload = workload;
	}
	public String getExeCondition() {
		return exeCondition;
	}
	public void setExeCondition(String exeCondition) {
		this.exeCondition = exeCondition;
	}
}
