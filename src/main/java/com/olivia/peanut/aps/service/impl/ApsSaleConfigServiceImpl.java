package com.olivia.peanut.aps.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.api.entity.apsSaleConfig.*;
import com.olivia.peanut.aps.mapper.ApsSaleConfigMapper;
import com.olivia.peanut.aps.model.ApsSaleConfig;
import com.olivia.peanut.aps.service.ApsSaleConfigService;
import com.olivia.sdk.ann.SetUserName;
import com.olivia.sdk.comment.ServiceComment;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * (ApsSaleConfig)表服务实现类
 *
 * @author peanut
 * @since 2024-03-29 23:10:39
 */
@Service("apsSaleConfigService")
@Transactional
public class ApsSaleConfigServiceImpl extends MPJBaseServiceImpl<ApsSaleConfigMapper, ApsSaleConfig> implements ApsSaleConfigService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();


  public @Override ApsSaleConfigQueryListRes queryList(ApsSaleConfigQueryListReq req) {

    MPJLambdaWrapper<ApsSaleConfig> q = getWrapper(req.getData());
    List<ApsSaleConfig> list = this.list(q);

    List<ApsSaleConfigDto> dataList = list.stream().map(t -> $.copy(t, ApsSaleConfigDto.class)).collect(Collectors.toList());

    return new ApsSaleConfigQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<ApsSaleConfigExportQueryPageListInfoRes> queryPageList(ApsSaleConfigExportQueryPageListReq req) {

    DynamicsPage<ApsSaleConfig> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsSaleConfig> q = getWrapper(req.getData());
    List<ApsSaleConfigExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsSaleConfig> list = this.page(page, q);
      IPage<ApsSaleConfigExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsSaleConfigExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsSaleConfigExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<ApsSaleConfigExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsSaleConfigExportQueryPageListInfoRes.class);
    ((ApsSaleConfigService) AopContext.currentProxy()).setName(listInfoRes);
    return DynamicsPage.init(page, listInfoRes);
  }

  @SetUserName
  public @Override void setName(List<? extends ApsSaleConfigDto> apsSaleConfigDtoList) {
    if (CollUtil.isEmpty(apsSaleConfigDtoList)) {
      return;
    }
    apsSaleConfigDtoList.sort(Comparator.comparing(ApsSaleConfigDto::getSaleCode));
    List<? extends ApsSaleConfigDto> parentList = apsSaleConfigDtoList.stream().filter(t -> Objects.equals(t.getParentId(), 0L)).toList();
    parentList.forEach(p -> p.setChildren(apsSaleConfigDtoList.stream().filter(t -> Objects.equals(t.getParentId(), p.getId())).toList()));
    apsSaleConfigDtoList.removeIf(t -> !Objects.equals(t.getParentId(), 0L));
  }

  // 以下为私有对象封装


  private MPJLambdaWrapper<ApsSaleConfig> getWrapper(ApsSaleConfigDto obj) {
    MPJLambdaWrapper<ApsSaleConfig> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q.eq(StringUtils.isNoneBlank(obj.getSaleCode()), ApsSaleConfig::getSaleCode, obj.getSaleCode())
          .eq(StringUtils.isNoneBlank(obj.getSaleName()), ApsSaleConfig::getSaleName, obj.getSaleName())
          .eq(Objects.nonNull(obj.getSupplierStatus()), ApsSaleConfig::getSupplierStatus, obj.getSupplierStatus())
          .eq(Objects.nonNull(obj.getIsValue()), ApsSaleConfig::getIsValue, obj.getIsValue())

      ;
    }
    q.orderByDesc(ApsSaleConfig::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsSaleConfig> page) {

    ServiceComment.header(page, "ApsSaleConfigService#queryPageList");

  }


}

