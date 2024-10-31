package com.olivia.peanut.base.service;

import com.olivia.sdk.utils.DynamicsPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.olivia.peanut.base.model.BaseUserRole;

import java.util.List;

import com.github.yulichang.base.MPJBaseService;

import com.olivia.peanut.base.api.entity.baseUserRole.*;

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

