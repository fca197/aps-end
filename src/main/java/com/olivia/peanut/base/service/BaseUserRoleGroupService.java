package com.olivia.peanut.base.service;

import com.olivia.sdk.utils.DynamicsPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.olivia.peanut.base.model.BaseUserRoleGroup;
import java.util.List;
import com.github.yulichang.base.MPJBaseService;

import com.olivia.peanut.base.api.entity.baseUserRoleGroup.*;

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

