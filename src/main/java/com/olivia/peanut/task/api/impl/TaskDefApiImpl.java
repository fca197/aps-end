package com.olivia.peanut.task.api.impl;

import cn.hutool.extra.spring.SpringUtil;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.task.api.TaskDefApi;
import com.olivia.peanut.task.api.entity.taskDef.*;
import com.olivia.peanut.task.api.impl.listener.TaskDefImportListener;
import com.olivia.peanut.task.engine.BaseTaskEngine;
import com.olivia.peanut.task.engine.exec.JavaTaskBeanExec;
import com.olivia.peanut.task.engine.exec.JavaTaskCheckBeanExec;
import com.olivia.peanut.task.engine.exec.TaskCheckRunnerExec;
import com.olivia.peanut.task.engine.exec.TaskRunnerExec;
import com.olivia.peanut.task.engine.listener.TaskListener;
import com.olivia.peanut.task.model.TaskDef;
import com.olivia.peanut.task.service.TaskDefService;
import com.olivia.sdk.model.KVEntity;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.olivia.peanut.task.converter.TaskDefConverter.INSTANCE;

/**
 * 任务定义(TaskDef)表服务实现类
 *
 * @author makejava
 * @since 2025-02-26 16:17:43
 */
@RestController
public class TaskDefApiImpl implements TaskDefApi {

  private @Autowired TaskDefService taskDefService;

  /****
   * insert
   *
   */
  public @Override TaskDefInsertRes insert(TaskDefInsertReq req) {
    TaskDef taskDef = INSTANCE.insertReq(req);
    this.taskDefService.save(taskDef);
    return new TaskDefInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override TaskDefDeleteByIdListRes deleteByIdList(TaskDefDeleteByIdListReq req) {
    taskDefService.removeByIds(req.getIdList());
    return new TaskDefDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override TaskDefQueryListRes queryList(TaskDefQueryListReq req) {
    return taskDefService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override TaskDefUpdateByIdRes updateById(TaskDefUpdateByIdReq req) {
    taskDefService.updateById(INSTANCE.updateReq(req));
    return new TaskDefUpdateByIdRes();

  }

  public @Override DynamicsPage<TaskDefExportQueryPageListInfoRes> queryPageList(TaskDefExportQueryPageListReq req) {
    return taskDefService.queryPageList(req);
  }

  public @Override void queryPageListExport(TaskDefExportQueryPageListReq req) {
    DynamicsPage<TaskDefExportQueryPageListInfoRes> page = queryPageList(req);
    List<TaskDefExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    PoiExcelUtil.export(TaskDefExportQueryPageListInfoRes.class, list, "任务定义");
  }

  public @Override TaskDefImportRes importData(@RequestParam("file") MultipartFile file) {
    List<TaskDefImportReq> reqList = PoiExcelUtil.readData(file, new TaskDefImportListener(), TaskDefImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<TaskDef> readList = INSTANCE.importReq(reqList);
    boolean bool = taskDefService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new TaskDefImportRes().setCount(c);
  }

  public @Override TaskDefQueryByIdListRes queryByIdListRes(TaskDefQueryByIdListReq req) {
    MPJLambdaWrapper<TaskDef> q = new MPJLambdaWrapper<TaskDef>(TaskDef.class).selectAll(TaskDef.class).in(TaskDef::getId, req.getIdList());
    List<TaskDef> list = this.taskDefService.list(q);
    List<TaskDefDto> dataList = INSTANCE.queryListRes(list);
    this.taskDefService.setName(dataList);
    return new TaskDefQueryByIdListRes().setDataList(dataList);
  }

  @Override
  public TaskStartRes taskStart(TaskStartReq req) {
    Long taskId = BaseTaskEngine.getInstance().startTaskByTaskDefId(req);
    return new TaskStartRes().setTaskId(taskId);
  }

  @Override
  public TaskListenerListRes taskListenerList(TaskListenerListReq req) {

    List<KVEntity> retList = getTaskNameList(TaskListener.class, TaskListener::getTaskListenerName);
    return new TaskListenerListRes().setList(retList);
  }

  @Override
  public GetTaskRunnerExecNameRes getTaskRunnerExecName(GetTaskRunnerExecNameReq req) {

    List<KVEntity> retList = getTaskNameList(TaskRunnerExec.class, TaskRunnerExec::getTaskRunnerExecName);

    return new GetTaskRunnerExecNameRes().setList(retList);
  }

  @Override
  public GetJavaTaskCheckBeanExecRes getJavaTaskCheckBeanExecName(GetJavaTaskCheckBeanExecReq req) {
    List<KVEntity> retList = getTaskNameList(JavaTaskCheckBeanExec.class, JavaTaskCheckBeanExec::getJavaTaskCheckBeanExecName);
    return new GetJavaTaskCheckBeanExecRes().setList(retList);
  }

  @Override
  public GetTaskCheckRunnerExecNameRes getTaskCheckRunnerExecName(GetTaskCheckRunnerExecNameReq req) {
    List<KVEntity> retList = getTaskNameList(TaskCheckRunnerExec.class, TaskCheckRunnerExec::getTaskCheckRunnerName);
    return new GetTaskCheckRunnerExecNameRes().setList(retList);
  }

  @Override
  public GetJavaTaskBeanExecNameRes getJavaTaskBeanExecName(GetJavaTaskBeanExecNameReq req) {
    List<KVEntity> retList = getTaskNameList(JavaTaskBeanExec.class, JavaTaskBeanExec::getJavaTaskBeanExecName);
    return new GetJavaTaskBeanExecNameRes().setList(retList);
  }

  private static @NotNull <T> List<KVEntity> getTaskNameList(Class<T> clazz, Function<? super T, KVEntity> mapper) {
    Map<String, T> beansOfTypeMap = SpringUtil.getBeansOfType(clazz);
    List<KVEntity> list = beansOfTypeMap.values().stream().map(mapper).collect(Collectors.toList());
    return KVEntity.mergeEntities(list);
  }
}
