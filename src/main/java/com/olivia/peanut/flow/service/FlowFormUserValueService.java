package com.olivia.peanut.flow.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.flow.api.entity.flowFormUserValue.*;
import com.olivia.peanut.flow.model.FlowFormUserValue;
import com.olivia.sdk.utils.DynamicsPage;

import java.util.List;

/**
 * 工作流表单用户数据表(FlowFormUserValue)表服务接口
 *
 * @author peanut
 * @since 2024-08-03 18:10:54
 */
public interface FlowFormUserValueService extends MPJBaseService<FlowFormUserValue> {

  FlowFormUserValueQueryListRes queryList(FlowFormUserValueQueryListReq req);

  DynamicsPage<FlowFormUserValueExportQueryPageListInfoRes> queryPageList(FlowFormUserValueExportQueryPageListReq req);


  void setName(List<? extends FlowFormUserValueDto> flowFormUserValueDtoList);
}

