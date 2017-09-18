package com.stdcMis.Vo;

import java.io.UnsupportedEncodingException;

public class UserInfo{

	private String code;
	private String password;
	private String conPwd;
	private String username;
	private String address;
	private String area;
	private String url;
	private String email;
	private String lperson;
	private String postcode;
	private String contacts;
	private String plphone;
	private String mbphone;
	private String fax;
	private String attribute;
	private String tmd;
	private String overView;
	private String role;
	private String randcode;
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConPwd() {
		return conPwd;
	}
	public void setConPwd(String conPwd) {
		this.conPwd = conPwd;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		try {
			this.username = java.net.URLDecoder.decode(username,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLperson() {
		return lperson;
	}
	public void setLperson(String lperson) {
		this.lperson = lperson;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getContacts() {
		return contacts;
	}
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	public String getPlphone() {
		return plphone;
	}
	public void setPlphone(String plphone) {
		this.plphone = plphone;
	}
	public String getMbphone() {
		return mbphone;
	}
	public void setMbphone(String mbphone) {
		this.mbphone = mbphone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getAttribute() {
		return attribute;
	}
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	public String getTmd() {
		return tmd;
	}
	public void setTmd(String tmd) {
		this.tmd = tmd;
	}
	public String getOverView() {
		return overView;
	}
	public void setOverView(String overView) {
		this.overView = overView;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getRandcode() {
		return randcode;
	}
	public void setRandcode(String randcode) {
		this.randcode = randcode;
	}
	
}
