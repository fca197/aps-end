package com.olivia.peanut.portal.api.entity.jcxBuyOrderItem;

import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * (JcxBuyOrderItem)查询对象返回
 *
 * @author peanut
 * @since 2024-03-27 13:51:37
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class JcxBuyOrderItemDto extends BaseEntityDto {


  private Long orderId;
  private Long goodsId;
  private String goodsName;
  private BigDecimal goodsCostPrice;
  private Long goodsBuyCount;
  private String goodsUnit;
  private BigDecimal goodsCostPriceTotal;
  private String goodsBuyRemark;

  private Long supplierId;
  private String supplierName;
}


