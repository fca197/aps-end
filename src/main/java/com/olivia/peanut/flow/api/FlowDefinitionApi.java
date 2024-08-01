package com.olivia.peanut.flow.api;

import org.springframework.validation.annotation.Validated;
import com.olivia.sdk.utils.DynamicsPage;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.olivia.peanut.flow.api.entity.flowDefinition.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.web.multipart.MultipartFile;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


/**
 * 工作定义表(FlowDefinition)对外API
 *
 * @author peanut
 * @since 2024-08-01 10:43:48
 */
// @FeignClient(value = "",contextId = "flowDefinition-api",url = "${ portal..center.endpoint:}")
public interface FlowDefinitionApi {

  /**
   * 保存 工作定义表
   */
  @PostMapping("/flowDefinition/insert")
  FlowDefinitionInsertRes insert(@RequestBody @Validated(InsertCheck.class) FlowDefinitionInsertReq req);

  /**
   * 根据ID 删除 工作定义表
   */
  @PostMapping("/flowDefinition/deleteByIdList")
  FlowDefinitionDeleteByIdListRes deleteByIdList(@RequestBody @Valid FlowDefinitionDeleteByIdListReq req);

  /**
   * 查询 工作定义表
   */
  @PostMapping("/flowDefinition/queryList")
  FlowDefinitionQueryListRes queryList(@RequestBody @Valid FlowDefinitionQueryListReq req);

  /**
   * 根据ID 更新 工作定义表
   */
  @PostMapping("/flowDefinition/updateById")
  FlowDefinitionUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) FlowDefinitionUpdateByIdReq req);

  /**
   * 分页查询 工作定义表
   */
  @PostMapping("/flowDefinition/queryPageList")
  DynamicsPage<FlowDefinitionExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid FlowDefinitionExportQueryPageListReq req);

  /**
   * 导出 工作定义表
   */
  @PostMapping("/flowDefinition/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid FlowDefinitionExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/flowDefinition/importData")
  FlowDefinitionImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/flowDefinition/queryByIdList")
  FlowDefinitionQueryByIdListRes queryByIdListRes(@RequestBody @Valid FlowDefinitionQueryByIdListReq req);


}
