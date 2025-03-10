package com.olivia.peanut.portal.api.entity.processLine;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 流水线信息(ProcessLine)保存返回
 *
 * @author makejava
 * @since 2024-03-11 10:55:21
 */
@Accessors(chain = true)
@Getter
@Setter

public class ProcessLineImportRes {

  /****
   * 写入行数
   */
  private int count;
  /**
   * 错误信息
   */
  private List<String> errorMsg;
}

