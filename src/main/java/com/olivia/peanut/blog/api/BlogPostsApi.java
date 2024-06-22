package com.olivia.peanut.blog.api;

import com.olivia.peanut.blog.api.entity.blogPosts.*;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import com.olivia.sdk.utils.DynamicsPage;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


/**
 * 帖子清单(BlogPosts)对外API
 *
 * @author peanut
 * @since 2024-06-21 13:23:44
 */
// @FeignClient(value = "",contextId = "blogPosts-api",url = "${ portal..center.endpoint:}")
public interface BlogPostsApi {

  /**
   * 保存 帖子清单
   */
  @PostMapping("/blogPosts/insert")
  BlogPostsInsertRes insert(@RequestBody @Validated(InsertCheck.class) BlogPostsInsertReq req);

  /**
   * 根据ID 删除 帖子清单
   */
  @PostMapping("/blogPosts/deleteByIdList")
  BlogPostsDeleteByIdListRes deleteByIdList(@RequestBody @Valid BlogPostsDeleteByIdListReq req);

  /**
   * 查询 帖子清单
   */
  @PostMapping("/blogPosts/queryList")
  BlogPostsQueryListRes queryList(@RequestBody @Valid BlogPostsQueryListReq req);

  /**
   * 根据ID 更新 帖子清单
   */
  @PostMapping("/blogPosts/updateById")
  BlogPostsUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) BlogPostsUpdateByIdReq req);

  /**
   * 分页查询 帖子清单
   */
  @PostMapping("/blogPosts/queryPageList")
  DynamicsPage<BlogPostsExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid BlogPostsExportQueryPageListReq req);

  /**
   * 导出 帖子清单
   */
  @PostMapping("/blogPosts/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid BlogPostsExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/blogPosts/importData")
  BlogPostsImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/blogPosts/queryByIdList")
  BlogPostsQueryByIdListRes queryByIdListRes(@RequestBody @Valid BlogPostsQueryByIdListReq req);


}
