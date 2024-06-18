package com.olivia.peanut.portal.api.entity.storey;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 楼层信息(Storey)保存返回
 *
 * @author makejava
 * @since 2024-03-11 17:20:55
 */
@Accessors(chain = true)
@Getter
@Setter

public class StoreyImportRes {

  /****
   * 写入行数
   */
  private int count;
  /**
   * 错误信息
   */
  private List<String> errorMsg;
}

