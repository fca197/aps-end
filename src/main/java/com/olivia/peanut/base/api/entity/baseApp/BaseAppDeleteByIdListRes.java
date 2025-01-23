package com.olivia.peanut.base.api.entity.baseApp;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 应用表(BaseApp)根据ID删除多个反参
 *
 * @author peanut
 * @since 2024-08-05 11:18:57
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseAppDeleteByIdListRes {

  /***
   * 受影响行数
   */
  private int count;

}

