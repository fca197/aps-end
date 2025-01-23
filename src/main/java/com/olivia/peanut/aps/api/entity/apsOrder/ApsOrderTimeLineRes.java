package com.olivia.peanut.aps.api.entity.apsOrder;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
    //    private LocalDate endDate;
    /*
    是否延期
     */
    private Boolean isDelay;
    private LocalDate expectMakeBeginTime;
    private LocalDate expectMakeEndTime;
    /***
     *  实际制造时间
     */
    private LocalDateTime actualMakeBeginTime;
    private LocalDateTime actualMakeEndTime;
  }
}

