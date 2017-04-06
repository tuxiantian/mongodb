package com.laijia.mongodb;

import org.junit.Assert;
import org.junit.Test;

import com.laijia.mongodb.entity.CarInfo;
import com.laijia.mongodb.entity.CarRecord;
import com.laijia.mongodb.service.CarRecordService;
import com.laijia.mongodb.service.impl.CarRecordServiceImpl;

public class CarRecordServiceTest {
	CarRecordService service=new CarRecordServiceImpl();
	
	@Test
	public void testSaveCarInfo() {
		CarInfo info=new CarInfo();
		info.setId(500);
		info.setSn("500");
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
		service.saveCarInfo(info);
	}
	@Test
	public void testSave() {
		/*"id": 0,
		"sn": "250004166",
		"idc": "19250004166",
		"gpsTime": 1491014684000,
		"longitude": 113.561008,
		"latitude": 34.79589,
		"speed": 16,
		"direction": 136,
		"altitude": 109
		"receivedTime": 1491014651000,
		"electricity": "70/100",
		"mileage": "",
		"engineStatus": 1,
		"leftFrontDoor": 0,
		"rightFrontDoor": 0,
		"leftRearDoor": 0,
		"rightRearDoor": 0,
		"centralLckingStatus": 1,
		"lightsStatus": 0,
		"chargeStatus": 0,
		"totalMileage": 2346,
		"voltage": "12.2",
		"on": 1*/
		CarRecord record=new CarRecord();
		record.setId(0);
		record.setSn("250004166");
		record.setIdc("19250004166");
		record.setGpsTime(1491014684000L);
		record.setLongitude(113.561008);
		record.setLatitude(113.561008);
		record.setSpeed(16);
		record.setDirection(136);
		record.setAltitude(109);
		record.setReceivedTime(1491014651002L);
		record.setElectricity("70/100");
		record.setMileage(0.0);
		record.setEngineStatus(1);
		record.setLeftFrontDoor(0);
		record.setRightFrontDoor(1);
		record.setLeftRearDoor(0);
		record.setRightRearDoor(0);
		record.setCentralLckingStatus(1);
		record.setLightsStatus(0);
		record.setChargeStatus(0);
		record.setTotalMileage(2346.0);
		record.setTotalMileage(12.2);
		record.setVoltage("12.2");
		record.setOn(1);
		
		service.save(record);
		
		String sn="250004166";
		System.out.println(service.findLastedCarRecord(sn));
		Assert.assertNotEquals("", service.findLastedCarRecord(sn));
	}
	
	@Test
	public void testFindBetweenReceivedTime() {
		long startTime=1491014651000L;
		long endTime=1491014651002L;
		String sn="250004166";
		String result = service.findBetweenReceivedTime(startTime, endTime, sn="250004166");
		System.out.println(result);
	}
}
