package com.olivia.peanut.aps.api.entity.apsOrderGoodsSaleHistory;

import com.olivia.sdk.filter.LoginUserContext;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.Objects;

@Setter
@Getter
@Accessors(chain = true)
public class SelectOrder2HistoryReq {

  private Long tenantId;

  private SelectOrder2HistoryType selectType;


  public void setTenantId(Long tenantId) {
    this.tenantId = tenantId;
    if (Objects.nonNull(this.tenantId))
      LoginUserContext.setContextThreadLocal(LoginUserContext.getLoginUser().setTenantId(this.tenantId));
  }

  public LocalDateTime getBeginDate() {
    return (switch (this.getSelectType()) {
      case LAST_MONTH -> YearMonth.from(YearMonth.now()).minusMonths(1).atDay(1);
      case null -> YearMonth.from(YearMonth.now()).minusMonths(1).atDay(1);
      case CURRENT_MONTH -> YearMonth.from(YearMonth.now()).atDay(1);
    }).atTime(LocalTime.MIN);
  }

  public LocalDateTime getEndDate() {
    return (switch (this.getSelectType()) {
      case LAST_MONTH -> YearMonth.from(LocalDate.now()).minusMonths(1).atEndOfMonth();
      case null -> YearMonth.from(LocalDate.now()).minusMonths(1).atEndOfMonth();
      case CURRENT_MONTH -> YearMonth.from(LocalDate.now()).atEndOfMonth();
    }).atTime(LocalTime.MAX);
  }

}
