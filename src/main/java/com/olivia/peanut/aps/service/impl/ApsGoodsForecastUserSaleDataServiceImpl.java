package com.olivia.peanut.aps.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.api.entity.apsGoodsForecastUserSaleData.*;
import com.olivia.peanut.aps.mapper.ApsGoodsForecastUserSaleDataMapper;
import com.olivia.peanut.aps.model.ApsGoodsForecastUserSaleData;
import com.olivia.peanut.aps.service.ApsGoodsForecastUserSaleDataService;
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
 * (ApsGoodsForecastUserSaleData)表服务实现类
 *
 * @author peanut
 * @since 2024-03-30 18:29:07
 */
@Service("apsGoodsForecastUserSaleDataService")
@Transactional
public class ApsGoodsForecastUserSaleDataServiceImpl extends MPJBaseServiceImpl<ApsGoodsForecastUserSaleDataMapper, ApsGoodsForecastUserSaleData> implements
    ApsGoodsForecastUserSaleDataService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();


  public @Override ApsGoodsForecastUserSaleDataQueryListRes queryList(ApsGoodsForecastUserSaleDataQueryListReq req) {

    MPJLambdaWrapper<ApsGoodsForecastUserSaleData> q = getWrapper(req.getData());
    List<ApsGoodsForecastUserSaleData> list = this.list(q);

    List<ApsGoodsForecastUserSaleDataDto> dataList = list.stream().map(t -> $.copy(t, ApsGoodsForecastUserSaleDataDto.class)).collect(Collectors.toList());

    return new ApsGoodsForecastUserSaleDataQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<ApsGoodsForecastUserSaleDataExportQueryPageListInfoRes> queryPageList(ApsGoodsForecastUserSaleDataExportQueryPageListReq req) {

    DynamicsPage<ApsGoodsForecastUserSaleData> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsGoodsForecastUserSaleData> q = getWrapper(req.getData());
    List<ApsGoodsForecastUserSaleDataExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsGoodsForecastUserSaleData> list = this.page(page, q);
      IPage<ApsGoodsForecastUserSaleDataExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsGoodsForecastUserSaleDataExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsGoodsForecastUserSaleDataExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<ApsGoodsForecastUserSaleDataExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsGoodsForecastUserSaleDataExportQueryPageListInfoRes.class);

    return DynamicsPage.init(page, listInfoRes);
  }


  @SetUserName
  public @Override void setName(List<? extends ApsGoodsForecastUserSaleDataDto> apsGoodsForecastUserSaleDataDtoList) {

  }

  // 以下为私有对象封装


  private MPJLambdaWrapper<ApsGoodsForecastUserSaleData> getWrapper(ApsGoodsForecastUserSaleDataDto obj) {
    MPJLambdaWrapper<ApsGoodsForecastUserSaleData> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(Objects.nonNull(obj.getForecastId()), ApsGoodsForecastUserSaleData::getForecastId, obj.getForecastId())
          .eq(Objects.nonNull(obj.getSaleConfigId()), ApsGoodsForecastUserSaleData::getSaleConfigId, obj.getSaleConfigId())
          .eq(Objects.nonNull(obj.getYear()), ApsGoodsForecastUserSaleData::getYear, obj.getYear())

      ;
    }
    q.orderByDesc(ApsGoodsForecastUserSaleData::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsGoodsForecastUserSaleData> page) {

    ServiceComment.header(page, "ApsGoodsForecastUserSaleDataService#queryPageList");

  }


}

