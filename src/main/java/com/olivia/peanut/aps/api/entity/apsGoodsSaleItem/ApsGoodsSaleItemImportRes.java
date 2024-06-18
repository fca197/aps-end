package com.olivia.peanut.aps.api.entity.apsGoodsSaleItem;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsGoodsSaleItem)保存返回
 *
 * @author peanut
 * @since 2024-03-30 10:38:36
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsSaleItemImportRes {

  /****
   * 写入行数
   */
  private int count;
  /**
   * 错误信息
   */
  private List<String> errorMsg;
}

