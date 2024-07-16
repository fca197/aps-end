package com.olivia.peanut.aps.service;

import com.olivia.sdk.utils.DynamicsPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.olivia.peanut.aps.model.ApsRollingForecastOrderItem;
import java.util.List;
import com.github.yulichang.base.MPJBaseService;

import com.olivia.peanut.aps.api.entity.apsRollingForecastOrderItem.*;

/**
 * 滚动预测订单节点表(ApsRollingForecastOrderItem)表服务接口
 *
 * @author peanut
 * @since 2024-07-16 10:31:19
 */
public interface ApsRollingForecastOrderItemService extends MPJBaseService<ApsRollingForecastOrderItem> {

  ApsRollingForecastOrderItemQueryListRes queryList(ApsRollingForecastOrderItemQueryListReq req);

  DynamicsPage<ApsRollingForecastOrderItemExportQueryPageListInfoRes> queryPageList(ApsRollingForecastOrderItemExportQueryPageListReq req);


  void setName(List<? extends ApsRollingForecastOrderItemDto> apsRollingForecastOrderItemDtoList);
}

