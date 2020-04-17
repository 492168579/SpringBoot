package com.bjbt.monitoring.mqtt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class MonitoringKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonitoringKafkaApplication.class, args);
	}
	  



}
