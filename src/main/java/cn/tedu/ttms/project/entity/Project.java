package cn.tedu.ttms.project.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Project implements Serializable{
	private static final long serialVersionUID = -5265879262748186874L;
	private int tid;
	private String tname;
	private String ttname;
	private String type;
	private String content;
	private String lng;//纬度
	private String lat;//经度
	private String ttid;//所属旅行社
	private String ttime;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date starttime;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date endtime;
	private String path;
	private int page;//当前页
	private int total;
	private int rows;
	private int records;
	
	
	
	
	public int getRecords() {
		return records;
	}

	public void setRecords(int records) {
		this.records = records;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getTtname() {
		return ttname;
	}

	public void setTtname(String ttname) {
		this.ttname = ttname;
	}

	public String getTtime() {
		return ttime;
	}

	public void setTtime(String ttime) {
		this.ttime = ttime;
	}

	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public String getTtid() {
		return ttid;
	}

	public void setTtid(String ttid) {
		this.ttid = ttid;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}




}
