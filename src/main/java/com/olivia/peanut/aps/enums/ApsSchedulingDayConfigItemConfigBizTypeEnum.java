package com.olivia.peanut.aps.enums;

import lombok.AllArgsConstructor;

/***
 *
 */
@AllArgsConstructor
public enum ApsSchedulingDayConfigItemConfigBizTypeEnum {


  sale("sale"), project("project"), bom("bom"), sleep("sleep");

  private final String code;


}
