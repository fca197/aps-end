package com.olivia.peanut.task.engine.exec;


import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.olivia.peanut.task.engine.entity.TaskCheckRunnerReq;
import com.olivia.peanut.task.engine.entity.vo.TaskExecStatus;
import com.olivia.peanut.task.model.TaskInstanceHistory;
import com.olivia.peanut.task.service.TaskInstanceHistoryService;
import com.olivia.sdk.model.KVEntity;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.BaseEntity;
import com.olivia.sdk.utils.RunUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public interface TaskCheckRunnerExec {
  Logger log = LoggerFactory.getLogger(TaskCheckRunnerExec.class);

  KVEntity getTaskCheckRunnerName();

  void exec(TaskCheckRunnerReq req);

  default void failToNexLoop(TaskCheckRunnerReq req) {
    Long taskId = req.getCurrentTaskInstanceHistory().getId();
//    TaskInstanceHistory currentTaskInstanceHistory = req.getCurrentTaskInstanceHistory();
    RunUtils.run("检查失败，执行下一次检查 " + taskId, () -> {
      ThreadUtil.sleep(req.getCurrentTaskInfoDef().getRetryInterval());
      TaskInstanceHistoryService taskInstanceHistoryService = SpringUtil.getBean(TaskInstanceHistoryService.class);
      taskInstanceHistoryService.update(new LambdaUpdateWrapper<TaskInstanceHistory>().setSql("check_loop = check_loop+1").eq(TaskInstanceHistory::getId, taskId));
      TaskInstanceHistory history = taskInstanceHistoryService.getById(taskId);
      long retryCount = $.firstNotNull(req.getCurrentTaskInfoDef().getRetryCount(), 0L);
      long isUserLoop = $.firstNotNull(history.getCheckLoop(), 0L);
      log.info("taskInstanceId :{} isUserLoop : {} retryCount : {}", taskId, isUserLoop, retryCount);
      if (isUserLoop < retryCount) {
        this.exec(req);
      } else {
        taskInstanceHistoryService.update(new LambdaUpdateWrapper<TaskInstanceHistory>().set(TaskInstanceHistory::getCheckExceptionMsg, "重试已达最大次数").set(TaskInstanceHistory::getCheckExecStatus, TaskExecStatus.LOOP_MAX).eq(BaseEntity::getId, taskId));
      }
    });
  }
}
