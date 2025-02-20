package com.olivia.peanut.aps.service;

import com.olivia.sdk.utils.DynamicsPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.olivia.peanut.aps.model.ApsOrderGoodsHistory;

import java.util.List;

import com.github.yulichang.base.MPJBaseService;

import com.olivia.peanut.aps.api.entity.apsOrderGoodsHistory.*;

/**
 * 历史订单记录(ApsOrderGoodsHistory)表服务接口
 *
 * @author makejava
 * @since 2025-02-20 17:18:46
 */
public interface ApsOrderGoodsHistoryService extends MPJBaseService<ApsOrderGoodsHistory> {
  ApsOrderGoodsHistoryQueryListRes queryList(ApsOrderGoodsHistoryQueryListReq req);

  DynamicsPage<ApsOrderGoodsHistoryExportQueryPageListInfoRes> queryPageList(ApsOrderGoodsHistoryExportQueryPageListReq req);


  void setName(List<? extends ApsOrderGoodsHistoryDto> apsOrderGoodsHistoryDtoList);
}

