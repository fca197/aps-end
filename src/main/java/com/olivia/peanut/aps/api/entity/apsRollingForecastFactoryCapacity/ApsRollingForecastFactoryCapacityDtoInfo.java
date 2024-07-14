package com.olivia.peanut.aps.api.entity.apsRollingForecastFactoryCapacity;

import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/***
 *
 */
@Setter
@Getter
@Accessors(chain = true)
public class ApsRollingForecastFactoryCapacityDtoInfo {

  @NotNull(message = "开始时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private LocalDate beginTime;
  @NotNull(message = "结束时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private LocalDate endTime;
  @NotNull(message = "产能不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Integer capacity;
}
