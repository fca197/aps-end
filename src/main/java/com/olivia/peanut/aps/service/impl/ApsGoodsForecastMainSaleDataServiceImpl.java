package com.olivia.peanut.aps.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.api.entity.apsGoodsForecastMainSaleData.*;
import com.olivia.peanut.aps.mapper.ApsGoodsForecastMainSaleDataMapper;
import com.olivia.peanut.aps.model.ApsGoodsForecastMainSaleData;
import com.olivia.peanut.aps.service.ApsGoodsForecastMainSaleDataService;
import com.olivia.sdk.ann.SetUserName;
import com.olivia.sdk.comment.ServiceComment;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * (ApsGoodsForecastMainSaleData)表服务实现类
 *
 * @author peanut
 * @since 2024-04-02 09:42:28
 */
@Service("apsGoodsForecastMainSaleDataService")
@Transactional
public class ApsGoodsForecastMainSaleDataServiceImpl extends MPJBaseServiceImpl<ApsGoodsForecastMainSaleDataMapper, ApsGoodsForecastMainSaleData> implements
    ApsGoodsForecastMainSaleDataService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();


  public @Override ApsGoodsForecastMainSaleDataQueryListRes queryList(ApsGoodsForecastMainSaleDataQueryListReq req) {

    MPJLambdaWrapper<ApsGoodsForecastMainSaleData> q = getWrapper(req.getData());
    List<ApsGoodsForecastMainSaleData> list = this.list(q);

    List<ApsGoodsForecastMainSaleDataDto> dataList = list.stream().map(t -> $.copy(t, ApsGoodsForecastMainSaleDataDto.class)).collect(Collectors.toList());

    return new ApsGoodsForecastMainSaleDataQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<ApsGoodsForecastMainSaleDataExportQueryPageListInfoRes> queryPageList(ApsGoodsForecastMainSaleDataExportQueryPageListReq req) {

    DynamicsPage<ApsGoodsForecastMainSaleData> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsGoodsForecastMainSaleData> q = getWrapper(req.getData());
    List<ApsGoodsForecastMainSaleDataExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsGoodsForecastMainSaleData> list = this.page(page, q);
      IPage<ApsGoodsForecastMainSaleDataExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsGoodsForecastMainSaleDataExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsGoodsForecastMainSaleDataExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<ApsGoodsForecastMainSaleDataExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsGoodsForecastMainSaleDataExportQueryPageListInfoRes.class);

    return DynamicsPage.init(page, listInfoRes);
  }


  @SetUserName
  public @Override void setName(List<? extends ApsGoodsForecastMainSaleDataDto> apsGoodsForecastMainSaleDataDtoList) {

  }

  // 以下为私有对象封装


  private MPJLambdaWrapper<ApsGoodsForecastMainSaleData> getWrapper(ApsGoodsForecastMainSaleDataDto obj) {
    MPJLambdaWrapper<ApsGoodsForecastMainSaleData> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(Objects.nonNull(obj.getGoodsId()), ApsGoodsForecastMainSaleData::getGoodsId, obj.getGoodsId())
          .eq(StringUtils.isNoneBlank(obj.getSaleConfigCode()), ApsGoodsForecastMainSaleData::getSaleConfigCode, obj.getSaleConfigCode())
          .eq(Objects.nonNull(obj.getYear()), ApsGoodsForecastMainSaleData::getYear, obj.getYear())
      ;
    }
    q.orderByDesc(ApsGoodsForecastMainSaleData::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsGoodsForecastMainSaleData> page) {

    ServiceComment.header(page, "ApsGoodsForecastMainSaleDataService#queryPageList");

  }


}

