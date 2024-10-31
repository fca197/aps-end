package com.olivia.peanut.portal.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.portal.api.entity.shiftItem.*;
import com.olivia.peanut.portal.model.ShiftItem;
import com.olivia.sdk.utils.DynamicsPage;

import java.util.List;

/**
 * (ShiftItem)表服务接口
 *
 * @author peanut
 * @since 2024-04-04 12:10:16
 */
public interface ShiftItemService extends MPJBaseService<ShiftItem> {

  ShiftItemQueryListRes queryList(ShiftItemQueryListReq req);

  DynamicsPage<ShiftItemExportQueryPageListInfoRes> queryPageList(ShiftItemExportQueryPageListReq req);


  void setName(List<? extends ShiftItemDto> shiftItemDtoList);
}

