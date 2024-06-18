package com.olivia.peanut.aps.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.aps.api.entity.apsOrderGoodsSaleConfig.*;
import com.olivia.peanut.aps.model.ApsOrderGoodsSaleConfig;
import com.olivia.sdk.utils.DynamicsPage;
import java.util.List;

/**
 * (ApsOrderGoodsSaleConfig)表服务接口
 *
 * @author peanut
 * @since 2024-04-09 13:19:38
 */
public interface ApsOrderGoodsSaleConfigService extends MPJBaseService<ApsOrderGoodsSaleConfig> {

  ApsOrderGoodsSaleConfigQueryListRes queryList(ApsOrderGoodsSaleConfigQueryListReq req);

  DynamicsPage<ApsOrderGoodsSaleConfigExportQueryPageListInfoRes> queryPageList(ApsOrderGoodsSaleConfigExportQueryPageListReq req);


  void setName(List<? extends ApsOrderGoodsSaleConfigDto> apsOrderGoodsSaleConfigDtoList);
}

