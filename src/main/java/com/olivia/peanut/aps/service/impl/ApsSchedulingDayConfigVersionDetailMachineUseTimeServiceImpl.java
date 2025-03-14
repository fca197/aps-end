package com.olivia.peanut.aps.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.api.entity.apsSchedulingDayConfigVersionDetailMachineUseTime.*;
import com.olivia.peanut.aps.mapper.ApsSchedulingDayConfigVersionDetailMachineUseTimeMapper;
import com.olivia.peanut.aps.model.ApsSchedulingDayConfigVersionDetailMachineUseTime;
import com.olivia.peanut.aps.service.ApsSchedulingDayConfigVersionDetailMachineUseTimeService;
import com.olivia.peanut.base.service.BaseTableHeaderService;
import com.olivia.sdk.service.SetNameService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import jakarta.annotation.Resource;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 排程结果机器使用率(ApsSchedulingDayConfigVersionDetailMachineUseTime)表服务实现类
 *
 * @author makejava
 * @since 2024-11-11 15:21:50
 */
@Service("apsSchedulingDayConfigVersionDetailMachineUseTimeService")
@Transactional
public class ApsSchedulingDayConfigVersionDetailMachineUseTimeServiceImpl extends MPJBaseServiceImpl<ApsSchedulingDayConfigVersionDetailMachineUseTimeMapper, ApsSchedulingDayConfigVersionDetailMachineUseTime> implements ApsSchedulingDayConfigVersionDetailMachineUseTimeService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  @Resource
  BaseTableHeaderService tableHeaderService;
  @Resource
  SetNameService setNameService;


  public @Override ApsSchedulingDayConfigVersionDetailMachineUseTimeQueryListRes queryList(ApsSchedulingDayConfigVersionDetailMachineUseTimeQueryListReq req) {

    MPJLambdaWrapper<ApsSchedulingDayConfigVersionDetailMachineUseTime> q = getWrapper(req.getData());
    List<ApsSchedulingDayConfigVersionDetailMachineUseTime> list = this.list(q);

    List<ApsSchedulingDayConfigVersionDetailMachineUseTimeDto> dataList = list.stream().map(t -> $.copy(t, ApsSchedulingDayConfigVersionDetailMachineUseTimeDto.class)).collect(Collectors.toList());
    ((ApsSchedulingDayConfigVersionDetailMachineUseTimeService) AopContext.currentProxy()).setName(dataList);
    return new ApsSchedulingDayConfigVersionDetailMachineUseTimeQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<ApsSchedulingDayConfigVersionDetailMachineUseTimeExportQueryPageListInfoRes> queryPageList(ApsSchedulingDayConfigVersionDetailMachineUseTimeExportQueryPageListReq req) {

    DynamicsPage<ApsSchedulingDayConfigVersionDetailMachineUseTime> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsSchedulingDayConfigVersionDetailMachineUseTime> q = getWrapper(req.getData());
    List<ApsSchedulingDayConfigVersionDetailMachineUseTimeExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsSchedulingDayConfigVersionDetailMachineUseTime> list = this.page(page, q);
      IPage<ApsSchedulingDayConfigVersionDetailMachineUseTimeExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsSchedulingDayConfigVersionDetailMachineUseTimeExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsSchedulingDayConfigVersionDetailMachineUseTimeExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作 

    List<ApsSchedulingDayConfigVersionDetailMachineUseTimeExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsSchedulingDayConfigVersionDetailMachineUseTimeExportQueryPageListInfoRes.class);
    ((ApsSchedulingDayConfigVersionDetailMachineUseTimeService) AopContext.currentProxy()).setName(listInfoRes);

    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装

  public @Override void setName(List<? extends ApsSchedulingDayConfigVersionDetailMachineUseTimeDto> list) {

    //   setNameService.setName(list, SetNamePojoUtils.FACTORY, SetNamePojoUtils.OP_USER_NAME);

  }


  private MPJLambdaWrapper<ApsSchedulingDayConfigVersionDetailMachineUseTime> getWrapper(ApsSchedulingDayConfigVersionDetailMachineUseTimeDto obj) {
    MPJLambdaWrapper<ApsSchedulingDayConfigVersionDetailMachineUseTime> q = new MPJLambdaWrapper<>();


    if (Objects.nonNull(obj)) {
      q
          .eq(Objects.nonNull(obj.getSchedulingDayId()), ApsSchedulingDayConfigVersionDetailMachineUseTime::getSchedulingDayId, obj.getSchedulingDayId())
          .eq(Objects.nonNull(obj.getMachineId()), ApsSchedulingDayConfigVersionDetailMachineUseTime::getMachineId, obj.getMachineId())
          .eq(Objects.nonNull(obj.getUseTime()), ApsSchedulingDayConfigVersionDetailMachineUseTime::getUseTime, obj.getUseTime())
          .eq(Objects.nonNull(obj.getUseUsageRate()), ApsSchedulingDayConfigVersionDetailMachineUseTime::getUseUsageRate, obj.getUseUsageRate())
          .eq(Objects.nonNull(obj.getMakeProduceCount()), ApsSchedulingDayConfigVersionDetailMachineUseTime::getMakeProduceCount, obj.getMakeProduceCount())

      ;
    }
    q.orderByDesc(ApsSchedulingDayConfigVersionDetailMachineUseTime::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsSchedulingDayConfigVersionDetailMachineUseTime> page) {

    tableHeaderService.listByBizKey(page, "ApsSchedulingDayConfigVersionDetailMachineUseTimeService#queryPageList");

  }


}

