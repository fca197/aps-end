package com.olivia.peanut.flow.api;

import org.springframework.validation.annotation.Validated;
import com.olivia.sdk.utils.DynamicsPage;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.olivia.peanut.flow.api.entity.flowForm.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.web.multipart.MultipartFile;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


/**
 * 工作流表单表(FlowForm)对外API
 *
 * @author peanut
 * @since 2024-08-02 23:26:21
 */
// @FeignClient(value = "",contextId = "flowForm-api",url = "${ portal..center.endpoint:}")
public interface FlowFormApi {

  /**
   * 保存 工作流表单表
   */
  @PostMapping("/flowForm/insert")
  FlowFormInsertRes insert(@RequestBody @Validated(InsertCheck.class) FlowFormInsertReq req);

  /**
   * 根据ID 删除 工作流表单表
   */
  @PostMapping("/flowForm/deleteByIdList")
  FlowFormDeleteByIdListRes deleteByIdList(@RequestBody @Valid FlowFormDeleteByIdListReq req);

  /**
   * 查询 工作流表单表
   */
  @PostMapping("/flowForm/queryList")
  FlowFormQueryListRes queryList(@RequestBody @Valid FlowFormQueryListReq req);

  /**
   * 根据ID 更新 工作流表单表
   */
  @PostMapping("/flowForm/updateById")
  FlowFormUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) FlowFormUpdateByIdReq req);

  /**
   * 分页查询 工作流表单表
   */
  @PostMapping("/flowForm/queryPageList")
  DynamicsPage<FlowFormExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid FlowFormExportQueryPageListReq req);

  /**
   * 导出 工作流表单表
   */
  @PostMapping("/flowForm/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid FlowFormExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/flowForm/importData")
  FlowFormImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/flowForm/queryByIdList")
  FlowFormQueryByIdListRes queryByIdListRes(@RequestBody @Valid FlowFormQueryByIdListReq req);


}
