package com.olivia.peanut.aps.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.aps.api.entity.apsSchedulingGoodsBom.*;
import com.olivia.peanut.aps.model.ApsSchedulingGoodsBom;
import com.olivia.sdk.utils.DynamicsPage;

import java.util.List;

/**
 * 订单商品零件表(ApsSchedulingGoodsBom)表服务接口
 *
 * @author peanut
 * @since 2024-06-02 21:50:25
 */
public interface ApsSchedulingGoodsBomService extends MPJBaseService<ApsSchedulingGoodsBom> {

  ApsSchedulingGoodsBomQueryListRes queryList(ApsSchedulingGoodsBomQueryListReq req);

  DynamicsPage<ApsSchedulingGoodsBomExportQueryPageListInfoRes> queryPageList(ApsSchedulingGoodsBomExportQueryPageListReq req);


  void setName(List<? extends ApsSchedulingGoodsBomDto> apsSchedulingGoodsBomDtoList);
}

