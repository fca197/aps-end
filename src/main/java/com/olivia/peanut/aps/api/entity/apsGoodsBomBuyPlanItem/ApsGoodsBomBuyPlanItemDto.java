package com.olivia.peanut.aps.api.entity.apsGoodsBomBuyPlanItem;

import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import com.olivia.sdk.utils.Str;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * BOM 购买清单(ApsGoodsBomBuyPlanItem)查询对象返回
 *
 * @author peanut
 * @since 2024-06-05 14:35:31
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsBomBuyPlanItemDto extends BaseEntityDto {


  private Long buyPlanId;
  /***
   *  ID
   */
  @NotNull(message = "零件不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long bomId;
  /***
   *  bom 编码
   */
  @NotBlank(message = "零件编码不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String bomCode;
  /***
   *  bom 名称
   */
  @NotBlank(message = "零件名称不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String bomName;
  /***
   *  是否关注
   */
  @NotNull(message = "是否关注不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Boolean isFollow;
  /***
   *  成本价
   */
  @NotNull(message = "成本价不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private BigDecimal bomCostPrice;
  /***
   *  规格
   */
  @NotBlank(message = "规格不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String bomCostPriceUnit;
  /***
   *  库存
   */
  @NotNull(message = "库存不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private BigDecimal bomInventory;
  /***
   *  购买数量
   */
  @NotNull(message = "购买数量不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private BigDecimal bomBuyCount;

  public String getFollowStr() {
    return Str.booleanToStr(isFollow);
  }
}


