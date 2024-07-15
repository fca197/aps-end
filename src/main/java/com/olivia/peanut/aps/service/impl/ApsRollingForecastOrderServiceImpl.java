package com.olivia.peanut.aps.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.api.entity.apsRollingForecastOrder.*;
import com.olivia.peanut.aps.mapper.ApsRollingForecastOrderMapper;
import com.olivia.peanut.aps.model.ApsRollingForecastOrder;
import com.olivia.peanut.aps.service.ApsRollingForecastOrderService;
import com.olivia.peanut.portal.service.BaseTableHeaderService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import jakarta.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 滚动预测(ApsRollingForecastOrder)表服务实现类
 *
 * @author peanut
 * @since 2024-07-14 20:22:28
 */
@Service("apsRollingForecastOrderService")
@Transactional
public class ApsRollingForecastOrderServiceImpl extends MPJBaseServiceImpl<ApsRollingForecastOrderMapper, ApsRollingForecastOrder> implements ApsRollingForecastOrderService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  @Resource
  BaseTableHeaderService tableHeaderService;


  public @Override ApsRollingForecastOrderQueryListRes queryList(ApsRollingForecastOrderQueryListReq req) {

    MPJLambdaWrapper<ApsRollingForecastOrder> q = getWrapper(req.getData());
    List<ApsRollingForecastOrder> list = this.list(q);

    List<ApsRollingForecastOrderDto> dataList = list.stream().map(t -> $.copy(t, ApsRollingForecastOrderDto.class)).collect(Collectors.toList());
    ((ApsRollingForecastOrderService) AopContext.currentProxy()).setName(dataList);
    return new ApsRollingForecastOrderQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<ApsRollingForecastOrderExportQueryPageListInfoRes> queryPageList(ApsRollingForecastOrderExportQueryPageListReq req) {

    DynamicsPage<ApsRollingForecastOrder> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsRollingForecastOrder> q = getWrapper(req.getData());
    List<ApsRollingForecastOrderExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsRollingForecastOrder> list = this.page(page, q);
      IPage<ApsRollingForecastOrderExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsRollingForecastOrderExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsRollingForecastOrderExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<ApsRollingForecastOrderExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsRollingForecastOrderExportQueryPageListInfoRes.class);
    ((ApsRollingForecastOrderService) AopContext.currentProxy()).setName(listInfoRes);

    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装

  public @Override void setName(List<? extends ApsRollingForecastOrderDto> apsRollingForecastOrderDtoList) {

    if (CollUtil.isEmpty(apsRollingForecastOrderDtoList)) {
    }


  }


  private MPJLambdaWrapper<ApsRollingForecastOrder> getWrapper(ApsRollingForecastOrderDto obj) {
    MPJLambdaWrapper<ApsRollingForecastOrder> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(StringUtils.isNoneBlank(obj.getRollCode()), ApsRollingForecastOrder::getRollCode, obj.getRollCode())
          .eq(StringUtils.isNoneBlank(obj.getRollName()), ApsRollingForecastOrder::getRollName, obj.getRollName())
          .eq(Objects.nonNull(obj.getBeginTime()), ApsRollingForecastOrder::getBeginTime, obj.getBeginTime())
          .eq(Objects.nonNull(obj.getEndTime()), ApsRollingForecastOrder::getEndTime, obj.getEndTime())

      ;
    }
    q.orderByDesc(ApsRollingForecastOrder::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsRollingForecastOrder> page) {

    tableHeaderService.listByBizKey(page, "ApsRollingForecastOrderService#queryPageList");

  }


}

