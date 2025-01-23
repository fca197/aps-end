package com.olivia.peanut.aps.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/***
 * 生产方式
 */
@Getter
@AllArgsConstructor
public enum ApsSchedulingDayConfigVersionProductType {
  MAKE("制造路径"), PROCESS("工艺路径");
  private final String desc;
}
