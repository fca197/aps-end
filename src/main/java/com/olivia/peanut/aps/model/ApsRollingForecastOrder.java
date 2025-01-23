package com.olivia.peanut.aps.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;

/**
 * 滚动预测(ApsRollingForecastOrder)表实体类
 *
 * @author peanut
 * @since 2024-07-14 20:22:28
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("aps_rolling_forecast_order")
public class ApsRollingForecastOrder extends BaseEntity {

  private Long factoryId;
  private Long beginStatusId;
  private Long endStatusId;
  /***
   *  唯一编码
   */
  private String rollCode;
  /***
   *  名称
   */
  private String rollName;
  /***
   *  开始时间
   */
  private LocalDate beginTime;
  /***
   *  结束时间
   */
  private LocalDate endTime;

}

