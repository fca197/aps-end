package com.olivia.peanut.base.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.base.api.entity.baseH3Code.*;
import com.olivia.peanut.base.model.BaseH3Code;
import com.olivia.sdk.utils.DynamicsPage;

import java.math.BigDecimal;
import java.util.List;

/**
 * H3对应的值(BaseH3Code)表服务接口
 *
 * @author makejava
 * @since 2024-11-19 16:09:19
 */
public interface BaseH3CodeService extends MPJBaseService<BaseH3Code> {
  BaseH3CodeQueryListRes queryList(BaseH3CodeQueryListReq req);

  DynamicsPage<BaseH3CodeExportQueryPageListInfoRes> queryPageList(BaseH3CodeExportQueryPageListReq req);


  void setName(List<? extends BaseH3CodeDto> baseH3CodeDtoList);

  BaseH3Code saveOrGet(BigDecimal lat, BigDecimal lng);
}

