package cn.tedu.ttms.travelagency.entity;

import java.io.Serializable;

public class ExamineEntity implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -454304140058524722L;
	private int tid; //关联id
	private int eid;
	private String examinetime; //检查时间
	private String content;//内容
	private String result; //结果
	private String exaname;//执法人
	private String name;
	private String number;
	private String time;
	
	private int page;//当前页
	private int total;
	private int rows;
	
	
	
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
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
	@Override
	public String toString() {
		return "ExamineEntity [tid=" + tid + ", eid=" + eid + ", examinetime=" + examinetime + ", content=" + content
				+ ", result=" + result + ", exaname=" + exaname + ", name=" + name + ", number=" + number + ", time="
				+ time + "]";
	}
	
	
	
	
	
	
	
	
	
	
}
