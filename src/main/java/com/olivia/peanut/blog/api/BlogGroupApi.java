package com.olivia.peanut.blog.api;

import com.olivia.peanut.blog.api.entity.blogGroup.*;
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
 * 帖子组清单(BlogGroup)对外API
 *
 * @author peanut
 * @since 2024-06-21 13:23:42
 */
// @FeignClient(value = "",contextId = "blogGroup-api",url = "${ portal..center.endpoint:}")
public interface BlogGroupApi {

  /**
   * 保存 帖子组清单
   */
  @PostMapping("/blogGroup/insert")
  BlogGroupInsertRes insert(@RequestBody @Validated(InsertCheck.class) BlogGroupInsertReq req);

  /**
   * 根据ID 删除 帖子组清单
   */
  @PostMapping("/blogGroup/deleteByIdList")
  BlogGroupDeleteByIdListRes deleteByIdList(@RequestBody @Valid BlogGroupDeleteByIdListReq req);

  /**
   * 查询 帖子组清单
   */
  @PostMapping("/blogGroup/queryList")
  BlogGroupQueryListRes queryList(@RequestBody @Valid BlogGroupQueryListReq req);

  /**
   * 根据ID 更新 帖子组清单
   */
  @PostMapping("/blogGroup/updateById")
  BlogGroupUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) BlogGroupUpdateByIdReq req);

  /**
   * 分页查询 帖子组清单
   */
  @PostMapping("/blogGroup/queryPageList")
  DynamicsPage<BlogGroupExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid BlogGroupExportQueryPageListReq req);

  /**
   * 导出 帖子组清单
   */
  @PostMapping("/blogGroup/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid BlogGroupExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/blogGroup/importData")
  BlogGroupImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/blogGroup/queryByIdList")
  BlogGroupQueryByIdListRes queryByIdListRes(@RequestBody @Valid BlogGroupQueryByIdListReq req);


}
