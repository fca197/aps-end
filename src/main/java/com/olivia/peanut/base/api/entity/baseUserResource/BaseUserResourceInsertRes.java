package com.olivia.peanut.base.api.entity.baseUserResource;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 用户角色资源表(BaseUserResource)保存返回
 *
 * @author peanut
 * @since 2024-08-09 16:26:39
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseUserResourceInsertRes {

  /****
   * 写入行数
   */
  private int count;

  private Long id;
}

