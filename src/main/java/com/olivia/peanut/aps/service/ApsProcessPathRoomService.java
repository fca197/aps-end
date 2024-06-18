package com.olivia.peanut.aps.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.aps.api.entity.apsProcessPathRoom.*;
import com.olivia.peanut.aps.model.ApsProcessPathRoom;
import com.olivia.sdk.utils.DynamicsPage;
import java.util.List;

/**
 * (ApsProcessPathRoom)表服务接口
 *
 * @author peanut
 * @since 2024-04-01 17:49:19
 */
public interface ApsProcessPathRoomService extends MPJBaseService<ApsProcessPathRoom> {

  ApsProcessPathRoomQueryListRes queryList(ApsProcessPathRoomQueryListReq req);

  DynamicsPage<ApsProcessPathRoomExportQueryPageListInfoRes> queryPageList(ApsProcessPathRoomExportQueryPageListReq req);


  void setName(List<? extends ApsProcessPathRoomDto> apsProcessPathRoomDtoList);
}

