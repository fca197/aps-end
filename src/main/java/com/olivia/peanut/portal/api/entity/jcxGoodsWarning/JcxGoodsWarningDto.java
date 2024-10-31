package com.olivia.peanut.portal.api.entity.jcxGoodsWarning;

import com.olivia.peanut.portal.api.entity.BaseEntityDto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * (JcxGoodsWarning)查询对象返回
 *
 * @author peanut
 * @since 2024-03-24 14:10:55
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class JcxGoodsWarningDto extends BaseEntityDto {


  private String reportNo;

  private Long goodsId;
  private String goodsName;
  private BigDecimal costPrice;
  private BigDecimal salesPrice;
  private Integer warningCount;
  private BigDecimal goodsGrossProfit;
  private BigDecimal goodsNetProfit;
  private BigDecimal goodsInventoryCount;
  private Boolean isInventory;
  private Boolean isDone;


}


