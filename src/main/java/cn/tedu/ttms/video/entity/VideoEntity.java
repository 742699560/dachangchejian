package cn.tedu.ttms.video.entity;

import java.io.Serializable;

public class VideoEntity implements Serializable {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 349495065680949665L;
	private int vid;
	private String tcode;
	private String vname;
	private String type;
	private String longitude;
	private String latitude;
	private String dvrIP;
	private String vport;
	private String userName;
	private String number;
	private int page;//当前页
	private int total;
	private int rows;
	
	
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public int getVid() {
		return vid;
	}
	public void setVid(int vid) {
		this.vid = vid;
	}
	
	public String getTcode() {
		return tcode;
	}
	public void setTcode(String tcode) {
		this.tcode = tcode;
	}
	public String getVname() {
		return vname;
	}
	public void setVname(String vname) {
		this.vname = vname;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getDvrIP() {
		return dvrIP;
	}
	public void setDvrIP(String dvrIP) {
		this.dvrIP = dvrIP;
	}
	public String getVport() {
		return vport;
	}
	public void setVport(String vport) {
		this.vport = vport;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	
	
	
	
	
	
	
}
