package com.olivia.peanut.flow.core.listener.task;

import com.olivia.peanut.flow.core.FlowBaseService;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;

/***
 *
 */

public class CompleteTaskListener  extends FlowBaseService implements TaskListener {

  @Override
  public void notify(DelegateTask delegateTask) {

  }
}
