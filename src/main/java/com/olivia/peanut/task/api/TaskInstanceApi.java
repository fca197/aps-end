package com.olivia.peanut.task.api;

import org.springframework.validation.annotation.Validated;
import com.olivia.sdk.utils.DynamicsPage;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.olivia.peanut.task.api.entity.taskInstance.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


/**
 * 任务流程实例(TaskInstance)对外API
 *
 * @author makejava
 * @since 2025-03-09 14:13:51
 */
// @FeignClient(value = "",contextId = "taskInstance-api",url = "${ portal..center.endpoint:}")
public interface TaskInstanceApi {

  /**
   * 保存 任务流程实例
   */
  @PostMapping("/taskInstance/insert")
  TaskInstanceInsertRes insert(@RequestBody @Validated(InsertCheck.class) TaskInstanceInsertReq req);

  /**
   * 根据ID 删除 任务流程实例
   */
  @PostMapping("/taskInstance/deleteByIdList")
  TaskInstanceDeleteByIdListRes deleteByIdList(@RequestBody @Valid TaskInstanceDeleteByIdListReq req);

  /**
   * 查询 任务流程实例
   */
  @PostMapping("/taskInstance/queryList")
  TaskInstanceQueryListRes queryList(@RequestBody @Valid TaskInstanceQueryListReq req);

  /**
   * 根据ID 更新 任务流程实例
   */
  @PostMapping("/taskInstance/updateById")
  TaskInstanceUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) TaskInstanceUpdateByIdReq req);

  /**
   * 分页查询 任务流程实例
   */
  @PostMapping("/taskInstance/queryPageList")
  DynamicsPage<TaskInstanceExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid TaskInstanceExportQueryPageListReq req);

  /**
   * 导出 任务流程实例
   */
  @PostMapping("/taskInstance/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid TaskInstanceExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/taskInstance/importData")
  TaskInstanceImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/taskInstance/queryByIdList")
  TaskInstanceQueryByIdListRes queryByIdListRes(@RequestBody @Valid TaskInstanceQueryByIdListReq req);


}
