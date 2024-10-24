package com.olivia.peanut.flow.api;

import org.springframework.validation.annotation.Validated;
import com.olivia.sdk.utils.DynamicsPage;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.olivia.peanut.flow.api.entity.flowFormItem.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


/**
 * 工作流表单项表(FlowFormItem)对外API
 *
 * @author peanut
 * @since 2024-08-02 23:26:25
 */
// @FeignClient(value = "",contextId = "flowFormItem-api",url = "${ portal..center.endpoint:}")
public interface FlowFormItemApi {

  /**
   * 保存 工作流表单项表
   */
  @PostMapping("/flowFormItem/insert")
  FlowFormItemInsertRes insert(@RequestBody @Validated(InsertCheck.class) FlowFormItemInsertReq req);

  /**
   * 根据ID 删除 工作流表单项表
   */
  @PostMapping("/flowFormItem/deleteByIdList")
  FlowFormItemDeleteByIdListRes deleteByIdList(@RequestBody @Valid FlowFormItemDeleteByIdListReq req);

  /**
   * 查询 工作流表单项表
   */
  @PostMapping("/flowFormItem/queryList")
  FlowFormItemQueryListRes queryList(@RequestBody @Valid FlowFormItemQueryListReq req);

  /**
   * 根据ID 更新 工作流表单项表
   */
  @PostMapping("/flowFormItem/updateById")
  FlowFormItemUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) FlowFormItemUpdateByIdReq req);

  /**
   * 分页查询 工作流表单项表
   */
  @PostMapping("/flowFormItem/queryPageList")
  DynamicsPage<FlowFormItemExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid FlowFormItemExportQueryPageListReq req);

  /**
   * 导出 工作流表单项表
   */
  @PostMapping("/flowFormItem/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid FlowFormItemExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/flowFormItem/importData")
  FlowFormItemImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/flowFormItem/queryByIdList")
  FlowFormItemQueryByIdListRes queryByIdListRes(@RequestBody @Valid FlowFormItemQueryByIdListReq req);


}
