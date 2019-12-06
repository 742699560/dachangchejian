package cn.tedu.ttms.attachement.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
public class Attachement implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**主键**/
	private int tid;
	/**人数**/
	private int tnum;
	/**路线**/
	private String tline;
	/**导游名称**/
	private String dname;
	/**导游身份证**/
	private String dcard;
	/**导游电话号码**/  
	private String dphone;
	/**附件**/
	private String dcont;
	/**所属旅行社id**/
	private int ttid;
	/*名称*/
	private String tname;
	private String fnumber;
	private String enumber;
	private int page;//当前页
	private int total;
	private int rows;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdTime;//创建时间
	
	
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
	public String getFnumber() {
		return fnumber;
	}
	public void setFnumber(String fnumber) {
		this.fnumber = fnumber;
	}
	public String getEnumber() {
		return enumber;
	}
	public void setEnumber(String enumber) {
		this.enumber = enumber;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	
	
	
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public int getTnum() {
		return tnum;
	}
	public void setTnum(int tnum) {
		this.tnum = tnum;
	}
	public String getTline() {
		return tline;
	}
	public void setTline(String tline) {
		this.tline = tline;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getDcard() {
		return dcard;
	}
	public void setDcard(String dcard) {
		this.dcard = dcard;
	}
	public String getDphone() {
		return dphone;
	}
	public void setDphone(String dphone) {
		this.dphone = dphone;
	}
	public String getDcont() {
		return dcont;
	}
	public void setDcont(String dcont) {
		this.dcont = dcont;
	}
	public int getTtid() {
		return ttid;
	}
	public void setTtid(int ttid) {
		this.ttid = ttid;
	}
	
	
	
   
	
}
