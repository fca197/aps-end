package com.olivia.peanut.aps.model;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsGoodsForecastMainSaleData)表实体类
 *
 * @author peanut
 * @since 2024-04-02 09:42:28
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("aps_goods_forecast_main_sale_data")
public class ApsGoodsForecastMainSaleData extends BaseEntity {

  private Long forecastMainId;
  private Long goodsId;
  private String saleConfigCode;
  private Integer year;

  @TableField(value = "month_01")
  private Long month01;
  @TableField(value = "month_02")
  private Long month02;
  @TableField(value = "month_03")
  private Long month03;
  @TableField(value = "month_04")
  private Long month04;
  @TableField(value = "month_05")
  private Long month05;
  @TableField(value = "month_06")
  private Long month06;
  @TableField(value = "month_07")
  private Long month07;
  @TableField(value = "month_08")
  private Long month08;
  @TableField(value = "month_09")
  private Long month09;
  @TableField(value = "month_10")
  private Long month10;
  @TableField(value = "month_11")
  private Long month11;
  @TableField(value = "month_12")
  private Long month12;

}

