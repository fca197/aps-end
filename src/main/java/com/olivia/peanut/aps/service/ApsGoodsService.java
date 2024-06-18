package com.olivia.peanut.aps.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.aps.api.entity.apsGoods.*;
import com.olivia.peanut.aps.model.ApsGoods;
import com.olivia.sdk.utils.DynamicsPage;
import java.util.List;

/**
 * (ApsGoods)表服务接口
 *
 * @author peanut
 * @since 2024-03-29 16:11:23
 */
public interface ApsGoodsService extends MPJBaseService<ApsGoods> {

  ApsGoodsQueryListRes queryList(ApsGoodsQueryListReq req);

  DynamicsPage<ApsGoodsExportQueryPageListInfoRes> queryPageList(ApsGoodsExportQueryPageListReq req);


  void setName(List<? extends ApsGoodsDto> ApsGoodsDtoList);
}


