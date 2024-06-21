package com.olivia.peanut.blog.api.impl;

import com.olivia.peanut.blog.api.entity.blogGroup.*;
import com.olivia.peanut.blog.api.impl.listener.BlogGroupImportListener;

import com.olivia.peanut.blog.model.BlogGroup;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import com.olivia.peanut.blog.service.BlogGroupService;

import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.web.bind.annotation.*;
import com.olivia.peanut.blog.api.BlogGroupApi;

import org.springframework.web.multipart.MultipartFile;

/**
 * 帖子组清单(BlogGroup)表服务实现类
 *
 * @author peanut
 * @since 2024-06-21 13:23:43
 */
@RestController
public class BlogGroupApiImpl implements BlogGroupApi {

  private @Autowired BlogGroupService blogGroupService;

  /****
   * insert
   *
   */
  public @Override BlogGroupInsertRes insert(BlogGroupInsertReq req) {
    this.blogGroupService.save($.copy(req, BlogGroup.class));
    return new BlogGroupInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override BlogGroupDeleteByIdListRes deleteByIdList(BlogGroupDeleteByIdListReq req) {
    blogGroupService.removeByIds(req.getIdList());
    return new BlogGroupDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override BlogGroupQueryListRes queryList(BlogGroupQueryListReq req) {
    return blogGroupService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override BlogGroupUpdateByIdRes updateById(BlogGroupUpdateByIdReq req) {
    blogGroupService.updateById($.copy(req, BlogGroup.class));
    return new BlogGroupUpdateByIdRes();

  }

  public @Override DynamicsPage<BlogGroupExportQueryPageListInfoRes> queryPageList(BlogGroupExportQueryPageListReq req) {
    return blogGroupService.queryPageList(req);
  }

  public @Override void queryPageListExport(BlogGroupExportQueryPageListReq req) {
    DynamicsPage<BlogGroupExportQueryPageListInfoRes> page = queryPageList(req);
    List<BlogGroupExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<BlogGroupExportQueryPageListInfoRes> listInfoRes = $.copyList(list, BlogGroupExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(BlogGroupExportQueryPageListInfoRes.class, listInfoRes, "帖子组清单");
  }

  public @Override BlogGroupImportRes importData(@RequestParam("file") MultipartFile file) {
    List<BlogGroupImportReq> reqList = PoiExcelUtil.readData(file, new BlogGroupImportListener(), BlogGroupImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<BlogGroup> readList = $.copyList(reqList, BlogGroup.class);
    boolean bool = blogGroupService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new BlogGroupImportRes().setCount(c);
  }

  public @Override BlogGroupQueryByIdListRes queryByIdListRes(BlogGroupQueryByIdListReq req) {
    MPJLambdaWrapper<BlogGroup> q = new MPJLambdaWrapper<BlogGroup>(BlogGroup.class)
        .selectAll(BlogGroup.class).in(BlogGroup::getId, req.getIdList());
    List<BlogGroup> list = this.blogGroupService.list(q);
    List<BlogGroupDto> dataList = $.copyList(list, BlogGroupDto.class);
    this.blogGroupService.setName(dataList);
    return new BlogGroupQueryByIdListRes().setDataList(dataList);
  }
}
