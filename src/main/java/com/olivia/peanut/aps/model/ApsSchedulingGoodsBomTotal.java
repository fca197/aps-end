package com.olivia.peanut.aps.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

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
   *  排产版本 ID
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
  private BigDecimal bomUsage;
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


  public ApsSchedulingGoodsBomTotal setBomUsageAdd(BigDecimal bomUsage) {
    if (Objects.isNull(this.bomUsage))
      this.bomUsage = bomUsage;
    else
      this.bomUsage = this.bomUsage.add(bomUsage);
    return this;
  }

  public ApsSchedulingGoodsBomTotal setBomCostPriceAdd(BigDecimal bomCostPrice) {
    if (Objects.isNull(bomCostPrice))
      this.bomCostPrice = bomCostPrice;
    else
      this.bomCostPrice = this.bomCostPrice.add(bomCostPrice);
    return this;
  }
}

