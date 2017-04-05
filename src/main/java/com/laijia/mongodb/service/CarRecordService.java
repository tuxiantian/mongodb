package com.laijia.mongodb.service;

import com.laijia.mongodb.entity.CarInfo;
import com.laijia.mongodb.entity.CarLocation;
import com.laijia.mongodb.entity.CarRecord;

public interface CarRecordService {

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