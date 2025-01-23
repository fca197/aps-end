package com.olivia.peanut.portal.api.entity.tenantInfo;

import com.olivia.peanut.portal.model.TenantInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/***
 *
 */
@Setter
@Getter
@Accessors(chain = true)
public class QueryByIdListRes {

  private List<TenantInfo> data;
}
