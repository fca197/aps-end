package com.olivia.peanut.aps.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.api.entity.apsGoodsForecastMainMakeSaleData.*;
import com.olivia.peanut.aps.mapper.ApsGoodsForecastMainMakeSaleDataMapper;
import com.olivia.peanut.aps.model.ApsGoodsForecastMainMakeSaleData;
import com.olivia.peanut.aps.service.ApsGoodsForecastMainMakeSaleDataService;
import com.olivia.sdk.ann.SetUserName;
import com.olivia.sdk.comment.ServiceComment;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.LambdaQueryUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.olivia.sdk.utils.Str.UN_CHECKED;

/**
 * (ApsGoodsForecastMainMakeSaleData)表服务实现类
 *
 * @author peanut
 * @since 2024-04-08 09:52:51
 */
@Service("apsGoodsForecastMainMakeSaleDataService")
@Transactional
public class ApsGoodsForecastMainMakeSaleDataServiceImpl extends MPJBaseServiceImpl<ApsGoodsForecastMainMakeSaleDataMapper, ApsGoodsForecastMainMakeSaleData> implements
    ApsGoodsForecastMainMakeSaleDataService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();


  public @Override ApsGoodsForecastMainMakeSaleDataQueryListRes queryList(ApsGoodsForecastMainMakeSaleDataQueryListReq req) {

    MPJLambdaWrapper<ApsGoodsForecastMainMakeSaleData> q = getWrapper(req.getData());
    List<ApsGoodsForecastMainMakeSaleData> list = this.list(q);

    List<ApsGoodsForecastMainMakeSaleDataDto> dataList = list.stream().map(t -> $.copy(t, ApsGoodsForecastMainMakeSaleDataDto.class)).collect(Collectors.toList());
    //  this.setName(dataList);

//    ((ApsGoodsForecastMainMakeSaleDataServiceImpl) AopContext.currentProxy()).setName(dataList);
    return new ApsGoodsForecastMainMakeSaleDataQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<ApsGoodsForecastMainMakeSaleDataExportQueryPageListInfoRes> queryPageList(ApsGoodsForecastMainMakeSaleDataExportQueryPageListReq req) {

    DynamicsPage<ApsGoodsForecastMainMakeSaleData> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsGoodsForecastMainMakeSaleData> q = getWrapper(req.getData());
    List<ApsGoodsForecastMainMakeSaleDataExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsGoodsForecastMainMakeSaleData> list = this.page(page, q);
      IPage<ApsGoodsForecastMainMakeSaleDataExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsGoodsForecastMainMakeSaleDataExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsGoodsForecastMainMakeSaleDataExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<ApsGoodsForecastMainMakeSaleDataExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsGoodsForecastMainMakeSaleDataExportQueryPageListInfoRes.class);
    // this.setName(listInfoRes);
//    ((ApsGoodsForecastMainMakeSaleDataServiceImpl) AopContext.currentProxy()).setName(listInfoRes);

    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装

  @SetUserName
  public @Override void setName(List<? extends ApsGoodsForecastMainMakeSaleDataDto> apsGoodsForecastMainMakeSaleDataDtoList) {

    if (CollUtil.isEmpty(apsGoodsForecastMainMakeSaleDataDtoList)) {
    }


  }


  @SuppressWarnings(UN_CHECKED)
  private MPJLambdaWrapper<ApsGoodsForecastMainMakeSaleData> getWrapper(ApsGoodsForecastMainMakeSaleDataDto obj) {
    MPJLambdaWrapper<ApsGoodsForecastMainMakeSaleData> q = new MPJLambdaWrapper<>();
    LambdaQueryUtil.lambdaQueryWrapper(q, obj, ApsGoodsForecastMainMakeSaleData.class, ApsGoodsForecastMainMakeSaleData::getGoodsId, //
        ApsGoodsForecastMainMakeSaleData::getMainMakeId, ApsGoodsForecastMainMakeSaleData::getYear,//
        ApsGoodsForecastMainMakeSaleData::getSaleConfigCode, ApsGoodsForecastMainMakeSaleData::getFactoryId);
    q.orderByDesc(ApsGoodsForecastMainMakeSaleData::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsGoodsForecastMainMakeSaleData> page) {

    ServiceComment.header(page, "ApsGoodsForecastMainMakeSaleDataService#queryPageList");

  }


}

