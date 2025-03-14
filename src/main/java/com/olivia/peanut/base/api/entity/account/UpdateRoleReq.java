package com.olivia.peanut.base.api.entity.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/***
 *
 */
@Setter
@Getter
@Accessors(chain = true)
public class UpdateRoleReq {

  @NotNull(message = "userId不能为空")
  private Long userId;
  @JsonProperty("roleGroupIds")
  private List<Long> roleGroupIds;
  @JsonProperty("roleIds")
  private List<Long> roleIds;
}
