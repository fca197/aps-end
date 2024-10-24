package com.olivia.peanut.aps.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.aps.api.entity.apsBomGroup.*;
import com.olivia.peanut.aps.model.ApsBomGroup;
import com.olivia.sdk.utils.DynamicsPage;

import java.util.List;

/**
 * 零件组配置(ApsBomGroup)表服务接口
 *
 * @author peanut
 * @since 2024-06-19 17:41:23
 */
public interface ApsBomGroupService extends MPJBaseService<ApsBomGroup> {

  ApsBomGroupQueryListRes queryList(ApsBomGroupQueryListReq req);

  DynamicsPage<ApsBomGroupExportQueryPageListInfoRes> queryPageList(ApsBomGroupExportQueryPageListReq req);


  void setName(List<? extends ApsBomGroupDto> apsBomGroupDtoList);

  ApsBomGroupUpdateByIdRes updateById(ApsBomGroupUpdateByIdReq req);
}

