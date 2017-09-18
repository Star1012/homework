package com.dbEntity;

import java.util.HashSet;
import java.util.Set;

/**
 * OperatingOrganization entity. @author MyEclipse Persistence Tools
 */

public class OperatingOrganization implements java.io.Serializable {

	// Fields

	private String OName;
	private String OCode;
	private String pwd;
	private String OAddress;
	private String OArea;
	private String OUrl;
	private String OEmail;
	private String OLperson;
	private String OPostcode;
	private String OContacts;
	private String OPlPhone;
	private String OMbPhone;
	private String OFax;
	private String OAttribute;
	private String OTmd;
	private String OOverView;
	private String role;
	private Set demandCollects = new HashSet(0);

	// Constructors

	/** default constructor */
	public OperatingOrganization() {
	}

	/** minimal constructor */
	public OperatingOrganization(String OName, String OCode, String pwd,
			String OAddress, String OArea, String role) {
		this.OName = OName;
		this.OCode = OCode;
		this.pwd = pwd;
		this.OAddress = OAddress;
		this.OArea = OArea;
		this.role = role;
	}

	/** full constructor */
	public OperatingOrganization(String OName, String OCode, String pwd,
			String OAddress, String OArea, String OUrl, String OEmail,
			String OLperson, String OPostcode, String OContacts,
			String OPlPhone, String OMbPhone, String OFax, String OAttribute,
			String OTmd, String OOverView, String role, Set demandCollects) {
		this.OName = OName;
		this.OCode = OCode;
		this.pwd = pwd;
		this.OAddress = OAddress;
		this.OArea = OArea;
		this.OUrl = OUrl;
		this.OEmail = OEmail;
		this.OLperson = OLperson;
		this.OPostcode = OPostcode;
		this.OContacts = OContacts;
		this.OPlPhone = OPlPhone;
		this.OMbPhone = OMbPhone;
		this.OFax = OFax;
		this.OAttribute = OAttribute;
		this.OTmd = OTmd;
		this.OOverView = OOverView;
		this.role = role;
		this.demandCollects = demandCollects;
	}

	// Property accessors

	public String getOName() {
		return this.OName;
	}

	public void setOName(String OName) {
		this.OName = OName;
	}

	public String getOCode() {
		return this.OCode;
	}

	public void setOCode(String OCode) {
		this.OCode = OCode;
	}

	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getOAddress() {
		return this.OAddress;
	}

	public void setOAddress(String OAddress) {
		this.OAddress = OAddress;
	}

	public String getOArea() {
		return this.OArea;
	}

	public void setOArea(String OArea) {
		this.OArea = OArea;
	}

	public String getOUrl() {
		return this.OUrl;
	}

	public void setOUrl(String OUrl) {
		this.OUrl = OUrl;
	}

	public String getOEmail() {
		return this.OEmail;
	}

	public void setOEmail(String OEmail) {
		this.OEmail = OEmail;
	}

	public String getOLperson() {
		return this.OLperson;
	}

	public void setOLperson(String OLperson) {
		this.OLperson = OLperson;
	}

	public String getOPostcode() {
		return this.OPostcode;
	}

	public void setOPostcode(String OPostcode) {
		this.OPostcode = OPostcode;
	}

	public String getOContacts() {
		return this.OContacts;
	}

	public void setOContacts(String OContacts) {
		this.OContacts = OContacts;
	}

	public String getOPlPhone() {
		return this.OPlPhone;
	}

	public void setOPlPhone(String OPlPhone) {
		this.OPlPhone = OPlPhone;
	}

	public String getOMbPhone() {
		return this.OMbPhone;
	}

	public void setOMbPhone(String OMbPhone) {
		this.OMbPhone = OMbPhone;
	}

	public String getOFax() {
		return this.OFax;
	}

	public void setOFax(String OFax) {
		this.OFax = OFax;
	}

	public String getOAttribute() {
		return this.OAttribute;
	}

	public void setOAttribute(String OAttribute) {
		this.OAttribute = OAttribute;
	}

	public String getOTmd() {
		return this.OTmd;
	}

	public void setOTmd(String OTmd) {
		this.OTmd = OTmd;
	}

	public String getOOverView() {
		return this.OOverView;
	}

	public void setOOverView(String OOverView) {
		this.OOverView = OOverView;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Set getDemandCollects() {
		return this.demandCollects;
	}

	public void setDemandCollects(Set demandCollects) {
		this.demandCollects = demandCollects;
	}

}