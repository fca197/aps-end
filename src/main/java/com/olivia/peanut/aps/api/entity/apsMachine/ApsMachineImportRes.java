package com.olivia.peanut.aps.api.entity.apsMachine;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * aps 生产机器(ApsMachine)保存返回
 *
 * @author makejava
 * @since 2024-10-24 16:31:15
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsMachineImportRes {
  /****
   * 写入行数
   */
  private int count;
  /**
   * 错误信息
   */
  private List<String> errorMsg;
}

