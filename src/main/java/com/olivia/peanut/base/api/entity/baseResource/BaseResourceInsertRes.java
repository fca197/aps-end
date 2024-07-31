package com.olivia.peanut.base.api.entity.baseResource;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 资源(BaseResource)保存返回
 *
 * @author peanut
 * @since 2024-07-31 14:33:33
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseResourceInsertRes {

  /****
   * 写入行数
   */
  private int count;

  private Long id;
}

