package com.olivia.peanut.portal.api.entity.property;

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
public class PropertyUpdateUseReq {

  @NotNull(message = "修改数据不能为空")
  private Long id;
  @NotNull(message = "修改状态不能为空")
  private Boolean inUse;
}
