package com.olivia.peanut.task.engine.exec;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.googlecode.aviator.AviatorEvaluator;
import com.olivia.peanut.task.engine.entity.ExecTaskReq;
import com.olivia.peanut.task.engine.entity.TaskCheckRunnerReq;
import com.olivia.peanut.task.engine.entity.TaskInfoDef;
import com.olivia.peanut.task.engine.entity.vo.*;
import com.olivia.peanut.task.engine.listener.TaskListener;
import com.olivia.peanut.task.model.TaskInstanceHistory;
import com.olivia.peanut.task.service.TaskInstanceHistoryService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.BaseEntity;
import com.olivia.sdk.utils.RunUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.data.redis.core.StringRedisTemplate;

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

  private static final String EXEC_ERROR_LOCK = "TASK:ERROR:";
  //  @NonNull
  private final Long instanceId;
  //  @NonNull
  private final Long lastTaskInstanceId;
  //  @NonNull
  private final List<TaskInfoDef> taskInfoDefList;
  //  @NonNull
  private final TaskInfoDef currentTaskInfoDef;

  private final Long taskId;


  private final AtomicInteger execLoop = new AtomicInteger(0);

  @Override
  public void run() {
    String lockKey = EXEC_ERROR_LOCK + instanceId;
    String va = SpringUtil.getBean(StringRedisTemplate.class).opsForValue().get(lockKey);
    if (StringUtils.isNotBlank(va)) {
      log.warn("instanceId {} 存在异常的任务，需要终止所有任务 ,异常任务ID : {}", instanceId, va);
      return;
    }
    long startTime = System.currentTimeMillis();
    TaskInstanceHistoryService taskInstanceHistoryService = SpringUtil.getBean(TaskInstanceHistoryService.class);
    TaskInstanceHistory currentInstanceHistory = new TaskInstanceHistory().setInstanceId(instanceId);

    Long taskInstanceId = IdWorker.getId();
    currentInstanceHistory.setId(taskInstanceId);
    currentInstanceHistory.setTaskId(taskId).setTaskDefId(currentTaskInfoDef.getId()).setTaskName(currentTaskInfoDef.getTaskName());
    currentInstanceHistory.setExecLoop(execLoop.get());
    Map<String, Object> outMap = null;
    boolean isToNextTask = true;

    try {
      log.info("获取上一个任务的历史记录 instanceId : {} currentInstanceHistory {}", instanceId, currentInstanceHistory);
      TaskInstanceHistory lastHistory = getLastTaskHistory(taskInstanceHistoryService);
      String taskOutput = $.firstNotNull(lastHistory.getTaskOutput(), "{}");
      log.info("lastTaskInstanceId {} taskOutput:{}", lastTaskInstanceId, taskOutput);
      Map<String, Object> lastOutMap = JSON.parseObject(taskOutput);

      // 映射输入数据
      mappingDataMap(lastOutMap, currentTaskInfoDef.getInputMappingList());
      currentInstanceHistory.setTaskInput(JSON.toJSONString(lastOutMap));
      log.info("lastTaskInstanceId {} getTaskInput:{}", lastTaskInstanceId, lastHistory.getTaskInput());
//      currentInstanceHistory.setTaskInput()
      // 入参校验

      boolean checkInputCondition = checkInputCondition(lastOutMap);
      log.info("lastTaskInstanceId {} task {}: {}  check run checkInputCondition:{}", lastTaskInstanceId, currentTaskInfoDef.getId(), currentTaskInfoDef.getTaskName(), checkInputCondition);

      if (!checkInputCondition) {
        return;
      }
      log.info("前置监听器 instanceId {} taskInstanceId {} {}", instanceId, taskInstanceId, currentTaskInfoDef.getPrefixListenerName());
      execListener(currentTaskInfoDef.getPrefixListenerName(), taskInstanceId, lastOutMap);

      log.info("开始执行任务 instanceId {} taskInstanceId {}", instanceId, taskInstanceId);
      // 开始执行任务
      outMap = executeTask(taskInstanceId, lastOutMap);
      log.info("开始执行任务 instanceId : {} taskInstanceId: {} 成功， 开始转换数据结构", instanceId, taskInstanceId);

      // 映射输出数据
      mappingDataMap(outMap, currentTaskInfoDef.getOutputMappingList());
      log.info("后置转换器 instanceId {} taskInstanceId {}", instanceId, taskInstanceId);

      // 后置监听器
      log.info("后置监听器 instanceId {} taskInstanceId {} {}", instanceId, taskInstanceId, currentTaskInfoDef.getSuffixListenerName());

      execListener(currentTaskInfoDef.getSuffixListenerName(), taskInstanceId, lastOutMap);
      log.info("task run end time:{}", System.currentTimeMillis() - startTime);
      log.info("task end  instanceId {} taskInstanceId {} task run end time:{}", instanceId, taskInstanceId, System.currentTimeMillis() - startTime);
      currentInstanceHistory.setTaskExecStatus(TaskExecStatus.SUCCESS);
    } catch (ExecutionException | TimeoutException | NoSuchBeanDefinitionException e) {
      log.error("任务执行出错，超时或执行异常", e);
      currentInstanceHistory.setTaskExecStatus(TaskExecStatus.FAILURE_EXCEPTION).setExceptionMsg(e.getLocalizedMessage());
      isToNextTask = false;
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      log.error("任务执行被中断", e);
      currentInstanceHistory.setTaskExecStatus(TaskExecStatus.FAILURE_EXCEPTION).setExceptionMsg(e.getLocalizedMessage());
      isToNextTask = false;
    } catch (Exception e) {
      log.error("任务执行出现未知异常", e);
      currentInstanceHistory.setTaskExecStatus(TaskExecStatus.FAILURE_EXCEPTION).setExceptionMsg(e.getLocalizedMessage());
      isToNextTask = false;
    }

    // 保存任务执行结果
    log.info("保存任务执行结果 {}", taskInstanceId);
    saveTaskHistory(taskInstanceHistoryService, currentInstanceHistory, outMap, startTime);


    // 执行下一个任务
    ExceptionStop exceptionStop = currentTaskInfoDef.getExceptionStop();
    log.info("执行下一个任务 {} isToNextTask {} exceptionStop {} ", taskInstanceId, isToNextTask, exceptionStop);
    if (!isToNextTask) {
      if (Objects.isNull(exceptionStop) || ExceptionStop.IGNORE.equals(exceptionStop)) {
        isToNextTask = true;
      } else if (ExceptionStop.ALL.equals(exceptionStop)) {
        SpringUtil.getBean(StringRedisTemplate.class).opsForValue().set(lockKey, String.valueOf(taskInstanceId), 1, TimeUnit.MINUTES);
        log.error("任务终止标识添加功 lockKey: {} instanceId :{} taskInstanceId : {}", lockKey, instanceId, taskInstanceId);
      }
    }
    if (isToNextTask) {
      try {
        executeTaskCheck(currentInstanceHistory, outMap, () -> executeNextTasks(taskInstanceHistoryService, taskInstanceId));
      } catch (Exception e) {
        log.info("任务执行后续任务 instanceId {} taskInstanceId {} error ", instanceId, taskInstanceId, e);

        taskInstanceHistoryService.update(new LambdaUpdateWrapper<TaskInstanceHistory>().set(TaskInstanceHistory::getCheckExceptionMsg, e.getMessage()).set(TaskInstanceHistory::getCheckExecStatus, TaskExecStatus.FAILURE_EXCEPTION).set(TaskInstanceHistory::getCheckLoop, 0L).eq(BaseEntity::getId, taskInstanceId));
      }
    } else {
      log.info("任务不能执行后续任务 isToNextTask is false instanceId {} taskInstanceId {}", instanceId, taskInstanceId);

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
      log.info("入参校验 ,{}", sourceTaskCondition);
      Boolean bool = (Boolean) AviatorEvaluator.execute(sourceTaskCondition, lastOutMap);
      if (!Boolean.TRUE.equals(bool)) {
        log.info("任务进入条件不通过 ");
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
  private Map<String, Object> executeTask(long taskInstanceId, Map<String, Object> lastOutMap) throws NoSuchBeanDefinitionException, ExecutionException, InterruptedException, TimeoutException {
    TaskType taskType = currentTaskInfoDef.getTaskType();
    TaskRunnerExec taskRunnerExec = SpringUtil.getBean(taskType.name().toLowerCase());
    return CompletableFuture.supplyAsync(() -> taskRunnerExec.exec(new ExecTaskReq(instanceId, lastTaskInstanceId, taskInstanceId, taskInfoDefList, currentTaskInfoDef, lastOutMap)), RunUtils.getExecutorService()).get(currentTaskInfoDef.getTimeOut(), TimeUnit.MILLISECONDS);

  }

  private void executeTaskCheck(TaskInstanceHistory taskInstanceHistory, Map<String, Object> lastOutMap, Runnable successRun) {
    CheckType taskType = currentTaskInfoDef.getCheckType();
    String runKey = "executeTaskCheck " + taskInstanceHistory.getId();
//    taskType = CheckType.HTTP;
    if (Objects.isNull(taskType)) {
      RunUtils.run(runKey, successRun);
      return;
    }
    TaskCheckRunnerExec checkRunnerExec = SpringUtil.getBean(taskType.name().toLowerCase() + "TaskCheckRunnerExec");
    RunUtils.run(runKey, () -> checkRunnerExec.exec(new TaskCheckRunnerReq(taskInstanceHistory, taskInfoDefList, currentTaskInfoDef, lastOutMap, successRun)));
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
    List<TaskInfoDefRunner> runnerList = infoDefList.stream().map(t -> new TaskInfoDefRunner(instanceId, taskInstanceHistoryService.getById(taskInstanceId).getId(), taskInfoDefList, t, taskId)).toList();
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