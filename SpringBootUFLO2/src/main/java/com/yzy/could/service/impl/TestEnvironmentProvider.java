package com.yzy.could.service.impl;



import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;

import com.bstek.uflo.env.EnvironmentProvider;
@Service
public class TestEnvironmentProvider implements EnvironmentProvider {
	@Autowired
    private SessionFactory sessionFactory;;

	@Autowired
	private PlatformTransactionManager transactionManager;

	@Override
	public SessionFactory getSessionFactory() {
		 return sessionFactory;
	}

	@Override
	public PlatformTransactionManager getPlatformTransactionManager() {
		return transactionManager;
	}

	@Override
	public String getLoginUser() {
		return "anonymous";
	}

	@Override
	public String getCategoryId() {
		return null;
	}

}
