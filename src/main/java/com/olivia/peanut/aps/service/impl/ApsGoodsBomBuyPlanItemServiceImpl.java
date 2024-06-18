package com.olivia.peanut.aps.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.api.entity.apsGoodsBomBuyPlanItem.*;
import com.olivia.peanut.aps.mapper.ApsGoodsBomBuyPlanItemMapper;
import com.olivia.peanut.aps.model.ApsGoodsBomBuyPlanItem;
import com.olivia.peanut.aps.service.ApsGoodsBomBuyPlanItemService;
import com.olivia.peanut.portal.service.BaseTableHeaderService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import jakarta.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * BOM 购买清单(ApsGoodsBomBuyPlanItem)表服务实现类
 *
 * @author peanut
 * @since 2024-06-05 14:35:31
 */
@Service("apsGoodsBomBuyPlanItemService")
@Transactional
public class ApsGoodsBomBuyPlanItemServiceImpl extends MPJBaseServiceImpl<ApsGoodsBomBuyPlanItemMapper, ApsGoodsBomBuyPlanItem> implements ApsGoodsBomBuyPlanItemService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  @Resource
  BaseTableHeaderService tableHeaderService;


  public @Override ApsGoodsBomBuyPlanItemQueryListRes queryList(ApsGoodsBomBuyPlanItemQueryListReq req) {

    MPJLambdaWrapper<ApsGoodsBomBuyPlanItem> q = getWrapper(req.getData());
    List<ApsGoodsBomBuyPlanItem> list = this.list(q);

    List<ApsGoodsBomBuyPlanItemDto> dataList = list.stream().map(t -> $.copy(t, ApsGoodsBomBuyPlanItemDto.class)).collect(Collectors.toList());
    this.setName(dataList);
    return new ApsGoodsBomBuyPlanItemQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<ApsGoodsBomBuyPlanItemExportQueryPageListInfoRes> queryPageList(ApsGoodsBomBuyPlanItemExportQueryPageListReq req) {

    DynamicsPage<ApsGoodsBomBuyPlanItem> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsGoodsBomBuyPlanItem> q = getWrapper(req.getData());
    List<ApsGoodsBomBuyPlanItemExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsGoodsBomBuyPlanItem> list = this.page(page, q);
      IPage<ApsGoodsBomBuyPlanItemExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsGoodsBomBuyPlanItemExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsGoodsBomBuyPlanItemExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<ApsGoodsBomBuyPlanItemExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsGoodsBomBuyPlanItemExportQueryPageListInfoRes.class);
    this.setName(listInfoRes);
    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装

  public @Override void setName(List<? extends ApsGoodsBomBuyPlanItemDto> apsGoodsBomBuyPlanItemDtoList) {

    if (CollUtil.isEmpty(apsGoodsBomBuyPlanItemDtoList)) {
      return;
    }
    apsGoodsBomBuyPlanItemDtoList.sort(Comparator.comparing(ApsGoodsBomBuyPlanItemDto::getIsFollow).reversed());
  }


  private MPJLambdaWrapper<ApsGoodsBomBuyPlanItem> getWrapper(ApsGoodsBomBuyPlanItemDto obj) {
    MPJLambdaWrapper<ApsGoodsBomBuyPlanItem> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q

          .eq(Objects.nonNull(obj.getBuyPlanId()), ApsGoodsBomBuyPlanItem::getBuyPlanId, obj.getBuyPlanId())
          .eq(Objects.nonNull(obj.getBomId()), ApsGoodsBomBuyPlanItem::getBomId, obj.getBomId())
          .eq(StringUtils.isNoneBlank(obj.getBomCode()), ApsGoodsBomBuyPlanItem::getBomCode, obj.getBomCode())
          .eq(StringUtils.isNoneBlank(obj.getBomName()), ApsGoodsBomBuyPlanItem::getBomName, obj.getBomName())
          .eq(Objects.nonNull(obj.getIsFollow()), ApsGoodsBomBuyPlanItem::getIsFollow, obj.getIsFollow())
          .eq(Objects.nonNull(obj.getBomCostPrice()), ApsGoodsBomBuyPlanItem::getBomCostPrice, obj.getBomCostPrice())
          .eq(StringUtils.isNoneBlank(obj.getBomCostPriceUnit()), ApsGoodsBomBuyPlanItem::getBomCostPriceUnit, obj.getBomCostPriceUnit())
          .eq(Objects.nonNull(obj.getBomInventory()), ApsGoodsBomBuyPlanItem::getBomInventory, obj.getBomInventory())
          .eq(Objects.nonNull(obj.getBomBuyCount()), ApsGoodsBomBuyPlanItem::getBomBuyCount, obj.getBomBuyCount())

      ;
    }
    q.orderByDesc(ApsGoodsBomBuyPlanItem::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsGoodsBomBuyPlanItem> page) {

    tableHeaderService.listByBizKey(page, "ApsGoodsBomBuyPlanItemService#queryPageList");

  }


}

