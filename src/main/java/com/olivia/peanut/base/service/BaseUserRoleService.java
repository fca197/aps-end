package com.olivia.peanut.base.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.base.api.entity.baseUserRole.*;
import com.olivia.peanut.base.model.BaseUserRole;
import com.olivia.sdk.utils.DynamicsPage;

import java.util.List;

/**
 * 用户角色表(BaseUserRole)表服务接口
 *
 * @author peanut
 * @since 2024-07-31 14:36:03
 */
public interface BaseUserRoleService extends MPJBaseService<BaseUserRole> {

  BaseUserRoleQueryListRes queryList(BaseUserRoleQueryListReq req);

  DynamicsPage<BaseUserRoleExportQueryPageListInfoRes> queryPageList(BaseUserRoleExportQueryPageListReq req);


  void setName(List<? extends BaseUserRoleDto> baseUserRoleDtoList);
}

