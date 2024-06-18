package com.olivia.peanut.aps.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsGoodsForecastMain)表实体类
 *
 * @author peanut
 * @since 2024-04-02 09:42:27
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("aps_goods_forecast_main")
public class ApsGoodsForecastMain extends BaseEntity {

  private Long factoryId;
  private Long goodsId;
  private String forecastNo;
  private String forecastName;
  private String forecastBeginDate;
  private String forecastEndDate;
  private String month;
  private String months;


}

