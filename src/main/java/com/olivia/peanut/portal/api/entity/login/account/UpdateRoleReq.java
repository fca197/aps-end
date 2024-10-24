package com.olivia.peanut.portal.api.entity.login.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

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
