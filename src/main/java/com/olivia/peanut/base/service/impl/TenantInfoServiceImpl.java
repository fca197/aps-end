package com.olivia.peanut.base.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.base.api.entity.tenantInfo.*;
import com.olivia.peanut.base.mapper.TenantInfoMapper;
import com.olivia.peanut.base.model.TenantInfo;
import com.olivia.peanut.base.service.TenantInfoService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 租户信息(TenantInfo)表服务实现类
 *
 * @author makejava
 * @since 2024-03-11 10:55:22
 */
@Service("tenantInfoService")
@Transactional
public class TenantInfoServiceImpl extends MPJBaseServiceImpl<TenantInfoMapper, TenantInfo> implements TenantInfoService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  public @Override TenantInfoQueryListRes queryList(TenantInfoQueryListReq req) {

    MPJLambdaWrapper<TenantInfo> q = getWrapper(req.getData());
    List<TenantInfo> list = this.list(q);

    List<TenantInfoQueryListRes.Info> dataList = list.stream().map(t -> $.copy(t, TenantInfoQueryListRes.Info.class)).collect(Collectors.toList());

    return new TenantInfoQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<TenantInfoExportQueryPageListInfoRes> queryPageList(TenantInfoExportQueryPageListReq req) {

    DynamicsPage<TenantInfo> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<TenantInfo> q = getWrapper(req.getData());
    List<TenantInfoExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<TenantInfo> list = this.page(page, q);
      IPage<TenantInfoExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, TenantInfoExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), TenantInfoExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作
    List<TenantInfoExportQueryPageListInfoRes> listInfoRes = $.copyList(records, TenantInfoExportQueryPageListInfoRes.class);

    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装


  private MPJLambdaWrapper<TenantInfo> getWrapper(TenantInfoDto obj) {
    MPJLambdaWrapper<TenantInfo> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(Objects.nonNull(obj.getId()), TenantInfo::getId, obj.getId())
          .eq(StringUtils.isNoneBlank(obj.getTenantName()), TenantInfo::getTenantName, obj.getTenantName())
          .eq(StringUtils.isNoneBlank(obj.getTenantCode()), TenantInfo::getTenantCode, obj.getTenantCode())
          .eq(Objects.nonNull(obj.getCreateTime()), TenantInfo::getCreateTime, obj.getCreateTime())
          .eq(Objects.nonNull(obj.getCreateBy()), TenantInfo::getCreateBy, obj.getCreateBy())
          .eq(Objects.nonNull(obj.getUpdateTime()), TenantInfo::getUpdateTime, obj.getUpdateTime())
          .eq(Objects.nonNull(obj.getUpdateBy()), TenantInfo::getUpdateBy, obj.getUpdateBy())
          .eq(Objects.nonNull(obj.getTenantId()), TenantInfo::getTenantId, obj.getTenantId())
          .orderByDesc(TenantInfo::getId)
      ;
    }

    return q;

  }

  private void setQueryListHeader(DynamicsPage<TenantInfo> page) {
    page
        .addHeader("id", "id")
        .addHeader("tenantName", "租户名称")
        .addHeader("tenantCode", "租户编码")
        .addHeader("isDelete", "是否删除(0:否 1:是)")
        .addHeader("createTime", "创建时间")
        .addHeader("createBy", "创建人id")
        .addHeader("updateTime", "更新时间")
        .addHeader("updateBy", "更新人id")
        .addHeader("traceId", "链路追踪ID")
        .addHeader("tenantId", "$column.comment")
    ;
  }


}

