package com.olivia.peanut.base.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.base.api.entity.baseAppResource.*;
import com.olivia.peanut.base.model.BaseAppResource;
import com.olivia.sdk.utils.DynamicsPage;

import java.util.List;

/**
 * 资源(BaseAppResource)表服务接口
 *
 * @author peanut
 * @since 2024-08-06 17:30:28
 */
public interface BaseAppResourceService extends MPJBaseService<BaseAppResource> {

  BaseAppResourceQueryListRes queryList(BaseAppResourceQueryListReq req);

  DynamicsPage<BaseAppResourceExportQueryPageListInfoRes> queryPageList(BaseAppResourceExportQueryPageListReq req);


  void setName(List<? extends BaseAppResourceDto> baseAppResourceDtoList);
}

