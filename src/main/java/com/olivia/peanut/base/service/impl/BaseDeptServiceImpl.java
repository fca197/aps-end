package com.olivia.peanut.base.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.base.api.entity.baseDept.*;
import com.olivia.peanut.base.mapper.BaseDeptMapper;
import com.olivia.peanut.base.model.BaseDept;
import com.olivia.peanut.base.service.BaseDeptService;
import com.olivia.peanut.base.service.BaseTableHeaderService;
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
 * 部门表(BaseDept)表服务实现类
 *
 * @author peanut
 * @since 2024-07-31 14:33:31
 */
@Service("baseDeptService")
@Transactional
public class BaseDeptServiceImpl extends MPJBaseServiceImpl<BaseDeptMapper, BaseDept> implements BaseDeptService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  @Resource
  BaseTableHeaderService tableHeaderService;
  @Resource
  SetNameService setNameService;


  public @Override BaseDeptQueryListRes queryList(BaseDeptQueryListReq req) {

    MPJLambdaWrapper<BaseDept> q = getWrapper(req.getData());
    List<BaseDept> list = this.list(q);

    List<BaseDeptDto> dataList = list.stream().map(t -> $.copy(t, BaseDeptDto.class)).collect(Collectors.toList());
    ((BaseDeptService) AopContext.currentProxy()).setName(dataList);
    return new BaseDeptQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<BaseDeptExportQueryPageListInfoRes> queryPageList(BaseDeptExportQueryPageListReq req) {

    DynamicsPage<BaseDept> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<BaseDept> q = getWrapper(req.getData());
    List<BaseDeptExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<BaseDept> list = this.page(page, q);
      IPage<BaseDeptExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, BaseDeptExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), BaseDeptExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<BaseDeptExportQueryPageListInfoRes> listInfoRes = $.copyList(records, BaseDeptExportQueryPageListInfoRes.class);
    ((BaseDeptService) AopContext.currentProxy()).setName(listInfoRes);

    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装

  public @Override void setName(List<? extends BaseDeptDto> list) {

    //   setNameService.setName(list, SetNamePojoUtils.FACTORY, SetNamePojoUtils.OP_USER_NAME);

  }


  private MPJLambdaWrapper<BaseDept> getWrapper(BaseDeptDto obj) {
    MPJLambdaWrapper<BaseDept> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(StringUtils.isNoneBlank(obj.getDeptCode()), BaseDept::getDeptCode, obj.getDeptCode())
          .eq(StringUtils.isNoneBlank(obj.getDeptName()), BaseDept::getDeptName, obj.getDeptName())
          .eq(Objects.nonNull(obj.getParentId()), BaseDept::getParentId, obj.getParentId())
          .eq(StringUtils.isNoneBlank(obj.getPath()), BaseDept::getPath, obj.getPath())

      ;
    }
    q.orderByDesc(BaseDept::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<BaseDept> page) {

    tableHeaderService.listByBizKey(page, "BaseDeptService#queryPageList");

  }


}

