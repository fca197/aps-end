package com.olivia.peanut.aps.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.api.entity.apsSchedulingIssueItem.*;
import com.olivia.peanut.aps.mapper.ApsSchedulingIssueItemMapper;
import com.olivia.peanut.aps.model.ApsSchedulingIssueItem;
import com.olivia.peanut.aps.model.ApsSchedulingVersionCapacity;
import com.olivia.peanut.aps.service.ApsSchedulingIssueItemService;
import com.olivia.peanut.aps.service.ApsSchedulingVersionCapacityService;
import com.olivia.peanut.base.service.BaseTableHeaderService;
import com.olivia.sdk.service.SetNameService;
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
 * 排产下发详情(ApsSchedulingIssueItem)表服务实现类
 *
 * @author peanut
 * @since 2024-07-20 13:55:55
 */
@Service("apsSchedulingIssueItemService")
@Transactional
public class ApsSchedulingIssueItemServiceImpl extends MPJBaseServiceImpl<ApsSchedulingIssueItemMapper, ApsSchedulingIssueItem> implements ApsSchedulingIssueItemService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  @Resource
  BaseTableHeaderService tableHeaderService;
  @Resource
  SetNameService setNameService;
  @Resource
  ApsSchedulingVersionCapacityService apsSchedulingVersionCapacityService;

  @Override
  @Transactional
  public ApsSchedulingIssueItemInsertRes save(ApsSchedulingIssueItemInsertReq req) {
    List<ApsSchedulingVersionCapacity> capacityList = this.apsSchedulingVersionCapacityService.list(new LambdaQueryWrapper<ApsSchedulingVersionCapacity>()
        .eq(ApsSchedulingVersionCapacity::getSchedulingVersionId, req.getSchedulingVersionId())
        .in(ApsSchedulingVersionCapacity::getCurrentDay, req.getScheduledDayList()));
    $.requireNonNullCanIgnoreException(capacityList, "排产数据为空");
    List<ApsSchedulingIssueItem> issueItemList = capacityList.stream().map(
        t -> new ApsSchedulingIssueItem().setSchedulingVersionId(req.getSchedulingVersionId())
            .setOrderId(t.getOrderId()).setCurrentDay(t.getCurrentDay()).setGoodsId(t.getGoodsId())
            .setOrderNo(t.getOrderNo())
            .setNumberIndex(t.getNumberIndex()).setFactoryId(t.getFactoryId())
    ).toList();
    this.remove(new LambdaUpdateWrapper<ApsSchedulingIssueItem>().in(ApsSchedulingIssueItem::getCurrentDay, req.getScheduledDayList()));
    this.saveBatch(issueItemList);

    return new ApsSchedulingIssueItemInsertRes().setCount(issueItemList.size());
  }

  public @Override ApsSchedulingIssueItemQueryListRes queryList(ApsSchedulingIssueItemQueryListReq req) {

    MPJLambdaWrapper<ApsSchedulingIssueItem> q = getWrapper(req.getData());
    List<ApsSchedulingIssueItem> list = this.list(q);

    List<ApsSchedulingIssueItemDto> dataList = list.stream().map(t -> $.copy(t, ApsSchedulingIssueItemDto.class)).collect(Collectors.toList());
    ((ApsSchedulingIssueItemService) AopContext.currentProxy()).setName(dataList);
    return new ApsSchedulingIssueItemQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<ApsSchedulingIssueItemExportQueryPageListInfoRes> queryPageList(ApsSchedulingIssueItemExportQueryPageListReq req) {

    DynamicsPage<ApsSchedulingIssueItem> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsSchedulingIssueItem> q = getWrapper(req.getData());
    List<ApsSchedulingIssueItemExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsSchedulingIssueItem> list = this.page(page, q);
      IPage<ApsSchedulingIssueItemExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsSchedulingIssueItemExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsSchedulingIssueItemExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<ApsSchedulingIssueItemExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsSchedulingIssueItemExportQueryPageListInfoRes.class);
    ((ApsSchedulingIssueItemService) AopContext.currentProxy()).setName(listInfoRes);

    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装

  public @Override void setName(List<? extends ApsSchedulingIssueItemDto> list) {

    //   setNameService.setName(list, SetNamePojoUtils.FACTORY, SetNamePojoUtils.OP_USER_NAME);

  }


  private MPJLambdaWrapper<ApsSchedulingIssueItem> getWrapper(ApsSchedulingIssueItemDto obj) {
    MPJLambdaWrapper<ApsSchedulingIssueItem> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(StringUtils.isNoneBlank(obj.getCurrentDay()), ApsSchedulingIssueItem::getCurrentDay, obj.getCurrentDay())
          .eq(Objects.nonNull(obj.getOrderId()), ApsSchedulingIssueItem::getOrderId, obj.getOrderId())
          .eq(Objects.nonNull(obj.getGoodsId()), ApsSchedulingIssueItem::getGoodsId, obj.getGoodsId())
          .eq(Objects.nonNull(obj.getNumberIndex()), ApsSchedulingIssueItem::getNumberIndex, obj.getNumberIndex())
          .eq(Objects.nonNull(obj.getFactoryId()), ApsSchedulingIssueItem::getFactoryId, obj.getFactoryId())

      ;
    }
    q.orderByDesc(ApsSchedulingIssueItem::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsSchedulingIssueItem> page) {

    tableHeaderService.listByBizKey(page, "ApsSchedulingIssueItemService#queryPageList");

  }


}

