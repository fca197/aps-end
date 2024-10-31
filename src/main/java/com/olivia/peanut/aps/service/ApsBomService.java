package com.olivia.peanut.aps.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.aps.api.entity.apsBom.*;
import com.olivia.peanut.aps.model.ApsBom;
import com.olivia.sdk.utils.DynamicsPage;

import java.util.List;

/**
 * BOM 清单(ApsBom)表服务接口
 *
 * @author peanut
 * @since 2024-06-06 11:27:34
 */
public interface ApsBomService extends MPJBaseService<ApsBom> {

  ApsBomQueryListRes queryList(ApsBomQueryListReq req);

  DynamicsPage<ApsBomExportQueryPageListInfoRes> queryPageList(ApsBomExportQueryPageListReq req);


  void setName(List<? extends ApsBomDto> apsBomDtoList);
}

