package com.olivia.peanut.base.api.entity.checkReport;

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
public class CheckReportDownLoadReq {

  @NotNull
  private Long id;
}
