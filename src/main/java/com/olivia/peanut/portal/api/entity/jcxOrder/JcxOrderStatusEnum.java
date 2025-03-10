package com.olivia.peanut.portal.api.entity.jcxOrder;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum JcxOrderStatusEnum {
  UN_PAY("10", "待付款"), UN_SEND("20", "待发货"), UN_RECEIVE("30", "待收货"), //
  UN_EVALUATE("40", "待评价"), DONE("50", "已完成"), CANCELED("60", "已取消"), //
  REFUND("70", "已退款"), RETURNED("80", "已退货"), REFUND_RETURNED("90", "已退款退货"), //
  CLOSED("100", "已关闭"), UN_CLOSE("110", "已作废"), REFUND_CLOSED("120", "已退款退货关闭"),
  //
  REFUND_RETURNED_CLOSED("130", "已退款退货作废");

  public static final Map<String, String> orderStatusNameMap;

  static {
    Map<String, String> tmp = new HashMap<>();
    for (JcxOrderStatusEnum jcxOrderStatusEnum : JcxOrderStatusEnum.values()) {
      tmp.put(jcxOrderStatusEnum.code, jcxOrderStatusEnum.msg);
    }
    orderStatusNameMap = Collections.unmodifiableMap(tmp);
  }

  final String code;

  final String msg;
}
