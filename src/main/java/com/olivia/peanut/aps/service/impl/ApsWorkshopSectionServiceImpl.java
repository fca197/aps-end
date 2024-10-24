package com.olivia.peanut.aps.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.api.entity.workshopSection.*;
import com.olivia.peanut.aps.model.ApsWorkshopSection;
import com.olivia.peanut.aps.service.ApsWorkshopSectionService;
import com.olivia.peanut.portal.mapper.WorkshopSectionMapper;
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
 * 工段信息(WorkshopSection)表服务实现类
 *
 * @author makejava
 * @since 2024-03-11 10:55:23
 */
@Service("workshopSectionService")
@Transactional
public class ApsWorkshopSectionServiceImpl extends MPJBaseServiceImpl<WorkshopSectionMapper, ApsWorkshopSection> implements ApsWorkshopSectionService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  public @Override WorkshopSectionQueryListRes queryList(WorkshopSectionQueryListReq req) {

    MPJLambdaWrapper<ApsWorkshopSection> q = getWrapper(req.getData());
    List<ApsWorkshopSection> list = this.list(q);

    List<WorkshopSectionQueryListRes.Info> dataList = list.stream().map(t -> $.copy(t, WorkshopSectionQueryListRes.Info.class)).collect(Collectors.toList());

    return new WorkshopSectionQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<WorkshopSectionExportQueryPageListInfoRes> queryPageList(WorkshopSectionExportQueryPageListReq req) {

    DynamicsPage<ApsWorkshopSection> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsWorkshopSection> q = getWrapper(req.getData());
    List<WorkshopSectionExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsWorkshopSection> list = this.page(page, q);
      IPage<WorkshopSectionExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, WorkshopSectionExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), WorkshopSectionExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作
    List<WorkshopSectionExportQueryPageListInfoRes> listInfoRes = $.copyList(records, WorkshopSectionExportQueryPageListInfoRes.class);

    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装


  private MPJLambdaWrapper<ApsWorkshopSection> getWrapper(WorkshopSectionDto obj) {
    MPJLambdaWrapper<ApsWorkshopSection> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(Objects.nonNull(obj.getId()), ApsWorkshopSection::getId, obj.getId())
          .eq(Objects.nonNull(obj.getTenantId()), ApsWorkshopSection::getTenantId, obj.getTenantId())
          .eq(Objects.nonNull(obj.getFactoryId()), ApsWorkshopSection::getFactoryId, obj.getFactoryId())
          .eq(StringUtils.isNoneBlank(obj.getSectionName()), ApsWorkshopSection::getSectionName, obj.getSectionName())
          .eq(StringUtils.isNoneBlank(obj.getSectionCode()), ApsWorkshopSection::getSectionCode, obj.getSectionCode())
          .eq(StringUtils.isNoneBlank(obj.getSectionType()), ApsWorkshopSection::getSectionType, obj.getSectionType())
          .eq(StringUtils.isNoneBlank(obj.getSectionStatus()), ApsWorkshopSection::getSectionStatus, obj.getSectionStatus())
          .eq(Objects.nonNull(obj.getCreateTime()), ApsWorkshopSection::getCreateTime, obj.getCreateTime())
          .eq(Objects.nonNull(obj.getUpdateTime()), ApsWorkshopSection::getUpdateTime, obj.getUpdateTime())
          .orderByDesc(ApsWorkshopSection::getId)
      ;
    }

    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsWorkshopSection> page) {
    page
        .addHeader("id", "序号")
        .addHeader("sectionCode", "工段编码")
        .addHeader("sectionName", "工段名称")
    ;
  }


}

