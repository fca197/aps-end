package com.olivia.peanut.aps.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsGoodsForecastMainMake)表实体类
 *
 * @author peanut
 * @since 2024-04-08 09:52:49
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("aps_goods_forecast_main_make")
public class ApsGoodsForecastMainMake extends BaseEntity {

  private Long goodsId;
  private String forecastMakeMainNo;
  private String forecastMakeMainName;
  private String forecastMakeMainBeginDate;
  private String forecastMakeMainEndDate;
  private Long factoryId;

}

