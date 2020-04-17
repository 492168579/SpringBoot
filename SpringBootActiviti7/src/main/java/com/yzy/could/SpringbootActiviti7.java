package com.yzy.could;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class SpringbootActiviti7 {
	public static void main(String[] args) {
		SpringApplication.run(SpringbootActiviti7.class, args);


	}

}
