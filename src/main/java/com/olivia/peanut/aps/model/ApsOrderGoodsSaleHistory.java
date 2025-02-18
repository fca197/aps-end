package com.olivia.peanut.aps.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * 销售规划订单历史销售占比(ApsOrderGoodsSaleHistory)表实体类
 *
 * @author makejava
 * @since 2025-02-18 14:28:42
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("aps_order_goods_sale_config_history")
public class ApsOrderGoodsSaleHistory extends BaseEntity {
  /***
   *  工厂ID
   */
  private Long factoryId;
  /***
   *  商品ID
   */
  private Long goodsId;
  /***
   *  销售上级ID
   */
  private Long saleParentId;
  /***
   *  销售ID
   */
  private Long saleConfigId;
  private Integer year;
  /***
   *  1月销售数量
   */
  private BigDecimal monthCount01;
  /***
   *  1月销售占比
   */
  private BigDecimal monthRatio01;
  /***
   *  2月销售数量
   */
  private BigDecimal monthCount02;
  /***
   *  2月销售占比
   */
  private BigDecimal monthRatio02;
  /***
   *  3月销售数量
   */
  private BigDecimal monthCount03;
  /***
   *  3月销售占比
   */
  private BigDecimal monthRatio03;
  /***
   *  4月销售数量
   */
  private BigDecimal monthCount04;
  /***
   *  4月销售占比
   */
  private BigDecimal monthRatio04;
  /***
   *  5月销售数量
   */
  private BigDecimal monthCount05;
  /***
   *  5月销售占比
   */
  private BigDecimal monthRatio05;
  /***
   *  6月销售数量
   */
  private BigDecimal monthCount06;
  /***
   *  6月销售占比
   */
  private BigDecimal monthRatio06;
  /***
   *  7月销售数量
   */
  private BigDecimal monthCount07;
  /***
   *  7月销售占比
   */
  private BigDecimal monthRatio07;
  /***
   *  8月销售数量
   */
  private BigDecimal monthCount08;
  /***
   *  8月销售占比
   */
  private BigDecimal monthRatio08;
  /***
   *  9月销售数量
   */
  private BigDecimal monthCount09;
  /***
   *  9月销售占比
   */
  private BigDecimal monthRatio09;
  /***
   *  10月销售数量
   */
  private BigDecimal monthCount10;
  /***
   *  10月销售占比
   */
  private BigDecimal monthRatio10;
  /***
   *  11月销售数量
   */
  private BigDecimal monthCount11;
  /***
   *  11月销售占比
   */
  private BigDecimal monthRatio11;
  /***
   *  12月销售数量
   */
  private BigDecimal monthCount12;
  /***
   *  12月销售占比
   */
  private BigDecimal monthRatio12;

}

