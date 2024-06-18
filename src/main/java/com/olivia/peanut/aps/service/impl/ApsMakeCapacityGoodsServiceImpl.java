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
import com.olivia.peanut.aps.api.entity.apsMakeCapacityGoods.*;
import com.olivia.peanut.aps.mapper.ApsMakeCapacityGoodsMapper;
import com.olivia.peanut.aps.model.ApsGoods;
import com.olivia.peanut.aps.model.ApsMakeCapacityGoods;
import com.olivia.peanut.aps.service.ApsGoodsService;
import com.olivia.peanut.aps.service.ApsMakeCapacityGoodsService;
import com.olivia.peanut.portal.service.BaseTableHeaderService;
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
 * (ApsMakeCapacityGoods)表服务实现类
 *
 * @author peanut
 * @since 2024-04-13 12:05:05
 */
@Service("apsMakeCapacityGoodsService")
@Transactional
public class ApsMakeCapacityGoodsServiceImpl extends MPJBaseServiceImpl<ApsMakeCapacityGoodsMapper, ApsMakeCapacityGoods> implements ApsMakeCapacityGoodsService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  @Resource
  BaseTableHeaderService tableHeaderService;
  @Resource
  ApsGoodsService apsGoodsService;

  public @Override ApsMakeCapacityGoodsQueryListRes queryList(ApsMakeCapacityGoodsQueryListReq req) {

    MPJLambdaWrapper<ApsMakeCapacityGoods> q = getWrapper(req.getData());
    List<ApsMakeCapacityGoods> list = this.list(q);

    List<ApsMakeCapacityGoodsDto> dataList = list.stream().map(t -> $.copy(t, ApsMakeCapacityGoodsDto.class)).collect(Collectors.toList());
    this.setName(dataList);
    return new ApsMakeCapacityGoodsQueryListRes().setDataList(dataList);
  }

  public @Override DynamicsPage<ApsMakeCapacityGoodsExportQueryPageListInfoRes> queryPageList(ApsMakeCapacityGoodsExportQueryPageListReq req) {

    DynamicsPage<ApsMakeCapacityGoods> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsMakeCapacityGoods> q = getWrapper(req.getData());
    List<ApsMakeCapacityGoodsExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsMakeCapacityGoods> list = this.page(page, q);
      IPage<ApsMakeCapacityGoodsExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsMakeCapacityGoodsExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsMakeCapacityGoodsExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<ApsMakeCapacityGoodsExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsMakeCapacityGoodsExportQueryPageListInfoRes.class);
    this.setName(listInfoRes);
    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装

  @Override
  public ApsMakeCapacityGoodsInsertRes save(ApsMakeCapacityGoodsInsertReq req) {
    Map<String, ApsMakeCapacityGoods> apsMakeCapacityFactoryMap = this.list(new LambdaQueryWrapper<ApsMakeCapacityGoods>()
            .eq(ApsMakeCapacityGoods::getGoodsId, req.getGoodsId())).stream()
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
        ApsMakeCapacityGoods makeCapacityFactory = apsMakeCapacityFactoryMap.getOrDefault(key, new ApsMakeCapacityGoods());
        makeCapacityFactory.setFactoryId(req.getFactoryId()).setYear(currentDate.getYear()).setMonth(currentDate.getMonthValue()).setGoodsId(req.getGoodsId());
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

    Collection<ApsMakeCapacityGoods> entityList = apsMakeCapacityFactoryMap.values();
    List<ApsMakeCapacityGoods> updateList = entityList.stream().filter(t -> Objects.nonNull(t.getId())).toList();
    if (CollUtil.isNotEmpty(updateList)) {
      this.updateBatchById(updateList);
    }
    List<ApsMakeCapacityGoods> insertList = entityList.stream().filter(t -> Objects.isNull(t.getId())).toList();
    if (CollUtil.isNotEmpty(insertList)) {
      this.saveBatch(insertList);
    }
    return new ApsMakeCapacityGoodsInsertRes();
  }

  public @Override void setName(List<? extends ApsMakeCapacityGoodsDto> apsMakeCapacityGoodsDtoList) {

    if (CollUtil.isEmpty(apsMakeCapacityGoodsDtoList)) {
      return;
    }
    Map<Long, String> gnMap = this.apsGoodsService.listByIds(apsMakeCapacityGoodsDtoList.stream()
            .map(ApsMakeCapacityGoodsDto::getGoodsId).toList()).stream()
        .collect(Collectors.toMap(BaseEntity::getId, ApsGoods::getGoodsName));
    apsMakeCapacityGoodsDtoList.forEach(t -> t.setGoodsName(gnMap.get(t.getGoodsId())));


  }


  private MPJLambdaWrapper<ApsMakeCapacityGoods> getWrapper(ApsMakeCapacityGoodsDto obj) {
    MPJLambdaWrapper<ApsMakeCapacityGoods> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q.eq(Objects.nonNull(obj.getFactoryId()), ApsMakeCapacityGoods::getFactoryId, obj.getFactoryId())
          .eq(Objects.nonNull(obj.getGoodsId()), ApsMakeCapacityGoods::getGoodsId, obj.getGoodsId())
          .eq(Objects.nonNull(obj.getMakeCapacityQuantity()), ApsMakeCapacityGoods::getMakeCapacityQuantity, obj.getMakeCapacityQuantity())
          .eq(Objects.nonNull(obj.getYear()), ApsMakeCapacityGoods::getYear, obj.getYear()).eq(Objects.nonNull(obj.getMonth()), ApsMakeCapacityGoods::getMonth, obj.getMonth())
          .eq(Objects.nonNull(obj.getDayMin1()), ApsMakeCapacityGoods::getDayMin1, obj.getDayMin1())
          .eq(Objects.nonNull(obj.getDayMax1()), ApsMakeCapacityGoods::getDayMax1, obj.getDayMax1())
          .eq(Objects.nonNull(obj.getDayMin2()), ApsMakeCapacityGoods::getDayMin2, obj.getDayMin2())
          .eq(Objects.nonNull(obj.getDayMax2()), ApsMakeCapacityGoods::getDayMax2, obj.getDayMax2())
          .eq(Objects.nonNull(obj.getDayMin3()), ApsMakeCapacityGoods::getDayMin3, obj.getDayMin3())
          .eq(Objects.nonNull(obj.getDayMax3()), ApsMakeCapacityGoods::getDayMax3, obj.getDayMax3())
          .eq(Objects.nonNull(obj.getDayMin4()), ApsMakeCapacityGoods::getDayMin4, obj.getDayMin4())
          .eq(Objects.nonNull(obj.getDayMax4()), ApsMakeCapacityGoods::getDayMax4, obj.getDayMax4())
          .eq(Objects.nonNull(obj.getDayMin5()), ApsMakeCapacityGoods::getDayMin5, obj.getDayMin5())
          .eq(Objects.nonNull(obj.getDayMax5()), ApsMakeCapacityGoods::getDayMax5, obj.getDayMax5())
          .eq(Objects.nonNull(obj.getDayMin6()), ApsMakeCapacityGoods::getDayMin6, obj.getDayMin6())
          .eq(Objects.nonNull(obj.getDayMax6()), ApsMakeCapacityGoods::getDayMax6, obj.getDayMax6())
          .eq(Objects.nonNull(obj.getDayMin7()), ApsMakeCapacityGoods::getDayMin7, obj.getDayMin7())
          .eq(Objects.nonNull(obj.getDayMax7()), ApsMakeCapacityGoods::getDayMax7, obj.getDayMax7())
          .eq(Objects.nonNull(obj.getDayMin8()), ApsMakeCapacityGoods::getDayMin8, obj.getDayMin8())
          .eq(Objects.nonNull(obj.getDayMax8()), ApsMakeCapacityGoods::getDayMax8, obj.getDayMax8())
          .eq(Objects.nonNull(obj.getDayMin9()), ApsMakeCapacityGoods::getDayMin9, obj.getDayMin9())
          .eq(Objects.nonNull(obj.getDayMax9()), ApsMakeCapacityGoods::getDayMax9, obj.getDayMax9())
          .eq(Objects.nonNull(obj.getDayMin10()), ApsMakeCapacityGoods::getDayMin10, obj.getDayMin10())
          .eq(Objects.nonNull(obj.getDayMax10()), ApsMakeCapacityGoods::getDayMax10, obj.getDayMax10())
          .eq(Objects.nonNull(obj.getDayMin11()), ApsMakeCapacityGoods::getDayMin11, obj.getDayMin11())
          .eq(Objects.nonNull(obj.getDayMax11()), ApsMakeCapacityGoods::getDayMax11, obj.getDayMax11())
          .eq(Objects.nonNull(obj.getDayMin12()), ApsMakeCapacityGoods::getDayMin12, obj.getDayMin12())
          .eq(Objects.nonNull(obj.getDayMax12()), ApsMakeCapacityGoods::getDayMax12, obj.getDayMax12())
          .eq(Objects.nonNull(obj.getDayMin13()), ApsMakeCapacityGoods::getDayMin13, obj.getDayMin13())
          .eq(Objects.nonNull(obj.getDayMax13()), ApsMakeCapacityGoods::getDayMax13, obj.getDayMax13())
          .eq(Objects.nonNull(obj.getDayMin14()), ApsMakeCapacityGoods::getDayMin14, obj.getDayMin14())
          .eq(Objects.nonNull(obj.getDayMax14()), ApsMakeCapacityGoods::getDayMax14, obj.getDayMax14())
          .eq(Objects.nonNull(obj.getDayMin15()), ApsMakeCapacityGoods::getDayMin15, obj.getDayMin15())
          .eq(Objects.nonNull(obj.getDayMax15()), ApsMakeCapacityGoods::getDayMax15, obj.getDayMax15())
          .eq(Objects.nonNull(obj.getDayMin16()), ApsMakeCapacityGoods::getDayMin16, obj.getDayMin16())
          .eq(Objects.nonNull(obj.getDayMax16()), ApsMakeCapacityGoods::getDayMax16, obj.getDayMax16())
          .eq(Objects.nonNull(obj.getDayMin17()), ApsMakeCapacityGoods::getDayMin17, obj.getDayMin17())
          .eq(Objects.nonNull(obj.getDayMax17()), ApsMakeCapacityGoods::getDayMax17, obj.getDayMax17())
          .eq(Objects.nonNull(obj.getDayMin18()), ApsMakeCapacityGoods::getDayMin18, obj.getDayMin18())
          .eq(Objects.nonNull(obj.getDayMax18()), ApsMakeCapacityGoods::getDayMax18, obj.getDayMax18())
          .eq(Objects.nonNull(obj.getDayMin19()), ApsMakeCapacityGoods::getDayMin19, obj.getDayMin19())
          .eq(Objects.nonNull(obj.getDayMax19()), ApsMakeCapacityGoods::getDayMax19, obj.getDayMax19())
          .eq(Objects.nonNull(obj.getDayMin20()), ApsMakeCapacityGoods::getDayMin20, obj.getDayMin20())
          .eq(Objects.nonNull(obj.getDayMax20()), ApsMakeCapacityGoods::getDayMax20, obj.getDayMax20())
          .eq(Objects.nonNull(obj.getDayMin21()), ApsMakeCapacityGoods::getDayMin21, obj.getDayMin21())
          .eq(Objects.nonNull(obj.getDayMax21()), ApsMakeCapacityGoods::getDayMax21, obj.getDayMax21())
          .eq(Objects.nonNull(obj.getDayMin22()), ApsMakeCapacityGoods::getDayMin22, obj.getDayMin22())
          .eq(Objects.nonNull(obj.getDayMax22()), ApsMakeCapacityGoods::getDayMax22, obj.getDayMax22())
          .eq(Objects.nonNull(obj.getDayMin23()), ApsMakeCapacityGoods::getDayMin23, obj.getDayMin23())
          .eq(Objects.nonNull(obj.getDayMax23()), ApsMakeCapacityGoods::getDayMax23, obj.getDayMax23())
          .eq(Objects.nonNull(obj.getDayMin24()), ApsMakeCapacityGoods::getDayMin24, obj.getDayMin24())
          .eq(Objects.nonNull(obj.getDayMax24()), ApsMakeCapacityGoods::getDayMax24, obj.getDayMax24())
          .eq(Objects.nonNull(obj.getDayMin25()), ApsMakeCapacityGoods::getDayMin25, obj.getDayMin25())
          .eq(Objects.nonNull(obj.getDayMax25()), ApsMakeCapacityGoods::getDayMax25, obj.getDayMax25())
          .eq(Objects.nonNull(obj.getDayMin26()), ApsMakeCapacityGoods::getDayMin26, obj.getDayMin26())
          .eq(Objects.nonNull(obj.getDayMax26()), ApsMakeCapacityGoods::getDayMax26, obj.getDayMax26())
          .eq(Objects.nonNull(obj.getDayMin27()), ApsMakeCapacityGoods::getDayMin27, obj.getDayMin27())
          .eq(Objects.nonNull(obj.getDayMax27()), ApsMakeCapacityGoods::getDayMax27, obj.getDayMax27())
          .eq(Objects.nonNull(obj.getDayMin28()), ApsMakeCapacityGoods::getDayMin28, obj.getDayMin28())
          .eq(Objects.nonNull(obj.getDayMax28()), ApsMakeCapacityGoods::getDayMax28, obj.getDayMax28())
          .eq(Objects.nonNull(obj.getDayMin29()), ApsMakeCapacityGoods::getDayMin29, obj.getDayMin29())
          .eq(Objects.nonNull(obj.getDayMax29()), ApsMakeCapacityGoods::getDayMax29, obj.getDayMax29())
          .eq(Objects.nonNull(obj.getDayMin30()), ApsMakeCapacityGoods::getDayMin30, obj.getDayMin30())
          .eq(Objects.nonNull(obj.getDayMax30()), ApsMakeCapacityGoods::getDayMax30, obj.getDayMax30())
          .eq(Objects.nonNull(obj.getDayMin31()), ApsMakeCapacityGoods::getDayMin31, obj.getDayMin31())
          .eq(Objects.nonNull(obj.getDayMax31()), ApsMakeCapacityGoods::getDayMax31, obj.getDayMax31())

      ;
    }
    q.orderByDesc(ApsMakeCapacityGoods::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsMakeCapacityGoods> page) {

    tableHeaderService.listByBizKey(page, "ApsMakeCapacityGoodsService#queryPageList");

  }


}

