package com.olivia.peanut.flow.core.listener.task;

import com.olivia.peanut.flow.core.FlowBaseService;
import com.olivia.peanut.flow.service.FlowConfigService;
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
    FlowConfigService flowConfigService = getFlowConfigService();
    Map<String, Object> map = runtimeService.getVariables(delegateTask.getExecutionId());
    flowConfigService.setInputConfig(map, true, getDelegateTaskInfo(delegateTask));
//    delegateTask.setAssignee(userIdList.get(0));
//    log.info("setAssignee:{}", userIdList.get(0));

  }
}
