package com.olivia.peanut.portal.api.entity.checkReportList;

import java.time.LocalDateTime;
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
public class CheckReportListRoomDataRes {

  private List<Info> dataList;

  @Setter
  @Getter
  @Accessors(chain = true)
  public static class Info {

    private Long checkId;
    private Long roomId;
    private Long propertyId;
    private String propertyName;
    private LocalDateTime checkDate;
    private Boolean isCheck;
  }
}
