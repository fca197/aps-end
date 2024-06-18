package com.olivia.peanut.portal.api.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.portal.api.RoomApi;
import com.olivia.peanut.portal.api.entity.room.*;
import com.olivia.peanut.portal.api.impl.listener.RoomImportListener;
import com.olivia.peanut.portal.model.Room;
import com.olivia.peanut.portal.service.RoomService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 房间信息(Room)表服务实现类
 *
 * @author makejava
 * @since 2024-03-11 17:20:54
 */
@RestController
public class RoomApiImpl implements RoomApi {

  private @Autowired RoomService roomService;

  /****
   * insert
   *
   */
  public @Override RoomInsertRes insert(RoomInsertReq req) {
    this.roomService.save($.copy(req, Room.class));
    return new RoomInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override RoomDeleteByIdListRes deleteByIdList(RoomDeleteByIdListReq req) {
    roomService.removeByIds(req.getIdList());
    return new RoomDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override RoomQueryListRes queryList(RoomQueryListReq req) {
    return roomService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override RoomUpdateByIdRes updateById(RoomUpdateByIdReq req) {
    roomService.updateById($.copy(req, Room.class));
    return new RoomUpdateByIdRes();

  }

  public @Override DynamicsPage<RoomExportQueryPageListInfoRes> queryPageList(RoomExportQueryPageListReq req) {
    return roomService.queryPageList(req);
  }

  public @Override void queryPageListExport(RoomExportQueryPageListReq req) {
    DynamicsPage<RoomExportQueryPageListInfoRes> page = queryPageList(req);
    List<RoomExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<RoomExportQueryPageListInfoRes> listInfoRes = $.copyList(list, RoomExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(RoomExportQueryPageListInfoRes.class, listInfoRes, "房间信息");
  }

  public @Override RoomImportRes importData(@RequestParam("file") MultipartFile file) {
    List<RoomImportReq> reqList = PoiExcelUtil.readData(file, new RoomImportListener(), RoomImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<Room> readList = $.copyList(reqList, Room.class);
    boolean bool = roomService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new RoomImportRes().setCount(c);
  }

  public @Override RoomQueryByIdListRes queryByIdListRes(RoomQueryByIdListReq req) {
    MPJLambdaWrapper<Room> q = new MPJLambdaWrapper<Room>(Room.class)
        .selectAll(Room.class).in(Room::getId, req.getIdList());
    List<Room> list = this.roomService.list(q);
    List<RoomDto> dataList = $.copyList(list, RoomDto.class);
    return new RoomQueryByIdListRes().setDataList(dataList);
  }

  @Override
  public RoomSaveBatchRes saveBatch(RoomSaveBatchReq req) {
    List<Room> roomList = new ArrayList<>();
    IntStream.range(1, req.getRoomCount() + 1).forEach(i -> {
      Room room = new Room();
      room.setId(IdWorker.getId());
      room.setFactoryId(req.getFactoryId());
      room.setStoreyId(req.getStoreyId());
      room.setRoomSort(i).setRoomCode(req.getRoomPrefix() + StringUtils.right("0" + i, 2));
      room.setRoomName(room.getRoomCode());
      roomList.add(room);
    });
    this.roomService.saveBatch(roomList);
    return new RoomSaveBatchRes().setIdList(roomList.stream().map(Room::getId).toList());
  }
}
