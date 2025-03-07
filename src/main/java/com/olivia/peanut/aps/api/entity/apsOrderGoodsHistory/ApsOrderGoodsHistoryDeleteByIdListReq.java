package com.olivia.peanut.aps.api.entity.apsOrderGoodsHistory;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 历史订单记录(ApsOrderGoodsHistory)根据ID删除多个入参
 *
 * @author makejava
 * @since 2025-02-20 17:18:46
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsOrderGoodsHistoryDeleteByIdListReq {
  /***
   * 要删除的ID
   */
  @NotEmpty(message = "请选择删除对象")
  private List<Long> idList;

}

