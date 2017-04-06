package com.laijia.mongodb.entity;

import java.util.HashMap;

/**
 * Created by wbq on 2016/10/13.
 */
public class ResultCode {
    //错误代码
    public static HashMap<String, String> codeMsg = new HashMap<String, String>();

    /**
     * 基础服务，包含基础模块或通用错误代码，比如参数错误，服务器异常，以0开头
     */
    public static class Base {
        public static final String SUCCESS_CODE = "0";
        public static final String ERR_PARA_ERROR = "0001";
        public static final String ERR_REQ_ERROR = "0003";
        public static final String ERR_SERVER_EXCEP = "0004";
        public static final String ERR_VALID_CODE = "0005";
        public static final String ERR_DATA_NO_FUND = "0006";
        //签名出错
        public static final String ERR_SIGN_ERROR = "0011";
        public static final String ERR_SAVE_FILE_ERROR = "0012";

        public static final String ERR_LOCATION_ERROR = "0013";
        public static final String ERR_SEND_CODE_ERROR = "0014";
        public static final String ERR_SEND_CODE_FREQUENT = "0015";
        public static final String ERR_FILE_TOO_BIG = "0016";
        public static final String ERR_IMAGE_CAN_NOT_UPLOAD = "0017";
        public static final String ERR_AREA_DATA_NOT_NEW = "0018";
        public static final String ERR_TOKEN = "0019";

        public static final String ERR_AUTH_ERROR = "0020";

        public static void init(HashMap<String, String> codeMsg) {
            codeMsg.put(SUCCESS_CODE, "请求成功");
            codeMsg.put(ERR_PARA_ERROR, "参数不正确");

            //用于对外请求，比如手机号归属地查询
            codeMsg.put(ERR_REQ_ERROR, "请求出错，无法获取内容");
            codeMsg.put(ERR_SERVER_EXCEP, "服务器繁忙,请稍后重试");
            codeMsg.put(ERR_VALID_CODE, "验证码错误");
            codeMsg.put(ERR_DATA_NO_FUND, "查询不到数据");
            codeMsg.put(ERR_SIGN_ERROR, "请求签名出错，无法处理");
            codeMsg.put(ERR_SAVE_FILE_ERROR, "上传文件出错");
            codeMsg.put(ERR_LOCATION_ERROR, "定位信息出错");
            codeMsg.put(ERR_SEND_CODE_ERROR, "发送验证码失败");
            codeMsg.put(ERR_SEND_CODE_FREQUENT, "发送验证码太频繁");
            codeMsg.put(ERR_FILE_TOO_BIG, "上传的文件太大");
            codeMsg.put(ERR_IMAGE_CAN_NOT_UPLOAD, "认证中和认证成功的照片不能重新上传");
            codeMsg.put(ERR_AREA_DATA_NOT_NEW, "行政区划数据需要更新");
            codeMsg.put(ERR_TOKEN, "登录信息已过期，请重新登录");
            codeMsg.put(ERR_AUTH_ERROR, "登录信息已过期，请重新登录");

        }
    }


    /**
     * 账户错误代码，会员模块，以1开头
     */
    public static class Account {

        public static final String ERR_ACCOUNT_NOT_FOUNT = "1001";
        public static final String ERR_MEMB_FREEZE = "1002";
        public static final String ERR_ACCOUNT_AUTH_NOT_FOUNT = "1003";
        public static final String ERR_ACCOUNT_AUTH_IMAGE_NOT_FOUNT = "1004";
        public static final String ERR_ACCOUNT_NOT_AUTH = "1005";

        public static void init(HashMap<String, String> codeMsg) {
            codeMsg.put(ERR_MEMB_FREEZE, "会员已冻结");
            codeMsg.put(ERR_ACCOUNT_NOT_FOUNT, "没有用户信息");
            codeMsg.put(ERR_ACCOUNT_AUTH_NOT_FOUNT, "没有用户认证信息");
            codeMsg.put(ERR_ACCOUNT_AUTH_IMAGE_NOT_FOUNT, "没有用户认证照片信息");
            codeMsg.put(ERR_ACCOUNT_NOT_AUTH, "用户认证失败");

        }
    }

    /**
     * 营销活动错误代码，以2开头
     */
    public static class Activity {
        public static final String ERR_REDEEM_CODE_INVALID = "2008";
        public static final String ERR_REDEEM_CODE_EXPIRE = "2009";
        public static final String ERR_REDEEM_CODE_EXCHANGED = "2010";
        public static final String ERR_GET_ONLY_ONCE = "2011";
        public static final String ERR_EXCEED_COUNT = "2012";
        public static final String ERR_RECEIVE_ERROR = "2013";

        public static void init(HashMap<String, String> codeMsg) {
            codeMsg.put(ERR_REDEEM_CODE_INVALID, "兑换码无效");
            codeMsg.put(ERR_REDEEM_CODE_EXPIRE, "兑换码已过期");
            codeMsg.put(ERR_REDEEM_CODE_EXCHANGED, "兑换码已被兑换");
            codeMsg.put(ERR_GET_ONLY_ONCE, "不能重复领取");
            codeMsg.put(ERR_EXCEED_COUNT, "已领完");
            codeMsg.put(ERR_RECEIVE_ERROR, "领取失败");

        }
    }

    /**
     * 订单模块错误代码，以3开头
     */
    public static class Order {

        public static final String ERR_NOT_PAY_ORDER = "3001";
        public static final String ERR_PROGRESS_ORDER = "3002";
        public static final String ERR_CAR_NO_PRICE = "3003";
        public static final String ERR_ORDER_NOT_FOUND = "3004";
        public static final String ERR_ORDER_CANCEL = "3005";
        public static final String ERR_ORDER_NO_CANCEL_NUM = "3006";
        public static final String ERR_ORDER_END = "3007";
        public static final String ERR_ORDER_END_COUNT = "3008";
        public static final String ERR_ORDER_COUPON = "3009";
        public static final String ERR_ORDER_PAY_AMOUNT_NOT_ENOUGH = "3010";
        public static final String ERR_ORDER_NO_PRICE = "3011";
        public static final String ERR_ORDER_STATUS = "3012";
        public static final String ERR_ORDER_PAY_AMOUNT = "3013";

        public static final String ERR_VIOLATION_STATUS_ERROR = "3014";
        public static final String ERR_DISPOSEWAY_ERROR = "3015";
        public static final String ERR_VIOLATION_NOT_FOUND = "3016";
        public static final String ERR_DEPOSITPAYID_NOT_FOUND = "3017";
        public static final String ERR_NOT_IN_BACKAREA = "3018";

        public static void init(HashMap<String, String> codeMsg) {
            codeMsg.put(ERR_NOT_PAY_ORDER, "未支付的订单");
            codeMsg.put(ERR_PROGRESS_ORDER, "您有一个进行中的行程");
            codeMsg.put(ERR_CAR_NO_PRICE, "车辆未设定价格");
            codeMsg.put(ERR_ORDER_NOT_FOUND, "订单不存在");
            codeMsg.put(ERR_ORDER_CANCEL, "订单无法取消");
            codeMsg.put(ERR_ORDER_NO_CANCEL_NUM, "今日取消订单数量已使用完毕");
            codeMsg.put(ERR_ORDER_END, "订单状态不对，无法结束");
            codeMsg.put(ERR_ORDER_END_COUNT, "订单信息有误无法结束");
            codeMsg.put(ERR_ORDER_COUPON, "优惠卷有误，无法支付");
            codeMsg.put(ERR_ORDER_PAY_AMOUNT_NOT_ENOUGH, "金额不足，无法支付");
            codeMsg.put(ERR_ORDER_NO_PRICE, "订单未计费，无法支付");
            codeMsg.put(ERR_ORDER_STATUS, "订单状态不对");
            codeMsg.put(ERR_ORDER_PAY_AMOUNT, "订单支付金额不符合");
            codeMsg.put(ERR_VIOLATION_STATUS_ERROR, "违章状态不正确");
            codeMsg.put(ERR_DISPOSEWAY_ERROR, "违章处理方式不正确");
            codeMsg.put(ERR_VIOLATION_NOT_FOUND, "没有该违章信息");
            codeMsg.put(ERR_DEPOSITPAYID_NOT_FOUND, "该订单没有关联的保障金");
            codeMsg.put(ERR_NOT_IN_BACKAREA, "不在还车区域,不能还车");
        }
    }

    /**
     * 车俩模块错误代码，以4开头
     */
    public static class Car {

        public static final String ERR_CAR_NOT_FOUND = "4001";
        public static final String ERR_CAR_NOT_USEABLE = "4002";
        public static final String ERR_CAR_AUTH_NOT_FOUND = "4003";
        public static final String ERR_CAR_AUTH_IMAGE_NOT_FOUNT = "4004";
        public static final String ERR_CAR_DEVICE_NOT_FOUND = "4005";
        public static final String ERR_CARMODEL_HAS_CAR = "4006";
        public static final String ERR_TBOX_HAS_CAR = "4007";
        public static final String ERR_CARMODEL_NOT_FOUND = "4008";
        public static final String ERR_VIN_HAS_EXIST = "4009";
        public static final String ERR_LPN_HAS_EXIST = "4010";
        public static final String ERR_ENG_HAS_EXIST = "4011";
        public static final String ERR_TBOX_HAS_EXIST = "4012";

        public static void init(HashMap<String, String> codeMsg) {
            codeMsg.put(ERR_CAR_NOT_FOUND, "车辆信息不存在");
            codeMsg.put(ERR_CAR_NOT_USEABLE, "车辆暂不可用");
            codeMsg.put(ERR_CAR_AUTH_NOT_FOUND, "车辆审核信息不存在");
            codeMsg.put(ERR_CAR_AUTH_IMAGE_NOT_FOUNT, "车辆审核图片信息不存在");
            codeMsg.put(ERR_CAR_DEVICE_NOT_FOUND, "车辆设备信息不存在");
            codeMsg.put(ERR_CARMODEL_HAS_CAR, "该车型已有车辆使用,不能删除");
            codeMsg.put(ERR_TBOX_HAS_CAR, "该终端已有车辆使用,不能删除");
            codeMsg.put(ERR_CARMODEL_NOT_FOUND, "车型不存在");
            codeMsg.put(ERR_VIN_HAS_EXIST, "该车架号已添加");
            codeMsg.put(ERR_LPN_HAS_EXIST, "该车牌号已添加");
            codeMsg.put(ERR_ENG_HAS_EXIST, "该发动机号已添加");
            codeMsg.put(ERR_TBOX_HAS_EXIST, "该设备编码已添加");
        }
    }

    /**
     * 财务模块错误代码，包含支付,以5开头
     */
    public static class Finance {

        public static final String ERR_WALLET_NOT_ENOUGH = "5001";
        public static final String ERR_WX_PAY = "5002";
        public static final String ERR_ALI_PAY = "5003";
        public static final String ERR_PAY_ORDER_FAIL = "5004";
        public static final String ERR_RECHARGE_NOT_FOUND = "5005";
        public static final String ERR_DEPOSIT_NOT_NEED = "5006";
        public static final String ERR_DEPOSIT_AMOUNT_ERROR = "5007";
        public static final String ERR_PAY_ERROR = "5008";
        public static final String ERR_DEPOSIT_NOT_FOUNT = "5009";
        public static final String ERR_DEPOSIT_APPLIED = "5010";
        public static final String ERR_NO_USABLE_DEPOSIT = "5011";
        public static final String ERR_DEPOSIT_LOCKED = "5012";

        public static final String ERR_INVOICE_AMOUNT = "5013";
        public static final String ERR_LOW_INVOICE_AMOUNT = "5014";
        public static final String ERR_MAIL_FEE_ERROR = "5015";
        public static final String ERR_PAY_TYPE_ERROR = "5016";

        public static final String ERR_PRICE_CONFIG_HAS = "5017";
        public static final String ERR_DEPOSIT_RE_BACK = "5018";
        public static final String ERR_DEPOSIT_LOCK = "5019";
        public static final String ERR_DEPOSIT_BACK = "5020";


        public static void init(HashMap<String, String> codeMsg) {
            codeMsg.put(ERR_WALLET_NOT_ENOUGH, "余额不足，无法支付");
            codeMsg.put(ERR_PAY_ERROR, "支付出现错误");
            codeMsg.put(ERR_WX_PAY, "微信支付，出现错误");
            codeMsg.put(ERR_ALI_PAY, "支付宝支付，出现错误");
            codeMsg.put(ERR_PAY_ORDER_FAIL, "支付下单失败");
            codeMsg.put(ERR_RECHARGE_NOT_FOUND, "充值配置有误");
            codeMsg.put(ERR_DEPOSIT_NOT_NEED, "不需要保障金");
            codeMsg.put(ERR_DEPOSIT_AMOUNT_ERROR, "保障金金额配置不正确");
            codeMsg.put(ERR_DEPOSIT_NOT_FOUNT, "没有该笔保障金信息");
            codeMsg.put(ERR_DEPOSIT_APPLIED, "该笔保障金已申请过退还");
            codeMsg.put(ERR_NO_USABLE_DEPOSIT, "没有可用的保障金");
            codeMsg.put(ERR_DEPOSIT_LOCKED, "该笔保障金被锁定");

            codeMsg.put(ERR_INVOICE_AMOUNT, "发票金额大于消费金额");
            codeMsg.put(ERR_LOW_INVOICE_AMOUNT, "发票金额小于最低可开金额");
            codeMsg.put(ERR_MAIL_FEE_ERROR, "邮费金额不正确");
            codeMsg.put(ERR_PAY_TYPE_ERROR, "支付方式有误");
            codeMsg.put(ERR_PRICE_CONFIG_HAS, "租金已经设置过，不能重复设置");
            codeMsg.put(ERR_DEPOSIT_RE_BACK, "保障金正在退还中，不能重复退还");
            codeMsg.put(ERR_DEPOSIT_LOCK, "保障金已经锁定状态，无法退还");
            codeMsg.put(ERR_DEPOSIT_BACK, "保障金退还失败，请联系客服");
        }
    }

    /**
     * 车联网模块错误代码，以6开头
     */
    public static class Iov {
        public static final String ERR_LOAD_INFO = "6001";
        public static final String ERR_CENTRAL_LOCK_STATUS = "6002";
        public static final String ERR_CENTRAL_LOCKING = "6003";
        public static final String ERR_OPT_CAR = "6004";
        public static final String ERR_ENGINE = "6005";
        public static final String ERR_TOTAL_MILEAGE = "6006";
        public static final String ERR_CENTRAL_LOCK_DOOR_OPEN = "6007";
        public static final String ERR_DOOR_OPEN = "6008";

        public static void init(HashMap<String, String> codeMsg) {
            codeMsg.put(ERR_LOAD_INFO, "获取不到车辆设备信息");
            codeMsg.put(ERR_CENTRAL_LOCK_STATUS, "车辆未锁定");
            codeMsg.put(ERR_CENTRAL_LOCKING, "锁车失败，无法还车");
            codeMsg.put(ERR_OPT_CAR, "车辆操控失败，请重试");
            codeMsg.put(ERR_ENGINE, "发动机未熄火，无法还车");
            codeMsg.put(ERR_TOTAL_MILEAGE, "获取不到行驶里程，无法租车");
            codeMsg.put(ERR_CENTRAL_LOCK_DOOR_OPEN, "车门未关，无法锁车");
            codeMsg.put(ERR_DOOR_OPEN, "车门未关，无法还车");
        }
    }

    public static class Sys {
        public static final String ERR_NEED_PASSWARD = "7001";
        public static final String ERR_ORG_NOT_FOUND = "7002";
        public static final String ERR_DEPT_NOT_FOUND = "7003";
        public static final String ERR_DEPT_HAS_CHILD = "7004";
        public static final String ERR_DEPT_HAS_USER = "7005";
        public static final String ERR_POST_HAS_USER = "7006";
        public static final String ERR_AREA_HAS_ORG = "7007";
        public static final String ERR_USER_NOT_FOUND = "7008";
        public static final String ERR_USER_PWD_ERR = "7009";
        public static final String ERR_USER_EXIST = "7010";
        public static final String ERR_USER_DISABLE = "7011";
        public static final String ERR_ORG_DISABLE = "7012";
        public static final String ERR_POST_HAS_CHILD = "7013";

        public static void init(HashMap<String, String> codeMsg) {
            codeMsg.put(ERR_NEED_PASSWARD, "新增用户,密码不能为空");
            codeMsg.put(ERR_ORG_NOT_FOUND, "该组织机构不存在");
            codeMsg.put(ERR_DEPT_NOT_FOUND, "该部门不存在");
            codeMsg.put(ERR_DEPT_HAS_CHILD, "该部门有子级部门,不能删除");
            codeMsg.put(ERR_DEPT_HAS_USER, "该部门有用户,不能删除");
            codeMsg.put(ERR_POST_HAS_USER, "该职位分配给了某个用户,不能删除");
            codeMsg.put(ERR_AREA_HAS_ORG, "该运营城市已设置运营商,不能删除");
            codeMsg.put(ERR_USER_NOT_FOUND, "用户不存在");
            codeMsg.put(ERR_USER_PWD_ERR, "密码错误");
            codeMsg.put(ERR_USER_EXIST, "已有用户使用该手机号");
            codeMsg.put(ERR_USER_DISABLE, "该用户已被禁用");
            codeMsg.put(ERR_ORG_DISABLE, "该用户所属的组织机构已被禁用");
            codeMsg.put(ERR_POST_HAS_CHILD, "该职位有子级职位,不能删除");
        }
    }

    static {
        //公共错误码
        Base.init(codeMsg);
        //会员模块
        Account.init(codeMsg);
        //营销活动模块
        Activity.init(codeMsg);
        //订单模块
        Order.init(codeMsg);
        //车辆信息模块
        Car.init(codeMsg);
        //财务信息模块
        Finance.init(codeMsg);
        //车联网信息模块
        Iov.init(codeMsg);
        //系统管理模块
        Sys.init(codeMsg);
    }

    public static String getMsg(String code) {
        return codeMsg.get(code);
    }

    public static boolean hasCode(String code) {
        return codeMsg.containsKey(code);
    }

    public static void put(String code, String msg) {
        codeMsg.put(code, msg);
    }
}
