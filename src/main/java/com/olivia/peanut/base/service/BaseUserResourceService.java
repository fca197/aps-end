package com.olivia.peanut.base.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.base.api.entity.baseUserResource.*;
import com.olivia.peanut.base.model.BaseUserResource;
import com.olivia.sdk.utils.DynamicsPage;

import java.util.List;

/**
 * 用户角色资源表(BaseUserResource)表服务接口
 *
 * @author peanut
 * @since 2024-08-09 16:26:40
 */
public interface BaseUserResourceService extends MPJBaseService<BaseUserResource> {

  BaseUserResourceQueryListRes queryList(BaseUserResourceQueryListReq req);

  DynamicsPage<BaseUserResourceExportQueryPageListInfoRes> queryPageList(BaseUserResourceExportQueryPageListReq req);


  void setName(List<? extends BaseUserResourceDto> baseUserResourceDtoList);
}

