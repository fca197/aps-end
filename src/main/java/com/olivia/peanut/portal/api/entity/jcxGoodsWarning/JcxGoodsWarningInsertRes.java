package com.olivia.peanut.portal.api.entity.jcxGoodsWarning;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (JcxGoodsWarning)保存返回
 *
 * @author peanut
 * @since 2024-03-24 14:10:55
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class JcxGoodsWarningInsertRes {

  /****
   * 写入行数
   */
  private int count;

}

