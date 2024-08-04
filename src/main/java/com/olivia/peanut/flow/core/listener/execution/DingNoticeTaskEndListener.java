package com.olivia.peanut.flow.core.listener.execution;

import com.alibaba.fastjson2.JSON;
import com.olivia.peanut.flow.core.FlowBaseService;
import com.olivia.sdk.config.ServiceNotice;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;

/***
 *
 */
@Slf4j
public class DingNoticeTaskEndListener extends FlowBaseService implements ExecutionListener {

  @Override
  public void notify(DelegateExecution execution) throws Exception {
    String processInstanceId = execution.getProcessInstanceId();
    RuntimeService runtimeService = getRuntimeService();
    Map<String, Object> variables = runtimeService.getVariables(processInstanceId);
    ServiceNotice.sendMsg("流程结束", JSON.toJSONString(variables));
    log.info("variables {}", JSON.toJSONString(variables));
  }
}
