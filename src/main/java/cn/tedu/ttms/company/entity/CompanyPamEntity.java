package cn.tedu.ttms.company.entity;

import java.io.Serializable;

public class CompanyPamEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -520981058679882311L;

	private int comnumber;
	private int tnumber;
	public int getComnumber() {
		return comnumber;
	}
	public void setComnumber(int comnumber) {
		this.comnumber = comnumber;
	}
	public int getTnumber() {
		return tnumber;
	}
	public void setTnumber(int tnumber) {
		this.tnumber = tnumber;
	}
	@Override
	public String toString() {
		return "CompanyPamEntity [comnumber=" + comnumber + ", tnumber=" + tnumber + "]";
	}
	
	
	
	
	
}
