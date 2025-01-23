package com.olivia.peanut.base.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.base.api.entity.baseUserRoleGroup.*;
import com.olivia.peanut.base.model.BaseUserRoleGroup;
import com.olivia.sdk.utils.DynamicsPage;

import java.util.List;

/**
 * 用户角色组表(BaseUserRoleGroup)表服务接口
 *
 * @author peanut
 * @since 2024-07-31 14:36:04
 */
public interface BaseUserRoleGroupService extends MPJBaseService<BaseUserRoleGroup> {

  BaseUserRoleGroupQueryListRes queryList(BaseUserRoleGroupQueryListReq req);

  DynamicsPage<BaseUserRoleGroupExportQueryPageListInfoRes> queryPageList(BaseUserRoleGroupExportQueryPageListReq req);


  void setName(List<? extends BaseUserRoleGroupDto> baseUserRoleGroupDtoList);
}

