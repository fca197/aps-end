package com.olivia.peanut.aps.service.pojo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

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
