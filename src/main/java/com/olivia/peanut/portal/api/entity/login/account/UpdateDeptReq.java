package com.olivia.peanut.portal.api.entity.login.account;

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
public class UpdateDeptReq {

  @NotNull(message = "userId不能为空")
  private Long userId;
  private List<Long> deptList;

}
