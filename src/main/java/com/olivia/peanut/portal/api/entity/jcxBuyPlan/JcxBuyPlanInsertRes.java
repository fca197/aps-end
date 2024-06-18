package com.olivia.peanut.portal.api.entity.jcxBuyPlan;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (JcxBuyPlan)保存返回
 *
 * @author peanut
 * @since 2024-03-24 20:27:10
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class JcxBuyPlanInsertRes {

  /****
   * 写入行数
   */
  private int count;

  private Long id;
}

