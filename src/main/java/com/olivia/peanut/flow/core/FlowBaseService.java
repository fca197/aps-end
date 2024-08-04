package com.olivia.peanut.flow.core;

import cn.hutool.extra.spring.SpringUtil;
import com.olivia.peanut.flow.core.listener.DelegateTaskInfo;
import com.olivia.peanut.flow.service.FlowConfigService;
import org.camunda.bpm.engine.*;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.repository.ProcessDefinition;

/***
 *
 */
public class FlowBaseService {


  public FlowConfigService getFlowConfigService() {
    return SpringUtil.getBean(FlowConfigService.class);
  }


  public CaseService getCaseService() {
    return SpringUtil.getBean(CaseService.class);
  }


  public RepositoryService repositoryService() {
    return SpringUtil.getBean(RepositoryService.class);
  }


  /**
   * Returns the process engine's {@link RuntimeService}.
   *
   * @return the {@link RuntimeService} object.
   */
  public RuntimeService getRuntimeService() {
    return SpringUtil.getBean(RuntimeService.class);
  }

  /**
   * Returns the process engine's {@link RepositoryService}.
   *
   * @return the {@link RepositoryService} object.
   */
  public RepositoryService getRepositoryService() {
    return SpringUtil.getBean(RepositoryService.class);
  }

  /**
   * Returns the process engine's {@link FormService}.
   *
   * @return the {@link FormService} object.
   */
  public FormService getFormService() {
    return SpringUtil.getBean(FormService.class);
  }

  /**
   * Returns the process engine's {@link TaskService}.
   *
   * @return the {@link TaskService} object.
   */
  public TaskService getTaskService() {
    return SpringUtil.getBean(TaskService.class);
  }

  /**
   * Returns the process engine's {@link HistoryService}.
   *
   * @return the {@link HistoryService} object.
   */
  public HistoryService getHistoryService() {
    return SpringUtil.getBean(HistoryService.class);
  }

  /**
   * Returns the process engine's {@link IdentityService}.
   *
   * @return the {@link IdentityService} object.
   */
  public IdentityService getIdentityService() {
    return SpringUtil.getBean(IdentityService.class);
  }

  /**
   * Returns the process engine's {@link ManagementService}.
   *
   * @return the {@link ManagementService} object.
   */
  public ManagementService getManagementService() {
    return SpringUtil.getBean(ManagementService.class);
  }

  /**
   * Returns the process engine's {@link AuthorizationService}.
   *
   * @return the {@link AuthorizationService} object.
   */
  public AuthorizationService getAuthorizationService() {
    return SpringUtil.getBean(AuthorizationService.class);
  }


  /**
   * Returns the engine's {@link FilterService}.
   *
   * @return the {@link FilterService} object.
   */
  public FilterService getFilterService() {
    return SpringUtil.getBean(FilterService.class);
  }

  /**
   * Returns the engine's {@link ExternalTaskService}.
   *
   * @return the {@link ExternalTaskService} object.
   */
  public ExternalTaskService getExternalTaskService() {
    return SpringUtil.getBean(ExternalTaskService.class);
  }

  /**
   * Returns the engine's {@link DecisionService}.
   *
   * @return the {@link DecisionService} object.
   */
  public DecisionService getDecisionService() {
    return SpringUtil.getBean(DecisionService.class);
  }

  public DelegateTaskInfo delegateTaskInfo(DelegateTask delegateTask) {
    String processDefinitionId = delegateTask.getProcessDefinitionId();
    String processInstanceId = delegateTask.getProcessInstanceId();

    ProcessDefinition processDefinition = getRepositoryService().getProcessDefinition(processDefinitionId);
//   RuntimeService runtimeService = getRuntimeService();

//   ProcessInstanceQuery query = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId);
//   HistoryService historyService = getHistoryService();
//   HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery().processDefinitionId(processInstanceId).singleResult();
//   ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();

    String name = processDefinition.getName();

    return new DelegateTaskInfo().setProcessDefinitionName(name).setTaskId(delegateTask.getId()).setTaskName(delegateTask.getName())//
        .setProcessInstanceId(processInstanceId).setProcessDefinitionId(processDefinitionId);
  }
}
