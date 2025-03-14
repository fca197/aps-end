package com.olivia.peanut.base.api.entity.baseTableHeader;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * (BaseTableHeader)保存返回
 *
 * @author peanut
 * @since 2024-03-25 14:19:10
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseTableHeaderImportRes {

  /****
   * 写入行数
   */
  private int count;
  /**
   * 错误信息
   */
  private List<String> errorMsg;
}

