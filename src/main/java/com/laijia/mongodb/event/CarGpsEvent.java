package com.laijia.mongodb.event;

import com.laijia.mongodb.entity.CarLocation;

public class CarGpsEvent implements Event {

	private CarLocation carLocation;

	public CarGpsEvent(CarLocation carLocation) {
		super();
		this.carLocation = carLocation;
	}

	public CarLocation getCarLocation() {
		return carLocation;
	}
	
}
