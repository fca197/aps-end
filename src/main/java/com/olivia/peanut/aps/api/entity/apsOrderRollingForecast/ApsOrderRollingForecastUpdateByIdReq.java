package com.olivia.peanut.aps.api.entity.apsOrderRollingForecast;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 滚动预测(ApsOrderRollingForecast)表实体类
 *
 * @author peanut
 * @since 2024-07-14 19:43:51
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsOrderRollingForecastUpdateByIdReq extends ApsOrderRollingForecastDto {


  public void checkParam() {
  }

}

