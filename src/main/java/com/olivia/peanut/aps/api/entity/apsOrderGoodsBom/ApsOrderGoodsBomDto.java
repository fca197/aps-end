package com.olivia.peanut.aps.api.entity.apsOrderGoodsBom;

import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 订单商品零件表(ApsOrderGoodsBom)查询对象返回
 *
 * @author peanut
 * @since 2024-07-30 10:28:23
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsOrderGoodsBomDto extends BaseEntityDto {

  /***
   *  订单ID
   */
  @NotNull(message = "订单ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long orderId;


  @NotNull(message = "商品零件ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long goodsBomId;
  /***
   *  商品ID
   */
  @NotNull(message = "商品ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long goodsId;
  /***
   *  订单状态
   */
  @NotNull(message = "订单状态不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long goodsStatusId;
  /***
   *  零件ID
   */
  @NotNull(message = "零件ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long bomId;
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
   *  使用量
   */
  @NotNull(message = "使用量不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private BigDecimal bomUsage;
  /***
   *  规格
   */
  @NotBlank(message = "规格不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String bomUnit;
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
   *  使用工位
   */
  @NotNull(message = "使用工位不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long bomUseWorkStation;
  /***
   *  使用时间
   */
  @NotNull(message = "使用时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private LocalDate bomUseDate;
  /***
   *  工厂ID
   */
  @NotNull(message = "工厂ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long factoryId;

}


