package cn.tedu.ttms.homeMessage.entity;

public class HomeMessageEntity {

	private int dueapprove;//已过期
	private int aboutprove;//将过期
	private int comnumner;//旅行社总量
	private int talnumner;//公司数量
	private String name;
	private String expire;
	
	
	
	public String getExpire() {
		return expire;
	}
	public void setExpire(String expire) {
		this.expire = expire;
	}
	public int getDueapprove() {
		return dueapprove;
	}
	public void setDueapprove(int dueapprove) {
		this.dueapprove = dueapprove;
	}
	public int getAboutprove() {
		return aboutprove;
	}
	public void setAboutprove(int aboutprove) {
		this.aboutprove = aboutprove;
	}
	public int getComnumner() {
		return comnumner;
	}
	public void setComnumner(int comnumner) {
		this.comnumner = comnumner;
	}
	public int getTalnumner() {
		return talnumner;
	}
	public void setTalnumner(int talnumner) {
		this.talnumner = talnumner;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "HomeMessageEntity [dueapprove=" + dueapprove + ", aboutprove=" + aboutprove + ", comnumner=" + comnumner
				+ ", talnumner=" + talnumner + ", name=" + name + ", expire=" + expire + "]";
	}
	
	
	
}
