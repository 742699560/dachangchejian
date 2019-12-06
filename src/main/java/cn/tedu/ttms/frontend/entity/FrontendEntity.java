package cn.tedu.ttms.frontend.entity;

public class FrontendEntity {
	
   private String name;
   private Double lng;
   private Double lat;
   
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getLng() {
		return lng;
	}
	public void setLng(Double lng) {
		this.lng = lng;
	}
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public FrontendEntity(String name, Double lng, Double lat) {
		super();
		this.name = name;
		this.lng = lng;
		this.lat = lat;
	}
	public FrontendEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
   
   
   
}
