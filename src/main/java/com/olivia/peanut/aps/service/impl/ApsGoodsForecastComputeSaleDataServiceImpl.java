package com.olivia.peanut.aps.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.api.entity.apsGoodsForecastComputeSaleData.*;
import com.olivia.peanut.aps.mapper.ApsGoodsForecastComputeSaleDataMapper;
import com.olivia.peanut.aps.model.ApsGoodsForecastComputeSaleData;
import com.olivia.peanut.aps.service.ApsGoodsForecastComputeSaleDataService;
import com.olivia.sdk.ann.SetUserName;
import com.olivia.sdk.comment.ServiceComment;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * (ApsGoodsForecastComputeSaleData)表服务实现类
 *
 * @author peanut
 * @since 2024-03-31 20:58:35
 */
@Service("apsGoodsForecastComputeSaleDataService")
@Transactional
public class ApsGoodsForecastComputeSaleDataServiceImpl extends MPJBaseServiceImpl<ApsGoodsForecastComputeSaleDataMapper, ApsGoodsForecastComputeSaleData> implements
    ApsGoodsForecastComputeSaleDataService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();


  public @Override ApsGoodsForecastComputeSaleDataQueryListRes queryList(ApsGoodsForecastComputeSaleDataQueryListReq req) {

    MPJLambdaWrapper<ApsGoodsForecastComputeSaleData> q = getWrapper(req.getData());
    List<ApsGoodsForecastComputeSaleData> list = this.list(q);

    List<ApsGoodsForecastComputeSaleDataDto> dataList = list.stream().map(t -> $.copy(t, ApsGoodsForecastComputeSaleDataDto.class)).collect(Collectors.toList());

    return new ApsGoodsForecastComputeSaleDataQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<ApsGoodsForecastComputeSaleDataExportQueryPageListInfoRes> queryPageList(ApsGoodsForecastComputeSaleDataExportQueryPageListReq req) {

    DynamicsPage<ApsGoodsForecastComputeSaleData> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsGoodsForecastComputeSaleData> q = getWrapper(req.getData());
    List<ApsGoodsForecastComputeSaleDataExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsGoodsForecastComputeSaleData> list = this.page(page, q);
      IPage<ApsGoodsForecastComputeSaleDataExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsGoodsForecastComputeSaleDataExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsGoodsForecastComputeSaleDataExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<ApsGoodsForecastComputeSaleDataExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsGoodsForecastComputeSaleDataExportQueryPageListInfoRes.class);

    return DynamicsPage.init(page, listInfoRes);
  }


  @SetUserName
  public @Override void setName(List<? extends ApsGoodsForecastComputeSaleDataDto> apsGoodsForecastComputeSaleDataDtoList) {

  }

  // 以下为私有对象封装


  private MPJLambdaWrapper<ApsGoodsForecastComputeSaleData> getWrapper(ApsGoodsForecastComputeSaleDataDto obj) {
    MPJLambdaWrapper<ApsGoodsForecastComputeSaleData> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(Objects.nonNull(obj.getForecastId()), ApsGoodsForecastComputeSaleData::getForecastId, obj.getForecastId())
          .eq(Objects.nonNull(obj.getSaleConfigId()), ApsGoodsForecastComputeSaleData::getSaleConfigId, obj.getSaleConfigId())
          .eq(Objects.nonNull(obj.getYear()), ApsGoodsForecastComputeSaleData::getYear, obj.getYear())
          .eq(Objects.nonNull(obj.getMonth01()), ApsGoodsForecastComputeSaleData::getMonth01, obj.getMonth01())
          .eq(Objects.nonNull(obj.getMonth02()), ApsGoodsForecastComputeSaleData::getMonth02, obj.getMonth02())
          .eq(Objects.nonNull(obj.getMonth03()), ApsGoodsForecastComputeSaleData::getMonth03, obj.getMonth03())
          .eq(Objects.nonNull(obj.getMonth04()), ApsGoodsForecastComputeSaleData::getMonth04, obj.getMonth04())
          .eq(Objects.nonNull(obj.getMonth05()), ApsGoodsForecastComputeSaleData::getMonth05, obj.getMonth05())
          .eq(Objects.nonNull(obj.getMonth06()), ApsGoodsForecastComputeSaleData::getMonth06, obj.getMonth06())
          .eq(Objects.nonNull(obj.getMonth07()), ApsGoodsForecastComputeSaleData::getMonth07, obj.getMonth07())
          .eq(Objects.nonNull(obj.getMonth08()), ApsGoodsForecastComputeSaleData::getMonth08, obj.getMonth08())
          .eq(Objects.nonNull(obj.getMonth09()), ApsGoodsForecastComputeSaleData::getMonth09, obj.getMonth09())
          .eq(Objects.nonNull(obj.getMonth10()), ApsGoodsForecastComputeSaleData::getMonth10, obj.getMonth10())
          .eq(Objects.nonNull(obj.getMonth11()), ApsGoodsForecastComputeSaleData::getMonth11, obj.getMonth11())
          .eq(Objects.nonNull(obj.getMonth12()), ApsGoodsForecastComputeSaleData::getMonth12, obj.getMonth12())

      ;
    }
    q.orderByDesc(ApsGoodsForecastComputeSaleData::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsGoodsForecastComputeSaleData> page) {

    ServiceComment.header(page, "ApsGoodsForecastComputeSaleDataService#queryPageList");

  }


}

