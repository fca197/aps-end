package com.olivia.peanut.aps.api.entity.apsGoodsForecast;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ForecastStatusEnum {
  TO_UPLOAD(10, "待上传"), //
  TO_COMPUTED(30, "待计算"), //
  COMPUTED_RESULT(50, "计算结束"),
  ;
  private final int code;
  private final String msg;
}
