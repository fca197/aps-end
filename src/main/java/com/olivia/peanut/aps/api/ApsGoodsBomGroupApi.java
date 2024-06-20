package com.olivia.peanut.aps.api;

import org.springframework.validation.annotation.Validated;
import com.olivia.sdk.utils.DynamicsPage;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.olivia.peanut.aps.api.entity.apsGoodsBomGroup.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.web.multipart.MultipartFile;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


/**
 * 零件组配置(ApsGoodsBomGroup)对外API
 *
 * @author peanut
 * @since 2024-06-19 16:49:03
 */
// @FeignClient(value = "",contextId = "apsGoodsBomGroup-api",url = "${ portal..center.endpoint:}")
public interface ApsGoodsBomGroupApi {

  /**
   * 保存 零件组配置
   */
  @PostMapping("/apsGoodsBomGroup/insert")
  ApsGoodsBomGroupInsertRes insert(@RequestBody @Validated(InsertCheck.class) ApsGoodsBomGroupInsertReq req);

  /**
   * 根据ID 删除 零件组配置
   */
  @PostMapping("/apsGoodsBomGroup/deleteByIdList")
  ApsGoodsBomGroupDeleteByIdListRes deleteByIdList(@RequestBody @Valid ApsGoodsBomGroupDeleteByIdListReq req);

  /**
   * 查询 零件组配置
   */
  @PostMapping("/apsGoodsBomGroup/queryList")
  ApsGoodsBomGroupQueryListRes queryList(@RequestBody @Valid ApsGoodsBomGroupQueryListReq req);

  /**
   * 根据ID 更新 零件组配置
   */
  @PostMapping("/apsGoodsBomGroup/updateById")
  ApsGoodsBomGroupUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) ApsGoodsBomGroupUpdateByIdReq req);

  /**
   * 分页查询 零件组配置
   */
  @PostMapping("/apsGoodsBomGroup/queryPageList")
  DynamicsPage<ApsGoodsBomGroupExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid ApsGoodsBomGroupExportQueryPageListReq req);

  /**
   * 导出 零件组配置
   */
  @PostMapping("/apsGoodsBomGroup/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid ApsGoodsBomGroupExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/apsGoodsBomGroup/importData")
  ApsGoodsBomGroupImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/apsGoodsBomGroup/queryByIdList")
  ApsGoodsBomGroupQueryByIdListRes queryByIdListRes(@RequestBody @Valid ApsGoodsBomGroupQueryByIdListReq req);


}
