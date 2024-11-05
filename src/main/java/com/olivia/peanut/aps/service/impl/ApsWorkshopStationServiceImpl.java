package com.olivia.peanut.aps.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.api.entity.workshopStation.*;
import com.olivia.peanut.aps.model.ApsWorkshopStation;
import com.olivia.peanut.aps.service.ApsWorkshopStationService;
import com.olivia.peanut.portal.mapper.WorkshopStationMapper;
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

/**
 * 工位信息(WorkshopStation)表服务实现类
 *
 * @author makejava
 * @since 2024-03-11 10:55:24
 */
@Service("workshopStationService")
@Transactional
public class ApsWorkshopStationServiceImpl extends MPJBaseServiceImpl<WorkshopStationMapper, ApsWorkshopStation> implements ApsWorkshopStationService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  public @Override WorkshopStationQueryListRes queryList(WorkshopStationQueryListReq req) {

    MPJLambdaWrapper<ApsWorkshopStation> q = getWrapper(req.getData());
    List<ApsWorkshopStation> list = this.list(q);

    List<WorkshopStationQueryListRes.Info> dataList = list.stream().map(t -> $.copy(t, WorkshopStationQueryListRes.Info.class)).collect(Collectors.toList());

    return new WorkshopStationQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<WorkshopStationExportQueryPageListInfoRes> queryPageList(WorkshopStationExportQueryPageListReq req) {

    DynamicsPage<ApsWorkshopStation> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsWorkshopStation> q = getWrapper(req.getData());
    List<WorkshopStationExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsWorkshopStation> list = this.page(page, q);
      IPage<WorkshopStationExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, WorkshopStationExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), WorkshopStationExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作
    List<WorkshopStationExportQueryPageListInfoRes> listInfoRes = $.copyList(records, WorkshopStationExportQueryPageListInfoRes.class);

    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装


  private MPJLambdaWrapper<ApsWorkshopStation> getWrapper(WorkshopStationDto obj) {
    MPJLambdaWrapper<ApsWorkshopStation> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(Objects.nonNull(obj.getId()), ApsWorkshopStation::getId, obj.getId())
          .eq(Objects.nonNull(obj.getTenantId()), ApsWorkshopStation::getTenantId, obj.getTenantId())
          .eq(StringUtils.isNoneBlank(obj.getStationName()), ApsWorkshopStation::getStationName, obj.getStationName())
          .eq(StringUtils.isNoneBlank(obj.getStationCode()), ApsWorkshopStation::getStationCode, obj.getStationCode())
          .eq(Objects.nonNull(obj.getCreateTime()), ApsWorkshopStation::getCreateTime, obj.getCreateTime())
          .eq(Objects.nonNull(obj.getUpdateTime()), ApsWorkshopStation::getUpdateTime, obj.getUpdateTime())
          .orderByDesc(ApsWorkshopStation::getId)
      ;
    }

    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsWorkshopStation> page) {
    page
        .addHeader("id", "序号")
        .addHeader("stationCode", "工位编码")
        .addHeader("stationName", "工位名称")
    ;
  }


}

