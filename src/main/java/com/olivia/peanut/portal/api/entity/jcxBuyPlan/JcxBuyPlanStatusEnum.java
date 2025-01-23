package com.olivia.peanut.portal.api.entity.jcxBuyPlan;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/***
 *
 */
@Getter
@AllArgsConstructor
public enum JcxBuyPlanStatusEnum {
  INIT("10", "草稿"),
  //
  SUCCESS("50", "通过"),
  REJECT("99", "驳回"),
  ;

  public static final Map<String, String> codeMsg;

  static {
    Map<String, String> map = new HashMap<>();
    for (JcxBuyPlanStatusEnum value : values()) {
      map.put(value.code, value.msg);
    }
    codeMsg = Collections.unmodifiableMap(map);
  }

  String code;
  String msg;

  public String getMsg(String code) {
    return codeMsg.get(code);
  }
}
