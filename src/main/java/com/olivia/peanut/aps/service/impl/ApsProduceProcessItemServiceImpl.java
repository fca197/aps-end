package com.olivia.peanut.aps.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.api.entity.apsProduceProcessItem.*;
import com.olivia.peanut.aps.mapper.ApsProduceProcessItemMapper;
import com.olivia.peanut.aps.model.ApsProduceProcessItem;
import com.olivia.peanut.aps.service.ApsProduceProcessItemService;
import com.olivia.peanut.base.service.BaseTableHeaderService;
import com.olivia.sdk.service.SetNameService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import jakarta.annotation.Resource;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * aps 生产机器(ApsProduceProcessItem)表服务实现类
 *
 * @author makejava
 * @since 2024-10-24 17:00:22
 */
@Service("apsProduceProcessItemService")
@Transactional
public class ApsProduceProcessItemServiceImpl extends MPJBaseServiceImpl<ApsProduceProcessItemMapper, ApsProduceProcessItem> implements ApsProduceProcessItemService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  @Resource
  BaseTableHeaderService tableHeaderService;
  @Resource
  SetNameService setNameService;


  public @Override ApsProduceProcessItemQueryListRes queryList(ApsProduceProcessItemQueryListReq req) {

    MPJLambdaWrapper<ApsProduceProcessItem> q = getWrapper(req.getData());
    List<ApsProduceProcessItem> list = this.list(q);

    List<ApsProduceProcessItemDto> dataList = list.stream().map(t -> $.copy(t, ApsProduceProcessItemDto.class)).collect(Collectors.toList());
    ((ApsProduceProcessItemService) AopContext.currentProxy()).setName(dataList);
    return new ApsProduceProcessItemQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<ApsProduceProcessItemExportQueryPageListInfoRes> queryPageList(ApsProduceProcessItemExportQueryPageListReq req) {

    DynamicsPage<ApsProduceProcessItem> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsProduceProcessItem> q = getWrapper(req.getData());
    List<ApsProduceProcessItemExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsProduceProcessItem> list = this.page(page, q);
      IPage<ApsProduceProcessItemExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsProduceProcessItemExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsProduceProcessItemExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作 

    List<ApsProduceProcessItemExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsProduceProcessItemExportQueryPageListInfoRes.class);
    ((ApsProduceProcessItemService) AopContext.currentProxy()).setName(listInfoRes);

    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装

  public @Override void setName(List<? extends ApsProduceProcessItemDto> list) {

    //   setNameService.setName(list, SetNamePojoUtils.FACTORY, SetNamePojoUtils.OP_USER_NAME);

  }


  private MPJLambdaWrapper<ApsProduceProcessItem> getWrapper(ApsProduceProcessItemDto obj) {
    MPJLambdaWrapper<ApsProduceProcessItem> q = new MPJLambdaWrapper<>();


    if (Objects.nonNull(obj)) {
      q
          .eq(Objects.nonNull(obj.getProduceProcessId()), ApsProduceProcessItem::getProduceProcessId, obj.getProduceProcessId())
          .eq(Objects.nonNull(obj.getMachineId()), ApsProduceProcessItem::getMachineId, obj.getMachineId())
          .eq(Objects.nonNull(obj.getMachineUseTimeSecond()), ApsProduceProcessItem::getMachineUseTimeSecond, obj.getMachineUseTimeSecond())

      ;
    }
    q.orderByDesc(ApsProduceProcessItem::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsProduceProcessItem> page) {

    tableHeaderService.listByBizKey(page, "ApsProduceProcessItemService#queryPageList");

  }


}

