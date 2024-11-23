package com.olivia.peanut.aps.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * BOM 购买清单(ApsGoodsBomBuyPlanItem)表实体类
 *
 * @author peanut
 * @since 2024-06-05 14:35:31
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("aps_goods_bom_buy_plan_item")
public class ApsGoodsBomBuyPlanItem extends BaseEntity {


  private Long buyPlanId;

  /***
   *  ID
   */
  private Long bomId;
  private Long goodsBomId;
  /***
   *  bom 编码
   */
  private String bomCode;
  /***
   *  bom 名称
   */
  private String bomName;
  /***
   *  是否关注
   */
  private Boolean isFollow;
  /***
   *  成本价
   */
  private BigDecimal bomCostPrice;
  /***
   *  规格
   */
  private String bomCostPriceUnit;
  /***
   *  库存
   */
  private BigDecimal bomInventory;
  /***
   *  购买数量
   */
  private BigDecimal bomBuyCount;

}

