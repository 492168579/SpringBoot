package com.yzy.could.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "ftp")
public class FTPConfig {

	/**
	 * ftp站点
	 */
	private String host;
	/**
	 * ftp端口号
	 */
	private int port;
	/**
	 * ftp访问用户名
	 */
	private String username;
	/**
	 * ftp访问密码
	 */
	private String password;
	/**
	 * ftp访问文件路径
	 */
	private String filepath;
	/**
	 * ftp提供的http方式访问地址
	 */
	private String webHost;
	/**
	 * ftp提供的http方式访问的端口号
	 */
	private String webPort;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String getWebHost() {
		return webHost;
	}

	public void setWebHost(String webHost) {
		this.webHost = webHost;
	}

	public String getWebPort() {
		return webPort;
	}

	public void setWebPort(String webPort) {
		this.webPort = webPort;
	}

}
