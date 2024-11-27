package com.olivia.peanut.aps.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.api.entity.apsRoomConfig.*;
import com.olivia.peanut.aps.mapper.ApsRoomConfigMapper;
import com.olivia.peanut.aps.model.ApsRoomConfig;
import com.olivia.peanut.aps.service.ApsRoomConfigService;
import com.olivia.sdk.ann.SetUserName;
import com.olivia.sdk.comment.ServiceComment;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * (ApsRoomConfig)表服务实现类
 *
 * @author peanut
 * @since 2024-04-01 15:27:30
 */
@Service("apsRoomConfigService")
@Transactional
public class ApsRoomConfigServiceImpl extends MPJBaseServiceImpl<ApsRoomConfigMapper, ApsRoomConfig> implements ApsRoomConfigService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();


  public @Override ApsRoomConfigQueryListRes queryList(ApsRoomConfigQueryListReq req) {

    MPJLambdaWrapper<ApsRoomConfig> q = getWrapper(req.getData());
    List<ApsRoomConfig> list = this.list(q);

    List<ApsRoomConfigDto> dataList = list.stream().map(t -> $.copy(t, ApsRoomConfigDto.class)).collect(Collectors.toList());

    return new ApsRoomConfigQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<ApsRoomConfigExportQueryPageListInfoRes> queryPageList(ApsRoomConfigExportQueryPageListReq req) {

    DynamicsPage<ApsRoomConfig> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsRoomConfig> q = getWrapper(req.getData());
    List<ApsRoomConfigExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsRoomConfig> list = this.page(page, q);
      IPage<ApsRoomConfigExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsRoomConfigExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsRoomConfigExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<ApsRoomConfigExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsRoomConfigExportQueryPageListInfoRes.class);

    return DynamicsPage.init(page, listInfoRes);
  }


  @SetUserName
  public @Override void setName(List<? extends ApsRoomConfigDto> apsRoomConfigDtoList) {

  }

  // 以下为私有对象封装


  private MPJLambdaWrapper<ApsRoomConfig> getWrapper(ApsRoomConfigDto obj) {
    MPJLambdaWrapper<ApsRoomConfig> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(Objects.nonNull(obj.getRoomId()), ApsRoomConfig::getRoomId, obj.getRoomId())
          .eq(Objects.nonNull(obj.getSectionId()), ApsRoomConfig::getSectionId, obj.getSectionId())
          .eq(Objects.nonNull(obj.getStationId()), ApsRoomConfig::getStationId, obj.getStationId())
          .eq(Objects.nonNull(obj.getExecuteTime()), ApsRoomConfig::getExecuteTime, obj.getExecuteTime())
          .eq(Objects.nonNull(obj.getFactoryId()), ApsRoomConfig::getFactoryId, obj.getFactoryId())

      ;
    }
    q.orderByDesc(ApsRoomConfig::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsRoomConfig> page) {

    ServiceComment.header(page, "ApsRoomConfigService#queryPageList");

  }


}

