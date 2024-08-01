package com.olivia.peanut.flow.service;

import com.olivia.peanut.flow.api.entity.FlowUserAssignee;
import java.util.List;
import java.util.Map;

public interface FlowUserService {

  /****
   *
   * @param assignee
   * @return
   */
  List<String> getUserIdList(String assignee);
  List<String> getUserIdList(FlowUserAssignee  assignee);

  List<String> getUserIdList(Map<String, String> userAssigneeMap);
}
