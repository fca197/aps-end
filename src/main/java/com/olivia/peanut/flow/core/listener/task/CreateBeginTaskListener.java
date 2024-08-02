package com.olivia.peanut.flow.core.listener.task;

import com.olivia.peanut.flow.api.entity.FlowUserAssignee;
import com.olivia.peanut.flow.core.FlowBaseService;
import com.olivia.peanut.flow.service.FlowUserService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;

/***
 *
 */
@Slf4j

public class CreateBeginTaskListener  extends FlowBaseService implements TaskListener {


  @Override
  public void notify(DelegateTask delegateTask) {
    FlowUserService flowsUserService = getFlowUserService();
    List<String> userIdList = flowsUserService.getUserIdList(FlowUserAssignee.userLoin);
//    delegateTask.setAssignee(userIdList.get(0));
    delegateTask.setOwner(userIdList.get(0));
    log.info("us : {}", delegateTask.getAssignee());
  }
}
