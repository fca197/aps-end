package com.olivia.peanut.task.api;

import com.olivia.peanut.task.api.entity.taskDef.*;
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
 * 任务定义(TaskDef)对外API
 *
 * @author makejava
 * @since 2025-02-26 16:17:43
 */
// @FeignClient(value = "",contextId = "taskDef-api",url = "${ portal..center.endpoint:}")
public interface TaskDefApi {

  /**
   * 保存 任务定义
   */
  @PostMapping("/taskDef/insert")
  TaskDefInsertRes insert(@RequestBody @Validated(InsertCheck.class) TaskDefInsertReq req);

  /**
   * 根据ID 删除 任务定义
   */
  @PostMapping("/taskDef/deleteByIdList")
  TaskDefDeleteByIdListRes deleteByIdList(@RequestBody @Valid TaskDefDeleteByIdListReq req);

  /**
   * 查询 任务定义
   */
  @PostMapping("/taskDef/queryList")
  TaskDefQueryListRes queryList(@RequestBody @Valid TaskDefQueryListReq req);

  /**
   * 根据ID 更新 任务定义
   */
  @PostMapping("/taskDef/updateById")
  TaskDefUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) TaskDefUpdateByIdReq req);

  /**
   * 分页查询 任务定义
   */
  @PostMapping("/taskDef/queryPageList")
  DynamicsPage<TaskDefExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid TaskDefExportQueryPageListReq req);

  /**
   * 导出 任务定义
   */
  @PostMapping("/taskDef/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid TaskDefExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/taskDef/importData")
  TaskDefImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/taskDef/queryByIdList")
  TaskDefQueryByIdListRes queryByIdListRes(@RequestBody @Valid TaskDefQueryByIdListReq req);

  @PostMapping("/taskDef/start")
  TaskStartRes taskStart(@RequestBody @Valid TaskStartReq req);

  @PostMapping("/taskDef/listener/list")
  TaskListenerListRes taskListenerList(@RequestBody @Valid TaskListenerListReq req);

  @PostMapping("/taskDef/taskRunnerExec/list")
  GetTaskRunnerExecNameRes getTaskRunnerExecName(@RequestBody @Valid GetTaskRunnerExecNameReq req);

  @PostMapping("/taskDef/javaTaskCheckBeanExec/list")
  GetJavaTaskCheckBeanExecRes getJavaTaskCheckBeanExecName(@RequestBody @Valid GetJavaTaskCheckBeanExecReq req);

  @PostMapping("/taskDef/taskCheckRunnerExec/list")
  GetTaskCheckRunnerExecNameRes getTaskCheckRunnerExecName(@RequestBody @Valid GetTaskCheckRunnerExecNameReq req);

  @PostMapping("/taskDef/javaTaskBeanExec/list")
  GetJavaTaskBeanExecNameRes getJavaTaskBeanExecName(@RequestBody @Valid GetJavaTaskBeanExecNameReq req);

  @PostMapping("/taskDef/aiTaskBeanExec/list")
  GetAITaskBeanExecNameRes getAITaskBeanExecName(@RequestBody @Valid GetAITaskBeanExecNameReq req);

}
