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
import com.olivia.sdk.comment.ServiceComment;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.BaseEntity;
import com.olivia.sdk.utils.DateUtils;
import com.olivia.sdk.utils.DynamicsPage;
import jakarta.annotation.Resource;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    this.setName(dataList);
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
    this.setName(listInfoRes);
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
            ReflectUtil.setFieldValue(makeCapacityFactory, "dayMin" + currentDate.getDayOfMonth(), nextMakeCapacityConfig.getMinValue());
            ReflectUtil.setFieldValue(makeCapacityFactory, "dayMax" + currentDate.getDayOfMonth(), nextMakeCapacityConfig.getMaxValue());
          }
        }
        ReflectUtil.setFieldValue(makeCapacityFactory, "dayMin" + currentDate.getDayOfMonth(), makeCapacityConfig.getMinValue());
        ReflectUtil.setFieldValue(makeCapacityFactory, "dayMax" + currentDate.getDayOfMonth(), makeCapacityConfig.getMaxValue());
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
          .eq(Objects.nonNull(obj.getDayMin1()), ApsMakeCapacitySaleConfig::getDayMin1, obj.getDayMin1())
          .eq(Objects.nonNull(obj.getDayMax1()), ApsMakeCapacitySaleConfig::getDayMax1, obj.getDayMax1())
          .eq(Objects.nonNull(obj.getDayMin2()), ApsMakeCapacitySaleConfig::getDayMin2, obj.getDayMin2())
          .eq(Objects.nonNull(obj.getDayMax2()), ApsMakeCapacitySaleConfig::getDayMax2, obj.getDayMax2())
          .eq(Objects.nonNull(obj.getDayMin3()), ApsMakeCapacitySaleConfig::getDayMin3, obj.getDayMin3())
          .eq(Objects.nonNull(obj.getDayMax3()), ApsMakeCapacitySaleConfig::getDayMax3, obj.getDayMax3())
          .eq(Objects.nonNull(obj.getDayMin4()), ApsMakeCapacitySaleConfig::getDayMin4, obj.getDayMin4())
          .eq(Objects.nonNull(obj.getDayMax4()), ApsMakeCapacitySaleConfig::getDayMax4, obj.getDayMax4())
          .eq(Objects.nonNull(obj.getDayMin5()), ApsMakeCapacitySaleConfig::getDayMin5, obj.getDayMin5())
          .eq(Objects.nonNull(obj.getDayMax5()), ApsMakeCapacitySaleConfig::getDayMax5, obj.getDayMax5())
          .eq(Objects.nonNull(obj.getDayMin6()), ApsMakeCapacitySaleConfig::getDayMin6, obj.getDayMin6())
          .eq(Objects.nonNull(obj.getDayMax6()), ApsMakeCapacitySaleConfig::getDayMax6, obj.getDayMax6())
          .eq(Objects.nonNull(obj.getDayMin7()), ApsMakeCapacitySaleConfig::getDayMin7, obj.getDayMin7())
          .eq(Objects.nonNull(obj.getDayMax7()), ApsMakeCapacitySaleConfig::getDayMax7, obj.getDayMax7())
          .eq(Objects.nonNull(obj.getDayMin8()), ApsMakeCapacitySaleConfig::getDayMin8, obj.getDayMin8())
          .eq(Objects.nonNull(obj.getDayMax8()), ApsMakeCapacitySaleConfig::getDayMax8, obj.getDayMax8())
          .eq(Objects.nonNull(obj.getDayMin9()), ApsMakeCapacitySaleConfig::getDayMin9, obj.getDayMin9())
          .eq(Objects.nonNull(obj.getDayMax9()), ApsMakeCapacitySaleConfig::getDayMax9, obj.getDayMax9())
          .eq(Objects.nonNull(obj.getDayMin10()), ApsMakeCapacitySaleConfig::getDayMin10, obj.getDayMin10())
          .eq(Objects.nonNull(obj.getDayMax10()), ApsMakeCapacitySaleConfig::getDayMax10, obj.getDayMax10())
          .eq(Objects.nonNull(obj.getDayMin11()), ApsMakeCapacitySaleConfig::getDayMin11, obj.getDayMin11())
          .eq(Objects.nonNull(obj.getDayMax11()), ApsMakeCapacitySaleConfig::getDayMax11, obj.getDayMax11())
          .eq(Objects.nonNull(obj.getDayMin12()), ApsMakeCapacitySaleConfig::getDayMin12, obj.getDayMin12())
          .eq(Objects.nonNull(obj.getDayMax12()), ApsMakeCapacitySaleConfig::getDayMax12, obj.getDayMax12())
          .eq(Objects.nonNull(obj.getDayMin13()), ApsMakeCapacitySaleConfig::getDayMin13, obj.getDayMin13())
          .eq(Objects.nonNull(obj.getDayMax13()), ApsMakeCapacitySaleConfig::getDayMax13, obj.getDayMax13())
          .eq(Objects.nonNull(obj.getDayMin14()), ApsMakeCapacitySaleConfig::getDayMin14, obj.getDayMin14())
          .eq(Objects.nonNull(obj.getDayMax14()), ApsMakeCapacitySaleConfig::getDayMax14, obj.getDayMax14())
          .eq(Objects.nonNull(obj.getDayMin15()), ApsMakeCapacitySaleConfig::getDayMin15, obj.getDayMin15())
          .eq(Objects.nonNull(obj.getDayMax15()), ApsMakeCapacitySaleConfig::getDayMax15, obj.getDayMax15())
          .eq(Objects.nonNull(obj.getDayMin16()), ApsMakeCapacitySaleConfig::getDayMin16, obj.getDayMin16())
          .eq(Objects.nonNull(obj.getDayMax16()), ApsMakeCapacitySaleConfig::getDayMax16, obj.getDayMax16())
          .eq(Objects.nonNull(obj.getDayMin17()), ApsMakeCapacitySaleConfig::getDayMin17, obj.getDayMin17())
          .eq(Objects.nonNull(obj.getDayMax17()), ApsMakeCapacitySaleConfig::getDayMax17, obj.getDayMax17())
          .eq(Objects.nonNull(obj.getDayMin18()), ApsMakeCapacitySaleConfig::getDayMin18, obj.getDayMin18())
          .eq(Objects.nonNull(obj.getDayMax18()), ApsMakeCapacitySaleConfig::getDayMax18, obj.getDayMax18())
          .eq(Objects.nonNull(obj.getDayMin19()), ApsMakeCapacitySaleConfig::getDayMin19, obj.getDayMin19())
          .eq(Objects.nonNull(obj.getDayMax19()), ApsMakeCapacitySaleConfig::getDayMax19, obj.getDayMax19())
          .eq(Objects.nonNull(obj.getDayMin20()), ApsMakeCapacitySaleConfig::getDayMin20, obj.getDayMin20())
          .eq(Objects.nonNull(obj.getDayMax20()), ApsMakeCapacitySaleConfig::getDayMax20, obj.getDayMax20())
          .eq(Objects.nonNull(obj.getDayMin21()), ApsMakeCapacitySaleConfig::getDayMin21, obj.getDayMin21())
          .eq(Objects.nonNull(obj.getDayMax21()), ApsMakeCapacitySaleConfig::getDayMax21, obj.getDayMax21())
          .eq(Objects.nonNull(obj.getDayMin22()), ApsMakeCapacitySaleConfig::getDayMin22, obj.getDayMin22())
          .eq(Objects.nonNull(obj.getDayMax22()), ApsMakeCapacitySaleConfig::getDayMax22, obj.getDayMax22())
          .eq(Objects.nonNull(obj.getDayMin23()), ApsMakeCapacitySaleConfig::getDayMin23, obj.getDayMin23())
          .eq(Objects.nonNull(obj.getDayMax23()), ApsMakeCapacitySaleConfig::getDayMax23, obj.getDayMax23())
          .eq(Objects.nonNull(obj.getDayMin24()), ApsMakeCapacitySaleConfig::getDayMin24, obj.getDayMin24())
          .eq(Objects.nonNull(obj.getDayMax24()), ApsMakeCapacitySaleConfig::getDayMax24, obj.getDayMax24())
          .eq(Objects.nonNull(obj.getDayMin25()), ApsMakeCapacitySaleConfig::getDayMin25, obj.getDayMin25())
          .eq(Objects.nonNull(obj.getDayMax25()), ApsMakeCapacitySaleConfig::getDayMax25, obj.getDayMax25())
          .eq(Objects.nonNull(obj.getDayMin26()), ApsMakeCapacitySaleConfig::getDayMin26, obj.getDayMin26())
          .eq(Objects.nonNull(obj.getDayMax26()), ApsMakeCapacitySaleConfig::getDayMax26, obj.getDayMax26())
          .eq(Objects.nonNull(obj.getDayMin27()), ApsMakeCapacitySaleConfig::getDayMin27, obj.getDayMin27())
          .eq(Objects.nonNull(obj.getDayMax27()), ApsMakeCapacitySaleConfig::getDayMax27, obj.getDayMax27())
          .eq(Objects.nonNull(obj.getDayMin28()), ApsMakeCapacitySaleConfig::getDayMin28, obj.getDayMin28())
          .eq(Objects.nonNull(obj.getDayMax28()), ApsMakeCapacitySaleConfig::getDayMax28, obj.getDayMax28())
          .eq(Objects.nonNull(obj.getDayMin29()), ApsMakeCapacitySaleConfig::getDayMin29, obj.getDayMin29())
          .eq(Objects.nonNull(obj.getDayMax29()), ApsMakeCapacitySaleConfig::getDayMax29, obj.getDayMax29())
          .eq(Objects.nonNull(obj.getDayMin30()), ApsMakeCapacitySaleConfig::getDayMin30, obj.getDayMin30())
          .eq(Objects.nonNull(obj.getDayMax30()), ApsMakeCapacitySaleConfig::getDayMax30, obj.getDayMax30())
          .eq(Objects.nonNull(obj.getDayMin31()), ApsMakeCapacitySaleConfig::getDayMin31, obj.getDayMin31())
          .eq(Objects.nonNull(obj.getDayMax31()), ApsMakeCapacitySaleConfig::getDayMax31, obj.getDayMax31())

      ;
    }
    q.orderByDesc(ApsMakeCapacitySaleConfig::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsMakeCapacitySaleConfig> page) {

    ServiceComment.header(page, "ApsMakeCapacitySaleConfigService#queryPageList");

  }


}

