package com.olivia.peanut.aps.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.api.entity.apsProjectConfig.*;
import com.olivia.peanut.aps.mapper.ApsProjectConfigMapper;
import com.olivia.peanut.aps.model.ApsProjectConfig;
import com.olivia.peanut.aps.service.ApsProjectConfigService;
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
 * (ApsProjectConfig)表服务实现类
 *
 * @author peanut
 * @since 2024-03-30 16:21:20
 */
@Service("apsProjectConfigService")
@Transactional
public class ApsProjectConfigServiceImpl extends MPJBaseServiceImpl<ApsProjectConfigMapper, ApsProjectConfig> implements ApsProjectConfigService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();


  public @Override ApsProjectConfigQueryListRes queryList(ApsProjectConfigQueryListReq req) {

    MPJLambdaWrapper<ApsProjectConfig> q = getWrapper(req.getData());
    List<ApsProjectConfig> list = this.list(q);

    List<ApsProjectConfigDto> dataList = list.stream().map(t -> $.copy(t, ApsProjectConfigDto.class)).collect(Collectors.toList());

    return new ApsProjectConfigQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<ApsProjectConfigExportQueryPageListInfoRes> queryPageList(ApsProjectConfigExportQueryPageListReq req) {

    DynamicsPage<ApsProjectConfig> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsProjectConfig> q = getWrapper(req.getData());
    List<ApsProjectConfigExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsProjectConfig> list = this.page(page, q);
      IPage<ApsProjectConfigExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsProjectConfigExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsProjectConfigExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<ApsProjectConfigExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsProjectConfigExportQueryPageListInfoRes.class);
//    setName(listInfoRes);
    ((ApsProjectConfigService) AopContext.currentProxy()).setName(listInfoRes);

    return DynamicsPage.init(page, listInfoRes);
  }


  @SetUserName
  public @Override void setName(List<? extends ApsProjectConfigDto> apsProjectConfigDtoList) {
    if (CollUtil.isEmpty(apsProjectConfigDtoList)) {
      return;
    }
    apsProjectConfigDtoList.sort(Comparator.comparing(ApsProjectConfigDto::getSaleCode));
    List<? extends ApsProjectConfigDto> parentList = apsProjectConfigDtoList.stream().filter(t -> Objects.equals(t.getParentId(), 0L)).toList();
    parentList.forEach(p ->
        p.setChildren(apsProjectConfigDtoList.stream().filter(t -> Objects.equals(t.getParentId(), p.getId())).toList())
    );
    apsProjectConfigDtoList.removeIf(t -> !Objects.equals(t.getParentId(), 0L));

  }

  // 以下为私有对象封装


  private MPJLambdaWrapper<ApsProjectConfig> getWrapper(ApsProjectConfigDto obj) {
    MPJLambdaWrapper<ApsProjectConfig> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(StringUtils.isNoneBlank(obj.getSaleCode()), ApsProjectConfig::getSaleCode, obj.getSaleCode())
          .eq(StringUtils.isNoneBlank(obj.getSaleName()), ApsProjectConfig::getSaleName, obj.getSaleName())
          .eq(Objects.nonNull(obj.getSupplierStatus()), ApsProjectConfig::getSupplierStatus, obj.getSupplierStatus())
          .eq(Objects.nonNull(obj.getIsValue()), ApsProjectConfig::getIsValue, obj.getIsValue())
          .eq(Objects.nonNull(obj.getParentId()), ApsProjectConfig::getParentId, obj.getParentId())

      ;
    }
    q.orderByDesc(ApsProjectConfig::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsProjectConfig> page) {

    ServiceComment.header(page, "ApsProjectConfigService#queryPageList");

  }


}

