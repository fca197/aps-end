package com.olivia.peanut.aps.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.aps.api.entity.apsGoodsBom.*;
import com.olivia.peanut.aps.model.ApsGoodsBom;
import com.olivia.sdk.utils.DynamicsPage;

import java.util.List;

/**
 * (ApsGoodsBom)表服务接口
 *
 * @author peanut
 * @since 2024-04-03 22:28:56
 */
public interface ApsGoodsBomService extends MPJBaseService<ApsGoodsBom> {

  ApsGoodsBomQueryListRes queryList(ApsGoodsBomQueryListReq req);

  DynamicsPage<ApsGoodsBomExportQueryPageListInfoRes> queryPageList(ApsGoodsBomExportQueryPageListReq req);


  void setName(List<? extends ApsGoodsBomDto> apsGoodsBomDtoList);
}

