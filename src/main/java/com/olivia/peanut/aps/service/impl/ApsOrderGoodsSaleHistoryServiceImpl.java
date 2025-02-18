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
import com.olivia.peanut.aps.model.ApsGoods;
import com.olivia.peanut.aps.model.ApsOrderGoodsSaleConfig;
import com.olivia.peanut.aps.model.ApsOrderGoodsSaleHistory;
import com.olivia.peanut.aps.model.ApsSaleConfig;
import com.olivia.peanut.aps.service.ApsGoodsService;
import com.olivia.peanut.aps.service.ApsOrderGoodsSaleConfigService;
import com.olivia.peanut.aps.service.ApsOrderGoodsSaleHistoryService;
import com.olivia.peanut.aps.service.ApsSaleConfigService;
import com.olivia.peanut.portal.service.BaseTableHeaderService;
import com.olivia.sdk.filter.LoginUserContext;
import com.olivia.sdk.service.SetNameService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.BaseEntity;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.RunUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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
    LocalDate beginDate = switch (req.getSelectType()) {
      case MONTH -> LocalDate.now().minusMonths(1);
      case DAY -> LocalDate.now().minusDays(1);
    };

    List<Long> goodsIdList = goodsList.stream().map(BaseEntity::getId).toList();
    List<Long> factoryIdList = goodsList.stream().map(ApsGoods::getFactoryId).toList();
    List<ApsOrderGoodsSaleConfig> apsOrderGoodsSaleConfigList = apsOrderGoodsSaleConfigService.list(new LambdaQueryWrapper<ApsOrderGoodsSaleConfig>()//
        .in(ApsOrderGoodsSaleConfig::getFactoryId, factoryIdList).select(ApsOrderGoodsSaleConfig::getConfigId).in(ApsOrderGoodsSaleConfig::getGoodsId, goodsIdList).gt(BaseEntity::getCreateTime, beginDate));

    if (CollUtil.isEmpty(apsOrderGoodsSaleConfigList)) {
      log.info("selectOrder2History apsOrderGoodsSaleConfigList is null");
      return res;
    }
    List<ApsOrderGoodsSaleHistory> insertList = new ArrayList<>();

    List<Runnable> runnableList = new ArrayList<>();

    runnableList.add(() -> {


      List<Map<String, Object>> listGoodsSaleMaps = this.listMaps(new QueryWrapper<ApsOrderGoodsSaleHistory>().select("factory_id fid ", "goods_id gid", "sale_config_id sid", "count(1) c").lambda().in(ApsOrderGoodsSaleHistory::getFactoryId, factoryIdList) //
          .in(ApsOrderGoodsSaleHistory::getGoodsId, goodsIdList)//
          .eq(ApsOrderGoodsSaleHistory::getYear, beginDate.getYear())//
          .groupBy(ApsOrderGoodsSaleHistory::getFactoryId, ApsOrderGoodsSaleHistory::getGoodsId, ApsOrderGoodsSaleHistory::getSaleConfigId));
      log.info("listGoodsSaleMaps {}", JSON.toJSONString(listGoodsSaleMaps));

    });
    runnableList.add(() -> {
      List<Map<String, Object>> listGoodsMaps = this.listMaps(new QueryWrapper<ApsOrderGoodsSaleHistory>().select("factory_id fid ", "goods_id gid", "count(1) c").lambda().in(ApsOrderGoodsSaleHistory::getFactoryId, factoryIdList) //
          .in(ApsOrderGoodsSaleHistory::getGoodsId, goodsIdList)//
          .eq(ApsOrderGoodsSaleHistory::getYear, beginDate.getYear())//
          .groupBy(ApsOrderGoodsSaleHistory::getFactoryId, ApsOrderGoodsSaleHistory::getGoodsId));
      log.info("listGoodsMaps {}", JSON.toJSONString(listGoodsMaps));
    });
    Map<Long, Long> saleHistoryIdMap = new HashMap<>();
    runnableList.add(() -> {
      Map<Long, Long> tMap = this.lambdaQuery().select(BaseEntity::getId, ApsOrderGoodsSaleHistory::getSaleConfigId).in(ApsOrderGoodsSaleHistory::getFactoryId, factoryIdList).eq(ApsOrderGoodsSaleHistory::getYear, beginDate.getYear()).list().stream().collect(Collectors.toMap(ApsOrderGoodsSaleHistory::getSaleConfigId, BaseEntity::getId));
      saleHistoryIdMap.putAll(tMap);
    });


    Map<Long, List<ApsSaleConfig>> saleParentMap = new HashMap<>();
    runnableList.add(() -> {
      Map<Long, List<ApsSaleConfig>> saleParentMapTmp = apsSaleConfigService.list().stream().collect(Collectors.groupingBy(ApsSaleConfig::getParentId,//
          Collectors.collectingAndThen(Collectors.<ApsSaleConfig>toList(),//
              list -> list.stream().sorted(Comparator.comparing(ApsSaleConfig::getSaleCode)).toList())));
      saleParentMap.putAll(saleParentMapTmp);
    });


    RunUtils.run("selectOrder2History", runnableList);

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


    $.lambdaQueryWrapper(q, obj, ApsOrderGoodsSaleHistory.class
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

