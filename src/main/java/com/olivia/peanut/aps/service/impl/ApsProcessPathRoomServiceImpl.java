package com.olivia.peanut.aps.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.api.entity.apsProcessPathRoom.*;
import com.olivia.peanut.aps.mapper.ApsProcessPathRoomMapper;
import com.olivia.peanut.aps.model.ApsProcessPathRoom;
import com.olivia.peanut.aps.service.ApsProcessPathRoomService;
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
 * (ApsProcessPathRoom)表服务实现类
 *
 * @author peanut
 * @since 2024-04-01 17:49:19
 */
@Service("apsProcessPathRoomService")
@Transactional
public class ApsProcessPathRoomServiceImpl extends MPJBaseServiceImpl<ApsProcessPathRoomMapper, ApsProcessPathRoom> implements ApsProcessPathRoomService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();


  public @Override ApsProcessPathRoomQueryListRes queryList(ApsProcessPathRoomQueryListReq req) {

    MPJLambdaWrapper<ApsProcessPathRoom> q = getWrapper(req.getData());
    List<ApsProcessPathRoom> list = this.list(q);

    List<ApsProcessPathRoomDto> dataList = list.stream().map(t -> $.copy(t, ApsProcessPathRoomDto.class)).collect(Collectors.toList());

    return new ApsProcessPathRoomQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<ApsProcessPathRoomExportQueryPageListInfoRes> queryPageList(ApsProcessPathRoomExportQueryPageListReq req) {

    DynamicsPage<ApsProcessPathRoom> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsProcessPathRoom> q = getWrapper(req.getData());
    List<ApsProcessPathRoomExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsProcessPathRoom> list = this.page(page, q);
      IPage<ApsProcessPathRoomExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsProcessPathRoomExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsProcessPathRoomExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<ApsProcessPathRoomExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsProcessPathRoomExportQueryPageListInfoRes.class);

    return DynamicsPage.init(page, listInfoRes);
  }


  @SetUserName
  public @Override void setName(List<? extends ApsProcessPathRoomDto> apsProcessPathRoomDtoList) {

  }

  // 以下为私有对象封装


  private MPJLambdaWrapper<ApsProcessPathRoom> getWrapper(ApsProcessPathRoomDto obj) {
    MPJLambdaWrapper<ApsProcessPathRoom> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(Objects.nonNull(obj.getProcessPathId()), ApsProcessPathRoom::getProcessPathId, obj.getProcessPathId())
          .eq(Objects.nonNull(obj.getRoomId()), ApsProcessPathRoom::getRoomId, obj.getRoomId())
          .eq(Objects.nonNull(obj.getFactoryId()), ApsProcessPathRoom::getFactoryId, obj.getFactoryId())

      ;
    }
    q.orderByDesc(ApsProcessPathRoom::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsProcessPathRoom> page) {

    ServiceComment.header(page, "ApsProcessPathRoomService#queryPageList");

  }


}

