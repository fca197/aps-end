package com.olivia.peanut.aps.api.entity.apsOrder;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;

/***
 *
 */
@Setter
@Getter
@Accessors(chain = true)
public class ApsOrderUpdateSchedulingDateReq {

  @NotNull(message = "id不能为空")
  private Long id;
  //  @NotNull(message = "schedulingDate不能为空")
  private LocalDate schedulingDate;
}
