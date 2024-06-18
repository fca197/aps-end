package com.olivia.peanut.portal.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.portal.api.entity.tenantInfo.TenantInfoExportQueryPageListInfoRes;
import com.olivia.peanut.portal.api.entity.tenantInfo.TenantInfoExportQueryPageListReq;
import com.olivia.peanut.portal.api.entity.tenantInfo.TenantInfoQueryListReq;
import com.olivia.peanut.portal.api.entity.tenantInfo.TenantInfoQueryListRes;
import com.olivia.peanut.portal.model.TenantInfo;
import com.olivia.sdk.utils.DynamicsPage;

/**
 * 租户信息(TenantInfo)表服务接口
 *
 * @author makejava
 * @since 2024-03-11 10:55:22
 */
public interface TenantInfoService extends MPJBaseService<TenantInfo> {

  TenantInfoQueryListRes queryList(TenantInfoQueryListReq req);

  DynamicsPage<TenantInfoExportQueryPageListInfoRes> queryPageList(TenantInfoExportQueryPageListReq req);

}

