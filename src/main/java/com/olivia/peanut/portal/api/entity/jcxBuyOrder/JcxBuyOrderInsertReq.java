package com.olivia.peanut.portal.api.entity.jcxBuyOrder;

import cn.hutool.core.collection.CollUtil;
import com.olivia.sdk.utils.$;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * (JcxBuyOrder)保存入参
 *
 * @author peanut
 * @since 2024-03-27 13:51:36
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class JcxBuyOrderInsertReq extends JcxBuyOrderDto {

  @NotNull(message = "购买计划不能为空")
  @Size(min = 1, message = "购买计划最低一条")
  private List<Long> buyPlanIdList;

  public void checkParam() {
    $.requireNonNullCanIgnoreException(StringUtils.isNotBlank(this.getOrderName()), "订单名称不能为空");
    $.requireNonNullCanIgnoreException(CollUtil.isNotEmpty(buyPlanIdList), "购买计划不能为空");
  }
}

