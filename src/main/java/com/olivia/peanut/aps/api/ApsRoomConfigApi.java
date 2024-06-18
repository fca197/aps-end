package com.olivia.peanut.aps.api;


import com.olivia.peanut.aps.api.entity.apsRoomConfig.*;
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
 * (ApsRoomConfig)对外API
 *
 * @author peanut
 * @since 2024-04-01 15:27:30
 */
// @FeignClient(value = "",contextId = "apsRoomConfig-api",url = "${ portal..center.endpoint:}")
public interface ApsRoomConfigApi {

  /**
   * 保存
   */
  @PostMapping("/apsRoomConfig/insert")
  ApsRoomConfigInsertRes insert(@RequestBody @Validated(InsertCheck.class) ApsRoomConfigInsertReq req);

  /**
   * 根据ID 删除
   */
  @PostMapping("/apsRoomConfig/deleteByIdList")
  ApsRoomConfigDeleteByIdListRes deleteByIdList(@RequestBody @Valid ApsRoomConfigDeleteByIdListReq req);

  /**
   * 查询
   */
  @PostMapping("/apsRoomConfig/queryList")
  ApsRoomConfigQueryListRes queryList(@RequestBody @Valid ApsRoomConfigQueryListReq req);

  /**
   * 根据ID 更新
   */
  @PostMapping("/apsRoomConfig/updateById")
  ApsRoomConfigUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) ApsRoomConfigUpdateByIdReq req);

  /**
   * 分页查询
   */
  @PostMapping("/apsRoomConfig/queryPageList")
  DynamicsPage<ApsRoomConfigExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid ApsRoomConfigExportQueryPageListReq req);

  /**
   * 导出
   */
  @PostMapping("/apsRoomConfig/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid ApsRoomConfigExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/apsRoomConfig/importData")
  ApsRoomConfigImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/apsRoomConfig/queryByIdList")
  ApsRoomConfigQueryByIdListRes queryByIdListRes(@RequestBody @Valid ApsRoomConfigQueryByIdListReq req);


}
