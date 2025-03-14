package com.olivia.peanut.aps.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ReflectUtil;
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
import com.olivia.peanut.aps.service.*;
import com.olivia.peanut.aps.service.impl.po.ApsOrderGoodsSaleHistoryCount;
import com.olivia.peanut.base.service.BaseTableHeaderService;
import com.olivia.sdk.service.SetNameService;
import com.olivia.sdk.utils.*;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.stream.Collectors;

import static cn.hutool.core.util.NumberUtil.decimalFormat;

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

  @Resource
  ApsOrderGoodsService apsOrderGoodsService;

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

    SelectOrder2HistoryRes res = new SelectOrder2HistoryRes();
    List<ApsGoods> goodsList = apsGoodsService.list();
    if (CollUtil.isEmpty(goodsList)) {
      log.info("selectOrder2History goodsList is null");
      return res;
    }
    LocalDateTime beginDate = req.getBeginDate();
    LocalDateTime endDate = req.getEndDate();


    List<ApsOrderGoodsSaleHistory> insertList = new ArrayList<>();

    goodsList.forEach(goods -> {
      List<Runnable> runnableList = new ArrayList<>();

      Long goodsId = goods.getId();
      List<ApsOrderGoodsSaleHistoryCount> apsOrderGoodsSaleConfigList = new ArrayList<>();
      runnableList.add(() -> {
        // 查询订单历史销售配置
        apsOrderGoodsSaleConfigList.addAll(apsOrderGoodsSaleConfigService.listMaps(new QueryWrapper<ApsOrderGoodsSaleConfig>()//
//        .in(ApsOrderGoodsSaleConfig::getFactoryId, factoryIdList)
                .select("config_id saleConfigId", "count(1) total").lambda()//
                .eq(ApsOrderGoodsSaleConfig::getGoodsId, goodsId) //
                .ge(BaseEntity::getCreateTime, beginDate).le(BaseEntity::getCreateTime, endDate).groupBy(ApsOrderGoodsSaleConfig::getConfigId)) //
            .stream().map(ApsOrderGoodsSaleHistoryCount::new).toList());
        if (log.isDebugEnabled()) {
          log.debug("apsOrderGoodsSaleConfigList {}", JSON.toJSONString(apsOrderGoodsSaleConfigList));
        }
      });
      AtomicLong goodsCount = new AtomicLong();
      // 查询总数
      runnableList.add(() -> {
        long count = this.apsOrderGoodsSaleConfigService.count(new LambdaQueryWrapper<ApsOrderGoodsSaleConfig>().eq(ApsOrderGoodsSaleConfig::getGoodsId, goodsId)//
            .gt(BaseEntity::getCreateTime, beginDate).le(BaseEntity::getCreateTime, endDate));
        goodsCount.set(count);
        if (log.isDebugEnabled()) {
          log.debug("goodsCount {}", JSON.toJSONString(count));
        }
      });
      // 查询已生成的订单历史销售配置
      runnableList.add(() -> {
        List<Map<String, Object>> listGoodsSaleMaps = this.listMaps(new QueryWrapper<ApsOrderGoodsSaleHistory>() //
            .select("factory_id fid ", "goods_id gid", "sale_config_id sid", "count(1) c").lambda()//
            .eq(ApsOrderGoodsSaleHistory::getGoodsId, goodsId)//
            .eq(ApsOrderGoodsSaleHistory::getYear, beginDate.getYear())//
            .groupBy(ApsOrderGoodsSaleHistory::getFactoryId, ApsOrderGoodsSaleHistory::getGoodsId, ApsOrderGoodsSaleHistory::getSaleConfigId));
//        log.info("listGoodsSaleMaps {}", JSON.toJSONString(listGoodsSaleMaps));
        if (log.isDebugEnabled()) {
          log.debug("listGoodsSaleMaps {}", JSON.toJSONString(listGoodsSaleMaps));
        }
      });

      // 查询已生成的订单历史销售配置 查询销售配置ID
      Map<Long, Long> saleHistoryIdMap = new HashMap<>();
      runnableList.add(() -> {
        Map<Long, Long> tMap = this.lambdaQuery().select(BaseEntity::getId, ApsOrderGoodsSaleHistory::getSaleConfigId)//
            .eq(ApsOrderGoodsSaleHistory::getGoodsId, goodsId).eq(ApsOrderGoodsSaleHistory::getYear, beginDate.getYear()).list()//
            .stream().collect(Collectors.toMap(ApsOrderGoodsSaleHistory::getSaleConfigId, BaseEntity::getId));
        saleHistoryIdMap.putAll(tMap);
        if (log.isDebugEnabled()) {
          log.debug("tMap {}", JSON.toJSONString(tMap));
        }
      });
      // 查询商品销售配置
//      Map<Long, List<ApsSaleConfig>> saleParentMap = new HashMap<>();
      Map<Long, ApsSaleConfig> saleAllMap = new HashMap<>();
      runnableList.add(() -> {
        Map<Long, ApsSaleConfig> saleParentMapTmp = apsSaleConfigService.list(new MPJLambdaWrapper<ApsSaleConfig>() //
            .leftJoin(ApsGoodsSaleItem.class, ApsGoodsSaleItem::getSaleConfigId, ApsSaleConfig::getId) //
            .eq(ApsGoodsSaleItem::getGoodsId, goodsId)).stream().collect(Collectors.toMap(BaseEntity::getId, Function.identity()));
//            .stream().collect(Collectors.groupingBy(ApsSaleConfig::getParentId,//
//            Collectors.collectingAndThen(Collectors.<ApsSaleConfig>toList(),//
//                list -> list.stream().sorted(Comparator.comparing(ApsSaleConfig::getSaleCode)).toList())));
//        saleParentMap.putAll(saleParentMapTmp);
        if (log.isDebugEnabled()) {
          log.debug("saleParentMapTmp {}", JSON.toJSONString(saleParentMapTmp));
        }
        Set<Long> idSet = saleParentMapTmp.values().stream().map(ApsSaleConfig::getParentId).collect(Collectors.toSet());
        saleAllMap.putAll(saleParentMapTmp);
        saleAllMap.putAll(apsSaleConfigService.listByIds(idSet).stream().collect(Collectors.toMap(BaseEntity::getId, Function.identity())));
      });

      RunUtils.run("selectOrder2History", runnableList);


      Map<Long, ApsOrderGoodsSaleHistoryCount> historyCountMap = apsOrderGoodsSaleConfigList.stream().collect(Collectors.toMap(ApsOrderGoodsSaleHistoryCount::getSaleConfigId, Function.identity()));
      saleAllMap.values().stream().filter(t -> Objects.equals(t.getIsValue(), 1)).collect(Collectors.groupingBy(ApsSaleConfig::getParentId,//
          Collectors.collectingAndThen(Collectors.<ApsSaleConfig>toList(),//
              list -> list.stream().sorted(Comparator.comparing(ApsSaleConfig::getSaleCode)).toList()))).forEach((p, ll) -> {
        BigDecimal useBigDecimal = new BigDecimal(0);
        for (int i = 0; i < ll.size() - 1; i++) {
          ApsSaleConfig saleConfig = ll.get(i);
          ApsSaleConfig parentSaleConfig = saleAllMap.getOrDefault(saleConfig.getParentId(), new ApsSaleConfig());
          ApsOrderGoodsSaleHistoryCount historyCount = historyCountMap.getOrDefault(saleConfig.getId(), new ApsOrderGoodsSaleHistoryCount());
          Long o = $.firstNotNull(historyCount.getTotal(), 0L);
          BigDecimal tmp = goodsCount.get() != 0 ? BigDecimal.valueOf(o * 1.0 / goodsCount.get()).setScale(4, RoundingMode.HALF_UP) : BigDecimal.ZERO;
          useBigDecimal = useBigDecimal.add(tmp);
          sale2history(goods, historyCountMap, saleConfig, saleHistoryIdMap, beginDate, tmp, insertList, parentSaleConfig);
        }
        ApsSaleConfig saleConfig = ll.getLast();
        ApsSaleConfig parentSaleConfig = saleAllMap.getOrDefault(saleConfig.getParentId(), new ApsSaleConfig());
        sale2history(goods, historyCountMap, saleConfig, saleHistoryIdMap, beginDate, BigDecimal.ONE.subtract(useBigDecimal), insertList, parentSaleConfig);
      });

    });
    this.updateBatchById(insertList.stream().filter(t -> Objects.nonNull(t.getId())).toList());
    this.saveBatch(insertList.stream().filter(t -> Objects.isNull(t.getId())).toList());

    return res;
  }

  private void sale2history(ApsGoods goods, Map<Long, ApsOrderGoodsSaleHistoryCount> historyCountMap, ApsSaleConfig saleConfig, //
                            Map<Long, Long> saleHistoryIdMap, LocalDateTime beginDate, BigDecimal useBigDecimal, List<ApsOrderGoodsSaleHistory> insertList //
      , ApsSaleConfig parentSaleConfig) {
    ApsOrderGoodsSaleHistoryCount historyCount = historyCountMap.getOrDefault(saleConfig.getId(), new ApsOrderGoodsSaleHistoryCount());
    if (Objects.isNull(historyCount)) {
      return;
    }
    ApsOrderGoodsSaleHistory history = apsOrderGoodsSaleHistory(saleHistoryIdMap, saleConfig, goods, beginDate, parentSaleConfig);
    String month = decimalFormat("00", beginDate.getMonthValue());
    ReflectUtil.setFieldValue(history, FieldUtils.getField(ApsOrderGoodsSaleHistory.class, "monthRatio" + month), useBigDecimal);
    ReflectUtil.setFieldValue(history, FieldUtils.getField(ApsOrderGoodsSaleHistory.class, "monthCount" + month), historyCount.getTotal());
    insertList.add(history);
  }

  private ApsOrderGoodsSaleHistory apsOrderGoodsSaleHistory(Map<Long, Long> saleHistoryIdMap, ApsSaleConfig apsSaleConfig, ApsGoods apsGoods, LocalDateTime localDateTime, ApsSaleConfig parentSaleConfig) {
    ApsOrderGoodsSaleHistory history = new ApsOrderGoodsSaleHistory();
    history.setId(saleHistoryIdMap.get(apsSaleConfig.getId()));
    return history.setSaleConfigId(apsSaleConfig.getId()).setSaleParentId(apsSaleConfig.getParentId()) //
        .setGoodsId(apsGoods.getId()).setGoodsName(apsGoods.getGoodsName()).setFactoryId(apsGoods.getFactoryId()).setYear(localDateTime.getYear())//
        .setGoodsName(history.getGoodsName()).setSaleConfigName(apsSaleConfig.getSaleName()).setSaleParentConfigName(parentSaleConfig.getSaleName());
  }


  // 以下为私有对象封装

  public @Override void setName(List<? extends ApsOrderGoodsSaleHistoryDto> list) {

    //   setNameService.setName(list, SetNamePojoUtils.FACTORY, SetNamePojoUtils.OP_USER_NAME);

    BigDecimalUtils.valueExpand(list, 100, BigDecimalUtils.MatchType.likeLeft, "monthRatio");

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


    q.orderByDesc(ApsOrderGoodsSaleHistory::getGoodsId, ApsOrderGoodsSaleHistory::getSaleParentId, ApsOrderGoodsSaleHistory::getSaleConfigId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsOrderGoodsSaleHistory> page) {

    tableHeaderService.listByBizKey(page, "ApsOrderGoodsSaleHistoryService#queryPageList");

  }


}

