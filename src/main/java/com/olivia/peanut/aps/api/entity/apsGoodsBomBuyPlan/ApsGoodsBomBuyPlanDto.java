package com.olivia.peanut.aps.api.entity.apsGoodsBomBuyPlan;

import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * BOM 购买计划(ApsGoodsBomBuyPlan)查询对象返回
 *
 * @author peanut
 * @since 2024-06-05 14:35:30
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsBomBuyPlanDto extends BaseEntityDto {

  /***
   *  计划名称
   */
  @NotBlank(message = "计划名称不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String planName;
  /***
   *  计划金额
   */
  @NotNull(message = "计划金额不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private BigDecimal planTotalAmount;
  /***
   *  计划来源
   */
  @NotBlank(message = "计划来源不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String planSource;
  /***
   *  计划备注
   */
  @NotBlank(message = "计划备注不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String planRemark;

  private String buyPlanType;
  private String bomUseDate;

  public void setBuyPlanType(Object buyPlanType) {
    this.buyPlanType = String.valueOf(buyPlanType);
  }
}


