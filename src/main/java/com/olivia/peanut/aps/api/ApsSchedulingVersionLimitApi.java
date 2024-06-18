package com.olivia.peanut.aps.api;


import com.olivia.peanut.aps.api.entity.apsSchedulingVersionLimit.*;
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
 * (ApsSchedulingVersionLimit)对外API
 *
 * @author peanut
 * @since 2024-04-19 14:56:59
 */
// @FeignClient(value = "",contextId = "apsSchedulingVersionLimit-api",url = "${ portal..center.endpoint:}")
public interface ApsSchedulingVersionLimitApi {

  /**
   * 保存
   */
  @PostMapping("/apsSchedulingVersionLimit/insert")
  ApsSchedulingVersionLimitInsertRes insert(@RequestBody @Validated(InsertCheck.class) ApsSchedulingVersionLimitInsertReq req);

  /**
   * 根据ID 删除
   */
  @PostMapping("/apsSchedulingVersionLimit/deleteByIdList")
  ApsSchedulingVersionLimitDeleteByIdListRes deleteByIdList(@RequestBody @Valid ApsSchedulingVersionLimitDeleteByIdListReq req);

  /**
   * 查询
   */
  @PostMapping("/apsSchedulingVersionLimit/queryList")
  ApsSchedulingVersionLimitQueryListRes queryList(@RequestBody @Valid ApsSchedulingVersionLimitQueryListReq req);

  /**
   * 根据ID 更新
   */
  @PostMapping("/apsSchedulingVersionLimit/updateById")
  ApsSchedulingVersionLimitUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) ApsSchedulingVersionLimitUpdateByIdReq req);

  /**
   * 分页查询
   */
  @PostMapping("/apsSchedulingVersionLimit/queryPageList")
  DynamicsPage<ApsSchedulingVersionLimitExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid ApsSchedulingVersionLimitExportQueryPageListReq req);

  /**
   * 导出
   */
  @PostMapping("/apsSchedulingVersionLimit/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid ApsSchedulingVersionLimitExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/apsSchedulingVersionLimit/importData")
  ApsSchedulingVersionLimitImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/apsSchedulingVersionLimit/queryByIdList")
  ApsSchedulingVersionLimitQueryByIdListRes queryByIdListRes(@RequestBody @Valid ApsSchedulingVersionLimitQueryByIdListReq req);


}
