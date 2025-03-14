package com.olivia.peanut.aps.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.api.entity.apsSchedulingDayConfigVersionDetail.*;
import com.olivia.peanut.aps.mapper.ApsSchedulingDayConfigVersionDetailMapper;
import com.olivia.peanut.aps.model.ApsSchedulingDayConfigVersionDetail;
import com.olivia.peanut.aps.service.ApsSchedulingDayConfigVersionDetailService;
import com.olivia.peanut.base.service.BaseTableHeaderService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 排程版本配置明细表(ApsSchedulingDayConfigVersionDetail)表服务实现类
 *
 * @author peanut
 * @since 2024-07-19 19:19:58
 */
@Service("apsSchedulingDayConfigVersionDetailService")
@Transactional
public class ApsSchedulingDayConfigVersionDetailServiceImpl extends MPJBaseServiceImpl<ApsSchedulingDayConfigVersionDetailMapper, ApsSchedulingDayConfigVersionDetail> implements
    ApsSchedulingDayConfigVersionDetailService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  @Resource
  BaseTableHeaderService tableHeaderService;


  public @Override ApsSchedulingDayConfigVersionDetailQueryListRes queryList(ApsSchedulingDayConfigVersionDetailQueryListReq req) {

    MPJLambdaWrapper<ApsSchedulingDayConfigVersionDetail> q = getWrapper(req.getData());
    List<ApsSchedulingDayConfigVersionDetail> list = this.list(q);

    List<ApsSchedulingDayConfigVersionDetailDto> dataList = list.stream().map(t -> $.copy(t, ApsSchedulingDayConfigVersionDetailDto.class)).collect(Collectors.toList());
    ((ApsSchedulingDayConfigVersionDetailService) AopContext.currentProxy()).setName(dataList);
    return new ApsSchedulingDayConfigVersionDetailQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<ApsSchedulingDayConfigVersionDetailExportQueryPageListInfoRes> queryPageList(ApsSchedulingDayConfigVersionDetailExportQueryPageListReq req) {

    DynamicsPage<ApsSchedulingDayConfigVersionDetail> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsSchedulingDayConfigVersionDetail> q = getWrapper(req.getData());
    List<ApsSchedulingDayConfigVersionDetailExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsSchedulingDayConfigVersionDetail> list = this.page(page, q);
      IPage<ApsSchedulingDayConfigVersionDetailExportQueryPageListInfoRes> dataList = list.convert(
          t -> $.copy(t, ApsSchedulingDayConfigVersionDetailExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsSchedulingDayConfigVersionDetailExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<ApsSchedulingDayConfigVersionDetailExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsSchedulingDayConfigVersionDetailExportQueryPageListInfoRes.class);
    ((ApsSchedulingDayConfigVersionDetailService) AopContext.currentProxy()).setName(listInfoRes);

    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装

  public @Override void setName(List<? extends ApsSchedulingDayConfigVersionDetailDto> apsSchedulingDayConfigVersionDetailDtoList) {

    if (CollUtil.isEmpty(apsSchedulingDayConfigVersionDetailDtoList)) {
    }


  }


  private MPJLambdaWrapper<ApsSchedulingDayConfigVersionDetail> getWrapper(ApsSchedulingDayConfigVersionDetailDto obj) {
    MPJLambdaWrapper<ApsSchedulingDayConfigVersionDetail> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(Objects.nonNull(obj.getSchedulingDayId()), ApsSchedulingDayConfigVersionDetail::getSchedulingDayId, obj.getSchedulingDayId())
          .eq(StringUtils.isNoneBlank(obj.getConfigBizType()), ApsSchedulingDayConfigVersionDetail::getConfigBizType, obj.getConfigBizType())
          .eq(Objects.nonNull(obj.getConfigBizId()), ApsSchedulingDayConfigVersionDetail::getConfigBizId, obj.getConfigBizId())
          .eq(StringUtils.isNoneBlank(obj.getConfigBizName()), ApsSchedulingDayConfigVersionDetail::getConfigBizName, obj.getConfigBizName())
          .eq(Objects.nonNull(obj.getConfigBizNum()), ApsSchedulingDayConfigVersionDetail::getConfigBizNum, obj.getConfigBizNum())
          .eq(Objects.nonNull(obj.getOrderId()), ApsSchedulingDayConfigVersionDetail::getOrderId, obj.getOrderId())
          .eq(StringUtils.isNoneBlank(obj.getOrderNo()), ApsSchedulingDayConfigVersionDetail::getOrderNo, obj.getOrderNo())
          .eq(Objects.nonNull(obj.getIsMatch()), ApsSchedulingDayConfigVersionDetail::getIsMatch, obj.getIsMatch())
          .eq(Objects.nonNull(obj.getLoopIndex()), ApsSchedulingDayConfigVersionDetail::getLoopIndex, obj.getLoopIndex())
          .eq(Objects.nonNull(obj.getLoopEnough()), ApsSchedulingDayConfigVersionDetail::getLoopEnough, obj.getLoopEnough())

      ;
    }
    q.orderByDesc(ApsSchedulingDayConfigVersionDetail::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsSchedulingDayConfigVersionDetail> page) {

    tableHeaderService.listByBizKey(page, "ApsSchedulingDayConfigVersionDetailService#queryPageList");

  }


}

