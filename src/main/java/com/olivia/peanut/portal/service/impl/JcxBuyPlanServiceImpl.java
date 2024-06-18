package com.olivia.peanut.portal.service.impl;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.portal.api.entity.jcxBuyPlan.*;
import com.olivia.peanut.portal.api.entity.jcxBuyPlanItem.JcxBuyPlanItemDto;
import com.olivia.peanut.portal.mapper.JcxBuyPlanMapper;
import com.olivia.peanut.portal.model.JcxBuyPlan;
import com.olivia.peanut.portal.model.JcxBuyPlanItem;
import com.olivia.peanut.portal.model.JcxGoods;
import com.olivia.peanut.portal.service.BaseTableHeaderService;
import com.olivia.peanut.portal.service.JcxBuyPlanItemService;
import com.olivia.peanut.portal.service.JcxBuyPlanService;
import com.olivia.peanut.portal.service.JcxGoodsService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.BaseEntity;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.RunUtils;
import jakarta.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * (JcxBuyPlan)表服务实现类
 *
 * @author peanut
 * @since 2024-03-24 20:27:11
 */
@Service("jcxBuyPlanService")
@Transactional
public class JcxBuyPlanServiceImpl extends MPJBaseServiceImpl<JcxBuyPlanMapper, JcxBuyPlan> implements JcxBuyPlanService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();
  @Resource
  JcxBuyPlanItemService jcxBuyPlanItemService;
  @Resource
  JcxGoodsService jcxGoodsService;

  // 以下为私有对象封装
  @Resource
  BaseTableHeaderService tableHeaderService;

  public @Override JcxBuyPlanQueryListRes queryList(JcxBuyPlanQueryListReq req) {

    MPJLambdaWrapper<JcxBuyPlan> q = getWrapper(req.getData());
    List<JcxBuyPlan> list = this.list(q);

    List<JcxBuyPlanDto> dataList = list.stream().map(t -> $.copy(t, JcxBuyPlanDto.class)).collect(Collectors.toList());
    setName(dataList);
    return new JcxBuyPlanQueryListRes().setDataList(dataList);
  }

  public @Override DynamicsPage<JcxBuyPlanExportQueryPageListInfoRes> queryPageList(JcxBuyPlanExportQueryPageListReq req) {

    DynamicsPage<JcxBuyPlan> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<JcxBuyPlan> q = getWrapper(req.getData());
    List<JcxBuyPlanExportQueryPageListInfoRes> records;
    if (TRUE.equals(req.getQueryPage())) {
      IPage<JcxBuyPlan> list = this.page(page, q);
      IPage<JcxBuyPlanExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, JcxBuyPlanExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), JcxBuyPlanExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作
    List<JcxBuyPlanExportQueryPageListInfoRes> listInfoRes = $.copyList(records, JcxBuyPlanExportQueryPageListInfoRes.class);
    setName(listInfoRes);
    return DynamicsPage.init(page, listInfoRes);
  }

  @Override
  public void setName(List<? extends JcxBuyPlanDto> jcxBuyPlanDtoList) {
    if (CollUtil.isEmpty(jcxBuyPlanDtoList)) {
      return;
    }
    List<Runnable> runnableList = new ArrayList<>();
    jcxBuyPlanDtoList.forEach(t -> {
      List<JcxBuyPlanItem> itemList = jcxBuyPlanItemService.list(new LambdaQueryWrapper<JcxBuyPlanItem>().eq(JcxBuyPlanItem::getPlanId, t.getId()));
      Map<Long, String> idNameMap = jcxGoodsService.listByIds(itemList.stream().map(JcxBuyPlanItem::getGoodsId).toList()).stream()
          .collect(Collectors.toMap(BaseEntity::getId, JcxGoods::getGoodsName));
      t.setJcxBuyPlanItemDtoList($.copyList(itemList, JcxBuyPlanItemDto.class));
      t.getJcxBuyPlanItemDtoList().forEach(g -> g.setGoodsName(idNameMap.get(g.getGoodsId())));
      t.setPlanStatusName(JcxBuyPlanStatusEnum.codeMsg.get(t.getPlanStatus()));
    });
    RunUtils.run("JcxBuyPlanServiceImpl#setName", jcxBuyPlanDtoList.size() / 5, runnableList);
  }

  private MPJLambdaWrapper<JcxBuyPlan> getWrapper(JcxBuyPlanDto obj) {
    MPJLambdaWrapper<JcxBuyPlan> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q.eq(Objects.nonNull(obj.getId()), JcxBuyPlan::getId, obj.getId())
          //
          .likeRight(StringUtils.isNoneBlank(obj.getPlanName()), JcxBuyPlan::getPlanName, obj.getPlanName())
//          .isNotNull(Boolean.TRUE.equals(obj.getIsCreateOrder()), JcxBuyPlan::getBuyOrderId)
          .eq(StringUtils.isNoneBlank(obj.getPlanStatus()), JcxBuyPlan::getPlanStatus, obj.getPlanStatus());
      if (TRUE.equals(obj.getIsCreateOrder())) {
        q.isNotNull(JcxBuyPlan::getBuyOrderId);
      } else if (FALSE.equals(obj.getIsCreateOrder())) {
        q.isNull(JcxBuyPlan::getBuyOrderId);
      }
    }
    return q.orderByDesc(JcxBuyPlan::getId);

  }

  private void setQueryListHeader(DynamicsPage<JcxBuyPlan> page) {
    String bizKey = "JcxBuyPlanService#queryPageList";
    tableHeaderService.listByBizKey(page, bizKey);
//    page.addHeader("planName", "计划名称")
//        .addHeader("createTime", "创建时间").addHeader("updateTime", "修改时间");
  }

  @Override
  @Transactional
  public JcxBuyPlanInsertRes save(JcxBuyPlanInsertReq req) {
    req.checkParam();
    JcxBuyPlan jcxBuyPlan = new JcxBuyPlan();
    jcxBuyPlan.setPlanName(req.getPlanName()).setPlanStatus(JcxBuyPlanStatusEnum.INIT.getCode()).setId(IdWorker.getId());
    jcxBuyPlan.setPlanStatus(JcxBuyPlanStatusEnum.INIT.getCode());
    List<JcxBuyPlanItemDto> buyGoodsPlanList = req.getJcxBuyPlanItemDtoList();
    Map<Long, JcxGoods> goodsMap = this.jcxGoodsService.listByIds(req.getJcxBuyPlanItemDtoList().stream().map(JcxBuyPlanItemDto::getGoodsId).toList()).stream()
        .collect(Collectors.toMap(BaseEntity::getId, Function.identity()));
    List<JcxBuyPlanItem> jcxBuyPlanItems = new ArrayList<>();
    buyGoodsPlanList.forEach(bg -> {
      JcxBuyPlanItem planItem = $.copy(goodsMap.get(bg.getGoodsId()), JcxBuyPlanItem.class);
      planItem.setPlanId(jcxBuyPlan.getId()).setGoodsId(bg.getGoodsId()).setGoodsBuyCount(bg.getGoodsBuyCount()).setId(IdWorker.getId());
      BigDecimal goodsBuyCount = new BigDecimal(planItem.getGoodsBuyCount());
      planItem.setCostPriceTotal(planItem.getSalesPrice().multiply(goodsBuyCount));
      planItem.setSalesPriceTotal(planItem.getSalesPrice().multiply(goodsBuyCount));
      planItem.setGoodsGrossProfitTotal(planItem.getGoodsNetProfit().multiply(goodsBuyCount));
      planItem.setGoodsNetProfitTotal(planItem.getGoodsNetProfit().multiply(goodsBuyCount));
      jcxBuyPlanItems.add(planItem);
    });
    jcxBuyPlan.setCostPriceTotal(jcxBuyPlanItems.stream().map(JcxBuyPlanItem::getCostPriceTotal).reduce(new BigDecimal(0), BigDecimal::add));
    jcxBuyPlan.setSalesPriceTotal(jcxBuyPlanItems.stream().map(JcxBuyPlanItem::getSalesPriceTotal).reduce(new BigDecimal(0), BigDecimal::add));
    jcxBuyPlan.setGoodsGrossProfitTotal(jcxBuyPlanItems.stream().map(JcxBuyPlanItem::getGoodsNetProfitTotal).reduce(new BigDecimal(0), BigDecimal::add));
    jcxBuyPlan.setGoodsNetProfitTotal(jcxBuyPlanItems.stream().map(JcxBuyPlanItem::getGoodsNetProfitTotal).reduce(new BigDecimal(0), BigDecimal::add));
    this.save(jcxBuyPlan);
    this.jcxBuyPlanItemService.saveBatch(jcxBuyPlanItems);
    return new JcxBuyPlanInsertRes().setCount(1).setId(jcxBuyPlan.getId());
  }

  @Override
  @Transactional
  public void updateById(JcxBuyPlanUpdateByIdReq req) {
    JcxBuyPlan buyPlan = $.copy(req, JcxBuyPlan.class);

    this.jcxBuyPlanItemService.remove(new LambdaQueryWrapper<JcxBuyPlanItem>().eq(JcxBuyPlanItem::getPlanId, req.getId()));

    Map<Long, JcxGoods> goodsMap = this.jcxGoodsService.listByIds(req.getJcxBuyPlanItemDtoList().stream().map(JcxBuyPlanItemDto::getGoodsId).toList()).stream()
        .collect(Collectors.toMap(BaseEntity::getId, Function.identity()));
    List<JcxBuyPlanItem> jcxBuyPlanItems = new ArrayList<>();

    req.getJcxBuyPlanItemDtoList().forEach(bg -> {
      JcxBuyPlanItem planItem = $.copy(goodsMap.get(bg.getGoodsId()), JcxBuyPlanItem.class);
      planItem.setPlanId(req.getId()).setGoodsId(bg.getGoodsId()).setGoodsBuyCount(bg.getGoodsBuyCount()).setId(IdWorker.getId());
      BigDecimal goodsBuyCount = new BigDecimal(bg.getGoodsBuyCount());
      planItem.setCostPriceTotal(planItem.getSalesPrice().multiply(goodsBuyCount));
      planItem.setSalesPriceTotal(planItem.getSalesPrice().multiply(goodsBuyCount));
      planItem.setGoodsGrossProfitTotal(planItem.getGoodsNetProfit().multiply(goodsBuyCount));
      planItem.setGoodsNetProfitTotal(planItem.getGoodsNetProfit().multiply(goodsBuyCount));
      jcxBuyPlanItems.add(planItem);
    });
    buyPlan.setPlanStatus(JcxBuyPlanStatusEnum.INIT.getCode());
    this.updateById(buyPlan);
    this.jcxBuyPlanItemService.saveBatch(jcxBuyPlanItems);
  }
}

