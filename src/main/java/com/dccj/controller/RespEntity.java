package com.dccj.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RespEntity implements java.io.Serializable {

    private static final long serialVersionUID = 7057131750244162270L;
    private String status; //错误码
    private String message; //错误信息
    private JSONObject data; //数据项目

    public RespEntity() {
        this(CODE_SUCCESS);
    }

    public RespEntity(String status) {
        this.status = status;
        this.message = mapParams.get(status);
    }

    public RespEntity(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
        if (mapParams.containsKey(status))
            this.message = mapParams.get(status);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public JSONObject getData() {
        return data;
    }

    public void setData(Object data) {
        if (data instanceof JSONObject) {
            this.data = (JSONObject) data;
        } else if (data instanceof String) {
            this.data = JSON.parseObject((String) data);
        }// else if (data instanceof JSONArray){}
        else {
            this.data = (JSONObject) JSON.toJSON(data);
        }
    }

    public void putData(String key, Object value) {
        if (this.data == null) {
            this.data = new JSONObject();
        }
        this.data.put(key, value);
    }

    public void putListData(List list) {
        if (this.data == null) {
            this.data = new JSONObject();
        }
        if (list != null) {
            this.data.put("list", list);
        }
    }

    private static Map<String, String> mapParams = new HashMap<String, String>() {
        private static final long serialVersionUID = -5999012532729550843L;
        {
            put(CODE_PARAM_ERROR, "参数错误");
            put(CODE_SYSTEM_BUSY, "系统繁忙，请稍候再试");
            put(CODE_SUCCESS, "操作成功");
            put(CODE_USER_NOT_EXISTS, "用户不存在");
            put(CODE_USER_REGISTED, "用户已注册");

            put(CODE_SMS_SEND_FAILED, "发送验证码失败");
            put(CODE_SMS_CODE_TRY_TO_MCUH, "发送验证码尝试次数过多");
            put(CODE_SMS_CODE_LOSE_EFFICACY, "验证码失效");
            put(CODE_SMS_CODE_ERROR, "验证码错误");
            put(CODE_USER_LOGIN_ERROR, "手机号或密码错误");
            put(CODE_USER_INFO_ERROR, "请完善个人信息");
            put(CODE_USER_NO_AUTH_ERROR, "访问受限请联系管理员");
            put(CODE_CAPTCHA_GET_ERROR, "图片验证码获取失败");
            put(CODE_CAPTCHA_VERIFY_ERROR, "图片验证码错误");
            put(CODE_CAPTCHA_VERIFY_TIMEOUT, "图片验证码过期");
            put(CODE_AD_BUDGET_MONTH,"您的月预算已超限，请修改后推广");
            put(CODE_AD_BUDGET,"您的单预算已超限，请修改后推广");
            put(CODE_AD_DOWNAPK,"APK还没有下载成功，请稍后再试");
            put(CODE_SIGN_ERROR, "签名参数错误");
        }
    };

    public static String getMsgByCode(String code) {
        return mapParams.get(code);
    }

    public static final String CODE_SIGN_ERROR = "600";

    /**
     * 参数错误
     */
    public static final String CODE_PARAM_ERROR = "-1";

    /**
     * 业务错误
     */
    public static final String CODE_ERROR = "999999";

    public static final String NO_DEVICE_ERROR = "888888";

    public static final String CODE_AD_TIMER_AGAIN_ERROR = "999990";


    //预算超限月预算
    public static final String CODE_AD_BUDGET_MONTH = "999991";

    //预算超限单预算
    public static final String CODE_AD_BUDGET = "999992";

    //APK没有下载成功
    public static final String CODE_AD_DOWNAPK = "999993";

    /**
     * 操作成功
     */
    public static final String CODE_SUCCESS = "200";
    /**
     * 系统繁忙
     */
    public static final String CODE_SYSTEM_BUSY = "9999";
    /**
     * 失败
     */
    public static final String CODE_USER_ERROR = "400001";

    /**
     * 用户不存在
     */
    public static final String CODE_USER_NOT_EXISTS = CODE_USER_ERROR + "001";

    /**
     * 用户已注册
     */
    public static final String CODE_USER_REGISTED = CODE_USER_ERROR + "002";

    /**
     * 手机号或密码错误
     */
    public static final String CODE_USER_LOGIN_ERROR = CODE_USER_ERROR + "003";
    /**
     * 请完善个人信息
     */
    public static final String CODE_USER_INFO_ERROR = CODE_USER_ERROR + "004";

    /**
     * 没有登录权限
     */
    public static final String CODE_USER_NO_AUTH_ERROR = CODE_USER_ERROR + "005";

    /**
     * 用户已注册
     */
    public static final String CODE_USER_LOGIN_ON = CODE_USER_ERROR + "006";

    /**
     * 密码校验失败
     */
    public static final String CODE_USER_PASSWORD_NUM_ERROR = CODE_USER_ERROR + "009";

    /**
     * 个人账户异常
     */
    public static final String CODE_ACCOUNT_ERROR = CODE_USER_ERROR + "016";

    public static final String CODE_TOKEN_ILLEGAL = "40001"; //token错误或失效
    /**
     * 手机号已经存在
     */
    public static final String CODE_MOBILE_EXIST_ERROR = CODE_USER_ERROR + "017";
    public static final String CODE_BLACKUSER_ERROR = CODE_USER_ERROR + "018";
    public static final String CODE_THIRDPARTY_LOGIN_ERROR = CODE_USER_ERROR + "019";

    /**
     * 未满16岁以下的不能绑卡
     */
    public static final String CODE_CARD_BIND_USER_AGE_LIMIT_ERROR = CODE_USER_ERROR + "020";


    /**
     * 短信异常前缀
     */
    public static final String CODE_SMS_ERROR = "400002";
    /**
     * 短信发送失败
     */
    public static final String CODE_SMS_SEND_FAILED = CODE_SMS_ERROR + "001";

    /**
     * 短信验证码错误
     */
    public static final String CODE_SMS_CODE_ERROR = CODE_SMS_ERROR + "002";

    /**
     * 短信验证码尝试次数过多
     */
    public static final String CODE_SMS_CODE_TRY_TO_MCUH = CODE_SMS_ERROR + "003";
    /**
     * 短信验证码失效
     */
    public static final String CODE_SMS_CODE_LOSE_EFFICACY = CODE_SMS_ERROR + "004";

    /**
     * 图片验证码异常前缀
     */
    public static final String CODE_CAPTCHA_ERROR = "400003";
    /**
     * 图片验证码获取失败
     */
    public static final String CODE_CAPTCHA_GET_ERROR = CODE_CAPTCHA_ERROR + "001";

    /**
     * 图片验证码校验失败
     */
    public static final String CODE_CAPTCHA_VERIFY_ERROR = CODE_CAPTCHA_ERROR + "002";

    public static final String CODE_CAPTCHA_VERIFY_TIMEOUT = CODE_CAPTCHA_ERROR + "003";

}
