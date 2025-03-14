package com.olivia.peanut.base.api.entity.baseH3Code;


import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * H3对应的值(BaseH3Code)查询对象返回
 *
 * @author makejava
 * @since 2024-11-19 16:09:19
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseH3CodeDto extends BaseEntityDto {

  /***
   *  纬度
   */
  @NotNull(message = "纬度不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private BigDecimal lat;
  /***
   *  经度
   */
  @NotNull(message = "经度不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private BigDecimal lng;
  /***
   *  第0级对应的h3值
   */
  @NotNull(message = "第0级对应的h3值不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long value0;
  /***
   *  第1级对应的h3值
   */
  @NotNull(message = "第1级对应的h3值不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long value1;
  /***
   *  第2级对应的h3值
   */
  @NotNull(message = "第2级对应的h3值不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long value2;
  /***
   *  第3级对应的h3值
   */
  @NotNull(message = "第3级对应的h3值不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long value3;
  /***
   *  第4级对应的h3值
   */
  @NotNull(message = "第4级对应的h3值不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long value4;
  /***
   *  第5级对应的h3值
   */
  @NotNull(message = "第5级对应的h3值不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long value5;
  /***
   *  第6级对应的h3值
   */
  @NotNull(message = "第6级对应的h3值不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long value6;
  /***
   *  第7级对应的h3值
   */
  @NotNull(message = "第7级对应的h3值不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long value7;
  /***
   *  第8级对应的h3值
   */
  @NotNull(message = "第8级对应的h3值不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long value8;
  /***
   *  第9级对应的h3值
   */
  @NotNull(message = "第9级对应的h3值不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long value9;
  /***
   *  第10级对应的h3值
   */
  @NotNull(message = "第10级对应的h3值不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long value10;
  /***
   *  第11级对应的h3值
   */
  @NotNull(message = "第11级对应的h3值不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long value11;
  /***
   *  第12级对应的h3值
   */
  @NotNull(message = "第12级对应的h3值不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long value12;
  /***
   *  第13级对应的h3值
   */
  @NotNull(message = "第13级对应的h3值不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long value13;
  /***
   *  第14级对应的h3值
   */
  @NotNull(message = "第14级对应的h3值不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long value14;
  /***
   *  第15级对应的h3值
   */
  @NotNull(message = "第15级对应的h3值不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long value15;

}


