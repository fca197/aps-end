//package com.olivia.peanut.flow.service.impl;
//
//import cn.hutool.core.collection.CollUtil;
//import com.alibaba.fastjson2.JSON;
//import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
//import com.olivia.peanut.base.model.BaseRole;
//import com.olivia.peanut.base.model.BaseUserDept;
//import com.olivia.peanut.base.model.BaseUserRole;
//import com.olivia.peanut.base.service.BaseRoleService;
//import com.olivia.peanut.base.service.BaseUserDeptService;
//import com.olivia.peanut.base.service.BaseUserRoleService;
//import com.olivia.peanut.flow.core.listener.DelegateTaskInfo;
//import com.olivia.peanut.flow.service.FlowConfigService;
//import com.olivia.sdk.filter.LoginUserContext;
//import com.olivia.sdk.utils.$;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.camunda.bpm.engine.RuntimeService;
//import org.camunda.bpm.engine.delegate.DelegateExecution;
//import org.camunda.bpm.engine.delegate.DelegateTask;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.time.Duration;
//import java.util.*;
//import java.util.stream.Collectors;
//
//import static com.olivia.peanut.flow.api.entity.FlowStr.FLOW_USER_ID;
//import static com.olivia.peanut.flow.api.entity.FlowStr.FLOW_USER_ID_LIST;
//
///***
// *
// */
//@Slf4j
//@Service
//public class FlowConfigServiceImpl implements FlowConfigService {
//
//  @Autowired(required = false)
//  BaseUserRoleService userRoleService;
//  @Autowired(required = false)
//  BaseRoleService roleService;
//  @Autowired(required = false)
//  RuntimeService runtimeService;
//
//  @Autowired(required = false)
//  BaseUserDeptService baseUserDeptService;
//
//  @Override
//  @SuppressWarnings("unchecked")
//  public void setInputConfig(Map<String, Object> variableMap, Boolean addVariableMap, DelegateTaskInfo delegateTaskInfo) {
//    DelegateTask delegateTask = delegateTaskInfo.getDelegateTask();
//    DelegateExecution delegateExecution = delegateTaskInfo.getDelegateExecution();
//    Map<String, String> userAssigneeMap = (Map<String, String>) variableMap.get("userAssignee");
//    List<String> list = getUserIdList(addVariableMap, delegateTaskInfo, userAssigneeMap);
//    if (Boolean.TRUE.equals(addVariableMap) && CollUtil.isNotEmpty(list)) {
//      runtimeService.setVariable(delegateTaskInfo.getProcessInstanceId(), FLOW_USER_ID, list.getFirst());
//      runtimeService.setVariable(delegateTaskInfo.getProcessInstanceId(), FLOW_USER_ID_LIST, list);
//    }
//    Map<String, String> copyAssigneeMap = (Map<String, String>) variableMap.get("copyAssignee");
//    List<String> copyUserIdList = getUserIdList(addVariableMap, delegateTaskInfo, copyAssigneeMap);
//    if (Objects.nonNull(delegateTask)) {
//      if (CollUtil.isNotEmpty(copyUserIdList)) {
//        delegateTask.addCandidateUsers(copyUserIdList);
//      }
//      Object timeOutObj = variableMap.get("timeOut");
//      if (Objects.nonNull(timeOutObj)) {
//        Duration duration = $.getDuration((String) timeOutObj);
//        delegateTask.setDueDate(new Date(new Date().getTime() + duration.toMillis()));
//      }
//      if (StringUtils.isBlank(delegateTask.getAssignee())) {
//        if (CollUtil.isNotEmpty(list)) {
//          delegateTask.setAssignee(list.getFirst());
//        } else {
//          delegateTask.setAssignee(LoginUserContext.getLoginUser().getIdStr());
//        }
//      }
//    }
////    if (Objects.nonNull(delegateExecution)){
////
////    }
//    log.info("variableMap: {} addVariableMap:{} 用户: {}", JSON.toJSONString(variableMap), addVariableMap, list);
//  }
//
//  private List<String> getUserIdList(Boolean addVariableMap, DelegateTaskInfo delegateTaskInfo, Map<String, String> userAssigneeMap) {
//    List<String> list = new ArrayList<>();
//    if (CollUtil.isNotEmpty(userAssigneeMap)) {
//      String role = userAssigneeMap.get("role");
//      if (StringUtils.isNotBlank(role)) {
//        role = role.replaceAll(" ", "");
//        BaseRole baseRole = roleService.getOne(new LambdaQueryWrapper<BaseRole>().eq(BaseRole::getRoleCode, role));
//        list = userRoleService.list(new LambdaQueryWrapper<BaseUserRole>().select(BaseUserRole::getUserId).eq(BaseUserRole::getRoleId, baseRole.getId())).stream()
//            .map(BaseUserRole::getUserId).distinct().map(Object::toString).toList();
//        log.info("角色: {} addVariableMap:{} 用户: {}", role, addVariableMap, list);
//        $.requireNonNullCanIgnoreException(list, "角色: " + role + " 没有用户");
//      }
//      String user = userAssigneeMap.get("user");
//      if (StringUtils.isNotBlank(user)) {
//        user = user.replaceAll(" ", "");
//        if ("login".equalsIgnoreCase(user)) {
//          list = List.of(LoginUserContext.getLoginUser().getIdStr());
////          delegateTaskInfo.getDelegateTask().setAssignee(list.getFirst());
//        }
//      }
//      String deptRole = userAssigneeMap.get("deptRole");
//      if (StringUtils.isNotBlank(deptRole)) {
//        Set<Long> userIdSet = this.baseUserDeptService.list(new LambdaQueryWrapper<BaseUserDept>().eq(BaseUserDept::getUserId, delegateTaskInfo.getCreateByUserId())).stream()
//            .map(BaseUserDept::getUserId).collect(Collectors.toSet());
//        list = userRoleService.list(new LambdaQueryWrapper<BaseUserRole>().in(BaseUserRole::getUserId, userIdSet)
//                .eq(BaseUserRole::getRoleId, roleService.getOne(new LambdaQueryWrapper<BaseRole>().eq(BaseRole::getRoleCode, deptRole)).getId())).stream().map(BaseUserRole::getUserId)
//            .distinct().map(Object::toString).toList();
//        log.info("用户所在部门中的角色: {} addVariableMap:{} 用户: {}", deptRole, addVariableMap, list);
//        $.requireNonNullCanIgnoreException(list, "用户所在部门中的角色: " + deptRole + " 没有用户");
//      }
//
//    }
//    return list;
//  }
//}
