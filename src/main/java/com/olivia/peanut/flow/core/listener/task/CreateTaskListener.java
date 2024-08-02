package com.olivia.peanut.flow.core.listener.task;

import com.olivia.peanut.flow.core.FlowBaseService;
import com.olivia.peanut.flow.service.FlowUserService;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;

/***
 *
 */
@Slf4j
@SuppressWarnings("unchecked")
public class CreateTaskListener extends FlowBaseService implements TaskListener {


  @Override
  public void notify(DelegateTask delegateTask) {

    RuntimeService runtimeService = getRuntimeService();
    FlowUserService flowUserService = getFlowUserService();
    Map<String, Object> map = runtimeService.getVariables(delegateTask.getExecutionId());
    Map<String, String> userAssigneeMap = (Map<String, String>) map.get("userAssignee");
    List<String> userIdList = flowUserService.getUserIdList(userAssigneeMap);
    delegateTask.setOwner(userIdList.get(0));
    log.info("setAssignee:{}", userIdList.get(0));

  }
}
