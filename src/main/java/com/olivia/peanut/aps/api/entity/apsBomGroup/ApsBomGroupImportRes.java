package com.olivia.peanut.aps.api.entity.apsBomGroup;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 零件组配置(ApsBomGroup)保存返回
 *
 * @author peanut
 * @since 2024-06-19 17:41:23
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsBomGroupImportRes {

  /****
   * 写入行数
   */
  private int count;
  /**
   * 错误信息
   */
  private List<String> errorMsg;
}

