package com.laijia.mongodb.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.eventbus.Subscribe;
import com.laijia.mongodb.event.CarGpsEvent;
import com.laijia.mongodb.service.CarRecordService;
import com.laijia.mongodb.util.PropertiesUtil;
import com.laijia.mq.producer.JMSProducer;

@Service
public class CarGpsListener implements EventListener{
	@Autowired
	CarRecordService service;
	
	@Subscribe
	public void onCarStatusEvent(CarGpsEvent event) {
		if (event != null && event.getCarLocation() != null) {
//			service.saveCarLocation(event.getCarLocation());
			JMSProducer.getInstance().send(PropertiesUtil.getActivemqString("queueName"), event.getCarLocation(), 4);
		}
	}
}
