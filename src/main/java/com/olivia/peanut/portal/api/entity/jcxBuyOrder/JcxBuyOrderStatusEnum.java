package com.olivia.peanut.portal.api.entity.jcxBuyOrder;

import com.olivia.sdk.exception.CanIgnoreException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum JcxBuyOrderStatusEnum {
  INIT(10, "草稿"), //
  PASS(30, "审核通过"), //
  SUCCESS(50, "成功"), //
  FINISH(60, "完成"),
  REJECT(99, "驳回"),
  ;


  public static final Map<Integer, String> codeMsg;

  static {
    Map<Integer, String> map = new HashMap<>();
    for (JcxBuyOrderStatusEnum value : values()) {
      map.put(value.code, value.msg);
    }
    codeMsg = Collections.unmodifiableMap(map);
  }

  private final int code;
  private final String msg;

  public static void checkCode(Object code) {
    if (!(code instanceof Integer) || !codeMsg.containsKey(code)) {
      throw new CanIgnoreException("状态不正确");
    }
  }
}
