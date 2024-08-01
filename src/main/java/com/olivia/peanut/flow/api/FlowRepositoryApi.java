package com.olivia.peanut.flow.api;

import com.olivia.peanut.flow.api.entity.*;
import com.olivia.sdk.utils.DynamicsPage;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/flow")
public interface FlowRepositoryApi {

  @PostMapping("/deployment/create")
  DeploymentCreateRes deploymentCreate(DeploymentCreateReq req);
  // start
  @PostMapping("/repository/start")
  StartRes start(@RequestBody @Valid StartReq req);
  // task - undone
  @PostMapping("/task/undone")
  DynamicsPage<TaskUndoneRes> taskUndone(@RequestBody @Valid TaskUndoneReq req);


}
