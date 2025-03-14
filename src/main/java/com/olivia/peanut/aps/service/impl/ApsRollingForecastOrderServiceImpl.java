package com.olivia.peanut.aps.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.api.entity.apsProcessPath.ApsProcessPathDto;
import com.olivia.peanut.aps.api.entity.apsRollingForecastOrder.*;
import com.olivia.peanut.aps.mapper.ApsRollingForecastOrderMapper;
import com.olivia.peanut.aps.model.ApsOrder;
import com.olivia.peanut.aps.model.ApsRollingForecastOrder;
import com.olivia.peanut.aps.model.ApsRollingForecastOrderItem;
import com.olivia.peanut.aps.service.*;
import com.olivia.peanut.aps.service.pojo.FactoryCapacityDay;
import com.olivia.peanut.aps.service.pojo.FactoryConfigReq;
import com.olivia.peanut.aps.service.pojo.FactoryConfigRes;
import com.olivia.peanut.aps.utils.model.ApsProcessPathInfo;
import com.olivia.peanut.aps.utils.model.ApsProcessPathVo;
import com.olivia.peanut.aps.utils.process.ProcessUtils;
import com.olivia.peanut.base.service.BaseTableHeaderService;
import com.olivia.peanut.util.SetNamePojoUtils;
import com.olivia.sdk.service.SetNameService;
import com.olivia.sdk.service.pojo.NameConfig;
import com.olivia.sdk.service.pojo.SetNamePojo;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.BaseEntity;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.RunUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static java.lang.Boolean.TRUE;

/**
 * 滚动预测(ApsRollingForecastOrder)表服务实现类
 *
 * @author peanut
 * @since 2024-07-14 20:22:28
 */
@Slf4j
@Service("apsRollingForecastOrderService")
@Transactional
public class ApsRollingForecastOrderServiceImpl extends MPJBaseServiceImpl<ApsRollingForecastOrderMapper, ApsRollingForecastOrder> implements ApsRollingForecastOrderService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  @Resource
  BaseTableHeaderService tableHeaderService;
  @Resource
  ApsOrderService apsOrderService;
  @Resource
  ApsFactoryService apsFactoryService;
  @Resource
  ApsRollingForecastFactoryCapacityService apsRollingForecastFactoryCapacityService;
  @Resource
  ApsRollingForecastOrderItemService apsRollingForecastOrderItemService;
  @Resource
  SetNameService setNameService;

  public @Override ApsRollingForecastOrderQueryListRes queryList(ApsRollingForecastOrderQueryListReq req) {

    MPJLambdaWrapper<ApsRollingForecastOrder> q = getWrapper(req.getData());
    List<ApsRollingForecastOrder> list = this.list(q);

    List<ApsRollingForecastOrderDto> dataList = list.stream().map(t -> $.copy(t, ApsRollingForecastOrderDto.class)).collect(Collectors.toList());
    ((ApsRollingForecastOrderService) AopContext.currentProxy()).setName(dataList);
    return new ApsRollingForecastOrderQueryListRes().setDataList(dataList);
  }

  public @Override DynamicsPage<ApsRollingForecastOrderExportQueryPageListInfoRes> queryPageList(ApsRollingForecastOrderExportQueryPageListReq req) {

    DynamicsPage<ApsRollingForecastOrder> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsRollingForecastOrder> q = getWrapper(req.getData());
    List<ApsRollingForecastOrderExportQueryPageListInfoRes> records;
    if (TRUE.equals(req.getQueryPage())) {
      IPage<ApsRollingForecastOrder> list = this.page(page, q);
      IPage<ApsRollingForecastOrderExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsRollingForecastOrderExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsRollingForecastOrderExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<ApsRollingForecastOrderExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsRollingForecastOrderExportQueryPageListInfoRes.class);
    ((ApsRollingForecastOrderService) AopContext.currentProxy()).setName(listInfoRes);

    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装

  @Override
  @Transactional
  public ApsRollingForecastOrderInsertRes save(ApsRollingForecastOrderInsertReq req) {
    FactoryConfigRes factoryConfig = apsFactoryService.getFactoryConfig(//
        new FactoryConfigReq().setFactoryId(req.getFactoryId()).setGetPath(TRUE).setGetPathDefault(TRUE)//
            .setGetShift(TRUE).setGetWeek(TRUE).setWeekBeginDate(req.getBeginTime()).setWeekEndDate(req.getEndTime())//
    );
    ApsProcessPathDto defaultApsProcessPathDto = factoryConfig.getDefaultApsProcessPathDto();
    ApsProcessPathVo apsProcessPathVo = $.copy(defaultApsProcessPathDto, ApsProcessPathVo.class);
    List<Long> allStatusIdList = ProcessUtils.getStatusBetween(apsProcessPathVo, req.getBeginStatusId(), req.getEndStatusId());
    log.info("factoryId: {} allStatusIdList: {}", req.getFactoryId(), allStatusIdList);
    if (CollUtil.isEmpty(allStatusIdList)) {
      return new ApsRollingForecastOrderInsertRes().setCount(-1);
    }
    List<ApsOrder> orderList = this.apsOrderService.list(new LambdaQueryWrapper<ApsOrder>().select(BaseEntity::getId, ApsOrder::getUrgencyLevel, ApsOrder::getOrderStatus)//
        .eq(ApsOrder::getFactoryId, req.getFactoryId()).in(ApsOrder::getOrderStatus, allStatusIdList));
    if (CollUtil.isEmpty(orderList)) {
      log.info("factoryId: {} orderList: is null", req.getFactoryId());
      return new ApsRollingForecastOrderInsertRes().setCount(-2);
    }

    List<FactoryCapacityDay> capacityDayList = apsRollingForecastFactoryCapacityService.list(req.getFactoryId(), req.getBeginTime(), req.getEndTime());

    if (CollUtil.isEmpty(capacityDayList)) {
      log.info("factoryId: {} capacityDayList: is null", req.getFactoryId());
      return new ApsRollingForecastOrderInsertRes().setCount(-3);
    }
//    Map<Long, String> statusIdNameMap = apsStatusService.list().stream().collect(Collectors.toMap(BaseEntity::getId, ApsStatus::getStatusName));
    Map<Long, List<ApsOrder>> orderMap = orderList.stream()
        .collect(Collectors.groupingBy(ApsOrder::getOrderStatus, Collectors.collectingAndThen(Collectors.<ApsOrder>toList(), list -> {
          list.sort(Comparator.comparing(ApsOrder::getUrgencyLevel).reversed());
          return list;
        })));

    Long forecastId = IdWorker.getId();
    List<ApsRollingForecastOrderItem> insertList = Collections.synchronizedList(new ArrayList<>());

    List<Runnable> runnableList = new ArrayList<>();

    ProcessUtils.getPathBetween(apsProcessPathVo, req.getBeginStatusId(), req.getEndStatusId());

    allStatusIdList.forEach(statusId -> {
      List<ApsOrder> apsOrderList = orderMap.get(statusId);
      if (CollUtil.isEmpty(apsOrderList)) {
        return;
      }
      List<ApsOrder> apsOrdersTmp = new ArrayList<>(apsOrderList);
      capacityDayList.forEach(t -> {
        if (CollUtil.isEmpty(apsOrdersTmp)) {
          return;
        }
        for (int i = 0; i < t.getCapacity() && CollUtil.isNotEmpty(apsOrdersTmp); i++) {
          ApsOrder currApsOrder = apsOrdersTmp.remove(0);
          insertList.add(new ApsRollingForecastOrderItem().setFactoryId(req.getFactoryId())//
              .setForecastId(forecastId).setGoodsStatusId(statusId).setOrderId(currApsOrder.getId()).setStatusBeginDate(t.getLocalDate()));
          runnableList.add(() -> {
            ApsProcessPathInfo schedulePathDate = ProcessUtils.schedulePathDate(apsProcessPathVo, factoryConfig.getWeekList(), factoryConfig.getDayWorkLastSecond(),
                factoryConfig.getDayWorkSecond(), statusId, Map.of(), t.getLocalDate());
            if (Objects.isNull(schedulePathDate) || CollUtil.isEmpty(schedulePathDate.getDataList())) {
              return;
            }
            schedulePathDate.getDataList().forEach(info -> {
              ApsRollingForecastOrderItem item = new ApsRollingForecastOrderItem().setFactoryId(req.getFactoryId())//
                  .setForecastId(forecastId).setGoodsStatusId(info.getStatusId()).setOrderId(currApsOrder.getId()).setStatusBeginDate(info.getBeginLocalDate());
              insertList.add(item);
            });
          });
        }
      });
    });
    RunUtils.run("status forecast " + forecastId, runnableList);
    ApsRollingForecastOrder forecastOrder = $.copy(req, ApsRollingForecastOrder.class);
    forecastOrder.setId(forecastId);
    this.save(forecastOrder);
    this.apsRollingForecastOrderItemService.saveBatch(insertList);
    return new ApsRollingForecastOrderInsertRes().setId(forecastId).setCount(insertList.size());
  }

  //  @SetUserName
  public @Override void setName(List<? extends ApsRollingForecastOrderDto> apsRollingForecastOrderDtoList) {

    SetNamePojo statusNamePojo = new SetNamePojo()//
        .setNameFieldName("statusName").setServiceName(ApsStatusService.class) //
        .setNameConfigList(List.of(new NameConfig().setIdField("beginStatusId").setNameField("beginStatusName"),//
            new NameConfig().setIdField("endStatusId").setNameField("endStatusName")));
    setNameService.setName(apsRollingForecastOrderDtoList, SetNamePojoUtils.FACTORY, statusNamePojo, SetNamePojoUtils.OP_USER_NAME);
  }


  private MPJLambdaWrapper<ApsRollingForecastOrder> getWrapper(ApsRollingForecastOrderDto obj) {
    MPJLambdaWrapper<ApsRollingForecastOrder> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q.eq(StringUtils.isNoneBlank(obj.getRollCode()), ApsRollingForecastOrder::getRollCode, obj.getRollCode())
          .eq(StringUtils.isNoneBlank(obj.getRollName()), ApsRollingForecastOrder::getRollName, obj.getRollName())
          .eq(Objects.nonNull(obj.getBeginTime()), ApsRollingForecastOrder::getBeginTime, obj.getBeginTime())
          .eq(Objects.nonNull(obj.getEndTime()), ApsRollingForecastOrder::getEndTime, obj.getEndTime())

      ;
    }
    q.orderByDesc(ApsRollingForecastOrder::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsRollingForecastOrder> page) {
    tableHeaderService.listByBizKey(page, "ApsRollingForecastOrderService#queryPageList");
  }


}

