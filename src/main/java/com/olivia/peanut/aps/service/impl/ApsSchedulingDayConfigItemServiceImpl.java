package com.olivia.peanut.aps.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.api.entity.apsSchedulingDayConfigItem.*;
import com.olivia.peanut.aps.mapper.ApsSchedulingDayConfigItemMapper;
import com.olivia.peanut.aps.model.ApsSchedulingDayConfigItem;
import com.olivia.peanut.aps.service.ApsSchedulingDayConfigItemService;
import com.olivia.peanut.base.service.BaseTableHeaderService;
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
 * 排程版本配置表(ApsSchedulingDayConfigItem)表服务实现类
 *
 * @author peanut
 * @since 2024-07-19 19:19:52
 */
@Service("apsSchedulingDayConfigItemService")
@Transactional
public class ApsSchedulingDayConfigItemServiceImpl extends MPJBaseServiceImpl<ApsSchedulingDayConfigItemMapper, ApsSchedulingDayConfigItem> implements
    ApsSchedulingDayConfigItemService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  @Resource
  BaseTableHeaderService tableHeaderService;


  public @Override ApsSchedulingDayConfigItemQueryListRes queryList(ApsSchedulingDayConfigItemQueryListReq req) {

    MPJLambdaWrapper<ApsSchedulingDayConfigItem> q = getWrapper(req.getData());
    List<ApsSchedulingDayConfigItem> list = this.list(q);

    List<ApsSchedulingDayConfigItemDto> dataList = list.stream().map(t -> $.copy(t, ApsSchedulingDayConfigItemDto.class)).collect(Collectors.toList());
    ((ApsSchedulingDayConfigItemService) AopContext.currentProxy()).setName(dataList);
    return new ApsSchedulingDayConfigItemQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<ApsSchedulingDayConfigItemExportQueryPageListInfoRes> queryPageList(ApsSchedulingDayConfigItemExportQueryPageListReq req) {

    DynamicsPage<ApsSchedulingDayConfigItem> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsSchedulingDayConfigItem> q = getWrapper(req.getData());
    List<ApsSchedulingDayConfigItemExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsSchedulingDayConfigItem> list = this.page(page, q);
      IPage<ApsSchedulingDayConfigItemExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsSchedulingDayConfigItemExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsSchedulingDayConfigItemExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<ApsSchedulingDayConfigItemExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsSchedulingDayConfigItemExportQueryPageListInfoRes.class);
    ((ApsSchedulingDayConfigItemService) AopContext.currentProxy()).setName(listInfoRes);

    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装

  public @Override void setName(List<? extends ApsSchedulingDayConfigItemDto> apsSchedulingDayConfigItemDtoList) {

    if (CollUtil.isEmpty(apsSchedulingDayConfigItemDtoList)) {
    }


  }


  private MPJLambdaWrapper<ApsSchedulingDayConfigItem> getWrapper(ApsSchedulingDayConfigItemDto obj) {
    MPJLambdaWrapper<ApsSchedulingDayConfigItem> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(Objects.nonNull(obj.getSchedulingDayId()), ApsSchedulingDayConfigItem::getSchedulingDayId, obj.getSchedulingDayId())
          .eq(Objects.nonNull(obj.getProcessId()), ApsSchedulingDayConfigItem::getProcessId, obj.getProcessId())
          .eq(Objects.nonNull(obj.getRoomId()), ApsSchedulingDayConfigItem::getRoomId, obj.getRoomId())
          .eq(Objects.nonNull(obj.getStatusId()), ApsSchedulingDayConfigItem::getStatusId, obj.getStatusId())
          .eq(StringUtils.isNoneBlank(obj.getConfigBizType()), ApsSchedulingDayConfigItem::getConfigBizType, obj.getConfigBizType())
          .eq(Objects.nonNull(obj.getConfigBizId()), ApsSchedulingDayConfigItem::getConfigBizId, obj.getConfigBizId())
          .eq(StringUtils.isNoneBlank(obj.getConfigBizName()), ApsSchedulingDayConfigItem::getConfigBizName, obj.getConfigBizName())
          .eq(Objects.nonNull(obj.getConfigBizNum()), ApsSchedulingDayConfigItem::getConfigBizNum, obj.getConfigBizNum())
          .eq(Objects.nonNull(obj.getConfigBizTime()), ApsSchedulingDayConfigItem::getConfigBizTime, obj.getConfigBizTime())
          .eq(Objects.nonNull(obj.getIsDefault()), ApsSchedulingDayConfigItem::getIsDefault, obj.getIsDefault())

      ;
    }
    q.orderByDesc(ApsSchedulingDayConfigItem::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsSchedulingDayConfigItem> page) {

    tableHeaderService.listByBizKey(page, "ApsSchedulingDayConfigItemService#queryPageList");

  }


}

