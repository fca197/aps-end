package com.olivia.peanut.task.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.portal.service.BaseTableHeaderService;
import com.olivia.peanut.task.api.entity.taskDef.*;
import com.olivia.peanut.task.converter.TaskDefConverter;
import com.olivia.peanut.task.mapper.TaskDefMapper;
import com.olivia.peanut.task.model.TaskDef;
import com.olivia.peanut.task.service.TaskDefService;
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
 * 任务定义(TaskDef)表服务实现类
 *
 * @author makejava
 * @since 2025-02-26 16:17:46
 */
@Service("taskDefService")
@Transactional
public class TaskDefServiceImpl extends MPJBaseServiceImpl<TaskDefMapper, TaskDef> implements TaskDefService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  @Resource
  BaseTableHeaderService tableHeaderService;
  @Resource
  SetNameService setNameService;


  public @Override TaskDefQueryListRes queryList(TaskDefQueryListReq req) {

    MPJLambdaWrapper<TaskDef> q = getWrapper(req.getData());
    List<TaskDef> list = this.list(q);

    List<TaskDefDto> dataList = TaskDefConverter.INSTANCE.queryListRes(list);
    ((TaskDefService) AopContext.currentProxy()).setName(dataList);
    return new TaskDefQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<TaskDefExportQueryPageListInfoRes> queryPageList(TaskDefExportQueryPageListReq req) {

    DynamicsPage<TaskDef> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<TaskDef> q = getWrapper(req.getData());
    List<TaskDefExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<TaskDef> list = this.page(page, q);
      IPage<TaskDefExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, TaskDefExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = TaskDefConverter.INSTANCE.queryPageListRes(this.list(q));
    }

    // 类型转换，  更换枚举 等操作 

    ((TaskDefService) AopContext.currentProxy()).setName(records);
    return DynamicsPage.init(page, records);
  }

  // 以下为私有对象封装

  public @Override void setName(List<? extends TaskDefDto> list) {

    //   setNameService.setName(list, SetNamePojoUtils.FACTORY, SetNamePojoUtils.OP_USER_NAME);

  }


  @SuppressWarnings("unchecked")
  private MPJLambdaWrapper<TaskDef> getWrapper(TaskDefDto obj) {
    MPJLambdaWrapper<TaskDef> q = new MPJLambdaWrapper<>();


    LambdaQueryUtil.lambdaQueryWrapper(q, obj, TaskDef.class
        // 查询条件
        , TaskDef::getTaskName //
        , TaskDef::getTasCode //
        , TaskDef::getTaskRemark //
        , TaskDef::getTaskDefContent //
    );


    q.orderByDesc(TaskDef::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<TaskDef> page) {

    tableHeaderService.listByBizKey(page, "TaskDefService#queryPageList");

  }


}

