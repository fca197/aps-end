package com.olivia.peanut.aps.api.entity.apsOrder;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatusEnum {
  UNPAID(0, "未支付"),//
  PAID_RESERVED(10, "已支付定金"), //
  PAID_FINISHED(30, "已支付尾款"), //
  DELIVERED(50, "已发货"), //
  FINISHED(60, "已完成"),//
  CANCELLED(70, "已取消"),
  ;

  private final Integer code;
  private final String desc;

}
