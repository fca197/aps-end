package com.olivia.peanut.aps.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 订单商品零件表(ApsSchedulingGoodsBom)表实体类
 *
 * @author peanut
 * @since 2024-06-02 21:50:25
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("aps_scheduling_goods_bom")
public class ApsSchedulingGoodsBom extends BaseEntity {

  /***
   *  订单ID
   */
  private Long schedulingId;
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
   *  零件ID
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


}

