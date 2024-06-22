package com.olivia.peanut.aps.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.aps.api.entity.apsSchedulingGoodsStatusDate.*;
import com.olivia.peanut.aps.model.ApsSchedulingGoodsStatusDate;
import com.olivia.sdk.utils.DynamicsPage;
import java.util.List;

/**
 * 订单商品状态表(ApsSchedulingGoodsStatusDate)表服务接口
 *
 * @author peanut
 * @since 2024-06-14 21:35:09
 */
public interface ApsSchedulingGoodsStatusDateService extends MPJBaseService<ApsSchedulingGoodsStatusDate> {

  ApsSchedulingGoodsStatusDateQueryListRes queryList(ApsSchedulingGoodsStatusDateQueryListReq req);

  DynamicsPage<ApsSchedulingGoodsStatusDateExportQueryPageListInfoRes> queryPageList(ApsSchedulingGoodsStatusDateExportQueryPageListReq req);


  void setName(List<? extends ApsSchedulingGoodsStatusDateDto> apsSchedulingGoodsStatusDateDtoList);
}

