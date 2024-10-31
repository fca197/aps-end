package com.olivia.peanut.portal.api.entity.jcxOrderItem;

import com.alibaba.excel.annotation.ExcelProperty;
import com.olivia.peanut.portal.api.entity.BaseEntityDto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * (JcxOrderItem)查询对象返回
 *
 * @author peanut
 * @since 2024-03-22 10:38:08
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class JcxOrderItemDto extends BaseEntityDto {


  @ExcelProperty("订单ID")
  private Long orderId;
  @ExcelProperty("订单备注")
  private String orderRemark;
  private Long goodsId;

  @ExcelProperty("商品名称")
  private String goodsName;
  @ExcelProperty("商品数量")
  private Integer goodsCount;
  @ExcelProperty("成本价")
  private BigDecimal goodsCostPrice;
  @ExcelProperty("售卖价")
  private BigDecimal goodsSalePrice;
  @ExcelProperty("毛利(分)")
  private BigDecimal goodsGrossProfit;
  @ExcelProperty("纯利")
  private BigDecimal goodsNetProfit;


}


