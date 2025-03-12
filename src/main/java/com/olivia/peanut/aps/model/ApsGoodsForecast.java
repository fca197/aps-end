package com.olivia.peanut.aps.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import com.olivia.sdk.utils.JSON;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * (ApsGoodsForecast)表实体类
 *
 * @author peanut
 * @since 2024-03-30 13:38:53
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("aps_goods_forecast")
public class ApsGoodsForecast extends BaseEntity {

  private Long goodsId;
  private String forecastNo;
  private String forecastName;
  private String forecastBeginDate;
  private String forecastEndDate;
  private String months;
  private Integer forecastStatus;

  public List<String> getMonthList() {
    return JSON.readList(this.getMonths(), String.class);
  }
}

