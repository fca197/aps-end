package com.olivia.peanut.portal.api.entity.jcxGoodsWarning;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (JcxGoodsWarning)根据ID删除多个反参
 *
 * @author peanut
 * @since 2024-03-24 14:10:55
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class JcxGoodsWarningDeleteByIdListRes {

  /***
   * 受影响行数
   */
  private int count;

}

