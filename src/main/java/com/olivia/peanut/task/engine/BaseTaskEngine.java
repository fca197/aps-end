package com.olivia.peanut.task.engine;

import com.alibaba.fastjson2.JSON;
import com.olivia.peanut.task.engine.entity.TaskInfoDef;
import com.olivia.peanut.task.engine.entity.TaskInfoDefRunner;
import com.olivia.peanut.task.engine.entity.vo.TaskExecStatus;
import com.olivia.peanut.task.engine.entity.vo.TaskType;
import com.olivia.peanut.task.model.TaskDef;
import com.olivia.peanut.task.model.TaskInstanceHistory;
import com.olivia.peanut.task.service.TaskDefService;
import com.olivia.peanut.task.service.TaskInstanceHistoryService;
import com.olivia.sdk.exception.RunException;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.IdUtils;
import com.olivia.sdk.utils.RunUtils;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Slf4j
@Component
public class BaseTaskEngine {

  private static BaseTaskEngine INSTANCE;

  @Resource
  TaskDefService taskDefService;

  @Resource
  TaskInstanceHistoryService taskInstanceHistoryService;

  @SneakyThrows
  @PostConstruct
  public void init() {
    INSTANCE = this;
  }


  public static BaseTaskEngine getInstance() {
    return BaseTaskEngine.INSTANCE;
  }

  /***
   * 开始一个任务
   * @param taskId 任务ID
   * @return 任务实例ID
   */
  public Long startTaskId(Long taskId) {
    TaskDef taskDef = taskDefService.getById(taskId);
    $.requireNonNullCanIgnoreException(taskDef, "任务不存在 " + taskId);
    $.assertTrueCanIgnoreException(StringUtils.isNoneBlank(taskDef.getTaskDefContent()), "任务不存在 " + taskId);
    List<TaskInfoDef> taskInfoDefList = JSON.parseArray(taskDef.getTaskDefContent(), TaskInfoDef.class);
    TaskInfoDef taskInfoDef = taskInfoDefList.stream().filter(t -> Objects.equals(t.getTaskType(), TaskType.BEGIN)).findFirst().orElseThrow(() -> new RunException("任务中没有开始环节"));

    Long instanceId = IdUtils.getId();
    TaskInstanceHistory taskInstanceHistory = new TaskInstanceHistory()
        .setTaskOutput("{}").setTaskInput("{}")
        .setTaskId(taskId).setTaskDefId(taskInfoDef.getId()).setInstanceId(instanceId).setTaskExecStatus(TaskExecStatus.SUCCESS).setUseTime(0L);
    Long taskHistoryId = IdUtils.getId();
    taskInstanceHistory.setId(taskHistoryId);
    taskInstanceHistoryService.save(taskInstanceHistory);
    List<TaskInfoDef> infoDefList = taskInfoDefList.stream().filter(t -> Objects.equals(t.getSourceTaskId(), taskInfoDef.getId())).toList();
    List<TaskInfoDefRunner> runnerList = infoDefList.stream().map(t -> new TaskInfoDefRunner(instanceId, taskHistoryId, taskInfoDefList, t)).toList();
    RunUtils.run("下个任务执行 " + instanceId, runnerList);
    return instanceId;
  }


}
