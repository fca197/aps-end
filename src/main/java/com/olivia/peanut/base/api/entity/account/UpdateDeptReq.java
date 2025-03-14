package com.olivia.peanut.base.api.entity.account;

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
public class UpdateDeptReq {

  @NotNull(message = "userId不能为空")
  private Long userId;
  private List<Long> deptList;

}
