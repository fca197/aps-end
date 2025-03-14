package com.olivia.peanut.flow.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.flow.api.entity.flowDefinition.FlowDefinitionDto;
import com.olivia.peanut.flow.api.entity.flowGroup.*;
import com.olivia.peanut.flow.mapper.FlowGroupMapper;
import com.olivia.peanut.flow.model.FlowDefinition;
import com.olivia.peanut.flow.model.FlowGroup;
import com.olivia.peanut.flow.service.FlowDefinitionService;
import com.olivia.peanut.flow.service.FlowGroupService;
import com.olivia.peanut.base.service.BaseTableHeaderService;
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
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 工作流组表(FlowGroup)表服务实现类
 *
 * @author peanut
 * @since 2024-08-01 10:43:53
 */
@Service("flowGroupService")
@Transactional
public class FlowGroupServiceImpl extends MPJBaseServiceImpl<FlowGroupMapper, FlowGroup> implements FlowGroupService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  @Resource
  BaseTableHeaderService tableHeaderService;
  @Resource
  SetNameService setNameService;
  @Resource
  FlowDefinitionService flowDefinitionService;

  public @Override FlowGroupQueryListRes queryList(FlowGroupQueryListReq req) {

    MPJLambdaWrapper<FlowGroup> q = getWrapper(req.getData());
    List<FlowGroup> list = this.list(q);

    List<FlowGroupDto> dataList = list.stream().map(t -> $.copy(t, FlowGroupDto.class)).collect(Collectors.toList());
    ((FlowGroupService) AopContext.currentProxy()).setName(dataList);
    return new FlowGroupQueryListRes().setDataList(dataList);
  }

  public @Override DynamicsPage<FlowGroupExportQueryPageListInfoRes> queryPageList(FlowGroupExportQueryPageListReq req) {

    DynamicsPage<FlowGroup> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<FlowGroup> q = getWrapper(req.getData());
    List<FlowGroupExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<FlowGroup> list = this.page(page, q);
      IPage<FlowGroupExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, FlowGroupExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), FlowGroupExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<FlowGroupExportQueryPageListInfoRes> listInfoRes = $.copyList(records, FlowGroupExportQueryPageListInfoRes.class);
    ((FlowGroupService) AopContext.currentProxy()).setName(listInfoRes);

    return DynamicsPage.init(page, listInfoRes);
  }
  // 以下为私有对象封装

  public @Override void setName(List<? extends FlowGroupDto> list) {

    //   setNameService.setName(list, SetNamePojoUtils.FACTORY, SetNamePojoUtils.OP_USER_NAME);
    if (CollUtil.isEmpty(list)) {
      return;
    }
    Set<Long> idSet = list.stream().map(FlowGroupDto::getId).collect(Collectors.toSet());
    Map<Long, List<FlowDefinition>> flowMap = flowDefinitionService.list(new LambdaQueryWrapper<FlowDefinition>().in(FlowDefinition::getFlowGroupId, idSet)).stream()
        .collect(Collectors.groupingBy(FlowDefinition::getFlowGroupId));
    list.forEach(t -> t.setFlowList($.copyList(flowMap.get(t.getId()), FlowDefinitionDto.class)));
  }


  private MPJLambdaWrapper<FlowGroup> getWrapper(FlowGroupDto obj) {
    MPJLambdaWrapper<FlowGroup> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(StringUtils.isNoneBlank(obj.getFlowGroupCode()), FlowGroup::getFlowGroupCode, obj.getFlowGroupCode())
          .eq(StringUtils.isNoneBlank(obj.getFlowGroupName()), FlowGroup::getFlowGroupName, obj.getFlowGroupName())

      ;
    }
    q.orderByDesc(FlowGroup::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<FlowGroup> page) {

    tableHeaderService.listByBizKey(page, "FlowGroupService#queryPageList");

  }


}

