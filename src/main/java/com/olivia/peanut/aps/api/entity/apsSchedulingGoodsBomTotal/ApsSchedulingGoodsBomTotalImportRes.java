package com.olivia.peanut.aps.api.entity.apsSchedulingGoodsBomTotal;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 订单商品零件汇总表(ApsSchedulingGoodsBomTotal)保存返回
 *
 * @author peanut
 * @since 2024-06-02 22:04:08
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingGoodsBomTotalImportRes {

  /****
   * 写入行数
   */
  private int count;
  /**
   * 错误信息
   */
  private List<String> errorMsg;
}

