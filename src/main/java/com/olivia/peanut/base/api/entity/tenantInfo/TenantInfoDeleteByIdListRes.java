package com.olivia.peanut.base.api.entity.tenantInfo;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 租户信息(TenantInfo)根据ID删除多个反参
 *
 * @author makejava
 * @since 2024-03-11 10:55:21
 */
@Accessors(chain = true)
@Getter
@Setter

public class TenantInfoDeleteByIdListRes {

  /***
   * 受影响行数
   */
  private int count;

}

