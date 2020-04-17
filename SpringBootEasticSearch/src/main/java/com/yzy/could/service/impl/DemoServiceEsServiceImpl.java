package com.yzy.could.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yzy.could.dao.DemoDao;
import com.yzy.could.entity.Demo;
import com.yzy.could.service.DemoService;
import com.yzy.could.utils.PageBean;
import com.yzy.could.utils.ParameterBo;


@Service
public class DemoServiceEsServiceImpl implements DemoService {

	@Autowired
	DemoDao atdao;

	public String getAll(String esServerPath) {
//		String json = JSONObject.toJSONString(atdao.getAll(esServerPath));
//		return json;
		return null ;
	}

	public Boolean insert(String esServerPath, Demo demo) {
		return atdao.insert(esServerPath, demo);
	}

	
	public String delete(String esServerPath) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Boolean update(Demo demo){
		// TODO Auto-generated method stub
		return atdao.update(demo);
	}

	
	public Boolean deleteDemoById(String id) {
		return atdao.deleteDemoById(id);
	}

	
	public PageBean getAllDemoByPage(ParameterBo<Demo> parameterBo) {
		return atdao.getAllDemoByPage(parameterBo);
	}

	

}
