package com.olivia.peanut.aps.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.api.entity.apsProcessPath.*;
import com.olivia.peanut.aps.api.entity.apsProcessPathRoom.ApsProcessPathRoomDto;
import com.olivia.peanut.aps.api.entity.apsRoomConfig.ApsRoomConfigDto;
import com.olivia.peanut.aps.mapper.ApsProcessPathMapper;
import com.olivia.peanut.aps.model.ApsProcessPath;
import com.olivia.peanut.aps.model.ApsProcessPathRoom;
import com.olivia.peanut.aps.model.ApsRoomConfig;
import com.olivia.peanut.aps.service.ApsProcessPathRoomService;
import com.olivia.peanut.aps.service.ApsProcessPathService;
import com.olivia.peanut.aps.service.ApsRoomConfigService;
import com.olivia.peanut.portal.model.Factory;
import com.olivia.peanut.portal.service.FactoryService;
import com.olivia.sdk.comment.ServiceComment;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.RunUtils;
import jakarta.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * (ApsProcessPath)表服务实现类
 *
 * @author peanut
 * @since 2024-04-01 17:49:18
 */
@Service("apsProcessPathService")
@Transactional
public class ApsProcessPathServiceImpl extends MPJBaseServiceImpl<ApsProcessPathMapper, ApsProcessPath> implements ApsProcessPathService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  @Resource
  ApsProcessPathRoomService apsProcessPathRoomService;
  @Resource
  FactoryService factoryService;
  @Resource
  ApsRoomConfigService apsRoomConfigService;

  public @Override ApsProcessPathQueryListRes queryList(ApsProcessPathQueryListReq req) {

    MPJLambdaWrapper<ApsProcessPath> q = getWrapper(req.getData());
    List<ApsProcessPath> list = this.list(q);

    List<ApsProcessPathDto> dataList = list.stream().map(t -> $.copy(t, ApsProcessPathDto.class)).collect(Collectors.toList());
    setName(dataList);
    return new ApsProcessPathQueryListRes().setDataList(dataList);
  }

  public @Override DynamicsPage<ApsProcessPathExportQueryPageListInfoRes> queryPageList(ApsProcessPathExportQueryPageListReq req) {

    DynamicsPage<ApsProcessPath> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsProcessPath> q = getWrapper(req.getData());
    List<ApsProcessPathExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsProcessPath> list = this.page(page, q);
      IPage<ApsProcessPathExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsProcessPathExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsProcessPathExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<ApsProcessPathExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsProcessPathExportQueryPageListInfoRes.class);
    setName(listInfoRes);
    return DynamicsPage.init(page, listInfoRes);
  }

  public @Override void setName(List<? extends ApsProcessPathDto> apsProcessPathDtoList) {
    if (CollUtil.isEmpty(apsProcessPathDtoList)) {
      return;
    }
    Map<Long, String> factoryMap = factoryService.listByIds(apsProcessPathDtoList.stream().map(ApsProcessPathDto::getFactoryId).collect(Collectors.toList())).stream()
        .collect(Collectors.toMap(Factory::getId, Factory::getFactoryName));

    List<Runnable> runnableList = new ArrayList<>();

    apsProcessPathDtoList.forEach(t -> {
      runnableList.add(() -> {
        List<ApsProcessPathRoom> pathRoomList = this.apsProcessPathRoomService.list(
            new LambdaQueryWrapper<ApsProcessPathRoom>().eq(ApsProcessPathRoom::getProcessPathId, t.getId()));
        t.setPathRoomList($.copyList(pathRoomList, ApsProcessPathRoomDto.class));
        t.setFactoryName(factoryMap.get(t.getFactoryId()));
        t.getPathRoomList().forEach(tr -> {
          List<ApsRoomConfig> roomConfigList = apsRoomConfigService.list(new LambdaQueryWrapper<ApsRoomConfig>().eq(ApsRoomConfig::getRoomId, tr.getRoomId()));
          tr.setApsRoomConfigList($.copyList(roomConfigList, ApsRoomConfigDto.class));
        });
      });
    });
    RunUtils.run("apsProcessPathService", runnableList);
  }

  @Override
  @Transactional
  public ApsProcessPathInsertRes save(ApsProcessPathInsertReq req) {
    ApsProcessPath path = $.copy(req, ApsProcessPath.class);
    path.setId(IdWorker.getId());
    this.save(path);
    List<ApsProcessPathRoom> roomList = $.copyList(req.getPathRoomList(), ApsProcessPathRoom.class);
    roomList.forEach(t -> t.setProcessPathId(path.getId()));
    this.apsProcessPathRoomService.saveBatch(roomList);
    return new ApsProcessPathInsertRes().setCount(1).setId(path.getId());
  }

  @Override
  @Transactional
  public void updateById(ApsProcessPathUpdateByIdReq req) {
    ApsProcessPath processPath = $.copy(req, ApsProcessPath.class);
    this.updateById(processPath);
    this.apsProcessPathRoomService.remove(new LambdaQueryWrapper<ApsProcessPathRoom>().eq(ApsProcessPathRoom::getProcessPathId, req.getId()));
    List<ApsProcessPathRoom> roomList = $.copyList(req.getPathRoomList(), ApsProcessPathRoom.class);
    roomList.forEach(t -> t.setProcessPathId(req.getId()).setId(IdWorker.getId()));
    this.apsProcessPathRoomService.saveBatch(roomList);

  }

  // 以下为私有对象封装


  private MPJLambdaWrapper<ApsProcessPath> getWrapper(ApsProcessPathDto obj) {
    MPJLambdaWrapper<ApsProcessPath> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q.eq(StringUtils.isNoneBlank(obj.getProcessPathCode()), ApsProcessPath::getProcessPathCode, obj.getProcessPathCode())
          .eq(StringUtils.isNoneBlank(obj.getProcessPathName()), ApsProcessPath::getProcessPathName, obj.getProcessPathName())
          .eq(StringUtils.isNoneBlank(obj.getProcessPathRemark()), ApsProcessPath::getProcessPathRemark, obj.getProcessPathRemark())
          .eq(Objects.nonNull(obj.getIsDefault()), ApsProcessPath::getIsDefault, obj.getIsDefault())
          .eq(Objects.nonNull(obj.getFactoryId()), ApsProcessPath::getFactoryId, obj.getFactoryId())

      ;
    }
    q.orderByDesc(ApsProcessPath::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsProcessPath> page) {

    ServiceComment.header(page, "ApsProcessPathService#queryPageList");

  }


}

