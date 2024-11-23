package com.olivia.peanut.aps.api.entity.apsBom;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public enum SupplyModelEnum {
  make("自制"), buy("购买"), UNK("未知");
  private final String desc;

  public static String getDesc(String key) {
    try {
      return SupplyModelEnum.valueOf(key).getDesc();
    } catch (Exception e) {
      return UNK.getDesc();
    }
  }
}
