package com.bjbt.monitoring.kafka.constant;

public enum ResponseCode {
	SUCCESS("请求成功", 1), FAIL("服务器异常", 0);
	// 成员变量
	private String name;
	private int code;

	// 构造方法
	private ResponseCode(String name, int code) {
		this.name = name;
		this.code = code;
	}

	// 普通方法
	public static String getName(int code) {
		for (ResponseCode c : ResponseCode.values()) {
			if (c.getCode() == code) {
				return c.name;
			}
		}
		return null;
	}

	// get set 方法
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	
}
