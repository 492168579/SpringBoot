package com.yzy.could.esclient;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class ExceptionHandleBean {
	private long time;
	private AtomicInteger total = new AtomicInteger();
	private String uid;

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public void increment() {
		total.incrementAndGet();
	}

	public int getTotal() {
		return this.total.intValue();
	}
	public void initExceptionBean(){
		this.increment();
		this.setTime(new Date().getTime());
	}
}
