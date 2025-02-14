package com.olivia.peanut.aps.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * (ApsOrderGoods)表实体类
 *
 * @author peanut
 * @since 2024-04-09 13:19:36
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("aps_order_goods")
public class ApsOrderGoods extends BaseEntity {

  private Long orderId;
  private Long goodsId;
  private String goodsName;
  private String goodsRemark;
  private BigDecimal goodsAmount;
  private BigDecimal goodsPrice;
  private BigDecimal goodsTotalPrice;
  private String goodsUnit;
  private BigDecimal goodsUnitPrice;
  private BigDecimal goodsUnitTotalPrice;
  private Long factoryId;
  private Long apsStatusId;

}

