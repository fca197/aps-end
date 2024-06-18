package com.olivia.peanut.aps.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.aps.api.entity.apsSchedulingVersionLimit.*;
import com.olivia.peanut.aps.model.ApsSchedulingVersionLimit;
import com.olivia.sdk.utils.DynamicsPage;
import java.util.List;

/**
 * (ApsSchedulingVersionLimit)表服务接口
 *
 * @author peanut
 * @since 2024-04-19 14:56:59
 */
public interface ApsSchedulingVersionLimitService extends MPJBaseService<ApsSchedulingVersionLimit> {

  ApsSchedulingVersionLimitQueryListRes queryList(ApsSchedulingVersionLimitQueryListReq req);

  DynamicsPage<ApsSchedulingVersionLimitExportQueryPageListInfoRes> queryPageList(ApsSchedulingVersionLimitExportQueryPageListReq req);


  void setName(List<? extends ApsSchedulingVersionLimitDto> apsSchedulingVersionLimitDtoList);
}

