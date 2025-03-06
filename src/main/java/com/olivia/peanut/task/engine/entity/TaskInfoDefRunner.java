package com.olivia.peanut.task.engine.entity;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.googlecode.aviator.AviatorEvaluator;
import com.olivia.peanut.task.engine.TaskRunnerExec;
import com.olivia.peanut.task.engine.entity.vo.Mapping;
import com.olivia.peanut.task.engine.entity.vo.MappingType;
import com.olivia.peanut.task.engine.entity.vo.TaskExecStatus;
import com.olivia.peanut.task.engine.entity.vo.TaskType;
import com.olivia.peanut.task.engine.listener.TaskListener;
import com.olivia.peanut.task.model.TaskInstanceHistory;
import com.olivia.peanut.task.service.TaskInstanceHistoryService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.RunUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Slf4j
@AllArgsConstructor
public class TaskInfoDefRunner implements Runnable {

  public Long instanceId;

  private Long lastTaskInstanceId;

  private List<TaskInfoDef> taskInfoDefList;

  private TaskInfoDef currentTaskInfoDef;

  @Override
  public void run() {

    Long now = System.currentTimeMillis();
    TaskInstanceHistoryService taskInstanceHistoryService = SpringUtil.getBean(TaskInstanceHistoryService.class);

    // 入参校验
    String sourceTaskCondition = currentTaskInfoDef.getSourceTaskCondition();
    TaskInstanceHistory taskInstanceHistory = new TaskInstanceHistory();
    Map<String, Object> outMap = null;

    long taskInstanceId = IdWorker.getId();
    boolean isToNextTask = true;
    try {
      TaskInstanceHistory history = taskInstanceHistoryService.getById(lastTaskInstanceId);
      String taskOutput = $.firstNotNull(history.getTaskOutput(), "{}");

      Map<String, Object> lastOutMap = JSON.parseObject(taskOutput);

      mappingDataMap(lastOutMap, currentTaskInfoDef.getInputMappingList());

      history.setTaskInput(JSON.toJSONString(lastOutMap));

      if (StringUtils.isNotBlank(sourceTaskCondition)) {
        if (log.isDebugEnabled()) log.debug("入参校验 ,{}", sourceTaskCondition);
        Boolean bool = (Boolean) AviatorEvaluator.execute(sourceTaskCondition, lastOutMap);
        if (!Boolean.TRUE.equals(bool)) {
          log.debug("任务进入条件不通过 ");
          return;
        }
      }
      taskInstanceHistory.setId(taskInstanceId);
      // 前置监听器


      execListener(currentTaskInfoDef.getPrefixListenerName(), taskInstanceId, lastOutMap);

      // 开始执行任务
      TaskType taskType = currentTaskInfoDef.getTaskType();

      TaskRunnerExec taskRunnerExec = SpringUtil.getBean(taskType.name().toLowerCase() + "TaskRunnerExec");

      outMap = CompletableFuture.<Map<String, Object>>supplyAsync(() -> //
          taskRunnerExec.exec(new ExecTaskReq(instanceId, lastTaskInstanceId, taskInstanceId, taskInfoDefList, currentTaskInfoDef, lastOutMap))//
      ).get(currentTaskInfoDef.getTimeOut(), TimeUnit.MILLISECONDS);


      mappingDataMap(outMap, currentTaskInfoDef.getOutputMappingList());
      if (log.isDebugEnabled()) {
        log.debug("后置转换器 instanceId {} taskInstanceId {}", instanceId, taskInstanceId);
      }

      execListener(currentTaskInfoDef.getSuffixListenerName(), taskInstanceId, lastOutMap);
    } catch (Exception e) {
      log.error("", e);
      taskInstanceHistory.setTaskExecStatus(TaskExecStatus.FAILURE_EXCEPTION).setExceptionMsg(e.getLocalizedMessage());
      isToNextTask = false;
    }

    taskInstanceHistory.setTaskOutput(JSON.toJSONString(outMap));
    taskInstanceHistory.setUseTime(System.currentTimeMillis() - now);
    taskInstanceHistoryService.save(taskInstanceHistory);
    if (isToNextTask) {
      List<TaskInfoDef> infoDefList = taskInfoDefList.stream().filter(t -> Objects.equals(t.getSourceTaskId(), currentTaskInfoDef.getId())).toList();
      List<TaskInfoDefRunner> runnerList = infoDefList.stream().map(t -> new TaskInfoDefRunner(instanceId, taskInstanceHistory.getId(), taskInfoDefList, t)).toList();
      RunUtils.run("下个任务执行 " + taskInstanceId, runnerList);
    }
  }

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
