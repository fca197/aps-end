package com.olivia.peanut.aps.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ReflectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.api.entity.apsMakeCapacityFactory.MakeCapacityConfig;
import com.olivia.peanut.aps.api.entity.apsMakeCapacitySaleConfig.*;
import com.olivia.peanut.aps.mapper.ApsMakeCapacitySaleConfigMapper;
import com.olivia.peanut.aps.model.ApsMakeCapacitySaleConfig;
import com.olivia.peanut.aps.model.ApsSaleConfig;
import com.olivia.peanut.aps.service.ApsMakeCapacitySaleConfigService;
import com.olivia.peanut.aps.service.ApsSaleConfigService;
import com.olivia.sdk.ann.SetUserName;
import com.olivia.sdk.comment.ServiceComment;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.BaseEntity;
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
 * (ApsMakeCapacitySaleConfig)表服务实现类
 *
 * @author peanut
 * @since 2024-04-13 12:05:06
 */
@Service("apsMakeCapacitySaleConfigService")
@Transactional
public class ApsMakeCapacitySaleConfigServiceImpl extends MPJBaseServiceImpl<ApsMakeCapacitySaleConfigMapper, ApsMakeCapacitySaleConfig> implements
    ApsMakeCapacitySaleConfigService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  // 以下为私有对象封装
  @Resource
  ApsSaleConfigService apsSaleConfigService;

  public @Override ApsMakeCapacitySaleConfigQueryListRes queryList(ApsMakeCapacitySaleConfigQueryListReq req) {

    MPJLambdaWrapper<ApsMakeCapacitySaleConfig> q = getWrapper(req.getData());
    List<ApsMakeCapacitySaleConfig> list = this.list(q);

    List<ApsMakeCapacitySaleConfigDto> dataList = list.stream().map(t -> $.copy(t, ApsMakeCapacitySaleConfigDto.class)).collect(Collectors.toList());
    //  this.setName(dataList);
    ((ApsMakeCapacitySaleConfigService) AopContext.currentProxy()).setName(dataList);

    return new ApsMakeCapacitySaleConfigQueryListRes().setDataList(dataList);
  }

  public @Override DynamicsPage<ApsMakeCapacitySaleConfigExportQueryPageListInfoRes> queryPageList(ApsMakeCapacitySaleConfigExportQueryPageListReq req) {

    DynamicsPage<ApsMakeCapacitySaleConfig> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsMakeCapacitySaleConfig> q = getWrapper(req.getData());
    List<ApsMakeCapacitySaleConfigExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsMakeCapacitySaleConfig> list = this.page(page, q);
      IPage<ApsMakeCapacitySaleConfigExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsMakeCapacitySaleConfigExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsMakeCapacitySaleConfigExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<ApsMakeCapacitySaleConfigExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsMakeCapacitySaleConfigExportQueryPageListInfoRes.class);
    // this.setName(listInfoRes);
    ((ApsMakeCapacitySaleConfigService) AopContext.currentProxy()).setName(listInfoRes);

    return DynamicsPage.init(page, listInfoRes);
  }

  @Override
  public ApsMakeCapacitySaleConfigInsertRes save(ApsMakeCapacitySaleConfigInsertReq req) {
    Map<String, ApsMakeCapacitySaleConfig> apsMakeCapacityFactoryMap = this.list(new LambdaQueryWrapper<ApsMakeCapacitySaleConfig>()
            .eq(ApsMakeCapacitySaleConfig::getSaleConfigId, req.getSaleConfigId())).stream()
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
        ApsMakeCapacitySaleConfig makeCapacityFactory = apsMakeCapacityFactoryMap.getOrDefault(key, new ApsMakeCapacitySaleConfig());
        makeCapacityFactory.setSaleConfigId(req.getSaleConfigId()).setYear(currentDate.getYear()).setMonth(currentDate.getMonthValue());
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

    Collection<ApsMakeCapacitySaleConfig> entityList = apsMakeCapacityFactoryMap.values();
    List<ApsMakeCapacitySaleConfig> updateList = entityList.stream().filter(t -> Objects.nonNull(t.getId())).toList();
    if (CollUtil.isNotEmpty(updateList)) {
      this.updateBatchById(updateList);
    }
    List<ApsMakeCapacitySaleConfig> insertList = entityList.stream().filter(t -> Objects.isNull(t.getId())).toList();
    if (CollUtil.isNotEmpty(insertList)) {
      this.saveBatch(insertList);
    }

    return new ApsMakeCapacitySaleConfigInsertRes();
  }

  @SetUserName
  public @Override void setName(List<? extends ApsMakeCapacitySaleConfigDto> apsMakeCapacitySaleConfigDtoList) {

    if (CollUtil.isEmpty(apsMakeCapacitySaleConfigDtoList)) {
      return;
    }
    Map<Long, String> snMap = apsSaleConfigService.listByIds(apsMakeCapacitySaleConfigDtoList.stream()
            .map(ApsMakeCapacitySaleConfigDto::getSaleConfigId).collect(Collectors.toSet())).stream()
        .collect(Collectors.toMap(BaseEntity::getId, ApsSaleConfig::getSaleName));
    apsMakeCapacitySaleConfigDtoList.forEach(t -> t.setSaleConfigName(snMap.get(t.getSaleConfigId())));

  }


  private MPJLambdaWrapper<ApsMakeCapacitySaleConfig> getWrapper(ApsMakeCapacitySaleConfigDto obj) {
    MPJLambdaWrapper<ApsMakeCapacitySaleConfig> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(Objects.nonNull(obj.getSaleConfigId()), ApsMakeCapacitySaleConfig::getSaleConfigId, obj.getSaleConfigId())
          .eq(Objects.nonNull(obj.getMakeCapacityQuantity()), ApsMakeCapacitySaleConfig::getMakeCapacityQuantity, obj.getMakeCapacityQuantity())
          .eq(Objects.nonNull(obj.getYear()), ApsMakeCapacitySaleConfig::getYear, obj.getYear())
          .eq(Objects.nonNull(obj.getMonth()), ApsMakeCapacitySaleConfig::getMonth, obj.getMonth())
      ;
    }
    q.orderByDesc(ApsMakeCapacitySaleConfig::getYear, ApsMakeCapacitySaleConfig::getMonth);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsMakeCapacitySaleConfig> page) {

    ServiceComment.header(page, "ApsMakeCapacitySaleConfigService#queryPageList");

  }


}

