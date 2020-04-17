package com.bjbt.monitoring.kafka.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "weburl")
public class WebUrlConfig {
	private String smeterRspConsumerUrl;// 服务端处理kafka.topic.smeter-rsp主题的接口地址
	private String snapReportConsumerUrl;// 服务端处理kafka.topic.snap-report主题的接口地址
	private String snapResp01ConsumerUrl;// 服务端处理SnapResp01主题的接口地址
	private String snapResp01ConsumerErrorUrl;//服务端处理SnapResp01主题异常的接口地址
	private String alarmReq01ConsumerUrl;// 服务端处理AlarmReq01主题的接口地址
	private String delayResp01ConsumerUrl;// 服务端处理	DelayResp01主题的接口地址
	private String versionResp01ConsumerUrl;// 服务端处理VersionResp01主题的接口地址
	private String ipResp01ConsumerUrl;// 服务端处理IpResp01主题的接口地址
	private String brightneesResp01ConsumerUrl;// 服务端处理BrightneesResp01主题的接口地址
	private String timeResp01ConsumerUrl;// 服务端处理TimeResp01主题的接口地址
	private String ammeterCommandConsumerUrl;// 服务端处理AmmterCommand主题的接口地址
	
	
	public String getSmeterRspConsumerUrl() {
		return smeterRspConsumerUrl;
	}
	public void setSmeterRspConsumerUrl(String smeterRspConsumerUrl) {
		this.smeterRspConsumerUrl = smeterRspConsumerUrl;
	}
	public String getSnapReportConsumerUrl() {
		return snapReportConsumerUrl;
	}
	public void setSnapReportConsumerUrl(String snapReportConsumerUrl) {
		this.snapReportConsumerUrl = snapReportConsumerUrl;
	}
	public String getSnapResp01ConsumerUrl() {
		return snapResp01ConsumerUrl;
	}
	public void setSnapResp01ConsumerUrl(String snapResp01ConsumerUrl) {
		this.snapResp01ConsumerUrl = snapResp01ConsumerUrl;
	}
	public String getAlarmReq01ConsumerUrl() {
		return alarmReq01ConsumerUrl;
	}
	public void setAlarmReq01ConsumerUrl(String alarmReq01ConsumerUrl) {
		this.alarmReq01ConsumerUrl = alarmReq01ConsumerUrl;
	}
	public String getDelayResp01ConsumerUrl() {
		return delayResp01ConsumerUrl;
	}
	public void setDelayResp01ConsumerUrl(String delayResp01ConsumerUrl) {
		this.delayResp01ConsumerUrl = delayResp01ConsumerUrl;
	}
	public String getVersionResp01ConsumerUrl() {
		return versionResp01ConsumerUrl;
	}
	public void setVersionResp01ConsumerUrl(String versionResp01ConsumerUrl) {
		this.versionResp01ConsumerUrl = versionResp01ConsumerUrl;
	}
	public String getIpResp01ConsumerUrl() {
		return ipResp01ConsumerUrl;
	}
	public void setIpResp01ConsumerUrl(String ipResp01ConsumerUrl) {
		this.ipResp01ConsumerUrl = ipResp01ConsumerUrl;
	}
	public String getBrightneesResp01ConsumerUrl() {
		return brightneesResp01ConsumerUrl;
	}
	public void setBrightneesResp01ConsumerUrl(String brightneesResp01ConsumerUrl) {
		this.brightneesResp01ConsumerUrl = brightneesResp01ConsumerUrl;
	}
	public String getTimeResp01ConsumerUrl() {
		return timeResp01ConsumerUrl;
	}
	public void setTimeResp01ConsumerUrl(String timeResp01ConsumerUrl) {
		this.timeResp01ConsumerUrl = timeResp01ConsumerUrl;
	}
	public String getSnapResp01ConsumerErrorUrl() {
		return snapResp01ConsumerErrorUrl;
	}
	public void setSnapResp01ConsumerErrorUrl(String snapResp01ConsumerErrorUrl) {
		this.snapResp01ConsumerErrorUrl = snapResp01ConsumerErrorUrl;
	}
	public String getAmmeterCommandConsumerUrl() {
		return ammeterCommandConsumerUrl;
	}
	public void setAmmeterCommandConsumerUrl(String ammeterCommandConsumerUrl) {
		this.ammeterCommandConsumerUrl = ammeterCommandConsumerUrl;
	}

	
	
}
