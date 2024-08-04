package com.olivia.peanut.flow.service;

import com.olivia.peanut.flow.api.entity.FlowUserAssignee;
import java.util.Map;
import org.camunda.bpm.engine.delegate.DelegateTask;

public interface FlowConfigService {

  void setInputConfig(Map<String, Object> userAssigneeMap, Boolean addVariableMap, DelegateTask delegateTask);
}
