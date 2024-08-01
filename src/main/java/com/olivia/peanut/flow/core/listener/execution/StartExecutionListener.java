package com.olivia.peanut.flow.core.listener.execution;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;

/***
 *
 */
@Slf4j
public class StartExecutionListener implements ExecutionListener {

  @Override
  public void notify(DelegateExecution execution) throws Exception {
    log.info("StartExecutionListener notify");

  }
}
