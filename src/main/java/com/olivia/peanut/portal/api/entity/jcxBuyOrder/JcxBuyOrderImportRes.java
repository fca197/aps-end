package com.olivia.peanut.portal.api.entity.jcxBuyOrder;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (JcxBuyOrder)保存返回
 *
 * @author peanut
 * @since 2024-03-27 13:51:36
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class JcxBuyOrderImportRes {

  /****
   * 写入行数
   */
  private int count;
  /**
   * 错误信息
   */
  private List<String> errorMsg;
}

