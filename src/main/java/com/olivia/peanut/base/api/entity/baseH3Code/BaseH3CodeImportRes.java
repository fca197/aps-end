package com.olivia.peanut.base.api.entity.baseH3Code;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * H3对应的值(BaseH3Code)保存返回
 *
 * @author makejava
 * @since 2024-11-19 16:09:18
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseH3CodeImportRes {
  /****
   * 写入行数
   */
  private int count;
  /**
   * 错误信息
   */
  private List<String> errorMsg;
}

