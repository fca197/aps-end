package com.olivia.peanut.flow.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.base.service.BaseTableHeaderService;
import com.olivia.peanut.flow.api.entity.flowFormItem.*;
import com.olivia.peanut.flow.mapper.FlowFormItemMapper;
import com.olivia.peanut.flow.model.FlowFormItem;
import com.olivia.peanut.flow.service.FlowFormItemService;
import com.olivia.sdk.service.SetNameService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 工作流表单项表(FlowFormItem)表服务实现类
 *
 * @author peanut
 * @since 2024-08-02 23:26:26
 */
@Service("flowFormItemService")
@Transactional
public class FlowFormItemServiceImpl extends MPJBaseServiceImpl<FlowFormItemMapper, FlowFormItem> implements FlowFormItemService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  @Resource
  BaseTableHeaderService tableHeaderService;
  @Resource
  SetNameService setNameService;


  public @Override FlowFormItemQueryListRes queryList(FlowFormItemQueryListReq req) {

    MPJLambdaWrapper<FlowFormItem> q = getWrapper(req.getData());
    List<FlowFormItem> list = this.list(q);

    List<FlowFormItemDto> dataList = list.stream().map(t -> $.copy(t, FlowFormItemDto.class)).collect(Collectors.toList());
    ((FlowFormItemService) AopContext.currentProxy()).setName(dataList);
    return new FlowFormItemQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<FlowFormItemExportQueryPageListInfoRes> queryPageList(FlowFormItemExportQueryPageListReq req) {

    DynamicsPage<FlowFormItem> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<FlowFormItem> q = getWrapper(req.getData());
    List<FlowFormItemExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<FlowFormItem> list = this.page(page, q);
      IPage<FlowFormItemExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, FlowFormItemExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), FlowFormItemExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<FlowFormItemExportQueryPageListInfoRes> listInfoRes = $.copyList(records, FlowFormItemExportQueryPageListInfoRes.class);
    ((FlowFormItemService) AopContext.currentProxy()).setName(listInfoRes);

    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装

  public @Override void setName(List<? extends FlowFormItemDto> list) {

    //   setNameService.setName(list, SetNamePojoUtils.FACTORY, SetNamePojoUtils.OP_USER_NAME);

  }


  private MPJLambdaWrapper<FlowFormItem> getWrapper(FlowFormItemDto obj) {
    MPJLambdaWrapper<FlowFormItem> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(Objects.nonNull(obj.getFormId()), FlowFormItem::getFormId, obj.getFormId())
          .eq(StringUtils.isNoneBlank(obj.getFormItemName()), FlowFormItem::getFormItemName, obj.getFormItemName())
          .eq(StringUtils.isNoneBlank(obj.getFormItemFiled()), FlowFormItem::getFormItemFiled, obj.getFormItemFiled())
          .eq(StringUtils.isNoneBlank(obj.getFormItemValue()), FlowFormItem::getFormItemValue, obj.getFormItemValue())
          .eq(StringUtils.isNoneBlank(obj.getFormItemDefaultValue()), FlowFormItem::getFormItemDefaultValue, obj.getFormItemDefaultValue())
          .eq(StringUtils.isNoneBlank(obj.getFormItemValueType()), FlowFormItem::getFormItemValueType, obj.getFormItemValueType())
          .eq(Objects.nonNull(obj.getIsAddFlowValue()), FlowFormItem::getIsAddFlowValue, obj.getIsAddFlowValue())
          .eq(Objects.nonNull(obj.getIsRequired()), FlowFormItem::getIsRequired, obj.getIsRequired())
          .eq(Objects.nonNull(obj.getSortIndex()), FlowFormItem::getSortIndex, obj.getSortIndex())
          .eq(StringUtils.isNoneBlank(obj.getLoseFocusEvent()), FlowFormItem::getLoseFocusEvent, obj.getLoseFocusEvent())

      ;
    }
    q.orderByDesc(FlowFormItem::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<FlowFormItem> page) {

    tableHeaderService.listByBizKey(page, "FlowFormItemService#queryPageList");

  }


}

