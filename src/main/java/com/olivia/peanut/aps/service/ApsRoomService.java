package com.olivia.peanut.aps.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.aps.api.entity.apsRoom.*;
import com.olivia.peanut.aps.model.ApsRoom;
import com.olivia.sdk.utils.DynamicsPage;
import java.util.List;

/**
 * (ApsRoom)表服务接口
 *
 * @author peanut
 * @since 2024-04-01 15:27:29
 */
public interface ApsRoomService extends MPJBaseService<ApsRoom> {

  ApsRoomQueryListRes queryList(ApsRoomQueryListReq req);

  DynamicsPage<ApsRoomExportQueryPageListInfoRes> queryPageList(ApsRoomExportQueryPageListReq req);


  void setName(List<? extends ApsRoomDto> apsRoomDtoList);

  ApsRoomInsertRes save(ApsRoomInsertReq req);

  void updateById(ApsRoomUpdateByIdReq req);
}

