package com.olivia.peanut.base.service;

import com.olivia.sdk.utils.DynamicsPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.olivia.peanut.base.model.BaseRoleGroupResource;
import java.util.List;
import com.github.yulichang.base.MPJBaseService;

import com.olivia.peanut.base.api.entity.baseRoleGroupResource.*;

/**
 * 角色组资源表(BaseRoleGroupResource)表服务接口
 *
 * @author peanut
 * @since 2024-08-09 15:42:34
 */
public interface BaseRoleGroupResourceService extends MPJBaseService<BaseRoleGroupResource> {

  BaseRoleGroupResourceQueryListRes queryList(BaseRoleGroupResourceQueryListReq req);

  DynamicsPage<BaseRoleGroupResourceExportQueryPageListInfoRes> queryPageList(BaseRoleGroupResourceExportQueryPageListReq req);


  void setName(List<? extends BaseRoleGroupResourceDto> baseRoleGroupResourceDtoList);
}

