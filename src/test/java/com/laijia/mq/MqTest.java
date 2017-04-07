package com.laijia.mq;

import org.junit.Test;

import com.laijia.mongodb.entity.CarInfo;
import com.laijia.mongodb.util.PropertiesUtil;
import com.laijia.mq.producer.JMSProducer;


public class MqTest {

	@Test
	public void testsavaCarInfo() {
		JMSProducer producer=JMSProducer.getInstance();
		CarInfo info=new CarInfo();
		info.setId(Integer.parseInt("001"));
		info.setSn("001");
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
		producer.send(PropertiesUtil.getActivemqString("queue"), info,4);
	}
	
	@Test
	public void testsaveCarLocation() {
		
	}
}
