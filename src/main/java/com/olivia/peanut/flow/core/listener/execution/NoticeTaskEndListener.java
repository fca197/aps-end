package com.olivia.peanut.flow.core.listener.execution;

import com.olivia.peanut.flow.core.FlowBaseService;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.runtime.ActivityInstance;
import org.camunda.bpm.engine.runtime.ProcessInstance;

/***
 *
 */
@Slf4j
public class NoticeTaskEndListener extends FlowBaseService implements ExecutionListener {

  @Override
  public void notify(DelegateExecution execution) throws Exception {
    String processDefinitionId = execution.getProcessDefinitionId();
    String processInstanceId = execution.getProcessInstanceId();
    RuntimeService runtimeService = getRuntimeService();
    RepositoryService repositoryService = getRepositoryService();
    ActivityInstance activityInstance = runtimeService.getActivityInstance(processInstanceId);
    ProcessDefinition processDefinition = repositoryService.getProcessDefinition(processDefinitionId);
    ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();

    log.info("processInstance {}", processDefinition.getName());
  }
}
