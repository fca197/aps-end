package com.olivia.peanut.base.api.entity.tenantInfo;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 租户信息(TenantInfo)表实体类
 *
 * @author peanut
 * @since 2023-12-28 16:34:24
 */
@Data
@Accessors(chain = true)
public class TenantInfoDeleteReq {

  @NotNull(message = "删除条数不能为空")
  @Size(min = 1, max = 100, message = "删除条数必须介于 {min} 到 {max} 之间")
  private List<Long> idList;
}


