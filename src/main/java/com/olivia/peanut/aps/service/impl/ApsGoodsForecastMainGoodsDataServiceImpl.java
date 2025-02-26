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
import com.olivia.sdk.utils.LambdaQueryUtil;
import com.olivia.sdk.utils.Str;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
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


  @SuppressWarnings(Str.UN_CHECKED)
  private MPJLambdaWrapper<ApsGoodsForecastMainGoodsData> getWrapper(ApsGoodsForecastMainGoodsDataDto obj) {
    MPJLambdaWrapper<ApsGoodsForecastMainGoodsData> q = new MPJLambdaWrapper<>();
    LambdaQueryUtil.lambdaQueryWrapper(q, obj, ApsGoodsForecastMainGoodsData.class, ApsGoodsForecastMainGoodsData::getGoodsId, ApsGoodsForecastMainGoodsData::getYear, ApsGoodsForecastMainGoodsData::getForecastMainId);
    q.orderByDesc(ApsGoodsForecastMainGoodsData::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsGoodsForecastMainGoodsData> page) {

    ServiceComment.header(page, "ApsGoodsForecastMainGoodsDataService#queryPageList");

  }


}

