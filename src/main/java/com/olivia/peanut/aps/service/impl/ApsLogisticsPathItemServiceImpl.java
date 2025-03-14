package com.olivia.peanut.aps.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.api.entity.apsLogisticsPathItem.*;
import com.olivia.peanut.aps.mapper.ApsLogisticsPathItemMapper;
import com.olivia.peanut.aps.model.ApsLogisticsPathItem;
import com.olivia.peanut.aps.service.ApsLogisticsPathItemService;
import com.olivia.peanut.base.service.BaseTableHeaderService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import jakarta.annotation.Resource;
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
 * 物流路详情径表(ApsLogisticsPathItem)表服务实现类
 *
 * @author peanut
 * @since 2024-07-18 13:27:40
 */
@Service("apsLogisticsPathItemService")
@Transactional
public class ApsLogisticsPathItemServiceImpl extends MPJBaseServiceImpl<ApsLogisticsPathItemMapper, ApsLogisticsPathItem> implements ApsLogisticsPathItemService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  @Resource
  BaseTableHeaderService tableHeaderService;


  public @Override ApsLogisticsPathItemQueryListRes queryList(ApsLogisticsPathItemQueryListReq req) {

    MPJLambdaWrapper<ApsLogisticsPathItem> q = getWrapper(req.getData());
    List<ApsLogisticsPathItem> list = this.list(q);

    List<ApsLogisticsPathItemDto> dataList = list.stream().map(t -> $.copy(t, ApsLogisticsPathItemDto.class)).collect(Collectors.toList());
    ((ApsLogisticsPathItemService) AopContext.currentProxy()).setName(dataList);
    return new ApsLogisticsPathItemQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<ApsLogisticsPathItemExportQueryPageListInfoRes> queryPageList(ApsLogisticsPathItemExportQueryPageListReq req) {

    DynamicsPage<ApsLogisticsPathItem> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsLogisticsPathItem> q = getWrapper(req.getData());
    List<ApsLogisticsPathItemExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsLogisticsPathItem> list = this.page(page, q);
      IPage<ApsLogisticsPathItemExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsLogisticsPathItemExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsLogisticsPathItemExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<ApsLogisticsPathItemExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsLogisticsPathItemExportQueryPageListInfoRes.class);
    ((ApsLogisticsPathItemService) AopContext.currentProxy()).setName(listInfoRes);

    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装

  public @Override void setName(List<? extends ApsLogisticsPathItemDto> apsLogisticsPathItemDtoList) {

    if (CollUtil.isEmpty(apsLogisticsPathItemDtoList)) {
    }


  }


  private MPJLambdaWrapper<ApsLogisticsPathItem> getWrapper(ApsLogisticsPathItemDto obj) {
    MPJLambdaWrapper<ApsLogisticsPathItem> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(Objects.nonNull(obj.getLogisticsPathId()), ApsLogisticsPathItem::getLogisticsPathId, obj.getLogisticsPathId())
          .eq(StringUtils.isNoneBlank(obj.getProvinceCode()), ApsLogisticsPathItem::getProvinceCode, obj.getProvinceCode())
          .eq(StringUtils.isNoneBlank(obj.getProvinceName()), ApsLogisticsPathItem::getProvinceName, obj.getProvinceName())
          .eq(StringUtils.isNoneBlank(obj.getCityCode()), ApsLogisticsPathItem::getCityCode, obj.getCityCode())
          .eq(StringUtils.isNoneBlank(obj.getCityName()), ApsLogisticsPathItem::getCityName, obj.getCityName())
          .eq(Objects.nonNull(obj.getTransportDay()), ApsLogisticsPathItem::getTransportDay, obj.getTransportDay())
          .eq(Objects.nonNull(obj.getIsDefault()), ApsLogisticsPathItem::getIsDefault, obj.getIsDefault())
          .eq(Objects.nonNull(obj.getFactoryId()), ApsLogisticsPathItem::getFactoryId, obj.getFactoryId())

      ;
    }
    q.orderByDesc(ApsLogisticsPathItem::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsLogisticsPathItem> page) {

    tableHeaderService.listByBizKey(page, "ApsLogisticsPathItemService#queryPageList");

  }


}

