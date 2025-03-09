package com.olivia.peanut.task.engine;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.olivia.peanut.task.api.entity.taskDef.TaskStartReq;
import com.olivia.peanut.task.engine.entity.TaskInfoDef;
import com.olivia.peanut.task.engine.entity.vo.TaskExecStatus;
import com.olivia.peanut.task.engine.entity.vo.TaskType;
import com.olivia.peanut.task.engine.exec.TaskInfoDefRunner;
import com.olivia.peanut.task.model.TaskDef;
import com.olivia.peanut.task.model.TaskInstance;
import com.olivia.peanut.task.model.TaskInstanceHistory;
import com.olivia.peanut.task.service.TaskDefService;
import com.olivia.peanut.task.service.TaskInstanceHistoryService;
import com.olivia.peanut.task.service.TaskInstanceService;
import com.olivia.sdk.exception.RunException;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.BaseEntity;
import com.olivia.sdk.utils.IdUtils;
import com.olivia.sdk.utils.RunUtils;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
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

  @Resource
  TaskInstanceService taskInstanceService;

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
   * @param req 任务信息
   * @return 任务实例ID
   */
  public Long startTaskByTaskDefId(TaskStartReq req) {
    Long taskId = req.getTaskId();
    TaskInstance taskInstance = new TaskInstance();
    Long instanceId = IdUtils.getId();
    taskInstance.setId(instanceId);
    taskInstance.setInstanceContent(req.getInstanceContent());
    taskInstance.setExecDate(LocalDate.now());
    taskInstance.setStartMs(System.currentTimeMillis());
    TaskDef taskDef = taskDefService.getById(taskId);

    $.requireNonNullCanIgnoreException(taskDef, "任务不存在 " + taskId);
    $.assertTrueCanIgnoreException(StringUtils.isNoneBlank(taskDef.getTaskDefContent()), "任务不存在 " + taskId);
    List<TaskInfoDef> taskInfoDefList = JSON.parseArray(taskDef.getTaskDefContent(), TaskInfoDef.class);


    this.taskDefService.update(new LambdaUpdateWrapper<TaskDef>().setSql(TaskDef.EXEC_COUNT_ADD_SQL).eq(BaseEntity::getId, taskId));
    taskInstanceService.save(taskInstance);
    TaskInfoDef taskInfoDef = taskInfoDefList.stream().filter(t -> Objects.equals(t.getTaskType(), TaskType.BEGIN)).findFirst().orElseThrow(() -> new RunException("任务中没有开始环节"));
    String taskData = "{}";
    if (CollUtil.isNotEmpty(req.getReqMap())) taskData = JSON.toJSONString(req.getReqMap());
    TaskInstanceHistory taskInstanceHistory = new TaskInstanceHistory().setTaskOutput(taskData).setTaskInput(taskData).setTaskName(taskDef.getTaskName()).setTaskId(taskId).setTaskDefId(taskInfoDef.getId()).setInstanceId(instanceId).setTaskExecStatus(TaskExecStatus.SUCCESS).setUseTime(0L).setExecLoop(0);
    Long taskHistoryId = IdUtils.getId();
    taskInstanceHistory.setId(taskHistoryId);
    taskInstanceHistoryService.save(taskInstanceHistory);
    List<TaskInfoDef> infoDefList = taskInfoDefList.stream().filter(t -> Objects.equals(t.getSourceTaskId(), taskInfoDef.getId())).toList();
    List<TaskInfoDefRunner> runnerList = infoDefList.stream().map(t -> new TaskInfoDefRunner(instanceId, taskHistoryId, taskInfoDefList, t, taskId)).toList();
    RunUtils.run("下个任务执行 " + instanceId, runnerList);
    if (CollUtil.isEmpty(runnerList)) {
      this.taskInstanceService.update(new LambdaUpdateWrapper<TaskInstance>().set(TaskInstance::getExecLoop, 1).set(TaskInstance::getTaskExecStatus, TaskExecStatus.SUCCESS)
          .set(TaskInstance::getUseTime, System.currentTimeMillis() - taskInstance.getStartMs())
          .eq(BaseEntity::getId, taskInstance.getId()));
    }
    return instanceId;
  }


}
