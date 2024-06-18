package com.olivia.peanut.aps.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 订单商品状态表(ApsOrderGoodsStatusDate)表实体类
 *
 * @author peanut
 * @since 2024-06-14 10:26:59
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("aps_order_goods_status_date")
public class ApsOrderGoodsStatusDate extends BaseEntity {

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
  private LocalDate actualMakeBeginTime;
  private LocalDate actualMakeEndTime;
  /***
   *  工厂ID
   */
  private Long factoryId;

  private Integer statusIndex;

}

