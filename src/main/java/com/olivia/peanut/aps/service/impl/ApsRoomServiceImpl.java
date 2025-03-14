package com.olivia.peanut.aps.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.api.entity.apsRoom.*;
import com.olivia.peanut.aps.api.entity.apsRoomConfig.ApsRoomConfigDto;
import com.olivia.peanut.aps.mapper.ApsRoomMapper;
import com.olivia.peanut.aps.model.ApsRoom;
import com.olivia.peanut.aps.model.ApsRoomConfig;
import com.olivia.peanut.aps.service.ApsRoomConfigService;
import com.olivia.peanut.aps.service.ApsRoomService;
import com.olivia.peanut.base.model.Factory;
import com.olivia.peanut.base.service.FactoryService;
import com.olivia.sdk.ann.SetUserName;
import com.olivia.sdk.comment.ServiceComment;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.RunUtils;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * (ApsRoom)表服务实现类
 *
 * @author peanut
 * @since 2024-04-01 15:27:30
 */
@Service("apsRoomService")
@Transactional
public class ApsRoomServiceImpl extends MPJBaseServiceImpl<ApsRoomMapper, ApsRoom> implements ApsRoomService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  @Resource
  ApsRoomConfigService roomConfigService;
  @Resource
  FactoryService factoryService;

  public @Override ApsRoomQueryListRes queryList(ApsRoomQueryListReq req) {

    MPJLambdaWrapper<ApsRoom> q = getWrapper(req.getData());
    List<ApsRoom> list = this.list(q);

    List<ApsRoomDto> dataList = list.stream().map(t -> $.copy(t, ApsRoomDto.class)).collect(Collectors.toList());
//    setName(dataList);
    ((ApsRoomServiceImpl) AopContext.currentProxy()).setName(dataList);

    return new ApsRoomQueryListRes().setDataList(dataList);
  }

  public @Override DynamicsPage<ApsRoomExportQueryPageListInfoRes> queryPageList(ApsRoomExportQueryPageListReq req) {

    DynamicsPage<ApsRoom> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsRoom> q = getWrapper(req.getData());
    List<ApsRoomExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsRoom> list = this.page(page, q);
      IPage<ApsRoomExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsRoomExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsRoomExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<ApsRoomExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsRoomExportQueryPageListInfoRes.class);
//    setName(listInfoRes);
    ((ApsRoomServiceImpl) AopContext.currentProxy()).setName(listInfoRes);

    return DynamicsPage.init(page, listInfoRes);
  }

  @SetUserName
  public @Override void setName(List<? extends ApsRoomDto> apsRoomDtoList) {
    if (CollUtil.isEmpty(apsRoomDtoList)) {
      return;
    }
    Map<Long, String> factoryMap = factoryService.listByIds(apsRoomDtoList.stream().map(ApsRoomDto::getFactoryId).toList()).stream()
        .collect(Collectors.toMap(Factory::getId, Factory::getFactoryName));
    List<Runnable> runnableList = new ArrayList<>();
    apsRoomDtoList.forEach(t -> {
      runnableList.add(() -> {
        List<ApsRoomConfig> configList = this.roomConfigService.list(new LambdaQueryWrapper<ApsRoomConfig>().eq(ApsRoomConfig::getRoomId, t.getId()));
        t.setConfigList($.copyList(configList, ApsRoomConfigDto.class));
        t.setFactoryName(factoryMap.get(t.getFactoryId()));
      });
    });
    RunUtils.run("apsRoomDtoList", runnableList);
  }

  @Override
  @Transactional
  public ApsRoomInsertRes save(ApsRoomInsertReq req) {
    ApsRoom room = $.copy(req, ApsRoom.class);
    room.setId(IdWorker.getId());
    this.save(room);
    List<ApsRoomConfig> configList = $.copyList(req.getConfigList(), ApsRoomConfig.class);
    configList.forEach(t -> t.setRoomId(room.getId()));
    roomConfigService.saveBatch(configList);
    return new ApsRoomInsertRes().setCount(1).setId(room.getId());
  }

  @Override
  @Transactional
  public void updateById(ApsRoomUpdateByIdReq req) {
    this.updateById($.copy(req, ApsRoom.class));
    this.roomConfigService.remove(new LambdaQueryWrapper<ApsRoomConfig>().eq(ApsRoomConfig::getRoomId, req.getId()));
    List<ApsRoomConfig> entityList = $.copyList(req.getConfigList(), ApsRoomConfig.class);
    entityList.forEach(t -> t.setRoomId(req.getId()).setId(IdWorker.getId()));
    this.roomConfigService.saveBatch(entityList);
  }

  // 以下为私有对象封装
  private MPJLambdaWrapper<ApsRoom> getWrapper(ApsRoomDto obj) {
    MPJLambdaWrapper<ApsRoom> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q.eq(StringUtils.isNoneBlank(obj.getRoomCode()), ApsRoom::getRoomCode, obj.getRoomCode())
          .eq(StringUtils.isNoneBlank(obj.getRoomName()), ApsRoom::getRoomName, obj.getRoomName())
          .eq(Objects.nonNull(obj.getFactoryId()), ApsRoom::getFactoryId, obj.getFactoryId())

      ;
    }
    q.orderByDesc(ApsRoom::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsRoom> page) {

    ServiceComment.header(page, "ApsRoomService#queryPageList");

  }


}

