package com.olivia.peanut.aps.api;

import org.springframework.validation.annotation.Validated;
import com.olivia.sdk.utils.DynamicsPage;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.olivia.peanut.aps.api.entity.apsSchedulingDayConfigVersionDetail.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.web.multipart.MultipartFile;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


/**
 * 排程版本配置明细表(ApsSchedulingDayConfigVersionDetail)对外API
 *
 * @author peanut
 * @since 2024-07-19 15:05:08
 */
// @FeignClient(value = "",contextId = "apsSchedulingDayConfigVersionDetail-api",url = "${ portal..center.endpoint:}")
public interface ApsSchedulingDayConfigVersionDetailApi {

  /**
   * 保存 排程版本配置明细表
   */
  @PostMapping("/apsSchedulingDayConfigVersionDetail/insert")
  ApsSchedulingDayConfigVersionDetailInsertRes insert(@RequestBody @Validated(InsertCheck.class) ApsSchedulingDayConfigVersionDetailInsertReq req);

  /**
   * 根据ID 删除 排程版本配置明细表
   */
  @PostMapping("/apsSchedulingDayConfigVersionDetail/deleteByIdList")
  ApsSchedulingDayConfigVersionDetailDeleteByIdListRes deleteByIdList(@RequestBody @Valid ApsSchedulingDayConfigVersionDetailDeleteByIdListReq req);

  /**
   * 查询 排程版本配置明细表
   */
  @PostMapping("/apsSchedulingDayConfigVersionDetail/queryList")
  ApsSchedulingDayConfigVersionDetailQueryListRes queryList(@RequestBody @Valid ApsSchedulingDayConfigVersionDetailQueryListReq req);

  /**
   * 根据ID 更新 排程版本配置明细表
   */
  @PostMapping("/apsSchedulingDayConfigVersionDetail/updateById")
  ApsSchedulingDayConfigVersionDetailUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) ApsSchedulingDayConfigVersionDetailUpdateByIdReq req);

  /**
   * 分页查询 排程版本配置明细表
   */
  @PostMapping("/apsSchedulingDayConfigVersionDetail/queryPageList")
  DynamicsPage<ApsSchedulingDayConfigVersionDetailExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid ApsSchedulingDayConfigVersionDetailExportQueryPageListReq req);

  /**
   * 导出 排程版本配置明细表
   */
  @PostMapping("/apsSchedulingDayConfigVersionDetail/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid ApsSchedulingDayConfigVersionDetailExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/apsSchedulingDayConfigVersionDetail/importData")
  ApsSchedulingDayConfigVersionDetailImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/apsSchedulingDayConfigVersionDetail/queryByIdList")
  ApsSchedulingDayConfigVersionDetailQueryByIdListRes queryByIdListRes(@RequestBody @Valid ApsSchedulingDayConfigVersionDetailQueryByIdListReq req);


}
