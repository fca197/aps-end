package com.olivia.peanut.aps.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;

/**
 * 滚动预测订单节点表(ApsRollingForecastOrderItem)表实体类
 *
 * @author peanut
 * @since 2024-07-16 10:31:19
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("aps_rolling_forecast_order_item")
public class ApsRollingForecastOrderItem extends BaseEntity {

  /***
   *  预测ID
   */
  private Long forecastId;
  /***
   *  工厂ID
   */
  private Long factoryId;
  /***
   *  订单ID
   */
  private Long orderId;
  /***
   *  状态ID
   */
  private Long goodsStatusId;
  /***
   *  状态开始时间
   */
  private LocalDate statusBeginDate;

}

