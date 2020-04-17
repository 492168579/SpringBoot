package com.yzy.could.service;

import com.yzy.could.entity.Demo;
import com.yzy.could.utils.PageBean;
import com.yzy.could.utils.ParameterBo;

public interface DemoService {
	public String getAll(String esServerPath);

	public Boolean insert(String esServerPath, Demo demo);

	public String delete(String esServerPath);

	public Boolean update(Demo demo);

	public Boolean deleteDemoById(String id);

	public PageBean getAllDemoByPage(ParameterBo<Demo> parameterBo);
	
}
