package com.olivia.peanut.aps.api.entity.apsGoodsBom;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import com.olivia.sdk.utils.Str;
import com.olivia.sdk.utils.fastjson.Str2BooleanConverter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

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
  @ExcelIgnore
  @NotNull(message = "零件名称不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long goodsId;
  @ExcelProperty("商品名称")
  private String goodsName;


  //工厂ID
  @ExcelIgnore
  @NotNull(message = "工厂不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long factoryId;

  @ExcelProperty("工厂")
  private String factoryName;

  @ExcelIgnore
  //  @NotBlank(message = "零件名称不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  @NotNull(message = "零件不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long bomId;
  //零件编号
  @ExcelProperty("零件编码")
  @NotBlank(message = "零件编号不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String bomCode;
  // 名称
  @ExcelProperty("零件名称")
  @NotBlank(message = "零件名称不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String bomName;
  // 用量
  @ExcelProperty("零件用量")
  @NotNull(message = "零件用量不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private BigDecimal bomUsage;
  //规格
  @ExcelProperty("零件规格")
  @NotBlank(message = "零件规格不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String bomUnit;
  // 成本价
  @ExcelProperty("成本价")
  @NotNull(message = "零件成本价不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private BigDecimal bomCostPrice;
  // 成本价规格
  @ExcelProperty("零件成本价规格")
  @NotBlank(message = "零件成本价规格不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String bomCostPriceUnit;
  //使用工位
  @ExcelIgnore
  @NotNull(message = "零件使用工位不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long bomUseWorkStation;
  @ExcelProperty("零件使用工位")
  private String bomUseWorkStationName;
  @ExcelProperty("零件使用表达式")
  //使用表达 工程值: 格式 . 所有工序  (AA001&&AC002)&&(AB001||AB002)
  @NotBlank(message = "零件使用表达式不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String bomUseExpression;

  /***
   * 是否关注
   */
//  //@JSONField(label = "isFollow", serializeUsing = Boolean2StrFeature.class, deserializeUsing = Str2BooleanConverter.class)
  @ExcelProperty(value = "关注", converter = Str2BooleanConverter.class)
  private Boolean isFollow;

  public String getFollow() {
    return Str.booleanToStr(isFollow);
  }
}


