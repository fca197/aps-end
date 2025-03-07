package com.olivia.peanut.task.engine.exec;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.googlecode.aviator.AviatorEvaluator;
import com.olivia.peanut.task.engine.entity.ExecTaskReq;
import com.olivia.peanut.task.engine.entity.TaskInfoDef;
import com.olivia.peanut.task.engine.entity.vo.Mapping;
import com.olivia.peanut.task.engine.entity.vo.MappingType;
import com.olivia.peanut.task.engine.entity.vo.TaskExecStatus;
import com.olivia.peanut.task.engine.entity.vo.TaskType;
import com.olivia.peanut.task.engine.listener.TaskListener;
import com.olivia.peanut.task.model.TaskInstanceHistory;
import com.olivia.peanut.task.service.TaskInstanceHistoryService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.RunUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RequiredArgsConstructor
public class TaskInfoDefRunner implements Runnable {

  //  @NonNull
  private final Long instanceId;
  //  @NonNull
  private final Long lastTaskInstanceId;
  //  @NonNull
  private final List<TaskInfoDef> taskInfoDefList;
  //  @NonNull
  private final TaskInfoDef currentTaskInfoDef;


  private final AtomicInteger execLoop = new AtomicInteger();

  @Override
  public void run() {
    long startTime = System.currentTimeMillis();
    TaskInstanceHistoryService taskInstanceHistoryService = SpringUtil.getBean(TaskInstanceHistoryService.class);
    TaskInstanceHistory taskInstanceHistory = new TaskInstanceHistory().setInstanceId(instanceId);

    Long taskInstanceId = IdWorker.getId();
    taskInstanceHistory.setId(taskInstanceId);
    taskInstanceHistory.setTaskName(currentTaskInfoDef.getTaskName());
    taskInstanceHistory.setExecLoop(execLoop.get());
    Map<String, Object> outMap = null;
    boolean isToNextTask = true;

    try {
      // 获取上一个任务的历史记录
      TaskInstanceHistory lastHistory = getLastTaskHistory(taskInstanceHistoryService);
      String taskOutput = $.firstNotNull(lastHistory.getTaskOutput(), "{}");
      log.info("lastTaskInstanceId {} taskOutput:{}", lastTaskInstanceId, taskOutput);
      Map<String, Object> lastOutMap = JSON.parseObject(taskOutput);

      // 映射输入数据
      mappingDataMap(lastOutMap, currentTaskInfoDef.getInputMappingList());
      lastHistory.setTaskInput(JSON.toJSONString(lastOutMap));
      log.info("lastTaskInstanceId {} getTaskInput:{}", lastTaskInstanceId, lastHistory.getTaskInput());
      // 入参校验

      boolean checkInputCondition = checkInputCondition(lastOutMap);
      log.info("lastTaskInstanceId {} task {}: {} not run checkInputCondition:{}", lastTaskInstanceId, currentTaskInfoDef.getId(), currentTaskInfoDef.getTaskName(), checkInputCondition);

      if (!checkInputCondition) {
        return;
      }
      log.info("前置监听器 {}", currentTaskInfoDef.getPrefixListenerName());
      execListener(currentTaskInfoDef.getPrefixListenerName(), taskInstanceId, lastOutMap);

      log.info("开始执行任务 {}", taskInstanceId);
      // 开始执行任务
      outMap = executeTask(taskInstanceId, lastOutMap);
      log.info("开始执行任务 {} 成功， 开始转换数据结构", taskInstanceId);

      // 映射输出数据
      mappingDataMap(outMap, currentTaskInfoDef.getOutputMappingList());
      log.debug("后置转换器 instanceId {} taskInstanceId {}", instanceId, taskInstanceId);

      // 后置监听器
      log.info("后置监听器 {}", currentTaskInfoDef.getSuffixListenerName());
      execListener(currentTaskInfoDef.getSuffixListenerName(), taskInstanceId, lastOutMap);
      log.info("task run end time:{}", System.currentTimeMillis() - startTime);
    } catch (ExecutionException | TimeoutException e) {
      log.error("任务执行出错，超时或执行异常", e);
      taskInstanceHistory.setTaskExecStatus(TaskExecStatus.FAILURE_EXCEPTION).setExceptionMsg(e.getLocalizedMessage());
      isToNextTask = false;
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      log.error("任务执行被中断", e);
      taskInstanceHistory.setTaskExecStatus(TaskExecStatus.FAILURE_EXCEPTION).setExceptionMsg(e.getLocalizedMessage());
      isToNextTask = false;
    } catch (Exception e) {
      log.error("任务执行出现未知异常", e);
      taskInstanceHistory.setTaskExecStatus(TaskExecStatus.FAILURE_EXCEPTION).setExceptionMsg(e.getLocalizedMessage());
      isToNextTask = false;
    }

    // 保存任务执行结果
    log.info("保存任务执行结果 {}", taskInstanceId);
    saveTaskHistory(taskInstanceHistoryService, taskInstanceHistory, outMap, startTime);

    // 执行下一个任务
    log.info("执行下一个任务 {} isToNextTask {} ", taskInstanceId, isToNextTask);
    if (isToNextTask) {
      executeNextTasks(taskInstanceHistoryService, taskInstanceId);
    }
  }

  /**
   * 获取上一个任务的历史记录
   *
   * @param taskInstanceHistoryService 任务实例历史服务
   * @return 上一个任务的历史记录
   */
  private TaskInstanceHistory getLastTaskHistory(TaskInstanceHistoryService taskInstanceHistoryService) {
    return taskInstanceHistoryService.getById(lastTaskInstanceId);
  }

  /**
   * 检查输入条件
   *
   * @param lastOutMap 上一个任务的输出结果
   * @return 输入条件是否通过
   */
  private boolean checkInputCondition(Map<String, Object> lastOutMap) {
    String sourceTaskCondition = currentTaskInfoDef.getSourceTaskCondition();
    if (StringUtils.isNotBlank(sourceTaskCondition)) {
      log.debug("入参校验 ,{}", sourceTaskCondition);
      Boolean bool = (Boolean) AviatorEvaluator.execute(sourceTaskCondition, lastOutMap);
      if (!Boolean.TRUE.equals(bool)) {
        log.debug("任务进入条件不通过 ");
        return false;
      }
    }
    return true;
  }

  /**
   * 执行任务
   *
   * @param taskInstanceId 当前任务实例 ID
   * @param lastOutMap     上一个任务的输出结果
   * @return 任务执行结果
   * @throws ExecutionException   执行异常
   * @throws InterruptedException 中断异常
   * @throws TimeoutException     超时异常
   */
  private Map<String, Object> executeTask(long taskInstanceId, Map<String, Object> lastOutMap) throws ExecutionException, InterruptedException, TimeoutException {
    TaskType taskType = currentTaskInfoDef.getTaskType();
    TaskRunnerExec taskRunnerExec = SpringUtil.getBean(taskType.name().toLowerCase() + "TaskRunnerExec");
    return CompletableFuture.<Map<String, Object>>supplyAsync(() -> taskRunnerExec.exec(new ExecTaskReq(instanceId, lastTaskInstanceId, taskInstanceId, taskInfoDefList, currentTaskInfoDef, lastOutMap))).get(currentTaskInfoDef.getTimeOut(), TimeUnit.MILLISECONDS);
  }

  /**
   * 保存任务历史记录
   *
   * @param taskInstanceHistoryService 任务实例历史服务
   * @param taskInstanceHistory        任务实例历史记录
   * @param outMap                     任务执行结果
   * @param startTime                  任务开始时间
   */
  private void saveTaskHistory(TaskInstanceHistoryService taskInstanceHistoryService, TaskInstanceHistory taskInstanceHistory, Map<String, Object> outMap, long startTime) {
    taskInstanceHistory.setTaskOutput(JSON.toJSONString(outMap));
    taskInstanceHistory.setUseTime(System.currentTimeMillis() - startTime);
    taskInstanceHistoryService.save(taskInstanceHistory);
  }

  /**
   * 执行下一个任务
   *
   * @param taskInstanceHistoryService 任务实例历史服务
   * @param taskInstanceId             当前任务实例 ID
   */
  private void executeNextTasks(TaskInstanceHistoryService taskInstanceHistoryService, long taskInstanceId) {
    List<TaskInfoDef> infoDefList = taskInfoDefList.stream().filter(t -> Objects.equals(t.getSourceTaskId(), currentTaskInfoDef.getId())).toList();
    List<TaskInfoDefRunner> runnerList = infoDefList.stream().map(t -> new TaskInfoDefRunner(instanceId, taskInstanceHistoryService.getById(taskInstanceId).getId(), taskInfoDefList, t)).toList();
    RunUtils.run("下个任务执行 " + taskInstanceId, runnerList);
  }

  /**
   * 映射数据
   *
   * @param map         数据映射
   * @param mappingList 映射列表
   */
  public void mappingDataMap(Map<String, Object> map, List<Mapping> mappingList) {
    if (CollUtil.isNotEmpty(mappingList)) {
      mappingList.forEach(mapping -> {
        Object o = map.get(mapping.getSource());
        if (MappingType.java.equals(mapping.getMappingType())) {
          Object ret = AviatorEvaluator.execute(mapping.getJavaExpression(), map);
          map.put(mapping.getTarget(), ret);
        } else {
          map.put(mapping.getTarget(), o);
        }
      });
    }
  }

  /**
   * 执行监听器
   *
   * @param listenerName   监听器名称
   * @param taskInstanceId 任务实例 ID
   * @param lastOutMap     上一个任务的输出结果
   */
  public void execListener(String listenerName, Long taskInstanceId, Map<String, Object> lastOutMap) {
    if (StringUtils.isNotBlank(listenerName)) {
      TaskListener taskListener = SpringUtil.getBean(listenerName, TaskListener.class);
      if (Objects.isNull(taskListener)) {
        log.warn("listenerName {}  taskListener is null", listenerName);
      } else {
        taskListener.execListener(new ExecTaskReq(instanceId, lastTaskInstanceId, taskInstanceId, taskInfoDefList, currentTaskInfoDef, lastOutMap));
      }
    }
  }
}