package com.olivia.peanut.aps.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.api.entity.apsSchedulingDayConfigVersionDetailMachine.*;
import com.olivia.peanut.aps.con.ApsStr;
import com.olivia.peanut.aps.mapper.ApsSchedulingDayConfigVersionDetailMachineMapper;
import com.olivia.peanut.aps.model.ApsSchedulingDayConfigVersionDetailMachine;
import com.olivia.peanut.aps.service.ApsMachineService;
import com.olivia.peanut.aps.service.ApsOrderService;
import com.olivia.peanut.aps.service.ApsOrderUserService;
import com.olivia.peanut.aps.service.ApsSchedulingDayConfigVersionDetailMachineService;
import com.olivia.peanut.base.service.BaseTableHeaderService;
import com.olivia.peanut.util.SetNamePojoUtils;
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
 * 排程版本详情_机器(ApsSchedulingDayConfigVersionDetailMachine)表服务实现类
 *
 * @author makejava
 * @since 2024-10-27 00:12:55
 */
@Service("apsSchedulingDayConfigVersionDetailMachineService")
@Transactional
public class ApsSchedulingDayConfigVersionDetailMachineServiceImpl extends MPJBaseServiceImpl<ApsSchedulingDayConfigVersionDetailMachineMapper, ApsSchedulingDayConfigVersionDetailMachine> implements ApsSchedulingDayConfigVersionDetailMachineService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  @Resource
  BaseTableHeaderService tableHeaderService;
  @Resource
  SetNameService setNameService;


  public @Override ApsSchedulingDayConfigVersionDetailMachineQueryListRes queryList(ApsSchedulingDayConfigVersionDetailMachineQueryListReq req) {

    MPJLambdaWrapper<ApsSchedulingDayConfigVersionDetailMachine> q = getWrapper(req.getData());
    List<ApsSchedulingDayConfigVersionDetailMachine> list = this.list(q);

    List<ApsSchedulingDayConfigVersionDetailMachineDto> dataList = list.stream().map(t -> $.copy(t, ApsSchedulingDayConfigVersionDetailMachineDto.class)).collect(Collectors.toList());
    ((ApsSchedulingDayConfigVersionDetailMachineService) AopContext.currentProxy()).setName(dataList);
    return new ApsSchedulingDayConfigVersionDetailMachineQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<ApsSchedulingDayConfigVersionDetailMachineExportQueryPageListInfoRes> queryPageList(ApsSchedulingDayConfigVersionDetailMachineExportQueryPageListReq req) {

    DynamicsPage<ApsSchedulingDayConfigVersionDetailMachine> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsSchedulingDayConfigVersionDetailMachine> q = getWrapper(req.getData());
    List<ApsSchedulingDayConfigVersionDetailMachineExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsSchedulingDayConfigVersionDetailMachine> list = this.page(page, q);
      IPage<ApsSchedulingDayConfigVersionDetailMachineExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsSchedulingDayConfigVersionDetailMachineExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsSchedulingDayConfigVersionDetailMachineExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作 

    List<ApsSchedulingDayConfigVersionDetailMachineExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsSchedulingDayConfigVersionDetailMachineExportQueryPageListInfoRes.class);
    ((ApsSchedulingDayConfigVersionDetailMachineService) AopContext.currentProxy()).setName(listInfoRes);

    return DynamicsPage.init(page, listInfoRes);
  }

//  @Resource
//  ApsOrderService apsOrderService;
//  @Resource
//  ApsOrderUserService apsOrderUserService;
  // 以下为私有对象封装

  public @Override void setName(List<? extends ApsSchedulingDayConfigVersionDetailMachineDto> list) {

    setNameService.setName(list,
        SetNamePojoUtils.getSetNamePojo(ApsOrderService.class, ApsStr.ORDER_NO, ApsStr.ORDER_ID, ApsStr.ORDER_NO),
        SetNamePojoUtils.getSetNamePojo(ApsMachineService.class, "machineName", "machineId", "machineName"),
        SetNamePojoUtils.getSetNamePojo(ApsOrderUserService.class, "userName", ApsStr.ORDER_ID, "orderUserName")
    );

  }


  private MPJLambdaWrapper<ApsSchedulingDayConfigVersionDetailMachine> getWrapper(ApsSchedulingDayConfigVersionDetailMachineDto obj) {
    MPJLambdaWrapper<ApsSchedulingDayConfigVersionDetailMachine> q = new MPJLambdaWrapper<>();


    if (Objects.nonNull(obj)) {
      q.eq(Objects.nonNull(obj.getSchedulingDayId()), ApsSchedulingDayConfigVersionDetailMachine::getSchedulingDayId, obj.getSchedulingDayId()).eq(Objects.nonNull(obj.getOrderId()), ApsSchedulingDayConfigVersionDetailMachine::getOrderId, obj.getOrderId()).eq(Objects.nonNull(obj.getMachineId()), ApsSchedulingDayConfigVersionDetailMachine::getMachineId, obj.getMachineId()).eq(Objects.nonNull(obj.getStatusId()), ApsSchedulingDayConfigVersionDetailMachine::getStatusId, obj.getStatusId()).eq(Objects.nonNull(obj.getBeginDateTime()), ApsSchedulingDayConfigVersionDetailMachine::getBeginDateTime, obj.getBeginDateTime()).eq(Objects.nonNull(obj.getEndDateTime()), ApsSchedulingDayConfigVersionDetailMachine::getEndDateTime, obj.getEndDateTime())

      ;
    }
    q.orderByDesc(ApsSchedulingDayConfigVersionDetailMachine::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsSchedulingDayConfigVersionDetailMachine> page) {

    tableHeaderService.listByBizKey(page, "ApsSchedulingDayConfigVersionDetailMachineService#queryPageList");

  }


}

