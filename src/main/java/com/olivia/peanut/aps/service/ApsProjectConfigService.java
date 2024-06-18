package com.olivia.peanut.aps.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.aps.api.entity.apsProjectConfig.*;
import com.olivia.peanut.aps.model.ApsProjectConfig;
import com.olivia.sdk.utils.DynamicsPage;
import java.util.List;

/**
 * (ApsProjectConfig)表服务接口
 *
 * @author peanut
 * @since 2024-03-30 16:21:20
 */
public interface ApsProjectConfigService extends MPJBaseService<ApsProjectConfig> {

  ApsProjectConfigQueryListRes queryList(ApsProjectConfigQueryListReq req);

  DynamicsPage<ApsProjectConfigExportQueryPageListInfoRes> queryPageList(ApsProjectConfigExportQueryPageListReq req);


  void setName(List<? extends ApsProjectConfigDto> apsProjectConfigDtoList);
}

