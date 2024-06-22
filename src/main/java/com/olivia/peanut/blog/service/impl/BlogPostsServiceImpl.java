package com.olivia.peanut.blog.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.blog.api.entity.blogPosts.*;
import com.olivia.peanut.blog.mapper.BlogPostsMapper;
import com.olivia.peanut.blog.model.BlogGroup;
import com.olivia.peanut.blog.model.BlogPosts;
import com.olivia.peanut.blog.service.BlogGroupService;
import com.olivia.peanut.blog.service.BlogPostsService;
import com.olivia.sdk.comment.ServiceComment;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.BaseEntity;
import com.olivia.sdk.utils.DynamicsPage;
import jakarta.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 帖子清单(BlogPosts)表服务实现类
 *
 * @author peanut
 * @since 2024-06-21 13:23:44
 */
@Service("blogPostsService")
@Transactional
public class BlogPostsServiceImpl extends MPJBaseServiceImpl<BlogPostsMapper, BlogPosts> implements BlogPostsService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  @Resource
  BlogGroupService blogGroupService;

  public @Override BlogPostsQueryListRes queryList(BlogPostsQueryListReq req) {

    MPJLambdaWrapper<BlogPosts> q = getWrapper(req.getData());
    List<BlogPosts> list = this.list(q);

    List<BlogPostsDto> dataList = list.stream().map(t -> $.copy(t, BlogPostsDto.class)).collect(Collectors.toList());
    this.setName(dataList);
    return new BlogPostsQueryListRes().setDataList(dataList);
  }

  // 以下为私有对象封装

  public @Override DynamicsPage<BlogPostsExportQueryPageListInfoRes> queryPageList(BlogPostsExportQueryPageListReq req) {

    DynamicsPage<BlogPosts> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<BlogPosts> q = getWrapper(req.getData());
    List<BlogPostsExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<BlogPosts> list = this.page(page, q);
      IPage<BlogPostsExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, BlogPostsExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), BlogPostsExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<BlogPostsExportQueryPageListInfoRes> listInfoRes = $.copyList(records, BlogPostsExportQueryPageListInfoRes.class);
    this.setName(listInfoRes);
    return DynamicsPage.init(page, listInfoRes);
  }

  public @Override void setName(List<? extends BlogPostsDto> blogPostsDtoList) {

    if (CollUtil.isEmpty(blogPostsDtoList)) {
      return;
    }
    Set<Long> idSet = blogPostsDtoList.stream().map(BlogPostsDto::getPostsGroupId).collect(Collectors.toSet());
    Map<Long, String> gMap = this.blogGroupService.listByIds(idSet).stream().collect(Collectors.toMap(BaseEntity::getId, BlogGroup::getGroupName));
    blogPostsDtoList.forEach(t -> t.setPostsGroupName(gMap.get(t.getPostsGroupId())));
  }


  private MPJLambdaWrapper<BlogPosts> getWrapper(BlogPostsDto obj) {
    MPJLambdaWrapper<BlogPosts> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(Objects.nonNull(obj.getPostsGroupId()), BlogPosts::getPostsGroupId, obj.getPostsGroupId())
          .eq(StringUtils.isNoneBlank(obj.getPostsName()), BlogPosts::getPostsName, obj.getPostsName())
          .eq(StringUtils.isNoneBlank(obj.getPostsTags()), BlogPosts::getPostsTags, obj.getPostsTags())
          .eq(StringUtils.isNoneBlank(obj.getPostsCode()), BlogPosts::getPostsCode, obj.getPostsCode())
          .eq(StringUtils.isNoneBlank(obj.getPostsContent()), BlogPosts::getPostsContent, obj.getPostsContent())
          .eq(Objects.nonNull(obj.getReadNumber()), BlogPosts::getReadNumber, obj.getReadNumber())
          .eq(Objects.nonNull(obj.getIsTop()), BlogPosts::getIsTop, obj.getIsTop())

      ;
    }
    q.orderByDesc(BlogPosts::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<BlogPosts> page) {

    ServiceComment.header(page, "BlogPostsService#queryPageList");

  }


}

