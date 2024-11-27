package com.olivia.peanut.base.api;

import com.olivia.peanut.base.api.entity.baseUserResource.*;
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
 * 用户角色资源表(BaseUserResource)对外API
 *
 * @author peanut
 * @since 2024-08-09 16:26:39
 */
// @FeignClient(value = "",contextId = "baseUserResource-api",url = "${ portal..center.endpoint:}")
public interface BaseUserResourceApi {

  /**
   * 保存 用户角色资源表
   */
  @PostMapping("/baseUserResource/insertList")
  BaseUserResourceInsertRes insert(@RequestBody @Validated(InsertCheck.class) BaseUserResourceInsertReq req);

  /**
   * 根据ID 删除 用户角色资源表
   */
  @PostMapping("/baseUserResource/deleteByIdList")
  BaseUserResourceDeleteByIdListRes deleteByIdList(@RequestBody @Valid BaseUserResourceDeleteByIdListReq req);

  /**
   * 查询 用户角色资源表
   */
  @PostMapping("/baseUserResource/queryList")
  BaseUserResourceQueryListRes queryList(@RequestBody @Valid BaseUserResourceQueryListReq req);

  /**
   * 根据ID 更新 用户角色资源表
   */
  @PostMapping("/baseUserResource/updateById")
  BaseUserResourceUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) BaseUserResourceUpdateByIdReq req);

  /**
   * 分页查询 用户角色资源表
   */
  @PostMapping("/baseUserResource/queryPageList")
  DynamicsPage<BaseUserResourceExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid BaseUserResourceExportQueryPageListReq req);

  /**
   * 导出 用户角色资源表
   */
  @PostMapping("/baseUserResource/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid BaseUserResourceExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/baseUserResource/importData")
  BaseUserResourceImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/baseUserResource/queryByIdList")
  BaseUserResourceQueryByIdListRes queryByIdListRes(@RequestBody @Valid BaseUserResourceQueryByIdListReq req);


}
