package com.olivia.peanut.aps.api;


import com.olivia.peanut.aps.api.entity.apsProcessPathRoom.*;
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
 * (ApsProcessPathRoom)对外API
 *
 * @author peanut
 * @since 2024-04-01 17:49:18
 */
// @FeignClient(value = "",contextId = "apsProcessPathRoom-api",url = "${ portal..center.endpoint:}")
public interface ApsProcessPathRoomApi {

  /**
   * 保存
   */
  @PostMapping("/apsProcessPathRoom/insert")
  ApsProcessPathRoomInsertRes insert(@RequestBody @Validated(InsertCheck.class) ApsProcessPathRoomInsertReq req);

  /**
   * 根据ID 删除
   */
  @PostMapping("/apsProcessPathRoom/deleteByIdList")
  ApsProcessPathRoomDeleteByIdListRes deleteByIdList(@RequestBody @Valid ApsProcessPathRoomDeleteByIdListReq req);

  /**
   * 查询
   */
  @PostMapping("/apsProcessPathRoom/queryList")
  ApsProcessPathRoomQueryListRes queryList(@RequestBody @Valid ApsProcessPathRoomQueryListReq req);

  /**
   * 根据ID 更新
   */
  @PostMapping("/apsProcessPathRoom/updateById")
  ApsProcessPathRoomUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) ApsProcessPathRoomUpdateByIdReq req);

  /**
   * 分页查询
   */
  @PostMapping("/apsProcessPathRoom/queryPageList")
  DynamicsPage<ApsProcessPathRoomExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid ApsProcessPathRoomExportQueryPageListReq req);

  /**
   * 导出
   */
  @PostMapping("/apsProcessPathRoom/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid ApsProcessPathRoomExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/apsProcessPathRoom/importData")
  ApsProcessPathRoomImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/apsProcessPathRoom/queryByIdList")
  ApsProcessPathRoomQueryByIdListRes queryByIdListRes(@RequestBody @Valid ApsProcessPathRoomQueryByIdListReq req);


}
