package com.olivia.peanut.aps.api.entity.apsOrder;

import java.time.LocalDate;
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
public class ApsOrderTimeLineRes extends ApsOrderDto {

  private List<StatusInfo> statusInfoList;

  @Setter
  @Getter
  public static class StatusInfo {

    private Long statusId;
    private String statusName;
    private LocalDate beginDate;
    private LocalDate endDate;
  }
}

