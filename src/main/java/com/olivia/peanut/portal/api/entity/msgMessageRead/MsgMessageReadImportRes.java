package com.olivia.peanut.portal.api.entity.msgMessageRead;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (MsgMessageRead)保存返回
 *
 * @author peanut
 * @since 2024-03-23 19:17:47
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class MsgMessageReadImportRes {

  /****
   * 写入行数
   */
  private int count;
  /**
   * 错误信息
   */
  private List<String> errorMsg;
}

