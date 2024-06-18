package com.olivia.peanut.aps.service.impl.po;

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
public class ProjectConfig {

  List<LocalDate> calendarList;
  private Long projectId;
}
