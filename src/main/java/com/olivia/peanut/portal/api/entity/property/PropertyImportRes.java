package com.olivia.peanut.portal.api.entity.property;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 资产信息(Property)保存返回
 *
 * @author makejava
 * @since 2024-03-11 17:20:53
 */
@Accessors(chain = true)
@Getter
@Setter

public class PropertyImportRes {

  /****
   * 写入行数
   */
  private int count;
  /**
   * 错误信息
   */
  private List<String> errorMsg;
}

