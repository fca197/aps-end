package com.olivia.peanut.aps.service.impl;

import org.springframework.aop.framework.AopContext;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import jakarta.annotation.Resource;
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
import com.olivia.peanut.aps.mapper.ApsOrderGoodsSaleHistoryMapper;
import com.olivia.peanut.aps.model.ApsOrderGoodsSaleHistory;
import com.olivia.peanut.aps.service.ApsOrderGoodsSaleHistoryService;
import cn.hutool.core.collection.CollUtil;
//import com.olivia.peanut.aps.service.BaseTableHeaderService;
import com.olivia.peanut.portal.service.BaseTableHeaderService;
import com.olivia.peanut.aps.api.entity.apsOrderGoodsSaleHistory.*;
import com.olivia.peanut.util.SetNamePojoUtils;
import com.olivia.sdk.service.SetNameService;

/**
 * 销售规划订单历史销售占比(ApsOrderGoodsSaleHistory)表服务实现类
 *
 * @author makejava
 * @since 2025-02-18 14:28:42
 */
@Service("apsOrderGoodsSaleHistoryService")
@Transactional
public class ApsOrderGoodsSaleHistoryServiceImpl extends MPJBaseServiceImpl<ApsOrderGoodsSaleHistoryMapper, ApsOrderGoodsSaleHistory> implements ApsOrderGoodsSaleHistoryService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  @Resource
  BaseTableHeaderService tableHeaderService;
  @Resource
  SetNameService setNameService;


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
        , ApsOrderGoodsSaleHistory::getGoodsId //
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

