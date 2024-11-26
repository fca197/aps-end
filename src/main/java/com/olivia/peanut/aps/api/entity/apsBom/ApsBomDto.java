package com.olivia.peanut.aps.api.entity.apsBom;

import com.alibaba.excel.annotation.ExcelProperty;
import com.olivia.peanut.ann.CheckObjectFieldValueAnn;
import com.olivia.peanut.aps.api.entity.apsBom.converter.SupplyModelConverter;
import com.olivia.peanut.enums.CheckEnums;
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

  @CheckObjectFieldValueAnn(checkEnum = CheckEnums.Long, max = 10, fieldShowName = "组ID")
  @NotNull(message = "groupId不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long groupId;

  @CheckObjectFieldValueAnn(checkEnum = CheckEnums.Str, max = 10, strValues = {"组1", "组2"}, fieldShowName = "组名称")
  @ExcelProperty("组名称")
  private String groupName;

  /***
   *  bom 编码
   */
  @ExcelProperty("编码")
  @NotBlank(message = "bom 编码不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String bomCode;
  /***
   *  bom 名称
   */
  @ExcelProperty("名称")
  @NotBlank(message = "bom 名称不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String bomName;
  /***
   *  成本价
   */
  @ExcelProperty("成本价")
  @NotNull(message = "成本价不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private BigDecimal bomCostPrice;
  /***
   *  规格
   */
  @ExcelProperty("规格")
  @NotBlank(message = "规格不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String bomCostPriceUnit;
  /***
   *  库存
   */
  @ExcelProperty("库存")
  @NotNull(message = "库存不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private BigDecimal bomInventory;

  /***
   * 供应方式 ，make , buy
   */
  @ExcelProperty(value = "供应方式", converter = SupplyModelConverter.class)
  @NotBlank(message = "供应方式不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String supplyMode;

  public String getSupplyModeStr() {
    return SupplyModelEnum.getDesc(this.supplyMode);
  }

  /***
   * 规格 ，100个*6
   */
  @ExcelProperty("零件规格")
  @NotBlank(message = "零件规格不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String bomUnit;

  /**
   * 使用规格
   */
  @ExcelProperty("规格")
  @NotBlank(message = "使用规格不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String useUnit;


}


