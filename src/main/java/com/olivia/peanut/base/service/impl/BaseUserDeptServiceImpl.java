package com.olivia.peanut.base.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.base.api.entity.baseUserDept.*;
import com.olivia.peanut.base.mapper.BaseUserDeptMapper;
import com.olivia.peanut.base.model.BaseUserDept;
import com.olivia.peanut.base.service.BaseTableHeaderService;
import com.olivia.peanut.base.service.BaseUserDeptService;
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
 * 用户部门表(BaseUserDept)表服务实现类
 *
 * @author peanut
 * @since 2024-07-31 14:36:01
 */
@Service("baseUserDeptService")
@Transactional
public class BaseUserDeptServiceImpl extends MPJBaseServiceImpl<BaseUserDeptMapper, BaseUserDept> implements BaseUserDeptService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  @Resource
  BaseTableHeaderService tableHeaderService;
  @Resource
  SetNameService setNameService;


  public @Override BaseUserDeptQueryListRes queryList(BaseUserDeptQueryListReq req) {

    MPJLambdaWrapper<BaseUserDept> q = getWrapper(req.getData());
    List<BaseUserDept> list = this.list(q);

    List<BaseUserDeptDto> dataList = list.stream().map(t -> $.copy(t, BaseUserDeptDto.class)).collect(Collectors.toList());
    ((BaseUserDeptService) AopContext.currentProxy()).setName(dataList);
    return new BaseUserDeptQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<BaseUserDeptExportQueryPageListInfoRes> queryPageList(BaseUserDeptExportQueryPageListReq req) {

    DynamicsPage<BaseUserDept> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<BaseUserDept> q = getWrapper(req.getData());
    List<BaseUserDeptExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<BaseUserDept> list = this.page(page, q);
      IPage<BaseUserDeptExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, BaseUserDeptExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), BaseUserDeptExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<BaseUserDeptExportQueryPageListInfoRes> listInfoRes = $.copyList(records, BaseUserDeptExportQueryPageListInfoRes.class);
    ((BaseUserDeptService) AopContext.currentProxy()).setName(listInfoRes);

    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装

  public @Override void setName(List<? extends BaseUserDeptDto> list) {

    //   setNameService.setName(list, SetNamePojoUtils.FACTORY, SetNamePojoUtils.OP_USER_NAME);

  }


  private MPJLambdaWrapper<BaseUserDept> getWrapper(BaseUserDeptDto obj) {
    MPJLambdaWrapper<BaseUserDept> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(Objects.nonNull(obj.getUserId()), BaseUserDept::getUserId, obj.getUserId())
          .eq(Objects.nonNull(obj.getDeptId()), BaseUserDept::getDeptId, obj.getDeptId())

      ;
    }
    q.orderByDesc(BaseUserDept::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<BaseUserDept> page) {

    tableHeaderService.listByBizKey(page, "BaseUserDeptService#queryPageList");

  }


}

