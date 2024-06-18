package com.olivia.peanut.portal.api.entity.jcxBuyPlan;

import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.peanut.portal.api.entity.jcxBuyPlanItem.JcxBuyPlanItemDto;
import java.math.BigDecimal;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * (JcxBuyPlan)查询对象返回
 *
 * @author peanut
 * @since 2024-03-24 20:27:11
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class JcxBuyPlanDto extends BaseEntityDto {


  private String planName;
  private String planStatus;
  private String planStatusName;
  private Boolean isCreateOrder;

  /***
   * 成本价
   */
  private BigDecimal costPriceTotal;
  /***
   * 销售价格
   */
  private BigDecimal salesPriceTotal;

  /***
   * 毛利(分)
   */
  private BigDecimal goodsGrossProfitTotal;
  /**
   * 净利(分)
   */
  private BigDecimal goodsNetProfitTotal;

  private Long buyOrderId;
  private List<JcxBuyPlanItemDto> jcxBuyPlanItemDtoList;


}


