package com.olivia.peanut.aps.model;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 订单商品零件表(ApsOrderGoodsBom)表实体类
 *
 * @author peanut
 * @since 2024-07-30 10:28:23
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("aps_order_goods_bom")
public class ApsOrderGoodsBom extends BaseEntity {

  /***
   *  订单ID
   */
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
   *  单位
   */
  private String bomUnit;
  /***
   *  成本价
   */
  private BigDecimal bomCostPrice;
  /***
   *  单位
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

