//package com.olivia.peanut.flow.core.listener.task;
//
//import com.olivia.peanut.flow.core.FlowBaseService;
//import com.olivia.peanut.flow.service.FlowConfigService;
//import lombok.extern.slf4j.Slf4j;
//import org.camunda.bpm.engine.RuntimeService;
//import org.camunda.bpm.engine.delegate.DelegateTask;
//import org.camunda.bpm.engine.delegate.TaskListener;
//
//import java.util.Map;
//
///***
// *
// */
//@Slf4j
//@SuppressWarnings("unchecked")
//public class CreateTaskListener extends FlowBaseService implements TaskListener {
//
//
//  @Override
//  public void notify(DelegateTask delegateTask) {
//
//    RuntimeService runtimeService = getRuntimeService();
//    FlowConfigService flowConfigService = getFlowConfigService();
//    Map<String, Object> map = runtimeService.getVariables(delegateTask.getExecutionId());
//    flowConfigService.setInputConfig(map, true, getDelegateTaskInfo(delegateTask));
////    delegateTask.setAssignee(userIdList.getFirst());
////    log.info("setAssignee:{}", userIdList.getFirst());
//
//  }
//}
