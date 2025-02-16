package com.olivia.peanut.aps.model;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * (ApsGoodsForecastUserSaleData)表实体类
 *
 * @author peanut
 * @since 2024-03-30 18:29:07
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("aps_goods_forecast_user_sale_data")
public class ApsGoodsForecastUserSaleData extends BaseEntity {

  private Long forecastId;
  private Long saleConfigParentId;
  private Long saleConfigId;
  private Integer year;
  @TableField(value = "month_01")
  private BigDecimal month01;
  @TableField(value = "month_02")
  private BigDecimal month02;
  @TableField(value = "month_03")
  private BigDecimal month03;
  @TableField(value = "month_04")
  private BigDecimal month04;
  @TableField(value = "month_05")
  private BigDecimal month05;
  @TableField(value = "month_06")
  private BigDecimal month06;
  @TableField(value = "month_07")
  private BigDecimal month07;
  @TableField(value = "month_08")
  private BigDecimal month08;
  @TableField(value = "month_09")
  private BigDecimal month09;
  @TableField(value = "month_10")
  private BigDecimal month10;
  @TableField(value = "month_11")
  private BigDecimal month11;
  @TableField(value = "month_12")
  private BigDecimal month12;


}

