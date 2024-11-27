package com.olivia.peanut.aps.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.api.entity.apsGoodsForecastMainGoodsData.*;
import com.olivia.peanut.aps.mapper.ApsGoodsForecastMainGoodsDataMapper;
import com.olivia.peanut.aps.model.ApsGoodsForecastMainGoodsData;
import com.olivia.peanut.aps.service.ApsGoodsForecastMainGoodsDataService;
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
 * (ApsGoodsForecastMainGoodsData)表服务实现类
 *
 * @author peanut
 * @since 2024-04-02 13:44:29
 */
@Service("apsGoodsForecastMainGoodsDataService")
@Transactional
public class ApsGoodsForecastMainGoodsDataServiceImpl extends MPJBaseServiceImpl<ApsGoodsForecastMainGoodsDataMapper, ApsGoodsForecastMainGoodsData> implements
    ApsGoodsForecastMainGoodsDataService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();


  public @Override ApsGoodsForecastMainGoodsDataQueryListRes queryList(ApsGoodsForecastMainGoodsDataQueryListReq req) {

    MPJLambdaWrapper<ApsGoodsForecastMainGoodsData> q = getWrapper(req.getData());
    List<ApsGoodsForecastMainGoodsData> list = this.list(q);

    List<ApsGoodsForecastMainGoodsDataDto> dataList = list.stream().map(t -> $.copy(t, ApsGoodsForecastMainGoodsDataDto.class)).collect(Collectors.toList());

    return new ApsGoodsForecastMainGoodsDataQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<ApsGoodsForecastMainGoodsDataExportQueryPageListInfoRes> queryPageList(ApsGoodsForecastMainGoodsDataExportQueryPageListReq req) {

    DynamicsPage<ApsGoodsForecastMainGoodsData> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsGoodsForecastMainGoodsData> q = getWrapper(req.getData());
    List<ApsGoodsForecastMainGoodsDataExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsGoodsForecastMainGoodsData> list = this.page(page, q);
      IPage<ApsGoodsForecastMainGoodsDataExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsGoodsForecastMainGoodsDataExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsGoodsForecastMainGoodsDataExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<ApsGoodsForecastMainGoodsDataExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsGoodsForecastMainGoodsDataExportQueryPageListInfoRes.class);

    return DynamicsPage.init(page, listInfoRes);
  }


  @SetUserName
  public @Override void setName(List<? extends ApsGoodsForecastMainGoodsDataDto> apsGoodsForecastMainGoodsDataDtoList) {

  }

  // 以下为私有对象封装


  private MPJLambdaWrapper<ApsGoodsForecastMainGoodsData> getWrapper(ApsGoodsForecastMainGoodsDataDto obj) {
    MPJLambdaWrapper<ApsGoodsForecastMainGoodsData> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(Objects.nonNull(obj.getGoodsId()), ApsGoodsForecastMainGoodsData::getGoodsId, obj.getGoodsId())
          .eq(Objects.nonNull(obj.getYear()), ApsGoodsForecastMainGoodsData::getYear, obj.getYear())
          .eq(Objects.nonNull(obj.getMonth01()), ApsGoodsForecastMainGoodsData::getMonth01, obj.getMonth01())
          .eq(Objects.nonNull(obj.getMonth02()), ApsGoodsForecastMainGoodsData::getMonth02, obj.getMonth02())
          .eq(Objects.nonNull(obj.getMonth03()), ApsGoodsForecastMainGoodsData::getMonth03, obj.getMonth03())
          .eq(Objects.nonNull(obj.getMonth04()), ApsGoodsForecastMainGoodsData::getMonth04, obj.getMonth04())
          .eq(Objects.nonNull(obj.getMonth05()), ApsGoodsForecastMainGoodsData::getMonth05, obj.getMonth05())
          .eq(Objects.nonNull(obj.getMonth06()), ApsGoodsForecastMainGoodsData::getMonth06, obj.getMonth06())
          .eq(Objects.nonNull(obj.getMonth07()), ApsGoodsForecastMainGoodsData::getMonth07, obj.getMonth07())
          .eq(Objects.nonNull(obj.getMonth08()), ApsGoodsForecastMainGoodsData::getMonth08, obj.getMonth08())
          .eq(Objects.nonNull(obj.getMonth09()), ApsGoodsForecastMainGoodsData::getMonth09, obj.getMonth09())
          .eq(Objects.nonNull(obj.getMonth10()), ApsGoodsForecastMainGoodsData::getMonth10, obj.getMonth10())
          .eq(Objects.nonNull(obj.getMonth11()), ApsGoodsForecastMainGoodsData::getMonth11, obj.getMonth11())
          .eq(Objects.nonNull(obj.getMonth12()), ApsGoodsForecastMainGoodsData::getMonth12, obj.getMonth12())
          .eq(Objects.nonNull(obj.getForecastMainId()), ApsGoodsForecastMainGoodsData::getForecastMainId, obj.getForecastMainId())

      ;
    }
    q.orderByDesc(ApsGoodsForecastMainGoodsData::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsGoodsForecastMainGoodsData> page) {

    ServiceComment.header(page, "ApsGoodsForecastMainGoodsDataService#queryPageList");

  }


}

