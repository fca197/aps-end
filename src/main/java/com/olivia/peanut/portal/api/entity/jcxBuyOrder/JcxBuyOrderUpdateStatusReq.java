package com.olivia.peanut.portal.api.entity.jcxBuyOrder;

import com.olivia.sdk.utils.$;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/***
 *
 */
@Setter
@Getter
@Accessors(chain = true)
public class JcxBuyOrderUpdateStatusReq extends JcxBuyOrderDto {

  public void checkParam() {
    $.requireNonNullCanIgnoreException(this.getId(), "订单不能为空");
    $.requireNonNullCanIgnoreException(this.getOrderStatus(), "订单状态不能为空");
    $.requireNonNullCanIgnoreException(this.getVersionNum(), "版本不能为空");
    JcxBuyOrderStatusEnum.checkCode(this.getOrderStatus());
  }
}
