package com.olivia.peanut.aps.api.entity.apsBom;

import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

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


  @NotNull(message = "groupId不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long groupId;

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

  /***
   * 供应方式 ，make , buy
   */
  @NotBlank(message = "供应方式不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String supplyMode;

  /***
   * 规格 ，100个*6
   */
  @NotBlank(message = "零件规格不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String bomUnit;

  /**
   * 使用单位
   */
  @NotBlank(message = "使用单位不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String useUnit;


}


