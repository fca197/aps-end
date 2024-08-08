package com.olivia.peanut.flow.api.impl;

import static com.olivia.peanut.flow.api.entity.FlowStr.*;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.olivia.peanut.flow.api.FlowApi;
import com.olivia.peanut.flow.api.entity.*;
import com.olivia.peanut.flow.model.FlowDefinition;
import com.olivia.peanut.flow.model.FlowFormItem;
import com.olivia.peanut.flow.model.FlowFormUserValue;
import com.olivia.peanut.flow.service.FlowDefinitionService;
import com.olivia.peanut.flow.service.FlowFormItemService;
import com.olivia.peanut.flow.service.FlowFormUserValueService;
import com.olivia.sdk.comment.ServiceComment;
import com.olivia.sdk.filter.LoginUser;
import com.olivia.sdk.filter.LoginUserContext;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.RunUtils;
import jakarta.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.camunda.bpm.engine.*;
import org.camunda.bpm.engine.history.HistoricProcessInstance;
import org.camunda.bpm.engine.history.HistoricTaskInstance;
import org.camunda.bpm.engine.history.HistoricTaskInstanceQuery;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.engine.runtime.ActivityInstance;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Comment;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.task.TaskQuery;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

/***
 *
 */
@Slf4j
@RestController
public class FlowApiImpl implements FlowApi {


  @Autowired(required = false)
  FlowDefinitionService flowDefinitionService;
  @Autowired(required = false)
  TaskService taskService;
  @Autowired(required = false)
  RepositoryService repositoryService;
  @Autowired(required = false)
  HistoryService historyService;
  @Autowired(required = false)
  FlowFormUserValueService flowFormUserValueService;
  @Resource
  FlowFormItemService flowFormItemService;
  @Autowired(required = false)
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
  @Transactional
  public StartRes start(StartReq req) {
    FlowDefinition flowDefinition = flowDefinitionService.getOne(new LambdaQueryWrapper<FlowDefinition>().eq(FlowDefinition::getFlowKey, req.getFlowKey()));
    $.requireNonNullCanIgnoreException(flowDefinition, "流程不存在");
    Map<String, Object> map = new HashMap<>(Map.of(FLOW_FORM_ID, flowDefinition.getFlowFormId() + "", IS_FIRST_TASK, "1"));
    map.put(FLOW_CREATE_USER_ID, LoginUserContext.getLoginUser().getIdStr());
    map.put(FLOW_NAME, flowDefinition.getFlowName());
    String businessKey = IdWorker.getIdStr();
    ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(req.getFlowKey(), businessKey, map);
    String processInstanceId = processInstance.getProcessInstanceId();
    List<FlowFormUserValue> flowFormUserValueList = flowFormItemService.list(new LambdaQueryWrapper<FlowFormItem>().eq(FlowFormItem::getFormId, flowDefinition.getFlowFormId()))
        .stream().map(t -> $.copy(t, FlowFormUserValue.class).setBusinessKey(businessKey).setProcessInstanceId(processInstanceId)).toList();
    flowFormUserValueList.forEach(t -> t.setId(null));
    this.flowFormUserValueService.saveBatch(flowFormUserValueList);
    Task task = this.taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
    return new StartRes().setProcessInstanceId(processInstanceId).setTaskId(task.getId()).setFlowKey(req.getFlowKey()).setFlowFormId(flowDefinition.getFlowFormId())
        .setBusinessKey(businessKey);
  }

  @Override
  public TaskUndoneByProcessInstanceIdRes taskUndoneByProcessInstanceId(TaskUndoneByProcessInstanceIdReq req) {
    String userId = LoginUserContext.getLoginUser().getIdStr();
    Task task = taskService.createTaskQuery().processInstanceId(req.getProcessInstanceId()).taskAssignee(userId).orderByTaskCreateTime().desc().active().singleResult();
    return new TaskUndoneByProcessInstanceIdRes().setTaskId(task.getId());
  }

  @Override
  public DynamicsPage<TaskUndoneRes> taskUndone(TaskUndoneReq req) {

    String userId = LoginUserContext.getLoginUser().getIdStr();
    TaskQuery active = taskService.createTaskQuery().processDefinitionKey(req.getFlowKey()).taskAssignee(userId).orderByTaskCreateTime().desc().active();
    return getTaskUndoneResDynamicsPage(active, req);
  }

  @Override
  public DynamicsPage<TaskDoneRes> taskDone(TaskDoneReq req) {
    HistoricTaskInstanceQuery historicTaskInstanceQuery = historyService.createHistoricTaskInstanceQuery().processDefinitionKey(req.getFlowKey())
        .taskAssignee(LoginUserContext.getLoginUser().getIdStr()).orderByHistoricActivityInstanceStartTime().desc();
    List<HistoricTaskInstance> taskInstanceList = historicTaskInstanceQuery.listPage((req.getPageNum() - 1) * req.getPageNum(), req.getPageSize());

    List<TaskDoneRes> taskDoneRes = new ArrayList<>();
    if (CollUtil.isNotEmpty(taskInstanceList)) {
//      Set<String>  processInstanceIdArr = taskInstanceList.stream().map(HistoricTaskInstance::getProcessInstanceId).collect(Collectors.toSet())();

      List<Runnable> runnableList = new ArrayList<>();
      taskInstanceList.forEach(t -> {

        TaskDoneRes doneRes = $.copy(t, TaskDoneRes.class);
        doneRes.setProcessInstanceId(t.getProcessInstanceId());
        taskDoneRes.add(doneRes);
        runnableList.add(() -> {
          String processInstanceId = t.getProcessInstanceId();
          HistoricProcessInstance processInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
          String businessKey = processInstance.getBusinessKey();
          String formId = (String) runtimeService.getVariable(processInstanceId, FLOW_FORM_ID);
          doneRes.setFlowFormId(formId);
          doneRes.setBusinessKey(businessKey);
        });
      });
      RunUtils.run("Task done get formId", runnableList);
//     taskDoneRes.add($.copy(taskIdList, (Function<String, TaskDoneRes>) taskId -> {))
    }
    DynamicsPage<TaskDoneRes> page = new DynamicsPage<>();
    page.setTotal(historicTaskInstanceQuery.count());
    page.setDataList(taskDoneRes);

    return page;
  }

  @Override
  public DynamicsPage<TaskUndoneRes> taskUndoneHome(TaskUndoneReq req) {
    String userId = LoginUserContext.getLoginUser().getIdStr();
    TaskQuery active = taskService.createTaskQuery().taskAssignee(userId).orderByTaskCreateTime().desc().active();

    return getTaskUndoneResDynamicsPage(active, req);

  }

  @NotNull
  private DynamicsPage<TaskUndoneRes> getTaskUndoneResDynamicsPage(TaskQuery active, TaskUndoneReq req) {
    DynamicsPage<TaskUndoneRes> page = new DynamicsPage<>();
    long count = active.count();
    int pageSize = req.getPageSize();
    int bg = (req.getPageNum() - 1) * pageSize;
    List<Task> taskList = active.listPage(bg, pageSize);
    if (CollUtil.isNotEmpty(taskList)) {
      List<TaskUndoneRes> undoneResList = Collections.synchronizedList(new ArrayList<>());
      List<Runnable> runnableList = new ArrayList<>();
      taskList.forEach(task -> {
        runnableList.add(() -> {
          TaskUndoneRes taskUndoneRes = $.copy(task, TaskUndoneRes.class);
          taskUndoneRes.setTaskId(task.getId());
          ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).active().singleResult();
          taskUndoneRes.setBusinessKey(processInstance.getBusinessKey());
          Map<String, Object> variableMap = runtimeService.getVariables(task.getExecutionId());
          taskUndoneRes.setFlowFormId(variableMap.get(FLOW_FORM_ID));
          taskUndoneRes.setIsFirstTask(IS_FIRST_TASK_YES.equals(variableMap.get(IS_FIRST_TASK)));
          undoneResList.add(taskUndoneRes);
        });
      });
      RunUtils.run("undoneLit", runnableList);
      undoneResList.sort(Comparator.comparing(TaskUndoneRes::getCreateTime).reversed());
      page.setDataList(undoneResList);
    }
    page.setTotal(count);

    ServiceComment.header(page, "FlowRepositoryServiceImpl#queryTaskPageList");

    return page;
  }

  @Override
  public RejectRes reject(RejectReq req) {
    List<Task> taskList = taskService.createTaskQuery().taskId(req.getTaskId()).active().list();
    $.requireNonNullCanIgnoreException(taskList, "任务不存在,或已结束");
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
        .startBeforeActivity("begin").execute();
    return null;

  }

  @Override
  @Transactional
  public CompleteRes complete(CompleteReq req) {
    List<Task> taskList = taskService.createTaskQuery().taskId(req.getTaskId()).active().list();
    $.requireNonNullCanIgnoreException(taskList, "任务不存在,或已结束");
    String processInstanceId = taskList.get(0).getProcessInstanceId();
    Map<String, Object> flowValueMap = this.flowFormUserValueService.list(
            new LambdaQueryWrapper<FlowFormUserValue>().eq(FlowFormUserValue::getProcessInstanceId, processInstanceId).eq(FlowFormUserValue::getIsAddFlowValue, true)).stream()
        .collect(Collectors.toMap(FlowFormUserValue::getFormItemFiled, FlowFormUserValue::getUserValue, (a, b) -> b));
    flowValueMap.put(IS_FIRST_TASK, IS_FIRST_TASK_NO);
    runtimeService.setVariables(processInstanceId, flowValueMap);
    taskService.createComment(req.getTaskId(), processInstanceId, req.getMessage());
    taskService.complete(req.getTaskId());
    return null;
  }

  @Override
  public DynamicsPage<SelectTaskCopyRes> selectTaskCopy(SelectTaskCopyReq req) {
    String userId = LoginUserContext.getLoginUser().getIdStr();
    TaskQuery active = taskService.createTaskQuery().taskCandidateUser(userId).processDefinitionKey(req.getFlowKey()).orderByTaskCreateTime().desc().active();
    DynamicsPage<TaskUndoneRes> undoneResDynamicsPage = getTaskUndoneResDynamicsPage(active, $.copy(req, TaskUndoneReq.class));
    DynamicsPage<SelectTaskCopyRes> page = new DynamicsPage<>();
    page.setTotal(undoneResDynamicsPage.getTotal());
    page.setDataList($.copyList(undoneResDynamicsPage.getDataList(), SelectTaskCopyRes.class));
    return page;

  }


  @Override
  public List<TaskHistoryListRes> taskHistoryList(TaskHistoryListReq req) {
    List<HistoricTaskInstance> taskInstanceList = historyService.createHistoricTaskInstanceQuery().processInstanceId(req.getProcessInstanceId()).list();
    taskInstanceList.sort(Comparator.comparing(HistoricTaskInstance::getStartTime));



    List<TaskHistoryListRes> resList = $.copyList(taskInstanceList, TaskHistoryListRes.class);
    List<Runnable> runnableList = new ArrayList<>();
    resList.forEach(taskInstance -> {
      runnableList.add(() -> {
        List<Comment> taskComments = taskService.getTaskComments(taskInstance.getId());
        if (Objects.nonNull(taskComments)) {
          taskInstance.setMessage(taskComments.stream().map(Comment::getFullMessage).collect(Collectors.joining(",")));
        }
      });
    });
    RunUtils.run("taskHistoryList", runnableList);
    return resList;
  }
}
