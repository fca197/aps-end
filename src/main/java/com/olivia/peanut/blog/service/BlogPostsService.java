package com.olivia.peanut.blog.service;

import com.olivia.peanut.blog.api.entity.blogPosts.*;
import com.olivia.peanut.blog.model.BlogPosts;
import com.olivia.sdk.utils.DynamicsPage;
import java.util.List;
import com.github.yulichang.base.MPJBaseService;



/**
 * 帖子清单(BlogPosts)表服务接口
 *
 * @author peanut
 * @since 2024-06-21 13:23:44
 */
public interface BlogPostsService extends MPJBaseService<BlogPosts> {

  BlogPostsQueryListRes queryList(BlogPostsQueryListReq req);

  DynamicsPage<BlogPostsExportQueryPageListInfoRes> queryPageList(BlogPostsExportQueryPageListReq req);


  void setName(List<? extends BlogPostsDto> blogPostsDtoList);
}

