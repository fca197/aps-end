package com.olivia.peanut.base.service;

import com.olivia.sdk.utils.DynamicsPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.olivia.peanut.base.model.BaseRoleGroup;
import java.util.List;
import com.github.yulichang.base.MPJBaseService;

import com.olivia.peanut.base.api.entity.baseRoleGroup.*;

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

