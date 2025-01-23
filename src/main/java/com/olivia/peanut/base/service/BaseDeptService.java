package com.olivia.peanut.base.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.base.api.entity.baseDept.*;
import com.olivia.peanut.base.model.BaseDept;
import com.olivia.sdk.utils.DynamicsPage;

import java.util.List;

/**
 * 部门表(BaseDept)表服务接口
 *
 * @author peanut
 * @since 2024-07-31 14:33:31
 */
public interface BaseDeptService extends MPJBaseService<BaseDept> {

  BaseDeptQueryListRes queryList(BaseDeptQueryListReq req);

  DynamicsPage<BaseDeptExportQueryPageListInfoRes> queryPageList(BaseDeptExportQueryPageListReq req);


  void setName(List<? extends BaseDeptDto> baseDeptDtoList);
}

