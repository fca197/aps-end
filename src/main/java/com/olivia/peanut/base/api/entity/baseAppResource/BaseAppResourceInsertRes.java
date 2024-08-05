package com.olivia.peanut.base.api.entity.baseAppResource;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 资源(BaseAppResource)保存返回
 *
 * @author peanut
 * @since 2024-08-05 11:19:00
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseAppResourceInsertRes {

  /****
   * 写入行数
   */
  private int count;

  private Long id;
}

