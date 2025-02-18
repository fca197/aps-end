package com.olivia.peanut.aps.api.entity.apsOrderGoodsSaleHistory;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 销售规划订单历史销售占比(ApsOrderGoodsSaleHistory)保存返回
 *
 * @author makejava
 * @since 2025-02-18 14:28:41
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsOrderGoodsSaleHistoryImportRes {
  /****
   * 写入行数
   */
  private int count;
  /**
   * 错误信息
   */
  private List<String> errorMsg;
}

