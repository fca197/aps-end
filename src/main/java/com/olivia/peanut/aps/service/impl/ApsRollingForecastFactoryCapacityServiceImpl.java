package com.olivia.peanut.aps.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ReflectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.olivia.peanut.aps.api.entity.apsRollingForecastFactoryCapacity.*;
import com.olivia.peanut.aps.mapper.ApsRollingForecastFactoryCapacityMapper;
import com.olivia.peanut.aps.model.ApsRollingForecastFactoryCapacity;
import com.olivia.peanut.aps.service.ApsRollingForecastFactoryCapacityService;
import com.olivia.peanut.portal.service.BaseTableHeaderService;
import com.olivia.peanut.portal.service.FactoryService;
import com.olivia.peanut.util.SetNamePojoUtils;
import com.olivia.sdk.service.SetNameService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DateUtils;
import com.olivia.sdk.utils.DynamicsPage;
import jakarta.annotation.Resource;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 滚动预测(ApsRollingForecastFactoryCapacity)表服务实现类
 *
 * @author peanut
 * @since 2024-07-14 20:22:23
 */
@Slf4j
@Service("apsRollingForecastFactoryCapacityService")
@Transactional
public class ApsRollingForecastFactoryCapacityServiceImpl extends MPJBaseServiceImpl<ApsRollingForecastFactoryCapacityMapper, ApsRollingForecastFactoryCapacity> implements
    ApsRollingForecastFactoryCapacityService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  @Resource
  BaseTableHeaderService tableHeaderService;
  @Resource
  FactoryService factoryService;
  @Resource
  SetNameService setNameService;

  // 以下为私有对象封装

  public @Override ApsRollingForecastFactoryCapacityQueryListRes queryList(ApsRollingForecastFactoryCapacityQueryListReq req) {

    MPJLambdaWrapper<ApsRollingForecastFactoryCapacity> q = getWrapper(req.getData());
    List<ApsRollingForecastFactoryCapacity> list = this.list(q);

    List<ApsRollingForecastFactoryCapacityDto> dataList = list.stream().map(t -> $.copy(t, ApsRollingForecastFactoryCapacityDto.class)).collect(Collectors.toList());
    ((ApsRollingForecastFactoryCapacityService) AopContext.currentProxy()).setName(dataList);
    return new ApsRollingForecastFactoryCapacityQueryListRes().setDataList(dataList);
  }

  public @Override DynamicsPage<ApsRollingForecastFactoryCapacityExportQueryPageListInfoRes> queryPageList(ApsRollingForecastFactoryCapacityExportQueryPageListReq req) {

    DynamicsPage<ApsRollingForecastFactoryCapacity> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsRollingForecastFactoryCapacity> q = getWrapper(req.getData());
    List<ApsRollingForecastFactoryCapacityExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsRollingForecastFactoryCapacity> list = this.page(page, q);
      IPage<ApsRollingForecastFactoryCapacityExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsRollingForecastFactoryCapacityExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsRollingForecastFactoryCapacityExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<ApsRollingForecastFactoryCapacityExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsRollingForecastFactoryCapacityExportQueryPageListInfoRes.class);
    ((ApsRollingForecastFactoryCapacityService) AopContext.currentProxy()).setName(listInfoRes);

    return DynamicsPage.init(page, listInfoRes);
  }

  public @Override void setName(List<? extends ApsRollingForecastFactoryCapacityDto> list) {

    setNameService.setName(list, SetNamePojoUtils.FACTORY);
//    if (CollUtil.isEmpty(list)) {
//      return;
//    }
//    Map<Long, String> fnMap = this.factoryService.listByIds(list.stream().map(ApsRollingForecastFactoryCapacityDto::getFactoryId).collect(Collectors.toSet())).stream()
//        .collect(Collectors.toMap(Factory::getId, Factory::getFactoryName));
//    list.forEach(t -> t.setFactoryName(fnMap.get(t.getFactoryId())));
  }


  @Override
  @Transactional
  public ApsRollingForecastFactoryCapacityInsertRes save(ApsRollingForecastFactoryCapacityInsertReq req) {

//    req.setCapacityList(req.getCapacityList().stream().sorted(Comparator.comparing(ApsRollingForecastFactoryCapacityDtoInfo::getBeginTime)).toList());

    AtomicReference<LocalDate> beginDateAtomicReference = new AtomicReference<>();
    AtomicReference<LocalDate> endDateAtomicReference = new AtomicReference<>();
    req.getCapacityList().forEach(t -> {
      LocalDate beginDateTmp = beginDateAtomicReference.get();
      if (Objects.isNull(beginDateTmp)) {
        beginDateAtomicReference.set(t.getBeginTime());
      } else {
        if (t.getBeginTime().isBefore(beginDateTmp)) {
          beginDateAtomicReference.set(t.getBeginTime());
        }
      }
      LocalDate endDateTmp = endDateAtomicReference.get();
      if (Objects.isNull(endDateTmp)) {
        endDateAtomicReference.set(t.getEndTime());
      } else {
        if (t.getEndTime().isAfter(endDateTmp)) {
          endDateAtomicReference.set(t.getEndTime());
        }
      }

    });

    Map<String, ApsRollingForecastFactoryCapacity> capacityMap = Maps.newHashMap();

    req.getCapacityList().forEach(t -> {
      List<LocalDate> localDateBetween = DateUtils.getLocalDateBetween(t.getBeginTime(), t.getEndTime());
      localDateBetween.forEach(t1 -> {
        String key = t1.getYear() + "-" + t1.getMonth();
        ApsRollingForecastFactoryCapacity apsRollingForecastFactoryCapacity = capacityMap.get(key);
        if (Objects.isNull(apsRollingForecastFactoryCapacity)) {
          apsRollingForecastFactoryCapacity = new ApsRollingForecastFactoryCapacity();
          apsRollingForecastFactoryCapacity.setFactoryId(req.getFactoryId()).setYear(t1.getYear()).setMonth(t1.getMonthValue());
        }
        ReflectUtil.setFieldValue(apsRollingForecastFactoryCapacity, "day" + ((t1.getDayOfMonth() < 10) ? "0" + t1.getDayOfMonth() : t1.getDayOfMonth()), t.getCapacity());
        capacityMap.put(key, apsRollingForecastFactoryCapacity);
      });
    });

    LocalDate endDate = endDateAtomicReference.get();
    LocalDate beginDate = beginDateAtomicReference.get();
    log.info("time range :{} - {}", beginDate, endDate);

    List<LocalDate> localDateBetween = DateUtils.getLocalDateBetween(beginDate, endDate);
    log.info("local date between :{}", localDateBetween);

    Map<Integer, Set<Integer>> ymMap = new HashMap<>();
    localDateBetween.forEach(t -> {
      Set<Integer> mL = ymMap.getOrDefault(t.getYear(), Sets.newHashSet());
      mL.add(t.getMonthValue());
      ymMap.put(t.getYear(), mL);
    });
    log.info("ym map :{}", ymMap);
    this.remove(new LambdaQueryWrapper<ApsRollingForecastFactoryCapacity>()//
        .eq(ApsRollingForecastFactoryCapacity::getFactoryId, req.getFactoryId())//
        .and(r -> {
          r.or(r3 -> {
            ymMap.forEach((y, ml) -> {
              r3.eq(ApsRollingForecastFactoryCapacity::getYear, y).and(r1 -> {
                ml.forEach(m -> {
                  r1.or(r2 -> r2.eq(ApsRollingForecastFactoryCapacity::getMonth, m));
                });
              });
            });
          });
        }));
    if (CollUtil.isNotEmpty(capacityMap)) {
      this.saveBatch(capacityMap.values());
    }

    return new ApsRollingForecastFactoryCapacityInsertRes();
  }

  private MPJLambdaWrapper<ApsRollingForecastFactoryCapacity> getWrapper(ApsRollingForecastFactoryCapacityDto obj) {
    MPJLambdaWrapper<ApsRollingForecastFactoryCapacity> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q.eq(Objects.nonNull(obj.getFactoryId()), ApsRollingForecastFactoryCapacity::getFactoryId, obj.getFactoryId())
          .eq(Objects.nonNull(obj.getYear()), ApsRollingForecastFactoryCapacity::getYear, obj.getYear())
          .eq(Objects.nonNull(obj.getMonth()), ApsRollingForecastFactoryCapacity::getMonth, obj.getMonth())

      ;
    }
    List<SFunction<ApsRollingForecastFactoryCapacity, ?>> columns = List.of(ApsRollingForecastFactoryCapacity::getYear, ApsRollingForecastFactoryCapacity::getMonth);
    q.orderByDesc(columns);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsRollingForecastFactoryCapacity> page) {

    tableHeaderService.listByBizKey(page, "ApsRollingForecastFactoryCapacityService#queryPageList");

  }


}

