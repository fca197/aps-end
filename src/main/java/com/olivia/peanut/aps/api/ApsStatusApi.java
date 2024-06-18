package com.olivia.peanut.aps.api;


import com.olivia.peanut.aps.api.entity.apsStatus.*;
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
 * (ApsStatus)对外API
 *
 * @author peanut
 * @since 2024-04-01 10:50:11
 */
// @FeignClient(value = "",contextId = "apsStatus-api",url = "${ portal..center.endpoint:}")
public interface ApsStatusApi {

  /**
   * 保存
   */
  @PostMapping("/apsStatus/insert")
  ApsStatusInsertRes insert(@RequestBody @Validated(InsertCheck.class) ApsStatusInsertReq req);

  /**
   * 根据ID 删除
   */
  @PostMapping("/apsStatus/deleteByIdList")
  ApsStatusDeleteByIdListRes deleteByIdList(@RequestBody @Valid ApsStatusDeleteByIdListReq req);

  /**
   * 查询
   */
  @PostMapping("/apsStatus/queryList")
  ApsStatusQueryListRes queryList(@RequestBody @Valid ApsStatusQueryListReq req);

  /**
   * 根据ID 更新
   */
  @PostMapping("/apsStatus/updateById")
  ApsStatusUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) ApsStatusUpdateByIdReq req);

  /**
   * 分页查询
   */
  @PostMapping("/apsStatus/queryPageList")
  DynamicsPage<ApsStatusExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid ApsStatusExportQueryPageListReq req);

  /**
   * 导出
   */
  @PostMapping("/apsStatus/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid ApsStatusExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/apsStatus/importData")
  ApsStatusImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/apsStatus/queryByIdList")
  ApsStatusQueryByIdListRes queryByIdListRes(@RequestBody @Valid ApsStatusQueryByIdListReq req);


}
