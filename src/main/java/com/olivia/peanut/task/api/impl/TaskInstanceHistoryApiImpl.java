package com.olivia.peanut.task.api.impl;

import java.time.LocalDateTime;

import com.olivia.peanut.task.model.TaskInstanceHistory;
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
import com.olivia.peanut.task.api.entity.taskInstanceHistory.*;
import com.olivia.peanut.task.service.TaskInstanceHistoryService;
import com.olivia.peanut.task.model.*;
import com.baomidou.mybatisplus.core.conditions.query.*;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.web.bind.annotation.*;
import com.olivia.peanut.task.api.TaskInstanceHistoryApi;

import static com.olivia.peanut.task.converter.TaskInstanceHistoryConverter.*;

import com.olivia.peanut.task.api.impl.listener.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 任务实例历史(TaskInstanceHistory)表服务实现类
 *
 * @author makejava
 * @since 2025-03-06 13:27:06
 */
@RestController
public class TaskInstanceHistoryApiImpl implements TaskInstanceHistoryApi {

  private @Autowired TaskInstanceHistoryService taskInstanceHistoryService;

  /****
   * insert
   *
   */
  public @Override TaskInstanceHistoryInsertRes insert(TaskInstanceHistoryInsertReq req) {
    TaskInstanceHistory taskInstanceHistory = INSTANCE.insertReq(req);
    this.taskInstanceHistoryService.save(taskInstanceHistory);
    return new TaskInstanceHistoryInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override TaskInstanceHistoryDeleteByIdListRes deleteByIdList(TaskInstanceHistoryDeleteByIdListReq req) {
    taskInstanceHistoryService.removeByIds(req.getIdList());
    return new TaskInstanceHistoryDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override TaskInstanceHistoryQueryListRes queryList(TaskInstanceHistoryQueryListReq req) {
    return taskInstanceHistoryService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override TaskInstanceHistoryUpdateByIdRes updateById(TaskInstanceHistoryUpdateByIdReq req) {
    taskInstanceHistoryService.updateById(INSTANCE.updateReq(req));
    return new TaskInstanceHistoryUpdateByIdRes();

  }

  public @Override DynamicsPage<TaskInstanceHistoryExportQueryPageListInfoRes> queryPageList(TaskInstanceHistoryExportQueryPageListReq req) {
    return taskInstanceHistoryService.queryPageList(req);
  }

  public @Override void queryPageListExport(TaskInstanceHistoryExportQueryPageListReq req) {
    DynamicsPage<TaskInstanceHistoryExportQueryPageListInfoRes> page = queryPageList(req);
    List<TaskInstanceHistoryExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    PoiExcelUtil.export(TaskInstanceHistoryExportQueryPageListInfoRes.class, list, "任务实例历史");
  }

  public @Override TaskInstanceHistoryImportRes importData(@RequestParam("file") MultipartFile file) {
    List<TaskInstanceHistoryImportReq> reqList = PoiExcelUtil.readData(file, new TaskInstanceHistoryImportListener(), TaskInstanceHistoryImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<TaskInstanceHistory> readList = INSTANCE.importReq(reqList);
    boolean bool = taskInstanceHistoryService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new TaskInstanceHistoryImportRes().setCount(c);
  }

  public @Override TaskInstanceHistoryQueryByIdListRes queryByIdListRes(TaskInstanceHistoryQueryByIdListReq req) {
    MPJLambdaWrapper<TaskInstanceHistory> q = new MPJLambdaWrapper<TaskInstanceHistory>(TaskInstanceHistory.class)
        .selectAll(TaskInstanceHistory.class).in(TaskInstanceHistory::getId, req.getIdList());
    List<TaskInstanceHistory> list = this.taskInstanceHistoryService.list(q);
    List<TaskInstanceHistoryDto> dataList = INSTANCE.queryListRes(list);
    this.taskInstanceHistoryService.setName(dataList);
    return new TaskInstanceHistoryQueryByIdListRes().setDataList(dataList);
  }
}
