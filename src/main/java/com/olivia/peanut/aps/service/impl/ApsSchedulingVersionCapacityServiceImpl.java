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
import com.olivia.sdk.utils.LambdaQueryUtil;
import com.olivia.sdk.utils.Str;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

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


  @SuppressWarnings(Str.UN_CHECKED)
  private MPJLambdaWrapper<ApsSchedulingVersionCapacity> getWrapper(ApsSchedulingVersionCapacityDto obj) {
    MPJLambdaWrapper<ApsSchedulingVersionCapacity> q = new MPJLambdaWrapper<>();

    LambdaQueryUtil.lambdaQueryWrapper(q, obj, ApsSchedulingVersionCapacity.class, ApsSchedulingVersionCapacity::getSchedulingVersionId//
        , ApsSchedulingVersionCapacity::getCurrentDay, ApsSchedulingVersionCapacity::getOrderId, ApsSchedulingVersionCapacity::getGoodsId);
    q.orderByDesc(ApsSchedulingVersionCapacity::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsSchedulingVersionCapacity> page) {

    ServiceComment.header(page, "ApsSchedulingVersionCapacityService#queryPageList");

  }


}

