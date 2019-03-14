package com.kj.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.system.domain.BaseDomain;


@Entity 
@Table(name = "PROJECT_ACCEPTANCE")
public class Project_Acceptance extends BaseDomain{

	//其他字段
	//项目完成时间
	@Column(name="COMPLETIONTIME",length=400)
	private String completionTime;
	//项目实施情况概述
	@Column(name="IMPLEMENTATION",length=400)
	private String implementation;
	//项目绩效情况。合同约定和实际完成情况
	@Column(name="PERFORMANCE",length=400)
	private String performance;
	//项目经费预算情况
	@Column(name="BUDGETSITUATION",length=400)
	private String budgetSituation;
	//项目经济效益和社会效益，获得政府支持或税收减免补助资金
	@Column(name="ECONOMICBENEFITS",length=400)
	private String economicBenefits;
	/*//推荐单位意见
*/	
	//验收编号
	@Column(name="ACCEPTANCENUMBER",length=400)
	private String acceptanceNumber;
	//验收时间
	@Column(name="ACCEPTANCETIME",length=400)
	private String acceptanceTime;
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
	public String getCompletionTime() {
		return completionTime;
	}
	public void setCompletionTime(String completionTime) {
		this.completionTime = completionTime;
	}
	public String getImplementation() {
		return implementation;
	}
	public void setImplementation(String implementation) {
		this.implementation = implementation;
	}
	public String getPerformance() {
		return performance;
	}
	public void setPerformance(String performance) {
		this.performance = performance;
	}
	public String getBudgetSituation() {
		return budgetSituation;
	}
	public void setBudgetSituation(String budgetSituation) {
		this.budgetSituation = budgetSituation;
	}
	public String getEconomicBenefits() {
		return economicBenefits;
	}
	public void setEconomicBenefits(String economicBenefits) {
		this.economicBenefits = economicBenefits;
	}
	public String getAcceptanceNumber() {
		return acceptanceNumber;
	}
	public void setAcceptanceNumber(String acceptanceNumber) {
		this.acceptanceNumber = acceptanceNumber;
	}
	public String getAcceptanceTime() {
		return acceptanceTime;
	}
	public void setAcceptanceTime(String acceptanceTime) {
		this.acceptanceTime = acceptanceTime;
	}
}
