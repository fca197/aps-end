package com.olivia.peanut.aps.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsSchedulingVersionCapacity)表实体类
 *
 * @author peanut
 * @since 2024-04-18 14:57:34
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("aps_scheduling_version_capacity")
public class ApsSchedulingVersionCapacity extends BaseEntity {

  private Long schedulingVersionId;
  private String currentDay;
  private Long orderId;
  private String orderNo;
  private Long factoryId;
  private Long goodsId;
  private Long goodsStatusId;
  private String field0;
  private String field1;
  private String field2;
  private String field3;
  private String field4;
  private String field5;
  private String field6;
  private String field7;
  private String field8;
  private String field9;
  private String field10;
  private String field11;
  private String field12;
  private String field13;
  private String field14;
  private String field15;
  private String field16;
  private String field17;
  private String field18;
  private String field19;
  private String field20;
  private String limitResult;
  private Integer resultType;
  private Long numberIndex;

}

