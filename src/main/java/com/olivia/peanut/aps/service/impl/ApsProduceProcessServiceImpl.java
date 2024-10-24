package com.olivia.peanut.aps.service.impl;

import org.springframework.aop.framework.AopContext;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import jakarta.annotation.Resource;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.olivia.peanut.aps.mapper.ApsProduceProcessMapper;
import com.olivia.peanut.aps.model.ApsProduceProcess;
import com.olivia.peanut.aps.service.ApsProduceProcessService;
import cn.hutool.core.collection.CollUtil;
//import com.olivia.peanut.aps.service.BaseTableHeaderService;
import com.olivia.peanut.portal.service.BaseTableHeaderService;
import com.olivia.peanut.aps.api.entity.apsProduceProcess.*;
import com.olivia.peanut.util.SetNamePojoUtils;
import com.olivia.sdk.service.SetNameService;

/**
 * aps 生产路径(ApsProduceProcess)表服务实现类
 *
 * @author makejava
 * @since 2024-10-24 17:00:20
 */
@Service("apsProduceProcessService")
@Transactional
public class ApsProduceProcessServiceImpl extends MPJBaseServiceImpl<ApsProduceProcessMapper, ApsProduceProcess> implements ApsProduceProcessService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  @Resource
  BaseTableHeaderService tableHeaderService;
  @Resource
  SetNameService setNameService;


  public @Override ApsProduceProcessQueryListRes queryList(ApsProduceProcessQueryListReq req) {

    MPJLambdaWrapper<ApsProduceProcess> q = getWrapper(req.getData());
    List<ApsProduceProcess> list = this.list(q);

    List<ApsProduceProcessDto> dataList = list.stream().map(t -> $.copy(t, ApsProduceProcessDto.class)).collect(Collectors.toList());
    ((ApsProduceProcessService) AopContext.currentProxy()).setName(dataList);
    return new ApsProduceProcessQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<ApsProduceProcessExportQueryPageListInfoRes> queryPageList(ApsProduceProcessExportQueryPageListReq req) {

    DynamicsPage<ApsProduceProcess> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsProduceProcess> q = getWrapper(req.getData());
    List<ApsProduceProcessExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsProduceProcess> list = this.page(page, q);
      IPage<ApsProduceProcessExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsProduceProcessExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsProduceProcessExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作 

    List<ApsProduceProcessExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsProduceProcessExportQueryPageListInfoRes.class);
    ((ApsProduceProcessService) AopContext.currentProxy()).setName(listInfoRes);

    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装

  public @Override void setName(List<? extends ApsProduceProcessDto> list) {

    //   setNameService.setName(list, SetNamePojoUtils.FACTORY, SetNamePojoUtils.OP_USER_NAME);

  }


  private MPJLambdaWrapper<ApsProduceProcess> getWrapper(ApsProduceProcessDto obj) {
    MPJLambdaWrapper<ApsProduceProcess> q = new MPJLambdaWrapper<>();


    if (Objects.nonNull(obj)) {
      q
          .eq(StringUtils.isNoneBlank(obj.getProduceProcessNo()), ApsProduceProcess::getProduceProcessNo, obj.getProduceProcessNo())
          .eq(StringUtils.isNoneBlank(obj.getProduceProcessName()), ApsProduceProcess::getProduceProcessName, obj.getProduceProcessName())

      ;
    }
    q.orderByDesc(ApsProduceProcess::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsProduceProcess> page) {

    tableHeaderService.listByBizKey(page, "ApsProduceProcessService#queryPageList");

  }


}

