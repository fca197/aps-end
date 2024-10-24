package com.olivia.peanut.portal.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (JcxOrderItem)表实体类
 *
 * @author peanut
 * @since 2024-03-22 10:38:07
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("jcx_order_item")
public class JcxOrderItem extends BaseEntity {

  private Long orderId;
  private String orderRemark;
  private Long goodsId;
  private BigDecimal goodsCount;
  private BigDecimal goodsCostPrice;
  private BigDecimal goodsSalePrice;
  private BigDecimal goodsGrossProfit;
  private BigDecimal goodsNetProfit;
  private BigDecimal goodsTotalSalePrice;
}

