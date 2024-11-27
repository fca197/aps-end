package com.olivia.peanut.flow.core.delegate;

import com.olivia.peanut.flow.core.FlowBaseService;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.history.HistoricProcessInstance;
import org.camunda.bpm.engine.repository.ProcessDefinition;

/***
 *
 */
@Slf4j
public class NoticeTaskDelegate extends FlowBaseService implements JavaDelegate {

  @Override
  public void execute(DelegateExecution execution) throws Exception {
//    DelegateExecution processInstance = execution.getProcessInstance();

    String processDefinitionId = execution.getProcessDefinitionId();
    String processInstanceId = execution.getProcessInstanceId();

    ProcessDefinition processDefinition = getRepositoryService().getProcessDefinition(processDefinitionId);
//    RuntimeService runtimeService = getRuntimeService();

//    ProcessInstanceQuery query = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId);
    HistoryService historyService = getHistoryService();
    HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
        .processInstanceId(processInstanceId).singleResult();
//    ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();

    String name = processDefinition.getName();
    log.info("name:{} 开始时间{}", name, historicProcessInstance.getStartTime());


  }
}
