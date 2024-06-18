package com.olivia.peanut.aps.api.entity.apsGoodsBomBuyPlanItem;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * BOM 购买清单(ApsGoodsBomBuyPlanItem)保存返回
 *
 * @author peanut
 * @since 2024-06-05 14:35:31
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsBomBuyPlanItemImportRes {

  /****
   * 写入行数
   */
  private int count;
  /**
   * 错误信息
   */
  private List<String> errorMsg;
}

