package com.bjbt.monitoring.kafka.constant;
/**
 * kafka响应数据常量类
 */
public class kafkaConstant {
	public static final String R_ERROR = "0001";
	public static final String ERROR_INFO = ",\"errorInfo\":\"";
	public static final String STATUS = "\",\"status\":\"1\"";
	public static final String SUFFIX = "}";
	public static final String RESULTCODE = "resultCode";
	public static final String MESSAGE = "message";
	
	//消息类型：1标示拍照
	public static final String MSGTYPE = "1";
	//请求类型：1标示拍照响应
	public static final String REQFLAG = "1";
	//获取指定服务器路径地址
	public static final String PICPATHURL = "/usr/local/tomcat/webapps/powergrid/ammeter/images/";
	
	
	public static final int Result = 0;
	public static final int MsgType = 1;
	public static final int ReqFlag = 0;
	public static final long SnapDelay = 0;
	public static final long  LivingDelay= 0;
	
	public static final float Top = 75;
	public static final float Left = 100;
	public static final float Bottom = 125;
	public static final float Right = 150;
	
	public static final int FaceType = 0;
	public static final int Status = 0;
	public static final String Imei = "imei";
	public static final String IMEI ="imei";
	
}
