package com.olivia.peanut.base.api;

import com.olivia.peanut.base.api.entity.baseRoleResource.*;
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
 * 角色资源表(BaseRoleResource)对外API
 *
 * @author peanut
 * @since 2024-08-09 15:42:36
 */
// @FeignClient(value = "",contextId = "baseRoleResource-api",url = "${ portal..center.endpoint:}")
public interface BaseRoleResourceApi {

  /**
   * 保存 角色资源表
   */
  @PostMapping("/baseRoleResource/insertList")
  BaseRoleResourceInsertRes insert(@RequestBody @Validated(InsertCheck.class) BaseRoleResourceInsertReq req);

  /**
   * 根据ID 删除 角色资源表
   */
  @PostMapping("/baseRoleResource/deleteByIdList")
  BaseRoleResourceDeleteByIdListRes deleteByIdList(@RequestBody @Valid BaseRoleResourceDeleteByIdListReq req);

  /**
   * 查询 角色资源表
   */
  @PostMapping("/baseRoleResource/queryList")
  BaseRoleResourceQueryListRes queryList(@RequestBody @Valid BaseRoleResourceQueryListReq req);

  /**
   * 根据ID 更新 角色资源表
   */
  @PostMapping("/baseRoleResource/updateById")
  BaseRoleResourceUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) BaseRoleResourceUpdateByIdReq req);

  /**
   * 分页查询 角色资源表
   */
  @PostMapping("/baseRoleResource/queryPageList")
  DynamicsPage<BaseRoleResourceExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid BaseRoleResourceExportQueryPageListReq req);

  /**
   * 导出 角色资源表
   */
  @PostMapping("/baseRoleResource/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid BaseRoleResourceExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/baseRoleResource/importData")
  BaseRoleResourceImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/baseRoleResource/queryByIdList")
  BaseRoleResourceQueryByIdListRes queryByIdListRes(@RequestBody @Valid BaseRoleResourceQueryByIdListReq req);


}
