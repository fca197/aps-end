package com.olivia.peanut.aps.api.entity.apsOrderGoodsSaleConfig;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsOrderGoodsSaleConfig)保存返回
 *
 * @author peanut
 * @since 2024-04-09 13:19:38
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsOrderGoodsSaleConfigImportRes {

  /****
   * 写入行数
   */
  private int count;
  /**
   * 错误信息
   */
  private List<String> errorMsg;
}

