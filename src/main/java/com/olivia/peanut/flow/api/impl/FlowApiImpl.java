package com.olivia.peanut.flow.api.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.olivia.peanut.flow.api.FlowApi;
import com.olivia.peanut.flow.api.entity.*;
import com.olivia.peanut.flow.model.FlowDefinition;
import com.olivia.peanut.flow.service.FlowDefinitionService;
import com.olivia.sdk.comment.ServiceComment;
import com.olivia.sdk.filter.LoginUser;
import com.olivia.sdk.filter.LoginUserContext;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import jakarta.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.camunda.bpm.engine.*;
import org.camunda.bpm.engine.history.HistoricActivityInstance;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.engine.runtime.ActivityInstance;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.task.TaskQuery;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.FlowNode;
import org.camunda.bpm.model.bpmn.instance.SequenceFlow;
import org.camunda.bpm.model.bpmn.instance.UserTask;
import org.camunda.bpm.model.xml.instance.ModelElementInstance;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

/***
 *
 */
@Slf4j
@RestController
public class FlowApiImpl implements FlowApi {


  @Resource
  FlowDefinitionService flowDefinitionService;
  @Resource
  TaskService taskService;
  @Resource
  RepositoryService repositoryService;
  @Resource
  HistoryService historyService;
  @Resource
  private RuntimeService runtimeService;

  @SneakyThrows
  @Override
  @Transactional
  public DeploymentCreateRes deploymentCreate(DeploymentCreateReq req) {
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    String content = IOUtils.resourceToString(req.getPath(), StandardCharsets.UTF_8);
    LoginUser loginUser = LoginUserContext.getLoginUser();
    Deployment deployed = repositoryService.createDeployment().addString(req.getFileName(), content).source("file").name(loginUser.getUserName()).deploy();
    log.info("deploymentCreate {}", JSON.toJSONString(deployed));
    FlowDefinition flowDefinition = new FlowDefinition().setFlowGroupId(req.getFlowGroupId()).setFlowName(req.getFileName());
    flowDefinitionService.save(flowDefinition);

    return new DeploymentCreateRes().setId(flowDefinition.getId());
  }

  @Override
  public StartRes start(StartReq req) {

    FlowDefinition flowDefinition = flowDefinitionService.getOne(new LambdaQueryWrapper<FlowDefinition>().eq(FlowDefinition::getFlowKey, req.getFlowKey()));
    $.requireNonNullCanIgnoreException(flowDefinition, "流程不存在");
    Map<String, Object> map = Map.of(FlowStr.TABLE_NAME, "qj");
    ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(req.getFlowKey(), IdWorker.getIdStr(), map);

    return null;
  }

  @Override
  public DynamicsPage<TaskUndoneRes> taskUndone(TaskUndoneReq req) {

    TaskQuery active = taskService.createTaskQuery().processDefinitionKey(req.getFlowKey()).taskAssignee(LoginUserContext.getLoginUser().getId().toString()).active();
    long count = active.count();
    List<Task> taskList = active.list();
    DynamicsPage<TaskUndoneRes> page = new DynamicsPage<>();
    page.setTotal(count);
    ServiceComment.header(page, "FlowRepositoryServiceImpl#queryTaskPageList");

    page.setDataList($.copyList(taskList, TaskUndoneRes.class));
    return page;
  }

  @Override
  public RejectRes reject(RejectReq req) {
    List<Task> taskList = taskService.createTaskQuery().taskId(req.getTaskId()).active().list();
    $.requireNonNullCanIgnoreException(taskList, "任务不存在");
    Task task = taskList.get(0);
    String processInstanceId = task.getProcessInstanceId();
    taskService.createComment(req.getTaskId(), processInstanceId, req.getMessage());

    //获取当前环节实例 这是个树结构
    ActivityInstance activity = runtimeService.getActivityInstance(processInstanceId);

    runtimeService.createProcessInstanceModification(processInstanceId)
        //取消现有的活动实例
        .cancelActivityInstance(activity.getId())
        //设置备注
        .setAnnotation(req.getMessage())
        //让流程实例从目标活动重新开始
        .startBeforeActivity("begin")
        .execute();
    return null;

  }

  @Override
  public CompleteRes complete(CompleteReq req) {
    List<Task> taskList = taskService.createTaskQuery().taskId(req.getTaskId()).active().list();
    $.requireNonNullCanIgnoreException(taskList, "任务不存在");
    taskService.createComment(req.getTaskId(), taskList.get(0).getProcessInstanceId(), req.getMessage());
    taskService.complete(req.getTaskId());
    return null;
  }
}
