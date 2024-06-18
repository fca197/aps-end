package com.olivia.peanut.portal.api.entity.jcxBuyPlanItem;

import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

/**
 * (JcxBuyPlanItem)查询对象返回
 *
 * @author peanut
 * @since 2024-03-24 20:27:12
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class JcxBuyPlanItemDto extends BaseEntityDto {

  private Long planId;
  private Long goodsId;
  private String goodsName;
  private BigDecimal costPrice;
  private BigDecimal salesPrice;
  private Integer warningCount;
  private BigDecimal goodsGrossProfit;
  private BigDecimal goodsNetProfit;
  private Long goodsInventoryCount;
  private Long goodsBuyCount;
}


