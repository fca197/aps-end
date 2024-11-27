package com.olivia.peanut.portal.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.portal.api.entity.property.*;
import com.olivia.peanut.portal.mapper.PropertyMapper;
import com.olivia.peanut.portal.model.Factory;
import com.olivia.peanut.portal.model.Property;
import com.olivia.peanut.portal.model.Room;
import com.olivia.peanut.portal.model.Storey;
import com.olivia.peanut.portal.service.FactoryService;
import com.olivia.peanut.portal.service.PropertyService;
import com.olivia.peanut.portal.service.RoomService;
import com.olivia.peanut.portal.service.StoreyService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.RunUtils;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 资产信息(Property)表服务实现类
 *
 * @author makejava
 * @since 2024-03-11 17:20:53
 */
@Service("propertyService")
@Transactional
public class PropertyServiceImpl extends MPJBaseServiceImpl<PropertyMapper, Property> implements PropertyService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();
  @Resource
  FactoryService factoryService;
  @Resource
  StoreyService storeyService;
  @Resource
  RoomService roomService;

  public @Override PropertyQueryListRes queryList(PropertyQueryListReq req) {

    MPJLambdaWrapper<Property> q = getWrapper(req.getData());
    List<Property> list = this.list(q);

    List<PropertyDto> dataList = list.stream().map(t -> $.copy(t, PropertyDto.class)).toList();
    setAllName(dataList);
    return new PropertyQueryListRes().setDataList(dataList);
  }

  private void setAllName(List<? extends PropertyDto> list) {
    if (CollUtil.isNotEmpty(list)) {

      List<Runnable> runnableList = new ArrayList<>();
      runnableList.add(() -> {
        Set<Long> idSet = list.stream().map(PropertyDto::getFactoryId).collect(Collectors.toSet());
        Map<Long, String> objMap = this.factoryService.listByIds(idSet).stream().collect(Collectors.toMap(Factory::getId, Factory::getFactoryName));
        list.forEach(t -> t.setFactoryName(objMap.get(t.getFactoryId())));

      });
      runnableList.add(() -> {
        Set<Long> idSet = list.stream().map(PropertyDto::getStoreyId).collect(Collectors.toSet());
        Map<Long, String> objMap = (this.storeyService.listByIds(idSet).stream().collect(Collectors.toMap(Storey::getId, Storey::getStoreyName)));
        list.forEach(t -> t.setStoreyName(objMap.get(t.getStoreyId())));
      });

      runnableList.add(() -> {
        Set<Long> idSet = list.stream().map(PropertyDto::getRoomId).collect(Collectors.toSet());
        Map<Long, String> objMap = (this.roomService.listByIds(idSet).stream().collect(Collectors.toMap(Room::getId, Room::getRoomName)));
        list.forEach(t -> t.setRoomName(objMap.get(t.getRoomId())));
      });
      RunUtils.run("setAllName", runnableList);
    }
  }

  public @Override DynamicsPage<PropertyExportQueryPageListInfoRes> queryPageList(PropertyExportQueryPageListReq req) {

    DynamicsPage<Property> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<Property> q = getWrapper(req.getData());
    List<PropertyExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<Property> list = this.page(page, q);
      IPage<PropertyExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, PropertyExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), PropertyExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作
    List<PropertyExportQueryPageListInfoRes> listInfoRes = $.copyList(records, PropertyExportQueryPageListInfoRes.class);
    setAllName(listInfoRes);
    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装


  private MPJLambdaWrapper<Property> getWrapper(PropertyDto obj) {
    MPJLambdaWrapper<Property> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q.eq(Objects.nonNull(obj.getId()), Property::getId, obj.getId()).eq(Objects.nonNull(obj.getTenantId()), Property::getTenantId, obj.getTenantId())
          .eq(Objects.nonNull(obj.getFactoryId()), Property::getFactoryId, obj.getFactoryId()).eq(Objects.nonNull(obj.getStoreyId()), Property::getStoreyId, obj.getStoreyId())
          .eq(Objects.nonNull(obj.getRoomId()), Property::getRoomId, obj.getRoomId())
          .eq(StringUtils.isNoneBlank(obj.getPropertyCode()), Property::getPropertyCode, obj.getPropertyCode())
          .likeRight(StringUtils.isNoneBlank(obj.getPropertyName()), Property::getPropertyName, obj.getPropertyName())
          .eq(Objects.nonNull(obj.getInUse()), Property::getInUse, obj.getInUse())
          .eq(Objects.nonNull(obj.getCreateTime()), Property::getCreateTime, obj.getCreateTime()).eq(Objects.nonNull(obj.getCreateBy()), Property::getCreateBy, obj.getCreateBy())
          .eq(Objects.nonNull(obj.getUpdateTime()), Property::getUpdateTime, obj.getUpdateTime()).eq(Objects.nonNull(obj.getUpdateBy()), Property::getUpdateBy, obj.getUpdateBy())
          .eq(Objects.nonNull(obj.getVersionNum()), Property::getVersionNum, obj.getVersionNum())
      ;
    }
    q.orderByAsc(Property::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<Property> page) {
    page.addHeader("id", "序号").addHeader("factoryName", "工厂").addHeader("storeyName", "楼层").addHeader("roomName", "房间").addHeader("propertyCode", "资产编号")
        .addHeader("propertyName", "资产")
//        .addHeader("inUseStr", "是否在用")
//        .addHeader("createTime", "创建时间")
//        .addHeader("updateTime", "更新时间")
    ;
  }


}

