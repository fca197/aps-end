package com.olivia.peanut.portal.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.portal.api.entity.room.RoomExportQueryPageListInfoRes;
import com.olivia.peanut.portal.api.entity.room.RoomExportQueryPageListReq;
import com.olivia.peanut.portal.api.entity.room.RoomQueryListReq;
import com.olivia.peanut.portal.api.entity.room.RoomQueryListRes;
import com.olivia.peanut.portal.model.Room;
import com.olivia.sdk.utils.DynamicsPage;

/**
 * 房间信息(Room)表服务接口
 *
 * @author makejava
 * @since 2024-03-11 17:20:54
 */
public interface RoomService extends MPJBaseService<Room> {

  RoomQueryListRes queryList(RoomQueryListReq req);

  DynamicsPage<RoomExportQueryPageListInfoRes> queryPageList(RoomExportQueryPageListReq req);

}

