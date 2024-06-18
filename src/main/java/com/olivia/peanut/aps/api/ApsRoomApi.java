package com.olivia.peanut.aps.api;


import com.olivia.peanut.aps.api.entity.apsRoom.*;
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
 * (ApsRoom)对外API
 *
 * @author peanut
 * @since 2024-04-01 15:27:29
 */
// @FeignClient(value = "",contextId = "apsRoom-api",url = "${ portal..center.endpoint:}")
public interface ApsRoomApi {

  /**
   * 保存
   */
  @PostMapping("/apsRoom/insert")
  ApsRoomInsertRes insert(@RequestBody @Validated(InsertCheck.class) ApsRoomInsertReq req);

  /**
   * 根据ID 删除
   */
  @PostMapping("/apsRoom/deleteByIdList")
  ApsRoomDeleteByIdListRes deleteByIdList(@RequestBody @Valid ApsRoomDeleteByIdListReq req);

  /**
   * 查询
   */
  @PostMapping("/apsRoom/queryList")
  ApsRoomQueryListRes queryList(@RequestBody @Valid ApsRoomQueryListReq req);

  /**
   * 根据ID 更新
   */
  @PostMapping("/apsRoom/updateById")
  ApsRoomUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) ApsRoomUpdateByIdReq req);

  /**
   * 分页查询
   */
  @PostMapping("/apsRoom/queryPageList")
  DynamicsPage<ApsRoomExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid ApsRoomExportQueryPageListReq req);

  /**
   * 导出
   */
  @PostMapping("/apsRoom/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid ApsRoomExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/apsRoom/importData")
  ApsRoomImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/apsRoom/queryByIdList")
  ApsRoomQueryByIdListRes queryByIdListRes(@RequestBody @Valid ApsRoomQueryByIdListReq req);


}
