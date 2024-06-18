package com.olivia.peanut.aps.service;

import com.olivia.sdk.utils.DynamicsPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.olivia.peanut.aps.model.ApsSchedulingGoodsStatusDate;
import java.util.List;
import com.github.yulichang.base.MPJBaseService;

import com.olivia.peanut.aps.api.entity.apsSchedulingGoodsStatusDate.*;

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

