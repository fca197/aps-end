package com.olivia.peanut.portal.api.entity.tenantInfo;

import jakarta.validation.constraints.NotNull;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 租户信息(TenantInfo)查询对象入参
 *
 * @author makejava
 * @since 2024-03-11 10:55:22
 */
@Accessors(chain = true)
@Getter
@Setter

public class TenantInfoQueryByIdListReq {

  @NotNull
  private List<Long> idList;


}

