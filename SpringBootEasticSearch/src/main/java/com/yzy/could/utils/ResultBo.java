package com.yzy.could.utils;

public class ResultBo {
	private String status = "200";

	private String msg = "";

	private Object data;

	public String getStatus() {
		return status;
	}

	public String getMsg() {
		return msg;
	}

	public Object getData() {
		return data;
	}

	public ResultBo() {
	}

	public ResultBo(Object data) {
		this.data = data;
	}

	public ResultBo(Status status, String msg) {
		this.status = status.status();
		this.msg = msg;
	}

	public enum Status {
		VERIFY_FAIL("302"), TIMEOUT("301"), ERROR("201"), POWER_ClOSE("303");
		String status;

		Status(String status) {
			this.status = status;
		}

		String status() {
			return this.status;
		}
	}

	public void setStatus(Status status) {
		this.status = status.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public void setFailForm(Status status, String msg) {
		this.status = status.status();
		this.msg = msg;
	}
}
