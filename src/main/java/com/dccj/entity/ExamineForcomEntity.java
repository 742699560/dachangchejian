package com.dccj.entity;

import java.io.Serializable;

public class ExamineForcomEntity implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2318217636284253793L;
	/**
	 * 
	 */

	private int cid; //关联id
	private int eid;
	private String examinetime; //检查时间
	private String content;//内容
	private String result; //结果
	private String exaname;//执法人
	private String cname;
	private String serialnumber;
	private String type;
	
	
	private int page;//当前页
	private int total;
	private int rows;
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public String getExaminetime() {
		return examinetime;
	}
	public void setExaminetime(String examinetime) {
		this.examinetime = examinetime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getExaname() {
		return exaname;
	}
	public void setExaname(String exaname) {
		this.exaname = exaname;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getSerialnumber() {
		return serialnumber;
	}
	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "ExamineForcomEntity [cid=" + cid + ", eid=" + eid + ", examinetime=" + examinetime + ", content="
				+ content + ", result=" + result + ", exaname=" + exaname + ", cname=" + cname + ", serialnumber="
				+ serialnumber + ", type=" + type + ", page=" + page + ", total=" + total + ", rows=" + rows + "]";
	}

	
	
	
	
	
	
	
	
	
}
