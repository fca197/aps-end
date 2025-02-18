package com.olivia.peanut.aps.service;

import com.olivia.sdk.utils.DynamicsPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.olivia.peanut.aps.model.ApsOrderGoodsSaleHistory;

import java.util.List;

import com.github.yulichang.base.MPJBaseService;

import com.olivia.peanut.aps.api.entity.apsOrderGoodsSaleHistory.*;

/**
 * 销售规划订单历史销售占比(ApsOrderGoodsSaleHistory)表服务接口
 *
 * @author makejava
 * @since 2025-02-18 14:28:42
 */
public interface ApsOrderGoodsSaleHistoryService extends MPJBaseService<ApsOrderGoodsSaleHistory> {
  ApsOrderGoodsSaleHistoryQueryListRes queryList(ApsOrderGoodsSaleHistoryQueryListReq req);

  DynamicsPage<ApsOrderGoodsSaleHistoryExportQueryPageListInfoRes> queryPageList(ApsOrderGoodsSaleHistoryExportQueryPageListReq req);


  void setName(List<? extends ApsOrderGoodsSaleHistoryDto> apsOrderGoodsSaleHistoryDtoList);
}

