package com.olivia.peanut.portal.api.entity.jcxOrder;

import cn.hutool.core.collection.CollUtil;
import com.olivia.sdk.exception.CanIgnoreException;
import com.olivia.sdk.utils.$;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * (JcxOrder)保存入参
 *
 * @author peanut
 * @since 2024-03-22 10:38:06
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class JcxOrderInsertReq extends JcxOrderDto {

  private List<GoodsInfo> goodsList;

  public void checkParam() {
    if (CollUtil.isEmpty(goodsList)) {
      throw new CanIgnoreException("商品列表不能为空");
    }
    boolean b = goodsList.stream().anyMatch(t -> Objects.isNull(t.getGoodsId()) || Objects.isNull(t.getGoodsCount()) || Objects.equals(0L, t.getGoodsCount()));
    $.requireNonNullCanIgnoreException(b, "商品或数量不能为0或为空");
  }

  @Setter
  @Getter
  public static class GoodsInfo {

    private BigDecimal goodsCount;
    private Long goodsId;
  }
}

