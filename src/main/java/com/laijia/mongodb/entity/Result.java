package com.laijia.mongodb.entity;

import java.io.Serializable;

/**
 * 返回的结果
 * Created by wbq on 2016/10/13.
 */
public class Result implements Serializable {

    private String return_code;
    private String return_msg;
    private MapBean data = new MapBean();


    public Result() {
    }

    public Result(String return_code, String return_msg, MapBean data) {
        this.return_code = return_code;
        this.return_msg = return_msg;
        this.data = data;
    }

    public static Result success(String msg, MapBean data) {
        return new Result(ResultCode.Base.SUCCESS_CODE, msg, data);
    }

    public static Result fail(String code, String msg, MapBean data) {
        return new Result(code, msg, data);
    }

    public static Result fail(String code, MapBean data) {
        return new Result(code, ResultCode.getMsg(code), data);
    }

    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }

    public MapBean getData() {
        return data;
    }

    public void setData(MapBean data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return ResultCode.Base.SUCCESS_CODE.equals(this.return_code);
    }

    public static Result code(String code, MapBean data) {
        Result result = new Result();
        result.setReturn_code(code);
        result.setReturn_msg(ResultCode.getMsg(code));
        result.data = data;
        return result;
    }
}
