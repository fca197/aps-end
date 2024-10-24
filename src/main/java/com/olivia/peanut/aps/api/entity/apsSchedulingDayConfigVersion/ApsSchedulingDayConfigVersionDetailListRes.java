package com.olivia.peanut.aps.api.entity.apsSchedulingDayConfigVersion;

import com.olivia.sdk.utils.DynamicsPage.Header;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/***
 *
 */
@Setter
@Getter
@Accessors(chain = true)
public class ApsSchedulingDayConfigVersionDetailListRes {

  private LocalDate scheduledDate;

  private List<Header> headerList;

  private Map<String, List<Map<String, Object>>> versionDetailMap;
}
