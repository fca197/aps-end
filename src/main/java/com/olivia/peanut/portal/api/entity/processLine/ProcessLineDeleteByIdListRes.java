package com.olivia.peanut.portal.api.entity.processLine;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 流水线信息(ProcessLine)根据ID删除多个反参
 *
 * @author makejava
 * @since 2024-03-11 10:55:20
 */
@Accessors(chain = true)
@Getter
@Setter

public class ProcessLineDeleteByIdListRes {

  /***
   * 受影响行数
   */
  private int count;

}

