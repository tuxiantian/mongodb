package com.laijia.mongodb.controler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.laijia.mongodb.entity.CarInfo;
import com.laijia.mongodb.entity.CarLocation;
import com.laijia.mongodb.entity.CarRecord;
import com.laijia.mongodb.entity.Result;
import com.laijia.mongodb.entity.ResultCode;
import com.laijia.mongodb.service.CarRecordService;

@Controller
@RequestMapping(value="devicedata")
public class DeviceDataControler {
	private static final Logger logger =LoggerFactory.getLogger(DeviceDataControler.class);
	@Autowired
	CarRecordService service;
	/**
	 * 保存车的基本信息
	 * @param carInfo
	 */
	@RequestMapping(value="savaCarInfo",method=RequestMethod.POST)
	@ResponseBody
	Result savaCarInfo(@RequestBody CarInfo carInfo){
		service.saveCarInfo(carInfo);
		return new Result(ResultCode.Base.SUCCESS_CODE, ResultCode.getMsg(ResultCode.Base.SUCCESS_CODE), null);
	}

	/**
	 * 保存车的位置相关信息
	 * @param carLocation
	 */
	@RequestMapping(value="saveCarLocation",method=RequestMethod.POST)
	@ResponseBody
	Result saveCarLocation(@RequestBody CarLocation carLocation){
		service.saveCarLocation(carLocation);
		return new Result(ResultCode.Base.SUCCESS_CODE, ResultCode.getMsg(ResultCode.Base.SUCCESS_CODE), null);
	}

	@RequestMapping(value="save",method=RequestMethod.POST)
	@ResponseBody
	Result save(@RequestBody CarRecord record){
		service.save(record);
		return new Result(ResultCode.Base.SUCCESS_CODE, ResultCode.getMsg(ResultCode.Base.SUCCESS_CODE), null);
	}
	
	/**
	 * 查询一辆车最新的状态信息，按receivedTime排序
	 * @param sn
	 * @return CarInfo json
	 */
	@RequestMapping(value="findLastedCarInfo",method=RequestMethod.POST)
	@ResponseBody
	String findLastedCarInfo(@RequestParam String sn){
		return service.findLastedCarInfo(sn);
	}
	
	/**
	 * 查询一辆车最新的位置信息，按gpsTime排序
	 * @param sn
	 * @return CarLocation json
	 */
	@RequestMapping(value="findLastedCarLocation",method=RequestMethod.POST)
	@ResponseBody
	String findLastedCarLocation(@RequestParam String sn){
		return service.findLastedCarLocation(sn);
	}
	
	/**
	 * 查询一辆车最新一条的记录，按receivedTime排序
	 * @param sn
	 * @return CarRecord json
	 */
	@RequestMapping(value="findLastedCarRecord",method=RequestMethod.POST)
	@ResponseBody
	String findLastedCarRecord(@RequestParam String sn){
		return service.findLastedCarRecord(sn);
	}

	/**
	 * 查询一辆车一段时间的记录
	 * @param startTime
	 * @param endTime
	 * @param sn
	 * @return
	 */
	@RequestMapping(value="findBetweenReceivedTime",method=RequestMethod.POST)
	@ResponseBody
	String findBetweenReceivedTime(@RequestParam long startTime,@RequestParam  long endTime,@RequestParam  String sn){
		return service.findBetweenReceivedTime(startTime, endTime, sn);
	}
}
