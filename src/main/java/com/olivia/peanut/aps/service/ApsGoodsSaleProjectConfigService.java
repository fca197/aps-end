package com.olivia.peanut.aps.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.aps.api.entity.apsGoodsSaleProjectConfig.*;
import com.olivia.peanut.aps.model.ApsGoodsSaleProjectConfig;
import com.olivia.sdk.utils.DynamicsPage;
import java.util.List;

/**
 * (ApsGoodsSaleProjectConfig)表服务接口
 *
 * @author peanut
 * @since 2024-04-27 16:07:22
 */
public interface ApsGoodsSaleProjectConfigService extends MPJBaseService<ApsGoodsSaleProjectConfig> {

  ApsGoodsSaleProjectConfigQueryListRes queryList(ApsGoodsSaleProjectConfigQueryListReq req);

  DynamicsPage<ApsGoodsSaleProjectConfigExportQueryPageListInfoRes> queryPageList(ApsGoodsSaleProjectConfigExportQueryPageListReq req);


  void setName(List<? extends ApsGoodsSaleProjectConfigDto> apsGoodsSaleProjectConfigDtoList);

  ApsGoodsSaleProjectConfigSale2ProjectRes sale2project(ApsGoodsSaleProjectConfigSale2ProjectReq req);
}

