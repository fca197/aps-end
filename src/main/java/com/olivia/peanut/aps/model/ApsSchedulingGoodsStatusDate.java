package com.olivia.peanut.aps.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 订单商品状态表(ApsSchedulingGoodsStatusDate)表实体类
 *
 * @author peanut
 * @since 2024-06-14 21:35:09
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("aps_scheduling_goods_status_date")
public class ApsSchedulingGoodsStatusDate extends BaseEntity {

  /***
   *  排产ID
   */
  private Long schedulingId;
  /***
   *  订单ID
   */
  private Long orderId;
  /***
   *  商品ID
   */
  private Long goodsId;
  /***
   *  订单状态
   */
  private Long goodsStatusId;
  /***
   *  期望制造时间
   */
  private LocalDate expectMakeBeginTime;

  private LocalDate expectMakeEndTime;
  /***
   *  实际制造时间
   */
  private LocalDateTime actualMakeBeginTime;
  private LocalDateTime actualMakeEndTime;
  /***
   *  工厂ID
   */
  private Long factoryId;
  /***
   *  状态索引
   */
  private Integer statusIndex;

}

