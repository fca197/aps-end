package com.olivia.peanut.aps.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.peanut.aps.enums.ApsGoodsBomBuyPlanTypeEnum;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * BOM 购买计划(ApsGoodsBomBuyPlan)表实体类
 *
 * @author peanut
 * @since 2024-06-05 14:35:30
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("aps_goods_bom_buy_plan")
public class ApsGoodsBomBuyPlan extends BaseEntity {

  /***
   *  计划名称
   */
  private String planName;
  /***
   *  计划金额
   */
  private BigDecimal planTotalAmount;
  /***
   *  计划来源
   */
  private String planSource;
  /***
   *  计划备注
   */
  private String planRemark;

  /***
   *
   */
  private ApsGoodsBomBuyPlanTypeEnum buyPlanType;

  private String bomUseDate;

}

