package com.olivia.peanut.portal.api.entity.brand;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 品牌信息(Brand)根据ID删除多个反参
 *
 * @author makejava
 * @since 2024-03-11 10:44:02
 */
@Accessors(chain = true)
@Getter
@Setter

public class BrandDeleteByIdListRes {

  /***
   * 受影响行数
   */
  private int count;

}

