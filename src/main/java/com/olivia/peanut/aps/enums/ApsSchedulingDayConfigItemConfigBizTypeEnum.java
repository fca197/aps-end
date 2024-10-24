package com.olivia.peanut.aps.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/***
 *
 */

@Getter
@AllArgsConstructor
public enum ApsSchedulingDayConfigItemConfigBizTypeEnum {


  sale("sale", "销售"), project("project", "工程"), bom("bom", "零件"), sleep("sleep", "休眠");

  private final String code;
  private final String desc;


}
