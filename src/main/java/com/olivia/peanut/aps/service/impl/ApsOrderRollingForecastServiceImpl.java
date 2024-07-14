package com.olivia.peanut.aps.service.impl;

import org.springframework.aop.framework.AopContext;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import jakarta.annotation.Resource;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.olivia.peanut.aps.mapper.ApsOrderRollingForecastMapper;
import com.olivia.peanut.aps.model.ApsOrderRollingForecast;
import com.olivia.peanut.aps.service.ApsOrderRollingForecastService;
import cn.hutool.core.collection.CollUtil;
//import com.olivia.peanut.aps.service.BaseTableHeaderService;
import com.olivia.peanut.portal.service.BaseTableHeaderService;
import com.olivia.peanut.aps.api.entity.apsOrderRollingForecast.*;

/**
 * 滚动预测(ApsOrderRollingForecast)表服务实现类
 *
 * @author peanut
 * @since 2024-07-14 19:43:51
 */
@Service("apsOrderRollingForecastService")
@Transactional
public class ApsOrderRollingForecastServiceImpl extends MPJBaseServiceImpl<ApsOrderRollingForecastMapper, ApsOrderRollingForecast> implements ApsOrderRollingForecastService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  @Resource
  BaseTableHeaderService tableHeaderService;


  public @Override ApsOrderRollingForecastQueryListRes queryList(ApsOrderRollingForecastQueryListReq req) {

    MPJLambdaWrapper<ApsOrderRollingForecast> q = getWrapper(req.getData());
    List<ApsOrderRollingForecast> list = this.list(q);

    List<ApsOrderRollingForecastDto> dataList = list.stream().map(t -> $.copy(t, ApsOrderRollingForecastDto.class)).collect(Collectors.toList());
    ((ApsOrderRollingForecastService) AopContext.currentProxy()).setName(dataList);
    return new ApsOrderRollingForecastQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<ApsOrderRollingForecastExportQueryPageListInfoRes> queryPageList(ApsOrderRollingForecastExportQueryPageListReq req) {

    DynamicsPage<ApsOrderRollingForecast> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsOrderRollingForecast> q = getWrapper(req.getData());
    List<ApsOrderRollingForecastExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsOrderRollingForecast> list = this.page(page, q);
      IPage<ApsOrderRollingForecastExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsOrderRollingForecastExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsOrderRollingForecastExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<ApsOrderRollingForecastExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsOrderRollingForecastExportQueryPageListInfoRes.class);
    ((ApsOrderRollingForecastService) AopContext.currentProxy()).setName(listInfoRes);

    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装

  public @Override void setName(List<? extends ApsOrderRollingForecastDto> apsOrderRollingForecastDtoList) {

    if (CollUtil.isEmpty(apsOrderRollingForecastDtoList)) {
    }


  }


  private MPJLambdaWrapper<ApsOrderRollingForecast> getWrapper(ApsOrderRollingForecastDto obj) {
    MPJLambdaWrapper<ApsOrderRollingForecast> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(StringUtils.isNoneBlank(obj.getRollCode()), ApsOrderRollingForecast::getRollCode, obj.getRollCode())
          .eq(StringUtils.isNoneBlank(obj.getRollName()), ApsOrderRollingForecast::getRollName, obj.getRollName())
          .eq(Objects.nonNull(obj.getBeginTime()), ApsOrderRollingForecast::getBeginTime, obj.getBeginTime())
          .eq(Objects.nonNull(obj.getEndTime()), ApsOrderRollingForecast::getEndTime, obj.getEndTime())

      ;
    }
    q.orderByDesc(ApsOrderRollingForecast::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsOrderRollingForecast> page) {

    tableHeaderService.listByBizKey(page, "ApsOrderRollingForecastService#queryPageList");

  }


}

