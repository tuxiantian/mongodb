package com.laijia.mongodb.task;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.google.common.eventbus.EventBus;
import com.laijia.mongodb.entity.CarInfo;
import com.laijia.mongodb.entity.CarLocation;
import com.laijia.mongodb.entity.TBox;
import com.laijia.mongodb.event.CarGpsEvent;
import com.laijia.mongodb.event.CarStatusEvent;
import com.laijia.mongodb.service.CarRecordService;

public class TBoxRefreshJob {

	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;
	@Autowired
	private CarRecordService service;
	@Autowired
	private EventBus eventBus;
	private static final int BATCH_NUM = 30;
	/**
	 * 加载在租车辆，并将刷新加入线程池执行
	 */
	public void refreshRentCars() {

		List<TBox> list = new ArrayList<TBox>(500);
		for (int i = 1; i <= 500; i++) {
			if (i<10) {
				list.add(new TBox("00"+i));
			}else if (i<100) {
				list.add(new TBox("0"+i));
			}else{
				list.add(new TBox(""+i));
			}
		}
		//休眠5秒
		int sleepTime = 5 * 1000;
		doInTask(list, sleepTime);
	}
	private void doInTask(List<TBox> list, int sleepTime) {
		if (list != null && list.size() > 0 && sleepTime > 0) {
			List<List<TBox>> thirtyList = new ArrayList<>();
			List<TBox> temp = new ArrayList<>();
			//每次请求消耗20秒
			int i = 0;
			for (int j = 0; j < list.size(); j++) {
				TBox tBox = list.get(j);
				if (j == 0) {
					thirtyList.add(temp);
				}
				temp.add(tBox);
				if (j > 0 && j % BATCH_NUM == 0) {
					temp = new ArrayList<>();
					thirtyList.add(temp);
				}
			}

			for (List<TBox> tBoxes : thirtyList) {
				taskExecutor.execute(new Load(tBoxes,service,eventBus));
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static class Load implements Runnable {
		List<TBox> tBoxes;
		CarRecordService service;
		EventBus eventBus;
		public Load(List<TBox> tBoxes, CarRecordService service, EventBus eventBus) {
			this.tBoxes=tBoxes;
			this.service=service;
			this.eventBus=eventBus;
		}

		public void run() {
			StringBuffer sb = new StringBuffer();
            for (TBox tBox : tBoxes) {
                sb.append(tBox.getDeviceId() + ",");
            }
            if (sb.length() > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
            List<CarInfo> carInfos = service.getCarInfos(sb.toString());
            for (CarInfo carInfo : carInfos) {
				eventBus.post(new CarStatusEvent(carInfo));
			}
            List<CarLocation> carLocations = service.getCarLocations(sb.toString());
            for (CarLocation carLocation : carLocations) {
				eventBus.post(new CarGpsEvent(carLocation));
			}
		}

	}
}
