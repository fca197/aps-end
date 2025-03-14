package com.olivia.peanut.base.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.base.api.entity.baseRole.*;
import com.olivia.peanut.base.mapper.BaseRoleMapper;
import com.olivia.peanut.base.model.BaseRole;
import com.olivia.peanut.base.service.BaseRoleGroupService;
import com.olivia.peanut.base.service.BaseRoleService;
import com.olivia.peanut.base.service.BaseTableHeaderService;
import com.olivia.peanut.util.SetNamePojoUtils;
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
 * 角色表(BaseRole)表服务实现类
 *
 * @author peanut
 * @since 2024-07-31 14:33:35
 */
@Service("baseRoleService")
@Transactional
public class BaseRoleServiceImpl extends MPJBaseServiceImpl<BaseRoleMapper, BaseRole> implements BaseRoleService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  @Resource
  BaseTableHeaderService tableHeaderService;
  @Resource
  SetNameService setNameService;


  public @Override BaseRoleQueryListRes queryList(BaseRoleQueryListReq req) {

    MPJLambdaWrapper<BaseRole> q = getWrapper(req.getData());
    List<BaseRole> list = this.list(q);

    List<BaseRoleDto> dataList = list.stream().map(t -> $.copy(t, BaseRoleDto.class)).collect(Collectors.toList());
    ((BaseRoleService) AopContext.currentProxy()).setName(dataList);
    return new BaseRoleQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<BaseRoleExportQueryPageListInfoRes> queryPageList(BaseRoleExportQueryPageListReq req) {

    DynamicsPage<BaseRole> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<BaseRole> q = getWrapper(req.getData());
    List<BaseRoleExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<BaseRole> list = this.page(page, q);
      IPage<BaseRoleExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, BaseRoleExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), BaseRoleExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<BaseRoleExportQueryPageListInfoRes> listInfoRes = $.copyList(records, BaseRoleExportQueryPageListInfoRes.class);
    ((BaseRoleService) AopContext.currentProxy()).setName(listInfoRes);

    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装

  public @Override void setName(List<? extends BaseRoleDto> list) {

    setNameService.setName(list, SetNamePojoUtils.getSetNamePojo(BaseRoleGroupService.class,
        "roleGroupName", "roleGroupId", "roleGroupName"));


  }


  private MPJLambdaWrapper<BaseRole> getWrapper(BaseRoleDto obj) {
    MPJLambdaWrapper<BaseRole> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(StringUtils.isNoneBlank(obj.getRoleCode()), BaseRole::getRoleCode, obj.getRoleCode())
          .eq(StringUtils.isNoneBlank(obj.getRoleName()), BaseRole::getRoleName, obj.getRoleName())
          .eq(Objects.nonNull(obj.getRoleGroupId()), BaseRole::getRoleGroupId, obj.getRoleGroupId())

      ;
    }
    q.orderByDesc(BaseRole::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<BaseRole> page) {

    tableHeaderService.listByBizKey(page, "BaseRoleService#queryPageList");

  }


}

