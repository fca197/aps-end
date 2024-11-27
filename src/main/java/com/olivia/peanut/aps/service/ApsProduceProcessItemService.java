package com.olivia.peanut.aps.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.aps.api.entity.apsProduceProcessItem.*;
import com.olivia.peanut.aps.model.ApsProduceProcessItem;
import com.olivia.sdk.utils.DynamicsPage;

import java.util.List;

/**
 * aps 生产机器(ApsProduceProcessItem)表服务接口
 *
 * @author makejava
 * @since 2024-10-24 17:00:22
 */
public interface ApsProduceProcessItemService extends MPJBaseService<ApsProduceProcessItem> {
  ApsProduceProcessItemQueryListRes queryList(ApsProduceProcessItemQueryListReq req);

  DynamicsPage<ApsProduceProcessItemExportQueryPageListInfoRes> queryPageList(ApsProduceProcessItemExportQueryPageListReq req);


  void setName(List<? extends ApsProduceProcessItemDto> apsProduceProcessItemDtoList);
}

