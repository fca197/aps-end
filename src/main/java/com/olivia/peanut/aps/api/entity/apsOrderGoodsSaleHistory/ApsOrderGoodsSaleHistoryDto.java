package com.olivia.peanut.aps.api.entity.apsOrderGoodsSaleHistory;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.alibaba.excel.annotation.ExcelProperty;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.fastjson2.annotation.JSONField;
import com.olivia.sdk.utils.fastjson.Boolean2StrFeature;
import com.olivia.sdk.utils.fastjson.Str2BooleanConverter;

/**
 * 销售规划订单历史销售占比(ApsOrderGoodsSaleHistory)查询对象返回
 *
 * @author makejava
 * @since 2025-02-18 14:28:43
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsOrderGoodsSaleHistoryDto extends BaseEntityDto {

  /***
   *  工厂ID
   */
  @JSONField(label = "factoryId")
  @NotNull(message = "工厂ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private Long factoryId;
  /***
   *  商品ID
   */
  @JSONField(label = "goodsId")
  @NotNull(message = "商品ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private Long goodsId;
  /***
   *  销售上级ID
   */
  @JSONField(label = "saleParentId")
  @NotNull(message = "销售上级ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private Long saleParentId;
  /***
   *  销售ID
   */
  @JSONField(label = "saleConfigId")
  @NotNull(message = "销售ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private Long saleConfigId;
  /***
   *  1月销售数量
   */
  @JSONField(label = "monthCount01")
  @NotNull(message = "1月销售数量不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private BigDecimal monthCount01;
  /***
   *  1月销售占比
   */
  @JSONField(label = "monthRatio01")
  @NotNull(message = "1月销售占比不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private BigDecimal monthRatio01;
  /***
   *  2月销售数量
   */
  @JSONField(label = "monthCount02")
  @NotNull(message = "2月销售数量不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private BigDecimal monthCount02;
  /***
   *  2月销售占比
   */
  @JSONField(label = "monthRatio02")
  @NotNull(message = "2月销售占比不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private BigDecimal monthRatio02;
  /***
   *  3月销售数量
   */
  @JSONField(label = "monthCount03")
  @NotNull(message = "3月销售数量不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private BigDecimal monthCount03;
  /***
   *  3月销售占比
   */
  @JSONField(label = "monthRatio03")
  @NotNull(message = "3月销售占比不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private BigDecimal monthRatio03;
  /***
   *  4月销售数量
   */
  @JSONField(label = "monthCount04")
  @NotNull(message = "4月销售数量不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private BigDecimal monthCount04;
  /***
   *  4月销售占比
   */
  @JSONField(label = "monthRatio04")
  @NotNull(message = "4月销售占比不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private BigDecimal monthRatio04;
  /***
   *  5月销售数量
   */
  @JSONField(label = "monthCount05")
  @NotNull(message = "5月销售数量不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private BigDecimal monthCount05;
  /***
   *  5月销售占比
   */
  @JSONField(label = "monthRatio05")
  @NotNull(message = "5月销售占比不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private BigDecimal monthRatio05;
  /***
   *  6月销售数量
   */
  @JSONField(label = "monthCount06")
  @NotNull(message = "6月销售数量不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private BigDecimal monthCount06;
  /***
   *  6月销售占比
   */
  @JSONField(label = "monthRatio06")
  @NotNull(message = "6月销售占比不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private BigDecimal monthRatio06;
  /***
   *  7月销售数量
   */
  @JSONField(label = "monthCount07")
  @NotNull(message = "7月销售数量不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private BigDecimal monthCount07;
  /***
   *  7月销售占比
   */
  @JSONField(label = "monthRatio07")
  @NotNull(message = "7月销售占比不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private BigDecimal monthRatio07;
  /***
   *  8月销售数量
   */
  @JSONField(label = "monthCount08")
  @NotNull(message = "8月销售数量不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private BigDecimal monthCount08;
  /***
   *  8月销售占比
   */
  @JSONField(label = "monthRatio08")
  @NotNull(message = "8月销售占比不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private BigDecimal monthRatio08;
  /***
   *  9月销售数量
   */
  @JSONField(label = "monthCount09")
  @NotNull(message = "9月销售数量不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private BigDecimal monthCount09;
  /***
   *  9月销售占比
   */
  @JSONField(label = "monthRatio09")
  @NotNull(message = "9月销售占比不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private BigDecimal monthRatio09;
  /***
   *  10月销售数量
   */
  @JSONField(label = "monthCount10")
  @NotNull(message = "10月销售数量不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private BigDecimal monthCount10;
  /***
   *  10月销售占比
   */
  @JSONField(label = "monthRatio10")
  @NotNull(message = "10月销售占比不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private BigDecimal monthRatio10;
  /***
   *  11月销售数量
   */
  @JSONField(label = "monthCount11")
  @NotNull(message = "11月销售数量不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private BigDecimal monthCount11;
  /***
   *  11月销售占比
   */
  @JSONField(label = "monthRatio11")
  @NotNull(message = "11月销售占比不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private BigDecimal monthRatio11;
  /***
   *  12月销售数量
   */
  @JSONField(label = "monthCount12")
  @NotNull(message = "12月销售数量不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private BigDecimal monthCount12;
  /***
   *  12月销售占比
   */
  @JSONField(label = "monthRatio12")
  @NotNull(message = "12月销售占比不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private BigDecimal monthRatio12;

}


