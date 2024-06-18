package com.olivia.peanut.portal.api;


import com.olivia.peanut.portal.api.entity.processLine.*;
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
 * 流水线信息(ProcessLine)对外API
 *
 * @author makejava
 * @since 2024-03-11 10:55:20
 */
// @FeignClient(value = "",contextId = "processLine-api",url = "${ portal..center.endpoint:}")
public interface ProcessLineApi {

  /**
   * 保存 流水线信息
   */
  @PostMapping("/processLine/insert")
  ProcessLineInsertRes insert(@RequestBody @Validated(InsertCheck.class) ProcessLineInsertReq req);

  /**
   * 根据ID 删除 流水线信息
   */
  @PostMapping("/processLine/deleteByIdList")
  ProcessLineDeleteByIdListRes deleteByIdList(@RequestBody @Valid ProcessLineDeleteByIdListReq req);

  /**
   * 查询 流水线信息
   */
  @PostMapping("/processLine/queryList")
  ProcessLineQueryListRes queryList(@RequestBody @Valid ProcessLineQueryListReq req);

  /**
   * 根据ID 更新 流水线信息
   */
  @PostMapping("/processLine/updateById")
  ProcessLineUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) ProcessLineUpdateByIdReq req);

  /**
   * 分页查询 流水线信息
   */
  @PostMapping("/processLine/queryPageList")
  DynamicsPage<ProcessLineExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid ProcessLineExportQueryPageListReq req);

  /**
   * 导出 流水线信息
   */
  @PostMapping("/processLine/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid ProcessLineExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/processLine/importData")
  ProcessLineImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/processLine/queryByIdList")
  ProcessLineQueryByIdListRes queryByIdListRes(@RequestBody @Valid ProcessLineQueryByIdListReq req);


}
