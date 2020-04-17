package com.yzy.could;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

import com.bstek.uflo.console.UfloServlet;

@SpringBootApplication
@ImportResource("classpath:uflo-console-context.xml")
public class SpringBootUFLO2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootUFLO2Application.class, args);
	}

	@Bean 
	public ServletRegistrationBean<UfloServlet> getServletRegistrationBean() { // 一定要返回ServletRegistrationBean
		ServletRegistrationBean<UfloServlet> bean = new ServletRegistrationBean<UfloServlet>(new UfloServlet()); // 放入自己的Servlet对象实例
		bean.addUrlMappings("/uflo/*"); // 访问路径值
		return bean;
	}

}
