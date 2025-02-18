package com.olivia.peanut.aps.api.entity.apsOrder;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum ApsOrderStatusEnum {
  INIT(0L, "下单成功"),//
  MAKE_ING(50L, "制造中"),
  MAKE_OK(80L, "制造完成"), //
  CANCELLED(99L, "已取消"),//
  FINISHED(100L, "已完成"),//
  ;

  private final static Map<Long, String> kv = new HashMap<>();

  static {
    for (ApsOrderStatusEnum value : ApsOrderStatusEnum.values()) {
      kv.put(value.code, value.desc);
    }
  }

  private final Long code;
  private final String desc;

  public static String getDescByCode(Long code) {
    return kv.get(code);
  }
}
