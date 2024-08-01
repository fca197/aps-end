package com.olivia.peanut.flow.api.impl;

import com.olivia.peanut.flow.api.FlowUserService;
import com.olivia.peanut.flow.api.entity.FlowUserAssignee;
import com.olivia.sdk.filter.LoginUserContext;
import com.olivia.sdk.utils.RunUtils;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/***
 *
 */
@Slf4j
@Service
public class FlowUserServiceImpl implements FlowUserService {

  @Override
  public List<String> getUserIdList(String assignee) {
    return getUserIdList(FlowUserAssignee.valueOf(assignee));
  }

  @Override
  public List<String> getUserIdList(FlowUserAssignee assignee) {
    if (FlowUserAssignee.userLoin.equals(assignee)) {
      return List.of(LoginUserContext.getLoginUser().getId().toString());
    }
    RunUtils.noImpl();
    return null;
  }
}
