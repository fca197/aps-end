package com.olivia.peanut.aps.api.entity.apsProduceProcess;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * aps 生产路径(ApsProduceProcess)保存返回
 *
 * @author makejava
 * @since 2024-10-24 17:00:18
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsProduceProcessInsertRes {
  /****
   * 写入行数
   */
  private int count;

  private Long id;
}

