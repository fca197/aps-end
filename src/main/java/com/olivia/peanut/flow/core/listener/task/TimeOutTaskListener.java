package com.olivia.peanut.flow.core.listener.task;

import com.olivia.peanut.flow.core.FlowBaseService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.history.HistoricProcessInstance;
import org.camunda.bpm.engine.task.DelegationState;
import org.camunda.bpm.engine.task.Task;

/***
 *
 */
@Slf4j
public class TimeOutTaskListener  extends FlowBaseService implements TaskListener {

  @Override
  public void notify(DelegateTask delegateTask) {
    String processInstanceId = delegateTask.getProcessInstanceId();

  }
}
