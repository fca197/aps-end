package com.olivia.peanut.base.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.base.api.entity.baseRoleGroup.*;
import com.olivia.peanut.base.mapper.BaseRoleGroupMapper;
import com.olivia.peanut.base.model.BaseRoleGroup;
import com.olivia.peanut.base.service.BaseRoleGroupService;
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
 * 角色组表(BaseRoleGroup)表服务实现类
 *
 * @author peanut
 * @since 2024-07-31 14:33:36
 */
@Service("baseRoleGroupService")
@Transactional
public class BaseRoleGroupServiceImpl extends MPJBaseServiceImpl<BaseRoleGroupMapper, BaseRoleGroup> implements BaseRoleGroupService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  @Resource
  BaseTableHeaderService tableHeaderService;
  @Resource
  SetNameService setNameService;


  public @Override BaseRoleGroupQueryListRes queryList(BaseRoleGroupQueryListReq req) {

    MPJLambdaWrapper<BaseRoleGroup> q = getWrapper(req.getData());
    List<BaseRoleGroup> list = this.list(q);

    List<BaseRoleGroupDto> dataList = list.stream().map(t -> $.copy(t, BaseRoleGroupDto.class)).collect(Collectors.toList());
    ((BaseRoleGroupService) AopContext.currentProxy()).setName(dataList);
    return new BaseRoleGroupQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<BaseRoleGroupExportQueryPageListInfoRes> queryPageList(BaseRoleGroupExportQueryPageListReq req) {

    DynamicsPage<BaseRoleGroup> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<BaseRoleGroup> q = getWrapper(req.getData());
    List<BaseRoleGroupExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<BaseRoleGroup> list = this.page(page, q);
      IPage<BaseRoleGroupExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, BaseRoleGroupExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), BaseRoleGroupExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<BaseRoleGroupExportQueryPageListInfoRes> listInfoRes = $.copyList(records, BaseRoleGroupExportQueryPageListInfoRes.class);
    ((BaseRoleGroupService) AopContext.currentProxy()).setName(listInfoRes);

    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装

  public @Override void setName(List<? extends BaseRoleGroupDto> list) {

    //   setNameService.setName(list, SetNamePojoUtils.FACTORY, SetNamePojoUtils.OP_USER_NAME);

  }


  private MPJLambdaWrapper<BaseRoleGroup> getWrapper(BaseRoleGroupDto obj) {
    MPJLambdaWrapper<BaseRoleGroup> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(StringUtils.isNoneBlank(obj.getRoleGroupCode()), BaseRoleGroup::getRoleGroupCode, obj.getRoleGroupCode())
          .eq(StringUtils.isNoneBlank(obj.getRoleGroupName()), BaseRoleGroup::getRoleGroupName, obj.getRoleGroupName())

      ;
    }
    q.orderByDesc(BaseRoleGroup::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<BaseRoleGroup> page) {

    tableHeaderService.listByBizKey(page, "BaseRoleGroupService#queryPageList");

  }


}

