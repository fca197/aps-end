package com.olivia.peanut.aps.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.api.entity.apsSchedulingDayConfig.*;
import com.olivia.peanut.aps.mapper.ApsSchedulingDayConfigMapper;
import com.olivia.peanut.aps.model.ApsSchedulingDayConfig;
import com.olivia.peanut.aps.model.ApsSchedulingDayConfigItem;
import com.olivia.peanut.aps.service.ApsSchedulingDayConfigItemService;
import com.olivia.peanut.aps.service.ApsSchedulingDayConfigService;
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
 * 排程版本表(ApsSchedulingDayConfig)表服务实现类
 *
 * @author peanut
 * @since 2024-07-19 15:05:00
 */
@Service("apsSchedulingDayConfigService")
@Transactional
public class ApsSchedulingDayConfigServiceImpl extends MPJBaseServiceImpl<ApsSchedulingDayConfigMapper, ApsSchedulingDayConfig> implements ApsSchedulingDayConfigService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  @Resource
  BaseTableHeaderService tableHeaderService;
  @Resource
  SetNameService setNameService;

  @Resource
  ApsSchedulingDayConfigItemService apsSchedulingDayConfigItemService;

  @Override
  @Transactional
  public ApsSchedulingDayConfigInsertRes save(ApsSchedulingDayConfigInsertReq req) {
    ApsSchedulingDayConfig config = $.copy(req, ApsSchedulingDayConfig.class);
    config.setId(IdWorker.getId());
    req.getSchedulingDayConfigItemDtoList().forEach(t -> t.setSchedulingDayId(config.getId()));
    apsSchedulingDayConfigItemService.saveBatch($.copyList(req.getSchedulingDayConfigItemDtoList(), ApsSchedulingDayConfigItem.class));
    this.save(config);
    return new ApsSchedulingDayConfigInsertRes().setId(config.getId());
  }

  @Override
  public ApsSchedulingDayConfigUpdateByIdRes updateById(ApsSchedulingDayConfigUpdateByIdReq req) {
    ApsSchedulingDayConfig config = $.copy(req, ApsSchedulingDayConfig.class);
    config.setId(IdWorker.getId());
    req.getSchedulingDayConfigItemDtoList().forEach(t -> t.setSchedulingDayId(config.getId()));
    this.apsSchedulingDayConfigItemService.remove(new LambdaQueryWrapper<ApsSchedulingDayConfigItem>().eq(ApsSchedulingDayConfigItem::getSchedulingDayId, req.getId()));
    apsSchedulingDayConfigItemService.saveBatch($.copyList(req.getSchedulingDayConfigItemDtoList(), ApsSchedulingDayConfigItem.class));
    this.updateById(config);
    return new ApsSchedulingDayConfigUpdateByIdRes();
  }

  public @Override ApsSchedulingDayConfigQueryListRes queryList(ApsSchedulingDayConfigQueryListReq req) {

    MPJLambdaWrapper<ApsSchedulingDayConfig> q = getWrapper(req.getData());
    List<ApsSchedulingDayConfig> list = this.list(q);

    List<ApsSchedulingDayConfigDto> dataList = list.stream().map(t -> $.copy(t, ApsSchedulingDayConfigDto.class)).collect(Collectors.toList());
    ((ApsSchedulingDayConfigService) AopContext.currentProxy()).setName(dataList);
    return new ApsSchedulingDayConfigQueryListRes().setDataList(dataList);
  }

  // 以下为私有对象封装

  public @Override DynamicsPage<ApsSchedulingDayConfigExportQueryPageListInfoRes> queryPageList(ApsSchedulingDayConfigExportQueryPageListReq req) {

    DynamicsPage<ApsSchedulingDayConfig> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsSchedulingDayConfig> q = getWrapper(req.getData());
    List<ApsSchedulingDayConfigExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsSchedulingDayConfig> list = this.page(page, q);
      IPage<ApsSchedulingDayConfigExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsSchedulingDayConfigExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsSchedulingDayConfigExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<ApsSchedulingDayConfigExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsSchedulingDayConfigExportQueryPageListInfoRes.class);
    ((ApsSchedulingDayConfigService) AopContext.currentProxy()).setName(listInfoRes);

    return DynamicsPage.init(page, listInfoRes);
  }

  public @Override void setName(List<? extends ApsSchedulingDayConfigDto> apsSchedulingDayConfigDtoList) {

    setNameService.setName(apsSchedulingDayConfigDtoList, SetNamePojoUtils.FACTORY);


  }


  private MPJLambdaWrapper<ApsSchedulingDayConfig> getWrapper(ApsSchedulingDayConfigDto obj) {
    MPJLambdaWrapper<ApsSchedulingDayConfig> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(Objects.nonNull(obj.getFactoryId()), ApsSchedulingDayConfig::getFactoryId, obj.getFactoryId())
          .eq(StringUtils.isNoneBlank(obj.getSchedulingDayNo()), ApsSchedulingDayConfig::getSchedulingDayNo, obj.getSchedulingDayNo())
          .eq(StringUtils.isNoneBlank(obj.getSchedulingDayName()), ApsSchedulingDayConfig::getSchedulingDayName, obj.getSchedulingDayName())
          .eq(Objects.nonNull(obj.getIsDefault()), ApsSchedulingDayConfig::getIsDefault, obj.getIsDefault())

      ;
    }
    q.orderByDesc(ApsSchedulingDayConfig::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsSchedulingDayConfig> page) {

    tableHeaderService.listByBizKey(page, "ApsSchedulingDayConfigService#queryPageList");

  }


}

