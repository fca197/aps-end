package com.olivia.peanut.aps.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 订单商品零件汇总表(ApsSchedulingGoodsBomTotal)表实体类
 *
 * @author peanut
 * @since 2024-06-02 22:04:08
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("aps_scheduling_goods_bom_total")
public class ApsSchedulingGoodsBomTotal extends BaseEntity {

  /***
   *  订单ID
   */
  private Long schedulingId;
  private Long bomId;
  /***
   *  零件ID
   */
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
   *  使用量
   */
  private Integer bomUsage;
  /***
   *  规格
   */
  private String bomUnit;
  /***
   *  成本价
   */
  private BigDecimal bomCostPrice;
  /***
   *  规格
   */
  private String bomCostPriceUnit;
  /***
   *  使用工位
   */
  private Long bomUseWorkStation;
  /***
   *  使用时间
   */
  private LocalDate bomUseDate;
  /***
   *  工厂ID
   */
  private Long factoryId;

  public ApsSchedulingGoodsBomTotal setBomUsageAdd(Integer bomUsage) {
    this.bomUsage += bomUsage;
    return this;
  }

  public ApsSchedulingGoodsBomTotal setBomCostPriceAdd(BigDecimal bomCostPrice) {
    this.bomCostPrice = bomCostPrice.add(bomCostPrice);
    return this;
  }
}

