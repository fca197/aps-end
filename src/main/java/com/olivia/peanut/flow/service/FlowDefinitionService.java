package com.olivia.peanut.flow.service;

import com.olivia.sdk.utils.DynamicsPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.olivia.peanut.flow.model.FlowDefinition;
import java.util.List;
import com.github.yulichang.base.MPJBaseService;

import com.olivia.peanut.flow.api.entity.flowDefinition.*;

/**
 * 工作定义表(FlowDefinition)表服务接口
 *
 * @author peanut
 * @since 2024-08-01 10:43:49
 */
public interface FlowDefinitionService extends MPJBaseService<FlowDefinition> {

  FlowDefinitionQueryListRes queryList(FlowDefinitionQueryListReq req);

  DynamicsPage<FlowDefinitionExportQueryPageListInfoRes> queryPageList(FlowDefinitionExportQueryPageListReq req);


  void setName(List<? extends FlowDefinitionDto> flowDefinitionDtoList);
}

