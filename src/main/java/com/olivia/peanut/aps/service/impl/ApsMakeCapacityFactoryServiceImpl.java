package com.olivia.peanut.aps.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ReflectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.api.entity.apsMakeCapacityFactory.*;
import com.olivia.peanut.aps.mapper.ApsMakeCapacityFactoryMapper;
import com.olivia.peanut.aps.model.ApsMakeCapacityFactory;
import com.olivia.peanut.aps.service.ApsMakeCapacityFactoryService;
import com.olivia.peanut.base.service.FactoryService;
import com.olivia.peanut.util.SetNamePojoUtils;
import com.olivia.sdk.comment.ServiceComment;
import com.olivia.sdk.service.SetNameService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DateUtils;
import com.olivia.sdk.utils.DynamicsPage;
import jakarta.annotation.Resource;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.olivia.sdk.utils.FieldUtils.getField;

/**
 * (ApsMakeCapacityFactory)表服务实现类
 *
 * @author peanut
 * @since 2024-04-13 12:05:04
 */
@Service("apsMakeCapacityFactoryService")
@Transactional
public class ApsMakeCapacityFactoryServiceImpl extends MPJBaseServiceImpl<ApsMakeCapacityFactoryMapper, ApsMakeCapacityFactory> implements ApsMakeCapacityFactoryService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  @Resource
  FactoryService factoryService;
  @Resource
  SetNameService setNameService;

  public @Override ApsMakeCapacityFactoryQueryListRes queryList(ApsMakeCapacityFactoryQueryListReq req) {

    MPJLambdaWrapper<ApsMakeCapacityFactory> q = getWrapper(req.getData());
    List<ApsMakeCapacityFactory> list = this.list(q);

    List<ApsMakeCapacityFactoryDto> dataList = list.stream().map(t -> $.copy(t, ApsMakeCapacityFactoryDto.class)).collect(Collectors.toList());
    //  this.setName(dataList);
    ((ApsMakeCapacityFactoryServiceImpl) AopContext.currentProxy()).setName(dataList);

    return new ApsMakeCapacityFactoryQueryListRes().setDataList(dataList);
  }

  // 以下为私有对象封装

  public @Override DynamicsPage<ApsMakeCapacityFactoryExportQueryPageListInfoRes> queryPageList(ApsMakeCapacityFactoryExportQueryPageListReq req) {

    DynamicsPage<ApsMakeCapacityFactory> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsMakeCapacityFactory> q = getWrapper(req.getData());
    List<ApsMakeCapacityFactoryExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsMakeCapacityFactory> list = this.page(page, q);
      IPage<ApsMakeCapacityFactoryExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsMakeCapacityFactoryExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsMakeCapacityFactoryExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<ApsMakeCapacityFactoryExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsMakeCapacityFactoryExportQueryPageListInfoRes.class);
    // this.setName(listInfoRes);

    ((ApsMakeCapacityFactoryServiceImpl) AopContext.currentProxy()).setName(listInfoRes);
    return DynamicsPage.init(page, listInfoRes);
  }

  @Override
  @Transactional
  public ApsMakeCapacityFactoryInsertRes save(ApsMakeCapacityFactoryInsertReq req) {
    Map<String, ApsMakeCapacityFactory> apsMakeCapacityFactoryMap = this.list(new LambdaQueryWrapper<ApsMakeCapacityFactory>()
            .eq(ApsMakeCapacityFactory::getFactoryId, req.getFactoryId())).stream()
        .collect(Collectors.toMap(t -> t.getYear() + "-" + t.getMonth(), t -> t));
//    Map<String, ApsMakeCapacityFactory> apsMakeCapacityFactoryMap = new HashMap<>();

    List<MakeCapacityConfig> makeCapacityConfigList = req.getMakeCapacityConfigList();

    int size = makeCapacityConfigList.size();
    for (int i = 0; i < size; i++) {
      MakeCapacityConfig makeCapacityConfig = makeCapacityConfigList.get(i);
      List<LocalDate> localDateBetween = DateUtils.getLocalDateBetween(makeCapacityConfig.getBeginDate(), makeCapacityConfig.getEndDate());

      final int cuIndex = i;
      localDateBetween.forEach(currentDate -> {
        String key = currentDate.getYear() + "-" + currentDate.getMonthValue();
        ApsMakeCapacityFactory makeCapacityFactory = apsMakeCapacityFactoryMap.getOrDefault(key, new ApsMakeCapacityFactory());
        makeCapacityFactory.setFactoryId(req.getFactoryId()).setYear(currentDate.getYear()).setMonth(currentDate.getMonthValue());
        for (int j = cuIndex + 1; j < size; j++) {
          MakeCapacityConfig nextMakeCapacityConfig = makeCapacityConfigList.get(j);
          Optional<LocalDate> dateOptional = DateUtils.getLocalDateBetween(nextMakeCapacityConfig.getBeginDate(), nextMakeCapacityConfig.getEndDate()).stream()
              .filter(t -> t.isEqual(currentDate)).findAny();
          if (dateOptional.isPresent()) {
            ReflectUtil.setFieldValue(makeCapacityFactory, getField(makeCapacityFactory, "dayMin" + currentDate.getDayOfMonth()), nextMakeCapacityConfig.getMinValue());
            ReflectUtil.setFieldValue(makeCapacityFactory, getField(makeCapacityFactory, "dayMax" + currentDate.getDayOfMonth()), nextMakeCapacityConfig.getMaxValue());
          }
        }
        ReflectUtil.setFieldValue(makeCapacityFactory, getField(makeCapacityFactory, "dayMin" + currentDate.getDayOfMonth()), makeCapacityConfig.getMinValue());
        ReflectUtil.setFieldValue(makeCapacityFactory, getField(makeCapacityFactory, "dayMax" + currentDate.getDayOfMonth()), makeCapacityConfig.getMaxValue());
        apsMakeCapacityFactoryMap.put(key, makeCapacityFactory);
      });
    }

    Collection<ApsMakeCapacityFactory> entityList = apsMakeCapacityFactoryMap.values();
    List<ApsMakeCapacityFactory> updateList = entityList.stream().filter(t -> Objects.nonNull(t.getId())).toList();
    if (CollUtil.isNotEmpty(updateList)) {
      this.updateBatchById(updateList);
    }
    List<ApsMakeCapacityFactory> insertList = entityList.stream().filter(t -> Objects.isNull(t.getId())).toList();
    if (CollUtil.isNotEmpty(insertList)) {
      this.saveBatch(insertList);
    }
    return new ApsMakeCapacityFactoryInsertRes();
  }

  //  @SetUserName
  public @Override void setName(List<? extends ApsMakeCapacityFactoryDto> apsMakeCapacityFactoryDtoList) {

    setNameService.setName(apsMakeCapacityFactoryDtoList, SetNamePojoUtils.FACTORY);

//    if (CollUtil.isEmpty(apsMakeCapacityFactoryDtoList)) {
//      return;
//    }
//    Map<Long, String> fnMap = factoryService.listByIds(apsMakeCapacityFactoryDtoList.stream().map(ApsMakeCapacityFactoryDto::getFactoryId).collect(Collectors.toSet())).stream()
//        .collect(Collectors.toMap(Factory::getId, Factory::getFactoryName));
//    apsMakeCapacityFactoryDtoList.forEach(t -> t.setFactoryName(fnMap.get(t.getFactoryId())));
  }


  private MPJLambdaWrapper<ApsMakeCapacityFactory> getWrapper(ApsMakeCapacityFactoryDto obj) {
    MPJLambdaWrapper<ApsMakeCapacityFactory> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q.eq(Objects.nonNull(obj.getFactoryId()), ApsMakeCapacityFactory::getFactoryId, obj.getFactoryId())
          .eq(Objects.nonNull(obj.getMakeCapacityQuantity()), ApsMakeCapacityFactory::getMakeCapacityQuantity, obj.getMakeCapacityQuantity())
          .eq(Objects.nonNull(obj.getYear()), ApsMakeCapacityFactory::getYear, obj.getYear()).eq(Objects.nonNull(obj.getMonth()), ApsMakeCapacityFactory::getMonth, obj.getMonth())

      ;
    }
    q.orderByDesc(ApsMakeCapacityFactory::getYear, ApsMakeCapacityFactory::getMonth);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsMakeCapacityFactory> page) {

    ServiceComment.header(page, "ApsMakeCapacityFactoryService#queryPageList");

  }


}

