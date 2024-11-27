package com.olivia.peanut.flow.core.listener.task;

import com.olivia.peanut.flow.core.FlowBaseService;
import com.olivia.peanut.flow.service.FlowConfigService;
import com.olivia.sdk.filter.LoginUserContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;

import java.util.Map;

/***
 *
 */
@Slf4j

public class CreateBeginTaskListener extends FlowBaseService implements TaskListener {


  @Override
  public void notify(DelegateTask delegateTask) {
    FlowConfigService flowsUserService = getFlowConfigService();
    Map<String, Object> variables = delegateTask.getVariables();
    if (StringUtils.isBlank(delegateTask.getAssignee())) {
//      variables.put("userAssignee", Map.of("user", "login"));
      delegateTask.setAssignee(LoginUserContext.getLoginUser().getIdStr());
    }
    flowsUserService.setInputConfig(variables, true, getDelegateTaskInfo(delegateTask));
//    delegateTask.setAssignee(LoginUserContext.getLoginUser().getIdStr());
    log.info("us : {}", delegateTask.getAssignee());
  }
}
