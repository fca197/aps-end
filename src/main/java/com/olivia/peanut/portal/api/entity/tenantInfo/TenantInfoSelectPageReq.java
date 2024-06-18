package com.olivia.peanut.portal.api.entity.tenantInfo;

import com.olivia.peanut.portal.model.TenantInfo;
import com.olivia.sdk.controller.entity.PageQuery;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/***
 *
 */
@Setter
@Getter
@Accessors(chain = true)
public class TenantInfoSelectPageReq extends PageQuery<TenantInfo> {

}
