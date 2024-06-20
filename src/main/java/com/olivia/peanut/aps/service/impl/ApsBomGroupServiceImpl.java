package com.olivia.peanut.aps.service.impl;

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
import com.olivia.peanut.aps.mapper.ApsBomGroupMapper;
import com.olivia.peanut.aps.model.ApsBomGroup;
import com.olivia.peanut.aps.service.ApsBomGroupService;
import cn.hutool.core.collection.CollUtil;
//import com.olivia.peanut.aps.service.BaseTableHeaderService;
import com.olivia.peanut.portal.service.BaseTableHeaderService;
import com.olivia.peanut.aps.api.entity.apsBomGroup.*;

/**
 * 零件组配置(ApsBomGroup)表服务实现类
 *
 * @author peanut
 * @since 2024-06-19 17:41:23
 */
@Service("apsBomGroupService")
@Transactional
public class ApsBomGroupServiceImpl extends MPJBaseServiceImpl<ApsBomGroupMapper, ApsBomGroup> implements ApsBomGroupService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  @Resource
  BaseTableHeaderService tableHeaderService;


  public @Override ApsBomGroupQueryListRes queryList(ApsBomGroupQueryListReq req) {

    MPJLambdaWrapper<ApsBomGroup> q = getWrapper(req.getData());
    List<ApsBomGroup> list = this.list(q);

    List<ApsBomGroupDto> dataList = list.stream().map(t -> $.copy(t, ApsBomGroupDto.class)).collect(Collectors.toList());
    this.setName(dataList);
    return new ApsBomGroupQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<ApsBomGroupExportQueryPageListInfoRes> queryPageList(ApsBomGroupExportQueryPageListReq req) {

    DynamicsPage<ApsBomGroup> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsBomGroup> q = getWrapper(req.getData());
    List<ApsBomGroupExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsBomGroup> list = this.page(page, q);
      IPage<ApsBomGroupExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsBomGroupExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsBomGroupExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<ApsBomGroupExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsBomGroupExportQueryPageListInfoRes.class);
    this.setName(listInfoRes);
    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装

  public @Override void setName(List<? extends ApsBomGroupDto> apsBomGroupDtoList) {

    if (CollUtil.isEmpty(apsBomGroupDtoList)) {
    }


  }


  private MPJLambdaWrapper<ApsBomGroup> getWrapper(ApsBomGroupDto obj) {
    MPJLambdaWrapper<ApsBomGroup> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(StringUtils.isNoneBlank(obj.getGroupCode()), ApsBomGroup::getGroupCode, obj.getGroupCode())
          .eq(StringUtils.isNoneBlank(obj.getGroupName()), ApsBomGroup::getGroupName, obj.getGroupName())
          .eq(Objects.nonNull(obj.getParentId()), ApsBomGroup::getParentId, obj.getParentId())
          .eq(StringUtils.isNoneBlank(obj.getPathId()), ApsBomGroup::getPathId, obj.getPathId())

      ;
    }
    q.orderByDesc(ApsBomGroup::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsBomGroup> page) {

    tableHeaderService.listByBizKey(page, "ApsBomGroupService#queryPageList");

  }


}

