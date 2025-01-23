package com.olivia.peanut.aps.api.entity.apsOrder;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum ApsOrderStatusEnum {
  INIT(0L, "下单成功"),//
  PAID_RESERVED(10L, "已支付定金"), //
  PAID_FINISHED(30L, "已支付尾款"), //
  MAKE_ING(40L, "制造中"),
  DELIVERED(50L, "已发货"), //
  FINISHED(60L, "已完成"),//
  CANCELLED(70L, "已取消"),
  ;

  private final Long code;
  private final String desc;

  private final static Map<Long, String> kv = new HashMap<>();

  static {
    for (ApsOrderStatusEnum value : ApsOrderStatusEnum.values()) {
      kv.put(value.code, value.desc);
    }
  }

  public static String getDescByCode(Long code) {
    return kv.get(code);
  }
}
