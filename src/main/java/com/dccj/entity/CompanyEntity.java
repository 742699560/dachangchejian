package com.dccj.entity;

import java.io.Serializable;

public class CompanyEntity implements Serializable {

	private static final long serialVersionUID = 5504155391780647881L;
	
	private int cid;
	private String cname;
	private String legal;
	private String contats;
	private String contactnumber;
	private String type;
	private String serialnumber;
	private String site;
	private int page;//当前页
	private int total;
	private int rows;
	private String lng;
	private String lat;
	
	
	
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getLegal() {
		return legal;
	}
	public void setLegal(String legal) {
		this.legal = legal;
	}
	public String getContats() {
		return contats;
	}
	public void setContats(String contats) {
		this.contats = contats;
	}
	public String getContactnumber() {
		return contactnumber;
	}
	public void setContactnumber(String contactnumber) {
		this.contactnumber = contactnumber;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSerialnumber() {
		return serialnumber;
	}
	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	@Override
	public String toString() {
		return "CompanyEntity [cid=" + cid + ", cname=" + cname + ", legal=" + legal + ", contats=" + contats
				+ ", contactnumber=" + contactnumber + ", type=" + type + ", serialnumber=" + serialnumber + ", site="
				+ site + "]";
	}
	
	
	
	
}
