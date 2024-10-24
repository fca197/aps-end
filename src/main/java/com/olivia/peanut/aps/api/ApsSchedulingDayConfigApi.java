package com.olivia.peanut.aps.api;

import org.springframework.validation.annotation.Validated;
import com.olivia.sdk.utils.DynamicsPage;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.olivia.peanut.aps.api.entity.apsSchedulingDayConfig.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


/**
 * 排程版本表(ApsSchedulingDayConfig)对外API
 *
 * @author peanut
 * @since 2024-07-19 19:19:48
 */
// @FeignClient(value = "",contextId = "apsSchedulingDayConfig-api",url = "${ portal..center.endpoint:}")
public interface ApsSchedulingDayConfigApi {

  /**
   * 保存 排程版本表
   */
  @PostMapping("/apsSchedulingDayConfig/insert")
  ApsSchedulingDayConfigInsertRes insert(@RequestBody @Validated(InsertCheck.class) ApsSchedulingDayConfigInsertReq req);

  /**
   * 根据ID 删除 排程版本表
   */
  @PostMapping("/apsSchedulingDayConfig/deleteByIdList")
  ApsSchedulingDayConfigDeleteByIdListRes deleteByIdList(@RequestBody @Valid ApsSchedulingDayConfigDeleteByIdListReq req);

  /**
   * 查询 排程版本表
   */
  @PostMapping("/apsSchedulingDayConfig/queryList")
  ApsSchedulingDayConfigQueryListRes queryList(@RequestBody @Valid ApsSchedulingDayConfigQueryListReq req);

  /**
   * 根据ID 更新 排程版本表
   */
  @PostMapping("/apsSchedulingDayConfig/updateById")
  ApsSchedulingDayConfigUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) ApsSchedulingDayConfigUpdateByIdReq req);

  /**
   * 分页查询 排程版本表
   */
  @PostMapping("/apsSchedulingDayConfig/queryPageList")
  DynamicsPage<ApsSchedulingDayConfigExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid ApsSchedulingDayConfigExportQueryPageListReq req);

  /**
   * 导出 排程版本表
   */
  @PostMapping("/apsSchedulingDayConfig/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid ApsSchedulingDayConfigExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/apsSchedulingDayConfig/importData")
  ApsSchedulingDayConfigImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/apsSchedulingDayConfig/queryByIdList")
  ApsSchedulingDayConfigQueryByIdListRes queryByIdListRes(@RequestBody @Valid ApsSchedulingDayConfigQueryByIdListReq req);


}
