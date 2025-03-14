package com.olivia.peanut.base.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.base.api.entity.baseUserRole.*;
import com.olivia.peanut.base.mapper.BaseUserRoleMapper;
import com.olivia.peanut.base.model.BaseUserRole;
import com.olivia.peanut.base.service.BaseTableHeaderService;
import com.olivia.peanut.base.service.BaseUserRoleService;
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
 * 用户角色表(BaseUserRole)表服务实现类
 *
 * @author peanut
 * @since 2024-07-31 14:36:03
 */
@Service("baseUserRoleService")
@Transactional
public class BaseUserRoleServiceImpl extends MPJBaseServiceImpl<BaseUserRoleMapper, BaseUserRole> implements BaseUserRoleService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  @Resource
  BaseTableHeaderService tableHeaderService;
  @Resource
  SetNameService setNameService;


  public @Override BaseUserRoleQueryListRes queryList(BaseUserRoleQueryListReq req) {

    MPJLambdaWrapper<BaseUserRole> q = getWrapper(req.getData());
    List<BaseUserRole> list = this.list(q);

    List<BaseUserRoleDto> dataList = list.stream().map(t -> $.copy(t, BaseUserRoleDto.class)).collect(Collectors.toList());
    ((BaseUserRoleService) AopContext.currentProxy()).setName(dataList);
    return new BaseUserRoleQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<BaseUserRoleExportQueryPageListInfoRes> queryPageList(BaseUserRoleExportQueryPageListReq req) {

    DynamicsPage<BaseUserRole> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<BaseUserRole> q = getWrapper(req.getData());
    List<BaseUserRoleExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<BaseUserRole> list = this.page(page, q);
      IPage<BaseUserRoleExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, BaseUserRoleExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), BaseUserRoleExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<BaseUserRoleExportQueryPageListInfoRes> listInfoRes = $.copyList(records, BaseUserRoleExportQueryPageListInfoRes.class);
    ((BaseUserRoleService) AopContext.currentProxy()).setName(listInfoRes);

    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装

  public @Override void setName(List<? extends BaseUserRoleDto> list) {

    //   setNameService.setName(list, SetNamePojoUtils.FACTORY, SetNamePojoUtils.OP_USER_NAME);

  }


  private MPJLambdaWrapper<BaseUserRole> getWrapper(BaseUserRoleDto obj) {
    MPJLambdaWrapper<BaseUserRole> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(Objects.nonNull(obj.getUserId()), BaseUserRole::getUserId, obj.getUserId())
          .eq(Objects.nonNull(obj.getRoleId()), BaseUserRole::getRoleId, obj.getRoleId())

      ;
    }
    q.orderByDesc(BaseUserRole::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<BaseUserRole> page) {

    tableHeaderService.listByBizKey(page, "BaseUserRoleService#queryPageList");

  }


}

