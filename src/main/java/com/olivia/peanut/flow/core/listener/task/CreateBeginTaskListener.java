package com.olivia.peanut.flow.core.listener.task;

import com.olivia.peanut.flow.service.FlowUserService;
import com.olivia.peanut.flow.api.entity.FlowUserAssignee;
import jakarta.annotation.Resource;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;

/***
 *
 */
@Slf4j
public class CreateBeginTaskListener implements TaskListener {

  @Resource
  FlowUserService flowsUserService;

  @Override
  public void notify(DelegateTask delegateTask) {
    List<String> userIdList = flowsUserService.getUserIdList(FlowUserAssignee.userLoin);
    delegateTask.setAssignee(userIdList.get(0));

    log.info("us : {}", delegateTask.getAssignee());
  }
}
