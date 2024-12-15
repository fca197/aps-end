package com.olivia.peanut.aps.api.entity.apsSchedulingGoodsBom;

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
 * 订单商品零件表(ApsSchedulingGoodsBom)查询对象返回
 *
 * @author peanut
 * @since 2024-06-02 21:50:25
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingGoodsBomDto extends BaseEntityDto {

  /***
   *  排产ID
   */
  @NotNull(message = "排产不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long schedulingId;
  /***
   *  商品ID
   */
  @NotNull(message = "商品不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long goodsId;
  /***
   *  订单状态
   */
  @NotNull(message = "订单状态不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long goodsStatusId;
  /***
   *  零件ID
   */
  @NotNull(message = "零件不能为空", groups = {InsertCheck.class, UpdateCheck.class})
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
  @NotBlank(message = "成本价规格不能为空", groups = {InsertCheck.class, UpdateCheck.class})
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
  @NotNull(message = "工厂不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long factoryId;

}


