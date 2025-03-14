package com.olivia.peanut.aps.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.api.entity.apsProduceProcess.*;
import com.olivia.peanut.aps.api.entity.apsProduceProcessItem.ApsProduceProcessItemDto;
import com.olivia.peanut.aps.mapper.ApsProduceProcessMapper;
import com.olivia.peanut.aps.model.ApsProduceProcess;
import com.olivia.peanut.aps.model.ApsProduceProcessItem;
import com.olivia.peanut.aps.service.ApsProduceProcessItemService;
import com.olivia.peanut.aps.service.ApsProduceProcessService;
import com.olivia.peanut.base.service.BaseTableHeaderService;
import com.olivia.peanut.portal.api.entity.BaseEntityDto;
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
 * aps 生产路径(ApsProduceProcess)表服务实现类
 *
 * @author makejava
 * @since 2024-10-24 17:00:20
 */
@Service("apsProduceProcessService")
@Transactional
public class ApsProduceProcessServiceImpl extends MPJBaseServiceImpl<ApsProduceProcessMapper, ApsProduceProcess> implements ApsProduceProcessService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  @Resource
  BaseTableHeaderService tableHeaderService;
  @Resource
  SetNameService setNameService;
  @Resource
  ApsProduceProcessItemService apsProduceProcessItemService;

  @Override
  @Transactional
  public void save(ApsProduceProcessInsertReq req) {
    ApsProduceProcess produceProcess = $.copy(req, ApsProduceProcess.class);
    produceProcess.setId(IdWorker.getId());
    List<ApsProduceProcessItem> processItemList = req.getProduceProcessItemDtoList().stream().map(t -> $.copy(t, ApsProduceProcessItem.class)).toList();
    processItemList.forEach(t -> t.setProduceProcessId(produceProcess.getId()));
    this.apsProduceProcessItemService.saveBatch(processItemList);
    this.save(produceProcess);
  }

  @Override
  @Transactional
  public void updateById(ApsProduceProcessUpdateByIdReq req) {
    ApsProduceProcess produceProcess = $.copy(req, ApsProduceProcess.class);
    this.apsProduceProcessItemService.remove(new LambdaQueryWrapper<ApsProduceProcessItem>().eq(ApsProduceProcessItem::getProduceProcessId, req.getId()));
    List<ApsProduceProcessItem> processItemList = req.getProduceProcessItemDtoList().stream().map(t -> $.copy(t, ApsProduceProcessItem.class)).toList();
    processItemList.forEach(t -> t.setProduceProcessId(produceProcess.getId()).setId(IdWorker.getId()));
    this.apsProduceProcessItemService.saveBatch(processItemList);
    this.updateById(produceProcess);
  }

  public @Override ApsProduceProcessQueryListRes queryList(ApsProduceProcessQueryListReq req) {

    MPJLambdaWrapper<ApsProduceProcess> q = getWrapper(req.getData());
    List<ApsProduceProcess> list = this.list(q);

    List<ApsProduceProcessDto> dataList = list.stream().map(t -> $.copy(t, ApsProduceProcessDto.class)).collect(Collectors.toList());
    ((ApsProduceProcessService) AopContext.currentProxy()).setName(dataList);
    return new ApsProduceProcessQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<ApsProduceProcessExportQueryPageListInfoRes> queryPageList(ApsProduceProcessExportQueryPageListReq req) {

    DynamicsPage<ApsProduceProcess> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsProduceProcess> q = getWrapper(req.getData());
    List<ApsProduceProcessExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsProduceProcess> list = this.page(page, q);
      IPage<ApsProduceProcessExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsProduceProcessExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsProduceProcessExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作 

    List<ApsProduceProcessExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsProduceProcessExportQueryPageListInfoRes.class);
    ((ApsProduceProcessService) AopContext.currentProxy()).setName(listInfoRes);

    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装

  public @Override void setName(List<? extends ApsProduceProcessDto> list) {

    setNameService.setName(list, SetNamePojoUtils.FACTORY);
    if (CollUtil.isEmpty(list)) {
      return;
    }
    Map<Long, List<ApsProduceProcessItem>> listMap = this.apsProduceProcessItemService.list(new LambdaQueryWrapper<ApsProduceProcessItem>().in(ApsProduceProcessItem::getProduceProcessId, list.stream().map(BaseEntityDto::getId).collect(Collectors.toSet()))).stream().collect(Collectors.groupingBy(ApsProduceProcessItem::getProduceProcessId));
    list.forEach(t -> t.setProduceProcessItemDtoList($.copyList(listMap.get(t.getId()), ApsProduceProcessItemDto.class)));
  }


  private MPJLambdaWrapper<ApsProduceProcess> getWrapper(ApsProduceProcessDto obj) {
    MPJLambdaWrapper<ApsProduceProcess> q = new MPJLambdaWrapper<>();


    if (Objects.nonNull(obj)) {
      q.eq(Objects.nonNull(obj.getFactoryId()), ApsProduceProcess::getFactoryId, obj.getFactoryId());
      q.eq(StringUtils.isNoneBlank(obj.getProduceProcessNo()), ApsProduceProcess::getProduceProcessNo, obj.getProduceProcessNo()).eq(StringUtils.isNoneBlank(obj.getProduceProcessName()), ApsProduceProcess::getProduceProcessName, obj.getProduceProcessName())

      ;
    }
    q.orderByDesc(ApsProduceProcess::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsProduceProcess> page) {

    tableHeaderService.listByBizKey(page, "ApsProduceProcessService#queryPageList");

  }


}

