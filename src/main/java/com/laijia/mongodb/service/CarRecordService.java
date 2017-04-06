package com.laijia.mongodb.service;

import java.util.List;

import com.laijia.mongodb.entity.CarInfo;
import com.laijia.mongodb.entity.CarLocation;
import com.laijia.mongodb.entity.CarRecord;

public interface CarRecordService {

	List<CarLocation> getCarLocations(String deviceStr);
	List<CarInfo> getCarInfos(String deviceStr);
	/**
	 * ��ѯһ�������µ�λ����Ϣ����gpsTime����
	 * @param sn
	 * @return CarLocation json
	 */
	public String findLastedCarLocation(String sn);
	
	/**
	 * ��ѯһ�������µ�״̬��Ϣ����receivedTime����
	 * @param sn
	 * @return CarInfo json
	 */
	public String findLastedCarInfo(String sn);
	
	/**
	 * ���泵�Ļ�����Ϣ
	 * @param carInfo
	 */
	void saveCarInfo(CarInfo carInfo);

	/**
	 * ���泵��λ�������Ϣ
	 * @param carLocation
	 */
	void saveCarLocation(CarLocation carLocation);

	void save(CarRecord record);

	/**
	 * ��ѯһ��������һ���ļ�¼����receivedTime����
	 * @param sn
	 * @return CarRecord json
	 */
	String findLastedCarRecord(String sn);

	/**
	 * ��ѯһ����һ��ʱ��ļ�¼
	 * @param startTime
	 * @param endTime
	 * @param sn
	 * @return
	 */
	String findBetweenReceivedTime(long startTime, long endTime, String sn);

}