package com.olivia.peanut.portal.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.portal.api.entity.checkReportList.*;
import com.olivia.peanut.portal.mapper.CheckReportListMapper;
import com.olivia.peanut.portal.model.CheckReportList;
import com.olivia.peanut.portal.service.CheckReportListService;
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
 * 报表检查记录信息(CheckReportList)表服务实现类
 *
 * @author makejava
 * @since 2024-03-14 13:31:37
 */
@Service("checkReportListService")
@Transactional
public class CheckReportListServiceImpl extends MPJBaseServiceImpl<CheckReportListMapper, CheckReportList> implements CheckReportListService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  public @Override CheckReportListQueryListRes queryList(CheckReportListQueryListReq req) {

    MPJLambdaWrapper<CheckReportList> q = getWrapper(req.getData());
    List<CheckReportList> list = this.list(q);

    List<CheckReportListDto> dataList = list.stream().map(t -> $.copy(t, CheckReportListDto.class)).collect(Collectors.toList());

    return new CheckReportListQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<CheckReportListExportQueryPageListInfoRes> queryPageList(CheckReportListExportQueryPageListReq req) {

    DynamicsPage<CheckReportList> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<CheckReportList> q = getWrapper(req.getData());
    List<CheckReportListExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<CheckReportList> list = this.page(page, q);
      IPage<CheckReportListExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, CheckReportListExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), CheckReportListExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作
    List<CheckReportListExportQueryPageListInfoRes> listInfoRes = $.copyList(records, CheckReportListExportQueryPageListInfoRes.class);

    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装


  private MPJLambdaWrapper<CheckReportList> getWrapper(CheckReportListDto obj) {
    MPJLambdaWrapper<CheckReportList> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q.eq(Objects.nonNull(obj.getId()), CheckReportList::getId, obj.getId()).eq(Objects.nonNull(obj.getTenantId()), CheckReportList::getTenantId, obj.getTenantId())
          .eq(Objects.nonNull(obj.getFactoryId()), CheckReportList::getFactoryId, obj.getFactoryId())
          .eq(Objects.nonNull(obj.getReportId()), CheckReportList::getReportId, obj.getReportId())
          .eq(Objects.nonNull(obj.getPropertyId()), CheckReportList::getPropertyId, obj.getPropertyId())
          .eq(StringUtils.isNoneBlank(obj.getRemark()), CheckReportList::getRemark, obj.getRemark())
          .eq(Objects.nonNull(obj.getCreateTime()), CheckReportList::getCreateTime, obj.getCreateTime())
          .eq(Objects.nonNull(obj.getCreateBy()), CheckReportList::getCreateBy, obj.getCreateBy())
          .eq(Objects.nonNull(obj.getUpdateTime()), CheckReportList::getUpdateTime, obj.getUpdateTime())
          .eq(Objects.nonNull(obj.getUpdateBy()), CheckReportList::getUpdateBy, obj.getUpdateBy())
          .eq(Objects.nonNull(obj.getVersionNum()), CheckReportList::getVersionNum, obj.getVersionNum())

      ;
    }
    q.orderByDesc(CheckReportList::getId);

    return q;

  }

  private void setQueryListHeader(DynamicsPage<CheckReportList> page) {
    page.addHeader("id", "id").addHeader("tenantId", "所属租户id").addHeader("factoryId", "工厂ID").addHeader("reportId", "报表编码").addHeader("propertyId", "资产ID")
        .addHeader("remark", "备注").addHeader("isDelete", "是否删除(0:否 1:是)").addHeader("createTime", "创建时间").addHeader("createBy", "创建人id")
        .addHeader("updateTime", "更新时间").addHeader("updateBy", "更新人id").addHeader("traceId", "链路追踪ID").addHeader("version", "版本号");
  }


}

