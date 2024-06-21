package com.olivia.peanut.blog.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.blog.api.entity.blogGroup.*;
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
import com.olivia.peanut.blog.mapper.BlogGroupMapper;
import com.olivia.peanut.blog.model.BlogGroup;
import com.olivia.peanut.blog.service.BlogGroupService;
import cn.hutool.core.collection.CollUtil;
//import com.olivia.blog.service.BaseTableHeaderService;
import com.olivia.peanut.portal.service.BaseTableHeaderService;


/**
 * 帖子组清单(BlogGroup)表服务实现类
 *
 * @author peanut
 * @since 2024-06-21 13:23:43
 */
@Service("blogGroupService")
@Transactional
public class BlogGroupServiceImpl extends MPJBaseServiceImpl<BlogGroupMapper, BlogGroup> implements BlogGroupService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  @Resource
  BaseTableHeaderService tableHeaderService;


  public @Override BlogGroupQueryListRes queryList(BlogGroupQueryListReq req) {

    MPJLambdaWrapper<BlogGroup> q = getWrapper(req.getData());
    List<BlogGroup> list = this.list(q);

    List<BlogGroupDto> dataList = list.stream().map(t -> $.copy(t, BlogGroupDto.class)).collect(Collectors.toList());
    this.setName(dataList);
    return new BlogGroupQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<BlogGroupExportQueryPageListInfoRes> queryPageList(BlogGroupExportQueryPageListReq req) {

    DynamicsPage<BlogGroup> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<BlogGroup> q = getWrapper(req.getData());
    List<BlogGroupExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<BlogGroup> list = this.page(page, q);
      IPage<BlogGroupExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, BlogGroupExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), BlogGroupExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<BlogGroupExportQueryPageListInfoRes> listInfoRes = $.copyList(records, BlogGroupExportQueryPageListInfoRes.class);
    this.setName(listInfoRes);
    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装

  public @Override void setName(List<? extends BlogGroupDto> blogGroupDtoList) {

    if (CollUtil.isEmpty(blogGroupDtoList)) {
    }


  }


  private MPJLambdaWrapper<BlogGroup> getWrapper(BlogGroupDto obj) {
    MPJLambdaWrapper<BlogGroup> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(StringUtils.isNoneBlank(obj.getGroupName()), BlogGroup::getGroupName, obj.getGroupName())
          .eq(StringUtils.isNoneBlank(obj.getGroupCode()), BlogGroup::getGroupCode, obj.getGroupCode())

      ;
    }
    q.orderByDesc(BlogGroup::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<BlogGroup> page) {

    tableHeaderService.listByBizKey(page, "BlogGroupService#queryPageList");

  }


}

