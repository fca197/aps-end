package com.olivia.peanut.aps.api;

import org.springframework.validation.annotation.Validated;
import com.olivia.sdk.utils.DynamicsPage;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.olivia.peanut.aps.api.entity.apsBomGroup.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.web.multipart.MultipartFile;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


/**
 * 零件组配置(ApsBomGroup)对外API
 *
 * @author peanut
 * @since 2024-06-19 17:41:23
 */
// @FeignClient(value = "",contextId = "apsBomGroup-api",url = "${ portal..center.endpoint:}")
public interface ApsBomGroupApi {

  /**
   * 保存 零件组配置
   */
  @PostMapping("/apsBomGroup/insert")
  ApsBomGroupInsertRes insert(@RequestBody @Validated(InsertCheck.class) ApsBomGroupInsertReq req);

  /**
   * 根据ID 删除 零件组配置
   */
  @PostMapping("/apsBomGroup/deleteByIdList")
  ApsBomGroupDeleteByIdListRes deleteByIdList(@RequestBody @Valid ApsBomGroupDeleteByIdListReq req);

  /**
   * 查询 零件组配置
   */
  @PostMapping("/apsBomGroup/queryList")
  ApsBomGroupQueryListRes queryList(@RequestBody @Valid ApsBomGroupQueryListReq req);

  /**
   * 根据ID 更新 零件组配置
   */
  @PostMapping("/apsBomGroup/updateById")
  ApsBomGroupUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) ApsBomGroupUpdateByIdReq req);

  /**
   * 分页查询 零件组配置
   */
  @PostMapping("/apsBomGroup/queryPageList")
  DynamicsPage<ApsBomGroupExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid ApsBomGroupExportQueryPageListReq req);

  /**
   * 导出 零件组配置
   */
  @PostMapping("/apsBomGroup/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid ApsBomGroupExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/apsBomGroup/importData")
  ApsBomGroupImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/apsBomGroup/queryByIdList")
  ApsBomGroupQueryByIdListRes queryByIdListRes(@RequestBody @Valid ApsBomGroupQueryByIdListReq req);


}
