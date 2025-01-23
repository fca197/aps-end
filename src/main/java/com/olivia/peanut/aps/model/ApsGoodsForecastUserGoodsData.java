package com.olivia.peanut.aps.model;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsGoodsForecastUserGoodsData)表实体类
 *
 * @author peanut
 * @since 2024-03-30 18:29:06
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("aps_goods_forecast_user_goods_data")
public class ApsGoodsForecastUserGoodsData extends BaseEntity {

  private Long forecastId;
  @TableField(value = "year")
  private Integer year;

  @TableField(value = "month_01")
  private Double month01;
  @TableField(value = "month_02")
  private Double month02;
  @TableField(value = "month_03")
  private Double month03;
  @TableField(value = "month_04")
  private Double month04;
  @TableField(value = "month_05")
  private Double month05;
  @TableField(value = "month_06")
  private Double month06;
  @TableField(value = "month_07")
  private Double month07;
  @TableField(value = "month_08")
  private Double month08;
  @TableField(value = "month_09")
  private Double month09;
  @TableField(value = "month_10")
  private Double month10;
  @TableField(value = "month_11")
  private Double month11;
  @TableField(value = "month_12")
  private Double month12;


}

