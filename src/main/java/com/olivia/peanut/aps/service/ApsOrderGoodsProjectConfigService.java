package com.olivia.peanut.aps.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.aps.api.entity.apsOrderGoodsProjectConfig.*;
import com.olivia.peanut.aps.model.ApsOrderGoodsProjectConfig;
import com.olivia.sdk.utils.DynamicsPage;
import java.util.List;

/**
 * (ApsOrderGoodsProjectConfig)表服务接口
 *
 * @author peanut
 * @since 2024-04-09 13:19:37
 */
public interface ApsOrderGoodsProjectConfigService extends MPJBaseService<ApsOrderGoodsProjectConfig> {

  ApsOrderGoodsProjectConfigQueryListRes queryList(ApsOrderGoodsProjectConfigQueryListReq req);

  DynamicsPage<ApsOrderGoodsProjectConfigExportQueryPageListInfoRes> queryPageList(ApsOrderGoodsProjectConfigExportQueryPageListReq req);


  void setName(List<? extends ApsOrderGoodsProjectConfigDto> apsOrderGoodsProjectConfigDtoList);
}

