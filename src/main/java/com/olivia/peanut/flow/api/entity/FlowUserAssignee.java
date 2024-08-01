package com.olivia.peanut.flow.api.entity;

import com.olivia.sdk.exception.RunException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FlowUserAssignee {
  userLoin("user-login", "当前登录账户");
  private final String name;
  private final String desc;


  public static FlowUserAssignee getByName(String name) {
    for (FlowUserAssignee flowUserAssignee : FlowUserAssignee.values()) {
      if (flowUserAssignee.getName().equalsIgnoreCase(name)) {
        return flowUserAssignee;
      }
    }
    throw new RunException("未找到对应的流程分配人类型 " + name);
  }

//  public

  public enum FlowUserAssigneeType {
    user;

    public static FlowUserAssigneeType valueOf(FlowUserAssignee assignee) {
      return FlowUserAssigneeType.valueOf(assignee.name().split("-")[0]);
    }
  }

  public enum FlowUserAssigneeValue {
    create;

    public static FlowUserAssigneeValue valueOf(FlowUserAssignee assignee) {
      return FlowUserAssigneeValue.valueOf(assignee.name().split("-")[1]);
    }
  }

}
