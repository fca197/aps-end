package com.olivia.peanut.base.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.base.api.entity.baseResource.*;
import com.olivia.peanut.base.model.BaseResource;
import com.olivia.sdk.utils.DynamicsPage;

import java.util.List;

/**
 * 资源(BaseResource)表服务接口
 *
 * @author peanut
 * @since 2024-08-06 17:29:01
 */
public interface BaseResourceService extends MPJBaseService<BaseResource> {

  BaseResourceQueryListRes queryList(BaseResourceQueryListReq req);

  DynamicsPage<BaseResourceExportQueryPageListInfoRes> queryPageList(BaseResourceExportQueryPageListReq req);


  void setName(List<? extends BaseResourceDto> baseResourceDtoList);
}

