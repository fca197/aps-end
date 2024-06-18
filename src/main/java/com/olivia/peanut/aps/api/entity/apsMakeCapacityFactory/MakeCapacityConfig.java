package com.olivia.peanut.aps.api.entity.apsMakeCapacityFactory;

import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/***
 *
 */
@Setter
@Getter
@Accessors(chain = true)
public class MakeCapacityConfig {

  @NotNull(message = "开始时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private LocalDate beginDate;
  @NotNull(message = "结束时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private LocalDate endDate;
  @NotNull(message = "最小值不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Integer minValue;
  @NotNull(message = "最大值不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Integer maxValue;

  public Integer getMaxValue() {
    return Objects.nonNull(maxValue) && maxValue > 0 ? maxValue : 0;
  }

  public Integer getMinValue() {
    return Objects.nonNull(minValue) && minValue > 0 ? minValue : 0;
  }
}
