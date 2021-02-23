package com.yzy.could.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "hdfs")
public class HdfsConfig {
	
	private String path;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
