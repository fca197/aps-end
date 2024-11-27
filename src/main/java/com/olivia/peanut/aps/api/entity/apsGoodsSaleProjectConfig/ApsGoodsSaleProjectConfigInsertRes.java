package com.olivia.peanut.aps.api.entity.apsGoodsSaleProjectConfig;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * (ApsGoodsSaleProjectConfig)保存返回
 *
 * @author peanut
 * @since 2024-04-27 16:07:22
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsSaleProjectConfigInsertRes {

  /****
   * 写入行数
   */
  private int count;

  private List<Long> idList;
}

