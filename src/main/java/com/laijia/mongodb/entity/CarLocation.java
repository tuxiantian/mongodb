package com.laijia.mongodb.entity;

public class CarLocation {
	private Integer id;
	private String sn;
	private String idc;
	private Long gpsTime;
	private Double longitude;
	private Double latitude;
	private Integer speed;
	private Integer direction;
	private Integer altitude;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getIdc() {
		return idc;
	}
	public void setIdc(String idc) {
		this.idc = idc;
	}
	public Long getGpsTime() {
		return gpsTime;
	}
	public void setGpsTime(Long gpsTime) {
		this.gpsTime = gpsTime;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Integer getSpeed() {
		return speed;
	}
	public void setSpeed(Integer speed) {
		this.speed = speed;
	}
	public Integer getDirection() {
		return direction;
	}
	public void setDirection(Integer direction) {
		this.direction = direction;
	}
	public Integer getAltitude() {
		return altitude;
	}
	public void setAltitude(Integer altitude) {
		this.altitude = altitude;
	}
	
}
