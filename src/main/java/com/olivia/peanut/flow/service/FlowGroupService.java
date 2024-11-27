package com.olivia.peanut.flow.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.flow.api.entity.flowGroup.*;
import com.olivia.peanut.flow.model.FlowGroup;
import com.olivia.sdk.utils.DynamicsPage;

import java.util.List;

/**
 * 工作流组表(FlowGroup)表服务接口
 *
 * @author peanut
 * @since 2024-08-01 10:43:53
 */
public interface FlowGroupService extends MPJBaseService<FlowGroup> {

  FlowGroupQueryListRes queryList(FlowGroupQueryListReq req);

  DynamicsPage<FlowGroupExportQueryPageListInfoRes> queryPageList(FlowGroupExportQueryPageListReq req);


  void setName(List<? extends FlowGroupDto> flowGroupDtoList);
}

