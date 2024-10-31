package com.olivia.peanut.aps.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.api.entity.apsSchedulingVersionCapacity.*;
import com.olivia.peanut.aps.mapper.ApsSchedulingVersionCapacityMapper;
import com.olivia.peanut.aps.model.ApsSchedulingVersionCapacity;
import com.olivia.peanut.aps.service.ApsSchedulingVersionCapacityService;
import com.olivia.sdk.ann.SetUserName;
import com.olivia.sdk.comment.ServiceComment;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * (ApsSchedulingVersionCapacity)表服务实现类
 *
 * @author peanut
 * @since 2024-04-18 14:57:34
 */
@Service("apsSchedulingVersionCapacityService")
@Transactional
public class ApsSchedulingVersionCapacityServiceImpl extends MPJBaseServiceImpl<ApsSchedulingVersionCapacityMapper, ApsSchedulingVersionCapacity> implements
    ApsSchedulingVersionCapacityService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();


  public @Override ApsSchedulingVersionCapacityQueryListRes queryList(ApsSchedulingVersionCapacityQueryListReq req) {

    MPJLambdaWrapper<ApsSchedulingVersionCapacity> q = getWrapper(req.getData());
    List<ApsSchedulingVersionCapacity> list = this.list(q);

    List<ApsSchedulingVersionCapacityDto> dataList = list.stream().map(t -> $.copy(t, ApsSchedulingVersionCapacityDto.class)).collect(Collectors.toList());
//    this.setName(dataList);
    ((ApsSchedulingVersionCapacityService) AopContext.currentProxy()).setName(dataList);

    return new ApsSchedulingVersionCapacityQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<ApsSchedulingVersionCapacityExportQueryPageListInfoRes> queryPageList(ApsSchedulingVersionCapacityExportQueryPageListReq req) {

    DynamicsPage<ApsSchedulingVersionCapacity> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsSchedulingVersionCapacity> q = getWrapper(req.getData());
    List<ApsSchedulingVersionCapacityExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsSchedulingVersionCapacity> list = this.page(page, q);
      IPage<ApsSchedulingVersionCapacityExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsSchedulingVersionCapacityExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsSchedulingVersionCapacityExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<ApsSchedulingVersionCapacityExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsSchedulingVersionCapacityExportQueryPageListInfoRes.class);
    // this.setName(listInfoRes);
    ((ApsSchedulingVersionCapacityService) AopContext.currentProxy()).setName(listInfoRes);

    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装

  @SetUserName
  public @Override void setName(List<? extends ApsSchedulingVersionCapacityDto> apsSchedulingVersionCapacityDtoList) {

    if (CollUtil.isEmpty(apsSchedulingVersionCapacityDtoList)) {
    }


  }


  private MPJLambdaWrapper<ApsSchedulingVersionCapacity> getWrapper(ApsSchedulingVersionCapacityDto obj) {
    MPJLambdaWrapper<ApsSchedulingVersionCapacity> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(Objects.nonNull(obj.getSchedulingVersionId()), ApsSchedulingVersionCapacity::getSchedulingVersionId, obj.getSchedulingVersionId())
          .eq(Objects.nonNull(obj.getCurrentDate()), ApsSchedulingVersionCapacity::getCurrentDay, obj.getCurrentDate())
          .eq(Objects.nonNull(obj.getOrderId()), ApsSchedulingVersionCapacity::getOrderId, obj.getOrderId())
          .eq(Objects.nonNull(obj.getGoodsId()), ApsSchedulingVersionCapacity::getGoodsId, obj.getGoodsId())
          .eq(StringUtils.isNoneBlank(obj.getField0()), ApsSchedulingVersionCapacity::getField0, obj.getField0())
          .eq(StringUtils.isNoneBlank(obj.getField1()), ApsSchedulingVersionCapacity::getField1, obj.getField1())
          .eq(StringUtils.isNoneBlank(obj.getField2()), ApsSchedulingVersionCapacity::getField2, obj.getField2())
          .eq(StringUtils.isNoneBlank(obj.getField3()), ApsSchedulingVersionCapacity::getField3, obj.getField3())
          .eq(StringUtils.isNoneBlank(obj.getField4()), ApsSchedulingVersionCapacity::getField4, obj.getField4())
          .eq(StringUtils.isNoneBlank(obj.getField5()), ApsSchedulingVersionCapacity::getField5, obj.getField5())
          .eq(StringUtils.isNoneBlank(obj.getField6()), ApsSchedulingVersionCapacity::getField6, obj.getField6())
          .eq(StringUtils.isNoneBlank(obj.getField7()), ApsSchedulingVersionCapacity::getField7, obj.getField7())
          .eq(StringUtils.isNoneBlank(obj.getField8()), ApsSchedulingVersionCapacity::getField8, obj.getField8())
          .eq(StringUtils.isNoneBlank(obj.getField9()), ApsSchedulingVersionCapacity::getField9, obj.getField9())
          .eq(StringUtils.isNoneBlank(obj.getField10()), ApsSchedulingVersionCapacity::getField10, obj.getField10())
          .eq(StringUtils.isNoneBlank(obj.getField11()), ApsSchedulingVersionCapacity::getField11, obj.getField11())
          .eq(StringUtils.isNoneBlank(obj.getField12()), ApsSchedulingVersionCapacity::getField12, obj.getField12())
          .eq(StringUtils.isNoneBlank(obj.getField13()), ApsSchedulingVersionCapacity::getField13, obj.getField13())
          .eq(StringUtils.isNoneBlank(obj.getField14()), ApsSchedulingVersionCapacity::getField14, obj.getField14())
          .eq(StringUtils.isNoneBlank(obj.getField15()), ApsSchedulingVersionCapacity::getField15, obj.getField15())
          .eq(StringUtils.isNoneBlank(obj.getField16()), ApsSchedulingVersionCapacity::getField16, obj.getField16())
          .eq(StringUtils.isNoneBlank(obj.getField17()), ApsSchedulingVersionCapacity::getField17, obj.getField17())
          .eq(StringUtils.isNoneBlank(obj.getField18()), ApsSchedulingVersionCapacity::getField18, obj.getField18())
          .eq(StringUtils.isNoneBlank(obj.getField19()), ApsSchedulingVersionCapacity::getField19, obj.getField19())
          .eq(StringUtils.isNoneBlank(obj.getField20()), ApsSchedulingVersionCapacity::getField20, obj.getField20())
          .eq(StringUtils.isNoneBlank(obj.getLimitResult()), ApsSchedulingVersionCapacity::getLimitResult, obj.getLimitResult())
          .eq(Objects.nonNull(obj.getResultType()), ApsSchedulingVersionCapacity::getResultType, obj.getResultType())
          .eq(Objects.nonNull(obj.getNumberIndex()), ApsSchedulingVersionCapacity::getNumberIndex, obj.getNumberIndex())

      ;
    }
    q.orderByDesc(ApsSchedulingVersionCapacity::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsSchedulingVersionCapacity> page) {

    ServiceComment.header(page, "ApsSchedulingVersionCapacityService#queryPageList");

  }


}

