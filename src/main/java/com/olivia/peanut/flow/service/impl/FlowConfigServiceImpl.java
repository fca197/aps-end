package com.olivia.peanut.flow.service.impl;

import static com.olivia.peanut.flow.api.entity.FlowStr.FLOW_USER_ID;
import static com.olivia.peanut.flow.api.entity.FlowStr.FLOW_USER_ID_LIST;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.olivia.peanut.base.model.BaseRole;
import com.olivia.peanut.base.model.BaseUserRole;
import com.olivia.peanut.base.service.BaseRoleService;
import com.olivia.peanut.base.service.BaseUserRoleService;
import com.olivia.peanut.flow.api.entity.FlowUserAssignee;
import com.olivia.peanut.flow.service.FlowConfigService;
import com.olivia.sdk.filter.LoginUserContext;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.RunUtils;
import jakarta.annotation.Resource;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.springframework.stereotype.Service;

/***
 *
 */
@Slf4j
@Service
public class FlowConfigServiceImpl implements FlowConfigService {

  @Resource
  BaseUserRoleService userRoleService;
  @Resource
  BaseRoleService roleService;
  @Resource
  RuntimeService runtimeService;



  @Override
  @SuppressWarnings("unchecked")
  public void setInputConfig(Map<String, Object> variableMap, Boolean addVariableMap, DelegateTask delegateTask) {
    Map<String, String> userAssigneeMap = (Map<String, String>) variableMap.get("userAssignee");
    List<String> list = null;
    if (CollUtil.isNotEmpty(userAssigneeMap)) {
      String role = userAssigneeMap.get("role");
      if (StringUtils.isNotBlank(role)) {
        role = role.replaceAll(" ", "");
        BaseRole baseRole = roleService.getOne(new LambdaQueryWrapper<BaseRole>().eq(BaseRole::getRoleCode, role));
        list = userRoleService.list(new LambdaQueryWrapper<BaseUserRole>().select(BaseUserRole::getUserId).eq(BaseUserRole::getRoleId, baseRole.getId())).stream()
            .map(BaseUserRole::getUserId).distinct().map(Object::toString).toList();
        log.info("角色: {} addVariableMap:{} 用户: {}", role, addVariableMap, list);
        $.requireNonNullCanIgnoreException(list, "角色: " + role + " 没有用户");
        if (Boolean.TRUE.equals(addVariableMap)) {
          runtimeService.setVariable(delegateTask.getExecutionId(), FLOW_USER_ID, list.get(0));
          runtimeService.setVariable(delegateTask.getExecutionId(), FLOW_USER_ID_LIST, list);
        }

      }
      String user = userAssigneeMap.get("user");
      if (StringUtils.isNotBlank(user)) {
        user = user.replaceAll(" ", "");
        if ("login".equalsIgnoreCase(user)) {
          list = List.of(LoginUserContext.getLoginUser().getIdStr());
          if (Boolean.TRUE.equals(addVariableMap)) {
            runtimeService.setVariable(delegateTask.getExecutionId(), FLOW_USER_ID, list.get(0));
            runtimeService.setVariable(delegateTask.getExecutionId(), FLOW_USER_ID_LIST, list);
          }

        }
      }
    }
    Object timeOutObj = variableMap.get("timeOut");
    if (Objects.nonNull(timeOutObj)) {
      Duration duration = $.getDuration((String) timeOutObj);
      delegateTask.setDueDate(new Date(new Date().getTime() + duration.toMillis()));
    }

    log.info("variableMap: {} addVariableMap:{} 用户: {}", JSON.toJSONString(variableMap), addVariableMap, list);
  }
}
