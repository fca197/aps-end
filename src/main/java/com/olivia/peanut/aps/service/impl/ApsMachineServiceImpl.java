package com.olivia.peanut.aps.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.api.entity.apsMachine.*;
import com.olivia.peanut.aps.mapper.ApsMachineMapper;
import com.olivia.peanut.aps.model.ApsMachine;
import com.olivia.peanut.aps.service.ApsMachineService;
import com.olivia.peanut.base.service.BaseTableHeaderService;
import com.olivia.peanut.util.SetNamePojoUtils;
import com.olivia.sdk.ann.SetUserName;
import com.olivia.sdk.service.SetNameService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * aps 生产机器(ApsMachine)表服务实现类
 *
 * @author makejava
 * @since 2024-10-24 16:31:16
 */
@Service("apsMachineService")
@Transactional
public class ApsMachineServiceImpl extends MPJBaseServiceImpl<ApsMachineMapper, ApsMachine> implements ApsMachineService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  @Resource
  BaseTableHeaderService tableHeaderService;
  @Resource
  SetNameService setNameService;


  public @Override ApsMachineQueryListRes queryList(ApsMachineQueryListReq req) {

    MPJLambdaWrapper<ApsMachine> q = getWrapper(req.getData());
    List<ApsMachine> list = this.list(q);

    List<ApsMachineDto> dataList = list.stream().map(t -> $.copy(t, ApsMachineDto.class)).collect(Collectors.toList());
    ((ApsMachineService) AopContext.currentProxy()).setName(dataList);
    return new ApsMachineQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<ApsMachineExportQueryPageListInfoRes> queryPageList(ApsMachineExportQueryPageListReq req) {

    DynamicsPage<ApsMachine> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsMachine> q = getWrapper(req.getData());
    List<ApsMachineExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsMachine> list = this.page(page, q);
      IPage<ApsMachineExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsMachineExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsMachineExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作 

    List<ApsMachineExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsMachineExportQueryPageListInfoRes.class);
    ((ApsMachineService) AopContext.currentProxy()).setName(listInfoRes);

    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装

  @SetUserName
  public @Override void setName(List<? extends ApsMachineDto> list) {

    setNameService.setName(list, SetNamePojoUtils.FACTORY);
    //   setNameService.setName(list, SetNamePojoUtils.FACTORY, SetNamePojoUtils.OP_USER_NAME);

  }


  private MPJLambdaWrapper<ApsMachine> getWrapper(ApsMachineDto obj) {
    MPJLambdaWrapper<ApsMachine> q = new MPJLambdaWrapper<>();


    if (Objects.nonNull(obj)) {
      q
          .eq(Objects.nonNull(obj.getFactoryId()), ApsMachine::getFactoryId, obj.getFactoryId())
          .eq(StringUtils.isNoneBlank(obj.getMachineNo()), ApsMachine::getMachineNo, obj.getMachineNo())
          .eq(StringUtils.isNoneBlank(obj.getMachineName()), ApsMachine::getMachineName, obj.getMachineName())

      ;
    }
    q.orderByDesc(ApsMachine::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsMachine> page) {

    tableHeaderService.listByBizKey(page, "ApsMachineService#queryPageList");

  }


}

