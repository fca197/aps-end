package com.olivia.peanut.flow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.olivia.peanut.base.model.BaseRole;
import com.olivia.peanut.base.model.BaseUserRole;
import com.olivia.peanut.base.service.BaseRoleService;
import com.olivia.peanut.base.service.BaseUserRoleService;
import com.olivia.peanut.flow.service.FlowUserService;
import com.olivia.peanut.flow.api.entity.FlowUserAssignee;
import com.olivia.sdk.filter.LoginUserContext;
import com.olivia.sdk.utils.RunUtils;
import jakarta.annotation.Resource;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/***
 *
 */
@Slf4j
@Service
public class FlowUserServiceImpl implements FlowUserService {

  @Resource
  BaseUserRoleService userRoleService;
  @Resource
  BaseRoleService roleService;

  @Override
  public List<String> getUserIdList(String assignee) {
    return getUserIdList(FlowUserAssignee.valueOf(assignee));
  }

  ;

  @Override
  public List<String> getUserIdList(FlowUserAssignee assignee) {
    if (FlowUserAssignee.userLoin.equals(assignee)) {
      return List.of(LoginUserContext.getLoginUser().getId().toString());
    }
    RunUtils.noImpl();
    return null;
  }

  ;

  @Override
  public List<String> getUserIdList(Map<String, String> userAssigneeMap) {
    String role = userAssigneeMap.get("role");
    if (StringUtils.isNotBlank(role)) {
      BaseRole baseRole = roleService.getOne(new LambdaQueryWrapper<BaseRole>().eq(BaseRole::getRoleCode, role));
      return userRoleService.list(new LambdaQueryWrapper<BaseUserRole>().select(BaseUserRole::getUserId).eq(BaseUserRole::getRoleId, baseRole.getId()))
          .stream().map(BaseUserRole::getUserId).distinct().map(Object::toString).toList();
    }
    RunUtils.noImpl("用户分派[" + String.join(",", userAssigneeMap.keySet()) + "]");
    return null;
  }
}
