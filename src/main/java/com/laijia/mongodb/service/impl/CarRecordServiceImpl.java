package com.laijia.mongodb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.laijia.mongodb.entity.CarInfo;
import com.laijia.mongodb.entity.CarLocation;
import com.laijia.mongodb.entity.CarRecord;
import com.laijia.mongodb.service.CarRecordService;
import com.laijia.mongodb.util.MongoDBUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.util.JSON;

public class CarRecordServiceImpl implements CarRecordService {
	static final String COLLECTION_PREFIX="col";
	static final String COLLECTION_PREFIX_CARINFO="carInfo";
	static final String COLLECTION_PREFIX_CARLOCATION="carLocation";
	
	
	/**
	 * 保存车的基本信息
	 * @param carInfo
	 */
	public void saveCarInfo(CarInfo carInfo) {
		Document document=new Document().append("id", carInfo.getId())
				.append("sn", carInfo.getSn())
				.append("idc", carInfo.getIdc())
				.append("receivedTime", carInfo.getReceivedTime())
				.append("electricity", carInfo.getElectricity())
				.append("mileage", carInfo.getMileage())
				.append("engineStatus", carInfo.getEngineStatus())
				.append("leftFrontDoor", carInfo.getLeftFrontDoor())
				.append("rightFrontDoor", carInfo.getRightFrontDoor())
				.append("leftRearDoor", carInfo.getLeftRearDoor())
				.append("rightRearDoor", carInfo.getRightRearDoor())
				.append("centralLckingStatus", carInfo.getCentralLckingStatus())
				.append("lightsStatus", carInfo.getLightsStatus())
				.append("chargeStatus", carInfo.getChargeStatus())
				.append("totalMileage", carInfo.getTotalMileage())
				.append("voltage", carInfo.getVoltage())
				.append("on", carInfo.getOn());
		
		MongoCollection<Document> collection = MongoDBUtil.getMongoDatabase().getCollection(COLLECTION_PREFIX_CARINFO+carInfo.getSn());
		collection.insertOne(document);
	}
	
	/**
	 * 保存车的位置相关信息
	 * @param carLocation
	 */
	public void saveCarLocation(CarLocation carLocation) {
		Document document=new Document().append("id", carLocation.getId())
				.append("sn", carLocation.getSn())
				.append("idc", carLocation.getIdc())
				.append("gpsTime", carLocation.getGpsTime())
				.append("longitude", carLocation.getLongitude())
				.append("latitude", carLocation.getLatitude())
				.append("speed", carLocation.getSpeed())
				.append("direction", carLocation.getDirection())
				.append("altitude", carLocation.getAltitude());
				
		MongoCollection<Document> collection = MongoDBUtil.getMongoDatabase().getCollection(COLLECTION_PREFIX_CARLOCATION+carLocation.getSn());
		collection.insertOne(document);
	}
	@Deprecated
	public void save(CarRecord record) {
		Document document=new Document().append("id", record.getId())
				.append("sn", record.getSn())
				.append("idc", record.getIdc())
				.append("gpsTime", record.getGpsTime())
				.append("longitude", record.getLongitude())
				.append("latitude", record.getLatitude())
				.append("speed", record.getSpeed())
				.append("direction", record.getDirection())
				.append("altitude", record.getAltitude())
				.append("receivedTime", record.getReceivedTime())
				.append("electricity", record.getElectricity())
				.append("mileage", record.getMileage())
				.append("engineStatus", record.getEngineStatus())
				.append("leftFrontDoor", record.getLeftFrontDoor())
				.append("rightFrontDoor", record.getRightFrontDoor())
				.append("leftRearDoor", record.getLeftRearDoor())
				.append("rightRearDoor", record.getRightRearDoor())
				.append("centralLckingStatus", record.getCentralLckingStatus())
				.append("lightsStatus", record.getLightsStatus())
				.append("chargeStatus", record.getChargeStatus())
				.append("totalMileage", record.getTotalMileage())
				.append("voltage", record.getVoltage())
				.append("on", record.getOn());
		MongoCollection<Document> collection = MongoDBUtil.getMongoDatabase().getCollection(COLLECTION_PREFIX+record.getSn());
		collection.insertOne(document);
	}
	
	/**
	 * 查询一辆车最新的状态信息，按receivedTime排序
	 * @param sn
	 * @return CarInfo json
	 */
	public String findLastedCarInfo(String sn) {
		BasicDBObject sort=new BasicDBObject("receivedTime", -1);
		FindIterable<Document> findIterable = MongoDBUtil.getMongoDatabase().getCollection(COLLECTION_PREFIX_CARINFO+sn).find().sort(sort);
		MongoCursor<Document> mongoCursor=findIterable.iterator();
		while (mongoCursor.hasNext()) {
			return JSON.serialize(mongoCursor.next());
		}
		return "";
	}
	
	/**
	 * 查询一辆车最新的位置信息，按gpsTime排序
	 * @param sn
	 * @return CarLocation json
	 */
	public String findLastedCarLocation(String sn) {
		BasicDBObject sort=new BasicDBObject("gpsTime", -1);
		FindIterable<Document> findIterable = MongoDBUtil.getMongoDatabase().getCollection(COLLECTION_PREFIX_CARLOCATION+sn).find().sort(sort);
		MongoCursor<Document> mongoCursor=findIterable.iterator();
		while (mongoCursor.hasNext()) {
			return JSON.serialize(mongoCursor.next());
		}
		return "";
	}
	
	/**
	 * 查询一辆车最新一条的记录，按receivedTime排序
	 * @param sn
	 * @return CarRecord json
	 */
	@Deprecated
	public String findLastedCarRecord(String sn) {
		BasicDBObject sort=new BasicDBObject("receivedTime", -1);
		FindIterable<Document> findIterable = MongoDBUtil.getMongoDatabase().getCollection(COLLECTION_PREFIX+sn).find().sort(sort);
		MongoCursor<Document> mongoCursor=findIterable.iterator();
		while (mongoCursor.hasNext()) {
			return JSON.serialize(mongoCursor.next());
		}
		return "";
	}
	
	/**
	 * 查询一辆车一段时间的记录
	 * @param startTime
	 * @param endTime
	 * @param sn
	 * @return
	 */
	@Deprecated
	public String findBetweenReceivedTime(long startTime,long endTime,String sn) {
		BasicDBObject condition=new BasicDBObject()
		.append("receivedTime", new BasicDBObject("$gte", startTime))
		.append("receivedTime", new BasicDBObject("$lte", endTime));
		FindIterable<Document> findIterable =MongoDBUtil.getMongoDatabase().getCollection(COLLECTION_PREFIX+sn).find(condition);
		MongoCursor<Document> mongoCursor=findIterable.iterator();
		StringBuilder builder=new StringBuilder();
		List<Document> result=new ArrayList<Document>();
		while (mongoCursor.hasNext()) {
			result.add(mongoCursor.next());
		}
		JSON.serialize(result);
		return JSON.serialize(result);
	}
	
	public List<CarLocation> getCarLocations(String deviceStr) {
		String[] devices = deviceStr.split(",");
		List<CarLocation> carLocations=new ArrayList<>(devices.length);
		for (int i = 0; i < devices.length; i++) {
			CarLocation location=new CarLocation();
			location.setAltitude(12);
			location.setDirection(14);
			location.setGpsTime(System.currentTimeMillis());
			location.setId(Integer.parseInt(devices[i]));
			location.setIdc("123");
			location.setLatitude(12.0);
			location.setSn(devices[i]);
			location.setSpeed(100);
			carLocations.add(location);
		}
		return carLocations;
	}
	public List<CarInfo> getCarInfos(String deviceStr) {
		String[] devices = deviceStr.split(",");
		List<CarInfo> carInfos=new ArrayList<>(devices.length);
		for (int i = 0; i < devices.length; i++) {
			CarInfo info=new CarInfo();
			info.setId(Integer.parseInt(devices[i]));
			info.setSn(devices[i]);
			info.setCentralLckingStatus(1);
			info.setChargeStatus(1);
			info.setElectricity("60/100");
			info.setIdc("123");
			info.setLeftFrontDoor(1);
			info.setLeftRearDoor(1);
			info.setLightsStatus(1);
			info.setMileage(12.0);
			info.setOn(0);
			info.setReceivedTime(System.currentTimeMillis());
			info.setRightFrontDoor(1);
			info.setRightRearDoor(1);
			info.setTotalMileage(24.0);
			info.setVoltage("12.0");
			carInfos.add(info);
		}
		return carInfos;
	}
}
