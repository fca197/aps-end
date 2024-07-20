package com.olivia.peanut.aps.service;

import com.olivia.sdk.utils.DynamicsPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.olivia.peanut.aps.model.ApsSchedulingDayConfigVersion;
import java.util.List;
import com.github.yulichang.base.MPJBaseService;

import com.olivia.peanut.aps.api.entity.apsSchedulingDayConfigVersion.*;

/**
 * 排程版本(ApsSchedulingDayConfigVersion)表服务接口
 *
 * @author peanut
 * @since 2024-07-19 19:19:55
 */
public interface ApsSchedulingDayConfigVersionService extends MPJBaseService<ApsSchedulingDayConfigVersion> {

  ApsSchedulingDayConfigVersionInsertRes save(ApsSchedulingDayConfigVersionInsertReq req);

  ApsSchedulingDayConfigVersionQueryListRes queryList(ApsSchedulingDayConfigVersionQueryListReq req);

  DynamicsPage<ApsSchedulingDayConfigVersionExportQueryPageListInfoRes> queryPageList(ApsSchedulingDayConfigVersionExportQueryPageListReq req);


  void setName(List<? extends ApsSchedulingDayConfigVersionDto> apsSchedulingDayConfigVersionDtoList);


}

