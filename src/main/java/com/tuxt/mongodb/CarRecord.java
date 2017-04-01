package com.tuxt.mongodb;

/**
 * 车辆的采集数据
 * @author tuxt
 *
 */
public class CarRecord {
	private Integer id;
	private String sn;
	private String idc;
	private Long gpsTime;
	private Double longitude;
	private Double latitude;
	private Integer speed;
	private Integer direction;
	private Integer altitude;
	private Long receivedTime;
	private String electricity;
	private Double mileage;
	private Integer engineStatus;
	private Integer leftFrontDoor;
	private Integer rightFrontDoor;
	private Integer leftRearDoor;
	private Integer rightRearDoor;
	private Integer centralLckingStatus;
	private Integer lightsStatus;
	private Integer chargeStatus;
	private Double totalMileage;
	private String voltage;
	private Integer on;
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
	public Long getReceivedTime() {
		return receivedTime;
	}
	public void setReceivedTime(Long receivedTime) {
		this.receivedTime = receivedTime;
	}
	public String getElectricity() {
		return electricity;
	}
	public void setElectricity(String electricity) {
		this.electricity = electricity;
	}
	public Double getMileage() {
		return mileage;
	}
	public void setMileage(Double mileage) {
		this.mileage = mileage;
	}
	public Integer getEngineStatus() {
		return engineStatus;
	}
	public void setEngineStatus(Integer engineStatus) {
		this.engineStatus = engineStatus;
	}
	public Integer getLeftFrontDoor() {
		return leftFrontDoor;
	}
	public void setLeftFrontDoor(Integer leftFrontDoor) {
		this.leftFrontDoor = leftFrontDoor;
	}
	public Integer getRightFrontDoor() {
		return rightFrontDoor;
	}
	public void setRightFrontDoor(Integer rightFrontDoor) {
		this.rightFrontDoor = rightFrontDoor;
	}
	public Integer getLeftRearDoor() {
		return leftRearDoor;
	}
	public void setLeftRearDoor(Integer leftRearDoor) {
		this.leftRearDoor = leftRearDoor;
	}
	public Integer getRightRearDoor() {
		return rightRearDoor;
	}
	public void setRightRearDoor(Integer rightRearDoor) {
		this.rightRearDoor = rightRearDoor;
	}
	public Integer getCentralLckingStatus() {
		return centralLckingStatus;
	}
	public void setCentralLckingStatus(Integer centralLckingStatus) {
		this.centralLckingStatus = centralLckingStatus;
	}
	public Integer getLightsStatus() {
		return lightsStatus;
	}
	public void setLightsStatus(Integer lightsStatus) {
		this.lightsStatus = lightsStatus;
	}
	public Integer getChargeStatus() {
		return chargeStatus;
	}
	public void setChargeStatus(Integer chargeStatus) {
		this.chargeStatus = chargeStatus;
	}
	public Double getTotalMileage() {
		return totalMileage;
	}
	public void setTotalMileage(Double totalMileage) {
		this.totalMileage = totalMileage;
	}
	public String getVoltage() {
		return voltage;
	}
	public void setVoltage(String voltage) {
		this.voltage = voltage;
	}
	public Integer getOn() {
		return on;
	}
	public void setOn(Integer on) {
		this.on = on;
	}
	
}
