package com.olivia.peanut.aps.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.api.entity.apsSchedulingGoodsStatusDate.*;
import com.olivia.peanut.aps.mapper.ApsSchedulingGoodsStatusDateMapper;
import com.olivia.peanut.aps.model.ApsSchedulingGoodsStatusDate;
import com.olivia.peanut.aps.service.ApsSchedulingGoodsStatusDateService;
import com.olivia.sdk.ann.SetUserName;
import com.olivia.sdk.comment.ServiceComment;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 订单商品状态表(ApsSchedulingGoodsStatusDate)表服务实现类
 *
 * @author peanut
 * @since 2024-06-14 21:35:09
 */
@Service("apsSchedulingGoodsStatusDateService")
@Transactional
public class ApsSchedulingGoodsStatusDateServiceImpl extends MPJBaseServiceImpl<ApsSchedulingGoodsStatusDateMapper, ApsSchedulingGoodsStatusDate> implements
    ApsSchedulingGoodsStatusDateService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();


  public @Override ApsSchedulingGoodsStatusDateQueryListRes queryList(ApsSchedulingGoodsStatusDateQueryListReq req) {

    MPJLambdaWrapper<ApsSchedulingGoodsStatusDate> q = getWrapper(req.getData());
    List<ApsSchedulingGoodsStatusDate> list = this.list(q);

    List<ApsSchedulingGoodsStatusDateDto> dataList = list.stream().map(t -> $.copy(t, ApsSchedulingGoodsStatusDateDto.class)).collect(Collectors.toList());
    //  this.setName(dataList);
    ((ApsSchedulingGoodsStatusDateService) AopContext.currentProxy()).setName(dataList);

    return new ApsSchedulingGoodsStatusDateQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<ApsSchedulingGoodsStatusDateExportQueryPageListInfoRes> queryPageList(ApsSchedulingGoodsStatusDateExportQueryPageListReq req) {

    DynamicsPage<ApsSchedulingGoodsStatusDate> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsSchedulingGoodsStatusDate> q = getWrapper(req.getData());
    List<ApsSchedulingGoodsStatusDateExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsSchedulingGoodsStatusDate> list = this.page(page, q);
      IPage<ApsSchedulingGoodsStatusDateExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsSchedulingGoodsStatusDateExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsSchedulingGoodsStatusDateExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<ApsSchedulingGoodsStatusDateExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsSchedulingGoodsStatusDateExportQueryPageListInfoRes.class);
    // this.setName(listInfoRes);
    ((ApsSchedulingGoodsStatusDateService) AopContext.currentProxy()).setName(listInfoRes);

    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装

  @SetUserName
  public @Override void setName(List<? extends ApsSchedulingGoodsStatusDateDto> apsSchedulingGoodsStatusDateDtoList) {

    if (CollUtil.isEmpty(apsSchedulingGoodsStatusDateDtoList)) {
    }


  }


  private MPJLambdaWrapper<ApsSchedulingGoodsStatusDate> getWrapper(ApsSchedulingGoodsStatusDateDto obj) {
    MPJLambdaWrapper<ApsSchedulingGoodsStatusDate> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(Objects.nonNull(obj.getSchedulingId()), ApsSchedulingGoodsStatusDate::getSchedulingId, obj.getSchedulingId())
          .eq(Objects.nonNull(obj.getOrderId()), ApsSchedulingGoodsStatusDate::getOrderId, obj.getOrderId())
          .eq(Objects.nonNull(obj.getGoodsId()), ApsSchedulingGoodsStatusDate::getGoodsId, obj.getGoodsId())
          .eq(Objects.nonNull(obj.getGoodsStatusId()), ApsSchedulingGoodsStatusDate::getGoodsStatusId, obj.getGoodsStatusId())
          .eq(Objects.nonNull(obj.getFactoryId()), ApsSchedulingGoodsStatusDate::getFactoryId, obj.getFactoryId())
          .eq(Objects.nonNull(obj.getStatusIndex()), ApsSchedulingGoodsStatusDate::getStatusIndex, obj.getStatusIndex())

      ;
    }
    q.orderByDesc(ApsSchedulingGoodsStatusDate::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsSchedulingGoodsStatusDate> page) {

    ServiceComment.header(page, "ApsSchedulingGoodsStatusDateService#queryPageList");

  }


}

