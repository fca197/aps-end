package com.olivia.peanut.aps.api.entity.apsProduceProcessItem;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * aps 生产机器(ApsProduceProcessItem)根据ID删除多个反参
 *
 * @author makejava
 * @since 2024-10-24 17:00:21
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsProduceProcessItemDeleteByIdListRes {
  /***
   * 受影响行数
   */
  private int count;

}

