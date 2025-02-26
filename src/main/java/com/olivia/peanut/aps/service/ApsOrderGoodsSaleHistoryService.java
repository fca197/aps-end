package com.olivia.peanut.aps.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.aps.api.entity.apsOrderGoodsSaleHistory.*;
import com.olivia.peanut.aps.model.ApsOrderGoodsSaleHistory;
import com.olivia.sdk.utils.DynamicsPage;

import java.util.List;

/**
 * 销售规划订单历史销售占比(ApsOrderGoodsSaleHistory)表服务接口
 *
 * @author makejava
 * @since 2025-02-18 14:28:42
 */
public interface ApsOrderGoodsSaleHistoryService extends MPJBaseService<ApsOrderGoodsSaleHistory> {
  ApsOrderGoodsSaleHistoryQueryListRes queryList(ApsOrderGoodsSaleHistoryQueryListReq req);

  DynamicsPage<ApsOrderGoodsSaleHistoryExportQueryPageListInfoRes> queryPageList(ApsOrderGoodsSaleHistoryExportQueryPageListReq req);

  SelectOrder2HistoryRes selectOrder2History(SelectOrder2HistoryReq req);

  void setName(List<? extends ApsOrderGoodsSaleHistoryDto> apsOrderGoodsSaleHistoryDtoList);

}

