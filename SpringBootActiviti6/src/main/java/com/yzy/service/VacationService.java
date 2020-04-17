package com.yzy.service;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowNode;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.image.ProcessDiagramGenerator;
import org.activiti.image.impl.DefaultProcessDiagramGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.yzy.entity.VacTask;
import com.yzy.entity.Vacation;
import com.yzy.util.ActivitiUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

/**
 * @author Created by yawn on 2018-01-08 13:44
 */
@Service
public class VacationService {

	@Resource
	private RuntimeService runtimeService;
	@Resource
	private IdentityService identityService;
	@Resource
	private TaskService taskService;
	@Resource
	private HistoryService historyService;
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private ProcessEngineConfiguration processEngineConfiguration;

	private static final String PROCESS_DEFINE_KEY = "vacationProcess";

	public Object startVac(String userName, Vacation vac) {

		identityService.setAuthenticatedUserId(userName);
		// 开始流程
		ProcessInstance vacationInstance = runtimeService.startProcessInstanceByKey(PROCESS_DEFINE_KEY);
		// 查询当前任务
		Task currentTask = taskService.createTaskQuery().processInstanceId(vacationInstance.getId()).singleResult();
		// 申明任务
		taskService.claim(currentTask.getId(), userName);

		Map<String, Object> vars = new HashMap<String, Object>(4);
		vars.put("applyUser", userName);
		vars.put("days", vac.getDays());
		vars.put("reason", vac.getReason());

		taskService.complete(currentTask.getId(), vars);

		return true;
	}

	public Object myVac(String userName) {
		List<ProcessInstance> instanceList = runtimeService.createProcessInstanceQuery().startedBy(userName).list();
		List<Vacation> vacList = new ArrayList<Vacation>();
		for (ProcessInstance instance : instanceList) {
			Vacation vac = getVac(instance);
			vacList.add(vac);
		}
		return vacList;
	}

	private Vacation getVac(ProcessInstance instance) {
		Integer days = runtimeService.getVariable(instance.getId(), "days", Integer.class);
		String reason = runtimeService.getVariable(instance.getId(), "reason", String.class);
		Vacation vac = new Vacation();
		vac.setApplyUser(instance.getStartUserId());
		vac.setDays(days);
		vac.setReason(reason);
		Date startTime = instance.getStartTime(); // activiti 6 才有
		vac.setApplyTime(startTime);
		vac.setApplyStatus(instance.isEnded() ? "申请结束" : "等待审批");
		return vac;
	}

	public Object myAudit(String userName) {
		List<Task> taskList = taskService.createTaskQuery().taskCandidateUser(userName).orderByTaskCreateTime().desc()
				.list();

		List<VacTask> vacTaskList = new ArrayList<VacTask>();
		for (Task task : taskList) {
			VacTask vacTask = new VacTask();
			vacTask.setId(task.getId());
			vacTask.setName(task.getName());
			vacTask.setCreateTime(task.getCreateTime());
			String instanceId = task.getProcessInstanceId();
			ProcessInstance instance = runtimeService.createProcessInstanceQuery().processInstanceId(instanceId)
					.singleResult();
			Vacation vac = getVac(instance);
			vacTask.setVac(vac);
			vacTaskList.add(vacTask);
		}
		return vacTaskList;
	}

	public Object passAudit(String userName, VacTask vacTask) {
		String taskId = vacTask.getId();
		String result = vacTask.getVac().getResult();
		Map<String, Object> vars = new HashMap<String, Object>();
		vars.put("result", result);
		vars.put("auditor", userName);
		vars.put("auditTime", new Date());
		taskService.claim(taskId, userName);
		taskService.complete(taskId, vars);
		return true;
	}

	public Object myVacRecord(String userName) {
		List<HistoricProcessInstance> hisProInstance = historyService.createHistoricProcessInstanceQuery()
				.processDefinitionKey(PROCESS_DEFINE_KEY).startedBy(userName).finished().orderByProcessInstanceEndTime()
				.desc().list();

		List<Vacation> vacList = new ArrayList<Vacation>();
		for (HistoricProcessInstance hisInstance : hisProInstance) {
			Vacation vacation = new Vacation();
			vacation.setApplyUser(hisInstance.getStartUserId());
			vacation.setApplyTime(hisInstance.getStartTime());
			vacation.setApplyStatus("申请结束");
			List<HistoricVariableInstance> varInstanceList = historyService.createHistoricVariableInstanceQuery()
					.processInstanceId(hisInstance.getId()).list();
			ActivitiUtil.setVars(vacation, varInstanceList);
			vacList.add(vacation);
		}
		return vacList;
	}

	public Object myAuditRecord(String userName) {
		List<HistoricProcessInstance> hisProInstance = historyService.createHistoricProcessInstanceQuery()
				.processDefinitionKey(PROCESS_DEFINE_KEY).involvedUser(userName).finished()
				.orderByProcessInstanceEndTime().desc().list();

		List<String> auditTaskNameList = new ArrayList<String>();
		auditTaskNameList.add("经理审批");
		auditTaskNameList.add("总监审批");
		List<Vacation> vacList = new ArrayList<Vacation>();
		for (HistoricProcessInstance hisInstance : hisProInstance) {
			List<HistoricTaskInstance> hisTaskInstanceList = historyService.createHistoricTaskInstanceQuery()
					.processInstanceId(hisInstance.getId()).processFinished().taskAssignee(userName)
					.taskNameIn(auditTaskNameList).orderByHistoricTaskInstanceEndTime().desc().list();
			boolean isMyAudit = false;
			for (HistoricTaskInstance taskInstance : hisTaskInstanceList) {
				if (taskInstance.getAssignee().equals(userName)) {
					isMyAudit = true;
				}
			}
			if (!isMyAudit) {
				continue;
			}
			Vacation vacation = new Vacation();
			vacation.setApplyUser(hisInstance.getStartUserId());
			vacation.setApplyStatus("申请结束");
			vacation.setApplyTime(hisInstance.getStartTime());
			List<HistoricVariableInstance> varInstanceList = historyService.createHistoricVariableInstanceQuery()
					.processInstanceId(hisInstance.getId()).list();
			ActivitiUtil.setVars(vacation, varInstanceList);
			vacList.add(vacation);
		}
		return vacList;
	}

	public void getFlowImgByInstanceId(String processInstanceId, HttpServletResponse response) {
		OutputStream outputStream = null;
		try {
			if (StringUtils.isEmpty(processInstanceId)) {
				return;
			}
			outputStream = response.getOutputStream();
			// 获取历史流程实例
			HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
					.processInstanceId(processInstanceId).singleResult();
			// 获取流程中已经执行的节点，按照执行先后顺序排序
			List<HistoricActivityInstance> historicActivityInstances = historyService
					.createHistoricActivityInstanceQuery().processInstanceId(processInstanceId)
					.orderByHistoricActivityInstanceId().asc().list();
			// 高亮已经执行流程节点ID集合
			List<String> highLightedActivitiIds = new ArrayList<String>();
			for (HistoricActivityInstance historicActivityInstance : historicActivityInstances) {
				highLightedActivitiIds.add(historicActivityInstance.getActivityId());
			}
			BpmnModel bpmnModel = repositoryService.getBpmnModel(historicProcessInstance.getProcessDefinitionId());
			// 高亮流程已发生流转的线id集合
			List<String> highLightedFlowIds = getHighLightedFlows(bpmnModel, historicActivityInstances);

			// 使用默认配置获得流程图表生成器，并生成追踪图片字符流

			ProcessDiagramGenerator processDiagramGenerator = processEngineConfiguration.getProcessDiagramGenerator();

			InputStream imageStream = processDiagramGenerator.generateDiagram(bpmnModel, "png", highLightedActivitiIds,
					highLightedFlowIds, "宋体", "微软雅黑", "黑体", null, 2.0);

			// 输出图片内容
			byte[] b = new byte[1024];
			int len;
			while ((len = imageStream.read(b, 0, 1024)) != -1) {
				outputStream.write(b, 0, len);
			}
			outputStream.flush();
		} catch (Exception e) {
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 获取已经流转的线
	 * 
	 * @param bpmnModel
	 * @param historicActivityInstances
	 * @return
	 */
	private static List<String> getHighLightedFlows(BpmnModel bpmnModel,
			List<HistoricActivityInstance> historicActivityInstances) {
		// 高亮流程已发生流转的线id集合
		List<String> highLightedFlowIds = new ArrayList<String>();
		// 全部活动节点
		List<FlowNode> historicActivityNodes = new ArrayList<FlowNode>();
		// 已完成的历史活动节点
		List<HistoricActivityInstance> finishedActivityInstances = new ArrayList<HistoricActivityInstance>();

		for (HistoricActivityInstance historicActivityInstance : historicActivityInstances) {
			FlowNode flowNode = (FlowNode) bpmnModel.getMainProcess()
					.getFlowElement(historicActivityInstance.getActivityId(), true);
			historicActivityNodes.add(flowNode);
			if (historicActivityInstance.getEndTime() != null) {
				finishedActivityInstances.add(historicActivityInstance);
			}
		}

		FlowNode currentFlowNode = null;
		FlowNode targetFlowNode = null;
		// 遍历已完成的活动实例，从每个实例的outgoingFlows中找到已执行的
		for (HistoricActivityInstance currentActivityInstance : finishedActivityInstances) {
			// 获得当前活动对应的节点信息及outgoingFlows信息
			currentFlowNode = (FlowNode) bpmnModel.getMainProcess()
					.getFlowElement(currentActivityInstance.getActivityId(), true);
			List<SequenceFlow> sequenceFlows = currentFlowNode.getOutgoingFlows();

			/**
			 * 遍历outgoingFlows并找到已已流转的 满足如下条件认为已已流转：
			 * 1.当前节点是并行网关或兼容网关，则通过outgoingFlows能够在历史活动中找到的全部节点均为已流转
			 * 2.当前节点是以上两种类型之外的，通过outgoingFlows查找到的时间最早的流转节点视为有效流转
			 */
			if ("parallelGateway".equals(currentActivityInstance.getActivityType())
					|| "inclusiveGateway".equals(currentActivityInstance.getActivityType())) {
				// 遍历历史活动节点，找到匹配流程目标节点的
				for (SequenceFlow sequenceFlow : sequenceFlows) {
					targetFlowNode = (FlowNode) bpmnModel.getMainProcess().getFlowElement(sequenceFlow.getTargetRef(),
							true);
					if (historicActivityNodes.contains(targetFlowNode)) {
						highLightedFlowIds.add(targetFlowNode.getId());
					}
				}
			} else {
				List<Map<String, Object>> tempMapList = new ArrayList<Map<String, Object>>();
				for (SequenceFlow sequenceFlow : sequenceFlows) {
					for (HistoricActivityInstance historicActivityInstance : historicActivityInstances) {
						if (historicActivityInstance.getActivityId().equals(sequenceFlow.getTargetRef())) {
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("highLightedFlowId", sequenceFlow.getId());
							map.put("highLightedFlowStartTime", historicActivityInstance.getStartTime().getTime());
							tempMapList.add(map);
						}
					}
				}
				if (!CollectionUtils.isEmpty(tempMapList)) {
					// 遍历匹配的集合，取得开始时间最早的一个
					long earliestStamp = 0L;
					String highLightedFlowId = null;
					for (Map<String, Object> map : tempMapList) {
						long highLightedFlowStartTime = Long.valueOf(map.get("highLightedFlowStartTime").toString());
						if (earliestStamp == 0 || earliestStamp >= highLightedFlowStartTime) {
							highLightedFlowId = map.get("highLightedFlowId").toString();
							earliestStamp = highLightedFlowStartTime;
						}
					}

					highLightedFlowIds.add(highLightedFlowId);
				}

			}

		}
		return highLightedFlowIds;
	}
}
