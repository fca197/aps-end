package com.olivia.peanut.portal.api.entity.jcxBuyPlan;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (JcxBuyPlan)保存返回
 *
 * @author peanut
 * @since 2024-03-24 20:27:11
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class JcxBuyPlanImportRes {

  /****
   * 写入行数
   */
  private int count;
  /**
   * 错误信息
   */
  private List<String> errorMsg;
}

