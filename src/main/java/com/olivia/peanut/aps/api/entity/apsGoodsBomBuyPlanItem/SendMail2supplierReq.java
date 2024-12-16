package com.olivia.peanut.aps.api.entity.apsGoodsBomBuyPlanItem;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Setter
@Getter
@Accessors(chain = true)
public class SendMail2supplierReq {
  @NotNull(message = "版本不能为空")
  private Long buyPlanId;
  @NotNull(message = "开始日期不能为空")
  private LocalDate beginDate;
  @NotNull(message = "结束日期不能为空")
  private LocalDate endDate;
}
