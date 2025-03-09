package com.olivia.peanut.task.service.impl;

import org.springframework.aop.framework.AopContext;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import jakarta.annotation.Resource;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.LambdaQueryUtil;
import com.olivia.sdk.utils.DynamicsPage;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.olivia.peanut.task.mapper.TaskInstanceMapper;
import com.olivia.peanut.task.model.TaskInstance;
import com.olivia.peanut.task.converter.TaskInstanceConverter;
import com.olivia.peanut.task.service.TaskInstanceService;
import cn.hutool.core.collection.CollUtil;
//import com.olivia.peanut.task.service.BaseTableHeaderService;
import com.olivia.peanut.portal.service.BaseTableHeaderService;
import com.olivia.peanut.task.api.entity.taskInstance.*;
import com.olivia.peanut.util.SetNamePojoUtils;
import com.olivia.sdk.service.SetNameService;

/**
 * 任务流程实例(TaskInstance)表服务实现类
 *
 * @author makejava
 * @since 2025-03-09 14:13:52
 */
@Service("taskInstanceService")
@Transactional
public class TaskInstanceServiceImpl extends MPJBaseServiceImpl<TaskInstanceMapper, TaskInstance> implements TaskInstanceService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  @Resource
  BaseTableHeaderService tableHeaderService;
  @Resource
  SetNameService setNameService;


  public @Override TaskInstanceQueryListRes queryList(TaskInstanceQueryListReq req) {

    MPJLambdaWrapper<TaskInstance> q = getWrapper(req.getData());
    List<TaskInstance> list = this.list(q);

    List<TaskInstanceDto> dataList = TaskInstanceConverter.INSTANCE.queryListRes(list);
    ((TaskInstanceService) AopContext.currentProxy()).setName(dataList);
    return new TaskInstanceQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<TaskInstanceExportQueryPageListInfoRes> queryPageList(TaskInstanceExportQueryPageListReq req) {

    DynamicsPage<TaskInstance> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<TaskInstance> q = getWrapper(req.getData());
    List<TaskInstanceExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<TaskInstance> list = this.page(page, q);
      IPage<TaskInstanceExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, TaskInstanceExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = TaskInstanceConverter.INSTANCE.queryPageListRes(this.list(q));
    }

    // 类型转换，  更换枚举 等操作 

    ((TaskInstanceService) AopContext.currentProxy()).setName(records);
    return DynamicsPage.init(page, records);
  }

  // 以下为私有对象封装

  public @Override void setName(List<? extends TaskInstanceDto> list) {

    //   setNameService.setName(list, SetNamePojoUtils.FACTORY, SetNamePojoUtils.OP_USER_NAME);

  }


  @SuppressWarnings("unchecked")
  private MPJLambdaWrapper<TaskInstance> getWrapper(TaskInstanceDto obj) {
    MPJLambdaWrapper<TaskInstance> q = new MPJLambdaWrapper<>();


    LambdaQueryUtil.lambdaQueryWrapper(q, obj, TaskInstance.class
        // 查询条件
        , TaskInstance::getTaskDefId //
        , TaskInstance::getInstanceContent //
        , TaskInstance::getTaskExecStatus //
        , TaskInstance::getExceptionMsg //
        , TaskInstance::getUseTime //
        , TaskInstance::getExecLoop //
        , TaskInstance::getCheckLoop //
        , TaskInstance::getCheckExecStatus //
        , TaskInstance::getCheckExceptionMsg //
        , TaskInstance::getExecDate //
    );


    q.orderByDesc(TaskInstance::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<TaskInstance> page) {

    tableHeaderService.listByBizKey(page, "TaskInstanceService#queryPageList");

  }


}

