package com.olivia.peanut.aps.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.aps.api.entity.apsGoodsSaleItem.*;
import com.olivia.peanut.aps.model.ApsGoodsSaleItem;
import com.olivia.sdk.utils.DynamicsPage;
import java.util.List;

/**
 * (ApsGoodsSaleItem)表服务接口
 *
 * @author peanut
 * @since 2024-03-30 10:38:36
 */
public interface ApsGoodsSaleItemService extends MPJBaseService<ApsGoodsSaleItem> {

  ApsGoodsSaleItemQueryListRes queryList(ApsGoodsSaleItemQueryListReq req);

  DynamicsPage<ApsGoodsSaleItemExportQueryPageListInfoRes> queryPageList(ApsGoodsSaleItemExportQueryPageListReq req);


  void setName(List<? extends ApsGoodsSaleItemDto> apsGoodsSaleItemDtoList);
}

