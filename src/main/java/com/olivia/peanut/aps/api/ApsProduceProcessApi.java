package com.olivia.peanut.aps.api;

import org.springframework.validation.annotation.Validated;
import com.olivia.sdk.utils.DynamicsPage;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.olivia.peanut.aps.api.entity.apsProduceProcess.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


/**
 * aps 生产路径(ApsProduceProcess)对外API
 *
 * @author makejava
 * @since 2024-10-24 17:00:18
 */
// @FeignClient(value = "",contextId = "apsProduceProcess-api",url = "${ portal..center.endpoint:}")
public interface ApsProduceProcessApi {

  /**
   * 保存 aps 生产路径
   */
  @PostMapping("/apsProduceProcess/insert")
  ApsProduceProcessInsertRes insert(@RequestBody @Validated(InsertCheck.class) ApsProduceProcessInsertReq req);

  /**
   * 根据ID 删除 aps 生产路径
   */
  @PostMapping("/apsProduceProcess/deleteByIdList")
  ApsProduceProcessDeleteByIdListRes deleteByIdList(@RequestBody @Valid ApsProduceProcessDeleteByIdListReq req);

  /**
   * 查询 aps 生产路径
   */
  @PostMapping("/apsProduceProcess/queryList")
  ApsProduceProcessQueryListRes queryList(@RequestBody @Valid ApsProduceProcessQueryListReq req);

  /**
   * 根据ID 更新 aps 生产路径
   */
  @PostMapping("/apsProduceProcess/updateById")
  ApsProduceProcessUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) ApsProduceProcessUpdateByIdReq req);

  /**
   * 分页查询 aps 生产路径
   */
  @PostMapping("/apsProduceProcess/queryPageList")
  DynamicsPage<ApsProduceProcessExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid ApsProduceProcessExportQueryPageListReq req);

  /**
   * 导出 aps 生产路径
   */
  @PostMapping("/apsProduceProcess/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid ApsProduceProcessExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/apsProduceProcess/importData")
  ApsProduceProcessImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/apsProduceProcess/queryByIdList")
  ApsProduceProcessQueryByIdListRes queryByIdListRes(@RequestBody @Valid ApsProduceProcessQueryByIdListReq req);


}
