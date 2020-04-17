package com.yzy.could.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yzy.could.entity.Demo;
import com.yzy.could.service.DemoService;
import com.yzy.could.utils.PageBean;
import com.yzy.could.utils.ParameterBo;
import com.yzy.could.utils.ResultBo;

@Controller
public class DemoController {
	private final static Logger log = LoggerFactory.getLogger(DemoController.class);
	private String esServerPath;
	@Autowired
	private DemoService appService;

	@RequestMapping("/insertDemo")
	@ResponseBody
	public ResultBo insert(HttpServletRequest request, HttpServletResponse response, @RequestBody Demo demo) {
		ResultBo resultBo = new ResultBo();
		Boolean insert = appService.insert(esServerPath, demo);
		if (insert) {
			resultBo.setMsg("添加成功！");
		} else {
			resultBo.setMsg("添加失败！");
		}
		return resultBo;
	}

	@RequestMapping("/deleteDemo")
	public void delete(HttpServletRequest request, HttpServletResponse response) {

		String json = appService.delete(esServerPath);
		renderData(response, json);
	}

	@RequestMapping("/updateDemo")
	@ResponseBody
	public ResultBo update(HttpServletRequest request, HttpServletResponse response, @RequestBody Demo demo) {
		ResultBo resultBo = new ResultBo();
		Boolean update = appService.update(demo);
		if (update) {
			resultBo.setMsg("更新成功！");
		} else {
			resultBo.setMsg("更新失败！");
		}
		return resultBo;
	}

	@RequestMapping("/getAllDemo")
	public void getAllDemo(HttpServletRequest request, HttpServletResponse response, @RequestBody Demo demo) {
		renderData(response, appService.getAll(esServerPath));
	}

	@RequestMapping("/getAllDemoByPage")
	@ResponseBody
	public ResultBo getAllDemoByPage(HttpServletRequest request, HttpServletResponse response,
			@RequestBody ParameterBo<Demo> parameterBo) {
		ResultBo resultBo = new ResultBo();
		PageBean data = appService.getAllDemoByPage(parameterBo);
		resultBo.setData(data);
		return resultBo;
	}

	@RequestMapping("/deleteDemoById")
	@ResponseBody
	public ResultBo deleteDemoById(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		ResultBo resultBo = new ResultBo();
		Boolean insert = appService.deleteDemoById(id);
		if (insert) {
			resultBo.setMsg("删除成功！");
		} else {
			resultBo.setMsg("删除失败！");
		}
		return resultBo;
	}

	private void renderData(HttpServletResponse response, String data) {
		PrintWriter printWriter = null;
		try {
			response.setContentType("text/html;charset=utf-8");
			printWriter = response.getWriter();
			printWriter.print(data);
		} catch (IOException ex) {
			log.error("", ex);
		} finally {
			if (null != printWriter) {
				printWriter.flush();
				printWriter.close();
			}
		}
	}

}
