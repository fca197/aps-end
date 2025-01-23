//package com.olivia.peanut.flow.core.listener.execution;
//
//import com.olivia.peanut.flow.api.entity.FlowStr;
//import com.olivia.peanut.flow.core.FlowBaseService;
//import com.olivia.peanut.flow.service.FlowConfigService;
//import lombok.extern.slf4j.Slf4j;
//import org.camunda.bpm.engine.delegate.DelegateExecution;
//import org.camunda.bpm.engine.delegate.ExecutionListener;
//
//import java.util.Map;
//
///***
// *
// */
//@Slf4j
//public class StartExecutionListener extends FlowBaseService implements ExecutionListener {
//
//  @Override
//  public void notify(DelegateExecution execution) throws Exception {
//    log.info("StartExecutionListener notify");
//    FlowConfigService flowsUserService = getFlowConfigService();
//    Map<String, Object> variables = execution.getVariables();
//    variables.put(FlowStr.USER_ASSIGNEE, Map.of(FlowStr.FLOW_USER, FlowStr.FLOW_LOGIN));
//
//    flowsUserService.setInputConfig(variables, true, getDelegateTaskInfo(execution));
//
//  }
//}
