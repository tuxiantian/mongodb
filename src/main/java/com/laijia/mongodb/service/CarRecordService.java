package com.laijia.mongodb.service;

import java.util.List;

import com.laijia.mongodb.entity.CarInfo;
import com.laijia.mongodb.entity.CarLocation;
import com.laijia.mongodb.entity.CarRecord;

public interface CarRecordService {

	List<CarLocation> getCarLocations(String deviceStr);
	List<CarInfo> getCarInfos(String deviceStr);
	/**
	 * 查询一辆车最新的位置信息，按gpsTime排序
	 * @param sn
	 * @return CarLocation json
	 */
	public String findLastedCarLocation(String sn);
	
	/**
	 * 查询一辆车最新的状态信息，按receivedTime排序
	 * @param sn
	 * @return CarInfo json
	 */
	public String findLastedCarInfo(String sn);
	
	/**
	 * 保存车的基本信息
	 * @param carInfo
	 */
	void saveCarInfo(CarInfo carInfo);

	/**
	 * 保存车的位置相关信息
	 * @param carLocation
	 */
	void saveCarLocation(CarLocation carLocation);

	void save(CarRecord record);

	/**
	 * 查询一辆车最新一条的记录，按receivedTime排序
	 * @param sn
	 * @return CarRecord json
	 */
	String findLastedCarRecord(String sn);

	/**
	 * 查询一辆车一段时间的记录
	 * @param startTime
	 * @param endTime
	 * @param sn
	 * @return
	 */
	String findBetweenReceivedTime(long startTime, long endTime, String sn);

}