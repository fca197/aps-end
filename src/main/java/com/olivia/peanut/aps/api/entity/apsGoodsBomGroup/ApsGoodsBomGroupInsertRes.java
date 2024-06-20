package com.olivia.peanut.aps.api.entity.apsGoodsBomGroup;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 零件组配置(ApsGoodsBomGroup)保存返回
 *
 * @author peanut
 * @since 2024-06-19 16:49:04
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsBomGroupInsertRes {

  /****
   * 写入行数
   */
  private int count;

  private Long id;
}

