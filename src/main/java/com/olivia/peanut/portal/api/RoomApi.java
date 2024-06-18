package com.olivia.peanut.portal.api;


import com.olivia.peanut.portal.api.entity.room.*;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import com.olivia.sdk.utils.DynamicsPage;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * 房间信息(Room)对外API
 *
 * @author makejava
 * @since 2024-03-11 17:20:54
 */
// @FeignClient(value = "",contextId = "room-api",url = "${ portal..center.endpoint:}")
public interface RoomApi {

  /**
   * 保存 房间信息
   */
  @PostMapping("/room/insert")
  RoomInsertRes insert(@RequestBody @Validated(InsertCheck.class) RoomInsertReq req);

  /**
   * 根据ID 删除 房间信息
   */
  @PostMapping("/room/deleteByIdList")
  RoomDeleteByIdListRes deleteByIdList(@RequestBody @Valid RoomDeleteByIdListReq req);

  /**
   * 查询 房间信息
   */
  @PostMapping("/room/queryList")
  RoomQueryListRes queryList(@RequestBody @Valid RoomQueryListReq req);

  /**
   * 根据ID 更新 房间信息
   */
  @PostMapping("/room/updateById")
  RoomUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) RoomUpdateByIdReq req);

  /**
   * 分页查询 房间信息
   */
  @PostMapping("/room/queryPageList")
  DynamicsPage<RoomExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid RoomExportQueryPageListReq req);

  /**
   * 导出 房间信息
   */
  @PostMapping("/room/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid RoomExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/room/importData")
  RoomImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/room/queryByIdList")
  RoomQueryByIdListRes queryByIdListRes(@RequestBody @Valid RoomQueryByIdListReq req);

  @PostMapping("/room/saveBatch")
  RoomSaveBatchRes saveBatch(@RequestBody @Valid RoomSaveBatchReq req);


}
