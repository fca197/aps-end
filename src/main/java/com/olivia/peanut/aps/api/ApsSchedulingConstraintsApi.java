package com.olivia.peanut.aps.api;


import com.olivia.peanut.aps.api.entity.apsSchedulingConstraints.*;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import com.olivia.sdk.utils.DynamicsPage;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * (ApsSchedulingConstraints)对外API
 *
 * @author peanut
 * @since 2024-04-13 21:32:12
 */
// @FeignClient(value = "",contextId = "apsSchedulingConstraints-api",url = "${ portal..center.endpoint:}")
public interface ApsSchedulingConstraintsApi {

  /**
   * 保存
   */
  @PostMapping("/apsSchedulingConstraints/insert")
  ApsSchedulingConstraintsInsertRes insert(@RequestBody @Validated(InsertCheck.class) ApsSchedulingConstraintsInsertReq req);

  /**
   * 根据ID 删除
   */
  @PostMapping("/apsSchedulingConstraints/deleteByIdList")
  ApsSchedulingConstraintsDeleteByIdListRes deleteByIdList(@RequestBody @Valid ApsSchedulingConstraintsDeleteByIdListReq req);

  /**
   * 查询
   */
  @PostMapping("/apsSchedulingConstraints/queryList")
  ApsSchedulingConstraintsQueryListRes queryList(@RequestBody @Valid ApsSchedulingConstraintsQueryListReq req);

  /**
   * 根据ID 更新
   */
  @PostMapping("/apsSchedulingConstraints/updateById")
  ApsSchedulingConstraintsUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) ApsSchedulingConstraintsUpdateByIdReq req);

  /**
   * 分页查询
   */
  @PostMapping("/apsSchedulingConstraints/queryPageList")
  DynamicsPage<ApsSchedulingConstraintsExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid ApsSchedulingConstraintsExportQueryPageListReq req);

  /**
   * 导出
   */
  @PostMapping("/apsSchedulingConstraints/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid ApsSchedulingConstraintsExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/apsSchedulingConstraints/importData")
  ApsSchedulingConstraintsImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/apsSchedulingConstraints/queryByIdList")
  ApsSchedulingConstraintsQueryByIdListRes queryByIdListRes(@RequestBody @Valid ApsSchedulingConstraintsQueryByIdListReq req);


  @GetMapping("/apsSchedulingConstraints/getUseField")
  ApsSchedulingConstraintsGetUseFieldRes getUseField();
}
