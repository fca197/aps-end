package com.olivia.peanut.base.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.base.api.entity.baseTableHeader.BaseTableHeaderExportQueryPageListInfoRes;
import com.olivia.peanut.base.api.entity.baseTableHeader.BaseTableHeaderExportQueryPageListReq;
import com.olivia.peanut.base.api.entity.baseTableHeader.BaseTableHeaderQueryListReq;
import com.olivia.peanut.base.api.entity.baseTableHeader.BaseTableHeaderQueryListRes;
import com.olivia.peanut.base.model.BaseTableHeader;
import com.olivia.sdk.utils.DynamicsPage;

/**
 * (BaseTableHeader)表服务接口
 *
 * @author peanut
 * @since 2024-03-25 14:19:10
 */
public interface BaseTableHeaderService extends MPJBaseService<BaseTableHeader> {

  BaseTableHeaderQueryListRes queryList(BaseTableHeaderQueryListReq req);

  DynamicsPage<BaseTableHeaderExportQueryPageListInfoRes> queryPageList(BaseTableHeaderExportQueryPageListReq req);

  void listByBizKey(DynamicsPage<? extends Object> page, String bizKey);
}

