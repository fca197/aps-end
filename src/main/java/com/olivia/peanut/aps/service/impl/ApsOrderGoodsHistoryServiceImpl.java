package com.olivia.peanut.aps.service.impl;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ReflectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.api.entity.apsOrderGoodsHistory.*;
import com.olivia.peanut.aps.api.entity.apsOrderGoodsSaleHistory.SelectOrder2HistoryReq;
import com.olivia.peanut.aps.api.entity.apsOrderGoodsSaleHistory.SelectOrder2HistoryRes;
import com.olivia.peanut.aps.mapper.ApsOrderGoodsHistoryMapper;
import com.olivia.peanut.aps.model.ApsGoods;
import com.olivia.peanut.aps.model.ApsOrderGoods;
import com.olivia.peanut.aps.model.ApsOrderGoodsHistory;
import com.olivia.peanut.aps.service.ApsGoodsService;
import com.olivia.peanut.aps.service.ApsOrderGoodsHistoryService;
import com.olivia.peanut.aps.service.ApsOrderGoodsService;
import com.olivia.peanut.base.service.BaseTableHeaderService;
import com.olivia.sdk.service.SetNameService;
import com.olivia.sdk.utils.*;
import jakarta.annotation.Resource;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 历史订单记录(ApsOrderGoodsHistory)表服务实现类
 *
 * @author makejava
 * @since 2025-02-20 17:18:46
 */
@Service("apsOrderGoodsHistoryService")
@Transactional
public class ApsOrderGoodsHistoryServiceImpl extends MPJBaseServiceImpl<ApsOrderGoodsHistoryMapper, ApsOrderGoodsHistory> implements ApsOrderGoodsHistoryService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  @Resource
  BaseTableHeaderService tableHeaderService;
  @Resource
  SetNameService setNameService;

  @Resource
  ApsOrderGoodsService apsOrderGoodsService;
  @Resource
  ApsGoodsService apsGoodsService;

  public @Override ApsOrderGoodsHistoryQueryListRes queryList(ApsOrderGoodsHistoryQueryListReq req) {

    MPJLambdaWrapper<ApsOrderGoodsHistory> q = getWrapper(req.getData());
    List<ApsOrderGoodsHistory> list = this.list(q);

    List<ApsOrderGoodsHistoryDto> dataList = list.stream().map(t -> $.copy(t, ApsOrderGoodsHistoryDto.class)).collect(Collectors.toList());
    ((ApsOrderGoodsHistoryService) AopContext.currentProxy()).setName(dataList);
    return new ApsOrderGoodsHistoryQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<ApsOrderGoodsHistoryExportQueryPageListInfoRes> queryPageList(ApsOrderGoodsHistoryExportQueryPageListReq req) {

    DynamicsPage<ApsOrderGoodsHistory> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsOrderGoodsHistory> q = getWrapper(req.getData());
    List<ApsOrderGoodsHistoryExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsOrderGoodsHistory> list = this.page(page, q);
      IPage<ApsOrderGoodsHistoryExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsOrderGoodsHistoryExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsOrderGoodsHistoryExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作 

    List<ApsOrderGoodsHistoryExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsOrderGoodsHistoryExportQueryPageListInfoRes.class);
    ((ApsOrderGoodsHistoryService) AopContext.currentProxy()).setName(listInfoRes);

    return DynamicsPage.init(page, listInfoRes);
  }

  @Override
  public SelectOrder2HistoryRes selectOrder2History(SelectOrder2HistoryReq req) {
    LocalDateTime beginDate = req.getBeginDate();
    long goodsTotal = this.apsOrderGoodsService.count(new LambdaUpdateWrapper<ApsOrderGoods>() //
        .ge(BaseEntity::getCreateTime, beginDate).le(BaseEntity::getCreateTime, req.getEndDate()));
    SelectOrder2HistoryRes res = new SelectOrder2HistoryRes();
    if (goodsTotal < 1) {
      return res;
    }
    List<ApsGoods> apsGoodsList = this.apsGoodsService.list();

    List<ApsOrderGoodsHistory> saveBatchList = new ArrayList<>();
    List<ApsOrderGoodsHistory> updateBatchList = new ArrayList<>();
    apsGoodsList.forEach(apsGoods -> {
      long goodsTotalTmp = this.apsOrderGoodsService.count(new LambdaUpdateWrapper<ApsOrderGoods>() //
          .eq(ApsOrderGoods::getGoodsId, apsGoods.getId())
          .ge(BaseEntity::getCreateTime, beginDate).le(BaseEntity::getCreateTime, req.getEndDate()));
      BigDecimal goodBigDecimal = BigDecimal.valueOf(goodsTotalTmp * 1.0 / goodsTotal).setScale(4, RoundingMode.DOWN);
      ApsOrderGoodsHistory goodsHistory = this.getOne(new LambdaQueryWrapper<ApsOrderGoodsHistory>().eq(ApsOrderGoodsHistory::getGoodsId, apsGoods.getId()).eq(ApsOrderGoodsHistory::getYear, beginDate.getYear()));
      if (goodsHistory == null) {
        goodsHistory = new ApsOrderGoodsHistory();
        goodsHistory.setGoodsId(apsGoods.getId()).setGoodsName(apsGoods.getGoodsName()).setFactoryId(apsGoods.getFactoryId()).setYear(beginDate.getYear());
      }
      ReflectUtil.setFieldValue(goodsHistory, FieldUtils.getField(ApsOrderGoodsHistory.class, "monthCount" + NumberUtil.decimalFormat("00", beginDate.getMonthValue())), goodsTotalTmp);
      ReflectUtil.setFieldValue(goodsHistory, FieldUtils.getField(ApsOrderGoodsHistory.class, "monthRatio" + NumberUtil.decimalFormat("00", beginDate.getMonthValue())), goodBigDecimal);
      if (Objects.isNull(goodsHistory.getId())) {
//        this.save(goodsHistory);
        saveBatchList.add(goodsHistory);
      } else {
        updateBatchList.add(goodsHistory);
      }
    });
    this.saveBatch(saveBatchList);
    this.updateBatchById(updateBatchList);
    return res;
  }


  // 以下为私有对象封装

  public @Override void setName(List<? extends ApsOrderGoodsHistoryDto> list) {

    //   setNameService.setName(list, SetNamePojoUtils.FACTORY, SetNamePojoUtils.OP_USER_NAME);

  }


  @SuppressWarnings("unchecked")
  private MPJLambdaWrapper<ApsOrderGoodsHistory> getWrapper(ApsOrderGoodsHistoryDto obj) {
    MPJLambdaWrapper<ApsOrderGoodsHistory> q = new MPJLambdaWrapper<>();


    LambdaQueryUtil.lambdaQueryWrapper(q, obj, ApsOrderGoodsHistory.class
        // 查询条件
        , ApsOrderGoodsHistory::getFactoryId //
        , ApsOrderGoodsHistory::getGoodsId //
        , ApsOrderGoodsHistory::getYear //
        , ApsOrderGoodsHistory::getMonthCount01 //
        , ApsOrderGoodsHistory::getMonthRatio01 //
        , ApsOrderGoodsHistory::getMonthCount02 //
        , ApsOrderGoodsHistory::getMonthRatio02 //
        , ApsOrderGoodsHistory::getMonthCount03 //
        , ApsOrderGoodsHistory::getMonthRatio03 //
        , ApsOrderGoodsHistory::getMonthCount04 //
        , ApsOrderGoodsHistory::getMonthRatio04 //
        , ApsOrderGoodsHistory::getMonthCount05 //
        , ApsOrderGoodsHistory::getMonthRatio05 //
        , ApsOrderGoodsHistory::getMonthCount06 //
        , ApsOrderGoodsHistory::getMonthRatio06 //
        , ApsOrderGoodsHistory::getMonthCount07 //
        , ApsOrderGoodsHistory::getMonthRatio07 //
        , ApsOrderGoodsHistory::getMonthCount08 //
        , ApsOrderGoodsHistory::getMonthRatio08 //
        , ApsOrderGoodsHistory::getMonthCount09 //
        , ApsOrderGoodsHistory::getMonthRatio09 //
        , ApsOrderGoodsHistory::getMonthCount10 //
        , ApsOrderGoodsHistory::getMonthRatio10 //
        , ApsOrderGoodsHistory::getMonthCount11 //
        , ApsOrderGoodsHistory::getMonthRatio11 //
        , ApsOrderGoodsHistory::getMonthCount12 //
        , ApsOrderGoodsHistory::getMonthRatio12 //
    );


    q.orderByDesc(ApsOrderGoodsHistory::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsOrderGoodsHistory> page) {

    tableHeaderService.listByBizKey(page, "ApsOrderGoodsHistoryService#queryPageList");

  }


}

