package com.olivia.peanut.base.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.base.api.entity.factory.*;
import com.olivia.peanut.base.mapper.FactoryMapper;
import com.olivia.peanut.base.model.Factory;
import com.olivia.peanut.base.service.FactoryService;
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
 * 工段信息(Factory)表服务实现类
 *
 * @author makejava
 * @since 2024-03-11 10:44:05
 */
@Service("factoryService")
@Transactional
public class FactoryServiceImpl extends MPJBaseServiceImpl<FactoryMapper, Factory> implements FactoryService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  public @Override FactoryQueryListRes queryList(FactoryQueryListReq req) {

    MPJLambdaWrapper<Factory> q = getWrapper(req.getData());
    List<Factory> list = this.list(q);

    List<FactoryQueryListRes.Info> dataList = list.stream().map(t -> $.copy(t, FactoryQueryListRes.Info.class)).collect(Collectors.toList());

    return new FactoryQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<FactoryExportQueryPageListInfoRes> queryPageList(FactoryExportQueryPageListReq req) {

    DynamicsPage<Factory> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<Factory> q = getWrapper(req.getData());
    List<FactoryExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<Factory> list = this.page(page, q);
      IPage<FactoryExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, FactoryExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), FactoryExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作
    List<FactoryExportQueryPageListInfoRes> listInfoRes = $.copyList(records, FactoryExportQueryPageListInfoRes.class);

    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装


  private MPJLambdaWrapper<Factory> getWrapper(FactoryDto obj) {
    MPJLambdaWrapper<Factory> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(Objects.nonNull(obj.getId()), Factory::getId, obj.getId())
          .eq(Objects.nonNull(obj.getTenantId()), Factory::getTenantId, obj.getTenantId())
          .eq(StringUtils.isNoneBlank(obj.getFactoryName()), Factory::getFactoryName, obj.getFactoryName())
          .eq(StringUtils.isNoneBlank(obj.getFactoryCode()), Factory::getFactoryCode, obj.getFactoryCode())
          .eq(StringUtils.isNoneBlank(obj.getFactoryStatus()), Factory::getFactoryStatus, obj.getFactoryStatus())
          .eq(Objects.nonNull(obj.getCreateTime()), Factory::getCreateTime, obj.getCreateTime())
          .eq(Objects.nonNull(obj.getCreateBy()), Factory::getCreateBy, obj.getCreateBy())
          .eq(Objects.nonNull(obj.getUpdateTime()), Factory::getUpdateTime, obj.getUpdateTime())
          .eq(Objects.nonNull(obj.getUpdateBy()), Factory::getUpdateBy, obj.getUpdateBy())
          .orderByDesc(Factory::getId)
      ;
    }

    return q;

  }

  private void setQueryListHeader(DynamicsPage<Factory> page) {
    page
        .addHeader("id", "id")
        .addHeader("tenantId", "所属租户id")
        .addHeader("factoryName", "工厂名称")
        .addHeader("factoryCode", "工厂编码")
        .addHeader("factoryStatus", "工厂状态 ")
        .addHeader("isDelete", "是否删除(0:否 1:是)")
        .addHeader("createTime", "创建时间")
        .addHeader("createBy", "创建人id")
        .addHeader("updateTime", "更新时间")
        .addHeader("updateBy", "更新人id")
        .addHeader("traceId", "链路追踪ID")
    ;
  }


}

