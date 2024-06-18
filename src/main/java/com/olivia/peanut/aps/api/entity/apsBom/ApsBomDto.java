package com.olivia.peanut.aps.api.entity.apsBom;

import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

/**
 * BOM 清单(ApsBom)查询对象返回
 *
 * @author peanut
 * @since 2024-06-06 11:27:34
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsBomDto extends BaseEntityDto {

  /***
   *  bom 编码
   */
  @NotBlank(message = "bom 编码不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String bomCode;
  /***
   *  bom 名称
   */
  @NotBlank(message = "bom 名称不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String bomName;
  /***
   *  成本价
   */
  @NotNull(message = "成本价不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private BigDecimal bomCostPrice;
  /***
   *  单位
   */
  @NotBlank(message = "单位不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String bomCostPriceUnit;
  /***
   *  库存
   */
  @NotNull(message = "库存不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private BigDecimal bomInventory;

}


