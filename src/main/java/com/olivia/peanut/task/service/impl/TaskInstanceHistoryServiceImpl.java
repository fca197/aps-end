package com.olivia.peanut.task.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.portal.service.BaseTableHeaderService;
import com.olivia.peanut.task.api.entity.taskInstanceHistory.*;
import com.olivia.peanut.task.converter.TaskInstanceHistoryConverter;
import com.olivia.peanut.task.mapper.TaskInstanceHistoryMapper;
import com.olivia.peanut.task.model.TaskInstanceHistory;
import com.olivia.peanut.task.service.TaskInstanceHistoryService;
import com.olivia.sdk.service.SetNameService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.LambdaQueryUtil;
import jakarta.annotation.Resource;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 任务实例历史(TaskInstanceHistory)表服务实现类
 *
 * @author makejava
 * @since 2025-03-06 13:27:08
 */
@Service("taskInstanceHistoryService")
@Transactional
public class TaskInstanceHistoryServiceImpl extends MPJBaseServiceImpl<TaskInstanceHistoryMapper, TaskInstanceHistory> implements TaskInstanceHistoryService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  @Resource
  BaseTableHeaderService tableHeaderService;
  @Resource
  SetNameService setNameService;


  public @Override TaskInstanceHistoryQueryListRes queryList(TaskInstanceHistoryQueryListReq req) {

    MPJLambdaWrapper<TaskInstanceHistory> q = getWrapper(req.getData());
    List<TaskInstanceHistory> list = this.list(q);

    List<TaskInstanceHistoryDto> dataList = TaskInstanceHistoryConverter.INSTANCE.queryListRes(list);
    ((TaskInstanceHistoryService) AopContext.currentProxy()).setName(dataList);
    return new TaskInstanceHistoryQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<TaskInstanceHistoryExportQueryPageListInfoRes> queryPageList(TaskInstanceHistoryExportQueryPageListReq req) {

    DynamicsPage<TaskInstanceHistory> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<TaskInstanceHistory> q = getWrapper(req.getData());
    List<TaskInstanceHistoryExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<TaskInstanceHistory> list = this.page(page, q);
      IPage<TaskInstanceHistoryExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, TaskInstanceHistoryExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = TaskInstanceHistoryConverter.INSTANCE.queryPageListRes(this.list(q));
    }

    // 类型转换，  更换枚举 等操作 

    ((TaskInstanceHistoryService) AopContext.currentProxy()).setName(records);
    return DynamicsPage.init(page, records);
  }

  // 以下为私有对象封装

  public @Override void setName(List<? extends TaskInstanceHistoryDto> list) {

    //   setNameService.setName(list, SetNamePojoUtils.FACTORY, SetNamePojoUtils.OP_USER_NAME);

  }


  @SuppressWarnings("unchecked")
  private MPJLambdaWrapper<TaskInstanceHistory> getWrapper(TaskInstanceHistoryDto obj) {
    MPJLambdaWrapper<TaskInstanceHistory> q = new MPJLambdaWrapper<>();


    LambdaQueryUtil.lambdaQueryWrapper(q, obj, TaskInstanceHistory.class
        // 查询条件
        , TaskInstanceHistory::getInstanceId //
        , TaskInstanceHistory::getTaskId //
        , TaskInstanceHistory::getTaskDefId //
        , TaskInstanceHistory::getTaskInput //
        , TaskInstanceHistory::getTaskOutput //
        , TaskInstanceHistory::getTaskExecStatus //
        , TaskInstanceHistory::getExceptionMsg //
        , TaskInstanceHistory::getUseTime //
    );


    q.orderByDesc(TaskInstanceHistory::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<TaskInstanceHistory> page) {

    tableHeaderService.listByBizKey(page, "TaskInstanceHistoryService#queryPageList");

  }


}

