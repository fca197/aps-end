package com.olivia.peanut.task.api.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.olivia.peanut.task.model.TaskInstance;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;

import java.util.stream.Collectors;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import com.olivia.peanut.task.api.entity.taskInstance.*;
import com.olivia.peanut.task.service.TaskInstanceService;
import com.olivia.peanut.task.model.*;
import com.baomidou.mybatisplus.core.conditions.query.*;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.web.bind.annotation.*;
import com.olivia.peanut.task.api.TaskInstanceApi;

import static com.olivia.peanut.task.converter.TaskInstanceConverter.*;

import com.olivia.peanut.task.api.impl.listener.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 任务流程实例(TaskInstance)表服务实现类
 *
 * @author makejava
 * @since 2025-03-09 14:13:51
 */
@RestController
public class TaskInstanceApiImpl implements TaskInstanceApi {

  private @Autowired TaskInstanceService taskInstanceService;

  /****
   * insert
   *
   */
  public @Override TaskInstanceInsertRes insert(TaskInstanceInsertReq req) {
    TaskInstance taskInstance = INSTANCE.insertReq(req);
    this.taskInstanceService.save(taskInstance);
    return new TaskInstanceInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override TaskInstanceDeleteByIdListRes deleteByIdList(TaskInstanceDeleteByIdListReq req) {
    taskInstanceService.removeByIds(req.getIdList());
    return new TaskInstanceDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override TaskInstanceQueryListRes queryList(TaskInstanceQueryListReq req) {
    return taskInstanceService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override TaskInstanceUpdateByIdRes updateById(TaskInstanceUpdateByIdReq req) {
    taskInstanceService.updateById(INSTANCE.updateReq(req));
    return new TaskInstanceUpdateByIdRes();

  }

  public @Override DynamicsPage<TaskInstanceExportQueryPageListInfoRes> queryPageList(TaskInstanceExportQueryPageListReq req) {
    return taskInstanceService.queryPageList(req);
  }

  public @Override void queryPageListExport(TaskInstanceExportQueryPageListReq req) {
    DynamicsPage<TaskInstanceExportQueryPageListInfoRes> page = queryPageList(req);
    List<TaskInstanceExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    PoiExcelUtil.export(TaskInstanceExportQueryPageListInfoRes.class, list, "任务流程实例");
  }

  public @Override TaskInstanceImportRes importData(@RequestParam("file") MultipartFile file) {
    List<TaskInstanceImportReq> reqList = PoiExcelUtil.readData(file, new TaskInstanceImportListener(), TaskInstanceImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<TaskInstance> readList = INSTANCE.importReq(reqList);
    boolean bool = taskInstanceService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new TaskInstanceImportRes().setCount(c);
  }

  public @Override TaskInstanceQueryByIdListRes queryByIdListRes(TaskInstanceQueryByIdListReq req) {
    MPJLambdaWrapper<TaskInstance> q = new MPJLambdaWrapper<TaskInstance>(TaskInstance.class)
        .selectAll(TaskInstance.class).in(TaskInstance::getId, req.getIdList());
    List<TaskInstance> list = this.taskInstanceService.list(q);
    List<TaskInstanceDto> dataList = INSTANCE.queryListRes(list);
    this.taskInstanceService.setName(dataList);
    return new TaskInstanceQueryByIdListRes().setDataList(dataList);
  }
}
