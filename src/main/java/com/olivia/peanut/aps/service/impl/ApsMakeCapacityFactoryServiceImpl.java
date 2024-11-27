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
import com.olivia.peanut.portal.service.FactoryService;
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
            ReflectUtil.setFieldValue(makeCapacityFactory, "dayMin" + currentDate.getDayOfMonth(), nextMakeCapacityConfig.getMinValue());
            ReflectUtil.setFieldValue(makeCapacityFactory, "dayMax" + currentDate.getDayOfMonth(), nextMakeCapacityConfig.getMaxValue());
          }
        }
        ReflectUtil.setFieldValue(makeCapacityFactory, "dayMin" + currentDate.getDayOfMonth(), makeCapacityConfig.getMinValue());
        ReflectUtil.setFieldValue(makeCapacityFactory, "dayMax" + currentDate.getDayOfMonth(), makeCapacityConfig.getMaxValue());
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
          .eq(Objects.nonNull(obj.getDayMin1()), ApsMakeCapacityFactory::getDayMin1, obj.getDayMin1())
          .eq(Objects.nonNull(obj.getDayMax1()), ApsMakeCapacityFactory::getDayMax1, obj.getDayMax1())
          .eq(Objects.nonNull(obj.getDayMin2()), ApsMakeCapacityFactory::getDayMin2, obj.getDayMin2())
          .eq(Objects.nonNull(obj.getDayMax2()), ApsMakeCapacityFactory::getDayMax2, obj.getDayMax2())
          .eq(Objects.nonNull(obj.getDayMin3()), ApsMakeCapacityFactory::getDayMin3, obj.getDayMin3())
          .eq(Objects.nonNull(obj.getDayMax3()), ApsMakeCapacityFactory::getDayMax3, obj.getDayMax3())
          .eq(Objects.nonNull(obj.getDayMin4()), ApsMakeCapacityFactory::getDayMin4, obj.getDayMin4())
          .eq(Objects.nonNull(obj.getDayMax4()), ApsMakeCapacityFactory::getDayMax4, obj.getDayMax4())
          .eq(Objects.nonNull(obj.getDayMin5()), ApsMakeCapacityFactory::getDayMin5, obj.getDayMin5())
          .eq(Objects.nonNull(obj.getDayMax5()), ApsMakeCapacityFactory::getDayMax5, obj.getDayMax5())
          .eq(Objects.nonNull(obj.getDayMin6()), ApsMakeCapacityFactory::getDayMin6, obj.getDayMin6())
          .eq(Objects.nonNull(obj.getDayMax6()), ApsMakeCapacityFactory::getDayMax6, obj.getDayMax6())
          .eq(Objects.nonNull(obj.getDayMin7()), ApsMakeCapacityFactory::getDayMin7, obj.getDayMin7())
          .eq(Objects.nonNull(obj.getDayMax7()), ApsMakeCapacityFactory::getDayMax7, obj.getDayMax7())
          .eq(Objects.nonNull(obj.getDayMin8()), ApsMakeCapacityFactory::getDayMin8, obj.getDayMin8())
          .eq(Objects.nonNull(obj.getDayMax8()), ApsMakeCapacityFactory::getDayMax8, obj.getDayMax8())
          .eq(Objects.nonNull(obj.getDayMin9()), ApsMakeCapacityFactory::getDayMin9, obj.getDayMin9())
          .eq(Objects.nonNull(obj.getDayMax9()), ApsMakeCapacityFactory::getDayMax9, obj.getDayMax9())
          .eq(Objects.nonNull(obj.getDayMin10()), ApsMakeCapacityFactory::getDayMin10, obj.getDayMin10())
          .eq(Objects.nonNull(obj.getDayMax10()), ApsMakeCapacityFactory::getDayMax10, obj.getDayMax10())
          .eq(Objects.nonNull(obj.getDayMin11()), ApsMakeCapacityFactory::getDayMin11, obj.getDayMin11())
          .eq(Objects.nonNull(obj.getDayMax11()), ApsMakeCapacityFactory::getDayMax11, obj.getDayMax11())
          .eq(Objects.nonNull(obj.getDayMin12()), ApsMakeCapacityFactory::getDayMin12, obj.getDayMin12())
          .eq(Objects.nonNull(obj.getDayMax12()), ApsMakeCapacityFactory::getDayMax12, obj.getDayMax12())
          .eq(Objects.nonNull(obj.getDayMin13()), ApsMakeCapacityFactory::getDayMin13, obj.getDayMin13())
          .eq(Objects.nonNull(obj.getDayMax13()), ApsMakeCapacityFactory::getDayMax13, obj.getDayMax13())
          .eq(Objects.nonNull(obj.getDayMin14()), ApsMakeCapacityFactory::getDayMin14, obj.getDayMin14())
          .eq(Objects.nonNull(obj.getDayMax14()), ApsMakeCapacityFactory::getDayMax14, obj.getDayMax14())
          .eq(Objects.nonNull(obj.getDayMin15()), ApsMakeCapacityFactory::getDayMin15, obj.getDayMin15())
          .eq(Objects.nonNull(obj.getDayMax15()), ApsMakeCapacityFactory::getDayMax15, obj.getDayMax15())
          .eq(Objects.nonNull(obj.getDayMin16()), ApsMakeCapacityFactory::getDayMin16, obj.getDayMin16())
          .eq(Objects.nonNull(obj.getDayMax16()), ApsMakeCapacityFactory::getDayMax16, obj.getDayMax16())
          .eq(Objects.nonNull(obj.getDayMin17()), ApsMakeCapacityFactory::getDayMin17, obj.getDayMin17())
          .eq(Objects.nonNull(obj.getDayMax17()), ApsMakeCapacityFactory::getDayMax17, obj.getDayMax17())
          .eq(Objects.nonNull(obj.getDayMin18()), ApsMakeCapacityFactory::getDayMin18, obj.getDayMin18())
          .eq(Objects.nonNull(obj.getDayMax18()), ApsMakeCapacityFactory::getDayMax18, obj.getDayMax18())
          .eq(Objects.nonNull(obj.getDayMin19()), ApsMakeCapacityFactory::getDayMin19, obj.getDayMin19())
          .eq(Objects.nonNull(obj.getDayMax19()), ApsMakeCapacityFactory::getDayMax19, obj.getDayMax19())
          .eq(Objects.nonNull(obj.getDayMin20()), ApsMakeCapacityFactory::getDayMin20, obj.getDayMin20())
          .eq(Objects.nonNull(obj.getDayMax20()), ApsMakeCapacityFactory::getDayMax20, obj.getDayMax20())
          .eq(Objects.nonNull(obj.getDayMin21()), ApsMakeCapacityFactory::getDayMin21, obj.getDayMin21())
          .eq(Objects.nonNull(obj.getDayMax21()), ApsMakeCapacityFactory::getDayMax21, obj.getDayMax21())
          .eq(Objects.nonNull(obj.getDayMin22()), ApsMakeCapacityFactory::getDayMin22, obj.getDayMin22())
          .eq(Objects.nonNull(obj.getDayMax22()), ApsMakeCapacityFactory::getDayMax22, obj.getDayMax22())
          .eq(Objects.nonNull(obj.getDayMin23()), ApsMakeCapacityFactory::getDayMin23, obj.getDayMin23())
          .eq(Objects.nonNull(obj.getDayMax23()), ApsMakeCapacityFactory::getDayMax23, obj.getDayMax23())
          .eq(Objects.nonNull(obj.getDayMin24()), ApsMakeCapacityFactory::getDayMin24, obj.getDayMin24())
          .eq(Objects.nonNull(obj.getDayMax24()), ApsMakeCapacityFactory::getDayMax24, obj.getDayMax24())
          .eq(Objects.nonNull(obj.getDayMin25()), ApsMakeCapacityFactory::getDayMin25, obj.getDayMin25())
          .eq(Objects.nonNull(obj.getDayMax25()), ApsMakeCapacityFactory::getDayMax25, obj.getDayMax25())
          .eq(Objects.nonNull(obj.getDayMin26()), ApsMakeCapacityFactory::getDayMin26, obj.getDayMin26())
          .eq(Objects.nonNull(obj.getDayMax26()), ApsMakeCapacityFactory::getDayMax26, obj.getDayMax26())
          .eq(Objects.nonNull(obj.getDayMin27()), ApsMakeCapacityFactory::getDayMin27, obj.getDayMin27())
          .eq(Objects.nonNull(obj.getDayMax27()), ApsMakeCapacityFactory::getDayMax27, obj.getDayMax27())
          .eq(Objects.nonNull(obj.getDayMin28()), ApsMakeCapacityFactory::getDayMin28, obj.getDayMin28())
          .eq(Objects.nonNull(obj.getDayMax28()), ApsMakeCapacityFactory::getDayMax28, obj.getDayMax28())
          .eq(Objects.nonNull(obj.getDayMin29()), ApsMakeCapacityFactory::getDayMin29, obj.getDayMin29())
          .eq(Objects.nonNull(obj.getDayMax29()), ApsMakeCapacityFactory::getDayMax29, obj.getDayMax29())
          .eq(Objects.nonNull(obj.getDayMin30()), ApsMakeCapacityFactory::getDayMin30, obj.getDayMin30())
          .eq(Objects.nonNull(obj.getDayMax30()), ApsMakeCapacityFactory::getDayMax30, obj.getDayMax30())
          .eq(Objects.nonNull(obj.getDayMin31()), ApsMakeCapacityFactory::getDayMin31, obj.getDayMin31())
          .eq(Objects.nonNull(obj.getDayMax31()), ApsMakeCapacityFactory::getDayMax31, obj.getDayMax31())

      ;
    }
    q.orderByDesc(ApsMakeCapacityFactory::getYear,ApsMakeCapacityFactory::getMonth);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsMakeCapacityFactory> page) {

    ServiceComment.header(page, "ApsMakeCapacityFactoryService#queryPageList");

  }


}

