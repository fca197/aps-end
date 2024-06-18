package com.olivia.peanut.portal.api.entity.tenantInfo;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 租户信息(TenantInfo)保存返回
 *
 * @author makejava
 * @since 2024-03-11 10:55:21
 */
@Accessors(chain = true)
@Getter
@Setter

public class TenantInfoImportRes {

  /****
   * 写入行数
   */
  private int count;
  /**
   * 错误信息
   */
  private List<String> errorMsg;
}

