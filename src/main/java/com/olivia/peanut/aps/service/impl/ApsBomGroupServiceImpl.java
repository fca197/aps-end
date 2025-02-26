package com.olivia.peanut.aps.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.api.entity.apsBomGroup.*;
import com.olivia.peanut.aps.mapper.ApsBomGroupMapper;
import com.olivia.peanut.aps.model.ApsBomGroup;
import com.olivia.peanut.aps.service.ApsBomGroupService;
import com.olivia.sdk.ann.SetUserName;
import com.olivia.sdk.comment.ServiceComment;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.LambdaQueryUtil;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.olivia.sdk.utils.Str.UN_CHECKED;

/**
 * 零件组配置(ApsBomGroup)表服务实现类
 *
 * @author peanut
 * @since 2024-06-19 17:41:23
 */
@Service("apsBomGroupService")
@Transactional
public class ApsBomGroupServiceImpl extends MPJBaseServiceImpl<ApsBomGroupMapper, ApsBomGroup> implements ApsBomGroupService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();


  public @Override ApsBomGroupQueryListRes queryList(ApsBomGroupQueryListReq req) {

    MPJLambdaWrapper<ApsBomGroup> q = getWrapper(req.getData());
    List<ApsBomGroup> list = this.list(q);

    List<ApsBomGroupDto> dataList = list.stream().map(t -> $.copy(t, ApsBomGroupDto.class)).collect(Collectors.toList());
//   //  this.setName(dataList);
    ((ApsBomGroupService) AopContext.currentProxy()).setName(dataList);
    return new ApsBomGroupQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<ApsBomGroupExportQueryPageListInfoRes> queryPageList(ApsBomGroupExportQueryPageListReq req) {

    DynamicsPage<ApsBomGroup> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsBomGroup> q = getWrapper(req.getData());
    List<ApsBomGroupExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsBomGroup> list = this.page(page, q);
      IPage<ApsBomGroupExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsBomGroupExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsBomGroupExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<ApsBomGroupExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsBomGroupExportQueryPageListInfoRes.class);
//   // this.setName(listInfoRes);
    ((ApsBomGroupService) AopContext.currentProxy()).setName(listInfoRes);

    return DynamicsPage.init(page, listInfoRes);
  }

  @Override
  public ApsBomGroupUpdateByIdRes updateById(ApsBomGroupUpdateByIdReq req) {
    ApsBomGroup apsBomGroup = this.getById(req.getId());
    $.requireNonNullCanIgnoreException(apsBomGroup, "数据不存在");
    Long parentId = apsBomGroup.getParentId();
    ApsBomGroup bomGroup = $.copy(req, ApsBomGroup.class);
    if (Objects.equals(parentId, req.getParentId())) {
      this.updateById(bomGroup);
    } else {
      String oldPathId = apsBomGroup.getPathId();
      ApsBomGroup parentApsBomGroup = this.getById(req.getParentId());
      bomGroup.setPathId(parentApsBomGroup.getPathId() + "/" + req.getId());
      String newPathId = bomGroup.getPathId();
      List<ApsBomGroup> apsBomGroupList = this.list(new LambdaQueryWrapper<ApsBomGroup>().likeRight(ApsBomGroup::getPathId, oldPathId));
      apsBomGroupList.forEach(t -> t.setPathId(t.getPathId().replace(oldPathId, newPathId)));
      apsBomGroupList.add(bomGroup);
      this.updateBatchById(apsBomGroupList);
    }

    return new ApsBomGroupUpdateByIdRes();
  }
  // 以下为私有对象封装

  @SetUserName
  public @Override void setName(List<? extends ApsBomGroupDto> apsBomGroupDtoList) {

    if (CollUtil.isEmpty(apsBomGroupDtoList)) {
    }


  }


  @SuppressWarnings(UN_CHECKED)
  private MPJLambdaWrapper<ApsBomGroup> getWrapper(ApsBomGroupDto obj) {
    MPJLambdaWrapper<ApsBomGroup> q = new MPJLambdaWrapper<>();

    LambdaQueryUtil.lambdaQueryWrapper(q, obj, ApsBomGroup.class, ApsBomGroup::getPathId, ApsBomGroup::getGroupName,//
        ApsBomGroup::getPathId, ApsBomGroup::getGroupCode);

    q.orderByDesc(ApsBomGroup::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsBomGroup> page) {

    ServiceComment.header(page, "ApsBomGroupService#queryPageList");

  }


}

