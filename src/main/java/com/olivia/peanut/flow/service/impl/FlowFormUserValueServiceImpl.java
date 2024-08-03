package com.olivia.peanut.flow.service.impl;

import org.springframework.aop.framework.AopContext;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import jakarta.annotation.Resource;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.olivia.peanut.flow.mapper.FlowFormUserValueMapper;
import com.olivia.peanut.flow.model.FlowFormUserValue;
import com.olivia.peanut.flow.service.FlowFormUserValueService;
import cn.hutool.core.collection.CollUtil;
//import com.olivia.peanut.flow.service.BaseTableHeaderService;
import com.olivia.peanut.portal.service.BaseTableHeaderService;
import com.olivia.peanut.flow.api.entity.flowFormUserValue.*;
import com.olivia.peanut.util.SetNamePojoUtils;
import com.olivia.sdk.service.SetNameService;

/**
 * 工作流表单用户数据表(FlowFormUserValue)表服务实现类
 *
 * @author peanut
 * @since 2024-08-03 18:10:54
 */
@Service("flowFormUserValueService")
@Transactional
public class FlowFormUserValueServiceImpl extends MPJBaseServiceImpl<FlowFormUserValueMapper, FlowFormUserValue> implements FlowFormUserValueService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  @Resource
  BaseTableHeaderService tableHeaderService;
  @Resource
  SetNameService setNameService;


  public @Override FlowFormUserValueQueryListRes queryList(FlowFormUserValueQueryListReq req) {

    MPJLambdaWrapper<FlowFormUserValue> q = getWrapper(req.getData());
    List<FlowFormUserValue> list = this.list(q);

    List<FlowFormUserValueDto> dataList = list.stream().map(t -> $.copy(t, FlowFormUserValueDto.class)).collect(Collectors.toList());
    ((FlowFormUserValueService) AopContext.currentProxy()).setName(dataList);
    return new FlowFormUserValueQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<FlowFormUserValueExportQueryPageListInfoRes> queryPageList(FlowFormUserValueExportQueryPageListReq req) {

    DynamicsPage<FlowFormUserValue> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<FlowFormUserValue> q = getWrapper(req.getData());
    List<FlowFormUserValueExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<FlowFormUserValue> list = this.page(page, q);
      IPage<FlowFormUserValueExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, FlowFormUserValueExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), FlowFormUserValueExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<FlowFormUserValueExportQueryPageListInfoRes> listInfoRes = $.copyList(records, FlowFormUserValueExportQueryPageListInfoRes.class);
    ((FlowFormUserValueService) AopContext.currentProxy()).setName(listInfoRes);

    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装

  public @Override void setName(List<? extends FlowFormUserValueDto> list) {

    //   setNameService.setName(list, SetNamePojoUtils.FACTORY, SetNamePojoUtils.OP_USER_NAME);

  }


  private MPJLambdaWrapper<FlowFormUserValue> getWrapper(FlowFormUserValueDto obj) {
    MPJLambdaWrapper<FlowFormUserValue> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(StringUtils.isNoneBlank(obj.getProcessInstanceId()), FlowFormUserValue::getProcessInstanceId, obj.getProcessInstanceId())
          .eq(StringUtils.isNoneBlank(obj.getBusinessKey()), FlowFormUserValue::getBusinessKey, obj.getBusinessKey())
          .eq(Objects.nonNull(obj.getFormId()), FlowFormUserValue::getFormId, obj.getFormId())
          .eq(StringUtils.isNoneBlank(obj.getFormItemDefaultValue()), FlowFormUserValue::getFormItemDefaultValue, obj.getFormItemDefaultValue())
          .eq(StringUtils.isNoneBlank(obj.getFormItemFiled()), FlowFormUserValue::getFormItemFiled, obj.getFormItemFiled())
          .eq(StringUtils.isNoneBlank(obj.getFormItemName()), FlowFormUserValue::getFormItemName, obj.getFormItemName())
          .eq(StringUtils.isNoneBlank(obj.getFormItemValue()), FlowFormUserValue::getFormItemValue, obj.getFormItemValue())
          .eq(StringUtils.isNoneBlank(obj.getFormItemValueType()), FlowFormUserValue::getFormItemValueType, obj.getFormItemValueType())
          .eq(Objects.nonNull(obj.getIsAddFlowValue()), FlowFormUserValue::getIsAddFlowValue, obj.getIsAddFlowValue())
          .eq(Objects.nonNull(obj.getIsRequired()), FlowFormUserValue::getIsRequired, obj.getIsRequired())
          .eq(StringUtils.isNoneBlank(obj.getLoseFocusEvent()), FlowFormUserValue::getLoseFocusEvent, obj.getLoseFocusEvent())
          .eq(Objects.nonNull(obj.getSortIndex()), FlowFormUserValue::getSortIndex, obj.getSortIndex())
          .eq(StringUtils.isNoneBlank(obj.getUserValue()), FlowFormUserValue::getUserValue, obj.getUserValue())

      ;
    }
    q.orderByDesc(FlowFormUserValue::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<FlowFormUserValue> page) {

    tableHeaderService.listByBizKey(page, "FlowFormUserValueService#queryPageList");

  }


}

