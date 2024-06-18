package com.olivia.peanut.portal.service.impl;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.portal.api.entity.jcxOrder.*;
import com.olivia.peanut.portal.api.entity.jcxOrder.JcxOrderInsertReq.GoodsInfo;
import com.olivia.peanut.portal.api.entity.jcxOrderItem.JcxOrderItemDto;
import com.olivia.peanut.portal.mapper.JcxOrderMapper;
import com.olivia.peanut.portal.model.JcxGoods;
import com.olivia.peanut.portal.model.JcxOrder;
import com.olivia.peanut.portal.model.JcxOrderItem;
import com.olivia.peanut.portal.service.JcxGoodsService;
import com.olivia.peanut.portal.service.JcxOrderItemService;
import com.olivia.peanut.portal.service.JcxOrderService;
import com.olivia.sdk.exception.CanIgnoreException;
import com.olivia.sdk.utils.*;
import jakarta.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * (JcxOrder)表服务实现类
 *
 * @author peanut
 * @since 2024-03-22 10:38:06
 */
@Slf4j
@Service("jcxOrderService")
@Transactional
public class JcxOrderServiceImpl extends MPJBaseServiceImpl<JcxOrderMapper, JcxOrder> implements JcxOrderService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  private @Resource JcxGoodsService jcxGoodsService;
  private @Resource JcxOrderItemService jcxOrderItemService;

  private Map<Long, JcxGoods> checkGoods(List<Long> goodsIdList, Map<Long, BigDecimal> minInventoryCount) {
    Map<Long, JcxGoods> goodsMap = jcxGoodsService.listByIds(goodsIdList).stream().collect(Collectors.toMap(BaseEntity::getId, Function.identity()));
    List<String> msgList = new ArrayList<>();
    for (int i = 0; i < goodsIdList.size(); i++) {
      JcxGoods goods = goodsMap.get(goodsIdList.get(i));
      if (Objects.isNull(goods)) {
        msgList.add("第" + (i + 1) + "个商品不存在");
      } else if (CollUtil.isNotEmpty(minInventoryCount) && goods.getGoodsInventoryCount().compareTo(minInventoryCount.get(goods.getId())) < 0) {
        msgList.add("第" + (i + 1) + "个商品库存不足");
      }
    }
    if (CollUtil.isNotEmpty(msgList)) {
      throw new CanIgnoreException(String.join(",", msgList));
    }
    return goodsMap;
  }

  @Override
  @Transactional
  public void save(JcxOrderInsertReq req) {
    Map<Long, JcxGoods> jcxGoodsMap = checkGoods(req.getGoodsList().stream().map(GoodsInfo::getGoodsId).toList(),
        req.getGoodsList().stream().collect(Collectors.toMap(GoodsInfo::getGoodsId, GoodsInfo::getGoodsCount)));
    JcxOrder order = $.copy(req, JcxOrder.class);
    order.setOrderNo(IdUtils.getUniqueId()).setId(IdWorker.getId());

    List<JcxOrderItem> orderItemList = new ArrayList<>();
    req.getGoodsList().forEach(goodsInfo -> {
      JcxGoods goods = jcxGoodsMap.get(goodsInfo.getGoodsId());
      JcxOrderItem orderItem = new JcxOrderItem();
      $.copy(goods, orderItem);
      orderItem.setOrderId(order.getId());
      orderItem.setGoodsId(goods.getId());
      orderItem.setGoodsCostPrice(goods.getCostPrice()).setGoodsCount(goodsInfo.getGoodsCount());
      orderItem.setGoodsSalePrice(goods.getSalesPrice()).setGoodsTotalSalePrice(goods.getSalesPrice().multiply(goodsInfo.getGoodsCount()));
      orderItemList.add(orderItem);
      boolean update = this.jcxGoodsService.update(
          new LambdaUpdateWrapper<JcxGoods>().eq(BaseEntity::getId, goods.getId()).ge(JcxGoods::getGoodsInventoryCount, goodsInfo.getGoodsCount())
              .eq(BaseEntity::getVersionNum, goods.getVersionNum()).set(JcxGoods::getGoodsInventoryCount, goods.getGoodsInventoryCount().subtract(goodsInfo.getGoodsCount()))
              .set(BaseEntity::getVersionNum, goods.getVersionNum() + 1));
      log.info("更新商品库存成功:{} res {}", goods.getId(), update);
      if (!update) {
        throw new CanIgnoreException(goods.getGoodsName() + "更新商品库存失败,库存不足");
      }
    });
    order.setOrderTotalSalePrice(orderItemList.stream().map(JcxOrderItem::getGoodsTotalSalePrice).reduce(new BigDecimal(0), BigDecimal::add));
    this.save(order);
    this.jcxOrderItemService.saveBatch(orderItemList);
  }

  public @Override JcxOrderQueryListRes queryList(JcxOrderQueryListReq req) {

    MPJLambdaWrapper<JcxOrder> q = getWrapper(req.getData());
    List<JcxOrder> list = this.list(q);

    List<JcxOrderDto> dataList = list.stream().map(t -> $.copy(t, JcxOrderDto.class)).collect(Collectors.toList());
    setName(false, dataList);
    return new JcxOrderQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<JcxOrderExportQueryPageListInfoRes> queryPageList(JcxOrderExportQueryPageListReq req) {

    DynamicsPage<JcxOrder> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<JcxOrder> q = getWrapper(req.getData());
    List<JcxOrderExportQueryPageListInfoRes> records;
    if (TRUE.equals(req.getQueryPage())) {
      IPage<JcxOrder> list = this.page(page, q);
      IPage<JcxOrderExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, JcxOrderExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), JcxOrderExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作
    List<JcxOrderExportQueryPageListInfoRes> listInfoRes = $.copyList(records, JcxOrderExportQueryPageListInfoRes.class);
    setName(true, listInfoRes);
    return DynamicsPage.init(page, listInfoRes);
  }

  @Override
  public void setName(Boolean queryOrderItem, List<? extends JcxOrderDto> jcxOrders) {
    if (FALSE.equals(queryOrderItem) || CollUtil.isEmpty(jcxOrders)) {
      return;
    }
    List<Runnable> runnableList = new ArrayList<>();
    jcxOrders.forEach(jcxOrder -> {
      runnableList.add(() -> {
        List<JcxOrderItem> orderItemList = this.jcxOrderItemService.list(new LambdaQueryWrapper<JcxOrderItem>().eq(JcxOrderItem::getOrderId, jcxOrder.getId()));
        jcxOrder.setOrderItemDtoList($.copyList(orderItemList, JcxOrderItemDto.class));
        List<Long> goodsIdList = orderItemList.stream().map(JcxOrderItem::getGoodsId).toList();
        Map<Long, JcxGoods> goodsMap = this.jcxGoodsService.listByIds(goodsIdList).stream().collect(Collectors.toMap(BaseEntity::getId, a -> a));
        jcxOrder.getOrderItemDtoList().forEach(t -> t.setGoodsName(goodsMap.getOrDefault(t.getGoodsId(), new JcxGoods()).getGoodsName()));
        jcxOrder.setGoodsName(goodsMap.values().stream().map(JcxGoods::getGoodsName).collect(Collectors.joining(",")));

      });
    });
    RunUtils.run("jcxOrder-setName", runnableList.size() / 3, runnableList);
  }

  // 以下为私有对象封装


  private MPJLambdaWrapper<JcxOrder> getWrapper(JcxOrderDto obj) {
    MPJLambdaWrapper<JcxOrder> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q.eq(Objects.nonNull(obj.getId()), JcxOrder::getId, obj.getId()).eq(Objects.nonNull(obj.getTenantId()), JcxOrder::getTenantId, obj.getTenantId())
          .eq(StringUtils.isNoneBlank(obj.getOrderNo()), JcxOrder::getOrderNo, obj.getOrderNo())
          .eq(StringUtils.isNoneBlank(obj.getOrderRemark()), JcxOrder::getOrderRemark, obj.getOrderRemark())
          .eq(Objects.nonNull(obj.getOrderStatus()), JcxOrder::getOrderStatus, obj.getOrderStatus())
          .eq(Objects.nonNull(obj.getCreateTime()), JcxOrder::getCreateTime, obj.getCreateTime())
          .eq(Objects.nonNull(obj.getUpdateTime()), JcxOrder::getUpdateTime, obj.getUpdateTime()).orderByDesc(JcxOrder::getId);
    }

    return q;

  }

  private void setQueryListHeader(DynamicsPage<JcxOrder> page) {
    page.addHeader("orderNo", "订单编号", 200).addHeader("goodsName", "商品名称", 400)
        .addHeader("orderTotalSalePrice", "总额").addHeader("orderRemark", "订单备注")
        .addHeader("orderStatusName", "订单状态")
        .addHeader("createTime", "创建时间", 200);
  }
}

