package com.dbEntity;

/**
 * DemandCollect entity. @author MyEclipse Persistence Tools
 */

public class DemandCollect implements java.io.Serializable {

	// Fields

	private String demandId;
	private OperatingOrganization operatingOrganization;
	private String formIdea;
	private String departIdea;
	private String manageDepart;
	private Integer DStatus;
	private Integer lock;
	private String DName;
	private String startTime;
	private String endTime;
	private String theme;
	private String techKey;
	private String expectTarget;
	private String DKey;
	private String planInvestment;
	private String DSolution;
	private String cooIntention;
	private String techType;
	private String subjects;
	private String DServics;
	private String DAppIndustry;

	// Constructors

	/** default constructor */
	public DemandCollect() {
	}

	/** minimal constructor */
	public DemandCollect(String demandId) {
		this.demandId = demandId;
	}

	/** full constructor */
	public DemandCollect(String demandId,
			OperatingOrganization operatingOrganization, String formIdea,
			String departIdea, String manageDepart, Integer DStatus,
			Integer lock, String DName, String startTime, String endTime,
			String theme, String techKey, String expectTarget, String DKey,
			String planInvestment, String DSolution, String cooIntention,
			String techType, String subjects, String DServics,
			String DAppIndustry) {
		this.demandId = demandId;
		this.operatingOrganization = operatingOrganization;
		this.formIdea = formIdea;
		this.departIdea = departIdea;
		this.manageDepart = manageDepart;
		this.DStatus = DStatus;
		this.lock = lock;
		this.DName = DName;
		this.startTime = startTime;
		this.endTime = endTime;
		this.theme = theme;
		this.techKey = techKey;
		this.expectTarget = expectTarget;
		this.DKey = DKey;
		this.planInvestment = planInvestment;
		this.DSolution = DSolution;
		this.cooIntention = cooIntention;
		this.techType = techType;
		this.subjects = subjects;
		this.DServics = DServics;
		this.DAppIndustry = DAppIndustry;
	}

	// Property accessors

	public String getDemandId() {
		return this.demandId;
	}

	public void setDemandId(String demandId) {
		this.demandId = demandId;
	}

	public OperatingOrganization getOperatingOrganization() {
		return this.operatingOrganization;
	}

	public void setOperatingOrganization(
			OperatingOrganization operatingOrganization) {
		this.operatingOrganization = operatingOrganization;
	}

	public String getFormIdea() {
		return this.formIdea;
	}

	public void setFormIdea(String formIdea) {
		this.formIdea = formIdea;
	}

	public String getDepartIdea() {
		return this.departIdea;
	}

	public void setDepartIdea(String departIdea) {
		this.departIdea = departIdea;
	}

	public String getManageDepart() {
		return this.manageDepart;
	}

	public void setManageDepart(String manageDepart) {
		this.manageDepart = manageDepart;
	}

	public Integer getDStatus() {
		return this.DStatus;
	}

	public void setDStatus(Integer DStatus) {
		this.DStatus = DStatus;
	}

	public Integer getLock() {
		return this.lock;
	}

	public void setLock(Integer lock) {
		this.lock = lock;
	}

	public String getDName() {
		return this.DName;
	}

	public void setDName(String DName) {
		this.DName = DName;
	}

	public String getStartTime() {
		return this.startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return this.endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getTheme() {
		return this.theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getTechKey() {
		return this.techKey;
	}

	public void setTechKey(String techKey) {
		this.techKey = techKey;
	}

	public String getExpectTarget() {
		return this.expectTarget;
	}

	public void setExpectTarget(String expectTarget) {
		this.expectTarget = expectTarget;
	}

	public String getDKey() {
		return this.DKey;
	}

	public void setDKey(String DKey) {
		this.DKey = DKey;
	}

	public String getPlanInvestment() {
		return this.planInvestment;
	}

	public void setPlanInvestment(String planInvestment) {
		this.planInvestment = planInvestment;
	}

	public String getDSolution() {
		return this.DSolution;
	}

	public void setDSolution(String DSolution) {
		this.DSolution = DSolution;
	}

	public String getCooIntention() {
		return this.cooIntention;
	}

	public void setCooIntention(String cooIntention) {
		this.cooIntention = cooIntention;
	}

	public String getTechType() {
		return this.techType;
	}

	public void setTechType(String techType) {
		this.techType = techType;
	}

	public String getSubjects() {
		return this.subjects;
	}

	public void setSubjects(String subjects) {
		this.subjects = subjects;
	}

	public String getDServics() {
		return this.DServics;
	}

	public void setDServics(String DServics) {
		this.DServics = DServics;
	}

	public String getDAppIndustry() {
		return this.DAppIndustry;
	}

	public void setDAppIndustry(String DAppIndustry) {
		this.DAppIndustry = DAppIndustry;
	}

}