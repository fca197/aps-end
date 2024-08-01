package com.olivia.peanut.flow.core.listener.task;

import com.olivia.peanut.flow.service.FlowUserService;
import jakarta.annotation.Resource;
import java.util.List;
import java.util.Map;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;

/***
 *
 */
@SuppressWarnings("unchecked")
public class CreateTaskListener implements TaskListener {

  @Resource
  RuntimeService runtimeService;
  @Resource
  FlowUserService flowUserService;

  @Override
  public void notify(DelegateTask delegateTask) {

    Map<String, Object> map = runtimeService.getVariables(delegateTask.getExecutionId());
    Map<String, String> userAssigneeMap = (Map<String, String>) map.get("userAssignee");
    List<String> userIdList = flowUserService.getUserIdList(userAssigneeMap);
    delegateTask.addCandidateUsers(userIdList);
  }
}
