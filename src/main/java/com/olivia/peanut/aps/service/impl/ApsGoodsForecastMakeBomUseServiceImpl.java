package com.olivia.peanut.aps.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.api.entity.apsGoodsForecastMakeBomUse.*;
import com.olivia.peanut.aps.mapper.ApsGoodsForecastMakeBomUseMapper;
import com.olivia.peanut.aps.model.ApsGoodsForecastMakeBomUse;
import com.olivia.peanut.aps.service.ApsGoodsForecastMakeBomUseService;
import com.olivia.sdk.ann.SetUserName;
import com.olivia.sdk.comment.ServiceComment;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * (ApsGoodsForecastMakeBomUse)表服务实现类
 *
 * @author peanut
 * @since 2024-05-15 10:26:04
 */
@Service("apsGoodsForecastMakeBomUseService")
@Transactional
public class ApsGoodsForecastMakeBomUseServiceImpl extends MPJBaseServiceImpl<ApsGoodsForecastMakeBomUseMapper, ApsGoodsForecastMakeBomUse> implements
    ApsGoodsForecastMakeBomUseService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();


  public @Override ApsGoodsForecastMakeBomUseQueryListRes queryList(ApsGoodsForecastMakeBomUseQueryListReq req) {

    MPJLambdaWrapper<ApsGoodsForecastMakeBomUse> q = getWrapper(req.getData());
    List<ApsGoodsForecastMakeBomUse> list = this.list(q);

    List<ApsGoodsForecastMakeBomUseDto> dataList = list.stream().map(t -> $.copy(t, ApsGoodsForecastMakeBomUseDto.class)).collect(Collectors.toList());
    //  this.setName(dataList);
    ((ApsGoodsForecastMakeBomUseServiceImpl) AopContext.currentProxy()).setName(dataList);

    return new ApsGoodsForecastMakeBomUseQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<ApsGoodsForecastMakeBomUseExportQueryPageListInfoRes> queryPageList(ApsGoodsForecastMakeBomUseExportQueryPageListReq req) {

    DynamicsPage<ApsGoodsForecastMakeBomUse> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsGoodsForecastMakeBomUse> q = getWrapper(req.getData());
    List<ApsGoodsForecastMakeBomUseExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsGoodsForecastMakeBomUse> list = this.page(page, q);
      IPage<ApsGoodsForecastMakeBomUseExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsGoodsForecastMakeBomUseExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsGoodsForecastMakeBomUseExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<ApsGoodsForecastMakeBomUseExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsGoodsForecastMakeBomUseExportQueryPageListInfoRes.class);
    // this.setName(listInfoRes);
    ((ApsGoodsForecastMakeBomUseServiceImpl) AopContext.currentProxy()).setName(listInfoRes);

    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装

  @SetUserName
  public @Override void setName(List<? extends ApsGoodsForecastMakeBomUseDto> apsGoodsForecastMakeBomUseDtoList) {

    if (CollUtil.isEmpty(apsGoodsForecastMakeBomUseDtoList)) {
      return;
    }


  }


  private MPJLambdaWrapper<ApsGoodsForecastMakeBomUse> getWrapper(ApsGoodsForecastMakeBomUseDto obj) {
    MPJLambdaWrapper<ApsGoodsForecastMakeBomUse> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(Objects.nonNull(obj.getGoodsId()), ApsGoodsForecastMakeBomUse::getGoodsId, obj.getGoodsId())
          .eq(Objects.nonNull(obj.getMakeMonthId()), ApsGoodsForecastMakeBomUse::getMakeMonthId, obj.getMakeMonthId())
          .eq(Objects.nonNull(obj.getBomId()), ApsGoodsForecastMakeBomUse::getBomId, obj.getBomId())
          .eq(Objects.nonNull(obj.getYear()), ApsGoodsForecastMakeBomUse::getYear, obj.getYear())

          .eq(Objects.nonNull(obj.getFactoryId()), ApsGoodsForecastMakeBomUse::getFactoryId, obj.getFactoryId())
          .eq(Objects.nonNull(obj.getMakeSaleConfigId()), ApsGoodsForecastMakeBomUse::getMakeSaleConfigId, obj.getMakeSaleConfigId())

      ;
    }
    q.orderByDesc(ApsGoodsForecastMakeBomUse::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsGoodsForecastMakeBomUse> page) {

    ServiceComment.header(page, "ApsGoodsForecastMakeBomUseService#queryPageList");

  }


}

