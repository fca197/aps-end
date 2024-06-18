package com.olivia.peanut.aps.model;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsGoodsSaleItem)表实体类
 *
 * @author peanut
 * @since 2024-03-30 10:38:36
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("aps_goods_sale_item")
public class ApsGoodsSaleItem extends BaseEntity {

  private Long goodsId;
  private Long saleConfigId;
  private Integer useForecast;
  private Integer supplierStatus;


  @TableField(exist = false)
  private String saleConfigCode;
  @TableField(exist = false)
  private String saleConfigName;
  @TableField(exist = false)
  private Integer isValue;


  @TableField(exist = false)
  private String parentSaleConfigCode;
  @TableField(exist = false)
  private String parentSaleConfigName;
}

