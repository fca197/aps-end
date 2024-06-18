package com.olivia.peanut.portal.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (JcxBuyPlan)表实体类
 *
 * @author peanut
 * @since 2024-03-24 20:27:11
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("jcx_buy_plan")
public class JcxBuyPlan extends BaseEntity {

  private String planName;
  private String planStatus;

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
  private Long buyOrderId;
}

