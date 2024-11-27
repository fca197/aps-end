package com.olivia.peanut.portal.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.portal.api.entity.room.*;
import com.olivia.peanut.portal.mapper.RoomMapper;
import com.olivia.peanut.portal.model.Room;
import com.olivia.peanut.portal.service.RoomService;
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
 * 房间信息(Room)表服务实现类
 *
 * @author makejava
 * @since 2024-03-11 17:20:54
 */
@Service("roomService")
@Transactional
public class RoomServiceImpl extends MPJBaseServiceImpl<RoomMapper, Room> implements RoomService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  public @Override RoomQueryListRes queryList(RoomQueryListReq req) {

    MPJLambdaWrapper<Room> q = getWrapper(req.getData());
    List<Room> list = this.list(q);

    List<RoomDto> dataList = list.stream().map(t -> $.copy(t, RoomDto.class)).collect(Collectors.toList());

    return new RoomQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<RoomExportQueryPageListInfoRes> queryPageList(RoomExportQueryPageListReq req) {

    DynamicsPage<Room> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<Room> q = getWrapper(req.getData());
    List<RoomExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<Room> list = this.page(page, q);
      IPage<RoomExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, RoomExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), RoomExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作
    List<RoomExportQueryPageListInfoRes> listInfoRes = $.copyList(records, RoomExportQueryPageListInfoRes.class);

    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装


  private MPJLambdaWrapper<Room> getWrapper(RoomDto obj) {
    MPJLambdaWrapper<Room> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(Objects.nonNull(obj.getId()), Room::getId, obj.getId())
          .eq(Objects.nonNull(obj.getTenantId()), Room::getTenantId, obj.getTenantId())
          .eq(Objects.nonNull(obj.getFactoryId()), Room::getFactoryId, obj.getFactoryId())
          .eq(Objects.nonNull(obj.getStoreyId()), Room::getStoreyId, obj.getStoreyId())
          .eq(StringUtils.isNoneBlank(obj.getRoomCode()), Room::getRoomCode, obj.getRoomCode())
          .eq(StringUtils.isNoneBlank(obj.getRoomName()), Room::getRoomName, obj.getRoomName())
          .eq(Objects.nonNull(obj.getRoomSort()), Room::getRoomSort, obj.getRoomSort())
          .eq(Objects.nonNull(obj.getCreateTime()), Room::getCreateTime, obj.getCreateTime())
          .eq(Objects.nonNull(obj.getCreateBy()), Room::getCreateBy, obj.getCreateBy())
          .eq(Objects.nonNull(obj.getUpdateTime()), Room::getUpdateTime, obj.getUpdateTime())
          .eq(Objects.nonNull(obj.getUpdateBy()), Room::getUpdateBy, obj.getUpdateBy())
          .eq(Objects.nonNull(obj.getVersionNum()), Room::getVersionNum, obj.getVersionNum())
          .orderByDesc(Room::getId)
      ;
    }

    return q;

  }

  private void setQueryListHeader(DynamicsPage<Room> page) {
    page
        .addHeader("id", "id")
        .addHeader("tenantId", "所属租户id")
        .addHeader("factoryId", "所属工厂id")
        .addHeader("storeyId", "楼层")
        .addHeader("roomCode", "编号")
        .addHeader("roomName", "楼层")
        .addHeader("roomSort", "排序")
        .addHeader("isDelete", "是否删除(0:否 1:是)")
        .addHeader("createTime", "创建时间")
        .addHeader("createBy", "创建人id")
        .addHeader("updateTime", "更新时间")
        .addHeader("updateBy", "更新人id")
        .addHeader("traceId", "链路追踪ID")
        .addHeader("version", "版本号")
    ;
  }


}

