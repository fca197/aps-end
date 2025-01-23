package com.olivia.peanut.base.api;

import com.olivia.peanut.base.api.entity.baseUserRoleGroup.*;
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
 * 用户角色组表(BaseUserRoleGroup)对外API
 *
 * @author peanut
 * @since 2024-07-31 14:36:03
 */
// @FeignClient(value = "",contextId = "baseUserRoleGroup-api",url = "${ portal..center.endpoint:}")
public interface BaseUserRoleGroupApi {

  /**
   * 保存 用户角色组表
   */
  @PostMapping("/baseUserRoleGroup/insert")
  BaseUserRoleGroupInsertRes insert(@RequestBody @Validated(InsertCheck.class) BaseUserRoleGroupInsertReq req);

  /**
   * 根据ID 删除 用户角色组表
   */
  @PostMapping("/baseUserRoleGroup/deleteByIdList")
  BaseUserRoleGroupDeleteByIdListRes deleteByIdList(@RequestBody @Valid BaseUserRoleGroupDeleteByIdListReq req);

  /**
   * 查询 用户角色组表
   */
  @PostMapping("/baseUserRoleGroup/queryList")
  BaseUserRoleGroupQueryListRes queryList(@RequestBody @Valid BaseUserRoleGroupQueryListReq req);

  /**
   * 根据ID 更新 用户角色组表
   */
  @PostMapping("/baseUserRoleGroup/updateById")
  BaseUserRoleGroupUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) BaseUserRoleGroupUpdateByIdReq req);

  /**
   * 分页查询 用户角色组表
   */
  @PostMapping("/baseUserRoleGroup/queryPageList")
  DynamicsPage<BaseUserRoleGroupExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid BaseUserRoleGroupExportQueryPageListReq req);

  /**
   * 导出 用户角色组表
   */
  @PostMapping("/baseUserRoleGroup/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid BaseUserRoleGroupExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/baseUserRoleGroup/importData")
  BaseUserRoleGroupImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/baseUserRoleGroup/queryByIdList")
  BaseUserRoleGroupQueryByIdListRes queryByIdListRes(@RequestBody @Valid BaseUserRoleGroupQueryByIdListReq req);


}
