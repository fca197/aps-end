package com.olivia.peanut.portal.api.entity.storey;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 楼层信息(Storey)根据ID删除多个反参
 *
 * @author makejava
 * @since 2024-03-11 17:20:55
 */
@Accessors(chain = true)
@Getter
@Setter

public class StoreyDeleteByIdListRes {

  /***
   * 受影响行数
   */
  private int count;

}

