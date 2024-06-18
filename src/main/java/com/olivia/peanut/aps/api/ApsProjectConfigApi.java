package com.olivia.peanut.aps.api;


import com.olivia.peanut.aps.api.entity.apsProjectConfig.*;
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
 * (ApsProjectConfig)对外API
 *
 * @author peanut
 * @since 2024-03-30 16:21:19
 */
// @FeignClient(value = "",contextId = "apsProjectConfig-api",url = "${ portal..center.endpoint:}")
public interface ApsProjectConfigApi {

  /**
   * 保存
   */
  @PostMapping("/apsProjectConfig/insert")
  ApsProjectConfigInsertRes insert(@RequestBody @Validated(InsertCheck.class) ApsProjectConfigInsertReq req);

  /**
   * 根据ID 删除
   */
  @PostMapping("/apsProjectConfig/deleteByIdList")
  ApsProjectConfigDeleteByIdListRes deleteByIdList(@RequestBody @Valid ApsProjectConfigDeleteByIdListReq req);

  /**
   * 查询
   */
  @PostMapping("/apsProjectConfig/queryList")
  ApsProjectConfigQueryListRes queryList(@RequestBody @Valid ApsProjectConfigQueryListReq req);

  /**
   * 根据ID 更新
   */
  @PostMapping("/apsProjectConfig/updateById")
  ApsProjectConfigUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) ApsProjectConfigUpdateByIdReq req);

  /**
   * 分页查询
   */
  @PostMapping("/apsProjectConfig/queryPageList")
  DynamicsPage<ApsProjectConfigExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid ApsProjectConfigExportQueryPageListReq req);

  /**
   * 导出
   */
  @PostMapping("/apsProjectConfig/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid ApsProjectConfigExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/apsProjectConfig/importData")
  ApsProjectConfigImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/apsProjectConfig/queryByIdList")
  ApsProjectConfigQueryByIdListRes queryByIdListRes(@RequestBody @Valid ApsProjectConfigQueryByIdListReq req);


}
