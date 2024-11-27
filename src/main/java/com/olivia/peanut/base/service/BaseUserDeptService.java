package com.olivia.peanut.base.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.base.api.entity.baseUserDept.*;
import com.olivia.peanut.base.model.BaseUserDept;
import com.olivia.sdk.utils.DynamicsPage;

import java.util.List;

/**
 * 用户部门表(BaseUserDept)表服务接口
 *
 * @author peanut
 * @since 2024-07-31 14:36:01
 */
public interface BaseUserDeptService extends MPJBaseService<BaseUserDept> {

  BaseUserDeptQueryListRes queryList(BaseUserDeptQueryListReq req);

  DynamicsPage<BaseUserDeptExportQueryPageListInfoRes> queryPageList(BaseUserDeptExportQueryPageListReq req);


  void setName(List<? extends BaseUserDeptDto> baseUserDeptDtoList);
}

