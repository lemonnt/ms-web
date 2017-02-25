package com.lemonnt.ms.common.util;


/**
 * 系统常量
 */
public interface MsgConstant 
{
	/**
	 * url前半部分
	 */
	//public static final String BASE_URL = "https://api.miaodiyun.com/20150822";
	
	public static final String SUCCESSCODE = "00000";
	
	public static final String BASE_URL = "https://api.miaodiyun.com/20150822/industrySMS/sendSMS";
	
	public static final String CONTENT_TYPE = "application/x-www-form-urlencoded";

	/**
	 * 开发者注册后系统自动生成的账号，可在官网登录后查看
	 */
	public static final String ACCOUNT_SID = "eb2e0ddac2ae4f79a1852041454bd88a";

	/**
	 * 开发者注册后系统自动生成的TOKEN，可在官网登录后查看
	 */
	public static final String AUTH_TOKEN = "09821de1fb3e46f693e51c2940fd7532";

	/**
	 * 响应数据类型, JSON或XML
	 */
	public static final String RESP_DATA_TYPE = "json";
	
	public static final String OPERATION = "/industrySMS/sendSMS";
	/**
	 * 发送时间戳的格式，上下不能超过5分钟
	 */
	public static final String TIMETYPE = "yyyyMMddHHmmss";
    /**
     * 短信的内容，必须和提供的模板一致
     */
	
	public static final String SMSCONTENTHEADER = "【柠檬网络科技】您好，您的本次注册验证码：";
	public static final String SMSCONTENTFOOTER = ",5分钟内输入有效，欢迎您加入我们！";
	
	public static final String EMAIL_HOST = "smtpdm.aliyun.com";
	public static final String EMAIL_PORT = "25";
	public static final String EMAIL_SEND_ACCOUNT = "lemonnt@yw.lemonnt.com";
	public static final String EMAIL_SEND_ACCOUNT_PWD = "LIjing582453";
	public static final String EMAIL_CONTENT_TYPE = "text/html;charset=UTF-8";
	public static final String EMAIL_OBJECT = "六安市柠檬网络科技有限责任公司";
	public static final String EMAIL_CONTENT_HEADER = "【柠檬网络科技】 您好，您本次注册测验证码为 ：";
	public static final String EMAIL_CONTENT_FOOTER = ",5分钟内输入有效，欢迎您加入我们！";
	//public static String SMSCONTENTHEADER = "【柠檬网络科技】您好，您的本次注册验证码：123456,5分钟内输入有效，欢迎您加入我们！";
}