package com.olivia.peanut.base.api.entity.account;

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
public class ResetPwdReq {

  @NotNull(message = "序号不能为空")
  private Long id;
}
