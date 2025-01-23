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
public class ApsOrderTimeLineReq {

  @NotNull(message = "开始时间不能为空")
  private LocalDate beginDate;
  @NotNull(message = "结束时间不能为空")
  private LocalDate endDate;

  private Boolean isActualMakeTime;
  private String orderNo;
  private Integer pageNum;
  private Integer pageSize;
}
