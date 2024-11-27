package com.olivia.peanut.aps.api.entity.apsSchedulingGoodsBom;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 订单商品零件表(ApsSchedulingGoodsBom)保存返回
 *
 * @author peanut
 * @since 2024-06-02 21:50:25
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingGoodsBomImportRes {

  /****
   * 写入行数
   */
  private int count;
  /**
   * 错误信息
   */
  private List<String> errorMsg;
}

