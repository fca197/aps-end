package com.olivia.peanut.aps.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.api.entity.apsGoodsForecastMakeProjectData.*;
import com.olivia.peanut.aps.mapper.ApsGoodsForecastMakeProjectDataMapper;
import com.olivia.peanut.aps.model.ApsGoodsForecastMakeProjectData;
import com.olivia.peanut.aps.service.ApsGoodsForecastMakeProjectDataService;
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
 * (ApsGoodsForecastMakeProjectData)表服务实现类
 *
 * @author peanut
 * @since 2024-05-10 13:58:08
 */
@Service("apsGoodsForecastMakeProjectDataService")
@Transactional
public class ApsGoodsForecastMakeProjectDataServiceImpl extends MPJBaseServiceImpl<ApsGoodsForecastMakeProjectDataMapper, ApsGoodsForecastMakeProjectData> implements
    ApsGoodsForecastMakeProjectDataService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();


  public @Override ApsGoodsForecastMakeProjectDataQueryListRes queryList(ApsGoodsForecastMakeProjectDataQueryListReq req) {

    MPJLambdaWrapper<ApsGoodsForecastMakeProjectData> q = getWrapper(req.getData());
    List<ApsGoodsForecastMakeProjectData> list = this.list(q);

    List<ApsGoodsForecastMakeProjectDataDto> dataList = list.stream().map(t -> $.copy(t, ApsGoodsForecastMakeProjectDataDto.class)).collect(Collectors.toList());
    //  this.setName(dataList);

    ((ApsGoodsForecastMakeProjectDataServiceImpl) AopContext.currentProxy()).setName(dataList);
    return new ApsGoodsForecastMakeProjectDataQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<ApsGoodsForecastMakeProjectDataExportQueryPageListInfoRes> queryPageList(ApsGoodsForecastMakeProjectDataExportQueryPageListReq req) {

    DynamicsPage<ApsGoodsForecastMakeProjectData> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsGoodsForecastMakeProjectData> q = getWrapper(req.getData());
    List<ApsGoodsForecastMakeProjectDataExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsGoodsForecastMakeProjectData> list = this.page(page, q);
      IPage<ApsGoodsForecastMakeProjectDataExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsGoodsForecastMakeProjectDataExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsGoodsForecastMakeProjectDataExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<ApsGoodsForecastMakeProjectDataExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsGoodsForecastMakeProjectDataExportQueryPageListInfoRes.class);
    // this.setName(listInfoRes);
    ((ApsGoodsForecastMakeProjectDataServiceImpl) AopContext.currentProxy()).setName(listInfoRes);

    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装

  @SetUserName
  public @Override void setName(List<? extends ApsGoodsForecastMakeProjectDataDto> apsGoodsForecastMakeProjectDataDtoList) {

    if (CollUtil.isEmpty(apsGoodsForecastMakeProjectDataDtoList)) {
    }


  }


  private MPJLambdaWrapper<ApsGoodsForecastMakeProjectData> getWrapper(ApsGoodsForecastMakeProjectDataDto obj) {
    MPJLambdaWrapper<ApsGoodsForecastMakeProjectData> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(Objects.nonNull(obj.getGoodsId()), ApsGoodsForecastMakeProjectData::getGoodsId, obj.getGoodsId())
          .eq(Objects.nonNull(obj.getMakeMonthId()), ApsGoodsForecastMakeProjectData::getMakeMonthId, obj.getMakeMonthId())
          .eq(StringUtils.isNoneBlank(obj.getProjectConfigCode()), ApsGoodsForecastMakeProjectData::getProjectConfigCode, obj.getProjectConfigCode())
          .eq(Objects.nonNull(obj.getYear()), ApsGoodsForecastMakeProjectData::getYear, obj.getYear())

          .eq(Objects.nonNull(obj.getFactoryId()), ApsGoodsForecastMakeProjectData::getFactoryId, obj.getFactoryId())

      ;
    }
    q.orderByDesc(ApsGoodsForecastMakeProjectData::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsGoodsForecastMakeProjectData> page) {

    ServiceComment.header(page, "ApsGoodsForecastMakeProjectDataService#queryPageList");

  }


}

