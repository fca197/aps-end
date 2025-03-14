package com.olivia.peanut.base.api.entity.tenantInfo;


import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 租户信息(TenantInfo)表实体类
 *
 * @author peanut
 * @since 2023-12-28 16:34:24
 */
@Data
@Accessors(chain = true)
public class TenantInfoUpdateIdReq {

  @NotNull(message = "修改信息不能为空")
  private Long id;
  /**
   * 租户名称
   **/
  private String tenantName;
  /**
   * 租户编码
   **/
  private String tenantCode;
}


