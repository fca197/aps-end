package com.olivia.peanut.aps.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.api.entity.apsRollingForecastOrderItem.*;
import com.olivia.peanut.aps.mapper.ApsRollingForecastOrderItemMapper;
import com.olivia.peanut.aps.model.ApsRollingForecastOrderItem;
import com.olivia.peanut.aps.service.ApsRollingForecastOrderItemService;
import com.olivia.peanut.base.service.BaseTableHeaderService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import jakarta.annotation.Resource;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 滚动预测订单节点表(ApsRollingForecastOrderItem)表服务实现类
 *
 * @author peanut
 * @since 2024-07-16 10:31:19
 */
@Service("apsRollingForecastOrderItemService")
@Transactional
public class ApsRollingForecastOrderItemServiceImpl extends MPJBaseServiceImpl<ApsRollingForecastOrderItemMapper, ApsRollingForecastOrderItem> implements
    ApsRollingForecastOrderItemService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  @Resource
  BaseTableHeaderService tableHeaderService;


  public @Override ApsRollingForecastOrderItemQueryListRes queryList(ApsRollingForecastOrderItemQueryListReq req) {

    MPJLambdaWrapper<ApsRollingForecastOrderItem> q = getWrapper(req.getData());
    List<ApsRollingForecastOrderItem> list = this.list(q);

    List<ApsRollingForecastOrderItemDto> dataList = list.stream().map(t -> $.copy(t, ApsRollingForecastOrderItemDto.class)).collect(Collectors.toList());
    ((ApsRollingForecastOrderItemService) AopContext.currentProxy()).setName(dataList);
    return new ApsRollingForecastOrderItemQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<ApsRollingForecastOrderItemExportQueryPageListInfoRes> queryPageList(ApsRollingForecastOrderItemExportQueryPageListReq req) {

    DynamicsPage<ApsRollingForecastOrderItem> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsRollingForecastOrderItem> q = getWrapper(req.getData());
    List<ApsRollingForecastOrderItemExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsRollingForecastOrderItem> list = this.page(page, q);
      IPage<ApsRollingForecastOrderItemExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsRollingForecastOrderItemExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsRollingForecastOrderItemExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<ApsRollingForecastOrderItemExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsRollingForecastOrderItemExportQueryPageListInfoRes.class);
    ((ApsRollingForecastOrderItemService) AopContext.currentProxy()).setName(listInfoRes);

    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装

  public @Override void setName(List<? extends ApsRollingForecastOrderItemDto> apsRollingForecastOrderItemDtoList) {

    if (CollUtil.isEmpty(apsRollingForecastOrderItemDtoList)) {
    }


  }


  private MPJLambdaWrapper<ApsRollingForecastOrderItem> getWrapper(ApsRollingForecastOrderItemDto obj) {
    MPJLambdaWrapper<ApsRollingForecastOrderItem> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(Objects.nonNull(obj.getForecastId()), ApsRollingForecastOrderItem::getForecastId, obj.getForecastId())
          .eq(Objects.nonNull(obj.getFactoryId()), ApsRollingForecastOrderItem::getFactoryId, obj.getFactoryId())
          .eq(Objects.nonNull(obj.getOrderId()), ApsRollingForecastOrderItem::getOrderId, obj.getOrderId())
          .eq(Objects.nonNull(obj.getGoodsStatusId()), ApsRollingForecastOrderItem::getGoodsStatusId, obj.getGoodsStatusId())
          .eq(Objects.nonNull(obj.getStatusBeginDate()), ApsRollingForecastOrderItem::getStatusBeginDate, obj.getStatusBeginDate())

      ;
    }
    q.orderByDesc(ApsRollingForecastOrderItem::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsRollingForecastOrderItem> page) {

    tableHeaderService.listByBizKey(page, "ApsRollingForecastOrderItemService#queryPageList");

  }


}

