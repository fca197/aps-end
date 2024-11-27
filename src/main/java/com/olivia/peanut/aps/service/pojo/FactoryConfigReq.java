package com.olivia.peanut.aps.service.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;

/***
 *
 */
@Setter
@Getter
@Accessors(chain = true)
public class FactoryConfigReq {


  private Long factoryId;
  private String factoryName;
  private Boolean getWeek;
  private LocalDate weekBeginDate;
  private LocalDate weekEndDate;
  private Boolean getShift;
  private Boolean getPath;
  private Long getPathId;
  private Boolean getPathDefault;

  private LocalDateTime nowDateTime;
}
