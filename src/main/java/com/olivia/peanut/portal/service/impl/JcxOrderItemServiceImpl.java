package com.olivia.peanut.portal.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.portal.api.entity.jcxOrderItem.*;
import com.olivia.peanut.portal.mapper.JcxOrderItemMapper;
import com.olivia.peanut.portal.model.JcxOrderItem;
import com.olivia.peanut.portal.service.JcxOrderItemService;
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

/**
 * (JcxOrderItem)表服务实现类
 *
 * @author peanut
 * @since 2024-03-22 10:38:07
 */
@Service("jcxOrderItemService")
@Transactional
public class JcxOrderItemServiceImpl extends MPJBaseServiceImpl<JcxOrderItemMapper, JcxOrderItem> implements JcxOrderItemService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  public @Override JcxOrderItemQueryListRes queryList(JcxOrderItemQueryListReq req) {

    MPJLambdaWrapper<JcxOrderItem> q = getWrapper(req.getData());
    List<JcxOrderItem> list = this.list(q);

    List<JcxOrderItemDto> dataList = list.stream().map(t -> $.copy(t, JcxOrderItemDto.class)).collect(Collectors.toList());

    return new JcxOrderItemQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<JcxOrderItemExportQueryPageListInfoRes> queryPageList(JcxOrderItemExportQueryPageListReq req) {

    DynamicsPage<JcxOrderItem> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<JcxOrderItem> q = getWrapper(req.getData());
    List<JcxOrderItemExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<JcxOrderItem> list = this.page(page, q);
      IPage<JcxOrderItemExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, JcxOrderItemExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), JcxOrderItemExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作
    List<JcxOrderItemExportQueryPageListInfoRes> listInfoRes = $.copyList(records, JcxOrderItemExportQueryPageListInfoRes.class);

    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装


  private MPJLambdaWrapper<JcxOrderItem> getWrapper(JcxOrderItemDto obj) {
    MPJLambdaWrapper<JcxOrderItem> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(Objects.nonNull(obj.getId()), JcxOrderItem::getId, obj.getId())
          .eq(Objects.nonNull(obj.getTenantId()), JcxOrderItem::getTenantId, obj.getTenantId())
          .eq(Objects.nonNull(obj.getOrderId()), JcxOrderItem::getOrderId, obj.getOrderId())
          .eq(StringUtils.isNoneBlank(obj.getOrderRemark()), JcxOrderItem::getOrderRemark, obj.getOrderRemark())
          .eq(Objects.nonNull(obj.getGoodsId()), JcxOrderItem::getGoodsId, obj.getGoodsId())
          .eq(Objects.nonNull(obj.getGoodsCount()), JcxOrderItem::getGoodsCount, obj.getGoodsCount())
          .eq(Objects.nonNull(obj.getGoodsCostPrice()), JcxOrderItem::getGoodsCostPrice, obj.getGoodsCostPrice())
          .eq(Objects.nonNull(obj.getGoodsSalePrice()), JcxOrderItem::getGoodsSalePrice, obj.getGoodsSalePrice())
          .eq(Objects.nonNull(obj.getGoodsGrossProfit()), JcxOrderItem::getGoodsGrossProfit, obj.getGoodsGrossProfit())
          .eq(Objects.nonNull(obj.getGoodsNetProfit()), JcxOrderItem::getGoodsNetProfit, obj.getGoodsNetProfit())
          .eq(Objects.nonNull(obj.getCreateTime()), JcxOrderItem::getCreateTime, obj.getCreateTime())
          .eq(Objects.nonNull(obj.getUpdateTime()), JcxOrderItem::getUpdateTime, obj.getUpdateTime())
          .orderByDesc(JcxOrderItem::getId)
      ;
    }

    return q;

  }

  private void setQueryListHeader(DynamicsPage<JcxOrderItem> page) {
    page
        .addHeader("orderId", "订单编号")
        .addHeader("orderRemark", "订单备注")
        .addHeader("goodsId", "$column.comment")
        .addHeader("goodsCount", "售出数量")
        .addHeader("goodsCostPrice", "成本价")
        .addHeader("goodsSalePrice", "售卖价")
        .addHeader("goodsGrossProfit", "毛利(分)")
        .addHeader("goodsNetProfit", "纯利")
        .addHeader("createTime", "创建时间")
    ;
  }


}

