package cn.tedu.ttms.travelagency.entity;

import java.io.Serializable;

public class ExamineNameEntity implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -4385432381595528370L;
	
	private int eid;
	private String examinename;
	private String performer;
	
	

	
	public String getPerformer() {
		return performer;
	}
	public void setPerformer(String performer) {
		this.performer = performer;
	}
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public String getExaminename() {
		return examinename;
	}
	public void setExaminename(String examinename) {
		this.examinename = examinename;
	}
	
	
	
	
	
	
	
	
	
}
