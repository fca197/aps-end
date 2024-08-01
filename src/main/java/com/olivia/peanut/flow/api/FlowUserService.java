package com.olivia.peanut.flow.api;

import com.olivia.peanut.flow.api.entity.FlowUserAssignee;
import java.util.List;

public interface FlowUserService {

  /****
   *
   * @param assignee
   * @return
   */
  List<String> getUserIdList(String assignee);
  List<String> getUserIdList(FlowUserAssignee  assignee);
}
