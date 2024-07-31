package com.olivia.peanut.base.service;

import com.olivia.sdk.utils.DynamicsPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.olivia.peanut.base.model.BaseRoleResource;
import java.util.List;
import com.github.yulichang.base.MPJBaseService;

import com.olivia.peanut.base.api.entity.baseRoleResource.*;

/**
 * 角色资源表(BaseRoleResource)表服务接口
 *
 * @author peanut
 * @since 2024-07-31 14:35:10
 */
public interface BaseRoleResourceService extends MPJBaseService<BaseRoleResource> {

  BaseRoleResourceQueryListRes queryList(BaseRoleResourceQueryListReq req);

  DynamicsPage<BaseRoleResourceExportQueryPageListInfoRes> queryPageList(BaseRoleResourceExportQueryPageListReq req);


  void setName(List<? extends BaseRoleResourceDto> baseRoleResourceDtoList);
}

