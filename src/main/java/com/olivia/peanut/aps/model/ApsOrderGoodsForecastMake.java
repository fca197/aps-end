package com.olivia.peanut.aps.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 订单商品节点预测表(ApsOrderGoodsForecastMake)表实体类
 *
 * @author peanut
 * @since 2024-06-02 23:11:41
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("aps_order_goods_forecast_make")
public class ApsOrderGoodsForecastMake extends BaseEntity {

  private Long orderId;
  /***
   *  商品ID
   */
  private Long goodsId;
  /***
   *  订单状态
   */
  private Long goodsStatusId;
  /***
   *  节点名称
   */
  private String goodsStatusName;
  /***
   *  预计制造日期
   */
  private LocalDateTime forecastMakeDate;
  /***
   *  工厂ID
   */
  private Long factoryId;

}

