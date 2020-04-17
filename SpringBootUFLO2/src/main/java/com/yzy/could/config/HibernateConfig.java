package com.yzy.could.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;



@Configuration
public class HibernateConfig {
    @Autowired
    private DataSource dataSource;
    
    
    @Bean
    public LocalSessionFactoryBean sessionFactoryBean() {
       LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
       sessionFactoryBean.setDataSource(dataSource);
       sessionFactoryBean.setPackagesToScan("com.bstek.uflo.model*","poc.mpdel");
       Properties properties = new Properties();
       properties.setProperty("hibernate.current_session_context_class", "org.springframework.orm.hibernate5.SpringSessionContext");
       properties.setProperty("hibernate.show_sql", "true");
       properties.setProperty("hibernate.hbm2ddl.auto", "update");
       sessionFactoryBean.setHibernateProperties(properties);
       return sessionFactoryBean;
    }
    
}
