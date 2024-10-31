package com.olivia.peanut.base.service;

import com.olivia.sdk.utils.DynamicsPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.olivia.peanut.base.model.BaseUserDept;

import java.util.List;

import com.github.yulichang.base.MPJBaseService;

import com.olivia.peanut.base.api.entity.baseUserDept.*;

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

