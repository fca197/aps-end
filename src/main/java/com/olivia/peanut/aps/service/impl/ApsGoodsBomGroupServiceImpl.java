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
import com.olivia.peanut.aps.mapper.ApsGoodsBomGroupMapper;
import com.olivia.peanut.aps.model.ApsGoodsBomGroup;
import com.olivia.peanut.aps.service.ApsGoodsBomGroupService;
import cn.hutool.core.collection.CollUtil;
//import com.olivia.peanut.aps.service.BaseTableHeaderService;
import com.olivia.peanut.portal.service.BaseTableHeaderService;
import com.olivia.peanut.aps.api.entity.apsGoodsBomGroup.*;

/**
 * 零件组配置(ApsGoodsBomGroup)表服务实现类
 *
 * @author peanut
 * @since 2024-06-19 16:49:04
 */
@Service("apsGoodsBomGroupService")
@Transactional
public class ApsGoodsBomGroupServiceImpl extends MPJBaseServiceImpl<ApsGoodsBomGroupMapper, ApsGoodsBomGroup> implements ApsGoodsBomGroupService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  @Resource
  BaseTableHeaderService tableHeaderService;


  public @Override ApsGoodsBomGroupQueryListRes queryList(ApsGoodsBomGroupQueryListReq req) {

    MPJLambdaWrapper<ApsGoodsBomGroup> q = getWrapper(req.getData());
    List<ApsGoodsBomGroup> list = this.list(q);

    List<ApsGoodsBomGroupDto> dataList = list.stream().map(t -> $.copy(t, ApsGoodsBomGroupDto.class)).collect(Collectors.toList());
    this.setName(dataList);
    return new ApsGoodsBomGroupQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<ApsGoodsBomGroupExportQueryPageListInfoRes> queryPageList(ApsGoodsBomGroupExportQueryPageListReq req) {

    DynamicsPage<ApsGoodsBomGroup> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsGoodsBomGroup> q = getWrapper(req.getData());
    List<ApsGoodsBomGroupExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsGoodsBomGroup> list = this.page(page, q);
      IPage<ApsGoodsBomGroupExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsGoodsBomGroupExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsGoodsBomGroupExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<ApsGoodsBomGroupExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsGoodsBomGroupExportQueryPageListInfoRes.class);
    this.setName(listInfoRes);
    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装

  public @Override void setName(List<? extends ApsGoodsBomGroupDto> apsGoodsBomGroupDtoList) {

    if (CollUtil.isEmpty(apsGoodsBomGroupDtoList)) {
    }


  }


  private MPJLambdaWrapper<ApsGoodsBomGroup> getWrapper(ApsGoodsBomGroupDto obj) {
    MPJLambdaWrapper<ApsGoodsBomGroup> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(StringUtils.isNoneBlank(obj.getGroupCode()), ApsGoodsBomGroup::getGroupCode, obj.getGroupCode())
          .eq(StringUtils.isNoneBlank(obj.getGroupName()), ApsGoodsBomGroup::getGroupName, obj.getGroupName())
          .eq(Objects.nonNull(obj.getParentId()), ApsGoodsBomGroup::getParentId, obj.getParentId())
          .eq(StringUtils.isNoneBlank(obj.getPathId()), ApsGoodsBomGroup::getPathId, obj.getPathId())

      ;
    }
    q.orderByDesc(ApsGoodsBomGroup::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsGoodsBomGroup> page) {

    tableHeaderService.listByBizKey(page, "ApsGoodsBomGroupService#queryPageList");

  }


}

