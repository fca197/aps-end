package com.olivia.peanut.flow.service;

import com.olivia.sdk.utils.DynamicsPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.olivia.peanut.flow.model.FlowForm;

import java.util.List;

import com.github.yulichang.base.MPJBaseService;

import com.olivia.peanut.flow.api.entity.flowForm.*;

/**
 * 工作流表单表(FlowForm)表服务接口
 *
 * @author peanut
 * @since 2024-08-02 23:26:22
 */
public interface FlowFormService extends MPJBaseService<FlowForm> {

  FlowFormQueryListRes queryList(FlowFormQueryListReq req);

  DynamicsPage<FlowFormExportQueryPageListInfoRes> queryPageList(FlowFormExportQueryPageListReq req);


  void setName(List<? extends FlowFormDto> flowFormDtoList);

  FlowFormInsertRes save(FlowFormInsertReq req);

  FlowFormUpdateByIdRes updateById(FlowFormUpdateByIdReq req);
}

