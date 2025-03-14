package com.olivia.peanut.flow.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.base.service.BaseTableHeaderService;
import com.olivia.peanut.flow.api.entity.flowDefinition.*;
import com.olivia.peanut.flow.mapper.FlowDefinitionMapper;
import com.olivia.peanut.flow.model.FlowDefinition;
import com.olivia.peanut.flow.service.FlowDefinitionService;
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
 * 工作定义表(FlowDefinition)表服务实现类
 *
 * @author peanut
 * @since 2024-08-01 10:43:49
 */
@Service("flowDefinitionService")
@Transactional
public class FlowDefinitionServiceImpl extends MPJBaseServiceImpl<FlowDefinitionMapper, FlowDefinition> implements FlowDefinitionService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  @Resource
  BaseTableHeaderService tableHeaderService;
  @Resource
  SetNameService setNameService;


  public @Override FlowDefinitionQueryListRes queryList(FlowDefinitionQueryListReq req) {

    MPJLambdaWrapper<FlowDefinition> q = getWrapper(req.getData());
    List<FlowDefinition> list = this.list(q);

    List<FlowDefinitionDto> dataList = list.stream().map(t -> $.copy(t, FlowDefinitionDto.class)).collect(Collectors.toList());
    ((FlowDefinitionService) AopContext.currentProxy()).setName(dataList);
    return new FlowDefinitionQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<FlowDefinitionExportQueryPageListInfoRes> queryPageList(FlowDefinitionExportQueryPageListReq req) {

    DynamicsPage<FlowDefinition> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<FlowDefinition> q = getWrapper(req.getData());
    List<FlowDefinitionExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<FlowDefinition> list = this.page(page, q);
      IPage<FlowDefinitionExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, FlowDefinitionExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), FlowDefinitionExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<FlowDefinitionExportQueryPageListInfoRes> listInfoRes = $.copyList(records, FlowDefinitionExportQueryPageListInfoRes.class);
    ((FlowDefinitionService) AopContext.currentProxy()).setName(listInfoRes);

    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装

  public @Override void setName(List<? extends FlowDefinitionDto> list) {

    //   setNameService.setName(list, SetNamePojoUtils.FACTORY, SetNamePojoUtils.OP_USER_NAME);

  }


  private MPJLambdaWrapper<FlowDefinition> getWrapper(FlowDefinitionDto obj) {
    MPJLambdaWrapper<FlowDefinition> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(StringUtils.isNoneBlank(obj.getFlowName()), FlowDefinition::getFlowName, obj.getFlowName())
          .eq(Objects.nonNull(obj.getFlowGroupId()), FlowDefinition::getFlowGroupId, obj.getFlowGroupId())

      ;
    }
    q.orderByDesc(FlowDefinition::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<FlowDefinition> page) {

    tableHeaderService.listByBizKey(page, "FlowDefinitionService#queryPageList");

  }


}

