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
import com.olivia.peanut.aps.api.entity.apsRollingForecastFactoryCapacity.*;
import com.olivia.peanut.aps.mapper.ApsRollingForecastFactoryCapacityMapper;
import com.olivia.peanut.aps.model.ApsRollingForecastFactoryCapacity;
import com.olivia.peanut.aps.service.ApsRollingForecastFactoryCapacityService;
import com.olivia.peanut.aps.service.pojo.FactoryCapacityDay;
import com.olivia.peanut.base.service.BaseTableHeaderService;
import com.olivia.peanut.base.service.FactoryService;
import com.olivia.peanut.util.SetNamePojoUtils;
import com.olivia.sdk.service.SetNameService;
import com.olivia.sdk.utils.*;
import com.olivia.sdk.utils.model.YearMonth;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.olivia.sdk.utils.FieldUtils.getField;

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

    List<LocalDate> minMaxDate = DateUtils.getMinMaxDate(req.getCapacityList().stream().map(t -> List.of(t.getBeginTime(), t.getEndTime())).toList());

    Map<String, ApsRollingForecastFactoryCapacity> capacityMap = Maps.newHashMap();

    LocalDate beginDate = minMaxDate.getFirst();
    LocalDate endDate = minMaxDate.get(1);
    log.info("time range :{} - {}", beginDate, endDate);

    List<YearMonth> monthList = DateUtils.getMonthList(beginDate, endDate);
    $.requireNonNullCanIgnoreException(monthList, "时间范围获取失败,请检查时间范围");
    List<ApsRollingForecastFactoryCapacity> factoryCapacityList = this.list(new LambdaQueryWrapper<ApsRollingForecastFactoryCapacity>()//
        .eq(ApsRollingForecastFactoryCapacity::getFactoryId, req.getFactoryId())//
        .and(r -> {
          monthList.forEach((ym) -> {
            r.or(rt -> {
              rt.eq(ApsRollingForecastFactoryCapacity::getYear, ym.getYear()).eq(ApsRollingForecastFactoryCapacity::getMonth, ym.getMonth());
            });
          });
        }));
    factoryCapacityList.forEach(t -> {
      capacityMap.put(t.getYear() + "-" + t.getMonth(), t);
    });

    req.getCapacityList().forEach(t -> {
      List<LocalDate> localDateBetween = DateUtils.getLocalDateBetween(t.getBeginTime(), t.getEndTime());
      localDateBetween.forEach(t1 -> {
        String key = t1.getYear() + "-" + t1.getMonthValue();
        ApsRollingForecastFactoryCapacity apsRollingForecastFactoryCapacity = capacityMap.get(key);
        if (Objects.isNull(apsRollingForecastFactoryCapacity)) {
          apsRollingForecastFactoryCapacity = new ApsRollingForecastFactoryCapacity();
          apsRollingForecastFactoryCapacity.setFactoryId(req.getFactoryId()).setYear(t1.getYear()).setMonth(t1.getMonthValue());
        }
        ReflectUtil.setFieldValue(apsRollingForecastFactoryCapacity, "day" + ((t1.getDayOfMonth() < 10) ? "0" + t1.getDayOfMonth() : t1.getDayOfMonth()), t.getCapacity());
        capacityMap.put(key, apsRollingForecastFactoryCapacity);
      });
    });

    Collection<ApsRollingForecastFactoryCapacity> values = capacityMap.values();
    if (CollUtil.isNotEmpty(values)) {
      List<ApsRollingForecastFactoryCapacity> insertList = values.stream().filter(t -> Objects.isNull(t.getId())).toList();
      this.saveBatch(insertList);
      List<ApsRollingForecastFactoryCapacity> updateList = values.stream().filter(t -> Objects.nonNull(t.getId())).toList();
      this.updateBatchById(updateList);
    }

    return new ApsRollingForecastFactoryCapacityInsertRes();
  }

  @Override
  public List<FactoryCapacityDay> list(Long factoryId, LocalDate beginDate, LocalDate endDate) {
    List<YearMonth> monthList = DateUtils.getMonthList(beginDate, endDate);
    if (CollUtil.isEmpty(monthList)) {
      return null;
    }
    List<ApsRollingForecastFactoryCapacity> factoryCapacityList = this.list(new LambdaQueryWrapper<ApsRollingForecastFactoryCapacity>()//
        .eq(ApsRollingForecastFactoryCapacity::getFactoryId, factoryId).and(r -> {
          monthList.forEach(t -> {
            r.or(rt -> {
              rt.eq(ApsRollingForecastFactoryCapacity::getYear, t.getYear()).eq(ApsRollingForecastFactoryCapacity::getMonth, t.getMonth());
            });
          });
        }));
    if (CollUtil.isEmpty(factoryCapacityList)) {
      return null;
    }
    List<LocalDate> localDateBetween = DateUtils.getLocalDateBetween(beginDate, endDate);
    Map<String, ApsRollingForecastFactoryCapacity> capacityMap = factoryCapacityList.stream().collect(Collectors.toMap(t -> t.getYear() + "-" + t.getMonth(), Function.identity()));

    List<FactoryCapacityDay> dayList = new ArrayList<>();
    localDateBetween.forEach(t -> {
      ApsRollingForecastFactoryCapacity apsRollingForecastFactoryCapacity = capacityMap.get(t.getYear() + "-" + t.getMonthValue());
      if (Objects.nonNull(apsRollingForecastFactoryCapacity)) {
        String fieldName = "day" + ((t.getDayOfMonth() < 10) ? "0" + t.getDayOfMonth() : t.getDayOfMonth());
        Field field = getField(apsRollingForecastFactoryCapacity, fieldName);
        Integer capacity = (Integer) FieldUtils.getFieldValue(apsRollingForecastFactoryCapacity, field);
        capacity = Objects.isNull(capacity) ? 0 : capacity;
        dayList.add(new FactoryCapacityDay().setCapacity(capacity).setLocalDate(t));
      }
    });
    return dayList;
  }

  private MPJLambdaWrapper<ApsRollingForecastFactoryCapacity> getWrapper(ApsRollingForecastFactoryCapacityDto obj) {
    MPJLambdaWrapper<ApsRollingForecastFactoryCapacity> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      ApsRollingForecastFactoryCapacity copy = $.copy(obj, ApsRollingForecastFactoryCapacity.class);
      LambdaQueryUtil.lambdaQueryWrapper(q, copy, ApsRollingForecastFactoryCapacity::getYear);
      LambdaQueryUtil.lambdaQueryWrapper(q, copy, ApsRollingForecastFactoryCapacity::getMonth);
      LambdaQueryUtil.lambdaQueryWrapper(q, copy, ApsRollingForecastFactoryCapacity::getFactoryId);
    }
    q.orderByDesc(ApsRollingForecastFactoryCapacity::getId);
    List<SFunction<ApsRollingForecastFactoryCapacity, ?>> columns = List.of(ApsRollingForecastFactoryCapacity::getYear, ApsRollingForecastFactoryCapacity::getMonth);
    q.orderByDesc(columns);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsRollingForecastFactoryCapacity> page) {

    tableHeaderService.listByBizKey(page, "ApsRollingForecastFactoryCapacityService#queryPageList");

  }


}

