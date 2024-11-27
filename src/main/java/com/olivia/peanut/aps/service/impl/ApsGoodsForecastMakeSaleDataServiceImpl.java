package com.olivia.peanut.aps.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.api.entity.apsGoodsForecastMakeSaleData.*;
import com.olivia.peanut.aps.mapper.ApsGoodsForecastMakeSaleDataMapper;
import com.olivia.peanut.aps.model.ApsGoodsForecastMakeSaleData;
import com.olivia.peanut.aps.service.ApsGoodsForecastMakeSaleDataService;
import com.olivia.sdk.ann.SetUserName;
import com.olivia.sdk.comment.ServiceComment;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * (ApsGoodsForecastMakeSaleData)表服务实现类
 *
 * @author peanut
 * @since 2024-04-07 15:07:49
 */
@Service("apsGoodsForecastMakeSaleDataService")
@Transactional
public class ApsGoodsForecastMakeSaleDataServiceImpl extends MPJBaseServiceImpl<ApsGoodsForecastMakeSaleDataMapper, ApsGoodsForecastMakeSaleData> implements
    ApsGoodsForecastMakeSaleDataService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();


  public @Override ApsGoodsForecastMakeSaleDataQueryListRes queryList(ApsGoodsForecastMakeSaleDataQueryListReq req) {

    MPJLambdaWrapper<ApsGoodsForecastMakeSaleData> q = getWrapper(req.getData());
    List<ApsGoodsForecastMakeSaleData> list = this.list(q);

    List<ApsGoodsForecastMakeSaleDataDto> dataList = list.stream().map(t -> $.copy(t, ApsGoodsForecastMakeSaleDataDto.class)).collect(Collectors.toList());
    //  this.setName(dataList);
    ((ApsGoodsForecastMakeSaleDataServiceImpl) AopContext.currentProxy()).setName(dataList);

    return new ApsGoodsForecastMakeSaleDataQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<ApsGoodsForecastMakeSaleDataExportQueryPageListInfoRes> queryPageList(ApsGoodsForecastMakeSaleDataExportQueryPageListReq req) {

    DynamicsPage<ApsGoodsForecastMakeSaleData> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsGoodsForecastMakeSaleData> q = getWrapper(req.getData());
    List<ApsGoodsForecastMakeSaleDataExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsGoodsForecastMakeSaleData> list = this.page(page, q);
      IPage<ApsGoodsForecastMakeSaleDataExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsGoodsForecastMakeSaleDataExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsGoodsForecastMakeSaleDataExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<ApsGoodsForecastMakeSaleDataExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsGoodsForecastMakeSaleDataExportQueryPageListInfoRes.class);
    // this.setName(listInfoRes);
    ((ApsGoodsForecastMakeSaleDataServiceImpl) AopContext.currentProxy()).setName(listInfoRes);

    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装

  @SetUserName
  public @Override void setName(List<? extends ApsGoodsForecastMakeSaleDataDto> apsGoodsForecastMakeSaleDataDtoList) {

    if (CollUtil.isEmpty(apsGoodsForecastMakeSaleDataDtoList)) {
    }


  }


  private MPJLambdaWrapper<ApsGoodsForecastMakeSaleData> getWrapper(ApsGoodsForecastMakeSaleDataDto obj) {
    MPJLambdaWrapper<ApsGoodsForecastMakeSaleData> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(Objects.nonNull(obj.getGoodsId()), ApsGoodsForecastMakeSaleData::getGoodsId, obj.getGoodsId())
          .eq(Objects.nonNull(obj.getMakeMonthId()), ApsGoodsForecastMakeSaleData::getMakeMonthId, obj.getMakeMonthId())
          .eq(StringUtils.isNoneBlank(obj.getSaleConfigCode()), ApsGoodsForecastMakeSaleData::getSaleConfigCode, obj.getSaleConfigCode())
          .eq(Objects.nonNull(obj.getYear()), ApsGoodsForecastMakeSaleData::getYear, obj.getYear())
          .eq(Objects.nonNull(obj.getFactoryId()), ApsGoodsForecastMakeSaleData::getFactoryId, obj.getFactoryId())
          .eq(StringUtils.isNoneBlank(obj.getMonth()), ApsGoodsForecastMakeSaleData::getMonth, obj.getMonth())
          .eq(Objects.nonNull(obj.getMonths()), ApsGoodsForecastMakeSaleData::getMonths, obj.getMonths())

      ;
    }
    q.orderByDesc(ApsGoodsForecastMakeSaleData::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsGoodsForecastMakeSaleData> page) {

    ServiceComment.header(page, "ApsGoodsForecastMakeSaleDataService#queryPageList");

  }


}

