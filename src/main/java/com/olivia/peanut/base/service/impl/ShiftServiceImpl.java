package com.olivia.peanut.base.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.base.api.entity.shift.*;
import com.olivia.peanut.base.api.entity.shiftItem.ShiftItemDto;
import com.olivia.peanut.base.mapper.ShiftMapper;
import com.olivia.peanut.base.model.Factory;
import com.olivia.peanut.base.model.Shift;
import com.olivia.peanut.base.model.ShiftItem;
import com.olivia.peanut.base.service.FactoryService;
import com.olivia.peanut.base.service.ShiftItemService;
import com.olivia.peanut.base.service.ShiftService;
import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.SetUserName;
import com.olivia.sdk.comment.ServiceComment;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * (Shift)表服务实现类
 *
 * @author peanut
 * @since 2024-04-04 12:10:15
 */
@Service("shiftService")
@Transactional
public class ShiftServiceImpl extends MPJBaseServiceImpl<ShiftMapper, Shift> implements ShiftService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  @Resource
  ShiftItemService shiftItemService;
  @Resource
  FactoryService factoryService;

  public @Override ShiftQueryListRes queryList(ShiftQueryListReq req) {

    MPJLambdaWrapper<Shift> q = getWrapper(req.getData());
    List<Shift> list = this.list(q);

    List<ShiftDto> dataList = list.stream().map(t -> $.copy(t, ShiftDto.class)).collect(Collectors.toList());
    setName(dataList);
    return new ShiftQueryListRes().setDataList(dataList);
  }

  public @Override DynamicsPage<ShiftExportQueryPageListInfoRes> queryPageList(ShiftExportQueryPageListReq req) {

    DynamicsPage<Shift> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<Shift> q = getWrapper(req.getData());
    List<ShiftExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<Shift> list = this.page(page, q);
      IPage<ShiftExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ShiftExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ShiftExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<ShiftExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ShiftExportQueryPageListInfoRes.class);
    setName(listInfoRes);
    return DynamicsPage.init(page, listInfoRes);
  }

  @Override
  @Transactional
  public ShiftInsertRes save(ShiftInsertReq req) {
    long size = this.list(new LambdaQueryWrapper<Shift>().eq(Shift::getFactoryId, req.getFactoryId())).size();
    $.assertTrueCanIgnoreException(size == 0, "该工厂已存在一个班次，请删除后重试");
    Shift shift = $.copy(req, Shift.class);
    shift.setId(IdWorker.getId());
    List<ShiftItemDto> shiftItemDtoList = req.getShiftItemDtoList();
    shiftItemDtoList.forEach(t -> t.setShiftId(shift.getId()));
    this.shiftItemService.saveBatch($.copyList(shiftItemDtoList, ShiftItem.class));
    this.save(shift);
    return new ShiftInsertRes().setCount(1).setId(shift.getId());
  }

  @Override
  @Transactional
  public ShiftUpdateByIdRes updateById(ShiftUpdateByIdReq req) {
    Shift shift = $.copy(req, Shift.class);
    this.shiftItemService.remove(new LambdaQueryWrapper<ShiftItem>().eq(ShiftItem::getShiftId, req.getId()));
    List<ShiftItem> entityList = $.copyList(req.getShiftItemDtoList(), ShiftItem.class);
    entityList.forEach(t -> t.setShiftId(req.getId()).setId(IdWorker.getId()));
    this.shiftItemService.saveBatch(entityList);
    this.updateById(shift);
    return new ShiftUpdateByIdRes();
  }

  @SetUserName
  public @Override void setName(List<? extends ShiftDto> shiftDtoList) {
    if (CollUtil.isEmpty(shiftDtoList)) {
      return;
    }
    Set<Long> sIdSet = shiftDtoList.stream().map(BaseEntityDto::getId).collect(Collectors.toSet());
    Map<Long, List<ShiftItem>> itemList = this.shiftItemService.list(new MPJLambdaWrapper<ShiftItem>().in(ShiftItem::getShiftId, sIdSet))
        .stream().collect(Collectors.groupingBy(ShiftItem::getShiftId));
    Set<Long> fidSet = shiftDtoList.stream().map(ShiftDto::getFactoryId).collect(Collectors.toSet());
    Map<Long, String> fnMap = this.factoryService.listByIds(fidSet).stream().collect(Collectors.toMap(Factory::getId, Factory::getFactoryName));
    shiftDtoList.forEach(t -> t.setFactoryName(fnMap.get(t.getFactoryId())).setShiftItemDtoList($.copyList(itemList.get(t.getId()), ShiftItemDto.class)));
  }

  // 以下为私有对象封装


  private MPJLambdaWrapper<Shift> getWrapper(ShiftDto obj) {
    MPJLambdaWrapper<Shift> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(StringUtils.isNoneBlank(obj.getShiftCode()), Shift::getShiftCode, obj.getShiftCode())
          .eq(StringUtils.isNoneBlank(obj.getShiftName()), Shift::getShiftName, obj.getShiftName())
          .eq(Objects.nonNull(obj.getFactoryId()), Shift::getFactoryId, obj.getFactoryId())

      ;
    }
    q.orderByDesc(Shift::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<Shift> page) {

    ServiceComment.header(page, "ShiftService#queryPageList");

  }


}

