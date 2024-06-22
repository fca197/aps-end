package com.olivia.peanut.aps.api.entity.apsOrderGoodsStatusDate;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 订单商品状态表(ApsOrderGoodsStatusDate)保存返回
 *
 * @author peanut
 * @since 2024-06-14 10:26:59
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsOrderGoodsStatusDateImportRes {

  /****
   * 写入行数
   */
  private int count;
  /**
   * 错误信息
   */
  private List<String> errorMsg;
}

