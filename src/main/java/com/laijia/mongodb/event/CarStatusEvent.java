package com.laijia.mongodb.event;

import com.laijia.mongodb.entity.CarInfo;

public class CarStatusEvent implements Event{

	private CarInfo carInfo;

	public CarStatusEvent(CarInfo carInfo) {
		this.carInfo = carInfo;
	}

	public CarInfo getCarInfo() {
		return carInfo;
	}
	
}
