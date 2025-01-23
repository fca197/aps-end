package com.olivia.peanut.base.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.base.api.entity.baseRoleGroup.*;
import com.olivia.peanut.base.model.BaseRoleGroup;
import com.olivia.sdk.utils.DynamicsPage;

import java.util.List;

/**
 * 角色组表(BaseRoleGroup)表服务接口
 *
 * @author peanut
 * @since 2024-07-31 14:33:36
 */
public interface BaseRoleGroupService extends MPJBaseService<BaseRoleGroup> {

  BaseRoleGroupQueryListRes queryList(BaseRoleGroupQueryListReq req);

  DynamicsPage<BaseRoleGroupExportQueryPageListInfoRes> queryPageList(BaseRoleGroupExportQueryPageListReq req);


  void setName(List<? extends BaseRoleGroupDto> baseRoleGroupDtoList);
}

