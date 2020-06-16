package com.example.hiot_cloud.utils;

/**
 * 常量类
 */
public class Constants {
    /**
     * 活动页面数量
     */
    public static final int MAIN_FRAGMENT_COUNT = 4;
    /**
     * 主页viewpager消息fragment选择序号 message
     */
    public static final int MAIN_VIEWPAGER_INDEX_MESSAGE = 0;
    /**
     * 主页viewpager消息fragment选择序号 equipment
     */
    public static final int MAIN_VIEWPAGER_INDEX_EQUIPMENT = 1;
    /**
     * 主页viewpager消息fragment选择序号 scene
     */
    public static final int MAIN_VIEWPAGER_INDEX_SCENE = 2;
    /**
     * 主页viewpager消息fragment选择序号 mine
     */
    public static final int MAIN_VIEWPAGER_INDEX_MINE = 3;
    /**
     * APP登录Logincode
     */
    public static final String LOGIN_CODE_APP = "app";
    /**
     * APP注册用户类型
     */
    public static final String REGISTER_TYPE_NOMAL = "1";
    /**
     * 服务端返回消息状态属性
     */
    public static final int MSG_STATUS_SUCCESS = 1;
    /**
     * 网络访问失败吐司
     */
    public static final String TOAST_MSG_NETWORK_FAIL = "当前网络无法访问，请稍后再试";
    /**
     * onError失败吐司
     */
    public static final String ERROR_MESSAGE = "服务器开小差，请稍后再试";
    /**
     * form-data类
     */
    public static final String MULTIPART_FORM_DATA = "multipart/form-data";
    /**
     * 如果token失效
     */
    public static final int MSG_STATUS_TOKEN_OUT = -100;
    /**
     * 设备已绑定状态
     */
    public static final int DEVICE_STATUS_BINDED = 1;
    /**
     * 设备未绑定状态
     */
    public static final int DEVICE_STATUS_UNBINDED = 0;
    /**
     * 设备id
     */
    public static final String INTENT_EXTRA_DEVICE_ID = "DEVICE_ID";
    /**
     * 已激活
     */
    public static final String DEVICE_STATUS_ACTIVITY = "1";
    /**
     * 未激活
     */
    public static final String DEVICE_STATUS_UNACTIVITY = "0";
    /**
     * 开关类型
     */
    public static final String DATA_STREAM_TYPE_VALUE = "1";
    /**
     * 数值类型
     */
    public static final String DATA_STREAM_TYPE_SWITCH = "2";
    /**
     * GPS类型
     */
    public static final String DATA_STREAM_TYPE_GPS = "3";
    /**
     * 文本类型
     */
    public static final String DATA_STREAM_TYPE_TEXT = "4";
    /**
     * 开关状态开
     */
    public static final int SWITCH_STATUS_ON = 1;
    /**
     * 开关状态关
     */
    public static final int SWITCH_STATUS_OFF = 0;
}
