package com.olivia.peanut.aps.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.api.entity.apsOrderGoodsSaleHistory.*;
import com.olivia.peanut.aps.mapper.ApsOrderGoodsSaleHistoryMapper;
import com.olivia.peanut.aps.model.*;
import com.olivia.peanut.aps.service.ApsGoodsService;
import com.olivia.peanut.aps.service.ApsOrderGoodsSaleConfigService;
import com.olivia.peanut.aps.service.ApsOrderGoodsSaleHistoryService;
import com.olivia.peanut.aps.service.ApsSaleConfigService;
import com.olivia.peanut.aps.service.impl.po.ApsOrderGoodsSaleHistoryCount;
import com.olivia.peanut.portal.service.BaseTableHeaderService;
import com.olivia.sdk.filter.LoginUserContext;
import com.olivia.sdk.service.SetNameService;
import com.olivia.sdk.utils.*;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 销售规划订单历史销售占比(ApsOrderGoodsSaleHistory)表服务实现类
 *
 * @author makejava
 * @since 2025-02-18 14:28:42
 */
@Slf4j
@Service("apsOrderGoodsSaleHistoryService")
@Transactional
public class ApsOrderGoodsSaleHistoryServiceImpl extends MPJBaseServiceImpl<ApsOrderGoodsSaleHistoryMapper, ApsOrderGoodsSaleHistory> implements ApsOrderGoodsSaleHistoryService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  @Resource
  BaseTableHeaderService tableHeaderService;
  @Resource
  SetNameService setNameService;

  @Resource
  ApsGoodsService apsGoodsService;
  @Resource
  ApsOrderGoodsSaleConfigService apsOrderGoodsSaleConfigService;
  @Resource
  ApsSaleConfigService apsSaleConfigService;

  public @Override ApsOrderGoodsSaleHistoryQueryListRes queryList(ApsOrderGoodsSaleHistoryQueryListReq req) {

    MPJLambdaWrapper<ApsOrderGoodsSaleHistory> q = getWrapper(req.getData());
    List<ApsOrderGoodsSaleHistory> list = this.list(q);

    List<ApsOrderGoodsSaleHistoryDto> dataList = list.stream().map(t -> $.copy(t, ApsOrderGoodsSaleHistoryDto.class)).collect(Collectors.toList());
    ((ApsOrderGoodsSaleHistoryService) AopContext.currentProxy()).setName(dataList);
    return new ApsOrderGoodsSaleHistoryQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<ApsOrderGoodsSaleHistoryExportQueryPageListInfoRes> queryPageList(ApsOrderGoodsSaleHistoryExportQueryPageListReq req) {

    DynamicsPage<ApsOrderGoodsSaleHistory> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsOrderGoodsSaleHistory> q = getWrapper(req.getData());
    List<ApsOrderGoodsSaleHistoryExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsOrderGoodsSaleHistory> list = this.page(page, q);
      IPage<ApsOrderGoodsSaleHistoryExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsOrderGoodsSaleHistoryExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsOrderGoodsSaleHistoryExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作 

    List<ApsOrderGoodsSaleHistoryExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsOrderGoodsSaleHistoryExportQueryPageListInfoRes.class);
    ((ApsOrderGoodsSaleHistoryService) AopContext.currentProxy()).setName(listInfoRes);

    return DynamicsPage.init(page, listInfoRes);
  }


  @Override
  @SuppressWarnings("unchecked")
  public SelectOrder2HistoryRes selectOrder2History(SelectOrder2HistoryReq req) {
    if (Objects.nonNull(req.getTenantId()))
      LoginUserContext.setContextThreadLocal(LoginUserContext.getLoginUser().setTenantId(req.getTenantId()));

    SelectOrder2HistoryRes res = new SelectOrder2HistoryRes();
    List<ApsGoods> goodsList = apsGoodsService.list();
    if (CollUtil.isEmpty(goodsList)) {
      log.info("selectOrder2History goodsList is null");
      return res;
    }
    LocalDateTime beginDate = (switch (req.getSelectType()) {
      case LAST_MONTH -> YearMonth.from(YearMonth.now()).minusMonths(1).atDay(1);
      case null -> YearMonth.from(YearMonth.now()).minusMonths(1).atDay(1);
      case CURRENT_MONTH -> YearMonth.from(YearMonth.now()).atDay(1);
    }).atTime(LocalTime.MIN);
    LocalDateTime endDate = (switch (req.getSelectType()) {
      case LAST_MONTH -> YearMonth.from(LocalDate.now()).minusMonths(1).atEndOfMonth();
      case null -> YearMonth.from(LocalDate.now()).minusMonths(1).atEndOfMonth();
      case CURRENT_MONTH -> YearMonth.from(LocalDate.now()).atEndOfMonth();
    }).atTime(LocalTime.MAX);

//    List<Long> goodsIdList = goodsList.stream().map(BaseEntity::getId).toList();
//    List<Long> factoryIdList = goodsList.stream().map(ApsGoods::getFactoryId).toList();

    List<ApsOrderGoodsSaleHistory> insertList = new ArrayList<>();
    goodsList.forEach(goods -> {
      List<Runnable> runnableList = new ArrayList<>();

      Long goodsId = goods.getId();

      runnableList.add(() -> {
        List<ApsOrderGoodsSaleConfig> apsOrderGoodsSaleConfigList = apsOrderGoodsSaleConfigService.list(new LambdaQueryWrapper<ApsOrderGoodsSaleConfig>()//
//        .in(ApsOrderGoodsSaleConfig::getFactoryId, factoryIdList)
            .select(ApsOrderGoodsSaleConfig::getConfigId).eq(ApsOrderGoodsSaleConfig::getGoodsId, goodsId).gt(BaseEntity::getCreateTime, beginDate).le(BaseEntity::getCreateTime, endDate));

        if (CollUtil.isEmpty(apsOrderGoodsSaleConfigList)) {
          log.info("selectOrder2History apsOrderGoodsSaleConfigList is null");
        }
      });

      runnableList.add(() -> {
        List<Map<String, Object>> listGoodsSaleMaps = this.listMaps(new QueryWrapper<ApsOrderGoodsSaleHistory>() //
            .select("factory_id fid ", "goods_id gid", "sale_config_id sid", "count(1) c").lambda()//
            .eq(ApsOrderGoodsSaleHistory::getGoodsId, goodsId)//
            .eq(ApsOrderGoodsSaleHistory::getYear, beginDate.getYear())//
            .groupBy(ApsOrderGoodsSaleHistory::getFactoryId, ApsOrderGoodsSaleHistory::getGoodsId, ApsOrderGoodsSaleHistory::getSaleConfigId));
        log.info("listGoodsSaleMaps {}", JSON.toJSONString(listGoodsSaleMaps));

      });
      List<ApsOrderGoodsSaleHistoryCount> apsOrderGoodsSaleHistoryCountList = new ArrayList<>();
      runnableList.add(() -> {

        apsOrderGoodsSaleHistoryCountList.addAll(this.listMaps(new QueryWrapper<ApsOrderGoodsSaleHistory>() //
            .select("factory_id fid ", "goods_id gid", "count(1) total")//
            .lambda() //
            .eq(ApsOrderGoodsSaleHistory::getGoodsId, goodsId)//
            .eq(ApsOrderGoodsSaleHistory::getYear, beginDate.getYear())//
            .groupBy(ApsOrderGoodsSaleHistory::getFactoryId, ApsOrderGoodsSaleHistory::getGoodsId)).stream().map(ApsOrderGoodsSaleHistoryCount::new).toList());
        log.info("apsOrderGoodsSaleHistoryCountList {}", JSON.toJSONString(apsOrderGoodsSaleHistoryCountList));
      });
      Map<Long, Long> saleHistoryIdMap = new HashMap<>();
      runnableList.add(() -> {
        Map<Long, Long> tMap = this.lambdaQuery().select(BaseEntity::getId, ApsOrderGoodsSaleHistory::getSaleConfigId).eq(ApsOrderGoodsSaleHistory::getGoodsId, goodsId).eq(ApsOrderGoodsSaleHistory::getYear, beginDate.getYear()).list().stream().collect(Collectors.toMap(ApsOrderGoodsSaleHistory::getSaleConfigId, BaseEntity::getId));
        saleHistoryIdMap.putAll(tMap);
      });

      Map<Long, List<ApsSaleConfig>> saleParentMap = new HashMap<>();
      runnableList.add(() -> {
        Map<Long, List<ApsSaleConfig>> saleParentMapTmp = apsSaleConfigService.list(new MPJLambdaWrapper<ApsSaleConfig>().leftJoin(ApsGoodsSaleItem.class, ApsGoodsSaleItem::getSaleConfigId, ApsSaleConfig::getId).eq(ApsGoodsSaleItem::getGoodsId, goodsId)).stream().collect(Collectors.groupingBy(ApsSaleConfig::getParentId,//
            Collectors.collectingAndThen(Collectors.<ApsSaleConfig>toList(),//
                list -> list.stream().sorted(Comparator.comparing(ApsSaleConfig::getSaleCode)).toList())));
        saleParentMap.putAll(saleParentMapTmp);
      });

      RunUtils.run("selectOrder2History", runnableList);

    });

    this.saveOrUpdateBatch(insertList);

    return res;
  }

  // 以下为私有对象封装

  public @Override void setName(List<? extends ApsOrderGoodsSaleHistoryDto> list) {

    //   setNameService.setName(list, SetNamePojoUtils.FACTORY, SetNamePojoUtils.OP_USER_NAME);

  }


  @SuppressWarnings("unchecked")
  private MPJLambdaWrapper<ApsOrderGoodsSaleHistory> getWrapper(ApsOrderGoodsSaleHistoryDto obj) {
    MPJLambdaWrapper<ApsOrderGoodsSaleHistory> q = new MPJLambdaWrapper<>();


    LambdaQueryUtil.lambdaQueryWrapper(q, obj, ApsOrderGoodsSaleHistory.class
        // 查询条件
        , ApsOrderGoodsSaleHistory::getFactoryId //
        , ApsOrderGoodsSaleHistory::getYear, ApsOrderGoodsSaleHistory::getGoodsId //
        , ApsOrderGoodsSaleHistory::getSaleParentId //
        , ApsOrderGoodsSaleHistory::getSaleConfigId //
        , ApsOrderGoodsSaleHistory::getMonthCount01 //
        , ApsOrderGoodsSaleHistory::getMonthRatio01 //
        , ApsOrderGoodsSaleHistory::getMonthCount02 //
        , ApsOrderGoodsSaleHistory::getMonthRatio02 //
        , ApsOrderGoodsSaleHistory::getMonthCount03 //
        , ApsOrderGoodsSaleHistory::getMonthRatio03 //
        , ApsOrderGoodsSaleHistory::getMonthCount04 //
        , ApsOrderGoodsSaleHistory::getMonthRatio04 //
        , ApsOrderGoodsSaleHistory::getMonthCount05 //
        , ApsOrderGoodsSaleHistory::getMonthRatio05 //
        , ApsOrderGoodsSaleHistory::getMonthCount06 //
        , ApsOrderGoodsSaleHistory::getMonthRatio06 //
        , ApsOrderGoodsSaleHistory::getMonthCount07 //
        , ApsOrderGoodsSaleHistory::getMonthRatio07 //
        , ApsOrderGoodsSaleHistory::getMonthCount08 //
        , ApsOrderGoodsSaleHistory::getMonthRatio08 //
        , ApsOrderGoodsSaleHistory::getMonthCount09 //
        , ApsOrderGoodsSaleHistory::getMonthRatio09 //
        , ApsOrderGoodsSaleHistory::getMonthCount10 //
        , ApsOrderGoodsSaleHistory::getMonthRatio10 //
        , ApsOrderGoodsSaleHistory::getMonthCount11 //
        , ApsOrderGoodsSaleHistory::getMonthRatio11 //
        , ApsOrderGoodsSaleHistory::getMonthCount12 //
        , ApsOrderGoodsSaleHistory::getMonthRatio12 //
    );


    q.orderByDesc(ApsOrderGoodsSaleHistory::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsOrderGoodsSaleHistory> page) {

    tableHeaderService.listByBizKey(page, "ApsOrderGoodsSaleHistoryService#queryPageList");

  }


}

