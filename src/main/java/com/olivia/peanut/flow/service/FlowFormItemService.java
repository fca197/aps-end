package com.olivia.peanut.flow.service;

import com.olivia.sdk.utils.DynamicsPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.olivia.peanut.flow.model.FlowFormItem;
import java.util.List;
import com.github.yulichang.base.MPJBaseService;

import com.olivia.peanut.flow.api.entity.flowFormItem.*;

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

