package com.olivia.peanut.base.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * H3对应的值(BaseH3Code)表实体类
 *
 * @author makejava
 * @since 2024-11-19 16:09:18
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("base_h3_code")
public class BaseH3Code extends BaseEntity {
  /***
   *  纬度
   */
  private BigDecimal lat;
  /***
   *  经度
   */
  private BigDecimal lng;
  /***
   *  第0级对应的h3值
   */
  private Long value0;
  /***
   *  第1级对应的h3值
   */
  private Long value1;
  /***
   *  第2级对应的h3值
   */
  private Long value2;
  /***
   *  第3级对应的h3值
   */
  private Long value3;
  /***
   *  第4级对应的h3值
   */
  private Long value4;
  /***
   *  第5级对应的h3值
   */
  private Long value5;
  /***
   *  第6级对应的h3值
   */
  private Long value6;
  /***
   *  第7级对应的h3值
   */
  private Long value7;
  /***
   *  第8级对应的h3值
   */
  private Long value8;
  /***
   *  第9级对应的h3值
   */
  private Long value9;
  /***
   *  第10级对应的h3值
   */
  private Long value10;
  /***
   *  第11级对应的h3值
   */
  private Long value11;
  /***
   *  第12级对应的h3值
   */
  private Long value12;
  /***
   *  第13级对应的h3值
   */
  private Long value13;
  /***
   *  第14级对应的h3值
   */
  private Long value14;
  /***
   *  第15级对应的h3值
   */
  private Long value15;

}

