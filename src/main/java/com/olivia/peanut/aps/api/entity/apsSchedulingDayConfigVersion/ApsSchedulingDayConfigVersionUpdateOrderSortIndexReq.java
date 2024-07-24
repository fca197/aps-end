package com.olivia.peanut.aps.api.entity.apsSchedulingDayConfigVersion;

import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class ApsSchedulingDayConfigVersionUpdateOrderSortIndexReq {

  @NotNull(message = "id不能为空", groups = {UpdateCheck.class})
  private Long id;
  @NotNull(message = "排序不能为空", groups = {UpdateCheck.class})
  @Size(min = 1, message = "排序不能为空", groups = {UpdateCheck.class})
  private List<OrderSortIndex> orderList;
}
