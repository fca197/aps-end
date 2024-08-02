package com.olivia.peanut.flow.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.flow.api.entity.flowForm.*;
import com.olivia.peanut.flow.api.entity.flowFormItem.FlowFormItemDto;
import com.olivia.peanut.flow.mapper.FlowFormMapper;
import com.olivia.peanut.flow.model.FlowForm;
import com.olivia.peanut.flow.model.FlowFormItem;
import com.olivia.peanut.flow.service.FlowFormItemService;
import com.olivia.peanut.flow.service.FlowFormService;
import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.peanut.portal.service.BaseTableHeaderService;
import com.olivia.sdk.service.SetNameService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import jakarta.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 工作流表单表(FlowForm)表服务实现类
 *
 * @author peanut
 * @since 2024-08-02 23:26:22
 */
@Service("flowFormService")
@Transactional
public class FlowFormServiceImpl extends MPJBaseServiceImpl<FlowFormMapper, FlowForm> implements FlowFormService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  @Resource
  BaseTableHeaderService tableHeaderService;
  @Resource
  SetNameService setNameService;

  @Resource
  FlowFormItemService flowFormItemService;


  @Override
  @Transactional
  public FlowFormInsertRes save(FlowFormInsertReq req) {
    FlowForm flowForm = $.copy(req, FlowForm.class);
    long formId = IdWorker.getId();
    flowForm.setId(formId);
    AtomicInteger count = new AtomicInteger(0);
    List<FlowFormItem> list = req.getFlowFormItemDtoList().stream().map(t -> {
      t.setFormId(formId);
      t.setSortIndex(count.getAndIncrement());
      t.setId(IdWorker.getId());
      return $.copy(t, FlowFormItem.class);
    }).toList();
    this.save(flowForm);
    flowFormItemService.saveBatch(list);
    return new FlowFormInsertRes().setId(formId).setCount(1);
  }

  @Override
  @Transactional
  public FlowFormUpdateByIdRes updateById(FlowFormUpdateByIdReq req) {
    FlowForm flowForm = $.copy(req, FlowForm.class);
    AtomicInteger count = new AtomicInteger(0);
    List<FlowFormItem> list = req.getFlowFormItemDtoList().stream().map(t -> {
      t.setSortIndex(count.getAndIncrement());
      t.setFormId(flowForm.getId());
      t.setId(IdWorker.getId());
      return $.copy(t, FlowFormItem.class);
    }).toList();
    this.updateById(flowForm);
    this.flowFormItemService.remove(new MPJLambdaWrapper<FlowFormItem>().eq(FlowFormItem::getFormId, flowForm.getId()));
    flowFormItemService.saveBatch(list);
    return new FlowFormUpdateByIdRes();
  }

  public @Override FlowFormQueryListRes queryList(FlowFormQueryListReq req) {

    MPJLambdaWrapper<FlowForm> q = getWrapper(req.getData());
    List<FlowForm> list = this.list(q);

    List<FlowFormDto> dataList = list.stream().map(t -> $.copy(t, FlowFormDto.class)).collect(Collectors.toList());
    ((FlowFormService) AopContext.currentProxy()).setName(dataList);
    return new FlowFormQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<FlowFormExportQueryPageListInfoRes> queryPageList(FlowFormExportQueryPageListReq req) {

    DynamicsPage<FlowForm> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<FlowForm> q = getWrapper(req.getData());
    List<FlowFormExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<FlowForm> list = this.page(page, q);
      IPage<FlowFormExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, FlowFormExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), FlowFormExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<FlowFormExportQueryPageListInfoRes> listInfoRes = $.copyList(records, FlowFormExportQueryPageListInfoRes.class);
    ((FlowFormService) AopContext.currentProxy()).setName(listInfoRes);

    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装

  public @Override void setName(List<? extends FlowFormDto> list) {

    //   setNameService.setName(list, SetNamePojoUtils.FACTORY, SetNamePojoUtils.OP_USER_NAME);
    if (CollUtil.isEmpty(list)) {
      return;
    }

    Map<Long, List<FlowFormItemDto>> listMap = this.flowFormItemService.list(
            new MPJLambdaWrapper<FlowFormItem>().in(FlowFormItem::getFormId, list.stream().map(BaseEntityDto::getId).collect(Collectors.toSet()))).stream()
        .collect(Collectors.groupingBy(FlowFormItem::getFormId, Collectors.collectingAndThen(Collectors.toList(), lt -> $.copyList(lt, FlowFormItemDto.class))));
    list.forEach(t -> t.setFlowFormItemDtoList(listMap.get(t.getId())));
  }


  private MPJLambdaWrapper<FlowForm> getWrapper(FlowFormDto obj) {
    MPJLambdaWrapper<FlowForm> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q.eq(StringUtils.isNoneBlank(obj.getFormName()), FlowForm::getFormName, obj.getFormName())
          .eq(StringUtils.isNoneBlank(obj.getFormCode()), FlowForm::getFormCode, obj.getFormCode())

      ;
    }
    q.orderByDesc(FlowForm::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<FlowForm> page) {

    tableHeaderService.listByBizKey(page, "FlowFormService#queryPageList");

  }


}

