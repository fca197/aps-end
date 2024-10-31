package com.olivia.peanut.aps.service;

import com.olivia.sdk.utils.DynamicsPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.olivia.peanut.aps.model.ApsProduceProcessItem;

import java.util.List;

import com.github.yulichang.base.MPJBaseService;

import com.olivia.peanut.aps.api.entity.apsProduceProcessItem.*;

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

