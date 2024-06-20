package com.olivia.peanut.aps.service;

import com.olivia.sdk.utils.DynamicsPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.olivia.peanut.aps.model.ApsBomGroup;
import java.util.List;
import com.github.yulichang.base.MPJBaseService;

import com.olivia.peanut.aps.api.entity.apsBomGroup.*;

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
}

