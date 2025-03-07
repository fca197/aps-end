package com.olivia.peanut.task.api;

import com.olivia.peanut.task.api.entity.taskInstanceHistory.*;
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
 * 任务实例历史(TaskInstanceHistory)对外API
 *
 * @author makejava
 * @since 2025-03-06 13:27:06
 */
// @FeignClient(value = "",contextId = "taskInstanceHistory-api",url = "${ portal..center.endpoint:}")
public interface TaskInstanceHistoryApi {

  /**
   * 保存 任务实例历史
   */
  @PostMapping("/taskInstanceHistory/insert")
  TaskInstanceHistoryInsertRes insert(@RequestBody @Validated(InsertCheck.class) TaskInstanceHistoryInsertReq req);

  /**
   * 根据ID 删除 任务实例历史
   */
  @PostMapping("/taskInstanceHistory/deleteByIdList")
  TaskInstanceHistoryDeleteByIdListRes deleteByIdList(@RequestBody @Valid TaskInstanceHistoryDeleteByIdListReq req);

  /**
   * 查询 任务实例历史
   */
  @PostMapping("/taskInstanceHistory/queryList")
  TaskInstanceHistoryQueryListRes queryList(@RequestBody @Valid TaskInstanceHistoryQueryListReq req);

  /**
   * 根据ID 更新 任务实例历史
   */
  @PostMapping("/taskInstanceHistory/updateById")
  TaskInstanceHistoryUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) TaskInstanceHistoryUpdateByIdReq req);

  /**
   * 分页查询 任务实例历史
   */
  @PostMapping("/taskInstanceHistory/queryPageList")
  DynamicsPage<TaskInstanceHistoryExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid TaskInstanceHistoryExportQueryPageListReq req);

  /**
   * 导出 任务实例历史
   */
  @PostMapping("/taskInstanceHistory/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid TaskInstanceHistoryExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/taskInstanceHistory/importData")
  TaskInstanceHistoryImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/taskInstanceHistory/queryByIdList")
  TaskInstanceHistoryQueryByIdListRes queryByIdListRes(@RequestBody @Valid TaskInstanceHistoryQueryByIdListReq req);


}
