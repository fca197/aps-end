package com.olivia.peanut.aps.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.api.entity.apsSchedulingDayConfig.ApsSchedulingDayConfigDto;
import com.olivia.peanut.aps.api.entity.apsSchedulingDayConfig.ApsSchedulingDayConfigExportQueryPageListInfoRes;
import com.olivia.peanut.aps.api.entity.apsSchedulingDayConfig.ApsSchedulingDayConfigExportQueryPageListReq;
import com.olivia.peanut.aps.api.entity.apsSchedulingDayConfigVersion.*;
import com.olivia.peanut.aps.mapper.ApsSchedulingDayConfigVersionMapper;
import com.olivia.peanut.aps.model.ApsSchedulingDayConfigVersion;
import com.olivia.peanut.aps.model.ApsSchedulingIssueItem;
import com.olivia.peanut.aps.service.ApsProcessPathService;
import com.olivia.peanut.aps.service.ApsSchedulingDayConfigService;
import com.olivia.peanut.aps.service.ApsSchedulingDayConfigVersionDetailService;
import com.olivia.peanut.aps.service.ApsSchedulingDayConfigVersionService;
import com.olivia.peanut.aps.service.impl.po.ApsSchedulingDayOrderRoomReq;
import com.olivia.peanut.aps.service.impl.po.ApsSchedulingDayOrderRoomRes;
import com.olivia.peanut.aps.service.impl.utils.ApsSchedulingDayUtils;
import com.olivia.peanut.portal.service.BaseTableHeaderService;
import com.olivia.peanut.util.SetNamePojoUtils;
import com.olivia.sdk.service.SetNameService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import jakarta.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 排程版本(ApsSchedulingDayConfigVersion)表服务实现类
 *
 * @author peanut
 * @since 2024-07-19 19:19:55
 */
@Service("apsSchedulingDayConfigVersionService")
@Transactional
public class ApsSchedulingDayConfigVersionServiceImpl extends MPJBaseServiceImpl<ApsSchedulingDayConfigVersionMapper, ApsSchedulingDayConfigVersion> implements
    ApsSchedulingDayConfigVersionService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  @Resource
  ApsSchedulingIssueItemServiceImpl apsSchedulingIssueItemService;
  @Resource
  BaseTableHeaderService tableHeaderService;
  @Resource
  SetNameService setNameService;

  @Resource
  ApsSchedulingDayConfigService apsSchedulingDayConfigService;

  @Resource
  ApsSchedulingDayConfigVersionDetailService apsSchedulingDayConfigVersionDetailService;
  @Override
  public ApsSchedulingDayConfigVersionInsertRes save(ApsSchedulingDayConfigVersionInsertReq req) {

    ApsSchedulingDayConfigDto dayConfigDto = new ApsSchedulingDayConfigDto();
    dayConfigDto.setId(req.getSchedulingDayConfigId());
    DynamicsPage<ApsSchedulingDayConfigExportQueryPageListInfoRes> apsSchedulingDayConfigExportQueryPageListInfoResDynamicsPage = apsSchedulingDayConfigService.queryPageList(
        new ApsSchedulingDayConfigExportQueryPageListReq().setQueryPage(false).setData(dayConfigDto));
    $.requireNonNullCanIgnoreException(apsSchedulingDayConfigExportQueryPageListInfoResDynamicsPage, "排程配置不能为空");
    $.requireNonNullCanIgnoreException(apsSchedulingDayConfigExportQueryPageListInfoResDynamicsPage.getDataList(), "排程配置不能为空");
    ApsSchedulingDayConfigExportQueryPageListInfoRes apsSchedulingDayConfigDto = apsSchedulingDayConfigExportQueryPageListInfoResDynamicsPage.getDataList().get(0);
    List<ApsSchedulingIssueItem> issueItemList = apsSchedulingIssueItemService.list(
        new LambdaQueryWrapper<ApsSchedulingIssueItem>().eq(ApsSchedulingIssueItem::getCurrentDay, req.getSchedulingDay())
            .eq(ApsSchedulingIssueItem::getFactoryId, req.getFactoryId()));
    $.requireNonNullCanIgnoreException(issueItemList, "排产订单不能为空");

    ApsSchedulingDayConfigVersion dayConfigVersion = $.copy(req, ApsSchedulingDayConfigVersion.class);
    dayConfigVersion.setId(IdWorker.getId());
    ApsSchedulingDayOrderRoomRes orderRoomRes = ApsSchedulingDayUtils.orderRoomStatus(
        new ApsSchedulingDayOrderRoomReq().setOrderItemList(issueItemList).setSchedulingDayId(dayConfigVersion.getId()).setSchedulingDayConfigDto(apsSchedulingDayConfigDto));
//    apsSchedulingDayConfigDto.getSchedulingDayConfigItemDtoList()

    this.save(dayConfigVersion);
    this.apsSchedulingDayConfigVersionDetailService.saveBatch(orderRoomRes.getApsSchedulingDayConfigVersionDetailList());
    return new ApsSchedulingDayConfigVersionInsertRes().setId(dayConfigVersion.getId()).setCount(orderRoomRes.getApsSchedulingDayConfigVersionDetailList().size());
  }

  public @Override ApsSchedulingDayConfigVersionQueryListRes queryList(ApsSchedulingDayConfigVersionQueryListReq req) {

    MPJLambdaWrapper<ApsSchedulingDayConfigVersion> q = getWrapper(req.getData());
    List<ApsSchedulingDayConfigVersion> list = this.list(q);

    List<ApsSchedulingDayConfigVersionDto> dataList = list.stream().map(t -> $.copy(t, ApsSchedulingDayConfigVersionDto.class)).collect(Collectors.toList());
    ((ApsSchedulingDayConfigVersionService) AopContext.currentProxy()).setName(dataList);
    return new ApsSchedulingDayConfigVersionQueryListRes().setDataList(dataList);
  }

  public @Override DynamicsPage<ApsSchedulingDayConfigVersionExportQueryPageListInfoRes> queryPageList(ApsSchedulingDayConfigVersionExportQueryPageListReq req) {

    DynamicsPage<ApsSchedulingDayConfigVersion> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsSchedulingDayConfigVersion> q = getWrapper(req.getData());
    List<ApsSchedulingDayConfigVersionExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsSchedulingDayConfigVersion> list = this.page(page, q);
      IPage<ApsSchedulingDayConfigVersionExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsSchedulingDayConfigVersionExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsSchedulingDayConfigVersionExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<ApsSchedulingDayConfigVersionExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsSchedulingDayConfigVersionExportQueryPageListInfoRes.class);
    ((ApsSchedulingDayConfigVersionService) AopContext.currentProxy()).setName(listInfoRes);

    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装

  public @Override void setName(List<? extends ApsSchedulingDayConfigVersionDto> apsSchedulingDayConfigVersionDtoList) {

    setNameService.setName(apsSchedulingDayConfigVersionDtoList, SetNamePojoUtils.FACTORY, SetNamePojoUtils.OP_USER_NAME,
        SetNamePojoUtils.getSetNamePojo(ApsProcessPathService.class, "processPathName", "processId", "processName"));

  }


  private MPJLambdaWrapper<ApsSchedulingDayConfigVersion> getWrapper(ApsSchedulingDayConfigVersionDto obj) {
    MPJLambdaWrapper<ApsSchedulingDayConfigVersion> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q.eq(Objects.nonNull(obj.getFactoryId()), ApsSchedulingDayConfigVersion::getFactoryId, obj.getFactoryId())
          .eq(StringUtils.isNoneBlank(obj.getSchedulingDayVersionNo()), ApsSchedulingDayConfigVersion::getSchedulingDayVersionNo, obj.getSchedulingDayVersionNo())
          .eq(Objects.nonNull(obj.getSchedulingDay()), ApsSchedulingDayConfigVersion::getSchedulingDay, obj.getSchedulingDay())
          .eq(Objects.nonNull(obj.getIsIssuedThird()), ApsSchedulingDayConfigVersion::getIsIssuedThird, obj.getIsIssuedThird())

      ;
    }
    q.orderByDesc(ApsSchedulingDayConfigVersion::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsSchedulingDayConfigVersion> page) {

    tableHeaderService.listByBizKey(page, "ApsSchedulingDayConfigVersionService#queryPageList");

  }


}

