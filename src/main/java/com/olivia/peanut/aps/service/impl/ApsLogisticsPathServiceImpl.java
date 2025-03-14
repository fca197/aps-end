package com.olivia.peanut.aps.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.api.entity.apsLogisticsPath.*;
import com.olivia.peanut.aps.mapper.ApsLogisticsPathMapper;
import com.olivia.peanut.aps.model.ApsLogisticsPath;
import com.olivia.peanut.aps.model.ApsLogisticsPathItem;
import com.olivia.peanut.aps.service.ApsLogisticsPathItemService;
import com.olivia.peanut.aps.service.ApsLogisticsPathService;
import com.olivia.peanut.base.service.BaseTableHeaderService;
import com.olivia.peanut.util.SetNamePojoUtils;
import com.olivia.sdk.service.SetNameService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 物流路径表(ApsLogisticsPath)表服务实现类
 *
 * @author peanut
 * @since 2024-07-18 13:27:36
 */
@Service("apsLogisticsPathService")
@Transactional
public class ApsLogisticsPathServiceImpl extends MPJBaseServiceImpl<ApsLogisticsPathMapper, ApsLogisticsPath> implements ApsLogisticsPathService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  @Resource
  BaseTableHeaderService tableHeaderService;
  @Resource
  ApsLogisticsPathItemService apsLogisticsPathItemService;
  @Resource
  SetNameService setNameService;

  @Override
  @Transactional
  public ApsLogisticsPathInsertRes save(ApsLogisticsPathInsertReq req) {

    ApsLogisticsPath apsLogisticsPath = $.copy(req, ApsLogisticsPath.class);
    apsLogisticsPath.setId(IdWorker.getId());
    req.getApsLogisticsPathItemList().forEach(t -> t.setLogisticsPathId(apsLogisticsPath.getId()));

    this.save(apsLogisticsPath);
    this.apsLogisticsPathItemService.saveBatch($.copyList(req.getApsLogisticsPathItemList(), ApsLogisticsPathItem.class));
//    apsLogisticsPathItemService.remove(new LambdaQueryChainWrapper<ApsLogisticsPathItem>().eq(ApsLogisticsPathItem::getLogisticsPathId))
    return new ApsLogisticsPathInsertRes().setId(apsLogisticsPath.getId());
  }

  @Override
  @Transactional
  public ApsLogisticsPathUpdateByIdRes updateById(ApsLogisticsPathUpdateByIdReq req) {

    ApsLogisticsPath apsLogisticsPath = $.copy(req, ApsLogisticsPath.class);
    req.getApsLogisticsPathItemList().forEach(t -> t.setLogisticsPathId(apsLogisticsPath.getId()));
    this.updateById(apsLogisticsPath);
    this.apsLogisticsPathItemService.remove(new LambdaQueryWrapper<ApsLogisticsPathItem>().eq(ApsLogisticsPathItem::getLogisticsPathId, req.getId()));
    this.apsLogisticsPathItemService.saveBatch($.copyList(req.getApsLogisticsPathItemList(), ApsLogisticsPathItem.class));
    return new ApsLogisticsPathUpdateByIdRes();
  }

  public @Override ApsLogisticsPathQueryListRes queryList(ApsLogisticsPathQueryListReq req) {

    MPJLambdaWrapper<ApsLogisticsPath> q = getWrapper(req.getData());
    List<ApsLogisticsPath> list = this.list(q);

    List<ApsLogisticsPathDto> dataList = list.stream().map(t -> $.copy(t, ApsLogisticsPathDto.class)).collect(Collectors.toList());
    ((ApsLogisticsPathService) AopContext.currentProxy()).setName(dataList);
    return new ApsLogisticsPathQueryListRes().setDataList(dataList);
  }

  // 以下为私有对象封装

  public @Override DynamicsPage<ApsLogisticsPathExportQueryPageListInfoRes> queryPageList(ApsLogisticsPathExportQueryPageListReq req) {

    DynamicsPage<ApsLogisticsPath> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsLogisticsPath> q = getWrapper(req.getData());
    List<ApsLogisticsPathExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsLogisticsPath> list = this.page(page, q);
      IPage<ApsLogisticsPathExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsLogisticsPathExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsLogisticsPathExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<ApsLogisticsPathExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsLogisticsPathExportQueryPageListInfoRes.class);
    ((ApsLogisticsPathService) AopContext.currentProxy()).setName(listInfoRes);

    return DynamicsPage.init(page, listInfoRes);
  }

  public @Override void setName(List<? extends ApsLogisticsPathDto> apsLogisticsPathDtoList) {
    setNameService.setName(apsLogisticsPathDtoList, SetNamePojoUtils.FACTORY);
  }


  private MPJLambdaWrapper<ApsLogisticsPath> getWrapper(ApsLogisticsPathDto obj) {
    MPJLambdaWrapper<ApsLogisticsPath> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q.eq(StringUtils.isNoneBlank(obj.getLogisticsPathCode()), ApsLogisticsPath::getLogisticsPathCode, obj.getLogisticsPathCode())
          .eq(StringUtils.isNoneBlank(obj.getLogisticsPathName()), ApsLogisticsPath::getLogisticsPathName, obj.getLogisticsPathName())
          .eq(StringUtils.isNoneBlank(obj.getLogisticsPathRemark()), ApsLogisticsPath::getLogisticsPathRemark, obj.getLogisticsPathRemark())
          .eq(Objects.nonNull(obj.getIsDefault()), ApsLogisticsPath::getIsDefault, obj.getIsDefault())
          .eq(Objects.nonNull(obj.getFactoryId()), ApsLogisticsPath::getFactoryId, obj.getFactoryId())

      ;
    }
    q.orderByDesc(ApsLogisticsPath::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsLogisticsPath> page) {

    tableHeaderService.listByBizKey(page, "ApsLogisticsPathService#queryPageList");

  }


}

