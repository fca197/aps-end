package com.olivia.peanut.portal.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * (JcxBuyPlanItem)表实体类
 *
 * @author peanut
 * @since 2024-03-24 20:27:12
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("jcx_buy_plan_item")
public class JcxBuyPlanItem extends BaseEntity {

  private Long planId;
  private Long goodsId;
  private BigDecimal costPrice;
  private BigDecimal salesPrice;
  private Long warningCount;
  private BigDecimal goodsGrossProfit;
  private BigDecimal goodsNetProfit;
  private Long goodsInventoryCount;
  private Long goodsBuyCount;


  /***
   * 成本价
   */
  private BigDecimal costPriceTotal;
  /***
   * 销售价格
   */
  private BigDecimal salesPriceTotal;

  /***
   * 毛利(分)
   */
  private BigDecimal goodsGrossProfitTotal;
  /**
   * 净利(分)
   */
  private BigDecimal goodsNetProfitTotal;
}

