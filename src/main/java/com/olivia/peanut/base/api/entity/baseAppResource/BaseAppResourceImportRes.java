package com.olivia.peanut.base.api.entity.baseAppResource;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 资源(BaseAppResource)保存返回
 *
 * @author peanut
 * @since 2024-08-06 17:30:28
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseAppResourceImportRes {

  /****
   * 写入行数
   */
  private int count;
  /**
   * 错误信息
   */
  private List<String> errorMsg;
}

