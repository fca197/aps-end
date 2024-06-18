package com.olivia.peanut.aps.api;


import com.olivia.peanut.aps.api.entity.apsProcessPath.*;
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
 * (ApsProcessPath)对外API
 *
 * @author peanut
 * @since 2024-04-01 17:49:17
 */
// @FeignClient(value = "",contextId = "apsProcessPath-api",url = "${ portal..center.endpoint:}")
public interface ApsProcessPathApi {

  /**
   * 保存
   */
  @PostMapping("/apsProcessPath/insert")
  ApsProcessPathInsertRes insert(@RequestBody @Validated(InsertCheck.class) ApsProcessPathInsertReq req);

  /**
   * 根据ID 删除
   */
  @PostMapping("/apsProcessPath/deleteByIdList")
  ApsProcessPathDeleteByIdListRes deleteByIdList(@RequestBody @Valid ApsProcessPathDeleteByIdListReq req);

  /**
   * 查询
   */
  @PostMapping("/apsProcessPath/queryList")
  ApsProcessPathQueryListRes queryList(@RequestBody @Valid ApsProcessPathQueryListReq req);

  /**
   * 根据ID 更新
   */
  @PostMapping("/apsProcessPath/updateById")
  ApsProcessPathUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) ApsProcessPathUpdateByIdReq req);

  /**
   * 分页查询
   */
  @PostMapping("/apsProcessPath/queryPageList")
  DynamicsPage<ApsProcessPathExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid ApsProcessPathExportQueryPageListReq req);

  /**
   * 导出
   */
  @PostMapping("/apsProcessPath/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid ApsProcessPathExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/apsProcessPath/importData")
  ApsProcessPathImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/apsProcessPath/queryByIdList")
  ApsProcessPathQueryByIdListRes queryByIdListRes(@RequestBody @Valid ApsProcessPathQueryByIdListReq req);


}
