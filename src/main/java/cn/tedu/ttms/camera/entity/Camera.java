package cn.tedu.ttms.camera.entity;

import java.io.Serializable;
/**
 *
 */
public class Camera implements Serializable{

	
    /**
	 * 
	 */
	private static final long serialVersionUID = 5677874587179690068L;

	private int cid;
	private String cname;
	private String nChannel;
	private String vid;
	private String model;
	private String brand;
	private String tcode;

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
	public String getnChannel() {
		return nChannel;
	}
	public void setnChannel(String nChannel) {
		this.nChannel = nChannel;
	}
	public String getVid() {
		return vid;
	}
	public void setVid(String vid) {
		this.vid = vid;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getTcode() {
		return tcode;
	}
	public void setTcode(String tcode) {
		this.tcode = tcode;
	}
	@Override
	public String toString() {
		return "Camera [cid=" + cid + ", cname=" + cname + ", nChannel=" + nChannel + ", vid=" + vid + ", model=" + model
				+ ", brand=" + brand + ", tcode=" + tcode + "]";
	}
	
	
	
	
}
