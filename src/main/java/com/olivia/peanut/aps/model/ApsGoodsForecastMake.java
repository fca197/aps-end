package com.olivia.peanut.aps.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsGoodsForecastMake)表实体类
 *
 * @author peanut
 * @since 2024-04-07 15:07:48
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("aps_goods_forecast_make")
public class ApsGoodsForecastMake extends BaseEntity {

  private Long forecastMainId;
  private Long goodsId;
  private String forecastMakeMonthNo;
  private String forecastMakeMonthName;
  private String forecastMakeMonthBeginDate;
  private String forecastMakeMonthEndDate;
  private Long factoryId;
  private String month;
  private String weeks;
  private Boolean isDeploy;


  private String bomUseBeginDate;
  private String bomUseEndDate;
}

