package com.olivia.peanut.flow.api;

import com.olivia.peanut.flow.api.entity.*;
import com.olivia.sdk.utils.DynamicsPage;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/flow")
public interface FlowApi {

  @PostMapping("/deployment/create")
  DeploymentCreateRes deploymentCreate(DeploymentCreateReq req);

  // start
  @PostMapping("/repository/start")
  StartRes start(@RequestBody @Valid StartReq req);

  // task - undone
  // processInstanceId
  @PostMapping("/task/undone/processInstanceId")
  TaskUndoneByProcessInstanceIdRes taskUndoneByProcessInstanceId(@RequestBody @Valid TaskUndoneByProcessInstanceIdReq req);

  @PostMapping("/task/undone")
  DynamicsPage<TaskUndoneRes> taskUndone(@RequestBody @Valid TaskUndoneReq req);
  @PostMapping("/task/undone/home")
  DynamicsPage<TaskUndoneRes> taskUndoneHome(@RequestBody @Valid TaskUndoneReq req);


  // reject
  @PostMapping("/task/reject")
  RejectRes reject(@RequestBody @Valid RejectReq req);

  @PostMapping("/task/complete")
  CompleteRes complete(@RequestBody @Valid CompleteReq req);
}
