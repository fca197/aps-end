package com.olivia.peanut.base.service;

import com.olivia.sdk.utils.DynamicsPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.olivia.peanut.base.model.BaseRole;

import java.util.List;

import com.github.yulichang.base.MPJBaseService;

import com.olivia.peanut.base.api.entity.baseRole.*;

/**
 * 角色表(BaseRole)表服务接口
 *
 * @author peanut
 * @since 2024-07-31 14:33:35
 */
public interface BaseRoleService extends MPJBaseService<BaseRole> {

  BaseRoleQueryListRes queryList(BaseRoleQueryListReq req);

  DynamicsPage<BaseRoleExportQueryPageListInfoRes> queryPageList(BaseRoleExportQueryPageListReq req);


  void setName(List<? extends BaseRoleDto> baseRoleDtoList);
}

