package cn.tedu.ttms.travelagency.entity;

import java.io.Serializable;

public class TravelagencyEntity implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5950040109008742137L;
	private int tid;
	private String tcode;
	private String tname;
	private String legal;
	private String contats;
	private String phone;
	private String expire;//许可证到期
	private String stime;
	private String num;
	private String address;
	private String lng;
	private String lat;
	private String user;
	private String passwd;
	
	private int page;//当前页
	private int total;
	private int rows;
	
	
	
	public String getExpire() {
		return expire;
	}
	public void setExpire(String expire) {
		this.expire = expire;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public String getTcode() {
		return tcode;
	}
	public void setTcode(String tcode) {
		this.tcode = tcode;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getStime() {
		return stime;
	}
	public void setStime(String stime) {
		this.stime = stime;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
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
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	
	
	
	
	
}
