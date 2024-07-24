package com.olivia.peanut.aps.api.entity.apsSchedulingDayConfigVersion;

import com.olivia.sdk.ann.UpdateCheck;
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
public class OrderSortIndex {

  @NotNull(message = "排程序号不能为空", groups = {UpdateCheck.class})
  private Long id;
  @NotNull(message = "排序不能为空", groups = {UpdateCheck.class})
  private Integer sortIndex;
}
