package com.olivia.peanut.flow.api.entity;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/***
 *
 */
@Setter
@Getter
@Accessors(chain = true)
public class DeploymentCreateReq {

  @NotNull(message = "流程组id不能为空")
  private Long flowGroupId;
  @NotNull(message = "文件名不能为空")
  private String fileName;
  @NotNull(message = "文件名不能为空")
  private String path;
}
