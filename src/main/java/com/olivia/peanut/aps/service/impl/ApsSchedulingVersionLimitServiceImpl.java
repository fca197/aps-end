package com.olivia.peanut.aps.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.api.entity.apsSchedulingVersionLimit.*;
import com.olivia.peanut.aps.mapper.ApsSchedulingVersionLimitMapper;
import com.olivia.peanut.aps.model.ApsSchedulingVersionLimit;
import com.olivia.peanut.aps.service.ApsSchedulingVersionLimitService;
import com.olivia.sdk.ann.SetUserName;
import com.olivia.sdk.comment.ServiceComment;
import com.olivia.sdk.utils.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * (ApsSchedulingVersionLimit)表服务实现类
 *
 * @author peanut
 * @since 2024-04-19 14:56:59
 */
@Service("apsSchedulingVersionLimitService")
@Transactional
public class ApsSchedulingVersionLimitServiceImpl extends MPJBaseServiceImpl<ApsSchedulingVersionLimitMapper, ApsSchedulingVersionLimit> implements
    ApsSchedulingVersionLimitService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();


  public @Override ApsSchedulingVersionLimitQueryListRes queryList(ApsSchedulingVersionLimitQueryListReq req) {

    MPJLambdaWrapper<ApsSchedulingVersionLimit> q = getWrapper(req.getData());
    List<ApsSchedulingVersionLimit> list = this.list(q);

    List<ApsSchedulingVersionLimitDto> dataList = list.stream().map(t -> $.copy(t, ApsSchedulingVersionLimitDto.class)).collect(Collectors.toList());
    //  this.setName(dataList);
    return new ApsSchedulingVersionLimitQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<ApsSchedulingVersionLimitExportQueryPageListInfoRes> queryPageList(ApsSchedulingVersionLimitExportQueryPageListReq req) {

    DynamicsPage<ApsSchedulingVersionLimit> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsSchedulingVersionLimit> q = getWrapper(req.getData());
    List<ApsSchedulingVersionLimitExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsSchedulingVersionLimit> list = this.page(page, q);
      IPage<ApsSchedulingVersionLimitExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsSchedulingVersionLimitExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsSchedulingVersionLimitExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<ApsSchedulingVersionLimitExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsSchedulingVersionLimitExportQueryPageListInfoRes.class);
    // this.setName(listInfoRes);
    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装

  @SetUserName
  public @Override void setName(List<? extends ApsSchedulingVersionLimitDto> apsSchedulingVersionLimitDtoList) {

    if (CollUtil.isEmpty(apsSchedulingVersionLimitDtoList)) {
    }


  }


  @SuppressWarnings({Str.UN_CHECKED})
  private MPJLambdaWrapper<ApsSchedulingVersionLimit> getWrapper(ApsSchedulingVersionLimitDto obj) {
    MPJLambdaWrapper<ApsSchedulingVersionLimit> q = new MPJLambdaWrapper<>();

    LambdaQueryUtil.lambdaQueryWrapper(q, obj, ApsSchedulingVersionLimit.class, BaseEntity::getId,
        ApsSchedulingVersionLimit::getShowName, ApsSchedulingVersionLimit::getFieldName, ApsSchedulingVersionLimit::getFieldValue);
    q.orderByDesc(ApsSchedulingVersionLimit::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsSchedulingVersionLimit> page) {

    ServiceComment.header(page, "ApsSchedulingVersionLimitService#queryPageList");

  }


}

