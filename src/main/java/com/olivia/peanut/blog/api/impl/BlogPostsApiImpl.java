package com.olivia.peanut.blog.api.impl;

import com.olivia.peanut.blog.api.entity.blogPosts.*;
import com.olivia.peanut.blog.api.impl.listener.BlogPostsImportListener;

import com.olivia.peanut.blog.model.BlogPosts;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import com.olivia.peanut.blog.service.BlogPostsService;

import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.web.bind.annotation.*;
import com.olivia.peanut.blog.api.BlogPostsApi;

import org.springframework.web.multipart.MultipartFile;

/**
 * 帖子清单(BlogPosts)表服务实现类
 *
 * @author peanut
 * @since 2024-06-21 13:23:44
 */
@RestController
public class BlogPostsApiImpl implements BlogPostsApi {

  private @Autowired BlogPostsService blogPostsService;

  /****
   * insert
   *
   */
  public @Override BlogPostsInsertRes insert(BlogPostsInsertReq req) {
    this.blogPostsService.save($.copy(req, BlogPosts.class));
    return new BlogPostsInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override BlogPostsDeleteByIdListRes deleteByIdList(BlogPostsDeleteByIdListReq req) {
    blogPostsService.removeByIds(req.getIdList());
    return new BlogPostsDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override BlogPostsQueryListRes queryList(BlogPostsQueryListReq req) {
    return blogPostsService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override BlogPostsUpdateByIdRes updateById(BlogPostsUpdateByIdReq req) {
    blogPostsService.updateById($.copy(req, BlogPosts.class));
    return new BlogPostsUpdateByIdRes();

  }

  public @Override DynamicsPage<BlogPostsExportQueryPageListInfoRes> queryPageList(BlogPostsExportQueryPageListReq req) {
    return blogPostsService.queryPageList(req);
  }

  public @Override void queryPageListExport(BlogPostsExportQueryPageListReq req) {
    DynamicsPage<BlogPostsExportQueryPageListInfoRes> page = queryPageList(req);
    List<BlogPostsExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<BlogPostsExportQueryPageListInfoRes> listInfoRes = $.copyList(list, BlogPostsExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(BlogPostsExportQueryPageListInfoRes.class, listInfoRes, "帖子清单");
  }

  public @Override BlogPostsImportRes importData(@RequestParam("file") MultipartFile file) {
    List<BlogPostsImportReq> reqList = PoiExcelUtil.readData(file, new BlogPostsImportListener(), BlogPostsImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<BlogPosts> readList = $.copyList(reqList, BlogPosts.class);
    boolean bool = blogPostsService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new BlogPostsImportRes().setCount(c);
  }

  public @Override BlogPostsQueryByIdListRes queryByIdListRes(BlogPostsQueryByIdListReq req) {
    MPJLambdaWrapper<BlogPosts> q = new MPJLambdaWrapper<BlogPosts>(BlogPosts.class)
        .selectAll(BlogPosts.class).in(BlogPosts::getId, req.getIdList());
    List<BlogPosts> list = this.blogPostsService.list(q);
    List<BlogPostsDto> dataList = $.copyList(list, BlogPostsDto.class);
    this.blogPostsService.setName(dataList);
    return new BlogPostsQueryByIdListRes().setDataList(dataList);
  }
}
