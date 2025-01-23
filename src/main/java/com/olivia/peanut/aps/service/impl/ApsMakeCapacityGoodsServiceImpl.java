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
import com.olivia.peanut.aps.model.ApsMakeCapacityGoods;
import com.olivia.peanut.aps.service.ApsGoodsService;
import com.olivia.peanut.aps.service.ApsMakeCapacityGoodsService;
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
  ApsGoodsService apsGoodsService;
  @Resource
  SetNameService setNameService;

  public @Override ApsMakeCapacityGoodsQueryListRes queryList(ApsMakeCapacityGoodsQueryListReq req) {

    MPJLambdaWrapper<ApsMakeCapacityGoods> q = getWrapper(req.getData());
    List<ApsMakeCapacityGoods> list = this.list(q);

    List<ApsMakeCapacityGoodsDto> dataList = list.stream().map(t -> $.copy(t, ApsMakeCapacityGoodsDto.class)).collect(Collectors.toList());
    //  this.setName(dataList);
    ((ApsMakeCapacityGoodsServiceImpl) AopContext.currentProxy()).setName(dataList);

    return new ApsMakeCapacityGoodsQueryListRes().setDataList(dataList);
  }

  // 以下为私有对象封装

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
    // this.setName(listInfoRes);
    ((ApsMakeCapacityGoodsServiceImpl) AopContext.currentProxy()).setName(listInfoRes);

    return DynamicsPage.init(page, listInfoRes);
  }

  @Override
  public ApsMakeCapacityGoodsInsertRes save(ApsMakeCapacityGoodsInsertReq req) {
    Map<String, ApsMakeCapacityGoods> apsMakeCapacityFactoryMap = this.list(new LambdaQueryWrapper<ApsMakeCapacityGoods>().eq(ApsMakeCapacityGoods::getGoodsId, req.getGoodsId())).stream().collect(Collectors.toMap(t -> t.getYear() + "-" + t.getMonth(), t -> t));
//    Map<String, ApsMakeCapacityFactory> apsMakeCapacityFactoryMap = new HashMap<>();

    List<MakeCapacityConfig> makeCapacityConfigList = req.getMakeCapacityConfigList();

    int size = makeCapacityConfigList.size();
    for (int i = 0; i < size; i++) {
      MakeCapacityConfig makeCapacityConfig = makeCapacityConfigList.get(i);
      List<LocalDate> localDateBetween = DateUtils.getLocalDateBetween(makeCapacityConfig.getBeginDate(), makeCapacityConfig.getEndDate());

      final int cuIndex = i;
      localDateBetween.forEach(currentDate -> {
        String key = currentDate.getYear() + "-" + currentDate.getMonthValue();
        ApsMakeCapacityGoods makeCapacityGoods = apsMakeCapacityFactoryMap.getOrDefault(key, new ApsMakeCapacityGoods());
        makeCapacityGoods.setFactoryId(req.getFactoryId()).setYear(currentDate.getYear()).setMonth(currentDate.getMonthValue()).setGoodsId(req.getGoodsId());
        for (int j = cuIndex + 1; j < size; j++) {
          MakeCapacityConfig nextMakeCapacityConfig = makeCapacityConfigList.get(j);
          Optional<LocalDate> dateOptional = DateUtils.getLocalDateBetween(nextMakeCapacityConfig.getBeginDate(), nextMakeCapacityConfig.getEndDate()).stream().filter(t -> t.isEqual(currentDate)).findAny();
          if (dateOptional.isPresent()) {
            ReflectUtil.setFieldValue(makeCapacityGoods, getField(ApsMakeCapacityGoods.class, "dayMin" + currentDate.getDayOfMonth()), nextMakeCapacityConfig.getMinValue());
            ReflectUtil.setFieldValue(makeCapacityGoods, getField(ApsMakeCapacityGoods.class, "dayMax" + currentDate.getDayOfMonth()), nextMakeCapacityConfig.getMaxValue());
          }
        }
        ReflectUtil.setFieldValue(makeCapacityGoods, getField(ApsMakeCapacityGoods.class, "dayMin" + currentDate.getDayOfMonth()), makeCapacityConfig.getMinValue());
        ReflectUtil.setFieldValue(makeCapacityGoods, getField(ApsMakeCapacityGoods.class, "dayMax" + currentDate.getDayOfMonth()), makeCapacityConfig.getMaxValue());
        apsMakeCapacityFactoryMap.put(key, makeCapacityGoods);
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

  //  @SetUserName
  public @Override void setName(List<? extends ApsMakeCapacityGoodsDto> apsMakeCapacityGoodsDtoList) {

    setNameService.setName(apsMakeCapacityGoodsDtoList,
//        SetNamePojoUtils.OP_USER_NAME,
        SetNamePojoUtils.GOODS);
//    if (CollUtil.isEmpty(apsMakeCapacityGoodsDtoList)) {
//      return;
//    }
//    Map<Long, String> gnMap = this.apsGoodsService.listByIds(apsMakeCapacityGoodsDtoList.stream()
//            .map(ApsMakeCapacityGoodsDto::getGoodsId).toList()).stream()
//        .collect(Collectors.toMap(BaseEntity::getId, ApsGoods::getGoodsName));
//    apsMakeCapacityGoodsDtoList.forEach(t -> t.setGoodsName(gnMap.get(t.getGoodsId())));

  }


  private MPJLambdaWrapper<ApsMakeCapacityGoods> getWrapper(ApsMakeCapacityGoodsDto obj) {
    MPJLambdaWrapper<ApsMakeCapacityGoods> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q.eq(Objects.nonNull(obj.getFactoryId()), ApsMakeCapacityGoods::getFactoryId, obj.getFactoryId()).eq(Objects.nonNull(obj.getGoodsId()), ApsMakeCapacityGoods::getGoodsId, obj.getGoodsId()).eq(Objects.nonNull(obj.getMakeCapacityQuantity()), ApsMakeCapacityGoods::getMakeCapacityQuantity, obj.getMakeCapacityQuantity()).eq(Objects.nonNull(obj.getYear()), ApsMakeCapacityGoods::getYear, obj.getYear()).eq(Objects.nonNull(obj.getMonth()), ApsMakeCapacityGoods::getMonth, obj.getMonth());
    }
    q.orderByDesc(ApsMakeCapacityGoods::getYear, ApsMakeCapacityGoods::getMonth);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsMakeCapacityGoods> page) {

    ServiceComment.header(page, "ApsMakeCapacityGoodsService#queryPageList");

  }


}

