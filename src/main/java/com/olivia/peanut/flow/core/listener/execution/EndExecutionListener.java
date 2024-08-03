package com.olivia.peanut.flow.core.listener.execution;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;

/***
 *
 */
public class EndExecutionListener implements ExecutionListener {

  @Override
  public void notify(DelegateExecution execution) throws Exception {
//    execution.getEventName()
  }
}
