package com.olivia.peanut.flow.api;

import org.springframework.validation.annotation.Validated;
import com.olivia.sdk.utils.DynamicsPage;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.olivia.peanut.flow.api.entity.flowGroup.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


/**
 * 工作流组表(FlowGroup)对外API
 *
 * @author peanut
 * @since 2024-08-01 10:43:52
 */
// @FeignClient(value = "",contextId = "flowGroup-api",url = "${ portal..center.endpoint:}")
public interface FlowGroupApi {

  /**
   * 保存 工作流组表
   */
  @PostMapping("/flowGroup/insert")
  FlowGroupInsertRes insert(@RequestBody @Validated(InsertCheck.class) FlowGroupInsertReq req);

  /**
   * 根据ID 删除 工作流组表
   */
  @PostMapping("/flowGroup/deleteByIdList")
  FlowGroupDeleteByIdListRes deleteByIdList(@RequestBody @Valid FlowGroupDeleteByIdListReq req);

  /**
   * 查询 工作流组表
   */
  @PostMapping("/flowGroup/queryList")
  FlowGroupQueryListRes queryList(@RequestBody @Valid FlowGroupQueryListReq req);

  /**
   * 根据ID 更新 工作流组表
   */
  @PostMapping("/flowGroup/updateById")
  FlowGroupUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) FlowGroupUpdateByIdReq req);

  /**
   * 分页查询 工作流组表
   */
  @PostMapping("/flowGroup/queryPageList")
  DynamicsPage<FlowGroupExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid FlowGroupExportQueryPageListReq req);

  /**
   * 导出 工作流组表
   */
  @PostMapping("/flowGroup/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid FlowGroupExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/flowGroup/importData")
  FlowGroupImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/flowGroup/queryByIdList")
  FlowGroupQueryByIdListRes queryByIdListRes(@RequestBody @Valid FlowGroupQueryByIdListReq req);


}
