package com.olivia.peanut.portal.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.base.model.BaseSupplier;
import com.olivia.peanut.base.model.MsgMessage;
import com.olivia.peanut.base.service.BaseSupplierService;
import com.olivia.peanut.base.service.MsgMessageService;
import com.olivia.peanut.portal.api.entity.jcxBuyOrder.*;
import com.olivia.peanut.portal.api.entity.jcxBuyOrderItem.JcxBuyOrderItemDto;
import com.olivia.peanut.portal.api.entity.jcxBuyPlan.JcxBuyPlanStatusEnum;
import com.olivia.peanut.portal.mapper.JcxBuyOrderMapper;
import com.olivia.peanut.portal.model.*;
import com.olivia.peanut.portal.service.*;
import com.olivia.sdk.ann.SetUserName;
import com.olivia.sdk.comment.ServiceComment;
import com.olivia.sdk.exception.CanIgnoreException;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.BaseEntity;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.RunUtils;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * (JcxBuyOrder)表服务实现类
 *
 * @author peanut
 * @since 2024-03-27 13:51:37
 */
@Service("jcxBuyOrderService")
@Transactional
public class JcxBuyOrderServiceImpl extends MPJBaseServiceImpl<JcxBuyOrderMapper, JcxBuyOrder> implements JcxBuyOrderService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();
  @Resource
  JcxGoodsService jcxGoodsService;
  @Resource
  JcxBuyOrderItemService jcxBuyOrderItemService;
  @Resource
  JcxBuyPlanItemService jcxBuyPlanItemService;
  @Resource
  JcxBuyPlanService jcxBuyPlanService;
  // 以下为私有对象封装
  @Resource
  MsgMessageService msgMessageService;
  @Resource
  BaseSupplierService supplierService;

  public @Override JcxBuyOrderQueryListRes queryList(JcxBuyOrderQueryListReq req) {
    MPJLambdaWrapper<JcxBuyOrder> q = getWrapper(req.getData());
    List<JcxBuyOrder> list = this.list(q);
    List<JcxBuyOrderDto> dataList = list.stream().map(t -> $.copy(t, JcxBuyOrderDto.class)).collect(Collectors.toList());
    return new JcxBuyOrderQueryListRes().setDataList(dataList);
  }

  public @Override DynamicsPage<JcxBuyOrderExportQueryPageListInfoRes> queryPageList(JcxBuyOrderExportQueryPageListReq req) {
    DynamicsPage<JcxBuyOrder> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<JcxBuyOrder> q = getWrapper(req.getData());
    List<JcxBuyOrderExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<JcxBuyOrder> list = this.page(page, q);
      IPage<JcxBuyOrderExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, JcxBuyOrderExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), JcxBuyOrderExportQueryPageListInfoRes.class);
    }
    // 类型转换，  更换枚举 等操作
    List<JcxBuyOrderExportQueryPageListInfoRes> listInfoRes = $.copyList(records, JcxBuyOrderExportQueryPageListInfoRes.class);
    // this.setName(listInfoRes);
    return DynamicsPage.init(page, listInfoRes);
  }

  @SetUserName
  public @Override void setName(List<? extends JcxBuyOrderDto> jcxBuyOrderDtoList) {
    if (CollUtil.isEmpty(jcxBuyOrderDtoList)) {
      return;
    }
    jcxBuyOrderDtoList.forEach(t -> {
      List<JcxBuyOrderItem> buyOrderItems = this.jcxBuyOrderItemService.list(new LambdaQueryWrapper<JcxBuyOrderItem>().eq(JcxBuyOrderItem::getOrderId, t.getId()));
      t.setSupplierName(String.join(",", buyOrderItems.stream().map(JcxBuyOrderItem::getSupplierName).filter(StringUtils::isNotBlank).toList()));
      t.setBuyOrderItemDtoList($.copyList(buyOrderItems, JcxBuyOrderItemDto.class));
    });
  }

  private MPJLambdaWrapper<JcxBuyOrder> getWrapper(JcxBuyOrderDto obj) {
    MPJLambdaWrapper<JcxBuyOrder> q = new MPJLambdaWrapper<>();
    if (Objects.nonNull(obj)) {

      q.eq(Objects.nonNull(obj.getId()), JcxBuyOrder::getId, obj.getId()).likeRight(StringUtils.isNoneBlank(obj.getOrderNo()), JcxBuyOrder::getOrderNo, obj.getOrderNo())
          .eq(StringUtils.isNoneBlank(obj.getOrderRemark()), JcxBuyOrder::getOrderRemark, obj.getOrderRemark())
          .eq(Objects.nonNull(obj.getOrderStatus()), JcxBuyOrder::getOrderStatus, obj.getOrderStatus())
          .eq(Objects.nonNull(obj.getTenantId()), JcxBuyOrder::getTenantId, obj.getTenantId())
          .eq(Objects.nonNull(obj.getVersionNum()), JcxBuyOrder::getVersionNum, obj.getVersionNum());
    }
    q.orderByDesc(JcxBuyOrder::getId);
    return q;
  }

  private void setQueryListHeader(DynamicsPage<JcxBuyOrder> page) {
    ServiceComment.header(page, "JcxBuyOrderService#queryPageList");
  }

  @Override
  @Transactional
  public JcxBuyOrderInsertRes save(JcxBuyOrderInsertReq req) {

    List<JcxBuyPlan> planList = this.jcxBuyPlanService.listByIds(req.getBuyPlanIdList());
    if (planList.size() != req.getBuyPlanIdList().size()) {
      throw new CanIgnoreException("有计划未找到，不能生成采购单");
    }
    String errMsg = planList.stream().filter(t -> !JcxBuyPlanStatusEnum.SUCCESS.getCode().equals(t.getPlanStatus())).map(JcxBuyPlan::getPlanName).collect(Collectors.joining(","));
    if (StringUtils.isNotBlank(errMsg)) {
      throw new CanIgnoreException("计划名称为：" + errMsg + " 的计划未完成，不能生成采购单");
    }
    errMsg = planList.stream().filter(t -> Objects.nonNull(t.getBuyOrderId())).map(JcxBuyPlan::getPlanName).collect(Collectors.joining(","));
    if (StringUtils.isNotBlank(errMsg)) {
      throw new CanIgnoreException("计划名称为：" + errMsg + " 的计划已生成采购单，不能重复生成采购单");
    }
    JcxBuyOrder jcxBuyOrder = new JcxBuyOrder();
    jcxBuyOrder.setId(IdWorker.getId());
    jcxBuyOrder.setOrderNo(req.getOrderNo());
    jcxBuyOrder.setOrderRemark(req.getOrderRemark()).setOrderName(req.getOrderName()).setOrderStatus(JcxBuyOrderStatusEnum.INIT.getCode());

    List<JcxBuyPlanItem> goodsList = Collections.synchronizedList(new ArrayList<>());
    List<Runnable> getGoodsList = new ArrayList<>();
    planList.forEach(t -> {
      getGoodsList.add(() -> {
        List<JcxBuyPlanItem> buyPlanItemList = this.jcxBuyPlanItemService.list(new LambdaQueryWrapper<JcxBuyPlanItem>().eq(JcxBuyPlanItem::getPlanId, t.getId()));
        goodsList.addAll(buyPlanItemList);
      });
    });
    RunUtils.run("JcxBuyOrderService#save", getGoodsList);
    Map<Long, Long> goodsIdCountMap = goodsList.stream().collect(Collectors.groupingBy(JcxBuyPlanItem::getGoodsId, Collectors.summingLong(JcxBuyPlanItem::getGoodsBuyCount)));
    Map<Long, JcxGoods> goodsMap = this.jcxGoodsService.listByIds(goodsIdCountMap.keySet()).stream().collect(Collectors.toMap(BaseEntity::getId, Function.identity()));
    List<JcxBuyOrderItem> jcxBuyOrderItemList = new ArrayList<>();

    goodsIdCountMap.forEach((gId, gc) -> {
      JcxGoods goods = goodsMap.get(gId);
      BaseSupplier baseSupplier = supplierService.getById(goods.getSupplierId());
      JcxBuyOrderItem jcxBuyOrderItem = new JcxBuyOrderItem().setOrderId(jcxBuyOrder.getId()).setGoodsId(gId).setGoodsName(goods.getGoodsName()).setGoodsBuyCount(gc)
          .setGoodsUnit(goods.getGoodsUnit()).setGoodsCostPrice(goods.getCostPrice()).setGoodsCostPriceTotal(goods.getCostPrice().multiply(new BigDecimal(gc)))
          .setGoodsBuyRemark(req.getOrderRemark());
      jcxBuyOrderItem.setSupplierId(goods.getSupplierId()).setSupplierName(baseSupplier.getSupplierName()).setId(IdWorker.getId());
      jcxBuyOrderItemList.add(jcxBuyOrderItem);
    });
    this.jcxBuyPlanService.update(new LambdaUpdateWrapper<JcxBuyPlan>().set(JcxBuyPlan::getBuyOrderId, jcxBuyOrder.getId()).in(BaseEntity::getId, req.getBuyPlanIdList()));
    jcxBuyOrderItemList.sort(Comparator.comparing(JcxBuyOrderItem::getGoodsName));
    this.jcxBuyOrderItemService.saveBatch(jcxBuyOrderItemList);
    String goodsName = jcxBuyOrderItemList.stream().map(JcxBuyOrderItem::getGoodsName).collect(Collectors.joining(","));
    goodsName = goodsName.length() > 20 ? goodsName.substring(0, 20) + "..." : goodsName;
    jcxBuyOrder.setGoodsName(goodsName);
    jcxBuyOrder.setGoodsCostPriceTotal(jcxBuyOrderItemList.stream().map(JcxBuyOrderItem::getGoodsCostPriceTotal).reduce(new BigDecimal(0), BigDecimal::add));
    this.save(jcxBuyOrder);
    return new JcxBuyOrderInsertRes().setCount(1).setId(jcxBuyOrder.getId());
  }

  @Override
  public void updateStatus(JcxBuyOrderUpdateStatusReq req) {
    boolean update = this.update(new LambdaUpdateWrapper<JcxBuyOrder>().eq(BaseEntity::getId, req.getId())
        //
        .set(JcxBuyOrder::getOrderStatus, req.getOrderStatus()).set(BaseEntity::getVersionNum, req.getVersionNum()));
    $.requireNonNullCanIgnoreException(update, "更新失败");
    // 订单拒绝  删除与采购单关联关系
    if (Objects.equals(JcxBuyOrderStatusEnum.REJECT.getCode(), req.getOrderStatus())) {
      List<JcxBuyPlan> jcxBuyPlans = this.jcxBuyPlanService.list(new LambdaUpdateWrapper<JcxBuyPlan>().eq(JcxBuyPlan::getBuyOrderId, req.getId()));
      this.jcxBuyPlanService.update(new LambdaUpdateWrapper<JcxBuyPlan>().set(JcxBuyPlan::getBuyOrderId, null).eq(JcxBuyPlan::getBuyOrderId, req.getId()));
      msgMessageService.save(
          new MsgMessage().setMessageTitle("采购单状态更新").setMessageContext("采购单已驳回:" + jcxBuyPlans.stream().map(JcxBuyPlan::getPlanName).collect(Collectors.joining(",")))
              .setIsAll(Boolean.TRUE));
    }
  }
}
