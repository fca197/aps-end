package com.olivia.peanut.aps.api.entity.apsGoodsBom;

import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsGoodsBom)查询对象返回
 *
 * @author peanut
 * @since 2024-04-03 22:28:56
 */
//@Accessors(chain=true)
@Getter
@Setter
@Accessors(chain = true)
@SuppressWarnings("serial")
public class ApsGoodsBomDto extends BaseEntityDto {

  // 商品ID
  @NotNull(message = "零件名称不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long goodsId;
  //  @NotBlank(message = "零件名称不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  @NotNull(message = "零件不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long bomId;
  private String goodsName;
  private String factoryName;
  //零件编号
  @NotBlank(message = "零件编号不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String bomCode;
  // 名称
  @NotBlank(message = "零件名称不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String bomName;
  // 用量
  @NotNull(message = "零件用量不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private BigDecimal bomUsage;
  //单位
  @NotBlank(message = "零件单位不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String bomUnit;
  // 成本价
  @NotNull(message = "零件成本价不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private BigDecimal bomCostPrice;
  // 成本价单位
  @NotBlank(message = "零件成本价单位不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String bomCostPriceUnit;
  //使用工位
  @NotNull(message = "零件使用工位不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long bomUseWorkStation;
  private String bomUseWorkStationName;
  //使用表达 工程值: 格式 . 所有工序  (AA001&&AC002)&&(AB001||AB002)
  @NotBlank(message = "零件使用工位不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String bomUseExpression;
  //工厂ID
  @NotNull(message = "工厂不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long factoryId;

  /***
   * 是否关注
   */
  private Boolean isFollow;

  public String getFollow() {
    return Boolean.TRUE.equals(isFollow) ? "是" : "否";
  }
}


