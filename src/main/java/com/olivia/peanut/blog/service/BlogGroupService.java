package com.olivia.peanut.blog.service;

import com.olivia.peanut.blog.api.entity.blogGroup.*;
import com.olivia.peanut.blog.model.BlogGroup;
import com.olivia.sdk.utils.DynamicsPage;
import java.util.List;
import com.github.yulichang.base.MPJBaseService;



/**
 * 帖子组清单(BlogGroup)表服务接口
 *
 * @author peanut
 * @since 2024-06-21 13:23:43
 */
public interface BlogGroupService extends MPJBaseService<BlogGroup> {

  BlogGroupQueryListRes queryList(BlogGroupQueryListReq req);

  DynamicsPage<BlogGroupExportQueryPageListInfoRes> queryPageList(BlogGroupExportQueryPageListReq req);


  void setName(List<? extends BlogGroupDto> blogGroupDtoList);
}

