package com.olivia.peanut.aps.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.aps.api.entity.apsSchedulingVersion.*;
import com.olivia.peanut.aps.model.ApsSchedulingVersion;
import com.olivia.sdk.utils.DynamicsPage;
import java.util.List;

/**
 * (ApsSchedulingVersion)表服务接口
 *
 * @author peanut
 * @since 2024-04-13 21:32:14
 */
public interface ApsSchedulingVersionService extends MPJBaseService<ApsSchedulingVersion> {

  ApsSchedulingVersionQueryListRes queryList(ApsSchedulingVersionQueryListReq req);

  DynamicsPage<ApsSchedulingVersionExportQueryPageListInfoRes> queryPageList(ApsSchedulingVersionExportQueryPageListReq req);


  void setName(List<? extends ApsSchedulingVersionDto> apsSchedulingVersionDtoList);

  ApsSchedulingVersionUseConstraintsRes useConstraints(ApsSchedulingVersionUseConstraintsReq req);

  ApsSchedulingVersionUseMakeCapacityRes useMakeCapacity(ApsSchedulingVersionUseMakeCapacityReq req);

  DynamicsPage<ApsSchedulingVersionUseConstraintsResultRes> useConstraintsResult(ApsSchedulingVersionUseConstraintsResultReq req);

  DynamicsPage<ApsSchedulingVersionUseMakeCapacityResultRes> useMakeCapacityResult(ApsSchedulingVersionUseMakeCapacityResultReq req);

  ApsSchedulingVersionFinishRes finish(ApsSchedulingVersionFinishReq req);
}

