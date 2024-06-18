package com.olivia.peanut.portal.api.entity.checkReportList;

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
public class CheckReportListRoomDataReq {

  @NotNull(message = "盘点不能为空")
  private Long checkId;
  @NotNull(message = "房间不能为空")
  private Long roomId;
}
