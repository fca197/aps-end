package com.olivia.peanut.portal.api.entity.brand;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 品牌信息(Brand)保存返回
 *
 * @author makejava
 * @since 2024-03-11 10:44:02
 */
@Accessors(chain = true)
@Getter
@Setter

public class BrandInsertRes {

  /****
   * 写入行数
   */
  private int count;

}

