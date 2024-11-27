package com.olivia.peanut.aps.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.aps.api.entity.apsSchedulingIssueItem.*;
import com.olivia.peanut.aps.model.ApsSchedulingIssueItem;
import com.olivia.sdk.utils.DynamicsPage;

import java.util.List;

/**
 * 排产下发详情(ApsSchedulingIssueItem)表服务接口
 *
 * @author peanut
 * @since 2024-07-20 13:55:55
 */
public interface ApsSchedulingIssueItemService extends MPJBaseService<ApsSchedulingIssueItem> {

  ApsSchedulingIssueItemQueryListRes queryList(ApsSchedulingIssueItemQueryListReq req);

  DynamicsPage<ApsSchedulingIssueItemExportQueryPageListInfoRes> queryPageList(ApsSchedulingIssueItemExportQueryPageListReq req);


  void setName(List<? extends ApsSchedulingIssueItemDto> apsSchedulingIssueItemDtoList);

  ApsSchedulingIssueItemInsertRes save(ApsSchedulingIssueItemInsertReq req);
}

