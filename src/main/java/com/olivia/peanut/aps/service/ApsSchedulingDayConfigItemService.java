package com.olivia.peanut.aps.service;

import com.olivia.sdk.utils.DynamicsPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.olivia.peanut.aps.model.ApsSchedulingDayConfigItem;
import java.util.List;
import com.github.yulichang.base.MPJBaseService;

import com.olivia.peanut.aps.api.entity.apsSchedulingDayConfigItem.*;

/**
 * 排程版本配置表(ApsSchedulingDayConfigItem)表服务接口
 *
 * @author peanut
 * @since 2024-07-19 19:19:52
 */
public interface ApsSchedulingDayConfigItemService extends MPJBaseService<ApsSchedulingDayConfigItem> {

  ApsSchedulingDayConfigItemQueryListRes queryList(ApsSchedulingDayConfigItemQueryListReq req);

  DynamicsPage<ApsSchedulingDayConfigItemExportQueryPageListInfoRes> queryPageList(ApsSchedulingDayConfigItemExportQueryPageListReq req);


  void setName(List<? extends ApsSchedulingDayConfigItemDto> apsSchedulingDayConfigItemDtoList);
}

