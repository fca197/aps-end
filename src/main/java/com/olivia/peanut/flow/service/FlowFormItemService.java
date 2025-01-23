package com.olivia.peanut.flow.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.flow.api.entity.flowFormItem.*;
import com.olivia.peanut.flow.model.FlowFormItem;
import com.olivia.sdk.utils.DynamicsPage;

import java.util.List;

/**
 * 工作流表单项表(FlowFormItem)表服务接口
 *
 * @author peanut
 * @since 2024-08-02 23:26:26
 */
public interface FlowFormItemService extends MPJBaseService<FlowFormItem> {

  FlowFormItemQueryListRes queryList(FlowFormItemQueryListReq req);

  DynamicsPage<FlowFormItemExportQueryPageListInfoRes> queryPageList(FlowFormItemExportQueryPageListReq req);


  void setName(List<? extends FlowFormItemDto> flowFormItemDtoList);
}

