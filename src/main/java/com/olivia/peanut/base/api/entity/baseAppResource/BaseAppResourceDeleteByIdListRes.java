package com.olivia.peanut.base.api.entity.baseAppResource;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.util.List;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 资源(BaseAppResource)根据ID删除多个反参
 *
 * @author peanut
 * @since 2024-08-06 17:30:28
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseAppResourceDeleteByIdListRes {

  /***
   * 受影响行数
   */
  private int count;

}

