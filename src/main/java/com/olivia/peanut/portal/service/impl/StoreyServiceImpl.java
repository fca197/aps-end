package com.olivia.peanut.portal.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.con.ApsStr;
import com.olivia.peanut.portal.api.entity.storey.*;
import com.olivia.peanut.portal.mapper.StoreyMapper;
import com.olivia.peanut.portal.model.Storey;
import com.olivia.peanut.portal.service.StoreyService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 楼层信息(Storey)表服务实现类
 *
 * @author makejava
 * @since 2024-03-11 17:20:55
 */
@Service("storeyService")
@Transactional
public class StoreyServiceImpl extends MPJBaseServiceImpl<StoreyMapper, Storey> implements StoreyService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  public @Override StoreyQueryListRes queryList(StoreyQueryListReq req) {

    MPJLambdaWrapper<Storey> q = getWrapper(req.getData());
    List<Storey> list = this.list(q);

    List<StoreyDto> dataList = list.stream().map(t -> $.copy(t, StoreyDto.class)).toList();

    return new StoreyQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<StoreyExportQueryPageListInfoRes> queryPageList(StoreyExportQueryPageListReq req) {

    DynamicsPage<Storey> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<Storey> q = getWrapper(req.getData());
    List<StoreyExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<Storey> list = this.page(page, q);
      IPage<StoreyExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, StoreyExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), StoreyExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作
    List<StoreyExportQueryPageListInfoRes> listInfoRes = $.copyList(records, StoreyExportQueryPageListInfoRes.class);

    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装


  private MPJLambdaWrapper<Storey> getWrapper(StoreyDto obj) {
    MPJLambdaWrapper<Storey> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(Objects.nonNull(obj.getId()), Storey::getId, obj.getId())
          .eq(Objects.nonNull(obj.getTenantId()), Storey::getTenantId, obj.getTenantId())
          .eq(Objects.nonNull(obj.getFactoryId()), Storey::getFactoryId, obj.getFactoryId())
          .likeRight(StringUtils.isNoneBlank(obj.getStoreyName()), Storey::getStoreyName, obj.getStoreyName())
          .eq(Objects.nonNull(obj.getStoreySort()), Storey::getStoreySort, obj.getStoreySort())
          .eq(Objects.nonNull(obj.getCreateTime()), Storey::getCreateTime, obj.getCreateTime())
          .eq(Objects.nonNull(obj.getCreateBy()), Storey::getCreateBy, obj.getCreateBy())
          .eq(Objects.nonNull(obj.getUpdateTime()), Storey::getUpdateTime, obj.getUpdateTime())
          .eq(Objects.nonNull(obj.getUpdateBy()), Storey::getUpdateBy, obj.getUpdateBy())
          .eq(Objects.nonNull(obj.getVersionNum()), Storey::getVersionNum, obj.getVersionNum())
      ;
    }
    q.orderByAsc(Storey::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<Storey> page) {
    page
        .addHeader("id", "id")
        .addHeader("tenantId", "所属租户id")
        .addHeader(ApsStr.FACTORY_ID, "所属工厂id")
        .addHeader("storeyName", "楼层")
        .addHeader("storeySort", "排序")
        .addHeader("isDelete", "是否删除(0:否 1:是)")
        .addHeader("createTime", "创建时间")
        .addHeader("createBy", "创建人id")
        .addHeader("updateTime", "更新时间")
        .addHeader("updateBy", "更新人id")
        .addHeader("traceId", "链路追踪ID")
        .addHeader("version", "版本号")
    ;
  }

  @Override
  public Map<Long, Storey> listByFactoryIdList(Long factoryId) {
    return this.list(new MPJLambdaWrapper<Storey>().eq(Storey::getFactoryId, factoryId))
        .stream().collect(Collectors.toMap(Storey::getId, Function.identity()));
  }
}

