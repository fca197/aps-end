package com.olivia.peanut.aps.service.impl.po;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.List;

/***
 *
 */
@Setter
@Getter
@Accessors(chain = true)
public class ProjectConfig {

  List<LocalDate> calendarList;
  private Long projectId;
}
