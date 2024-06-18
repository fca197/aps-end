package com.olivia.peanut.aps.service.impl.po;

import java.util.Objects;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/***
 *
 */
@Setter
@Getter
@Accessors(chain = true)
public class OrderGoods {

  private Long orderId;
  private Long goodsId;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OrderGoods that = (OrderGoods) o;
    return Objects.equals(orderId, that.orderId) && Objects.equals(goodsId, that.goodsId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(orderId, goodsId);
  }
}
