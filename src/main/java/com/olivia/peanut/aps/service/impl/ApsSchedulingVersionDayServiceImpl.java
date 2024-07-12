package com.olivia.peanut.aps.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.api.entity.apsSchedulingVersionDay.*;
import com.olivia.peanut.aps.mapper.ApsSchedulingVersionDayMapper;
import com.olivia.peanut.aps.model.ApsSchedulingVersionDay;
import com.olivia.peanut.aps.service.ApsSchedulingVersionCapacityService;
import com.olivia.peanut.aps.service.ApsSchedulingVersionDayService;
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
 * (ApsSchedulingVersionDay)表服务实现类
 *
 * @author peanut
 * @since 2024-04-23 14:37:05
 */
@Service("apsSchedulingVersionDayService")
@Transactional
public class ApsSchedulingVersionDayServiceImpl extends MPJBaseServiceImpl<ApsSchedulingVersionDayMapper, ApsSchedulingVersionDay> implements ApsSchedulingVersionDayService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();


  public @Override ApsSchedulingVersionDayQueryListRes queryList(ApsSchedulingVersionDayQueryListReq req) {

    MPJLambdaWrapper<ApsSchedulingVersionDay> q = getWrapper(req.getData());
    List<ApsSchedulingVersionDay> list = this.list(q);

    List<ApsSchedulingVersionDayDto> dataList = list.stream().map(t -> $.copy(t, ApsSchedulingVersionDayDto.class)).collect(Collectors.toList());
    //  this.setName(dataList);
    ((ApsSchedulingVersionDayServiceImpl) AopContext.currentProxy()).setName(dataList);

    return new ApsSchedulingVersionDayQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<ApsSchedulingVersionDayExportQueryPageListInfoRes> queryPageList(ApsSchedulingVersionDayExportQueryPageListReq req) {

    DynamicsPage<ApsSchedulingVersionDay> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsSchedulingVersionDay> q = getWrapper(req.getData());
    List<ApsSchedulingVersionDayExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsSchedulingVersionDay> list = this.page(page, q);
      IPage<ApsSchedulingVersionDayExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsSchedulingVersionDayExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsSchedulingVersionDayExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<ApsSchedulingVersionDayExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsSchedulingVersionDayExportQueryPageListInfoRes.class);
    // this.setName(listInfoRes);
    ((ApsSchedulingVersionDayServiceImpl) AopContext.currentProxy()).setName(listInfoRes);

    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装

  @SetUserName
  public @Override void setName(List<? extends ApsSchedulingVersionDayDto> apsSchedulingVersionDayDtoList) {

    if (CollUtil.isEmpty(apsSchedulingVersionDayDtoList)) {
    }


  }


  private MPJLambdaWrapper<ApsSchedulingVersionDay> getWrapper(ApsSchedulingVersionDayDto obj) {
    MPJLambdaWrapper<ApsSchedulingVersionDay> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(Objects.nonNull(obj.getVersionId()), ApsSchedulingVersionDay::getVersionId, obj.getVersionId())
          .eq(StringUtils.isNoneBlank(obj.getCurrentDay()), ApsSchedulingVersionDay::getCurrentDay, obj.getCurrentDay())
          .eq(Objects.nonNull(obj.getHasEnough()), ApsSchedulingVersionDay::getHasEnough, obj.getHasEnough())

      ;
    }
    q.orderByDesc(ApsSchedulingVersionDay::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsSchedulingVersionDay> page) {

    ServiceComment.header(page, "ApsSchedulingVersionDayService#queryPageList");

  }


}

