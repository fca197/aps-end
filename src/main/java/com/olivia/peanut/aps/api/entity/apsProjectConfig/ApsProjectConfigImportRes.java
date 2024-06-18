package com.olivia.peanut.aps.api.entity.apsProjectConfig;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsProjectConfig)保存返回
 *
 * @author peanut
 * @since 2024-03-30 16:21:20
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsProjectConfigImportRes {

  /****
   * 写入行数
   */
  private int count;
  /**
   * 错误信息
   */
  private List<String> errorMsg;
}

