package com.laijia.mongodb.entity;

import java.util.HashMap;

/**
 * Created by wbq on 2016/10/13.
 */
public class ResultCode {
    //�������
    public static HashMap<String, String> codeMsg = new HashMap<String, String>();

    /**
     * �������񣬰�������ģ���ͨ�ô�����룬����������󣬷������쳣����0��ͷ
     */
    public static class Base {
        public static final String SUCCESS_CODE = "0";
        public static final String ERR_PARA_ERROR = "0001";
        public static final String ERR_REQ_ERROR = "0003";
        public static final String ERR_SERVER_EXCEP = "0004";
        public static final String ERR_VALID_CODE = "0005";
        public static final String ERR_DATA_NO_FUND = "0006";
        //ǩ������
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
            codeMsg.put(SUCCESS_CODE, "����ɹ�");
            codeMsg.put(ERR_PARA_ERROR, "��������ȷ");

            //���ڶ������󣬱����ֻ��Ź����ز�ѯ
            codeMsg.put(ERR_REQ_ERROR, "��������޷���ȡ����");
            codeMsg.put(ERR_SERVER_EXCEP, "��������æ,���Ժ�����");
            codeMsg.put(ERR_VALID_CODE, "��֤�����");
            codeMsg.put(ERR_DATA_NO_FUND, "��ѯ��������");
            codeMsg.put(ERR_SIGN_ERROR, "����ǩ�������޷�����");
            codeMsg.put(ERR_SAVE_FILE_ERROR, "�ϴ��ļ�����");
            codeMsg.put(ERR_LOCATION_ERROR, "��λ��Ϣ����");
            codeMsg.put(ERR_SEND_CODE_ERROR, "������֤��ʧ��");
            codeMsg.put(ERR_SEND_CODE_FREQUENT, "������֤��̫Ƶ��");
            codeMsg.put(ERR_FILE_TOO_BIG, "�ϴ����ļ�̫��");
            codeMsg.put(ERR_IMAGE_CAN_NOT_UPLOAD, "��֤�к���֤�ɹ�����Ƭ���������ϴ�");
            codeMsg.put(ERR_AREA_DATA_NOT_NEW, "��������������Ҫ����");
            codeMsg.put(ERR_TOKEN, "��¼��Ϣ�ѹ��ڣ������µ�¼");
            codeMsg.put(ERR_AUTH_ERROR, "��¼��Ϣ�ѹ��ڣ������µ�¼");

        }
    }


    /**
     * �˻�������룬��Աģ�飬��1��ͷ
     */
    public static class Account {

        public static final String ERR_ACCOUNT_NOT_FOUNT = "1001";
        public static final String ERR_MEMB_FREEZE = "1002";
        public static final String ERR_ACCOUNT_AUTH_NOT_FOUNT = "1003";
        public static final String ERR_ACCOUNT_AUTH_IMAGE_NOT_FOUNT = "1004";
        public static final String ERR_ACCOUNT_NOT_AUTH = "1005";

        public static void init(HashMap<String, String> codeMsg) {
            codeMsg.put(ERR_MEMB_FREEZE, "��Ա�Ѷ���");
            codeMsg.put(ERR_ACCOUNT_NOT_FOUNT, "û���û���Ϣ");
            codeMsg.put(ERR_ACCOUNT_AUTH_NOT_FOUNT, "û���û���֤��Ϣ");
            codeMsg.put(ERR_ACCOUNT_AUTH_IMAGE_NOT_FOUNT, "û���û���֤��Ƭ��Ϣ");
            codeMsg.put(ERR_ACCOUNT_NOT_AUTH, "�û���֤ʧ��");

        }
    }

    /**
     * Ӫ���������룬��2��ͷ
     */
    public static class Activity {
        public static final String ERR_REDEEM_CODE_INVALID = "2008";
        public static final String ERR_REDEEM_CODE_EXPIRE = "2009";
        public static final String ERR_REDEEM_CODE_EXCHANGED = "2010";
        public static final String ERR_GET_ONLY_ONCE = "2011";
        public static final String ERR_EXCEED_COUNT = "2012";
        public static final String ERR_RECEIVE_ERROR = "2013";

        public static void init(HashMap<String, String> codeMsg) {
            codeMsg.put(ERR_REDEEM_CODE_INVALID, "�һ�����Ч");
            codeMsg.put(ERR_REDEEM_CODE_EXPIRE, "�һ����ѹ���");
            codeMsg.put(ERR_REDEEM_CODE_EXCHANGED, "�һ����ѱ��һ�");
            codeMsg.put(ERR_GET_ONLY_ONCE, "�����ظ���ȡ");
            codeMsg.put(ERR_EXCEED_COUNT, "������");
            codeMsg.put(ERR_RECEIVE_ERROR, "��ȡʧ��");

        }
    }

    /**
     * ����ģ�������룬��3��ͷ
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
            codeMsg.put(ERR_NOT_PAY_ORDER, "δ֧���Ķ���");
            codeMsg.put(ERR_PROGRESS_ORDER, "����һ�������е��г�");
            codeMsg.put(ERR_CAR_NO_PRICE, "����δ�趨�۸�");
            codeMsg.put(ERR_ORDER_NOT_FOUND, "����������");
            codeMsg.put(ERR_ORDER_CANCEL, "�����޷�ȡ��");
            codeMsg.put(ERR_ORDER_NO_CANCEL_NUM, "����ȡ������������ʹ�����");
            codeMsg.put(ERR_ORDER_END, "����״̬���ԣ��޷�����");
            codeMsg.put(ERR_ORDER_END_COUNT, "������Ϣ�����޷�����");
            codeMsg.put(ERR_ORDER_COUPON, "�Żݾ������޷�֧��");
            codeMsg.put(ERR_ORDER_PAY_AMOUNT_NOT_ENOUGH, "���㣬�޷�֧��");
            codeMsg.put(ERR_ORDER_NO_PRICE, "����δ�Ʒѣ��޷�֧��");
            codeMsg.put(ERR_ORDER_STATUS, "����״̬����");
            codeMsg.put(ERR_ORDER_PAY_AMOUNT, "����֧��������");
            codeMsg.put(ERR_VIOLATION_STATUS_ERROR, "Υ��״̬����ȷ");
            codeMsg.put(ERR_DISPOSEWAY_ERROR, "Υ�´���ʽ����ȷ");
            codeMsg.put(ERR_VIOLATION_NOT_FOUND, "û�и�Υ����Ϣ");
            codeMsg.put(ERR_DEPOSITPAYID_NOT_FOUND, "�ö���û�й����ı��Ͻ�");
            codeMsg.put(ERR_NOT_IN_BACKAREA, "���ڻ�������,���ܻ���");
        }
    }

    /**
     * ����ģ�������룬��4��ͷ
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
            codeMsg.put(ERR_CAR_NOT_FOUND, "������Ϣ������");
            codeMsg.put(ERR_CAR_NOT_USEABLE, "�����ݲ�����");
            codeMsg.put(ERR_CAR_AUTH_NOT_FOUND, "���������Ϣ������");
            codeMsg.put(ERR_CAR_AUTH_IMAGE_NOT_FOUNT, "�������ͼƬ��Ϣ������");
            codeMsg.put(ERR_CAR_DEVICE_NOT_FOUND, "�����豸��Ϣ������");
            codeMsg.put(ERR_CARMODEL_HAS_CAR, "�ó������г���ʹ��,����ɾ��");
            codeMsg.put(ERR_TBOX_HAS_CAR, "���ն����г���ʹ��,����ɾ��");
            codeMsg.put(ERR_CARMODEL_NOT_FOUND, "���Ͳ�����");
            codeMsg.put(ERR_VIN_HAS_EXIST, "�ó��ܺ������");
            codeMsg.put(ERR_LPN_HAS_EXIST, "�ó��ƺ������");
            codeMsg.put(ERR_ENG_HAS_EXIST, "�÷������������");
            codeMsg.put(ERR_TBOX_HAS_EXIST, "���豸���������");
        }
    }

    /**
     * ����ģ�������룬����֧��,��5��ͷ
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
            codeMsg.put(ERR_WALLET_NOT_ENOUGH, "���㣬�޷�֧��");
            codeMsg.put(ERR_PAY_ERROR, "֧�����ִ���");
            codeMsg.put(ERR_WX_PAY, "΢��֧�������ִ���");
            codeMsg.put(ERR_ALI_PAY, "֧����֧�������ִ���");
            codeMsg.put(ERR_PAY_ORDER_FAIL, "֧���µ�ʧ��");
            codeMsg.put(ERR_RECHARGE_NOT_FOUND, "��ֵ��������");
            codeMsg.put(ERR_DEPOSIT_NOT_NEED, "����Ҫ���Ͻ�");
            codeMsg.put(ERR_DEPOSIT_AMOUNT_ERROR, "���Ͻ������ò���ȷ");
            codeMsg.put(ERR_DEPOSIT_NOT_FOUNT, "û�иñʱ��Ͻ���Ϣ");
            codeMsg.put(ERR_DEPOSIT_APPLIED, "�ñʱ��Ͻ���������˻�");
            codeMsg.put(ERR_NO_USABLE_DEPOSIT, "û�п��õı��Ͻ�");
            codeMsg.put(ERR_DEPOSIT_LOCKED, "�ñʱ��Ͻ�����");

            codeMsg.put(ERR_INVOICE_AMOUNT, "��Ʊ���������ѽ��");
            codeMsg.put(ERR_LOW_INVOICE_AMOUNT, "��Ʊ���С����Ϳɿ����");
            codeMsg.put(ERR_MAIL_FEE_ERROR, "�ʷѽ���ȷ");
            codeMsg.put(ERR_PAY_TYPE_ERROR, "֧����ʽ����");
            codeMsg.put(ERR_PRICE_CONFIG_HAS, "����Ѿ����ù��������ظ�����");
            codeMsg.put(ERR_DEPOSIT_RE_BACK, "���Ͻ������˻��У������ظ��˻�");
            codeMsg.put(ERR_DEPOSIT_LOCK, "���Ͻ��Ѿ�����״̬���޷��˻�");
            codeMsg.put(ERR_DEPOSIT_BACK, "���Ͻ��˻�ʧ�ܣ�����ϵ�ͷ�");
        }
    }

    /**
     * ������ģ�������룬��6��ͷ
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
            codeMsg.put(ERR_LOAD_INFO, "��ȡ���������豸��Ϣ");
            codeMsg.put(ERR_CENTRAL_LOCK_STATUS, "����δ����");
            codeMsg.put(ERR_CENTRAL_LOCKING, "����ʧ�ܣ��޷�����");
            codeMsg.put(ERR_OPT_CAR, "�����ٿ�ʧ�ܣ�������");
            codeMsg.put(ERR_ENGINE, "������δϨ���޷�����");
            codeMsg.put(ERR_TOTAL_MILEAGE, "��ȡ������ʻ��̣��޷��⳵");
            codeMsg.put(ERR_CENTRAL_LOCK_DOOR_OPEN, "����δ�أ��޷�����");
            codeMsg.put(ERR_DOOR_OPEN, "����δ�أ��޷�����");
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
            codeMsg.put(ERR_NEED_PASSWARD, "�����û�,���벻��Ϊ��");
            codeMsg.put(ERR_ORG_NOT_FOUND, "����֯����������");
            codeMsg.put(ERR_DEPT_NOT_FOUND, "�ò��Ų�����");
            codeMsg.put(ERR_DEPT_HAS_CHILD, "�ò������Ӽ�����,����ɾ��");
            codeMsg.put(ERR_DEPT_HAS_USER, "�ò������û�,����ɾ��");
            codeMsg.put(ERR_POST_HAS_USER, "��ְλ�������ĳ���û�,����ɾ��");
            codeMsg.put(ERR_AREA_HAS_ORG, "����Ӫ������������Ӫ��,����ɾ��");
            codeMsg.put(ERR_USER_NOT_FOUND, "�û�������");
            codeMsg.put(ERR_USER_PWD_ERR, "�������");
            codeMsg.put(ERR_USER_EXIST, "�����û�ʹ�ø��ֻ���");
            codeMsg.put(ERR_USER_DISABLE, "���û��ѱ�����");
            codeMsg.put(ERR_ORG_DISABLE, "���û���������֯�����ѱ�����");
            codeMsg.put(ERR_POST_HAS_CHILD, "��ְλ���Ӽ�ְλ,����ɾ��");
        }
    }

    static {
        //����������
        Base.init(codeMsg);
        //��Աģ��
        Account.init(codeMsg);
        //Ӫ���ģ��
        Activity.init(codeMsg);
        //����ģ��
        Order.init(codeMsg);
        //������Ϣģ��
        Car.init(codeMsg);
        //������Ϣģ��
        Finance.init(codeMsg);
        //��������Ϣģ��
        Iov.init(codeMsg);
        //ϵͳ����ģ��
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
