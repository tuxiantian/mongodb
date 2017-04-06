package com.laijia.mongodb.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.eventbus.Subscribe;
import com.laijia.mongodb.event.CarStatusEvent;
import com.laijia.mongodb.service.CarRecordService;

@Service
public class CarStatusListener  implements EventListener{

	@Autowired
	CarRecordService service;
	@Subscribe
	public void onCarStatusEvent(CarStatusEvent event) {
		if (event != null && event.getCarInfo() != null) {
			service.saveCarInfo(event.getCarInfo());
		}
	}
}
