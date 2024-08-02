package com.olivia.peanut.flow.core.listener.task;

import com.olivia.peanut.flow.core.FlowBaseService;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.history.HistoricProcessInstance;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.runtime.ProcessInstanceQuery;

/***
 *  通知
 */
@Slf4j
public class NoticeTaskListener  extends FlowBaseService implements TaskListener {


  @Override
  public void notify(DelegateTask delegateTask) {
    delegateTask.complete();
    String processDefinitionId = delegateTask.getProcessDefinitionId();
    String processInstanceId = delegateTask.getProcessInstanceId();

    ProcessDefinition processDefinition = getRepositoryService().getProcessDefinition(processDefinitionId);
    RuntimeService runtimeService = getRuntimeService();

    ProcessInstanceQuery query = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId);
    HistoryService historyService = getHistoryService();
    HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery().processDefinitionId(processInstanceId).singleResult();
    ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();

    String name = processDefinition.getName();
    log.info("name:{} 开始时间{}", name, historicProcessInstance.getStartTime());


  }
}
